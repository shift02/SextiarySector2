package shift.sextiarysector.item;

import java.text.NumberFormat;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
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
                return stack;
            }

            NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
            fluidTag.setInteger("Amount", fluidTag.getInteger("Amount") - maxDrain);
            container.stackTagCompound.setTag("Fluid", fluidTag);
        }
        return stack;
    }

}
