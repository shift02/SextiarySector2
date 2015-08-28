package shift.sextiarysector.event;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSAchievement;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSFluids.SSFluid;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.event.BlockBottleEvent;
import shift.sextiarysector.block.BlockMonitor;
import shift.sextiarysector.block.BlockMonitor.MonitorType;
import shift.sextiarysector.item.ItemKnife;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CommonEventHandler {

	@SubscribeEvent
	public void useEvent(PlayerInteractEvent event) {

		ItemStack item = event.entityPlayer.getCurrentEquippedItem();
		EntityPlayer p = event.entityPlayer;

		if (item != null && item.getItem() == Items.fireworks && item.stackTagCompound != null) {

			NBTTagCompound nbt = item.getTagCompound().getCompoundTag("Fireworks");

			if (nbt != null)
			{
				NBTTagList nbttaglist = nbt.getTagList("Explosions", 10);

				if (nbttaglist != null && nbttaglist.tagCount() > 0)
				{

					NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(0);

					byte b0 = nbttagcompound1.getByte("Type");
					if (b0 == 3) {
						p.addStat(SSAchievement.creeperFirework, 1);
					}

				}

			}

		}

	}

	/*無理でした(╹◡╹)
	@SubscribeEvent
	public void useBottleEvent(PlayerInteractEvent event) {

		ItemStack item = event.entityPlayer.getCurrentEquippedItem();
		EntityPlayer p = event.entityPlayer;
		World w = event.world;

		if (item == null) return;

		if (item.getItem() != Items.glass_bottle) return;

		//if(w.isRemote)return;

		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(w, p, true);

		if (movingobjectposition == null)
		{
			return;
		}
		else
		{
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!w.canMineBlock(p, i, j, k))
				{
					return;
				}

				if (!p.canPlayerEdit(i, j, k, movingobjectposition.sideHit, item))
				{
					return;
				}

				if (w.getBlock(i, j, k) == SSBlocks.drinkingWater)
				{
					--item.stackSize;
					event.useItem = Result.DENY;
					if (event.action.equals(Action.RIGHT_CLICK_AIR)) {
						event.setCanceled(true);
						event.useItem = Result.DEFAULT;
					}

					//event.setResult(Result.DENY);

					if (item.stackSize <= 0)
					{
						p.inventory.setInventorySlotContents(p.inventory.currentItem, new ItemStack(SSItems.drinkingWaterBottle));
						return;
						//return new ItemStack(SSItems.drinkingWaterSmallBottle);
					}

					if (!p.inventory.addItemStackToInventory(new ItemStack(SSItems.drinkingWaterBottle)))
					{
						p.dropPlayerItemWithRandomChoice(new ItemStack(SSItems.drinkingWaterBottle, 1, 0), false);
					}
				}
			}

			return;
		}

	}*/

	protected MovingObjectPosition getMovingObjectPositionFromPlayer(World p_77621_1_, EntityPlayer p_77621_2_, boolean p_77621_3_)
	{
		float f = 1.0F;
		float f1 = p_77621_2_.prevRotationPitch + (p_77621_2_.rotationPitch - p_77621_2_.prevRotationPitch) * f;
		float f2 = p_77621_2_.prevRotationYaw + (p_77621_2_.rotationYaw - p_77621_2_.prevRotationYaw) * f;
		double d0 = p_77621_2_.prevPosX + (p_77621_2_.posX - p_77621_2_.prevPosX) * f;
		double d1 = p_77621_2_.prevPosY + (p_77621_2_.posY - p_77621_2_.prevPosY) * f + (p_77621_1_.isRemote ? p_77621_2_.getEyeHeight() - p_77621_2_.getDefaultEyeHeight() : p_77621_2_.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = p_77621_2_.prevPosZ + (p_77621_2_.posZ - p_77621_2_.prevPosZ) * f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		if (p_77621_2_ instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP) p_77621_2_).theItemInWorldManager.getBlockReachDistance();
		}
		Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
		return p_77621_1_.func_147447_a(vec3, vec31, p_77621_3_, !p_77621_3_, false);
	}

	/**クリーパーチェスト*/
	@SubscribeEvent
	public void LivingSleepingEvent(PlayerWakeUpEvent event) {

		if (event.entityPlayer.worldObj.isRemote) {
			return;
		}

		if (!(event.entityPlayer instanceof EntityPlayerMP)) {
			return;
		}

		EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;

		if (!player.func_147099_x().hasAchievementUnlocked(SSAchievement.creeperFirework) || player.func_147099_x().hasAchievementUnlocked(SSAchievement.creeperChest)) {
			return;
		}

		int x = (int) player.posX;
		int y = (int) player.posY;
		int z = (int) player.posZ;
		World world = player.worldObj;

		int range = 1;
		for (int i = -range; i < range; i++) {
			for (int j = -range; j < range; j++) {
				for (int k = -range; k < range; k++) {
					if (world.isAirBlock(x + i, y + k, z + j)) {
						if (generateChest(world, x + i, y + k, z + j)) {
							player.addStat(SSAchievement.creeperChest, 1);
							return;
						}
					}
				}
			}
		}

	}

	/**クリーパーチェスト*/
	/*
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

			if(!player.func_147099_x().hasAchievementUnlocked(SSAchievement.creeperFirework)||player.func_147099_x().hasAchievementUnlocked(SSAchievement.creeperChest)){
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
			    					player.addStat(SSAchievement.creeperChest, 1);
			    					return;
			    				}
			    			}
			    		}
			    	}
			    }
			}

		}

	}*/

	protected boolean generateChest(World world, int x, int y, int z)
	{
		boolean isGen = world.setBlock(x, y, z, SSBlocks.creeperChest);
		if (isGen)
		{
			TileEntityChest tileEntityChest = (TileEntityChest) world.getTileEntity(x, y, z);
			if (tileEntityChest != null) {

				tileEntityChest.setInventorySlotContents(0, new ItemStack(SSBlocks.shippingBox));
				tileEntityChest.setInventorySlotContents(1, BlockMonitor.getMonitor(MonitorType.creeper));
				tileEntityChest.setInventorySlotContents(2, new ItemStack(Items.gunpowder, 16));
				tileEntityChest.setInventorySlotContents(3, new ItemStack(Items.diamond, 4));

				return true;

			}
		}
		return false;
	}

	@SubscribeEvent
	public void onSpawn(LivingSpawnEvent.CheckSpawn event)
	{

		World world = event.world;
		EntityLivingBase living = event.entityLiving;

		if (world.rand.nextInt(12) != 0) return;

		if (living instanceof IMob)
		{
			int x = (int) event.x;
			int y = (int) event.y;
			int z = (int) event.z;

			if (!world.getBlock(x, y, z).isAir(world, x, y, z)) return;

			if (world.getBlock(x, y - 1, z) != Blocks.sand) return;

			if (!BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), Type.BEACH)) return;

			world.setBlock(x, y, z, SSBlocks.sandpit);

		}
	}

	@SubscribeEvent
	public void onSpawns(WorldEvent.PotentialSpawns event)
	{

		World world = event.world;

		if (world.rand.nextInt(6) != 0) return;

		int x = event.x;
		int y = event.y;
		int z = event.z;

		if (!world.getBlock(x, y, z).isAir(world, x, y, z)) return;

		if (world.getBlock(x, y - 1, z) != Blocks.sand) return;

		if (!BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), Type.BEACH)) return;

		world.setBlock(x, y, z, SSBlocks.sandpit);

	}

	/** */
	@SubscribeEvent
	public void onPlayerEatenEvent(BlockBottleEvent event) {

		FluidStack fluid = event.fluid;
		EntityPlayer player = (EntityPlayer) event.entity;

		if (fluid.getFluid() instanceof SSFluid) {

			SextiarySectorAPI.playerManager.addMoistureStats(player, ((SSFluid) fluid.getFluid()).moisture, ((SSFluid) fluid.getFluid()).moistureSaturation);

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
			EntityPlayer p = (EntityPlayer) entity;

			ItemStack item = p.getCurrentEquippedItem();

			if (item == null) {
				return;
			}

			if (item.getItem() instanceof ItemKnife) {

				double x = event.entityLiving.posX;
				double y = event.entityLiving.posY;
				double z = event.entityLiving.posZ;

				if (event.entityLiving instanceof EntityCreeper) {
					event.drops.add(new EntityItem(event.entityLiving.worldObj, x, y, z, new ItemStack(Items.skull, 1, 4)));
				}

			}

		}

	}

	//葉っぱ
	@SubscribeEvent
	public void DecayEvent(BlockEvent.HarvestDropsEvent event)
	{

		if (event.isSilkTouching) return;

		if (!(event.block instanceof BlockLeavesBase)) return;

		if (event.harvester != null && event.harvester.getCurrentEquippedItem() != null && event.harvester.getCurrentEquippedItem().getItem() instanceof ItemShears) return;

		if (event.world.rand.nextBoolean()) event.drops.add(new ItemStack(Items.stick, event.world.rand.nextInt(1) + 1));
		if (event.world.rand.nextBoolean()) event.drops.add(new ItemStack(SSItems.leaf, event.world.rand.nextInt(2) + 1));

		byte b0 = 2;
		int i1 = b0 + 1;

		if (event.world.checkChunksExist(event.x - i1, event.y - i1, event.z - i1, event.x + i1, event.y + i1, event.z + i1))
		{
			for (int j1 = -b0; j1 <= b0; ++j1)
			{
				for (int k1 = -b0; k1 <= b0; ++k1)
				{
					for (int l1 = -b0; l1 <= b0; ++l1)
					{
						Block block = event.world.getBlock(event.x + j1, event.y + k1, event.z + l1);
						if (block.isLeaves(event.world, event.x + j1, event.y + k1, event.z + l1))
						{
							//block.updateTick(event.world, event.x + j1, event.y + k1, event.z + l1, event.world.rand);
							event.world.scheduleBlockUpdate(event.x + j1, event.y + k1, event.z + l1, block, 20 + event.world.rand.nextInt(8));
						}
					}
				}
			}
		}

		//for (int i = 0; i < 2; i++) {
		//	this.updateLeavesTick(event);
		//}

	}

	/*
	@SubscribeEvent
	public void DecayEvent(BlockEvent.BreakEvent event)
	{

		byte b0 = 2;
		int i1 = b0 + 1;

		if (event.world.checkChunksExist(event.x - i1, event.y - i1, event.z - i1, event.x + i1, event.y + i1, event.z + i1))
		{
			for (int j1 = -b0; j1 <= b0; ++j1)
			{
				for (int k1 = -b0; k1 <= b0; ++k1)
				{
					for (int l1 = -b0; l1 <= b0; ++l1)
					{
						Block block = event.world.getBlock(event.x + j1, event.y + k1, event.z + l1);
						if (block.isLeaves(event.world, event.x + j1, event.y + k1, event.z + l1))
						{
							block.updateTick(event.world, event.x + j1, event.y + k1, event.z + l1, event.world.rand);
						}
					}
				}
			}
		}
	}*/

	//動物のドロップを増やす
	@SubscribeEvent
	public void onLivingAnimalDrops(LivingDropsEvent event)
	{

		if (event.source.getSourceOfDamage() == null || event.source.getEntity() == null) {
			return;
		}

		double x = event.entityLiving.posX;
		double y = event.entityLiving.posY;
		double z = event.entityLiving.posZ;

		Random r = event.entityLiving.worldObj.rand;

		if (event.entityLiving instanceof EntitySquid) {
			event.drops.add(new EntityItem(event.entityLiving.worldObj, x, y, z, new ItemStack(SSItems.squidSashimi, r.nextInt(5) + 1)));
		}

	}

	/*
	@SubscribeEvent
	public void onFluidRegisterEvent(FluidRegisterEvent event) {

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(event.fluidID), new ItemStack(SSBlocks.fluidCrafter,1,event.fluidID), new ItemStack(SSBlocks.fluidCrafter,1,0));

	}*/

}
