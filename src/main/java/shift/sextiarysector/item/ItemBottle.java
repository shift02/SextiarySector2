package shift.sextiarysector.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.event.FillBottleEvent;
import cpw.mods.fml.common.eventhandler.Event;

public class ItemBottle extends Item{

	public ItemBottle(){
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {

        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(p_77659_2_, p_77659_3_, true);

        if (movingobjectposition == null) return p_77659_1_;

        FillBottleEvent event = new FillBottleEvent(p_77659_3_, p_77659_1_, p_77659_2_, movingobjectposition);
        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return p_77659_1_;
        }

        if (event.getResult() == Event.Result.ALLOW)
        {
            if (p_77659_3_.capabilities.isCreativeMode)
            {
                return p_77659_1_;
            }

            if (--p_77659_1_.stackSize <= 0)
            {
                return event.result;
            }

            if (!p_77659_3_.inventory.addItemStackToInventory(event.result))
            {
                p_77659_3_.dropPlayerItemWithRandomChoice(event.result, false);
            }

            return p_77659_1_;

        }

        if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;

            if (!p_77659_2_.canMineBlock(p_77659_3_, i, j, k))
            {
                return p_77659_1_;
            }

            if (!p_77659_3_.canPlayerEdit(i, j, k, movingobjectposition.sideHit, p_77659_1_))
            {
                return p_77659_1_;
            }

            Block block = p_77659_2_.getBlock(i, j, k);
            int l = p_77659_2_.getBlockMetadata(i, j, k);

            if (block == Blocks.water && l == 0)
            {
                //p_77659_2_.setBlockToAir(i, j, k);
                return this.func_150910_a(p_77659_1_, p_77659_3_, SSItems.waterBottle);
            }

            if (block == Blocks.lava && l == 0)
            {
                p_77659_2_.setBlockToAir(i, j, k);
                return this.func_150910_a(p_77659_1_, p_77659_3_, SSItems.lavaBottle);
            }

            if (block == SSBlocks.drinkingWater && l == 0)
            {
                //p_77659_2_.setBlockToAir(i, j, k);
                return this.func_150910_a(p_77659_1_, p_77659_3_, SSItems.drinkingWaterBottle);
            }

        }

        return p_77659_1_;

    }


	private ItemStack func_150910_a(ItemStack p_150910_1_, EntityPlayer p_150910_2_, Item p_150910_3_)
    {
        if (p_150910_2_.capabilities.isCreativeMode) return p_150910_1_;

        if (--p_150910_1_.stackSize <= 0) return new ItemStack(p_150910_3_);

        if (!p_150910_2_.inventory.addItemStackToInventory(new ItemStack(p_150910_3_)))
        {
            p_150910_2_.dropPlayerItemWithRandomChoice(new ItemStack(p_150910_3_, 1, 0), false);
        }

        return p_150910_1_;

    }

}
