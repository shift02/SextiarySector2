package shift.sextiarysector.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSSFluid extends BlockFluidClassic{

	public static final Material water = new SSMaterial();
	public BlockSSFluid(Fluid fluid) {
		super(fluid, Material.water);
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
		if(p_149691_2_ == 0){
			return this.getFluid().getStillIcon();
		}else{
			return this.getFluid().getFlowingIcon();
		}
    }

	@SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return this.getFluid().getColor();
    }

	public static class SSMaterial extends MaterialLiquid{

		public SSMaterial() {
			super(MapColor.waterColor);
			this.setNoPushMobility();
		}

	}

}
