package shift.sextiarysector.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.season.SeasonAPI;
import shift.sextiarysector.api.shop.IShopMemory;
import shift.sextiarysector.api.shop.ProductListBase;

public class ItemShopMemory extends Item implements IShopMemory {

    private ResourceLocation monitor;
    private ProductListBase list;
    private ProductListBase[] seasonList;

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
    public int getShopID(World world) {

        if (world == null || seasonList == null) return this.getList().getShopID();

        return seasonList[SeasonAPI.getSeason(world).ordinal()].getShopID();

    }

    public ProductListBase getList() {
        return list;
    }

    public void setList(ProductListBase list) {
        this.list = list;
    }

    public void setList(ProductListBase[] list) {
        this.seasonList = list;
        this.list = list[0];
    }

}
