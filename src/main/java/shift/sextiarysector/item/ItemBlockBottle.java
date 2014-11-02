package shift.sextiarysector.item;

import java.text.NumberFormat;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.tileentity.TileEntityBlockBottle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockBottle extends ItemBlock  implements IFluidContainerItem{

	protected int capacity = FluidContainerRegistry.BUCKET_VOLUME*64;

	public ItemBlockBottle(Block block){
		super(block);
		this.setHasSubtypes(true);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	/* Block */
	@Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
		boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ,metadata);

		if(result && world.getTileEntity(x, y, z) instanceof TileEntityBlockBottle){
			TileEntityBlockBottle tile = (TileEntityBlockBottle)world.getTileEntity(x, y, z);
			tile.fill(ForgeDirection.UP, this.getFluid(stack), true);
		}

		return result;

    }



	/* Item */
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
		} else if(this.getFluid(par1ItemStack)!=null && this.getFluid(par1ItemStack).amount>=1000 && this.canDrink(par3EntityPlayer, false)){
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

		}
		return par1ItemStack;

    }

	@Override
	public ItemStack onEaten(ItemStack stack, World par2World,EntityPlayer par3EntityPlayer) {


		this.drain(stack, 1000, true);

		return stack;
		//--stack.stackSize;

	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
		return EnumAction.drink;
    }

	public boolean canDrink(EntityPlayer par3EntityPlayer,boolean par1)
    {
        return (par1 || EntityPlayerManager.getMoistureStats(par3EntityPlayer).needMoisture()) && !par3EntityPlayer.capabilities.disableDamage;
    }

	public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 32;
    }



	@SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));

		for(int i=1;i<FluidRegistry.getRegisteredFluids().size();i++){
			ItemStack item = new ItemStack(p_150895_1_, 1, i);
			this.fill(item, new FluidStack(FluidRegistry.getFluid(i), capacity), true);
			p_150895_3_.add(item);
		}

    }

	@Override
	public void addInformation(ItemStack itemstack,EntityPlayer par1EntityPlayer, List list , boolean flag)
    {

		String name = "None";
		int amount = 0;

		if(this.getFluid(itemstack)!=null){
			name = this.getFluid(itemstack).getFluid().getLocalizedName(this.getFluid(itemstack));
			amount = this.getFluid(itemstack).amount;
		}

		NumberFormat nfNum = NumberFormat.getNumberInstance();

		list.add("Name" +" : " + name);
		list.add("Amount" +" : " + nfNum.format(amount) + " / " + nfNum.format(this.capacity) + " mB");

    }


	/* IFluidContainerItem */
    @Override
    public FluidStack getFluid(ItemStack container)
    {
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid"))
        {
            return null;
        }
        return FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
    }

    @Override
    public int getCapacity(ItemStack container)
    {
        return capacity;
    }

    @Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill)
    {
        if (resource == null)
        {
            return 0;
        }

        if (!doFill)
        {
            if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid"))
            {
                return Math.min(capacity, resource.amount);
            }

            FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));

            if (stack == null)
            {
                return Math.min(capacity, resource.amount);
            }

            if (!stack.isFluidEqual(resource))
            {
                return 0;
            }

            return Math.min(capacity - stack.amount, resource.amount);
        }

        if (container.stackTagCompound == null)
        {
            container.stackTagCompound = new NBTTagCompound();
        }

        if (!container.stackTagCompound.hasKey("Fluid"))
        {
            NBTTagCompound fluidTag = resource.writeToNBT(new NBTTagCompound());

            container.setItemDamage(resource.fluidID);

            if (capacity < resource.amount)
            {
                fluidTag.setInteger("Amount", capacity);
                container.stackTagCompound.setTag("Fluid", fluidTag);
                return capacity;
            }

            container.stackTagCompound.setTag("Fluid", fluidTag);
            return resource.amount;
        }

        NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
        FluidStack stack = FluidStack.loadFluidStackFromNBT(fluidTag);

        if (!stack.isFluidEqual(resource))
        {
            return 0;
        }

        int filled = capacity - stack.amount;
        if (resource.amount < filled)
        {
            stack.amount += resource.amount;
            filled = resource.amount;
        }
        else
        {
            stack.amount = capacity;
        }

        container.stackTagCompound.setTag("Fluid", stack.writeToNBT(fluidTag));
        return filled;
    }

    @Override
    public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain)
    {
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid"))
        {
            return null;
        }

        FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
        if (stack == null)
        {
            return null;
        }

        stack.amount = Math.min(stack.amount, maxDrain);
        if (doDrain)
        {
            if (maxDrain >= capacity)
            {
                container.stackTagCompound.removeTag("Fluid");

                if (container.stackTagCompound.hasNoTags())
                {
                    container.stackTagCompound = null;
                }

                container.setItemDamage(0);

                return stack;
            }

            NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
            fluidTag.setInteger("Amount", fluidTag.getInteger("Amount") - maxDrain);
            container.stackTagCompound.setTag("Fluid", fluidTag);
        }
        return stack;
    }

}
