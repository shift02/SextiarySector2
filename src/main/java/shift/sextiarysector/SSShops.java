package shift.sextiarysector;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProductItem;
import shift.mceconomy2.api.shop.IProductList;
import shift.mceconomy2.api.shop.ProductItem;
import shift.sextiarysector.block.BlockMonitor;
import shift.sextiarysector.block.BlockMonitor.MonitorType;
import shift.sextiarysector.item.ItemFigureBox;
import shift.sextiarysector.item.ItemShopRing;

public class SSShops {

	public static SSProductList[] creepers;

	public static void initShops() {

		creepers = new SSProductList[4];

		for (int i = 0; i < 4; i++) {

			creepers[i] = new SSProductList("shop.ss.creeper");
			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.blueStoneDust, 2), 320));
			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.yellowStoneDust, 2), 320));

			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.brassIngot, 2), 230));

			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.takumiTeaBottle, 2), 216));

			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.seasonStone, 1), 3000));

			creepers[i].addItemProduct(new ProductItem(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox("figure_beginner"), 300));
			creepers[i].addItemProduct(new ProductItem(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox("ore_festival"), 500));

			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.woodStoneGearShaft, 1, 0), 260));
			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.woodStoneGearShaft, 1, 1), 260));

			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.gfContactLenses, 1), 300));

			//creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.attackUnit, 1), 2400));
			//creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.defenseUnit, 1), 3600));
			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.craftUnit, 1), 6800));

			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.wood, 1), 180));

			if (i == 0) {
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.turnip, 1), 180));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.cucumber, 1), 360));

				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.rice, 1), 280));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.shiitake, 1), 210));

				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.mithrilIngot, 1), 1400));
			}

			if (i == 1) {
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.onion, 1), 180));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.tomato, 1), 190));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.corn, 1), 740));

				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.magicDust, 4), 500));
			}

			if (i == 2) {
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.eggplant, 1), 290));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.sweetPotato, 1), 110));
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.greenPepper, 1), 320));

				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.fluidCrafter, 4, 0), 580));

			}

			if (i == 3) {
				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.radish, 1), 170));

				creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.stoneDust, 4), 500));
			}

			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.curryPowder, 4), 200));

			creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.orichalcumGem, 1), 9999));

			creepers[i].addItemProduct(new ProductItem(BlockMonitor.getMonitor(MonitorType.creeper), 100000));

		}

		MonitorType.creeper.setList(creepers);
		//SSProductList[] creepersR = new SSProductList[4];
		//for (int i = 0; i < 4; i++) {
		//	creepersR[i] = creepers[i].copySSProductList();
		//}
		//((ItemShopRing) SSItems.creeperRing).setList(creepersR);
		((ItemShopRing) SSItems.creeperRing).setList(creepers);

		SSProductList robot = new SSProductList("shop.ss.robot");
		MonitorType.robot.setList(robot);

		robot.addItemProduct(new ProductItem(new ItemStack(Blocks.lava, 1), 250));

	}

	public static class SSProductList implements IProductList {

		public ArrayList<IProductItem> productItemList = new ArrayList<IProductItem>();
		String name;
		public int id;

		public SSProductList(String name) {
			this.name = name;
			this.id = MCEconomyAPI.registerProductList(this);
		}

		@Override
		public String getProductListName() {
			return this.name;
		}

		public SSProductList copySSProductList() {

			SSProductList c = new SSProductList(this.name);
			c.productItemList = this.getProductList();

			return c;
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

	public static void initPurchase() {

		MCEconomyAPI.addPurchaseItem(new ItemStack(Items.skull, 1, OreDictionary.WILDCARD_VALUE), 25);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.turnip), 280);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.cucumber), 350);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.ironTurnip), 300);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.turnip, 1, 1), 2800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.cucumber, 1, 1), 3500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.ironTurnip, 1, 1), 3000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.onion), 320);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.tomato), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.corn), 720);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.goldenCorn), 800);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.onion, 1, 1), 3200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.tomato, 1, 1), 1500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.corn, 1, 1), 7200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.goldenCorn, 1, 1), 8000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.eggplant), 210);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.sweetPotato), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.greenPepper), 200);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.eggplant, 1, 1), 2100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.sweetPotato, 1, 1), 1200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.greenPepper, 1, 1), 2000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.radish), 220);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.radish, 1, 1), 2200);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.rice), 400);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.rice, 1, 1), 4000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.shiitake), 310);

		MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.shiitake, 1, 1), 3100);

	}

}
