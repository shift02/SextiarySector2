package shift.sextiarysector.item;

import java.util.Set;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemKnife extends ItemTool {

    private static final Set field_150916_c = Sets.newHashSet(new Block[] { Blocks.crafting_table });

    private boolean repair = false;
    private boolean furnace = false;

    public ItemKnife(ToolMaterial p_i45343_1_) {
        super(1.0F, p_i45343_1_, field_150916_c);
        int i = p_i45343_1_.getHarvestLevel();
        float d = p_i45343_1_.getMaxUses();
        this.setHarvestLevel("knife", i);
        if (i == 0) {
            this.setHarvestLevel("axe", 0);
        } else {
            this.setHarvestLevel("axe", i - 1);
        }
        this.setMaxDamage((int) (d / 2.0f));
        this.setCreativeTab(SextiarySectorAPI.TabSSCooking);
        FMLCommonHandler.instance().bus().register(this);

    }

    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {

        return false;
        //return BlockKnife.block.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, side, p_77648_8_, p_77648_9_, p_77648_10_);

    }

    //アイテムがクラフト後に戻らないようにする
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
        return false;
    }

    //修理以外ならクラフト後にgetContainerItemStackを呼び出す
    @Override
    public boolean hasContainerItem() {
        return !repair && !furnace;
    }

    //修理かどうかを判定する
    @SubscribeEvent
    public void onCrafting(ItemCraftedEvent event) {
        //IDが無くなったので、アイテムインスタンスで比較。
        repair = this == event.crafting.getItem();
        furnace = false;
    }

    //クラフト後のアイテムを、ダメージを与えて返す
    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {

        if (!hasContainerItem(itemStack)) {
            return null;
        }

        if (itemStack != null && itemStack.getItem() == this) {
            itemStack.setItemDamage(itemStack.getItemDamage() + 1);
        }
        return itemStack;
    }

    //カマド用
    @Override
    public String getToolMaterialName() {

        if (this.toolMaterial.toString().equals("WOOD")) {
            furnace = true;
        }

        return this.toolMaterial.toString();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    public Item setTextureName(String p_111206_1_) {
        this.iconString = "sextiarysector:tool/" + p_111206_1_;
        return this;
    }

}
