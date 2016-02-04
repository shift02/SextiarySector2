package shift.sextiarysector.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;
import shift.sextiarysector.SSCrops;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.agriculture.IFertilizer;

public class AgricultureEventHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void preCropTextureStitchEvent(TextureStitchEvent.Pre event) {

        if (event.map.getTextureType() == 0) {

            for (ICrop crop : SSCrops.cropManager.crops.values()) {
                crop.registerCropIcons(event.map);
            }

        }

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void preFertilizerTextureStitchEvent(TextureStitchEvent.Pre event) {

        if (event.map.getTextureType() == 0) {

            for (IFertilizer fertilizer : SSCrops.fertilizerManager.fertilizers.values()) {
                fertilizer.registerFertilizerIcons(event.map);
            }

        }

    }

}
