package shift.sextiarysector;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import shift.sextiarysector.container.ContainerGFTank;
import shift.sextiarysector.container.ContainerLargeFurnace;
import shift.sextiarysector.container.ContainerSimpleMachine;
import shift.sextiarysector.gui.GuiGFTank;
import shift.sextiarysector.gui.GuiLargeFurnace;
import shift.sextiarysector.gui.GuiLoom;
import shift.sextiarysector.gui.GuiMillstone;
import shift.sextiarysector.gui.GuiSawmill;
import shift.sextiarysector.gui.IServerGuiElement;
import shift.sextiarysector.tileentity.TileEntityGFTank;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;
import cpw.mods.fml.common.network.IGuiHandler;

public class SSGuiHandler implements IGuiHandler {

	public static ArrayList<IServerGuiElement> list = new ArrayList<IServerGuiElement>();


	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z) {
		//System.out.println(ID);

		/*
		if(list.size()<ID){
			return list.get(ID);
		}else{
			return null;
		}*/


		switch(ID){

		case 0 : return new ContainerLargeFurnace(player.inventory, (TileEntityLargeFurnace) world.getTileEntity(x, y, z));

		case 20:
		case 21:
		case 25:
			return new ContainerSimpleMachine(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));

		case 40:
		case 41:
			return new ContainerGFTank(player.inventory, (TileEntityGFTank) world.getTileEntity(x, y, z));

		}

		/*
		if(ID==0){
			return new ContainerBaseMachine(player.inventory, (TileEntityBaseMachine) world.getBlockTileEntity(x, y, z));
		}else if(ID==1){
			return new ContainerLargeFurnace(player.inventory, (TileEntityLargeFurnace) world.getBlockTileEntity(x, y, z));
		}else if(ID==2){
			return new ContainerGFTank(player.inventory, (TileEntityGFTank) world.getBlockTileEntity(x, y, z));
		}else if(ID==3){
			return new ContainerBaseMachine(player.inventory, (TileEntityLoom) world.getBlockTileEntity(x, y, z));
		}else if(ID==4){
			return new ContainerBrewingStand(player.inventory, (TileEntityBrewingStand) world.getBlockTileEntity(x, y, z));
		}else if(ID==5){
			return new ContainerCentrifugalSeparator(player.inventory, (TileEntityCentrifugalSeparator) world.getBlockTileEntity(x, y, z));
		}else

		if(ID/100==1){

			System.out.println(ID+"a");
			if(ID%100>=0&&ID%100<16){
				System.out.println(ID+"b");
				return new ContainerSimpleMachine(player.inventory, (TileEntitySimpleMachine) world.getBlockTileEntity(x, y, z));
			}

		}*/

		return null;


	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z) {


		switch(ID){

		case 0 : return new GuiLargeFurnace(player.inventory, (TileEntityLargeFurnace) world.getTileEntity(x, y, z));

		case 20:return new GuiMillstone(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));
		case 21:return new GuiLoom(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));

		case 25:return new GuiSawmill(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));

		case 40:return new GuiGFTank(player.inventory, (TileEntityGFTank) world.getTileEntity(x, y, z),1);
		case 41:return new GuiGFTank(player.inventory, (TileEntityGFTank) world.getTileEntity(x, y, z),2);

		}

		/*
		System.out.println(ID);
		if(ID==0){
			//return new GuiMillstone(player.inventory, (TileEntityBaseMachine) world.getBlockTileEntity(x, y, z));
		}else if(ID==1){
			return new GuiLargeFurnace(player.inventory, (TileEntityLargeFurnace) world.getBlockTileEntity(x, y, z));
		}else if(ID==2){
			return new GuiGFTank(player.inventory, (TileEntityGFTank) world.getBlockTileEntity(x, y, z));
		}else if(ID==3){
			return new GuiLoom(player.inventory, (TileEntityLoom) world.getBlockTileEntity(x, y, z));
		}else if(ID==4){
			return new GuiBrewingStand(player.inventory, (TileEntityBrewingStand) world.getBlockTileEntity(x, y, z));
		}else if(ID==5){
			return new GuiCentrifugaSeparator(player.inventory, (TileEntityCentrifugalSeparator) world.getBlockTileEntity(x, y, z));
		}else

		if(ID/100==1){
			if(ID%100==0){
				return new GuiMillstone(player.inventory, (TileEntitySimpleMachine) world.getBlockTileEntity(x, y, z));
			}
			if(ID%100==1){
				return new GuiLoon(player.inventory, (TileEntitySimpleMachine) world.getBlockTileEntity(x, y, z));
			}
			if(ID%100==2){
				return new GuiMill(player.inventory, (TileEntitySimpleMachine) world.getBlockTileEntity(x, y, z));
			}

		}

		System.out.println(ID);*/

		return null;

	}

}
