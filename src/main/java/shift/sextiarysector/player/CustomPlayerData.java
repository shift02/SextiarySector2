package shift.sextiarysector.player;

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

	//@SideOnly(Side.CLIENT)
	//private TabStats tab;

	public void onUpdateEntity(EntityPlayer entityPlayer)
	{

		if (moisture.isPacket() || stamina.isPacket()) {
			SSPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this), (EntityPlayerMP) entityPlayer);
			//.out.println("onUpdateEntity");
		}

		this.moisture.onUpdate(entityPlayer);
		this.stamina.onUpdate(entityPlayer);

	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {

		this.moisture.writeNBT(compound);

		this.stamina.writeNBT(compound);

		this.equipment.writeNBT(compound);

	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {

		this.moisture.readNBT(compound);

		this.stamina.readNBT(compound);

		this.equipment.readNBT(compound);

	}

	@Override
	public void init(Entity entity, World world) {

		this.moisture = new MoistureStats();

		this.stamina = new StaminaStats();

		this.equipment = new EquipmentStats((EntityPlayer) entity);

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
