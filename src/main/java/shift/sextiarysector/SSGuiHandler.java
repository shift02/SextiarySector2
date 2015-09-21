package shift.sextiarysector;

import java.util.ArrayList;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import shift.sextiarysector.container.ContainerCraftFurnace;
import shift.sextiarysector.container.ContainerFluidGFMachineBase;
import shift.sextiarysector.container.ContainerFluidMachineBase;
import shift.sextiarysector.container.ContainerFreezer;
import shift.sextiarysector.container.ContainerFunnel;
import shift.sextiarysector.container.ContainerGFTank;
import shift.sextiarysector.container.ContainerLargeFurnace;
import shift.sextiarysector.container.ContainerMPRepair;
import shift.sextiarysector.container.ContainerMagicFurnace;
import shift.sextiarysector.container.ContainerPlayerNext;
import shift.sextiarysector.container.ContainerQuiver;
import shift.sextiarysector.container.ContainerRucksack;
import shift.sextiarysector.container.ContainerShippingBox;
import shift.sextiarysector.container.ContainerSimpleMachine;
import shift.sextiarysector.container.ContainerSteamMotor;
import shift.sextiarysector.container.ContainerTabWorkbench;
import shift.sextiarysector.gui.GuiCraftFurnace;
import shift.sextiarysector.gui.GuiExtractor;
import shift.sextiarysector.gui.GuiFluidFurnace;
import shift.sextiarysector.gui.GuiFoodSmokers;
import shift.sextiarysector.gui.GuiFreezer;
import shift.sextiarysector.gui.GuiFunnel;
import shift.sextiarysector.gui.GuiGFTank;
import shift.sextiarysector.gui.GuiInventoryNext;
import shift.sextiarysector.gui.GuiLargeFurnace;
import shift.sextiarysector.gui.GuiLoom;
import shift.sextiarysector.gui.GuiMPRepair;
import shift.sextiarysector.gui.GuiMagicFurnace;
import shift.sextiarysector.gui.GuiManaSqueezer;
import shift.sextiarysector.gui.GuiMillstone;
import shift.sextiarysector.gui.GuiPulverizer;
import shift.sextiarysector.gui.GuiQuiver;
import shift.sextiarysector.gui.GuiRollingMachine;
import shift.sextiarysector.gui.GuiRucksack;
import shift.sextiarysector.gui.GuiSawmill;
import shift.sextiarysector.gui.GuiShippingBox;
import shift.sextiarysector.gui.GuiSpinningMachine;
import shift.sextiarysector.gui.GuiSteamMotor;
import shift.sextiarysector.gui.GuiTabCrafting;
import shift.sextiarysector.gui.GuiTimeMachine;
import shift.sextiarysector.gui.IServerGuiElement;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.tileentity.TileEntityCraftFurnace;
import shift.sextiarysector.tileentity.TileEntityFluidFGFMachineBase;
import shift.sextiarysector.tileentity.TileEntityFluidMachineBase;
import shift.sextiarysector.tileentity.TileEntityFreezer;
import shift.sextiarysector.tileentity.TileEntityFunnel;
import shift.sextiarysector.tileentity.TileEntityGFTank;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;
import shift.sextiarysector.tileentity.TileEntityMagicFurnace;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;
import shift.sextiarysector.tileentity.TileEntitySteamMotor;

public class SSGuiHandler implements IGuiHandler {

    public static ArrayList<IServerGuiElement> list = new ArrayList<IServerGuiElement>();

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //System.out.println(ID);

        /*
        if(list.size()<ID){
        	return list.get(ID);
        }else{
        	return null;
        }*/

        switch (ID) {

            case 0:
                return new ContainerLargeFurnace(player.inventory, (TileEntityLargeFurnace) world.getTileEntity(x, y, z));
            case 1:
            case 2:
                return new ContainerFluidMachineBase(player.inventory, (TileEntityFluidMachineBase) world.getTileEntity(x, y, z));

            case 5:
                return new ContainerCraftFurnace(player.inventory, (TileEntityCraftFurnace) world.getTileEntity(x, y, z));
            case 6:
                return new ContainerFreezer(player.inventory, (TileEntityFreezer) world.getTileEntity(x, y, z));

            case 10:
                return new ContainerMPRepair(player.inventory, world, x, y, z, player);
            case 11:
                return new ContainerFunnel(player.inventory, (TileEntityFunnel) world.getTileEntity(x, y, z));

            case 20:
            case 21:
            case 25:
            case 26:
            case 30:
            case 35:
            case 40:
                return new ContainerSimpleMachine(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));

            case 31:
            case 36:
                return new ContainerFluidGFMachineBase(player.inventory, (TileEntityFluidFGFMachineBase) world.getTileEntity(x, y, z));

            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                return new ContainerGFTank(player.inventory, (TileEntityGFTank) world.getTileEntity(x, y, z));

            case 60:
                return new ContainerSteamMotor(player.inventory, (TileEntitySteamMotor) world.getTileEntity(x, y, z));

            case 90:
                return new ContainerMagicFurnace(player.inventory, (TileEntityMagicFurnace) world.getTileEntity(x, y, z));

            case 200:
                return new ContainerPlayerNext(player.inventory, player);
            case 201:
                return new ContainerTabWorkbench(player.inventory, world, x, y, z);

            case 205:
                return new ContainerRucksack(player.inventory);
            case 206:
                return new ContainerRucksack(player.inventory, EntityPlayerManager.getEquipmentStats(player).inventory);

            case 207:
                return new ContainerQuiver(player.inventory);
            case 208:
                return new ContainerQuiver(player.inventory, EntityPlayerManager.getEquipmentStats(player).inventory);

            case 210:
                return new ContainerShippingBox(player.inventory, player);
            case 211:
                return new ContainerRucksack(player.inventory, EntityPlayerManager.getEquipmentStats(player).inventory);

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
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch (ID) {

            case 0:
                return new GuiLargeFurnace(player.inventory, (TileEntityLargeFurnace) world.getTileEntity(x, y, z));
            case 1:
                return new GuiFluidFurnace(player.inventory, (TileEntityFluidMachineBase) world.getTileEntity(x, y, z));
            case 2:
                return new GuiFoodSmokers(player.inventory, (TileEntityFluidMachineBase) world.getTileEntity(x, y, z));

            case 5:
                return new GuiCraftFurnace(player.inventory, (TileEntityCraftFurnace) world.getTileEntity(x, y, z));
            case 6:
                return new GuiFreezer(player.inventory, (TileEntityFreezer) world.getTileEntity(x, y, z));

            case 10:
                return new GuiMPRepair(player.inventory, world, x, y, z);

            case 11:
                return new GuiFunnel(player.inventory, (TileEntityFunnel) world.getTileEntity(x, y, z));

            case 20:
                return new GuiMillstone(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));
            case 21:
                return new GuiLoom(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));

            case 25:
                return new GuiSawmill(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));
            case 26:
                return new GuiSpinningMachine(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));

            case 30:
                return new GuiPulverizer(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));
            case 31:
                return new GuiExtractor(player.inventory, (TileEntityFluidFGFMachineBase) world.getTileEntity(x, y, z));

            case 35:
                return new GuiRollingMachine(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));

            case 36:
                return new GuiManaSqueezer(player.inventory, (TileEntityFluidFGFMachineBase) world.getTileEntity(x, y, z));

            case 40:
                return new GuiTimeMachine(player.inventory, (TileEntitySimpleMachine) world.getTileEntity(x, y, z));

            case 50:
                return new GuiGFTank(player.inventory, (TileEntityGFTank) world.getTileEntity(x, y, z), 1);
            case 51:
                return new GuiGFTank(player.inventory, (TileEntityGFTank) world.getTileEntity(x, y, z), 2);
            case 52:
                return new GuiGFTank(player.inventory, (TileEntityGFTank) world.getTileEntity(x, y, z), 3);
            case 53:
                return new GuiGFTank(player.inventory, (TileEntityGFTank) world.getTileEntity(x, y, z), 4);
            case 54:
                return new GuiGFTank(player.inventory, (TileEntityGFTank) world.getTileEntity(x, y, z), 5);

            case 60:
                return new GuiSteamMotor(player.inventory, (TileEntitySteamMotor) world.getTileEntity(x, y, z));

            case 90:
                return new GuiMagicFurnace(player.inventory, (TileEntityMagicFurnace) world.getTileEntity(x, y, z));

            case 200:
                return new GuiInventoryNext(player);
            case 201:
                return new GuiTabCrafting(player.inventory, world, x, y, z);

            case 205:
                return new GuiRucksack(player.inventory);
            case 206:
                return new GuiRucksack(player.inventory, EntityPlayerManager.getEquipmentStats(player).inventory);

            case 207:
                return new GuiQuiver(player.inventory);
            case 208:
                return new GuiQuiver(player.inventory, EntityPlayerManager.getEquipmentStats(player).inventory);

            case 210:
                return new GuiShippingBox(player);

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
