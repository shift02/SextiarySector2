package shift.sextiarysector.item;

import java.text.NumberFormat;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import shift.sextiarysector.SSFluids;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.EnumColor;
import shift.sextiarysector.api.IColorItem;
import shift.sextiarysector.fluid.FluidColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpray extends Item implements IColorItem, IFluidContainerItem {

	protected int capacity;

	public ItemSpray() {
		this.setHasSubtypes(true);
		this.capacity = FluidContainerRegistry.BUCKET_VOLUME * 1;//capacity;
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
	{

		//p_150895_3_.add(new ItemStack(this, 1, 0));

		for (int i = 0; i < SSFluids.color.length; i++) {

			ItemStack item = new ItemStack(this, 1, i);
			this.fill(item, new FluidStack(SSFluids.color[i], capacity), true);

			p_150895_3_.add(item);

		}

	}

	@Override
	public int getDamage(ItemStack stack)
	{
		if (this.getFluid(stack) == null) return 16;

		return ((FluidColor) this.getFluid(stack).getFluid()).color.ordinal();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer par3EntityPlayer, List list, boolean par4)
	{

		String f = "None";
		int amount = 0;

		if (this.getFluid(itemStack) != null)
		{
			f = this.getFluid(itemStack).getFluid().getLocalizedName(this.getFluid(itemStack));
			amount = this.getFluid(itemStack).amount;
		}

		NumberFormat nf = NumberFormat.getNumberInstance();

		list.add("Dye : " + f);
		list.add("Amount : " + nf.format(amount) + " / " + nf.format(this.capacity) + " mB");

	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{

		//if (this.getFluidAmount(stack) == 0) return false;

		return true;//stack.isItemDamaged();
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return 1.0D - (double) this.getFluidAmount(stack) / (double) this.capacity;
	}

	/*
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer par1EntityPlayer, List list, boolean flag)
	{
		list.add(EnumColor.getColor(itemstack.getItemDamage()).name());
	}*/

	@Override
	public EnumColor getColor(ItemStack itemstack) {

		if (this.getFluid(itemstack) == null) return EnumColor.Unknown;
		return ((FluidColor) this.getFluid(itemstack).getFluid()).color;

	}

	@Override
	public void useItem(ItemStack item) {
		this.drain(item, 10, true);
	}

	@Override
	public boolean canUse(ItemStack item) {
		return this.getFluidAmount(item) >= 10;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int p_77618_1_, int p_77618_2_)
	{
		return p_77618_2_ == 0 ? SSItems.emptyBottle.getIconFromDamage(0) : super.getIconFromDamageForRenderPass(p_77618_1_, p_77618_2_);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemstack, int p_82790_2_)
	{

		if (p_82790_2_ == 0) return super.getColorFromItemStack(itemstack, p_82790_2_);
		if (this.getFluid(itemstack) == null) return super.getColorFromItemStack(itemstack, p_82790_2_);

		return ((FluidColor) this.getFluid(itemstack).getFluid()).color.color;

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

		if (!(resource.getFluid() instanceof FluidColor)) return 0;

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

		int currentAmount = stack.amount;
		stack.amount = Math.min(stack.amount, maxDrain);
		if (doDrain)
		{
			if (currentAmount == stack.amount)
			{
				container.stackTagCompound.removeTag("Fluid");

				if (container.stackTagCompound.hasNoTags())
				{
					container.stackTagCompound = null;
				}
				return stack;
			}

			NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
			fluidTag.setInteger("Amount", currentAmount - stack.amount);
			container.stackTagCompound.setTag("Fluid", fluidTag);
		}
		return stack;
	}

	public int getFluidAmount(ItemStack container) {

		if (this.getFluid(container) == null) {
			return 0;
		}
		return this.getFluid(container).amount;

	}

}
