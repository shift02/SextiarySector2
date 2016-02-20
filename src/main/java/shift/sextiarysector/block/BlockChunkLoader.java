package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureClock;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.module.ModuleChunkLoader;
import shift.sextiarysector.module.ModuleChunkLoader.IChunkLoaderBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChunkLoader extends Block implements IChunkLoaderBlock {

    @SideOnly(Side.CLIENT)
    protected IIcon active;

    public BlockChunkLoader() {
        super(Material.iron);
        this.setCreativeTab(SextiarySectorAPI.TabSSCore);
        this.setHardness(1.4f);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int par1, int par2) {
        if (par2 == 1) return this.active;

        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());

        this.active = new TextureClock(this.getTextureName() + "_active");

        ((TextureMap) par1IconRegister).setTextureEntry(this.getTextureName() + "_active", (TextureAtlasSprite) this.active);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (!par1World.isRemote && par5EntityPlayer.isSneaking() && par5EntityPlayer.getCurrentEquippedItem() == null) {

            if (par1World.getBlockMetadata(par2, par3, par4) == 0) {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
                ModuleChunkLoader.setBlockTicket(par1World, par2, par3, par4);
            } else {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
                ModuleChunkLoader.deleteBlockTicket(par1World, par2, par3, par4);
            }

            return true;

        }

        return false;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.getBlockMetadata(x, y, z) == 1) return 15;
        return 0;
    }

    @Override
    public boolean canLoad(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) == 1;
    }

}
