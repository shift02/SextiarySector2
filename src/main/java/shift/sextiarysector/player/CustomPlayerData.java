package shift.sextiarysector.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import shift.sextiarysector.packet.PacketHandler;
import shift.sextiarysector.packet.PacketPlayerData;

public class CustomPlayerData implements IExtendedEntityProperties{

	/** 水分 */
	private MoistureStats moisture ;

	/** スタミナ */
	private StaminaStats stamina;


	public void onUpdateEntity(EntityPlayer entityPlayer)
    {

		if(moisture.isPacket()||stamina.isPacket()){
			PacketHandler.INSTANCE.sendTo(new PacketPlayerData(this), (EntityPlayerMP) entityPlayer);
			//.out.println("onUpdateEntity");
		}

		//System.out.println("AAA"+this.moisture.getMoistureLevel()+" : "+this.stamina.getStaminaLevel());

		this.moisture.onUpdate(entityPlayer);
		this.stamina.onUpdate(entityPlayer);

    }

	@Override
	public void saveNBTData(NBTTagCompound compound) {

		this.moisture.writeNBT(compound);

		this.stamina.writeNBT(compound);

	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {

		this.moisture.readNBT(compound);

		this.moisture.readNBT(compound);

	}

	@Override
	public void init(Entity entity, World world) {

		this.moisture = new MoistureStats();

		this.stamina = new StaminaStats();

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



}
