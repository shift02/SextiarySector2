package shift.sextiarysector.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import shift.sextiarysector.SSAchievement;
import shift.sextiarysector.api.IPlayerManager;
import shift.sextiarysector.packet.PacketPlayerData;
import shift.sextiarysector.packet.SSPacketHandler;

public class EntityPlayerManager implements IPlayerManager {//implements {//IPlayerTracker{

    //@SideOnly(Side.CLIENT)
    //public static Minecraft mc = FMLClientHandler.instance().getClient();

    private final static String kye = "sextiarysector";

    private final static int MAX_MOISTURE_LEVEL = 20;
    private final static int MAX_PREVMOISTURE_LEVEL = 20;

    private final static int MAX_STAMINA_LEVEL = 100;
    private final static int MAX_PREV_STAMINA_LEVEL = 20;

    /*
    public static final Map<String, MoistureStats> moistureMap = new HashMap<String, MoistureStats>();
    public static final Map<String, StaminaStats> staminaMap = new HashMap<String, StaminaStats>();
    
    public static final Map<String, Integer> lastMoistureLevel = new HashMap<String, Integer>();
    public static final Map<String, Boolean> wasThirsty = new HashMap<String, Boolean>();
    
    public static final Map<String, Integer> lastStaminaLevel = new HashMap<String, Integer>();
    public static final Map<String, Boolean> wasTired = new HashMap<String, Boolean>();
    
    */

    public static EntityPlayerManager instance = new EntityPlayerManager();

    private EntityPlayerManager() {

    }

    @Override
    public void addMoistureStats(EntityPlayer entityPlayer, int par1, float par2) {
        if (!entityPlayer.worldObj.isRemote) {

            getMoistureStats(entityPlayer).addStats(entityPlayer, par1, par2);
            //Achievement
            entityPlayer.addStat(SSAchievement.moisture, 1);
        }
    }

    @Override
    public void addStaminaStats(EntityPlayer entityPlayer, int par1, float par2) {
        if (!entityPlayer.worldObj.isRemote) {
            getStaminaStats(entityPlayer).addStats(entityPlayer, par1, par2);
        }
    }

    @Override
    public void addMoistureExhaustion(EntityPlayer entityPlayer, float par1) {
        if (!entityPlayer.worldObj.isRemote) {
            getMoistureStats(entityPlayer).addExhaustion(entityPlayer, par1);
        }
    }

    @Override
    public void addStaminaExhaustion(EntityPlayer entityPlayer, float par1) {
        if (!entityPlayer.worldObj.isRemote) {
            getStaminaStats(entityPlayer).addExhaustion(entityPlayer, par1);
        }
    }

    @Override
    public int getMoistureLevel(EntityPlayer entityPlayer) {
        return getMoistureStats(entityPlayer).getMoistureLevel();
    }

    @Override
    public int getStaminaLevel(EntityPlayer entityPlayer) {
        return getStaminaStats(entityPlayer).getStaminaLevel();
    }

    //触らない

    //tick
    @SubscribeEvent
    public void LivingUpdateEvent(LivingUpdateEvent event) {
        if (!event.entityLiving.worldObj.isRemote && event.entityLiving instanceof EntityPlayer) {

            onUpdateEntity((EntityPlayer) event.entityLiving);

        }

    }

    public static void onUpdateEntity(EntityPlayer entityPlayer) {

        getCustomPlayerData(entityPlayer).onUpdateEntity(entityPlayer);

        /*
        String name = entityPlayer.username;
        
        MoistureStats m = getMoistureStats(entityPlayer);
        StaminaStats s = getStaminaStats(entityPlayer);
        int lM;
        int lS;
        boolean wM;
        boolean wS;
        
        if(!lastMoistureLevel.containsKey(name)){
        	lM = -1;
        	lastMoistureLevel.put(name, lM);
        }else{
        	lM =lastMoistureLevel.get(name);
        }
        
        if(!wasThirsty.containsKey(name)){
        	wM = true;
        	wasThirsty.put(name, wM);
        }else{
        	wM =wasThirsty.get(name);
        }
        
        if(!lastStaminaLevel.containsKey(name)){
        	lS = -1;
        	lastStaminaLevel.put(name, lS);
        }else{
        	lS =lastStaminaLevel.get(name);
        }
        
        if(!wasTired.containsKey(name)){
        	wS = true;
        	wasTired.put(name, wS);
        }else{
        	wS =wasTired.get(name);
        }
        
        m.onUpdate(entityPlayer);
        s.onUpdate(entityPlayer);
        
        
        if (lM != m.getMoistureLevel() || m.getSaturationLevel() == 0.0F != wM  ||  lS != s.getStaminaLevel() || s.getSaturationLevel() == 0.0F != wS)
        {
        	entityPlayer.playerNetServerHandler.sendPacketToPlayer(getPacketUpdate(m.getMoistureLevel(),m.getSaturationLevel(),s.getStaminaLevel(),s.getSaturationLevel()));
        	lastMoistureLevel.put(name, m.getMoistureLevel());
        	lastStaminaLevel.put(name, s.getStaminaLevel());
        	wasThirsty.put(name, m.getSaturationLevel() == 0.0F);
        	wasTired.put(name, s.getSaturationLevel() == 0.0F);
        
        	NBTTagCompound nbt = entityPlayer.getEntityData();
        	m.writeNBT(nbt);
        	s.writeNBT(nbt);
        
        }*/

    }

    @SubscribeEvent
    public void onPlayerDropsEvent(PlayerDropsEvent event) {
        if (!event.entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            this.getCustomPlayerData(event.entityPlayer).getEquipmentStats().inventory.dropAllItems(event.entityPlayer);
        }

    }

    @SubscribeEvent
    public void onPlayerCloneEvent(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {

        //死んでいない場合
        if (!event.wasDeath) {

            EntityPlayer old = event.original;

            EquipmentStats e = this.getEquipmentStats(old);
            NBTTagCompound nbt = new NBTTagCompound();
            e.writeNBT(nbt);

            EquipmentStats eNew = this.getEquipmentStats(event.entityPlayer);
            eNew.readNBT(nbt);

            //this.getCustomPlayerData(event.entityPlayer).setEquipmentStats(e);

            ShippingBoxStats s = this.getShippingBoxStats(old);
            NBTTagCompound nbtS = new NBTTagCompound();
            s.writeNBT(nbtS);

            ShippingBoxStats sNew = this.getShippingBoxStats(event.entityPlayer);
            sNew.readNBT(nbtS);

            return;
        }

        if (event.entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {

            this.getCustomPlayerData(event.entityPlayer).setEquipmentStats(this.getEquipmentStats(event.original));

        }

        this.getCustomPlayerData(event.entityPlayer).setShippingBoxStats(this.getShippingBoxStats(event.original));

    }

    private void oneton() {

    }

    /** Playerのデータの登録*/
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayerManager.register((EntityPlayer) event.entity);
        }
    }

    public static void register(EntityPlayer entityPlayer) {
        entityPlayer.registerExtendedProperties(kye, new CustomPlayerData());
    }

    public static CustomPlayerData getCustomPlayerData(EntityPlayer entityPlayer) {
        return (CustomPlayerData) entityPlayer.getExtendedProperties(kye);
    }

    public static MoistureStats getMoistureStats(EntityPlayer entityPlayer) {
        return getCustomPlayerData(entityPlayer).getMoisture();

        /*
        String name = entityPlayer.username;
        
        if(!moistureMap.containsKey(name)){
        	MoistureStats m = new MoistureStats();
        	moistureMap.put(name, m);
        	return m;
        }else{
        	return moistureMap.get(name);
        }*/

    }

    public static StaminaStats getStaminaStats(EntityPlayer entityPlayer) {

        return getCustomPlayerData(entityPlayer).getStamina();

        /*
        String name = entityPlayer.username;
        
        if(!staminaMap.containsKey(name)){
        	StaminaStats s = new StaminaStats();
        	staminaMap.put(name, s);
        	return s;
        }else{
        	return staminaMap.get(name);
        }*/

    }

    public static EquipmentStats getEquipmentStats(EntityPlayer entityPlayer) {
        return getCustomPlayerData(entityPlayer).getEquipmentStats();
    }

    public static ShippingBoxStats getShippingBoxStats(EntityPlayer entityPlayer) {
        return getCustomPlayerData(entityPlayer).getShippingBoxStats();
    }

    //パケット用
    /*
    public void onPacketData(INetworkManager manager,Packet250CustomPayload packet, Player player) {
    
    	if (packet.channel.equals(SextiarySector.channels))
    	{
    
    		ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
    		//ByteArrayInputStream bos = new ByteArrayInputStream(packet.data);
    		//DataInputStream dos =  new DataInputStream(bos);
    
    
    		try
    		{
    
    
    			if(player instanceof EntityPlayer){
    
    				//System.out.println("onPacketData");
    
    				this.getMoistureStats((EntityPlayer) player).setMoistureLevel(data.readInt());
    				this.getStaminaStats((EntityPlayer) player).setStaminaLevel(data.readInt());
    				this.getMoistureStats((EntityPlayer) player).setMoistureSaturationLevel(data.readFloat());
    				this.getStaminaStats((EntityPlayer) player).setStaminaSaturationLevel(data.readFloat());
    
    			}
    
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    
    }
    
    public static Packet getPacketUpdate(int m,float f,int s,float g)
    {
    
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	DataOutputStream dos = new DataOutputStream(bos);
    
    	try
    	{
    		dos.writeInt(m);//
    		dos.writeInt(s);//
    		dos.writeFloat(f);//
    		dos.writeFloat(g);//
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    
    	Packet250CustomPayload packet = new Packet250CustomPayload();
    	packet.channel = SextiarySector.channels;
    	packet.data    = bos.toByteArray();
    	packet.length  = bos.size();
    	packet.isChunkDataPacket = true;
    
    	return packet;
    
    
    }*/

    //GUI用
    public static int getPrevMoistureLevel(EntityPlayer entityPlayer) {

        return getMoistureStats(entityPlayer).getMoistureLevel() / (MAX_MOISTURE_LEVEL / MAX_PREVMOISTURE_LEVEL);

    }

    public static int getPrevStaminaLevel(EntityPlayer entityPlayer) {

        return getStaminaStats(entityPlayer).getStaminaLevel() / (MAX_STAMINA_LEVEL / MAX_PREV_STAMINA_LEVEL);

    }

    /*
    @Override
    public void onPlayerLogin(EntityPlayer player){
    	//プレイヤーがログインした時の処理
    
    	if(player instanceof EntityPlayerMP){
    
    		EntityPlayerMP P = (EntityPlayerMP) player;
    
    		NBTTagCompound nbt = P.getEntityData();
    
    		MoistureStats m = this.getMoistureStats(player);
    		m.readNBT(nbt);
    
    		StaminaStats s = this.getStaminaStats(player);
    		s.readNBT(nbt);
    
    		P.playerNetServerHandler.sendPacketToPlayer(getPacketUpdate(m.getMoistureLevel(),m.getSaturationLevel(),s.getStaminaLevel(),s.getSaturationLevel()));
    
    	}
    }
    
    @Override
    public void onPlayerLogout(EntityPlayer player) {
    	//プレイヤーがログアウトした時の処理
    
    	if(player instanceof EntityPlayerMP){
    
    		EntityPlayerMP P = (EntityPlayerMP) player;
    
    		String name = player.username;
    
    		NBTTagCompound nbt = P.getEntityData();
    
    		MoistureStats m = this.getMoistureStats(player);
    		m.writeNBT(nbt);
    
    		StaminaStats s = this.getStaminaStats(player);
    		s.writeNBT(nbt);
    
    		P.playerNetServerHandler.sendPacketToPlayer(getPacketUpdate(m.getMoistureLevel(),m.getSaturationLevel(),s.getStaminaLevel(),s.getSaturationLevel()));
    
    		moistureMap.remove(name);
    		staminaMap.remove(name);
    
    	}
    
    }
    
    */

    @SubscribeEvent
    /* ワールドに入った時に呼ばれるイベント。 */
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {

        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {

            SSPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this.getCustomPlayerData((EntityPlayer) event.entity)), (EntityPlayerMP) event.entity);

            this.sendOtherPlayer((EntityPlayer) event.entity);

        }
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        //プレイヤーがディメンション間を移動したときの処理

        if (!event.player.worldObj.isRemote) {
            SSPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this.getCustomPlayerData(event.player)), (EntityPlayerMP) event.player);

            this.sendOtherPlayer(event.player);
        }

    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        //プレイヤーがリスポーンした時の処理
        //System.out.println("onPlayerRespawn");
        if (!event.player.worldObj.isRemote) {

            SSPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this.getCustomPlayerData(event.player)), (EntityPlayerMP) event.player);

            this.sendOtherPlayer(event.player);
        }

    }

    public void sendOtherPlayer(EntityPlayer player) {
        //他のプレイヤーに送る
        PacketPlayerData d = new PacketPlayerData(this.getCustomPlayerData(player));
        d.getData().setString("uuid", player.getUniqueID().toString());
        SSPacketHandler.INSTANCE.sendToAllAround(d, new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 64));
    }

}
