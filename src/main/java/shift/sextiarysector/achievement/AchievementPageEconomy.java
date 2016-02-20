package shift.sextiarysector.achievement;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.util.IIcon;

public class AchievementPageEconomy extends AchievementPageBase implements IAchievementIcon {

    public AchievementPageEconomy(String name, ArrayList<Achievement> achievements) {
        super(name, achievements);
    }

    @Override
    public IIcon getIcon(Random random, int k3, int j2, int i3) {

        IIcon iicon = Blocks.sand.getIcon(0, 0);

        if (k3 <= 37 && j2 + i3 != 35) {
            if (k3 == 22) {
                if (random.nextInt(2) == 0) {
                    iicon = Blocks.gold_ore.getIcon(0, 0);
                } else {
                    iicon = Blocks.gold_ore.getIcon(0, 0);
                }
            } else if (k3 == 10) {
                iicon = Blocks.gold_ore.getIcon(0, 0);
            } else if (k3 == 8) {
                iicon = Blocks.gold_ore.getIcon(0, 0);
            } else if (k3 > 4) {
                iicon = Blocks.stone.getIcon(0, 0);
            } else if (k3 > 0) {
                iicon = Blocks.dirt.getIcon(0, 0);
            }
        } else {
            iicon = Blocks.bedrock.getIcon(0, 0);
        }

        return iicon;
    }

    @Override
    public float getBrightness(int j2, int i3) {
        return 0.6F - (float) (j2 + i3) / 25.0F * 0.3F;
    }
}
