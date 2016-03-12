package shift.sextiarysector.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public abstract class CustomPlayerStats {

    protected EntityPlayer entityPlayer;

    public CustomPlayerStats(EntityPlayer player) {
        this.entityPlayer = player;
    }

    public EntityPlayer getEntityPlayer() {
        return entityPlayer;
    }

    public abstract void onUpdate();

    public abstract void writeNBT(NBTTagCompound compound);

    public abstract void readNBT(NBTTagCompound compound);
}