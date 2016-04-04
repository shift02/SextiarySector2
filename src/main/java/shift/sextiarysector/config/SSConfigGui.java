package shift.sextiarysector.config;

import java.util.List;

import com.google.common.collect.Lists;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import shift.sextiarysector.SSConfig;
import shift.sextiarysector.SextiarySector;

public class SSConfigGui extends GuiConfig {

    public SSConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(), SextiarySector.MODID, false, false, I18n.format(SSConfig.SS_LANG + "title"));
    }

    private static List<IConfigElement> getConfigElements() {

        List<IConfigElement> list = Lists.newArrayList();

        for (String name : SSConfig.config.getCategoryNames()) {

            ConfigCategory category = SSConfig.config.getCategory(name);

            if (category.showInGui()) {

                list.add(new ConfigElement(category));
            }
        }

        return list;
    }
}
