package shift.sextiarysector.event;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import shift.sextiarysector.SSAchievement;
import shift.sextiarysector.SSBlocks;
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

    @SubscribeEvent
    public void useHoeEvent(UseHoeEvent event) {

        int x = event.x;
        int y = event.y;
        int z = event.z;
        World w = event.world;

        Block block = w.getBlock(x, y, z);

        if (w.getBlock(x, y + 1, z).isAir(w, x, y + 1, z) && (block == Blocks.grass || block == Blocks.dirt)) {
            Block block1 = SSBlocks.farmland;
            w.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

            if (!w.isRemote) event.entityPlayer.addStat(SSAchievement.farmland, 1);
            if (w.isRemote) {
                event.setResult(Result.ALLOW);
            } else {
                w.setBlock(x, y, z, block1);
                event.setResult(Result.ALLOW);
            }
        } else {
            event.setCanceled(true);
        }

    }

}
