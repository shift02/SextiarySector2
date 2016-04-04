package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.player.EntityPlayerManager;

public class ItemFoodDrink extends ItemFood {

    private boolean alwaysEdible;
    private boolean isDrink;

    private int healAmount;
    private float saturationModifier;

    private int healMoistureAmount;
    private float moistureSaturationModifier;

    private int healStaminaAmount;
    private float staminaSaturationModifier;

    public ItemFoodDrink(int food, float foodM, int drink, float drinkM, int stamina, float staminaM, boolean p_i45339_3_) {
        super(food, foodM, p_i45339_3_);

        this.healAmount = food;
        this.saturationModifier = foodM;

        this.healMoistureAmount = drink;
        this.moistureSaturationModifier = drinkM;

        this.healStaminaAmount = stamina;
        this.staminaSaturationModifier = staminaM;

        this.setCreativeTab(SextiarySectorAPI.TabSSCooking);

    }

    @Override
    public ItemStack onEaten(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer) {

        --stack.stackSize;

        /*
        StatusFood food = null;
        
        food = PluginPI.Foods.get(par1ItemStack.getItemDamage());
        
        //StatusFood food = foodStatus[par1ItemStack.getItemDamage()];
        
        if(food.isDrink){
        	par3EntityPlayer.triggerAchievement(AchievementSSList.moisture);
        }*/

        par3EntityPlayer.getFoodStats().addStats(this.healAmount, this.saturationModifier);
        EntityPlayerManager.getMoistureStats(par3EntityPlayer).addStats(par3EntityPlayer, this.healMoistureAmount, this.moistureSaturationModifier);
        EntityPlayerManager.getStaminaStats(par3EntityPlayer).addStats(par3EntityPlayer, this.healStaminaAmount, this.staminaSaturationModifier);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(stack, par2World, par3EntityPlayer);

        if (stack.getItem().hasContainerItem(stack)) {
            if (!par3EntityPlayer.capabilities.isCreativeMode) {
                if (stack.stackSize <= 0) {
                    return stack.getItem().getContainerItem(stack);

                } else {

                    if (!par3EntityPlayer.inventory.addItemStackToInventory(stack.getItem().getContainerItem(stack))) {

                        par3EntityPlayer.dropPlayerItemWithRandomChoice(stack.getItem().getContainerItem(stack), false);

                    }

                }

            }
        }

        return stack;

    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        if (this.getItemUseAction(par1ItemStack) == EnumAction.eat) {

            if (par3EntityPlayer.canEat(this.alwaysEdible)) {

                par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            }

        } else {

            if (this.canDrink(par3EntityPlayer, this.alwaysEdible)) {
                par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            }

        }

        return par1ItemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        if (this.isDrink()) {
            return EnumAction.drink;
        }
        return EnumAction.eat;
    }

    public boolean canDrink(EntityPlayer par3EntityPlayer, boolean par1) {
        return (par1 || EntityPlayerManager.getMoistureStats(par3EntityPlayer).needMoisture()) && !par3EntityPlayer.capabilities.disableDamage;
    }

    @Override
    public ItemFoodDrink setAlwaysEdible() {
        this.alwaysEdible = true;
        return this;
    }

    public boolean isDrink() {
        return isDrink;
    }

    public ItemFoodDrink setDrink() {
        this.isDrink = true;
        return this;
    }

}
