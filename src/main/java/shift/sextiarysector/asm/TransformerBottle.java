package shift.sextiarysector.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class TransformerBottle implements IClassTransformer, Opcodes {
    // 改変対象のクラスの完全修飾名です。
    // 後述でMinecraft.jar内の難読化されるファイルを対象とする場合の簡易な取得方法を紹介します。
    private static final String TARGET_CLASS_NAME = "net.minecraft.item.ItemGlassBottle";

    // クラスがロードされる際に呼び出されるメソッドです。
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        // FMLRelauncher.side() : Client/Server どちらか一方のを対象とする場合や、
        // 一つのMODで Client/Sever 両方に対応したMODで、この値を判定して処理を変える事ができます。
        // 今回は"CLIENT"と比較し、Client側のファイルを対象としている例です。
        // Client側専用のMODとして公開するのであれば、判定は必須ではありません。

        // name : 現在ロードされようとしているクラス名が格納されています。
        if (!transformedName.equals(TARGET_CLASS_NAME)) {

            // 処理対象外なので何もしない
            return basicClass;
        }

        try {
            // --------------------------------------------------------------
            // ASMを使用し、既存のクラスファイルに改変を施す場合。
            // --------------------------------------------------------------
            return hookOnItemRightClick(name, basicClass);

        } catch (Exception e) {
            throw new RuntimeException("failed : TransformerBottle loading", e);
        }
    }

    // 下記の想定で実装されています。
    // EntityLiving.class の doRenderLiving の先頭に
    // tutorial/test.class の passTestRender(EntityLiving, double, double, double)メソッドの呼び出しを追加する。
    private byte[] hookOnItemRightClick(String className, byte[] bytes) {
        // ASMで、bytesに格納されたクラスファイルを解析します。
        ClassNode cnode = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(cnode, 0);

        // 改変対象のメソッド名です
        String targetMethodName = "onItemRightClick";
        String targetMethodNameSRG = "func_77659_a";

        // 改変対象メソッドの戻り値型および、引数型をあらわします　※１
        String targetMethoddesc = "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;";
        String targetMethoddescSRG = "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;";

        // 対象のメソッドを検索取得します。
        MethodNode mnode = null;

        String mdesc = null;

        for (MethodNode curMnode : cnode.methods) {

            String mName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(className, curMnode.name, curMnode.desc);
            String mdName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(curMnode.desc);
            //System.out.println("[ " + mName + " : " + curMnode.name + " ]  [ " + mdName + " : " + curMnode.desc);
            if ((targetMethodName.equals(curMnode.name) && targetMethoddesc.equals(curMnode.desc)) || (targetMethodNameSRG.equals(mName) && targetMethoddescSRG.equals(mdName))) {
                mnode = curMnode;
                mdesc = curMnode.desc;
                break;
            }
        }

        if (mnode != null) {

            InsnList overrideList = new InsnList();

            // メソッドコールを、バイトコードであらわした例です。

            overrideList.add(new VarInsnNode(ALOAD, 1));
            overrideList.add(new VarInsnNode(ALOAD, 2));
            overrideList.add(new VarInsnNode(ALOAD, 3));
            overrideList.add(new MethodInsnNode(INVOKESTATIC,
                    "shift/sextiarysector/asm/vanilla/BottleMethod",
                    "onBottleRightClick",
                    mdesc, false));
            overrideList.add(new InsnNode(ARETURN));

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

}
