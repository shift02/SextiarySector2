package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityTank;

public class BlockTank extends BlockContainer {

	public BlockTank() {
		super(Material.glass);
		float b = 1.0f / 16.0f;
		this.setBlockBounds(b, 0, b, 1.0f - b, 1, 1.0f - b);
		this.setHardness(0.4f);
		this.setStepSound(soundTypeGlass);
	}

	//プレイヤーの右クリック処理
	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{

		ItemStack itemstack = player.inventory.getCurrentItem();
		TileEntityTank tank = (TileEntityTank) par1World.getTileEntity(x, y, z);

		if (tank == null) return false;
		if (!FluidContainerRegistry.isContainer(itemstack)) return false;

		FluidStack fluid = tank.getFluidStack();

		if (FluidContainerRegistry.isFilledContainer(itemstack)) {
			return this.addFluid(par1World, x, y, z, itemstack, tank, player);
		}
		else if (fluid != null && fluid.getFluid() != null && FluidContainerRegistry.isContainer(itemstack)) {
			return this.drainFluid(par1World, x, y, z, itemstack, tank, player);
		}

		return false;

	}

	public boolean addFluid(World world, int x, int y, int z, ItemStack itemstack, TileEntityTank tank, EntityPlayer player) {

		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(itemstack);
		int put = tank.fill(ForgeDirection.UNKNOWN, fluid, false);

		if (put != fluid.amount) return false;

		tank.fill(ForgeDirection.UNKNOWN, fluid, true);
		this.addContainerItemToPlayer(itemstack, FluidContainerRegistry.drainFluidContainer(itemstack), player);

		tank.markDirty();
		world.markBlockForUpdate(x, y, z);

		world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);

		return true;

	}

	public boolean drainFluid(World world, int x, int y, int z, ItemStack empty, TileEntityTank tank, EntityPlayer player) {

		for (FluidContainerData f : FluidContainerRegistry.getRegisteredFluidContainerData()) {

			if (!(f.emptyContainer.isItemEqual(empty) && f.fluid.isFluidEqual(tank.getFluidStack()) && f.fluid.amount <= tank.tank.getFluidAmount())) continue;

			this.addContainerItemToPlayer(empty, FluidContainerRegistry.fillFluidContainer(tank.getFluidStack(), empty), player);
			tank.tank.drain(f.fluid.amount, true);

			tank.markDirty();
			world.markBlockForUpdate(x, y, z);

			world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);

			return true;

		}

		return false;

	}

	public void addContainerItemToPlayer(ItemStack itemstack, ItemStack newstack, EntityPlayer player) {

		if (player.capabilities.isCreativeMode) return;

		if (itemstack.stackSize == 1 && player.worldObj.isRemote) return;

		--itemstack.stackSize;

		ItemStack emptyContainer = newstack;

		if (emptyContainer != null)
		{

			if (!player.capabilities.isCreativeMode) {

				if (itemstack.stackSize <= 0) {

					player.inventory.setInventorySlotContents(player.inventory.currentItem, emptyContainer);

				} else {

					if (!player.inventory.addItemStackToInventory(emptyContainer)) {
						player.dropPlayerItemWithRandomChoice(emptyContainer, false);

					}

					player.inventory.markDirty();

				}

			}

		}

	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType()
	{
		return SextiarySector.proxy.tankType;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTank();
	}

}
