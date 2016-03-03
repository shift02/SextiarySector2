/*
* 作成者: Shift02
* 作成日: 2016/03/02 - 15:09:10
*/
package shift.sextiarysector.plugin.lmm;

import littleMaidMobX.LMM_EntityLittleMaid;
import littleMaidMobX.LMM_EntityModeBase;
import littleMaidMobX.LMM_EnumSound;
import littleMaidMobX.LMM_TriggerSelect;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
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

        TileEntity t = worldObj.getTileEntity(px, py, pz);

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

    @Override
    public void resetBlock(int pMode) {
        //owner.setSneaking(false);
    }

}
