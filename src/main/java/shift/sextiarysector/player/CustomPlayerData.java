package shift.sextiarysector.player;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import shift.sextiarysector.packet.PacketPlayerData;
import shift.sextiarysector.packet.SSPacketHandler;

public class CustomPlayerData implements IExtendedEntityProperties {

    /** 水分 */
    private MoistureStats moisture;

    /** スタミナ */
    private StaminaStats stamina;

    /**装備**/
    private EquipmentStats equipment;

    /**出荷箱 */
    private ShippingBoxStats shippingBox;

    //@SideOnly(Side.CLIENT)
    //private TabStats tab;

    public void onUpdateEntity(EntityPlayer entityPlayer) {

        if (moisture.isPacket() || stamina.isPacket()) {
            SSPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this), (EntityPlayerMP) entityPlayer);
            //.out.println("onUpdateEntity");
        }

        if (equipment.isPacket()) {
            PacketPlayerData d = new PacketPlayerData(this);
            d.getData().setString("uuid", entityPlayer.getUniqueID().toString());
            SSPacketHandler.INSTANCE.sendToAllAround(d, new TargetPoint(entityPlayer.dimension, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, 64));
        }

        this.moisture.onUpdate(entityPlayer);
        this.stamina.onUpdate(entityPlayer);
        this.equipment.onUpdate(entityPlayer);
        this.shippingBox.onUpdate(entityPlayer);

    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {

        this.moisture.writeNBT(compound);

        this.stamina.writeNBT(compound);

        this.equipment.writeNBT(compound);

        this.shippingBox.writeNBT(compound);

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

        this.moisture.readNBT(compound);

        this.stamina.readNBT(compound);

        this.equipment.readNBT(compound);

        this.shippingBox.readNBT(compound);

    }

    @Override
    public void init(Entity entity, World world) {

        if (this.moisture == null) this.moisture = new MoistureStats();

        if (this.stamina == null) this.stamina = new StaminaStats();

        if (this.equipment == null) this.equipment = new EquipmentStats();

        if (this.shippingBox == null) this.shippingBox = new ShippingBoxStats();

        //this.tab = new TabStats();

    }

    public MoistureStats getMoisture() {
        return moisture;
    }

    private void setMoisture(MoistureStats moisture) {
        this.moisture = moisture;
    }

    public StaminaStats getStamina() {
        return stamina;
    }

    private void setStamina(StaminaStats stamina) {
        this.stamina = stamina;
    }

    public EquipmentStats getEquipmentStats() {
        return equipment;
    }

    public void setEquipmentStats(EquipmentStats e) {
        this.equipment = e;
    }

    public ShippingBoxStats getShippingBoxStats() {
        return shippingBox;
    }

    public void setShippingBoxStats(ShippingBoxStats s) {
        this.shippingBox = s;
    }

    //@SideOnly(Side.CLIENT)
    //public void setTabList(ArrayList<AbstractTab> tabList){
    //	tab.setTabList(tabList);
    //}

    //@SideOnly(Side.CLIENT)
    //public ArrayList<AbstractTab> getTabList(){
    //	return tab.getTabList();
    //}

    //public void setSelectPage(int i){
    //	tab.selectPage = i;
    //}

    ///public int getSelectPage(){
    //	return tab.selectPage;
    //}

}
