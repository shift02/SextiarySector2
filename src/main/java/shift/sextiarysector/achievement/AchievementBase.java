package shift.sextiarysector.achievement;

import java.util.ArrayList;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.StatCollector;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AchievementBase extends Achievement{

	private final String achievementDescription2;

	public AchievementBase(String p_i45302_1_,int p_i45302_3_, int p_i45302_4_, ItemStack p_i45302_5_, Achievement p_i45302_6_, ArrayList<Achievement>a) {
		super("achievement.ss"+p_i45302_1_, "ss." + p_i45302_1_, p_i45302_3_, p_i45302_4_, p_i45302_5_,p_i45302_6_);
		this.achievementDescription2 = "achievement.ss." + p_i45302_1_ + ".desc2";
		a.add(this);
	}

	@SideOnly(Side.CLIENT)
    public String getDescription()
    {

		if(((EntityClientPlayerMP) SextiarySector.proxy.getClientPlayer()).getStatFileWriter().hasAchievementUnlocked(this)){
			return StatCollector.translateToLocal(this.achievementDescription2);
		}else{
			return super.getDescription();
		}

    }

}
