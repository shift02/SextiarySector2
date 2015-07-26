package shift.sextiarysector.block;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import shift.sextiarysector.SSItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPoweredBuoy extends BlockBuoyBase {

	protected IIcon blockIcons[];

	public BlockPoweredBuoy() {
		super();
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{

		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().getItem() == SSItems.hammer) {
			int i = par1World.getBlockMetadata(par2, par3, par4);
			if (i < 7) {
				i += 1;
			} else {
				i = 0;
			}
			par1World.setBlockMetadataWithNotify(par2, par3, par4, i, 1);
			par1World.markBlockForUpdate(par2, par3, par4);
			return true;
		}
		return false;

	}

	/*
	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{
	    int i = par1World.getBlockMetadata(par2,par3,par4);
	    if(!(i>=9)){
	    	i += 1;
	    }else{
	    	i = 0;
	    }

	    par1World.setBlockMetadataWithNotify(par2, par3, par4, i, 1);
	}*/

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (par5Entity instanceof EntityBoat) {

			int x = 0;
			int z = 0;

			int y = 0;

			int i = par1World.getBlockMetadata(par2, par3, par4);

			switch (i) {

			/*
			case 1 : z = -1; break;
			case 2 : x = 1; break;
			case 3 : z = 1; break;
			case 4 : x = -1; break;

			case 5 : x = -1; z = -1 ; break;
			case 6 : x = 1;z = -1 ; break;
			case 7 : x = 1;z = 1 ; break;
			case 8 : x = -1; z = 1 ; break;
			*/

			case 0:
				z = -1;
				break;
			case 2:
				x = 1;
				break;
			case 4:
				z = 1;
				break;
			case 6:
				x = -1;
				break;

			case 1:
				x = 1;
				z = -1;
				break;
			case 3:
				x = 1;
				z = 1;
				break;
			case 5:
				x = -1;
				z = 1;
				break;
			case 7:
				x = -1;
				z = -1;
				break;

			//case 9 : y = 1;break;

			}

			par5Entity.motionX += x;
			par5Entity.motionZ += z;
			par5Entity.motionY += y;
			//par5Entity.motionY += 1;
			//par5Entity.motionZ += 1;
			//moveFlying(1, 1, 1);
			//par5Entity.setPositionAndRotation2(2,2,2,1,1,1);
		}

	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		//TileEntityLargeFurnace tileEntity = (TileEntityLargeFurnace)par1World.getBlockTileEntity(par2, par3, par4);

		if (l == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
			//tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[2];
		}

		if (l == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 6, 2);
			//tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[5];
		}

		if (l == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
			//tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[3];
		}

		if (l == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			//tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[4];
		}

	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		/*
		for(int i = 1;i<9;i++){
			if(par2==i){
				return blockIcons[i-1];
			}
		}*/
		return blockIcons[par2];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		blockIcons = new IIcon[8];

		for (int i = 0; i < 8; i++) {
			blockIcons[i] = par1IconRegister.registerIcon(this.getTextureName() + "_" + i);

		}

		this.blockIcon = blockIcons[0];//par1IconRegister.registerIcon(this.getTextureName());

	}

}
