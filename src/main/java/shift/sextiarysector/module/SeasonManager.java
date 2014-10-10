package shift.sextiarysector.module;

import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import shift.sextiarysector.api.season.ISeason;
import shift.sextiarysector.api.season.Season;

public class SeasonManager implements ISeason {

	private static SeasonManager instance = new SeasonManager();

	private SeasonManager() {
	}

	public static SeasonManager getInstance() {
		return instance;
	}

	public static Minecraft mc()
	{
		return Minecraft.getMinecraft();
	}

	public static long getTime()
	{
		return mc().theWorld.getWorldInfo().getWorldTime();
	}

	@Override
	public long getTotalTime(World world)
	{
		return world.getWorldInfo().getWorldTime();
	}

	@Override
	public long getTime(World world)
	{
		return world.getWorldInfo().getWorldTime() % 24000;
	}

	public String getTime2(World world)
	{
		long t = world.getWorldInfo().getWorldTime() % 24000;
		t+=6000;
		if(t>=24000)t-=24000;
		if(t>=12000){
			t-=12000;
		}

		int h,m,s;
		h = (int) (t/1000);
		m = (int) ((t%1000)/(1000f/60f));
		s = (int) (((t%1000)/(1000f/3600f)))%60;

		return "["+getAMorPM(world)+"] "+String.format("%1$2d", h)+":"+String.format("%1$02d", m);//+":"+String.format("%1$02d", s);

	}

	@Override
	public int getYear(World world)
	{

		long day = (getTotalTime(world) / 24000L) + 1;

		return (int) (day / 120) + 1;

	}

	@Override
	public int getDay(World world)
	{
		long day = (getTotalTime(world) / 24000L) + 1;
		int day22 = (int) (day / 30);
		int day2 = (int) (day - (day22 * 30));

		if (day2 == 0) {
			day2 = 30;
		}
		return day2;
	}

	@Override
	public Season getSeason(World world)
	{

		long day = (getTotalTime(world) / 24000L) + 1;
		int s = (int) ((day - 1) / 30) % 4;

		return Season.SEASON[s];
	}

	@Override
	public String getAMorPM(World world) {
		long t = world.getWorldInfo().getWorldTime() % 24000;
		t+=6000;
		if(t>=24000)t-=24000;
		if(t>=12000){
			return StatCollector.translateToLocal("tooltip.season.pm");
		}
		return StatCollector.translateToLocal("tooltip.season.am");
	}

	@Override
	public int getHour(World world, int i) {

		long t = world.getWorldInfo().getWorldTime() % 24000;
		t+=6000;
		if(t>=24000)t-=24000;
		if(t>=12000&&i==1){
			t-=12000;
		}

		return (int) (t/1000);

	}

	@Override
	public int getMinute(World world) {

		long t = world.getWorldInfo().getWorldTime() % 24000;
		t+=6000;
		if(t>=24000)t-=24000;
		if(t>=12000){
			t-=12000;
		}

		return (int) ((t%1000)/(1000f/60f));
	}

	@Override
	public int getSecond(World world) {

		long t = world.getWorldInfo().getWorldTime() % 24000;
		t+=6000;
		if(t>=24000)t-=24000;
		if(t>=12000){
			t-=12000;
		}

		return (int) (((t%1000)/(1000f/3600f)))%60;
	}

}
