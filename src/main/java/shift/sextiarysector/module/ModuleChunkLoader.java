package shift.sextiarysector.module;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleChunkLoader  implements IModule ,LoadingCallback{

	private static ModuleChunkLoader instance = new ModuleChunkLoader();

	protected static final HashMap<List<Integer>, Ticket> ticketList = new HashMap<List<Integer>, Ticket>();

	private ModuleChunkLoader() {
	}

	public static ModuleChunkLoader getInstance() {
		if(instance==null){
			instance = new ModuleChunkLoader();
		}
		return instance;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {

		ForgeChunkManager.setForcedChunkLoadingCallback(SextiarySector.instance, instance);

	}

	@Override
	public void load(FMLInitializationEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
	}

	public static boolean setBlockTicket(World world, int x, int y, int z){

		Ticket t = ForgeChunkManager.requestTicket(SextiarySector.instance, world, Type.NORMAL);

		setBlockType(t);
		setBlock(t, x, y, z);
		ticketList.put(Arrays.asList(x, y, z), t);
		ForgeChunkManager.forceChunk(t, world.getChunkFromBlockCoords(x, z).getChunkCoordIntPair());
		return true;
	}

	public static void deleteBlockTicket(World world, int x, int y, int z){

		if(ticketList.containsKey(Arrays.asList(x, y, z))){

			ForgeChunkManager.unforceChunk(ticketList.get(Arrays.asList(x, y, z)), world.getChunkFromBlockCoords(x, z).getChunkCoordIntPair());
			ticketList.remove(Arrays.asList(x, y, z));

		}

	}

	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) {

		for(Ticket t : tickets){

			if(this.isBlockTicket(t)){

				int x = t.getModData().getInteger("x");
				int y = t.getModData().getInteger("y");
				int z = t.getModData().getInteger("z");

				if(this.getBlock(t, world) instanceof IChunkLoaderBlock){

					if(((IChunkLoaderBlock) this.getBlock(t, world)).canLoad(world, x, y, z)){
						setBlockTicket(world, x, y, z);
					}else{
						deleteBlockTicket(world, x, y, z);
					}

				}else{
					deleteBlockTicket(world, x, y, z);
				}

			}

		}

	}

	public static void setBlockType(Ticket ticket){
		ticket.getModData().setString("type", "block");
	}

	public boolean isBlockTicket(Ticket ticket){
		return ticket.getModData().getString("type").equals("block");
	}

	public static void setBlock(Ticket ticket, int x, int y, int z){

		ticket.getModData().setInteger("x", x);
		ticket.getModData().setInteger("y", y);
		ticket.getModData().setInteger("z", z);

	}

	public Block getBlock(Ticket ticket,World world){
		return world.getBlock(ticket.getModData().getInteger("x"), ticket.getModData().getInteger("y"), ticket.getModData().getInteger("z"));
	}

	public interface IChunkLoaderBlock{

		public boolean canLoad(World world,int x, int y, int z);

	}

}
