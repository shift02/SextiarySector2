package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.item.IHammer;
import shift.sextiarysector.tileentity.TileEntityDirection;

public abstract class BlockDirection extends BlockContainer {

	protected BlockDirection(Material p_i45386_1_) {
		super(p_i45386_1_);
		this.setHarvestLevel("hammer", 0);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

		ItemStack item = par5EntityPlayer.getCurrentEquippedItem();

		if (item == null || !(item.getItem() instanceof IHammer)) return false;

		if (par1World.isRemote) return true;

		if (!par5EntityPlayer.isSneaking()) {
			this.changeToHammer(par1World, x, y, z, par5EntityPlayer, item);
		} else {
			this.breakToHammer(par1World, x, y, z, par5EntityPlayer, item);
		}

		return true;
	}

	/*
	@Override
	public void onBlockClicked(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer) {

		ItemStack item = par5EntityPlayer.getCurrentEquippedItem();

		if (item == null || !(item.getItem() instanceof IHammer)) return;

		if (par1World.isRemote) return;

		this.breakToHammer(par1World, x, y, z, par5EntityPlayer, item);

		return;

	}*/

	private void changeToHammer(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, ItemStack item) {

		if (!((IHammer) item.getItem()).canUse(item, par5EntityPlayer, 2)) return;

		TileEntityDirection t = (TileEntityDirection) par1World.getTileEntity(x, y, z);

		ForgeDirection d = t.getDirection();

		if (d.ordinal() > 4) {
			t.direction = d.getOrientation(0);
		} else {
			t.direction = d.getOrientation(d.ordinal() + 1);
		}

		((IHammer) item.getItem()).use(item, par5EntityPlayer, 2);

		par1World.playSoundEffect(x, y, z, this.stepSound.getStepResourcePath(), 1.0F, par1World.rand.nextFloat() * 0.1F + 0.6F);
		par1World.markBlockForUpdate(x, y, z);

	}

	private void breakToHammer(World par1World, int x, int y, int z, EntityPlayer player, ItemStack item) {

		if (!((IHammer) item.getItem()).canUse(item, player, 10)) return;

		EntityItem eItem = new EntityItem(player.worldObj, x + 0.5d, y + 0.5d, z + 0.5d, new ItemStack(this, 1));

		player.worldObj.spawnEntityInWorld(eItem);

		((IHammer) item.getItem()).use(item, player, 10);

		player.worldObj.setBlockToAir(x, y, z);
		player.worldObj.removeTileEntity(x, y, z);

	}

}
