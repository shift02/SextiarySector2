package shift.sextiarysector.achievement;

import java.util.Random;

import net.minecraft.util.IIcon;

public interface IAchievementIcon {

    IIcon getIcon(Random random, int k3, int j2, int i3);

    float getBrightness(int j2, int i3);

}
