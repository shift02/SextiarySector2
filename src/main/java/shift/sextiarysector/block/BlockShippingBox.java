package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.module.ModuleAchievement;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockShippingBox extends Block{

	private IIcon top;

	public BlockShippingBox() {
		super(Material.wood);
		this.setBlockUnbreakable();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.getCurrentEquippedItem()==null){
			return false;
		}

		if(!world.isRemote && par5EntityPlayer.getCurrentEquippedItem().getItem()==SSItems.hammer){

			EntityItem item = new EntityItem(world, x+0.5d, y+0.5d, z+0.5d, new ItemStack(this,1));

			world.spawnEntityInWorld(item);

			world.setBlockToAir(x, y, z);
			return true;
		}

		ItemStack item = par5EntityPlayer.getCurrentEquippedItem();

		int i = MCEconomyAPI.getPurchase(item);
		if(i==-2){
			return false;
		}

		if(i==-1){
			return false;
		}

		MCEconomyAPI.addPlayerMP(par5EntityPlayer, i,false);
		par5EntityPlayer.addStat(ModuleAchievement.objectSellStats[Item.getIdFromItem(item.getItem())], 1);
		par5EntityPlayer.addStat(ModuleAchievement.shipping, 1);
		item.stackSize--;
		world.playSoundAtEntity(par5EntityPlayer, "damage.fallsmall", 1.0F, 1.0F);
		return true;

	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());
        this.top = par1IconRegister.registerIcon(this.getTextureName()+"_top");

    }

	@Override
	public IIcon getIcon(int par1, int par2)
    {
		if(par1==0||par1==1){
			return this.top;
		}
        return this.blockIcon;
    }

}
