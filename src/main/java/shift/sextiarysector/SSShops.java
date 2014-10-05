package shift.sextiarysector;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.ProductItem;
import shift.mceconomy2.api.shop.ProductList;
import shift.sextiarysector.block.BlockMonitor.MonitorType;

public class SSShops {

	public static void initShops(){

		SSProductList creeper = new SSProductList("shop.ss.creeper");
		MonitorType.creeper.setList(creeper);

		creeper.addItemProduct(new ProductItem(new ItemStack(SSItems.blueStoneDust,2),320));
		creeper.addItemProduct(new ProductItem(new ItemStack(SSItems.yellowStoneDust,2),320));

		SSProductList robot = new SSProductList("shop.ss.robot");
		MonitorType.robot.setList(robot);

		robot.addItemProduct(new ProductItem(new ItemStack(Blocks.chest,1),250));

	}

	public static class SSProductList extends ProductList{

		String name;
		public int id;

		public SSProductList(String name){
			this.name = name;
			this.id = MCEconomyAPI.registerProductList(this);
		}

		@Override
		public String getProductListName() {
			return this.name;
		}

	}

}
