package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.tileentity.TileEntityFunnel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerFunnel extends Container
{
	private final TileEntityFunnel tileFurnace;

	//private int lastFluid;
	private int lastFluidID;
	private int lastFluidAmount;

	public ContainerFunnel(InventoryPlayer p_i1812_1_, TileEntityFunnel p_i1812_2_)
	{
		this.tileFurnace = p_i1812_2_;
		//this.addSlotToContainer(new Slot(p_i1812_2_, 0, 56, 17));
		//this.addSlotToContainer(new Slot(p_i1812_2_, 1, 56, 53));
		//this.addSlotToContainer(new SlotFurnace(p_i1812_1_.player, p_i1812_2_, 2, 107, 53));
		//this.addSlotToContainer(new SlotFurnace(p_i1812_1_.player, p_i1812_2_, 3, 125, 53));
		this.addSlotToContainer(new SlotFluid(p_i1812_2_, 0, 26, 20));
		this.addSlotToContainer(new SlotFluid(p_i1812_2_, 1, 134, 20));
		int i;

		byte b0 = 51;
		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(p_i1812_1_, j + i * 9 + 9, 8 + j * 18, i * 18 + b0));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(p_i1812_1_, i, 8 + i * 18, 58 + b0));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting p_75132_1_)
	{
		super.addCraftingToCrafters(p_75132_1_);
		if (this.tileFurnace.getFluidStack() == null) {
			p_75132_1_.sendProgressBarUpdate(this, 0, 0);
			p_75132_1_.sendProgressBarUpdate(this, 1, 0);
		} else {
			p_75132_1_.sendProgressBarUpdate(this, 0, this.tileFurnace.getFluidStack().getFluid().getID());
			p_75132_1_.sendProgressBarUpdate(this, 1, this.tileFurnace.getFluidStack().amount);
		}

	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastFluidAmount != this.tileFurnace.tank.getFluidAmount())
			{
				if (this.tileFurnace.getFluidStack() == null) {
					icrafting.sendProgressBarUpdate(this, 0, 0);
					icrafting.sendProgressBarUpdate(this, 1, 0);
				} else {
					icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.getFluidStack().getFluid().getID());
					icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.getFluidStack().amount);
				}
			}

		}

		this.lastFluidAmount = this.tileFurnace.tank.getFluidAmount();

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int p_75137_1_, int p_75137_2_)
	{

		if (p_75137_1_ == 0 || p_75137_1_ == 1)
		{

			if (p_75137_2_ == 0) {
				this.tileFurnace.tank.setFluid(null);
				return;
			}

			if (this.tileFurnace.getFluidStack() == null) {

				if (p_75137_1_ == 0) {

					this.tileFurnace.tank.setFluid(new FluidStack(p_75137_2_, 1));

				} else if (p_75137_1_ == 1) {

					this.tileFurnace.tank.setFluid(new FluidStack(1, p_75137_2_));

				}

			} else {

				if (p_75137_1_ == 0) {

					//this.tileFurnace.tank.getFluid(). = p_75137_2_;
					this.tileFurnace.tank.setFluid(new FluidStack(p_75137_2_, 1));

				} else if (p_75137_1_ == 1) {

					this.tileFurnace.tank.getFluid().amount = p_75137_2_;

				}

			}

		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return this.tileFurnace.isUseableByPlayer(p_75145_1_);
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(p_82846_2_);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (p_82846_2_ == 1)
			{
				if (!this.mergeItemStack(itemstack1, 2, 38, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (p_82846_2_ != 1 && p_82846_2_ != 0)
			{
				if (FluidContainerRegistry.isContainer(itemstack))
				{
					if (!this.mergeItemStack(itemstack1, 0, 1, false))
					{
						return null;
					}
				}
				else if (p_82846_2_ >= 2 && p_82846_2_ < 29)
				{
					if (!this.mergeItemStack(itemstack1, 29, 38, false))
					{
						return null;
					}
				}
				else if (p_82846_2_ >= 29 && p_82846_2_ < 38 && !this.mergeItemStack(itemstack1, 2, 29, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 2, 38, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(p_82846_1_, itemstack1);
		}

		return itemstack;
	}
}
