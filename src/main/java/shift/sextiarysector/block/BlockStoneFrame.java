package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityCraftFurnace;
import shift.sextiarysector.tileentity.TileEntityStoneFrame;

public class BlockStoneFrame extends BlockContainer {

    public BlockStoneFrame() {
        super(Material.rock);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par1World.isRemote) {
            return true;
        } else {
            TileEntityStoneFrame tileentityfurnace = (TileEntityStoneFrame) par1World.getTileEntity(par2, par3, par4);

            System.out.println("AAA");

            if (tileentityfurnace != null) {

                if (!tileentityfurnace.large) return true;

                System.out.println("BBB");

                par5EntityPlayer.openGui(SextiarySector.instance, 5, par1World, tileentityfurnace.largeX, tileentityfurnace.largeY, tileentityfurnace.largeZ);
            }

            return true;
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        TileEntityStoneFrame stoneFrame = (TileEntityStoneFrame) world.getTileEntity(x, y, z);

        if (!stoneFrame.large) return 0;

        TileEntityCraftFurnace tileentityfurnace = stoneFrame.getCraftFurnace();

        if (tileentityfurnace == null || tileentityfurnace instanceof TileEntityCraftFurnace) {
            stoneFrame.large = false;
            return 0;
        }

        if (tileentityfurnace.isFuel()) return 15;
        return 0;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
        TileEntityStoneFrame stoneFrame = (TileEntityStoneFrame) world.getTileEntity(x, y, z);

        if (!stoneFrame.large) return;

        stoneFrame.breakLarge();

        super.breakBlock(world, x, y, z, par5, par6);

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityStoneFrame();
    }

}
