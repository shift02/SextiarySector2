package shift.sextiarysector.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import shift.sextiarysector.api.season.SeasonAPI;

public class TextureSeason extends TextureAtlasSprite {

    private double field_94239_h;
    private double field_94240_i;

    public TextureSeason(String p_i1282_1_) {
        super(p_i1282_1_);
        // TODO 自動生成されたコンストラクター・スタブ
    }

    public void updateAnimation() {
        if (!this.framesTextureData.isEmpty()) {
            Minecraft minecraft = Minecraft.getMinecraft();
            double d0 = 0.0D;

            if (minecraft.theWorld != null && minecraft.thePlayer != null) {
                //float f = minecraft.theWorld.getCelestialAngle(1.0F);
                float f = SeasonAPI.getSeason(minecraft.theWorld).ordinal() / 4.0f + 0.1f;
                d0 = (double) f;

                if (!minecraft.theWorld.provider.isSurfaceWorld()) {
                    d0 = Math.random();
                }
            }

            double d1;

            for (d1 = d0 - this.field_94239_h; d1 < -0.5D; ++d1) {
                ;
            }

            while (d1 >= 0.5D) {
                --d1;
            }

            if (d1 < -1.0D) {
                d1 = -1.0D;
            }

            if (d1 > 1.0D) {
                d1 = 1.0D;
            }

            this.field_94240_i += d1 * 0.1D;
            this.field_94240_i *= 0.8D;
            this.field_94239_h += this.field_94240_i;
            int i;

            for (i = (int) ((this.field_94239_h + 1.0D) * (double) this.framesTextureData.size()) % this.framesTextureData.size(); i < 0; i = (i + this.framesTextureData.size()) % this.framesTextureData.size()) {
                ;
            }

            if (i != this.frameCounter) {
                this.frameCounter = i;
                TextureUtil.uploadTextureMipmap((int[][]) this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
            }
        }
    }

}
