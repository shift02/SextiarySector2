package shift.sextiarysector.api.shop;

import java.util.ArrayList;

import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProductItem;
import shift.mceconomy2.api.shop.IProductList;

public class ProductListBase implements IProductList {

    private ArrayList<IProductItem> productItemList = new ArrayList<IProductItem>();
    private String name;
    private int id;

    public ProductListBase(String name) {
        this.name = name;
        this.id = MCEconomyAPI.registerProductList(this);
    }

    public int getShopID() {
        return this.id;
    }

    @Override
    public String getProductListName() {
        return this.name;
    }

    @Override
    public void addItemProduct(IProductItem item) {
        productItemList.add(item);
    }

    @Override
    public int getItemProductSize() {
        return productItemList.size();
    }

    @Override
    public ArrayList<IProductItem> getProductList() {
        return productItemList;
    }

}
