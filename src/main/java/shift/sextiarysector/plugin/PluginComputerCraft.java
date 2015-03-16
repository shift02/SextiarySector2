package shift.sextiarysector.plugin;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.machine.energy.IGFEnergyHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.api.turtle.TurtleCommandResult;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.api.turtle.TurtleUpgradeType;
import dan200.computercraft.api.turtle.TurtleVerb;

public class PluginComputerCraft  implements IPlugin{

	@Override
	public void prePlugin(FMLPreInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void initPlugin(FMLInitializationEvent event) {

		ComputerCraftAPI.registerTurtleUpgrade(new TurtleGearTool(420,new ItemStack(SSItems.woodGear,1)));

	}

	@Override
	public void postPlugin(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public static class TurtleGearTool implements ITurtleUpgrade{

		public int id;
		public ItemStack toolItem;

		public TurtleGearTool(){

		}

		public TurtleGearTool(int id,ItemStack item){

			this.id = id;
			this.toolItem = item;

		}

		@Override
		public int getUpgradeID() {
			return this.id;
		}

		@Override
		public String getUnlocalisedAdjective() {
			return toolItem.getUnlocalizedName()+".name";
		}

		@Override
		public TurtleUpgradeType getType() {
			return TurtleUpgradeType.Tool;
		}

		@Override
		public ItemStack getCraftingItem() {
			return this.toolItem;
		}

		@Override
		public IPeripheral createPeripheral(ITurtleAccess turtle,TurtleSide side) {
			return null;
		}

		@Override
		public TurtleCommandResult useTool(ITurtleAccess turtle,TurtleSide side, TurtleVerb verb, int direction) {

			World world = turtle.getWorld();
			ChunkCoordinates position = turtle.getPosition();

			if (position == null)
			{
				return TurtleCommandResult.failure();
			}

			int newX = (int)position.posX + Facing.offsetsXForSide[direction];
			int newY = (int)position.posY + Facing.offsetsYForSide[direction];
			int newZ = (int)position.posZ + Facing.offsetsZForSide[direction];

			if ( (newY < 0) || (newY >= world.getHeight()) )
			{
				return TurtleCommandResult.failure();
			}

			TileEntity t = world.getTileEntity(newX, newY, newZ);
			if(t instanceof IGFEnergyHandler){

				if(verb==TurtleVerb.Attack){
					return this.addEnergy(turtle, (IGFEnergyHandler) t,ForgeDirection.getOrientation(direction) );
				}else{
					return this.getEnergy(turtle, (IGFEnergyHandler) t, ForgeDirection.getOrientation(direction) );
				}

			}

			return TurtleCommandResult.failure();
		}

		private TurtleCommandResult addEnergy(ITurtleAccess turtle,IGFEnergyHandler h,ForgeDirection direction){

			if(turtle.getFuelLevel()>0){

				if(h.addEnergy(direction.getOpposite(), 1, 100, true)>0){
					if(turtle.consumeFuel(10)){
						h.addEnergy(direction.getOpposite(), 1, 100, false);
						return TurtleCommandResult.success();
					}
				}

			}
			return TurtleCommandResult.failure();

		}


		private TurtleCommandResult getEnergy(ITurtleAccess turtle,IGFEnergyHandler h,ForgeDirection direction){

			int i = h.drawEnergy(direction.getOpposite(), 1, 100, true)/10;
			if(i>0){

				turtle.addFuel(i);
				h.drawEnergy(direction.getOpposite(), 1, i*10, false);
				return TurtleCommandResult.success();

			}

			return TurtleCommandResult.failure();

		}

		@Override
		public IIcon getIcon(ITurtleAccess turtle, TurtleSide side) {
			return toolItem.getIconIndex();
		}

		@Override
		public void update(ITurtleAccess turtle, TurtleSide side) {

		}

	}

	@Override
	public String getModName() {
		return "ComputerCraft";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void preClientPlugin(FMLPreInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}


}
