package shift.sextiarysector;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.ProductItem;
import shift.mceconomy2.api.shop.ProductList;
import shift.sextiarysector.block.BlockMonitor.MonitorType;

public class SSShops {

	public static void initShops(){

		SSProductList[] creepers = new SSProductList[4];

		for(int i = 0;i<4;i++){

			creepers[i] = new SSProductList("shop.ss.creeper");
			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.blueStoneDust,2),320));
			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.yellowStoneDust,2),320));

			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.seasonStone,1),3000));

		}

		MonitorType.creeper.setList(creepers);


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
