package shift.sextiarysector.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.block.BlockSSCrop;

public class ItemBlockCrop extends ItemBlock {

    public ItemBlockCrop(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    EnumChatFormatting[] InformationC = new EnumChatFormatting[] { EnumChatFormatting.LIGHT_PURPLE, EnumChatFormatting.GREEN, EnumChatFormatting.YELLOW, EnumChatFormatting.BLUE, EnumChatFormatting.GRAY };

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

        Season[] season = ((BlockSSCrop) this.field_150939_a).getStatus().getSeason();

        String s = StatCollector.translateToLocal("tooltip.season.seed");
        String s1 = season[0].getTranslatedName();

        if (season.length == 1) {
            par3List.add(s + " : " + InformationC[season[0].ordinal()] + s1);
        } else if (season.length >= 2) {

            String s2 = season[season.length - 1].getTranslatedName();

            par3List.add(s + " : " + InformationC[season[0].ordinal()] + s1 + InformationC[4] + " - " + InformationC[season[season.length - 1].ordinal()] + s2);
        }

    }

}
