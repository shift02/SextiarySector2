package shift.sextiarysector.asm.vanilla;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;

public class BottleMethod {

    public static ItemStack onBottleRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {

        MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(p_77659_2_, p_77659_3_, true);

        if (movingobjectposition == null) {
            return p_77659_1_;
        } else {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!p_77659_2_.canMineBlock(p_77659_3_, i, j, k)) {
                    return p_77659_1_;
                }

                if (!p_77659_3_.canPlayerEdit(i, j, k, movingobjectposition.sideHit, p_77659_1_)) {
                    return p_77659_1_;
                }

                Block block = p_77659_2_.getBlock(i, j, k);

                if (block == Blocks.water) {

                    --p_77659_1_.stackSize;

                    if (p_77659_1_.stackSize <= 0) {
                        return new ItemStack(Items.potionitem);
                    }

                    if (!p_77659_3_.inventory.addItemStackToInventory(new ItemStack(Items.potionitem))) {
                        p_77659_3_.dropPlayerItemWithRandomChoice(new ItemStack(Items.potionitem, 1, 0), false);
                    }

                }

                if (block == Blocks.lava) {

                    --p_77659_1_.stackSize;

                    p_77659_2_.setBlockToAir(i, j, k);

                    if (p_77659_1_.stackSize <= 0) {
                        return new ItemStack(SSItems.lavaBottle);
                    }

                    if (!p_77659_3_.inventory.addItemStackToInventory(new ItemStack(SSItems.lavaBottle))) {
                        p_77659_3_.dropPlayerItemWithRandomChoice(new ItemStack(SSItems.lavaBottle, 1, 0), false);
                    }

                }

                if (block == SSBlocks.drinkingWater) {

                    --p_77659_1_.stackSize;

                    //p_77659_2_.setBlockToAir(i, j, k);

                    if (p_77659_1_.stackSize <= 0) {
                        return new ItemStack(SSItems.drinkingWaterBottle);
                    }

                    if (!p_77659_3_.inventory.addItemStackToInventory(new ItemStack(SSItems.drinkingWaterBottle))) {
                        p_77659_3_.dropPlayerItemWithRandomChoice(new ItemStack(SSItems.drinkingWaterBottle, 1, 0), false);
                    }

                }

            }

            return p_77659_1_;
        }

    }

    protected static MovingObjectPosition getMovingObjectPositionFromPlayer(World p_77621_1_, EntityPlayer p_77621_2_, boolean p_77621_3_) {
        float f = 1.0F;
        float f1 = p_77621_2_.prevRotationPitch + (p_77621_2_.rotationPitch - p_77621_2_.prevRotationPitch) * f;
        float f2 = p_77621_2_.prevRotationYaw + (p_77621_2_.rotationYaw - p_77621_2_.prevRotationYaw) * f;
        double d0 = p_77621_2_.prevPosX + (p_77621_2_.posX - p_77621_2_.prevPosX) * f;
        double d1 = p_77621_2_.prevPosY + (p_77621_2_.posY - p_77621_2_.prevPosY) * f + (p_77621_1_.isRemote ? p_77621_2_.getEyeHeight() - p_77621_2_.getDefaultEyeHeight() : p_77621_2_.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
        double d2 = p_77621_2_.prevPosZ + (p_77621_2_.posZ - p_77621_2_.prevPosZ) * f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        if (p_77621_2_ instanceof EntityPlayerMP) {
            d3 = ((EntityPlayerMP) p_77621_2_).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
        return p_77621_1_.func_147447_a(vec3, vec31, p_77621_3_, !p_77621_3_, false);
    }

}
