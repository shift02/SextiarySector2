package shift.sextiarysector.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import shift.sextiarysector.container.ItemBox;

public class EntityMineboatContainer extends EntityMineboat  implements IInventory{

	//private ItemStack[] mineboatContainerItems = new ItemStack[36];
	protected ItemBox items = new ItemBox("Base", 36);

	private boolean dropContentsWhenDead = true;

	public EntityMineboatContainer(World par1World) {
		super(par1World);

	}

	public EntityMineboatContainer(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);

	}

	@Override
	public void killMineBoat(DamageSource par1DamageSource)
    {
        super.killMineBoat(par1DamageSource);

        for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            ItemStack itemstack = this.getStackInSlot(i);

            if (itemstack != null)
            {
                float f = this.rand.nextFloat() * 0.8F + 0.1F;
                float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                while (itemstack.stackSize > 0)
                {
                    int j = this.rand.nextInt(21) + 10;

                    if (j > itemstack.stackSize)
                    {
                        j = itemstack.stackSize;
                    }

                    itemstack.stackSize -= j;
                    EntityItem entityitem = new EntityItem(this.worldObj, this.posX + f, this.posY + f1, this.posZ + f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
                    float f3 = 0.05F;
                    entityitem.motionX = (float)this.rand.nextGaussian() * f3;
                    entityitem.motionY = (float)this.rand.nextGaussian() * f3 + 0.2F;
                    entityitem.motionZ = (float)this.rand.nextGaussian() * f3;
                    this.worldObj.spawnEntityInWorld(entityitem);
                }
            }
        }
    }

	@Override
	public int getSizeInventory() {
		return this.items.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int par1)
    {
        return this.items.getStackInSlot(par1);
    }

	@Override
	public ItemStack decrStackSize(int par1, int par2)
    {
		return this.items.decrStackSize(par1, par2);
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
    {
        return this.getStackInSlotOnClosing(par1);
    }

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.items.setInventorySlotContents(par1, par2ItemStack);
    }

	@Override
	public void openInventory() {}

	@Override
    public void closeInventory() {}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.isDead ? false : par1EntityPlayer.getDistanceSqToEntity(this) <= 64.0D;
    }

	@Override
	public boolean isInvNameLocalized()
    {
        return this.entityName != null;
    }

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return true;
    }

	@Override
	public String getInventoryName()
    {
        return this.isInvNameLocalized() ? this.func_95999_t() : "container.mineboat";
    }

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void markDirty() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int getInventoryStackLimit()
    {
        return 64;
    }

	@Override
    public void travelToDimension(int par1)
    {
        this.dropContentsWhenDead = false;
        super.travelToDimension(par1);
    }

	@Override
	public void setDead()
    {
        if (this.dropContentsWhenDead)
        {
            for (int i = 0; i < this.getSizeInventory(); ++i)
            {
                ItemStack itemstack = this.getStackInSlot(i);

                if (itemstack != null)
                {
                    float f = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int j = this.rand.nextInt(21) + 10;

                        if (j > itemstack.stackSize)
                        {
                            j = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j;
                        EntityItem entityitem = new EntityItem(this.worldObj, this.posX + f, this.posY + f1, this.posZ + f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (float)this.rand.nextGaussian() * f3;
                        entityitem.motionY = (float)this.rand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float)this.rand.nextGaussian() * f3;
                        this.worldObj.spawnEntityInWorld(entityitem);
                    }
                }
            }
        }

        super.setDead();
    }

	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        items.writeToNBT(par1NBTTagCompound);
    }

	@Override
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        items.readFromNBT(par1NBTTagCompound);
    }

	@Override
    //ここに重要な事がある。
    public boolean interactFirst(EntityPlayer par1EntityPlayer)
    {
		/*
        if(MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent((EntityMinecart)this, par1EntityPlayer)))
        {
            return true;
        }
        */

        if (!this.worldObj.isRemote)
        {


            par1EntityPlayer.displayGUIChest(this);
        }

        return true;
    }

}
