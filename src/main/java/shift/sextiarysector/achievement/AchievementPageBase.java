package shift.sextiarysector.achievement;

import java.util.ArrayList;

import net.minecraft.stats.Achievement;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.AchievementPage;

public class AchievementPageBase extends AchievementPage{

	public AchievementPageBase(String name, ArrayList<Achievement> achievements) {
		super(name, achievements.toArray(new Achievement[0]));
	}

	@Override
	public String getName()
    {
        return StatCollector.translateToLocal(super.getName());
    }

}
