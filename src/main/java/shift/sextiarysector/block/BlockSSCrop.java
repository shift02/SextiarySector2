package shift.sextiarysector.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSSCrop extends BlockBush implements ITileEntityProvider{

	@SideOnly(Side.CLIENT)
    private IIcon[] field_149867_a;
    private static final String __OBFID = "CL_00000222";
    private static CropType type;

    public BlockSSCrop(CropType type)
    {
        this.setTickRandomly(true);
        this.type = type;

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
    }

    protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.farmland || p_149854_1_ == Blocks.farmland;
    }

    @SideOnly(Side.CLIENT)
    public String getItemIconName()
    {
    	return this.getTextureName();
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
    	return this.field_149867_a[p_149691_2_];
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_149867_a = new IIcon[4];

        for (int i = 0; i < this.field_149867_a.length; ++i)
        {
            this.field_149867_a[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
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
        return Items.wheat;
    }

    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, 0);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return p_149650_1_ == 3 ? this.func_149865_P() : this.func_149866_i();
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
                    ret.add(new ItemStack(this.func_149866_i(), 1, 0));
                }
            }
        }

        return ret;
    }

    public enum CropType{

    	Normal(6),
    	Close(1)

    	;

    	public int id;

    	CropType(int renderID){
    		this.id = renderID;
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
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
