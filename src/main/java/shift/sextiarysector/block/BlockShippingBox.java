package shift.sextiarysector.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.SSMaterials;
import shift.sextiarysector.SextiarySector;

public class BlockShippingBox extends Block {//extends BlockContainer {

    private IIcon top;

    public BlockShippingBox() {
        super(SSMaterials.machine);
        this.setHardness(0.9f);
        this.setHarvestLevel("spanner", 1);
        //this.setBlockUnbreakable();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

        if (world.isRemote) {
            return true;
        } else {
            par5EntityPlayer.openGui(SextiarySector.instance, 210, world, x, y, z);

            return true;
        }

        /*
        TileEntityShippingBox s = (TileEntityShippingBox) world.getTileEntity(x, y, z);
        ItemStack pItem = par5EntityPlayer.getCurrentEquippedItem();
        
        if (!par5EntityPlayer.isSneaking() && pItem == null) {
        
        	String name = par5EntityPlayer.getDisplayName();
        
        	if (s.player == null) {
        
        		s.setPlayer(par5EntityPlayer);
        
        		if (!world.isRemote) par5EntityPlayer.addChatMessage(new ChatComponentText("Set Owner : " + name));
        
        	} else if (s.player.equals(par5EntityPlayer.getGameProfile().getId())) {
        
        		s.player = null;
        
        		if (!world.isRemote) par5EntityPlayer.addChatMessage(new ChatComponentText("Delete Owner : " + name));
        
        	}
        
        	return true;
        }
        
        if (par5EntityPlayer.isSneaking() && pItem == null && (s.player == null || s.player.equals(par5EntityPlayer.getGameProfile().getId()))) {
        
        	int mp = (int) s.mp;
        
        	if (mp == 0) return true;
        
        	MCEconomyAPI.addPlayerMP(par5EntityPlayer, mp, false);
        	s.mp -= mp;
        	par5EntityPlayer.worldObj.playSoundAtEntity(par5EntityPlayer, "mceconomy2:coin", 0.6f, 0.8f);
        
        	return true;
        
        }
        
        if (pItem == null) return false;
        
        if (!world.isRemote && pItem.getItem() == SSItems.hammer) {
        
        	if (s.player != null && !s.player.equals(par5EntityPlayer.getGameProfile().getId())) return false;
        
        	EntityItem item = new EntityItem(world, x + 0.5d, y + 0.5d, z + 0.5d, new ItemStack(this, 1));
        
        	world.spawnEntityInWorld(item);
        
        	world.setBlockToAir(x, y, z);
        	return true;
        }
        
        int i = MCEconomyAPI.getPurchase(pItem);
        if (i == -2) {
        	return false;
        }
        
        if (i == -1) {
        	return false;
        }
        */

        /*
        MCEconomyAPI.addPlayerMP(par5EntityPlayer, i, false);
        par5EntityPlayer.addStat(ModuleStatistics.objectSellStats[Item.getIdFromItem(item.getItem())], 1);
        par5EntityPlayer.addStat(SSAchievement.shipping, 1);
        */

        /*
        s.setInventorySlotContents(0, pItem.copy());
        pItem.stackSize = 0;
        //item.stackSize--;
        world.playSoundAtEntity(par5EntityPlayer, "game.neutral.hurt.fall.small", 1.0F, 1.0F);
        return true;
        */

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());
        this.top = par1IconRegister.registerIcon(this.getTextureName() + "_top");

    }

    @Override
    public IIcon getIcon(int par1, int par2) {
        if (par1 == 0 || par1 == 1) {
            return this.top;
        }
        return this.blockIcon;
    }

    /*
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
    	return new TileEntityShippingBox();
    }*/

}
