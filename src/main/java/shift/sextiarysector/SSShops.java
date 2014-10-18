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

			if(i==0){
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.turnip,1),200));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.cucumber,1),380));
			}

			if(i==1){
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.onion,1),200));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.tomato,1),200));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.corn,1),800));
			}

			if(i==2){
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.eggplant,1),310));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.sweetPotato,1),120));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.greenPepper,1),340));
			}

			if(i==3){
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.radish,1),180));
			}

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

	public static void initPurchase(){

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.turnip), 280);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.cucumber), 350);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.turnip,1,1), 2800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.cucumber,1,1), 3500);


		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.onion), 320);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.tomato), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.corn), 720);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.onion,1,1), 3200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.tomato,1,1), 1500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.corn,1,1), 7200);


		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.eggplant), 210);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.sweetPotato), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.greenPepper), 200);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.eggplant,1,1), 2100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.sweetPotato,1,1), 1200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.greenPepper,1,1), 2000);


		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.radish), 220);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.radish,1,1), 2200);

	}

}
