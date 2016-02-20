package shift.sextiarysector.achievement;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.block.BlockSSCrop;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AchievementCrop extends AchievementPickup {

    private BlockSSCrop crop;
    private String n = "\n";//System.getProperty("line.separator");

    public AchievementCrop(String p_i45302_1_, int p_i45302_3_, int p_i45302_4_, ItemStack p_i45302_5_, Block block, Achievement p_i45302_6_, ArrayList<Achievement> a) {
        super(p_i45302_1_, p_i45302_3_, p_i45302_4_, p_i45302_5_, p_i45302_6_, a);
        this.crop = (BlockSSCrop) block;
    }

    EnumChatFormatting[] InformationC = new EnumChatFormatting[] { EnumChatFormatting.LIGHT_PURPLE, EnumChatFormatting.GREEN, EnumChatFormatting.YELLOW, EnumChatFormatting.BLUE, EnumChatFormatting.GRAY };

    @SideOnly(Side.CLIENT)
    public String getDescription() {

        if (((EntityClientPlayerMP) SextiarySector.proxy.getClientPlayer()).getStatFileWriter().hasAchievementUnlocked(this)) {

            StringBuffer str = new StringBuffer();

            str.append(StatCollector.translateToLocal("achievement.ss.data"));
            str.append(n);
            str.append(StatCollector.translateToLocal("tooltip.season.seed"));
            str.append(" : ");

            Season[] season = ((BlockSSCrop) this.crop).getStatus().getSeason();

            if (season.length == 1) {

                str.append(InformationC[season[0].ordinal()]);
                str.append(season[0].getTranslatedName());

            } else if (season.length >= 2) {

                str.append(InformationC[season[0].ordinal()]);
                str.append(season[0].getTranslatedName());

                str.append(InformationC[4]);
                str.append(" - ");

                str.append(InformationC[season[season.length - 1].ordinal()]);
                str.append(season[season.length - 1].getTranslatedName());

            }

            str.append(InformationC[4]);

            str.append(n);

            str.append(StatCollector.translateToLocal("tooltip.season.day"));
            str.append(" : ");
            str.append(this.crop.getStatus().getDays()[2]);

            return str.toString();
        } else {
            return super.getDescription();
        }

    }

}
