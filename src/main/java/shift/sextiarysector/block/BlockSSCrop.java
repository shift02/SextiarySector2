package shift.sextiarysector.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.module.FertilizerManager;
import shift.sextiarysector.tileentity.TileEntityFarmland;
import shift.sextiarysector.tileentity.TileEntitySSCrop;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSSCrop extends BlockBush implements ITileEntityProvider{

	@SideOnly(Side.CLIENT)
    private IIcon[] field_149867_a;
    private CropType type;
    private Item drop;
    private CropStatus status;
    private Block farmland;
    private boolean re_harvest;

    private static Random r = new Random();

    public BlockSSCrop(CropType type, CropStatus status,Block farmland, Item drop, Boolean re_harvest)
    {
        this.setTickRandomly(true);
        this.type = type;
        this.drop = drop;
        this.status = status;
        this.farmland = farmland;
        this.re_harvest = re_harvest;

        if(type.equals(CropType.Normal)){
        	float f = 0.5F;
        	this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        }else{
        	float f = 0.2F;
            this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        }

        //this.setCreativeTab((CreativeTabs)null);
        this.setHardness(0.2F);
        this.setStepSound(soundTypeGrass);
        this.disableStats();
        this.isBlockContainer = true;
        this.setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
    }

    @Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6,float par7, float par8, float par9) {

    	if(par5EntityPlayer.getCurrentEquippedItem()!= null && par5EntityPlayer.getCurrentEquippedItem().getItem() instanceof ItemShears){

    		return this.doHarvestFromShears(par1World, x, y, z, par5EntityPlayer);

    	}


    	if(this.hasFarmland(par1World, x, y, z)){
    		return par1World.getBlock(x, y-1, z).onBlockActivated(par1World, x, y-1, z, par5EntityPlayer, par6, par7, par8, par9);
    	}

    	return false;

    }

    private boolean doHarvestFromShears(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer){

    	if(this.re_harvest && this.getStatus().isReHarvest() && par1World.getBlockMetadata(x, y, z)==3){

    		ItemStack item = this.getAfter(par1World, x, y, z);

    		if(!par1World.isRemote){
    			par5EntityPlayer.getCurrentEquippedItem().damageItem(4, par5EntityPlayer);

    			float var10 = this.r.nextFloat() * 0.8F + 0.1F;
                float var11 = this.r.nextFloat() * 0.8F + 0.1F;
                float var12 = this.r.nextFloat() * 0.8F + 0.1F;

                item = this.getAfter(par1World, x, y, z);

                if(item==null){
                	item = new ItemStack(this.func_149865_P(), 1);
                }

                EntityItem var14 = new EntityItem(par1World, (x + var10), (y + var11), (z + var12), item);

                par1World.setBlock(x, y, z, this,4, 1);
                TileEntitySSCrop crop = (TileEntitySSCrop) par1World.getTileEntity(x, y, z);
                crop.onHarvest();

                par1World.playSoundAtEntity(par5EntityPlayer, "mob.sheep.shear", 1.0F, 1.0F);

                return par1World.spawnEntityInWorld(var14);

    		}else{

    			return true;

    		}

    	}

		return false;
    }

    public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
    {
        return  p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_) == this.farmland;
    }

    protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == this.farmland;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
		return EnumPlantType.Crop;
    }

    public Block setBlockTextureName(String p_149658_1_)
    {
        this.textureName = p_149658_1_;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public String getItemIconName()
    {
    	return "sextiarysector:seed/"+this.getTextureName()+"_seed";
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
    	if(this.field_149867_a.length == 3 && p_149691_2_ == 4){
    		return this.field_149867_a[3];
    	}

    	return this.field_149867_a[p_149691_2_];
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
    	int s =4;
    	if(this.canReHarvest())s = 5;
        this.field_149867_a = new IIcon[s];

        for (int i = 0; i < this.field_149867_a.length; ++i)
        {
            this.field_149867_a[i] = p_149651_1_.registerIcon("sextiarysector:crop/"+this.getTextureName() + "_stage_" + i);
        }
    }

    public int getRenderType()
    {
        return 6;
    }

    protected Item func_149866_i()
    {
        return Item.getItemFromBlock(this);
    }

    protected Item func_149865_P()
    {
        return drop;
    }

    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, 0);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return p_149650_1_ == 3 ? this.func_149865_P() : null;//this.func_149866_i();
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (metadata >= 3)
        {
            for (int i = 0; i < 3 + fortune; ++i)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    //ret.add(new ItemStack(this.func_149866_i(), 1, 0));
                    ret.add(new ItemStack(this.func_149865_P(), 1, 0));
                }
            }

            ItemStack after = this.getAfter(world, x, y, z);
            if(after!=null){
            	ret.add(after);
            }

        }

        return ret;
    }

    private ItemStack getAfter(World world, int x, int y, int z){

    	if(this.hasFarmland(world, x, y, z)){

        	TileEntityFarmland f =(TileEntityFarmland) world.getTileEntity(x, y-1, z);

        	if(f.getFertilizer()!=null && FertilizerManager.canMutation(f.getFertilizer(), new ItemStack(func_149865_P() ) ) ){

        		ItemStack item = FertilizerManager.getAfter(f.getFertilizer(), new ItemStack(func_149865_P()));
        		f.clearFertilizer();
        		world.markBlockForUpdate(x, y-1, z);

        		return item;

        	}else{
        		return null;
        	}

        }else{
        	return null;
        }

    }


    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
        p_149749_1_.removeTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    }

    public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_)
    {
        super.onBlockEventReceived(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
        TileEntity tileentity = p_149696_1_.getTileEntity(p_149696_2_, p_149696_3_, p_149696_4_);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySSCrop();
	}

	public boolean canReHarvest(){
		return re_harvest;
	}

	public CropStatus getStatus() {
		return status;
	}

	public static boolean hasFarmland(World world, int x, int y, int z){
		return world.getBlock(x, y-1, z)==SSBlocks.farmland;
	}


    //---------------------------------------------------

	public enum CropType{

    	Normal(6),
    	Close(1)

    	;

    	public int id;

    	CropType(int renderID){
    		this.id = renderID;
    	}

    }

    public static class CropStatus{

    	private int days[] ;//= new int[3];

    	public Season[] season;

		public CropStatus(int[] i1,Season... s){

    		this.days= i1;
    		this.season= s;

    	}

    	public synchronized int[] getDays() {
			return days;
		}

		private synchronized void setDay(int[] day) {
			this.days = day;
		}

		public Season[] getSeason() {
			return season;
		}

    	public boolean isReHarvest(){
    		return this.days.length == 4;
    	}

    }


}
