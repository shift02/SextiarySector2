package shift.sextiarysector.entity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMineboatChest extends EntityMineboatContainer {

    public EntityMineboatChest(World par1World) {
        super(par1World);

    }

    public EntityMineboatChest(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);

    }

    @Override
    public void killMineBoat(DamageSource par1DamageSource) {
        super.killMineBoat(par1DamageSource);
        this.func_145778_a(Item.getItemFromBlock(Blocks.chest), 1, 0.0F);
    }

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public String getInventoryName() {
        return this.isInvNameLocalized() ? this.func_95999_t() : "container.mineboat_chest";
    }

    @Override
    public Block getDefaultDisplayTile() {
        return Blocks.chest;
    }

    @Override
    public int getDefaultDisplayTileOffset() {
        return 8;
    }

}
