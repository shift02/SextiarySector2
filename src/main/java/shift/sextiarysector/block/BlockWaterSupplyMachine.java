/*
* 作成者: Shift02
* 作成日: 2016/02/15 - 18:03:52
*/
package shift.sextiarysector.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.tileentity.TileEntityWaterSupplyMachine;

public class BlockWaterSupplyMachine extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconUnder;

    public BlockWaterSupplyMachine() {
        super(Material.wood);
        this.setHardness(2.4f);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int par1, int par2) {
        if (par1 == 0) return iconUnder;
        if (par1 == 1) return iconTop;
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(getTextureName());
        this.iconTop = par1IconRegister.registerIcon(getTextureName() + "_top");
        this.iconUnder = par1IconRegister.registerIcon(getTextureName() + "_under");

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityWaterSupplyMachine();
    }

}
