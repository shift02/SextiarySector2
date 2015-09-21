package shift.sextiarysector.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.item.IHammer;

public class ItemSpanner extends Item implements IHammer {

    public ItemSpanner() {

        this.setHarvestLevel("spanner", 1);
        this.maxStackSize = 1;
        this.setMaxDamage(140);

    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (ForgeHooks.isToolEffective(stack, block, meta)) {
            return 6.0f;
        }
        return super.getDigSpeed(stack, block, meta);
    }

    /*
    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
    
    	if (block == Blocks.anvil) {
    
    		if (p_77648_3_.isRemote) return true;
    
    		p_77648_2_.openGui(SextiarySector.instance, 10, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_);
    		return true;
    
    	}
    
    	return false;
    }*/

    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {

        Block block = p_77648_3_.getBlock(x, y, z);

        ForgeDirection[] d = block.getValidRotations(p_77648_3_, x, y, z);

        boolean f = block.rotateBlock(p_77648_3_, x, y, z, d[0]);

        if (f) {

            if (!p_77648_3_.isRemote) {
                p_77648_3_.playSoundEffect(x, y, z, block.stepSound.getStepResourcePath(), 1.0F, p_77648_3_.rand.nextFloat() * 0.1F + 0.6F);
                //p_77648_2_.swingItem();
                p_77648_1_.damageItem(3, p_77648_2_);
            }

            //p_77648_1_.damageItem(3, p_77648_2_);
        }

        return f;

    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {

        p_77659_3_.swingItem();
        return p_77659_1_;

    }

    @Override
    public boolean canUse(ItemStack item, EntityPlayer player, int damage) {
        return true;
    }

    @Override
    public boolean use(ItemStack item, EntityPlayer player, int damage) {

        //item.setItemDamage(damage);
        item.damageItem(damage, player);

        return true;

    }

    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
        p_77644_1_.damageItem(2, p_77644_3_);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
        if (p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D) {
            p_150894_1_.damageItem(1, p_150894_7_);
        }

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

}
