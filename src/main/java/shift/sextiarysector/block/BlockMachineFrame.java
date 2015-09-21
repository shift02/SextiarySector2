package shift.sextiarysector.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import shift.sextiarysector.SSMaterials;

public class BlockMachineFrame extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    public BlockMachineFrame() {
        super(SSMaterials.machine);
        this.setHardness(0.3f);
        this.setHarvestLevel("spanner", 1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int par1, int par2) {

        if (par1 == 1 || par1 == 0) {
            return this.iconTop;
        } else {
            return this.blockIcon;
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(this.textureName);
        this.iconTop = par1IconRegister.registerIcon(this.textureName + "_top");

    }

}
