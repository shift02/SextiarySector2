package shift.sextiarysector.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.block.BlockFigure;
import shift.sextiarysector.module.ModuleFigure;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFigureBox extends Item{

	public ItemFigureBox() {
		this.hasSubtypes = true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {

		NBTTagCompound nbt = p_77659_1_.getTagCompound();

		ItemStack item = ModuleFigure.getFigure(p_77659_2_, nbt.getString("edition"));

		if(item!=null){

			if(p_77659_2_.isRemote)return p_77659_1_;
			ItemStack f = new ItemStack(SSBlocks.figure);
			BlockFigure.setFigureItem(f, item, nbt.getString("edition"));
			p_77659_3_.entityDropItem(f , 0.5f);
			p_77659_1_.stackSize--;

		}

		return p_77659_1_;
    }

	@SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {

		for(String edition : ModuleFigure.getInstance().figures.keySet()){

			p_150895_3_.add(getEditionFigureBox(edition));

		}

    }

	public ItemStack getEditionFigureBox(String edition){

		ItemStack item = new ItemStack(this, 1, 0);
		NBTTagCompound nbt = new NBTTagCompound();
		item.setTagCompound(nbt);
		nbt.setString("edition", edition);
		return item;

	}

	@Override
	public void addInformation(ItemStack itemstack,EntityPlayer par1EntityPlayer, List list , boolean flag)
    {


		NBTTagCompound nbt = itemstack.getTagCompound();

		list.add(StatCollector.translateToLocal("tooltip.edition") + " : " + StatCollector.translateToLocal("edition."+nbt.getString("edition")));


    }

}
