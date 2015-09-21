package shift.sextiarysector.asm;

import java.util.Set;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AnalyzerAdapter;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class TransformerEntityPlayerSP implements IClassTransformer, Opcodes {
    // 改変対象のクラスの完全修飾名です。
    // 後述でMinecraft.jar内の難読化されるファイルを対象とする場合の簡易な取得方法を紹介します。
    private static final String TARGET_CLASS_NAME = "net.minecraft.client.entity.EntityPlayerSP";

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        try {
            final String targetClassName = "net.minecraft.client.entity.EntityPlayerSP";
            if (targetClassName.equals(transformedName)) {
                System.out.println("start transform > EntityPlayerSP");
                ClassReader classReader = new ClassReader(bytes);
                ClassWriter classWriter = new ClassWriter(0);
                classReader.accept(new addHooksVisitor(name, classWriter), ClassReader.EXPAND_FRAMES);
                return classWriter.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("failed : EntityPlayerSP loading", e);
        }

        return bytes;
    }

    static class addHooksVisitor extends ClassVisitor {
        String owner;

        public addHooksVisitor(String owner, ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
            this.owner = owner;
        }

        private final Set<String> targetMethodName = Sets.newHashSet("onLivingUpdate", "func_70636_d");
        private final String targetMethoddesc = "()V";

        private boolean isTransformed = false;

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

            if (!isTransformed) {

                FMLDeobfuscatingRemapper remap = FMLDeobfuscatingRemapper.INSTANCE;

                if (targetMethodName.contains(remap.mapMethodName(owner, name, desc)) && targetMethoddesc.equals(remap.mapMethodDesc(desc))) {
                    mv = new AddHookMethodVisitor(owner, access, name, desc, mv);

                    isTransformed = true;

                }

            }

            return mv;
        }
    }

    static class AddHookMethodVisitor extends AnalyzerAdapter {

        protected AddHookMethodVisitor(String owner, int access, String name, String desc, MethodVisitor mv) {
            super(Opcodes.ASM5, owner, access, name, desc, mv);
        }

        private final String targetoOwner = "net/minecraft/util/FoodStats";
        private final Set<String> targetName = Sets.newHashSet("getFoodLevel", "func_75116_a");
        private final String targetDesc = "()I";

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            super.visitMethodInsn(opcode, owner, name, desc, itf);

            FMLDeobfuscatingRemapper remap = FMLDeobfuscatingRemapper.INSTANCE;
            if (opcode == Opcodes.INVOKEVIRTUAL
                    && targetoOwner.equals(remap.map(owner))
                    && targetName.contains(remap.mapMethodName(owner, name, desc))
                    && targetDesc.equals(remap.mapMethodDesc(desc))) {

                //mv.visitVarInsn(ALOAD, 1);

                this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "shift/sextiarysector/asm/vanilla/EntityPlayerSPMethod", "isFlag", "(I)I", false);

            }
        }
    }

    /*
    // クラスがロードされる際に呼び出されるメソッドです。
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
    	// FMLRelauncher.side() : Client/Server どちらか一方のを対象とする場合や、
    	// 一つのMODで Client/Sever 両方に対応したMODで、この値を判定して処理を変える事ができます。
    	// 今回は"CLIENT"と比較し、Client側のファイルを対象としている例です。
    	// Client側専用のMODとして公開するのであれば、判定は必須ではありません。
    
    	// name : 現在ロードされようとしているクラス名が格納されています。
    	if (!transformedName.equals(TARGET_CLASS_NAME))
    	{
    
    		// 処理対象外なので何もしない
    		return basicClass;
    	}
    
    	try
    	{
    		// --------------------------------------------------------------
    		// ASMを使用し、既存のクラスファイルに改変を施す場合。
    		// --------------------------------------------------------------
    		return hookOnItemRightClick(name, basicClass);
    
    	} catch (Exception e)
    	{
    		throw new RuntimeException("failed : TransformerBottle loading", e);
    	}
    }
    
    // 下記の想定で実装されています。
    // EntityLiving.class の doRenderLiving の先頭に
    // tutorial/test.class の passTestRender(EntityLiving, double, double, double)メソッドの呼び出しを追加する。
    private byte[] hookOnItemRightClick(String className, byte[] bytes)
    {
    	// ASMで、bytesに格納されたクラスファイルを解析します。
    	ClassNode cnode = new ClassNode();
    	ClassReader reader = new ClassReader(bytes);
    	reader.accept(cnode, 0);
    
    	// 改変対象のメソッド名です
    	String targetMethodName = "onLivingUpdate";//"setSprinting";
    	String targetMethodNameSRG = "func_70636_d";//"func_70031_b";
    
    	// 改変対象メソッドの戻り値型および、引数型をあらわします　※１
    	String targetMethoddesc = "()V";//"(Z)V";
    	String targetMethoddescSRG = "(Z)V";
    
    	// 対象のメソッドを検索取得します。
    	MethodNode mnode = null;
    
    	String mdesc = null;
    
    	for (MethodNode curMnode : cnode.methods)
    	{
    
    		String mName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(className, curMnode.name, curMnode.desc);
    		String mdName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(curMnode.desc);
    		System.out.println("[ " + mName + " : " + curMnode.name + " ]  [ " + mdName + " : " + curMnode.desc);
    		if ((targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) || (targetMethodNameSRG.equals(mName) && targetMethoddescSRG.equals(mdName)))
    		{
    			mnode = curMnode;
    			mdesc = curMnode.desc;
    			break;
    		}
    	}
    
    	if (mnode != null)
    	{
    
    		InsnList overrideList = new InsnList();
    
    		// メソッドコールを、バイトコードであらわした例です。
    
    		overrideList.add(new VarInsnNode(ILOAD, 1));
    		overrideList.add(new MethodInsnNode(INVOKESTATIC,
    				"shift/sextiarysector/asm/vanilla/EntityPlayerSPMethod",
    				"isSprinting",
    				"(Z)Z"
    				, false));
    		Label l8 = new Label();
    		overrideList.add(new JumpInsnNode(IFEQ, this.getLabelNode(l8)));
    		overrideList.add(new InsnNode(RETURN));
    		overrideList.add(this.getLabelNode(l8));
    
    		// mnode.instructions.get(1)で、対象のメソッドの先頭を取得
    		// mnode.instructions.insertで、指定した位置にバイトコードを挿入します。
    		mnode.instructions.insert(mnode.instructions.get(1), overrideList);
    
    		//mnode.maxLocals = 4;
    
    		// 改変したクラスファイルをバイト列に書き出します
    		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    		cnode.accept(cw);
    		bytes = cw.toByteArray();
    
    		System.out.println("bbbb");
    	}
    
    	return bytes;
    }
    
    protected LabelNode getLabelNode(final Label l) {
    	if (!(l.info instanceof LabelNode)) {
    		l.info = new LabelNode();
    	}
    	return (LabelNode) l.info;
    }
    
    */
}
