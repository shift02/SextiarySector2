package shift.sextiarysector.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.agriculture.CropBase;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.season.Season;

public class ItemSeed extends Item {

    public Map<String, IIcon> seedIcons = new HashMap<String, IIcon>();
    public Map<String, CropBase> seeds = new HashMap<String, CropBase>();

    EnumChatFormatting[] InformationC = new EnumChatFormatting[] { EnumChatFormatting.LIGHT_PURPLE, EnumChatFormatting.GREEN, EnumChatFormatting.YELLOW, EnumChatFormatting.BLUE, EnumChatFormatting.GRAY };

    public ItemSeed() {
        this.setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
    }

    public void addSeed(String name, CropBase crop) {
        this.seeds.put(name, crop);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {

        for (String name : seeds.keySet()) {

            ItemStack item = new ItemStack(p_150895_1_);
            setSeedName(item, name);
            p_150895_3_.add(item);

        }
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

        Season[] season = seeds.get(this.getSeedName(itemstack)).getSeason();

        String s = StatCollector.translateToLocal("tooltip.season.seed");
        String s1 = season[0].getTranslatedName();

        if (season.length == 1) {
            par3List.add(s + " : " + InformationC[season[0].ordinal()] + s1);
        } else if (season.length >= 2) {

            String s2 = season[season.length - 1].getTranslatedName();

            par3List.add(s + " : " + InformationC[season[0].ordinal()] + s1 + InformationC[4] + " - " + InformationC[season[season.length - 1].ordinal()] + s2);
        }

    }

    @Override
    public String getUnlocalizedName(ItemStack p_77667_1_) {
        return "tile." + "ss." + this.getSeedName(p_77667_1_);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_) {

        for (String name : seeds.keySet()) {
            seedIcons.put(name, p_94581_1_.registerIcon("sextiarysector:seed/" + name + "_seed"));
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack item) {
        return seedIcons.get(this.getSeedName(item));
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return this.getIconIndex(stack);
    }

    /**
     * 作物の名前から種を取得
     * @param name 名前
     * @return 種
     */
    public static ItemStack getSeedItemStack(String name, int amount) {

        ItemStack item = new ItemStack(SSItems.seeds, amount);
        setSeedName(item, name);

        return item;

    }

    public static void setSeedName(ItemStack p_77667_1_, String name) {

        NBTTagCompound nbt = p_77667_1_.getTagCompound();

        if (nbt == null) {
            nbt = new NBTTagCompound();
            p_77667_1_.setTagCompound(nbt);
        }

        nbt.setString("seed", name);

    }

    public static String getSeedName(ItemStack p_77667_1_) {

        NBTTagCompound nbt = p_77667_1_.getTagCompound();

        if (nbt == null) {
            nbt = new NBTTagCompound();
            p_77667_1_.setTagCompound(nbt);
        } else {
            return nbt.getString("seed");
        }

        return "none";

    }

}
