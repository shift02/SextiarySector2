package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.gearforce.item.IHammer;
import shift.sextiarysector.tileentity.TileEntityDirection;
import shift.sextiarysector.tileentity.TileEntityFluidMachineBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockFluidMachineBase extends BlockContainer {

	private final Random furnaceRand = new Random();

	private static boolean keepFurnaceInventory;

	@SideOnly(Side.CLIENT)
	private IIcon furnaceIconTop;
	@SideOnly(Side.CLIENT)
	private IIcon[] furnaceIconFront;
	@SideOnly(Side.CLIENT)
	private IIcon furnaceIconTopOn;

	public BlockFluidMachineBase(Material material) {
		super(material);
		this.setHardness(1.0F);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		TileEntityFluidMachineBase tileentityfurnace = (TileEntityFluidMachineBase) world.getTileEntity(x, y, z);
		if (tileentityfurnace.isFuel()) return 15;
		return 0;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (par1World.isRemote)
		{
			return true;
		}
		else
		{
			TileEntityFluidMachineBase tileentityfurnace = (TileEntityFluidMachineBase) par1World.getTileEntity(par2, par3, par4);

			if (player.getCurrentEquippedItem() != null) {
				if (player.getCurrentEquippedItem().getItem() instanceof IHammer && ((IHammer) player.getCurrentEquippedItem().getItem()).canUse(player.getCurrentEquippedItem(), player, 2)) {

					tileentityfurnace.on = !tileentityfurnace.on;
					((IHammer) player.getCurrentEquippedItem().getItem()).use(player.getCurrentEquippedItem(), player, 2);
					par1World.markBlockForUpdate(par2, par3, par4);
					return true;

				}
			}

			if (tileentityfurnace != null)
			{
				player.openGui(SextiarySector.instance, 1, par1World, par2, par3, par4);
			}

			return true;
		}
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
	}

	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			Block block = par1World.getBlock(par2, par3, par4 - 1);
			Block block1 = par1World.getBlock(par2, par3, par4 + 1);
			Block block2 = par1World.getBlock(par2 - 1, par3, par4);
			Block block3 = par1World.getBlock(par2 + 1, par3, par4);

			TileEntityDirection tileEntity = (TileEntityDirection) par1World.getTileEntity(par2, par3, par4);

			byte b0 = 3;

			if (block.func_149730_j() && !block1.func_149730_j())
			{
				b0 = 3;
			}

			if (block1.func_149730_j() && !block.func_149730_j())
			{
				b0 = 2;
			}

			if (block2.func_149730_j() && !block3.func_149730_j())
			{
				b0 = 5;
			}

			if (block3.func_149730_j() && !block2.func_149730_j())
			{
				b0 = 4;
			}

			//par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
			tileEntity.direction = ForgeDirection.getOrientation(b0);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{

		return par1 == 1 ? this.furnaceIconTop : (par1 == 0 ? this.furnaceIconTop : (par1 != 3 ? this.blockIcon : this.furnaceIconFront[1]));

	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess p_149673_1_, int x, int y, int z, int side)
	{

		TileEntityFluidMachineBase tileEntity = (TileEntityFluidMachineBase) p_149673_1_.getTileEntity(x, y, z);

		int meta = tileEntity.getDirection().ordinal();//p_149673_1_.getBlockMetadata(x, y, z);

		return side == 1 ? (tileEntity.on ? this.furnaceIconTopOn : this.furnaceIconTop) : (side == 0 ? this.furnaceIconTop : (side != meta ? this.blockIcon : (tileEntity.isFuel() ? this.furnaceIconFront[0] : this.furnaceIconFront[1])));

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("furnace_side");
		this.furnaceIconFront = new IIcon[2];
		this.furnaceIconFront[0] = par1IconRegister.registerIcon("furnace_front_on");
		this.furnaceIconFront[1] = par1IconRegister.registerIcon("furnace_front_off");
		this.furnaceIconTop = par1IconRegister.registerIcon("sextiarysector:fluid_furnace_top");
		this.furnaceIconTopOn = par1IconRegister.registerIcon("sextiarysector:fluid_furnace_top_on");
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		TileEntityDirection tileEntity = (TileEntityDirection) par1World.getTileEntity(par2, par3, par4);

		if (l == 0)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[2];
		}

		if (l == 1)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
			tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[5];
		}

		if (l == 2)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[3];
		}

		if (l == 3)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
			tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[4];
		}

	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
	{
		if (!keepFurnaceInventory)
		{
			TileEntityFluidMachineBase tileentityfurnace = (TileEntityFluidMachineBase) par1World.getTileEntity(par2, par3, par4);

			if (tileentityfurnace != null)
			{
				for (int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1)
				{
					ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);

					this.dropItem(itemstack, par1World, par2, par3, par4);

				}

				par1World.func_147453_f(par2, par3, par4, par5);
			}
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	private void dropItem(ItemStack itemstack, World par1World, int par2, int par3, int par4) {

		if (itemstack != null)
		{
			float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
			float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
			float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

			while (itemstack.stackSize > 0)
			{
				int k1 = this.furnaceRand.nextInt(21) + 10;

				if (k1 > itemstack.stackSize)
				{
					k1 = itemstack.stackSize;
				}

				itemstack.stackSize -= k1;
				EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

				if (itemstack.hasTagCompound())
				{
					entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
				}

				float f3 = 0.05F;
				entityitem.motionX = (float) this.furnaceRand.nextGaussian() * f3;
				entityitem.motionY = (float) this.furnaceRand.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float) this.furnaceRand.nextGaussian() * f3;
				par1World.spawnEntityInWorld(entityitem);
			}
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
	{
		TileEntityFluidMachineBase tileentityfurnace = (TileEntityFluidMachineBase) p_149734_1_.getTileEntity(p_149734_2_, p_149734_3_, p_149734_4_);

		if (tileentityfurnace.isFuel())
		{
			int l = tileentityfurnace.getDirection().ordinal();
			float f = p_149734_2_ + 0.5F;
			float f1 = p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
			float f2 = p_149734_4_ + 0.5F;
			float f3 = 0.52F;
			float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

			if (l == 4)
			{
				p_149734_1_.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				p_149734_1_.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 5)
			{
				p_149734_1_.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				p_149734_1_.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 2)
			{
				p_149734_1_.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
				p_149734_1_.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 3)
			{
				p_149734_1_.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
				p_149734_1_.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
	{
		return Container.calcRedstoneFromInventory((IInventory) par1World.getTileEntity(par2, par3, par4));
	}
}
