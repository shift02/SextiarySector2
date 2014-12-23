package shift.sextiarysector.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSFluids.SSFluid;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.event.BlockBottleEvent;
import shift.sextiarysector.block.BlockMonitor;
import shift.sextiarysector.block.BlockMonitor.MonitorType;
import shift.sextiarysector.item.ItemKnife;
import shift.sextiarysector.module.ModuleAchievement;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class CommonEventHandler {

	@SubscribeEvent
	public void useEvent(PlayerInteractEvent event) {


		ItemStack item = event.entityPlayer.getCurrentEquippedItem();
		EntityPlayer p = event.entityPlayer;

		if(item!=null && item.getItem()==Items.fireworks && item.stackTagCompound !=null){

			NBTTagCompound nbt = item.getTagCompound().getCompoundTag("Fireworks");

			if (nbt != null)
            {
				NBTTagList nbttaglist = nbt.getTagList("Explosions", 10);

				if (nbttaglist != null && nbttaglist.tagCount() > 0)
                {

					NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(0);

					byte b0 = nbttagcompound1.getByte("Type");
					if(b0==3){
						p.addStat(ModuleAchievement.creeperFirework, 1);
					}

                }

            }


		}

	}

	/**クリーパーチェスト*/
	@SubscribeEvent
	public void livingSleepingEvent(LivingUpdateEvent event) {
		if (event.entityLiving.worldObj.isRemote) {
			return;
		}

		if (!(event.entityLiving instanceof EntityPlayerMP)) {
			return;
		}

		if (!event.entityLiving.worldObj.isRemote) {

			EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;

			if(!player.func_147099_x().hasAchievementUnlocked(ModuleAchievement.creeperFirework)||player.func_147099_x().hasAchievementUnlocked(ModuleAchievement.creeperChest)){
				return;
			}

			if (player.isPlayerFullyAsleep()) {

				//System.out.println("CCCCC");

				int x = (int) player.posX;
			    int y = (int) player.posY;
			    int z = (int) player.posZ;
			    World world = player.worldObj;

			    int range = 1;
			    for (int i = -range; i < range; i++){
			    	for (int j = -range; j < range; j++) {
			    		for(int k = -range; k < range; k++){
			    			if (world.isAirBlock(x + i, y + k, z + j)) {
			    				if (generateChest(world, x + i, y + k, z + j)) {
			    					player.addStat(ModuleAchievement.creeperChest, 1);
			    					return;
			    				}
			    			}
			    		}
			    	}
			    }
			}

		}

	}

	protected boolean generateChest(World world, int x, int y, int z)
	{
		boolean isGen = world.setBlock(x, y, z,SSBlocks.creeperChest);
	    if (isGen)
	    {
	    	TileEntityChest tileEntityChest = (TileEntityChest)world.getTileEntity(x, y, z);
		      if (tileEntityChest != null) {

		    	  tileEntityChest.setInventorySlotContents(0,new ItemStack(SSBlocks.shippingBox));
		    	  tileEntityChest.setInventorySlotContents(1,BlockMonitor.getMonitor(MonitorType.creeper));
		    	  tileEntityChest.setInventorySlotContents(2,new ItemStack(Items.gunpowder,16));
		    	  tileEntityChest.setInventorySlotContents(3,new ItemStack(Items.diamond,4));

		    	  return true;

		      }
	    }
	    return false;
	}

	@SubscribeEvent
    public void onSpawn(LivingSpawnEvent.CheckSpawn event)
    {
		/*
		World world = event.world;
        EntityLivingBase living = event.entityLiving;

        if (living instanceof IMob)
        {
            int x = (int)event.x;
            int y = (int)event.y;
            int z = (int)event.z;

            System.out.println( x+" : "+ y + " : "+ z);

            if(world.getBlock(x, y-1, z)!=Blocks.stone){
            	world.setBlock(x, y, z, Blocks.stone);
            }

        }*/
    }

	/** */
	@SubscribeEvent
    public void onPlayerEatenEvent(BlockBottleEvent event) {

		FluidStack fluid = event.fluid;
		EntityPlayer player = (EntityPlayer) event.entity;

		if(fluid.getFluid() instanceof SSFluid){

			SextiarySectorAPI.playerManager.addMoistureStats(player, ((SSFluid)fluid.getFluid()).moisture, ((SSFluid)fluid.getFluid()).moistureSaturation);

		}

	}

	@SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event)
    {

		if (event.source.getSourceOfDamage() == null || event.source.getEntity() == null) {
			return;
		}

		Entity entity = event.source.getEntity();

		if (entity instanceof EntityPlayer)
        {
			EntityPlayer p = (EntityPlayer)entity;

			ItemStack item = p.getCurrentEquippedItem();

			if(item == null){
				return;
			}

			if(item.getItem() instanceof ItemKnife){

				double x = event.entityLiving.posX;
				double y = event.entityLiving.posY;
				double z = event.entityLiving.posZ;

				if(event.entityLiving instanceof EntityCreeper){
					event.drops.add(new EntityItem(event.entityLiving.worldObj, x, y, z, new ItemStack(Items.skull, 1, 4)));
				}

			}


        }

    }

	/*
	@SubscribeEvent
	public void onFluidRegisterEvent(FluidRegisterEvent event) {

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(event.fluidID), new ItemStack(SSBlocks.fluidCrafter,1,event.fluidID), new ItemStack(SSBlocks.fluidCrafter,1,0));

	}*/









}
