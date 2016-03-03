/*
* 作成者: Shift02
* 作成日: 2016/03/02 - 15:09:10
*/
package shift.sextiarysector.plugin.lmm;

import littleMaidMobX.LMM_EntityLittleMaid;
import littleMaidMobX.LMM_EntityModeBase;
import littleMaidMobX.LMM_EnumSound;
import littleMaidMobX.LMM_TriggerSelect;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.agriculture.TileFarmland;

public class LMM_EntityMode_Watering extends LMM_EntityModeBase {

    public static final int mmode_Watering = 0x0120;
    public static final String MODE_NAME = "Watering";

    public LMM_EntityMode_Watering(LMM_EntityLittleMaid pEntity) {
        super(pEntity);
    }

    @Override
    public int priority() {
        return 3010;
    }

    @Override
    public void init() {

        /* langファイルに移動
        ModLoader.addLocalization("littleMaidMob.mode.Torcher", "Torcher");
        ModLoader.addLocalization("littleMaidMob.mode.F-Torcher", "F-Torcher");
        ModLoader.addLocalization("littleMaidMob.mode.D-Torcher", "D-Torcher");
        ModLoader.addLocalization("littleMaidMob.mode.T-Torcher", "T-Torcher");
        */
        LMM_TriggerSelect.appendTriggerItem(null, MODE_NAME, "");

    }

    @Override
    public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {
        // Torcher:0x0020
        EntityAITasks[] ltasks = new EntityAITasks[2];
        ltasks[0] = pDefaultMove;
        ltasks[1] = pDefaultTargeting;

        owner.addMaidMode(ltasks, MODE_NAME, mmode_Watering);
    }

    @Override
    public boolean changeMode(EntityPlayer pentityplayer) {

        ItemStack litemstack = owner.maidInventory.getStackInSlot(0);
        if (litemstack != null) {
            if (litemstack.getItem() == SSItems.woodWateringCan || LMM_TriggerSelect.checkWeapon(owner.getMaidMaster(), MODE_NAME, litemstack)) {
                owner.setMaidMode(MODE_NAME);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setMode(int pMode) {
        switch (pMode) {
            case mmode_Watering:
                owner.setBloodsuck(false);
                owner.aiAttack.setEnable(false);
                owner.aiShooting.setEnable(false);
                return true;
        }

        return false;
    }

    @Override
    public int getNextEquipItem(int pMode) {
        int li;
        ItemStack litemstack;

        // モードに応じた識別判定、速度優先
        switch (pMode) {
            case mmode_Watering:
                for (li = 0; li < owner.maidInventory.maxInventorySize; li++) {

                    litemstack = owner.maidInventory.getStackInSlot(li);
                    if (litemstack == null) continue;

                    // ジョウロ
                    if (litemstack.getItem() == SSItems.woodWateringCan || LMM_TriggerSelect.checkWeapon(owner.getMaidMaster(), MODE_NAME, litemstack)) {
                        return li;
                    }

                }
                break;
        }

        return -1;
    }

    @Override
    public boolean checkItemStack(ItemStack pItemStack) {
        return false;//pItemStack.getItem() == Item.getItemFromBlock(Blocks.lever);
    }

    @Override
    public boolean isSearchBlock() {
        return true;
    }

    @Override
    public boolean shouldBlock(int pMode) {
        return !(owner.getCurrentEquippedItem() == null);
    }

    protected int getBlockLighting(int i, int j, int k) {
        World worldObj = owner.worldObj;
        if (worldObj.getBlock(i, j - 1, k).getMaterial().isSolid() && worldObj.getBlock(i, j, k) == Blocks.air) {
            return worldObj.getBlockLightValue(i, j, k);
        }
        return 32;
    }

    @Override
    public boolean checkBlock(int pMode, int px, int py, int pz) {

        if (pMode != mmode_Watering) return false;

        World worldObj = owner.worldObj;

        owner.getNextEquipItem();
        ItemStack lis = owner.getCurrentEquippedItem();
        //水の補給
        if (lis.getMaxDamage() - lis.getItemDamage() == 1) return checkBlockWater(pMode, px, py, pz);

        int i = 0;
        if (py != 0) i = 1;

        TileEntity t = worldObj.getTileEntity(px, py - i, pz);

        if (!(t instanceof TileFarmland)) return false;

        TileFarmland tf = (TileFarmland) t;

        if (tf.hasWater()) return false;

        return true;

        //        int v = getBlockLighting(px, py, pz);
        //        if (v < 8 && canBlockBeSeen(px, py - 1, pz, true, true, false)) {
        //            if (owner.getNavigator().tryMoveToXYZ(px, py, pz, 1.0F)) {
        //                owner.playSound(LMM_EnumSound.findTarget_D, false);
        //                return true;
        //            }
        //        }
        //        return false;
    }

    public boolean checkBlockWater(int pMode, int px, int py, int pz) {

        World worldObj = owner.worldObj;

        if (worldObj.getBlock(px, py, pz).getMaterial() != Material.water) return false;

        return true;

    }

    @Override
    public boolean executeBlock(int pMode, int px, int py, int pz) {
        /*
        ItemStack lis = owner.getCurrentEquippedItem();
        if (lis == null) return false;
        
        int li = lis.stackSize;
        // TODO:当たり判定をどうするか
        if (lis.tryPlaceItemIntoWorld(owner.maidAvatar, owner.worldObj, px, py - 1, pz, 1, 0.5F, 1.0F, 0.5F)) {
            owner.setSwing(10, LMM_EnumSound.installation);
        
            if (owner.maidAvatar.capabilities.isCreativeMode) {
                lis.stackSize = li;
            }
            if (lis.stackSize <= 0) {
                owner.maidInventory.setInventoryCurrentSlotContents(null);
                owner.getNextEquipItem();
            }
        }
        */
        return false;
    }

    @Override
    public void onUpdate(int pMode) {

        // 水やり
        if (pMode == mmode_Watering && owner.getNextEquipItem()) {
            ItemStack lis = owner.getCurrentEquippedItem();

            if (lis.getMaxDamage() - lis.getItemDamage() == 1) {
                this.onUpdateWater(pMode, lis);
                return;
            }

            int lic = lis.stackSize;
            Item lii = lis.getItem();
            World lworld = owner.worldObj;

            // 周囲を検索
            int lxx = MathHelper.floor_double(owner.posX);
            int lyy = MathHelper.floor_double(owner.posY);
            int lzz = MathHelper.floor_double(owner.posZ);
            int lym = MathHelper.floor_float(owner.height) + 1;

            //調べる
            boolean f = false;
            for (int y = -1; y < 2; y++) {

                if (owner.worldObj.isAirBlock(lxx, lyy + y, lzz)) continue;

                TileEntity t = owner.worldObj.getTileEntity(lxx, lyy + y, lzz);

                if (!(t instanceof TileFarmland)) continue;
                TileFarmland tf = (TileFarmland) t;

                if (!tf.hasWater()) {

                    lyy += y;
                    f = true;
                    break;
                }

            }

            if (!f) return;

            if (lii.onItemUse(lis, owner.maidAvatar, lworld, lxx, lyy, lzz, 1, 0.5f, 0.5f, 0.5f)) {

                owner.setSwing(10, LMM_EnumSound.installation);
                owner.setMaidWaitCount(6);
            }

        }
    }

    public void onUpdateWater(int pMode, ItemStack lis) {
        this.scoopWater(lis, owner.worldObj, owner.maidAvatar);
    }

    public ItemStack scoopWater(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

        if (movingobjectposition == null) {
            return par1ItemStack;
        } else {
            if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!par2World.canMineBlock(par3EntityPlayer, i, j, k)) {
                    return par1ItemStack;
                }

                if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack)) {
                    return par1ItemStack;
                }

                if (par2World.getBlock(i, j, k).getMaterial() == Material.water) {
                    par2World.playSoundAtEntity(par3EntityPlayer, "liquid.swim", 1.0F, 1.0F);
                    par1ItemStack.setItemDamage(1);
                }
            }

            return par1ItemStack;
        }
    }

    protected MovingObjectPosition getMovingObjectPositionFromPlayer(World p_77621_1_, EntityPlayer p_77621_2_, boolean p_77621_3_) {
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

    @Override
    public void resetBlock(int pMode) {
        //owner.setSneaking(false);
    }

}
