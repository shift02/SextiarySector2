/*
* 作成者: Shift02
* 作成日: 2016/02/25 - 16:45:55
*/
package shift.sextiarysector.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.agriculture.AgricultureAPI;

public class BlockWood extends BlockAbstractFarmland {

    private IIcon blockTopIcon;
    private IIcon blockTop2Icon;

    public BlockWood() {
        super(Material.wood);
        this.setLightOpacity(255);
        this.setStepSound(soundTypeGrass);
        this.setBlockBounds(2.0f / 16.0f, 0.0f, 2.0f / 16.0f, 14.0f / 16.0f, 15.0f / 16.0f, 14.0f / 16.0f);
        this.setHardness(1.2f);
        this.setStepSound(soundTypeWood);
        this.useNeighborBrightness = true;
    }

    @Override
    public String getName() {
        return AgricultureAPI.WOOD;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
        this.blockTopIcon = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        this.blockTop2Icon = p_149651_1_.registerIcon(this.getTextureName() + "_top2");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        if (p_149691_1_ != 0 && p_149691_1_ != 1) {
            return this.blockIcon;
        }

        if (p_149691_2_ == 0) {
            return this.blockTopIcon;
        } else {
            return this.blockTop2Icon;
        }

    }

    @Override
    public int getRenderType() {
        return SextiarySector.proxy.woodType;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

}