package shift.sextiarysector.api;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;

/**
 * SextiarySectorAPI SextiarySectorの要素にアクセスするためのClass <br>
 * 基本的には、このクラスからアクセスしてください。
 *
 * @author Shift02
 */
public class SextiarySectorAPI {

	/** ModID */
	public static final String MODID = "SextiarySector";

	//CreativeTabs
	public static CreativeTabs TabSSCore;
	public static CreativeTabs TabSSFluid;
	public static CreativeTabs TabSSMachine;
	public static CreativeTabs TabSSPlayer;
	public static CreativeTabs TabSSAgriculture;
	public static CreativeTabs TabSSMining;
	public static CreativeTabs TabSSCooking;
	public static CreativeTabs TabSSEconomy;
	public static CreativeTabs TabSSMagic;

	/**
	 * IPlayerManager Player関係のインターフェース <br>
	 * このインスタンスから操作をしてください。
	 */
	public static IPlayerManager playerManager ;




	/*
	 * -----------------------
	 * Player プレイヤー
	 *------------------------
	 */

	//メモ  MAX水分 20 , MAXスタミナ 1000

	/**
	 * addMoistureStats プレイヤーの水分ゲージを回復します
	 * @param entityPlayer プレイヤー
	 * @param par1 水分
	 * @param par2 隠し水分
	 */
	public static void addMoistureStats(EntityPlayer entityPlayer, int par1, float par2) {
		playerManager.addMoistureStats(entityPlayer, par1, par2);
	}

	/**
	 * addMoistureExhaustion プレイヤーの水分ゲージを減らします
	 * @param entityPlayer プレイヤー
	 * @param amount 減らす量(4.0fで1)
	 */
	public static void addMoistureExhaustion(EntityPlayer entityPlayer, float amount){
		playerManager.addMoistureExhaustion(entityPlayer, amount);
	}

	/**
	 * addMoistureStats プレイヤーのスタミナゲージを回復します
	 * @param entityPlayer プレイヤー
	 * @param par1 スタミナ
	 * @param par2 隠しスタミナ
	 */
	public static void addStaminaStats(EntityPlayer entityPlayer, int par1, float par2){
		playerManager.addStaminaStats(entityPlayer, par1, par2);
	}

	/**
	 * addMoistureExhaustion プレイヤーのスタミナゲージを減らします
	 * @param entityPlayer プレイヤー
	 * @param amount 減らす量(4.0fで1)
	 */
	public static void addStaminaExhaustion(EntityPlayer entityPlayer, float amount){
		playerManager.addStaminaExhaustion(entityPlayer, amount);
	}


}
