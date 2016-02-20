package shift.sextiarysector.api.agriculture;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCropBase extends BlockBush implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    private CropRendererType rendererID;
    private CropStatus status;

    private Item drop;

    private static Random random = new Random();

    /*
    public BlockCropBase(CropStatus status, CropRendererType rendererID, Item drop)
    {
    	this.setTickRandomly(true);
    	this.type = type;
    	this.drop = drop;
    	this.status = status;
    	this.farmland = farmland;
    	this.re_harvest = re_harvest;
    
    	if (type.equals(CropType.Normal)) {
    		float f = 0.5F;
    		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    	} else {
    		float f = 0.2F;
    		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
    	}
    
    	//this.setCreativeTab((CreativeTabs)null);
    	this.setHardness(0.2F);
    	this.setStepSound(soundTypeGrass);
    	this.disableStats();
    	this.isBlockContainer = true;
    	this.setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
    }*/

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }

}
