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
		this.setHasSubtypes(true);
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

	/*
	//アイテム
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,int par5, int par6, int par7, float par8, float par9, float par10)
	{

		if (par2EntityPlayer.isSneaking()) {
			return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9,par10);
		} else {
			return false;
		}

	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

		if (par3EntityPlayer.isSneaking()) {
			return par1ItemStack;
		} else {

			if(par1ItemStack.getItemDamage()==0){
				return this.onEmptyItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
			}

		}


		return par1ItemStack;

    }

	private ItemStack onEmptyItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);


		if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            int x = movingobjectposition.blockX;
            int y = movingobjectposition.blockY;
            int z = movingobjectposition.blockZ;

            if (!par3EntityPlayer.canPlayerEdit(x, y, z, movingobjectposition.sideHit, par1ItemStack))
            {
                return par1ItemStack;
            }

            Block block = par2World.getBlock(x, y, z);

            Fluid f = FluidRegistry.lookupFluidForBlock(block);

            if(f!=null){

            	if (par3EntityPlayer.capabilities.isCreativeMode)
                {
                    return par1ItemStack;
                }

            	ItemStack item = par1ItemStack.copy();
            	item.stackSize = 1;
            	item.setItemDamage(f.getID());

                if (--par1ItemStack.stackSize <= 0)
                {
                    return item;
                }

                if (!par3EntityPlayer.inventory.addItemStackToInventory(item))
                {
                	par3EntityPlayer.dropPlayerItemWithRandomChoice(item, false);
                }

            	par2World.setBlockToAir(x, y, z);
            }

        }


		return par1ItemStack;
    }*/

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
