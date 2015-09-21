package shift.sextiarysector.item;

import java.text.NumberFormat;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import shift.sextiarysector.SSFluids;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.api.equipment.IEquipment;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOxygenTank extends Item implements IEquipment, IFluidContainerItem {

    protected int capacity;

    public ItemOxygenTank() {
        super();
        this.capacity = FluidContainerRegistry.BUCKET_VOLUME * 32;//capacity;
        this.setMaxStackSize(1);
    }

    @Override
    public boolean canTakeStack(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
        return equipment.ordinal() == EquipmentType.Bag.ordinal();
    }

    @Override
    public boolean isItemValid(EquipmentType equipment, ItemStack stack) {
        return equipment.ordinal() == EquipmentType.Bag.ordinal();
    }

    @Override
    public void onUpdate(EquipmentType equipment, ItemStack stack, World world, Entity player, int slot) {

        if (this.getFluid(stack) == null) return;
        if (this.getFluid(stack).amount < 50) return;
        if (!player.isInsideOfMaterial(Material.water)) return;

        if (player.worldObj.getWorldTime() % 50 == 0) {
            player.setAir(300);
            this.drain(stack, 50, true);
        }

    }

    @Override
    public boolean canDrop(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer par3EntityPlayer, List list, boolean par4) {

        String f = "None";
        int amount = 0;

        if (this.getFluid(itemStack) != null) {
            f = this.getFluid(itemStack).getFluid().getLocalizedName(this.getFluid(itemStack));
            amount = this.getFluid(itemStack).amount;
        }

        NumberFormat nf = NumberFormat.getNumberInstance();

        list.add("Fluid  : " + f);
        list.add("Amount : " + nf.format(amount) + " / " + nf.format(this.capacity) + " mB");

    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {

        //if (this.getFluidAmount(stack) == 0) return false;

        return true;//stack.isItemDamaged();
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0D - (double) this.getFluidAmount(stack) / (double) this.capacity;
    }

    /* IFluidContainerItem */
    @Override
    public FluidStack getFluid(ItemStack container) {
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid")) {
            return null;
        }
        return FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
    }

    @Override
    public int getCapacity(ItemStack container) {
        return capacity;
    }

    @Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill) {
        if (resource == null) {
            return 0;
        }

        if (resource.getFluid().getID() != SSFluids.oxygen.getID()) return 0;

        if (!doFill) {
            if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid")) {
                return Math.min(capacity, resource.amount);
            }

            FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));

            if (stack == null) {
                return Math.min(capacity, resource.amount);
            }

            if (!stack.isFluidEqual(resource)) {
                return 0;
            }

            return Math.min(capacity - stack.amount, resource.amount);
        }

        if (container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }

        if (!container.stackTagCompound.hasKey("Fluid")) {
            NBTTagCompound fluidTag = resource.writeToNBT(new NBTTagCompound());

            if (capacity < resource.amount) {
                fluidTag.setInteger("Amount", capacity);
                container.stackTagCompound.setTag("Fluid", fluidTag);
                return capacity;
            }

            container.stackTagCompound.setTag("Fluid", fluidTag);
            return resource.amount;
        }

        NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
        FluidStack stack = FluidStack.loadFluidStackFromNBT(fluidTag);

        if (!stack.isFluidEqual(resource)) {
            return 0;
        }

        int filled = capacity - stack.amount;
        if (resource.amount < filled) {
            stack.amount += resource.amount;
            filled = resource.amount;
        } else {
            stack.amount = capacity;
        }

        container.stackTagCompound.setTag("Fluid", stack.writeToNBT(fluidTag));
        return filled;
    }

    @Override
    public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid")) {
            return null;
        }

        FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
        if (stack == null) {
            return null;
        }

        int currentAmount = stack.amount;
        stack.amount = Math.min(stack.amount, maxDrain);
        if (doDrain) {
            if (currentAmount == stack.amount) {
                container.stackTagCompound.removeTag("Fluid");

                if (container.stackTagCompound.hasNoTags()) {
                    container.stackTagCompound = null;
                }
                return stack;
            }

            NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
            fluidTag.setInteger("Amount", currentAmount - stack.amount);
            container.stackTagCompound.setTag("Fluid", fluidTag);
        }
        return stack;
    }

    public int getFluidAmount(ItemStack container) {

        if (this.getFluid(container) == null) {
            return 0;
        }
        return this.getFluid(container).amount;

    }

}
