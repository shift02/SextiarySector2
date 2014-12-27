package shift.sextiarysector.tileentity;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import shift.sextiarysector.container.InventoryFurnaceCrafting;
import shift.sextiarysector.container.ItemBox;
import shift.sextiarysector.recipe.FurnaceCraftingManager;

import com.mojang.authlib.GameProfile;

public class TileEntityCraftFurnace  extends TileEntitySimpleFurnace {

	protected static final int[] slots_top = new int[] { 2,3,4,5,6,7,8,9,10 };
	protected static final int[] slots_bottom = new int[] { 1, 0 };
	protected static final int[] slots_sides = new int[] { 1 };

	protected static FakePlayer player;// = new FakePlayer(worldObj, null);

	private int time = 0;

	//0 燃料 ,1 完成品
	protected ItemBox items = new ItemBox("Base", 2);

	//2,3,4,5,6,7,8,9,10 素材
	public InventoryFurnaceCrafting craftMatrix = new InventoryFurnaceCrafting(3, 3);


	private boolean large = false;


	@Override
	public void updateServerEntity()
	{
		super.updateServerEntity();

		if(large)return;

		if(time<=100){
			time++;
			return;
		}else{
			time = 0;
		}


		if(!checkLarge()){
			return;
		}else{
			this.large = true;
			setLarge();
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

	}

	private boolean checkLarge(){

		System.out.println("AAA");
		int h = 1;

		for(int i = h*-1; i<=h; i++){
			for(int j = h*-1; j<=h; j++){
				for(int k = h*-1; k<=h; k++){

					if(i == 0 && j == 0 && k==0)continue;

					int x = this.xCoord + i;
					int y = this.yCoord + j;
					int z = this.zCoord + k;

					//if(this.worldObj.getBlock(x, y, z) != SSBlocks.stoneFrame)return false;

				}
			}
		}

		return true;
	}

	private void setLarge(){

		int h = 1;

		for(int i = h*-1; i<=h; i++){
			for(int j = h*-1; j<=h; j++){
				for(int k = h*-1; k<=h; k++){

					if(i == 0 && j == 0 && k==0)continue;

					int x = this.xCoord + i;
					int y = this.yCoord + j;
					int z = this.zCoord + k;

					TileEntityStoneFrame t = (TileEntityStoneFrame) this.worldObj.getTileEntity(x, y, z);

					t.setLarge(xCoord, yCoord, zCoord);

				}
			}
		}

	}

	public void breakLarge(){

		int h = 1;

		for(int i = h*-1; i<=h; i++){
			for(int j = h*-1; j<=h; j++){
				for(int k = h*-1; k<=h; k++){

					if(i == 0 && j == 0 && k==0)continue;

					int x = this.xCoord + i;
					int y = this.yCoord + j;
					int z = this.zCoord + k;

					TileEntityStoneFrame t = (TileEntityStoneFrame) this.worldObj.getTileEntity(x, y, z);

					t.large = false;

					Block block = this.worldObj.getBlock(x, y, z);
					block.dropBlockAsItem(worldObj, x, y, z, 0, 0);
					worldObj.setBlock(x, y, z, Blocks.air, 0, 3);


					this.worldObj.markBlockForUpdate(x, y, z);

				}
			}
		}

		Block block = this.worldObj.getBlock(xCoord, yCoord, zCoord);
		block.dropBlockAsItem(worldObj, xCoord, yCoord, zCoord, 0, 0);
		worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air, 0, 3);
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

	}

	public boolean canWork()
    {
        if (!hasMaterialItems() || !isLarge())return false;


        ItemStack itemstack = this.getResult();

        if (itemstack == null) return false;

        return this.checkItem(itemstack) ;

    }

	public boolean isLarge(){

		return large;

	}

	private boolean hasMaterialItems(){

		for(ItemStack item : craftMatrix.stackList){
			if(item!=null)return true;
		}

		return false;
	}

	public void workItem()
    {
        if (this.canWork())
        {
            ItemStack itemstack = this.getResult();

            //item
            if (this.getItems().getStackInSlot(getResultItemSlot()) == null)
            {
                this.setInventorySlotContents(getResultItemSlot(), itemstack.copy());
            }
            else if (this.getItems().getStackInSlot(getResultItemSlot()).isItemEqual(itemstack))
            {
            	this.getItems().getStackInSlot(getResultItemSlot()).stackSize += itemstack.stackSize;
            }

            this.reduceMaterialStackSize();

            this.markDirty();

            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        }
    }


	@Override
	protected ItemStack getFuelItem() {
		return items.getStackInSlot(0);
	}

	protected int getFuelItemSlot(){
		return 0;
	}

	@Override
	protected void reduceFuelStackSize(){
		this.items.reduceStackSize(0, 1);
	}

	@Override
	@Deprecated
	protected ItemStack getMaterialItem() {
		return null;
	}

	@Override
	protected int getResultItemSlot(){
		return 1;
	}

	@Override
	protected void reduceMaterialStackSize() {

		for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);

            if (itemstack1 != null)
            {
                this.craftMatrix.decrStackSize(i, 1);

                if (itemstack1.getItem().hasContainerItem(itemstack1))
                {
                    ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);

                    if(itemstack2 == null){
                    	continue;
                    }

                    if (itemstack2 != null && itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                    {
                    	MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, itemstack2));
                        continue;
                    }

                    if (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1))
                    {
                        if (this.craftMatrix.getStackInSlot(i) == null)
                        {
                            this.craftMatrix.setInventorySlotContents(i, itemstack2);
                        }
                        else
                        {
                        	this.worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord + 0.5f, yCoord + 3, zCoord + 0.5f, itemstack2));
                        }
                    }
                }
            }
        }

	}

	private FakePlayer getFakePlayer(){

		if(player == null){
			this.player = new FakePlayer((WorldServer) this.worldObj, new GameProfile(new UUID(66, 66), "CraftFurnace"));
		}

		return player;
	}

	private ItemStack getResult() {
		return FurnaceCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj);
	}

	@Override
	@Deprecated
	protected ItemStack getResult(ItemStack stackInSlot) {
		return this.getResult();
	}

	@Override
	public int getItemFuelTime(ItemStack p_145952_0_)
    {
        if (p_145952_0_ == null)
        {
            return 0;
        }
        else
        {
        	return TileEntityFurnace.getItemBurnTime(p_145952_0_);
        }
    }

	@Override
	protected ItemBox getItems() {
		return items;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		if(i == 0|| i ==1){
			getItems().setInventorySlotContents(i, itemstack);
		}else if(2<=i && i <=10){
			this.craftMatrix.setInventorySlotContents(i - 2, itemstack);
		}
	}

	@Override
	public String getInventoryName() {
		return "gui.ss.craft_furnace";
	}

	@Override
	public void markDirty(){
		super.markDirty();
		this.craftMatrix.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 128.0D;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {

		if (i == 0) {
			return this.getItemFuelTime(itemstack) > 0;
		}

		return i != 1;
	}

	//ISidedInventory関係
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return p_102008_3_ != 0 || p_102008_1_ != 1 || p_102008_2_.getItem() == Items.bucket;
    }

	//NBT関係
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		items.readFromNBT(nbt);
		craftMatrix.readFromNBT(nbt);
		large = nbt.getBoolean("large");

	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		items.writeToNBT(nbt);
		craftMatrix.writeToNBT(nbt);
		nbt.setBoolean("large", large);

	}

}
