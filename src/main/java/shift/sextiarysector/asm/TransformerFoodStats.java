/*
* 作成者: Shift02
* 作成日: 2016/02/28 - 11:21:12
*/
package shift.sextiarysector.asm;

import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AnalyzerAdapter;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraft.launchwrapper.IClassTransformer;

public class TransformerFoodStats implements IClassTransformer, Opcodes {

    // 改変対象のクラスの完全修飾名です。
    // 後述でMinecraft.jar内の難読化されるファイルを対象とする場合の簡易な取得方法を紹介します。
    private static final String TARGET_CLASS_NAME = "net.minecraft.util.FoodStats";

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        try {
            final String targetClassName = "net.minecraft.util.FoodStats";
            if (targetClassName.equals(transformedName)) {
                System.out.println("start transform > FoodStats");
                ClassReader classReader = new ClassReader(bytes);
                ClassWriter classWriter = new ClassWriter(0);
                classReader.accept(new addFoodStatsHooksVisitor(name, classWriter), ClassReader.EXPAND_FRAMES);
                return classWriter.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("failed : FoodStats loading", e);
        }

        return bytes;
    }

    static class addFoodStatsHooksVisitor extends ClassVisitor {
        String owner;

        public addFoodStatsHooksVisitor(String owner, ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
            this.owner = owner;
        }

        private final Set<String> targetMethodName = Sets.newHashSet("onUpdate", "func_75118_a");
        private final String targetMethoddesc = "(Lnet/minecraft/entity/player/EntityPlayer;)V";

        private boolean isTransformed = false;

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

            if (!isTransformed) {

                FMLDeobfuscatingRemapper remap = FMLDeobfuscatingRemapper.INSTANCE;

                if (targetMethodName.contains(remap.mapMethodName(owner, name, desc)) && targetMethoddesc.equals(remap.mapMethodDesc(desc))) {
                    mv = new FoodStatusMethodVisitor(owner, access, name, desc, mv);

                    isTransformed = true;

                }

            }

            return mv;
        }
    }

    static class FoodStatusMethodVisitor extends AnalyzerAdapter {

        public FoodStatusMethodVisitor(String owner, int access, String name, String desc, org.objectweb.asm.MethodVisitor mv) {
            super(Opcodes.ASM5, owner, access, name, desc, mv);
        }

        private final String targetoOwner = "net/minecraft/entity/player/EntityPlayer";
        private final Set<String> targetName = Sets.newHashSet("attackEntityFrom", "func_70097_a");
        private final String targetDesc = "(Lnet/minecraft/util/DamageSource;F)Z";

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {

            FMLDeobfuscatingRemapper remap = FMLDeobfuscatingRemapper.INSTANCE;
            if (opcode == INVOKEVIRTUAL
                    && targetoOwner.equals(remap.map(owner))
                    && targetName.contains(remap.mapMethodName(owner, name, desc))
                    && targetDesc.equals(remap.mapMethodDesc(desc))) {
                //すでに積んである1を積みおろす
                //mv.visitInsn(FCONST_1);
                mv.visitInsn(POP);
                mv.visitInsn(POP);

                mv.visitLdcInsn(new Float("0.5"));
                mv.visitMethodInsn(INVOKESTATIC, "shift/sextiarysector/asm/vanilla/FoodStatsMethod", "onExhaustion", "(Lnet/minecraft/entity/player/EntityPlayer;F)V", false);

                //attackEntityFromの戻り値boolを破棄するpop命令が続くので、スタックにダミー値を積んでスタックのずれを解消する
                mv.visitInsn(FCONST_1);

            } else
                super.visitMethodInsn(opcode, owner, name, desc, itf);
        }
    }
}
