package shift.sextiarysector.achievement;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.util.IIcon;

public class AchievementPageIndustry extends AchievementPageBase implements IAchievementIcon {

    public AchievementPageIndustry(String name, ArrayList<Achievement> achievements) {
        super(name, achievements);
    }

    @Override
    public IIcon getIcon(Random random, int k3, int j2, int i3) {

        return Blocks.iron_block.getIcon(0, 0);

    }

    @Override
    public float getBrightness(int j2, int i3) {
        return 0.6f;
    }
}
