package shift.sextiarysector.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.recipe.RecipeSimpleMachine;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSimpleMachine extends BlockContainer {

	private final String IconName;
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconUnder;
	private String frontIcon;
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;

	private int guiID;
	private RecipeSimpleMachine recipe;
	private String guiuUnlocalizedName;

	public BlockSimpleMachine(String iconFront,int gui,RecipeSimpleMachine recipe) {
		super( Material.wood);
		this.setHardness(3.0F);
		this.frontIcon = iconFront;
		this.guiID = gui;
		this.recipe=recipe;
		IconName = ("sextiarysector:machine/machine");
	}

	@Override
	@SideOnly(Side.CLIENT)

    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		int d = ((TileEntitySimpleMachine)par1IBlockAccess.getTileEntity(par2, par3, par4)).direction.ordinal();

		if (par5 == 0)
		{
			return this.iconTop;
		}
		else if (par5 == 1)
		{
			return this.iconUnder;
		}
		else if (par5 == d)
		{
			return this.iconFront;
		}else
		{
			return this.blockIcon;
		}


    }

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{

		if (par1 == 0)
		{
			return this.iconTop;
		}
		else if (par1 == 1)
		{
			return this.iconUnder;
		}
		else if (par1 == 3)
		{
			return this.iconFront;
		}else
		{
			return this.blockIcon;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(IconName);
		this.iconTop = par1IconRegister.registerIcon(IconName+"_top");
		this.iconUnder = par1IconRegister.registerIcon(IconName+"_under");

		//iconFront = new Icon[SimpleMachine.values().length];

		this.iconFront = par1IconRegister.registerIcon("sextiarysector:machine/"+this.frontIcon);

		/*
		for(int i =0;i<iconFront.length;i++){
			iconFront[i]=par1IconRegister.registerIcon(IconName+"_"+SimpleMachine.values()[i].icon);
		}*/

	}

	@Override
	@SideOnly(Side.CLIENT)
	protected String getTextureName()
	{
		return this.IconName;
	}

	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
		//for(int i = 0;i<SimpleMachine.values().length;i++){
			p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
		//}

    }

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6,
			float par7, float par8, float par9) {

		if (par1World.isRemote)
		{
			return true;
		}
		else
		{
			par5EntityPlayer.openGui(SextiarySector.instance, this.guiID, par1World, x, y, z);

			return true;
		}
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			Block block = par1World.getBlock(par2, par3, par4 - 1);
            Block block1 = par1World.getBlock(par2, par3, par4 + 1);
            Block block2 = par1World.getBlock(par2 - 1, par3, par4);
            Block block3 = par1World.getBlock(par2 + 1, par3, par4);

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

			((TileEntitySimpleMachine) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection.getOrientation(b0);

			//par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l == 0)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			((TileEntitySimpleMachine) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
					.getOrientation(2);
		}

		if (l == 1)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
			((TileEntitySimpleMachine) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
					.getOrientation(5);

		}

		if (l == 2)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			((TileEntitySimpleMachine) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
					.getOrientation(3);

		}

		if (l == 3)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
			((TileEntitySimpleMachine) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
					.getOrientation(4);

		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, par6ItemStack.getItemDamage(), 1);

		par1World.markBlockForUpdate(par2, par3, par4);

	}

	public Block setBlockName(String p_149663_1_)
    {
		super.setBlockName(p_149663_1_);
        this.guiuUnlocalizedName = p_149663_1_;
        return this;
    }

	public String getGUIUnlocalizedName()
    {
        return this.guiuUnlocalizedName;
    }

	public ItemStack getResult(ItemStack itemstack){
		return this.recipe.getResult(itemstack);
	}

	@Override
	public TileEntity createNewTileEntity(World world,int i) {
		return new TileEntitySimpleMachine();
	}

}
