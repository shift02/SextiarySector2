package shift.sextiarysector.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMineboat extends EntityBoat{

	protected String entityName;;

	public EntityMineboat(World par1World) {
		super(par1World);

	}

	public EntityMineboat(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);

	}

	protected void entityInit()
    {
		super.entityInit();
        this.dataWatcher.addObject(20, new Integer(0));
        this.dataWatcher.addObject(21, new Integer(6));
        this.dataWatcher.addObject(22, Byte.valueOf((byte)0));
    }

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (!this.worldObj.isRemote && !this.isDead)
        {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0F);
            this.setBeenAttacked();
            boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode;

            if (flag || this.getDamageTaken() > 40.0F)
            {
                if (this.riddenByEntity != null)
                {
                    this.riddenByEntity.mountEntity(this);
                }

                if (flag && !this.isInvNameLocalized())
                {
                    this.setDead();
                }
                else
                {
                    this.killMineBoat(par1DamageSource);
                }
            }

            return true;
        }
        else
        {
            return true;
        }
    }

	public void killMineBoat(DamageSource par1DamageSource)
    {
        this.setDead();
        ItemStack itemstack = new ItemStack(Items.boat, 1);

        if (this.entityName != null)
        {
            itemstack.setStackDisplayName(this.entityName);
        }

        this.entityDropItem(itemstack, 0.0F);
    }

	public boolean isInvNameLocalized()
    {
        return this.entityName != null;
    }

	public Block getDisplayTile()
    {
        if (!this.hasDisplayTile())
        {
            return this.getDefaultDisplayTile();
        }
        else
        {
        	int i = this.getDataWatcher().getWatchableObjectInt(20) & 65535;
            return Block.getBlockById(i);
        }
    }

    public Block getDefaultDisplayTile()
    {
        return null;
    }

    public int getDisplayTileData()
    {
        return !this.hasDisplayTile() ? this.getDefaultDisplayTileData() : this.getDataWatcher().getWatchableObjectInt(20) >> 16;
    }

    public int getDefaultDisplayTileData()
    {
        return 0;
    }

    public int getDisplayTileOffset()
    {
        return !this.hasDisplayTile() ? this.getDefaultDisplayTileOffset() : this.getDataWatcher().getWatchableObjectInt(21);
    }

    public int getDefaultDisplayTileOffset()
    {
        return 6;
    }

    public void setDisplayTile(int par1)
    {
        this.getDataWatcher().updateObject(20, Integer.valueOf(par1 & 65535 | this.getDisplayTileData() << 16));
        this.setHasDisplayTile(true);
    }

    public void setDisplayTileData(int par1)
    {
    	this.getDataWatcher().updateObject(20, Integer.valueOf(Block.getIdFromBlock(this.getDisplayTile()) & 65535 | par1 << 16));
        this.setHasDisplayTile(true);
    }

    public void setDisplayTileOffset(int par1)
    {
        this.getDataWatcher().updateObject(21, Integer.valueOf(par1));
        this.setHasDisplayTile(true);
    }

    public boolean hasDisplayTile()
    {
        //return false;//this.getDataWatcher().getWatchableObjectByte(22) == 1;
    	return this.getDataWatcher().getWatchableObjectByte(22) == 1;
    }

    public void setHasDisplayTile(boolean par1)
    {
        this.getDataWatcher().updateObject(22, Byte.valueOf((byte)(par1 ? 1 : 0)));
    }

	public void setMineboatName(String par1Str)
    {
        this.entityName = par1Str;
    }

	public String getCommandSenderName()
    {
        return this.entityName != null ? this.entityName : super.getCommandSenderName();
    }

    public String func_95999_t()
    {
        return this.entityName;
    }

}
