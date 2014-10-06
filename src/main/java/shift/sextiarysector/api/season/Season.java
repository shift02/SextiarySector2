package shift.sextiarysector.api.season;

import net.minecraft.util.StatCollector;

public enum Season {

	SPRING("spring"),

	SUMMER("summer"),

	AUTUMN("autumn"),

	WINTER("winter");

	public static final Season[] SEASON = { SPRING, SUMMER, AUTUMN, WINTER };

	public final String name;

	private Season(String name)
	{
		this.name = name;
	}

	public String getTranslatedName() {
		return StatCollector.translateToLocal("tooltip.season." + this.name);
	}

	public static Season getNextSeason(Season season){

		if(season.ordinal()==3){
			return SEASON[0];
		}else{
			return SEASON[season.ordinal()+1];
		}

	}

}
