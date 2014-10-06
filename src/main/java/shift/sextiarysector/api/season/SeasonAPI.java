package shift.sextiarysector.api.season;

import net.minecraft.world.World;

/**
 * 時間関係のAPI
 * @version 1.0.0
 * @author Shift02
 */
public class SeasonAPI {

	/*
	 * Season シーズン(季節)
	 */
	public static ISeason SeasonManager;

	/**
	 * getTotalTime 日付を跨いでも時間がリセットされないトータルな時間
	 * @param world ワールド
	 * @return 時間
	 */
	public static long getTotalTime(World world){
		return SeasonManager.getTotalTime(world);
	}

	/**
	 * getTime 現在の時刻
	 * @param world ワールド
	 * @return 時間
	 */
	public static long getTime(World world){
		return SeasonManager.getTime(world);
	}

	/**
	 * getHour 現在の時
	 * @param world ワールド
	 * @param i 0== 24時間制 , 1== 12時間制
	 * @return 時
	 */
	public static int getHour(World world ,int i){
		return SeasonManager.getHour(world, i);
	}

	/**
	 * getTime 現在の分
	 * @param world ワールド
	 * @return 分
	 */
	public static int getMinute(World world){
		return SeasonManager.getMinute(world);
	}

	/**
	 * getTime 現在の秒
	 * @param world ワールド
	 * @return 秒
	 */
	public static int getSecond(World world){
		return SeasonManager.getSecond(world);
	}

	/**
	 * getTime 午前か午後
	 * @param world ワールド
	 * @return 午前か午後
	 */
	public static String getAMorPM(World world){
		return SeasonManager.getAMorPM(world);
	}

	/**
	 * getDay 現在の日付の取得 <br>
	 * 一月は30日です。
	 * @param world ワールド
	 * @return 日
	 */
	public static int getDay(World world) {
		return SeasonManager.getDay(world);
	}

	/**
	 * getYear 現在の年の取得 <br>
	 * 一年は春→夏→秋→冬です。
	 * @param world ワールド
	 * @return 年
	 */
	public static int getYear(World world) {
		return SeasonManager.getYear(world);
	}

	/**
	 * getSeason 現在の季節の取得
	 * @param world ワールド
	 * @return 季節
	 */
	public static Season getSeason(World world) {
		return SeasonManager.getSeason(world);
	}

}
