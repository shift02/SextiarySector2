package shift.sextiarysector.item;

import java.text.NumberFormat;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.tileentity.TileEntityFluidCrafter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockFluidCrafter extends ItemBlock{

	public ItemBlockFluidCrafter(Block par1) {
		super(par1);
	}

	public int getMetadata(int p_77647_1_)
    {
        return p_77647_1_;
    }

	@Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
		boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ,metadata);

		if(result){

			if(stack.getItemDamage()!=0){
				TileEntityFluidCrafter tileEntity = (TileEntityFluidCrafter)world.getTileEntity(x, y, z);
				tileEntity.f = new FluidStack(FluidRegistry.getFluid(stack.getItemDamage()),1000);
			}

		}

		return result;
    }

	@SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));

		for(int i=1;i<=FluidRegistry.getRegisteredFluids().size();i++){
			ItemStack item = new ItemStack(p_150895_1_, 1, i);
			p_150895_3_.add(item);
		}

    }

	@Override
	public void addInformation(ItemStack itemstack,EntityPlayer par1EntityPlayer, List list , boolean flag)
    {

		String name = "None";
		int amount = 0;

		if(itemstack.getItemDamage()!=0){
			name = FluidRegistry.getFluid(itemstack.getItemDamage()).getLocalizedName();
			amount = 1000;
		}

		NumberFormat nfNum = NumberFormat.getNumberInstance();

		list.add("Name" +" : " + name);
		list.add("Amount" +" : " + nfNum.format(amount) + " / " + nfNum.format(1000) + " mB");

    }
}
