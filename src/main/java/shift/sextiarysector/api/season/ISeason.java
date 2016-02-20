package shift.sextiarysector.api.season;

import net.minecraft.world.World;

public interface ISeason {

    public long getTotalTime(World world);

    public long getTime(World world);

    public int getDay(World world);

    public int getYear(World world);

    public Season getSeason(World world);

    public int getHour(World world, int i);

    public int getMinute(World world);

    public int getSecond(World world);

    public String getAMorPM(World world);

}
