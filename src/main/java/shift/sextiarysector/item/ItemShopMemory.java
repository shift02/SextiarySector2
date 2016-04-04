package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.shop.IShopMemory;
import shift.sextiarysector.module.ModuleShop.ShopSeasonBase;

public class ItemShopMemory extends Item implements IShopMemory {

    private ResourceLocation monitor;
    private ShopSeasonBase shop;

    public ItemShopMemory(String resource) {
        this.monitor = new ResourceLocation("sextiarysector:textures/models/monitor_" + resource + ".png");
        this.setMaxStackSize(1);
        this.setCreativeTab(SextiarySectorAPI.TabSSEconomy);
    }

    @Override
    public ResourceLocation getMonitorResource() {
        return monitor;
    }

    @Override
    public int getShopID(World world, EntityPlayer player) {

        return this.shop.getID();

    }

    public void setShopData(ShopSeasonBase shop) {
        this.shop = shop;
    }

}
