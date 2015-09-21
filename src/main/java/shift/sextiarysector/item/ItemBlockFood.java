package shift.sextiarysector.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**  blockを食べれるようにするClass ver2(Potion効果付き)
 *
 *   GameRegistry.registerBlock(sampleBlock, ItemBlockFood.class, "SampleBlock");
 *
 *   のように使う
 *  */
public class ItemBlockFood extends ItemBlock {

    private final int healAmount = 4; //回復量
    private final float saturationModifier = 2.5f; // 回復する隠し空腹度

    private final Potion potion = Potion.heal;//Potionの種類
    private final int level = 0;//Potionの強さ
    private final int time = 600;//Potionの時間

    public ItemBlockFood(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
        --p_77654_1_.stackSize;
        p_77654_3_.getFoodStats().addStats(healAmount, saturationModifier);
        p_77654_2_.playSoundAtEntity(p_77654_3_, "random.burp", 0.5F, p_77654_2_.rand.nextFloat() * 0.1F + 0.9F);

        //Potion効果をplayerに付加
        p_77654_3_.addPotionEffect(new PotionEffect(potion.id, time, level));

        return p_77654_1_;
    }

    //時間
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 32;
    }

    //食べ物
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.eat;
    }

    //
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if (p_77659_3_.canEat(false)) {
            p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        }

        return p_77659_1_;
    }

}
