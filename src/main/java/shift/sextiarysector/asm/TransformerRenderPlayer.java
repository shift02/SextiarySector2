/*
* 作成者: Shift02
* 作成日: 2016/03/11 - 17:34:49
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

public class TransformerRenderPlayer implements IClassTransformer, Opcodes {

    // 改変対象のクラスの完全修飾名です。
    // 後述でMinecraft.jar内の難読化されるファイルを対象とする場合の簡易な取得方法を紹介します。
    private static final String TARGET_CLASS_NAME = "net.minecraft.client.renderer.entity.RenderPlayer";

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        try {
            if (TARGET_CLASS_NAME.equals(transformedName)) {
                System.out.println("start transform > RenderPlayer");
                ClassReader classReader = new ClassReader(bytes);
                ClassWriter classWriter = new ClassWriter(0);
                classReader.accept(new addFoodStatsHooksVisitor(name, classWriter), ClassReader.EXPAND_FRAMES);
                return classWriter.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("failed : RenderPlayer loading", e);
        }

        return bytes;
    }

    static class addFoodStatsHooksVisitor extends ClassVisitor {
        String owner;

        public addFoodStatsHooksVisitor(String owner, ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
            this.owner = owner;
        }

        private final Set<String> targetMethodName1 = Sets.newHashSet("shouldRenderPass", "func_77032_a");
        private final String targetMethoddesc1 = "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I";

        private boolean isTransformed1 = false;

        private final Set<String> targetMethodName2 = Sets.newHashSet("func_82408_c", "func_82408_c");
        private final String targetMethoddesc2 = "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V";

        private boolean isTransformed2 = false;

        private final Set<String> targetMethodName3 = Sets.newHashSet("renderEquippedItems", "func_77029_c");
        private final String targetMethoddesc3 = "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V";

        private boolean isTransformed3 = false;

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

            if (!isTransformed1) {

                FMLDeobfuscatingRemapper remap = FMLDeobfuscatingRemapper.INSTANCE;

                if (targetMethodName1.contains(remap.mapMethodName(owner, name, desc)) && targetMethoddesc1.equals(remap.mapMethodDesc(desc))) {
                    mv = new RenderPlayerMethodVisitor(owner, access, name, desc, mv);

                    isTransformed1 = true;

                }

            }

            if (!isTransformed2) {

                FMLDeobfuscatingRemapper remap = FMLDeobfuscatingRemapper.INSTANCE;

                if (targetMethodName2.contains(remap.mapMethodName(owner, name, desc)) && targetMethoddesc2.equals(remap.mapMethodDesc(desc))) {
                    mv = new RenderPlayerMethodVisitor(owner, access, name, desc, mv);

                    isTransformed2 = true;

                }

            }

            if (!isTransformed3) {

                FMLDeobfuscatingRemapper remap = FMLDeobfuscatingRemapper.INSTANCE;

                if (targetMethodName3.contains(remap.mapMethodName(owner, name, desc)) && targetMethoddesc3.equals(remap.mapMethodDesc(desc))) {
                    mv = new RenderPlayerMethodVisitor(owner, access, name, desc, mv);

                    isTransformed3 = true;

                }

            }

            return mv;
        }
    }

    static class RenderPlayerMethodVisitor extends AnalyzerAdapter {

        public RenderPlayerMethodVisitor(String owner, int access, String name, String desc, org.objectweb.asm.MethodVisitor mv) {
            super(Opcodes.ASM5, owner, access, name, desc, mv);
        }

        private final String targetoOwner = "net/minecraft/entity/player/InventoryPlayer";
        private final Set<String> targetName = Sets.newHashSet("armorItemInSlot", "func_70440_f");
        private final String targetDesc = "(I)Lnet/minecraft/item/ItemStack;";

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {

            FMLDeobfuscatingRemapper remap = FMLDeobfuscatingRemapper.INSTANCE;
            if (opcode == INVOKEVIRTUAL
                    && targetoOwner.equals(remap.map(owner))
                    && targetName.contains(remap.mapMethodName(owner, name, desc))
                    && targetDesc.equals(remap.mapMethodDesc(desc))) {

                //AbstractClientPlayerを積み
                mv.visitVarInsn(ALOAD, 1);
                //Methodを呼ぶ
                mv.visitMethodInsn(INVOKESTATIC, "shift/sextiarysector/asm/vanilla/InventoryPlayerMethod", "armorItemInSlot", "(ILnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/item/ItemStack;", false);

            } else
                super.visitMethodInsn(opcode, owner, name, desc, itf);
        }
    }
}
