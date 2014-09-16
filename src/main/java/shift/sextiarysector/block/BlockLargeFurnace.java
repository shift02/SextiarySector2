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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLargeFurnace extends BlockContainer{

	private final Random furnaceRand = new Random();

    private static boolean keepFurnaceInventory;

    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon[] furnaceIconFront = new IIcon[2];

    private int GUIID;

	public BlockLargeFurnace() {
		super(Material.rock);
		this.setHardness(1.0F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
        	TileEntityLargeFurnace tileentityfurnace = (TileEntityLargeFurnace)par1World.getTileEntity(par2, par3, par4);

            if (tileentityfurnace != null)
            {
                par5EntityPlayer.openGui(SextiarySector.instance, 0, par1World, par2, par3, par4);
            }

            return true;
        }
    }

	public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
    {

    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        //this.setDefaultDirection(par1World, par2, par3, par4);
    }

    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            Block block = par1World.getBlock(par2, par3, par4 - 1);
            Block block1 = par1World.getBlock(par2, par3, par4 + 1);
            Block block2 = par1World.getBlock(par2 - 1, par3, par4);
            Block block3 = par1World.getBlock(par2 + 1, par3, par4);

            TileEntityLargeFurnace tileEntity = (TileEntityLargeFurnace)par1World.getTileEntity(par2, par3, par4);


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

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
            tileEntity.direction = ForgeDirection.getOrientation(b0);
        }
    }

    @Override
	@SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {

		return par1 == 1 ? this.furnaceIconTop : (par1 == 0 ? this.furnaceIconTop : (par1 != 3 ? this.blockIcon : this.furnaceIconFront[1]));

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess p_149673_1_, int x, int y, int z, int side)
    {
    	//System.out.println("Icon");
    	TileEntityLargeFurnace tileEntity = (TileEntityLargeFurnace)p_149673_1_.getTileEntity(x, y, z);

    	int meta = p_149673_1_.getBlockMetadata(x, y, z);

		return side == 1 ? this.furnaceIconTop : (side == 0 ? this.furnaceIconTop : (side != meta ? this.blockIcon : (tileEntity.isBurning() ? this.furnaceIconFront[0] : this.furnaceIconFront[1])));


    }

    @Override
	@SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("furnace_side");
        this.furnaceIconFront[0] = par1IconRegister.registerIcon("furnace_front_on");
        this.furnaceIconFront[1] = par1IconRegister.registerIcon("furnace_front_off");
        this.furnaceIconTop = par1IconRegister.registerIcon("sextiarysector:furnace_top");
    }


    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
	public TileEntity createNewTileEntity(World par1World, int p_149915_2_)
    {
        return new TileEntityLargeFurnace();
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileEntityLargeFurnace tileEntity = (TileEntityLargeFurnace)par1World.getTileEntity(par2, par3, par4);

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
            tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[2];
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
            tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[5];
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
            tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[3];
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
            tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[4];
        }

    }

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
        if (!keepFurnaceInventory)
        {
        	TileEntityLargeFurnace tileentityfurnace = (TileEntityLargeFurnace)par1World.getTileEntity(par2, par3, par4);

            if (tileentityfurnace != null)
            {
                for (int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1)
                {
                    ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);

                    this.dropItem(itemstack, par1World, par2, par3, par4);

                }

                for (int j1 = 0; j1 < tileentityfurnace.buffer.getSizeInventory(); ++j1)
                {
                    ItemStack itemstack = tileentityfurnace.buffer.getStackInSlot(j1);

                    this.dropItem(itemstack, par1World, par2, par3, par4);
                }

                for (int j1 = 0; j1 < tileentityfurnace.craftMatrix.getSizeInventory(); ++j1)
                {
                    ItemStack itemstack = tileentityfurnace.craftMatrix.getStackInSlot(j1);

                    this.dropItem(itemstack, par1World, par2, par3, par4);
                }

                par1World.func_147453_f(par2, par3, par4, par5);
            }
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

	private void dropItem(ItemStack itemstack ,World par1World,int par2, int par3, int par4){

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
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                }

                float f3 = 0.05F;
                entityitem.motionX = (float)this.furnaceRand.nextGaussian() * f3;
                entityitem.motionY = (float)this.furnaceRand.nextGaussian() * f3 + 0.2F;
                entityitem.motionZ = (float)this.furnaceRand.nextGaussian() * f3;
                par1World.spawnEntityInWorld(entityitem);
            }
        }

	}

	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
    	TileEntityLargeFurnace tileentityfurnace = (TileEntityLargeFurnace)p_149734_1_.getTileEntity(p_149734_2_, p_149734_3_, p_149734_4_);


        if (tileentityfurnace.isBurning())
        {
            int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
            float f = (float)p_149734_2_ + 0.5F;
            float f1 = (float)p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)p_149734_4_ + 0.5F;
            float f3 = 0.52F;
            float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                p_149734_1_.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5)
            {
                p_149734_1_.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                p_149734_1_.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                p_149734_1_.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

	@Override
	public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    @Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getTileEntity(par2, par3, par4));
    }



}
