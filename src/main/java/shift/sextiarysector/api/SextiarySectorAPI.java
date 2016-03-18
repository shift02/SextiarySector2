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

    /*
     * -------------------------------------------
     * CreativeTabs クリエイティブタブ
     * -------------------------------------------
     */

    public static CreativeTabs TabSSCore;
    public static CreativeTabs TabSSFluid;
    public static CreativeTabs TabSSPlayer;
    public static CreativeTabs TabSSAgriculture;
    public static CreativeTabs TabSSFishery;
    public static CreativeTabs TabSSMining;
    public static CreativeTabs TabSSIndustry;
    public static CreativeTabs TabSSCooking;
    public static CreativeTabs TabSSTransport;
    public static CreativeTabs TabSSEconomy;
    public static CreativeTabs TabSSMagic;

    /*
     * -------------------------------------------
     * Player プレイヤー
     * -------------------------------------------
     */

    public static IPlayerManager playerManager;

    //メモ  MAX水分 20 , MAXスタミナ 100

    /**
     * addMoistureStats プレイヤーの水分ゲージを回復します
     * @param entityPlayer プレイヤー
     * @param par1 水分
     * @param par2 隠し水分
     */
    public static void addMoistureStats(EntityPlayer entityPlayer, int par1, float par2) {
        playerManager.addMoistureStats(entityPlayer, par1, par2);
    }

    public static int getMoistureLevel(EntityPlayer entityPlayer) {
        return playerManager.getMoistureLevel(entityPlayer);
    }

    /**
     * addMoistureExhaustion プレイヤーの水分ゲージを減らします
     * @param entityPlayer プレイヤー
     * @param amount 減らす量(4.0fで1)
     */
    public static void addMoistureExhaustion(EntityPlayer entityPlayer, float amount) {
        playerManager.addMoistureExhaustion(entityPlayer, amount);
    }

    /**
     * addMoistureStats プレイヤーのスタミナゲージを回復します
     * @param entityPlayer プレイヤー
     * @param par1 スタミナ
     * @param par2 隠しスタミナ
     */
    public static void addStaminaStats(EntityPlayer entityPlayer, int par1, float par2) {
        playerManager.addStaminaStats(entityPlayer, par1, par2);
    }

    public static int getStaminaLevel(EntityPlayer entityPlayer) {
        return playerManager.getStaminaLevel(entityPlayer);
    }

    /**
     * addMoistureExhaustion プレイヤーのスタミナゲージを減らします
     * @param entityPlayer プレイヤー
     * @param amount 減らす量(4.0fで1)
     */
    public static void addStaminaExhaustion(EntityPlayer entityPlayer, float amount) {
        playerManager.addStaminaExhaustion(entityPlayer, amount);
    }

    /*
     * -------------------------------------------
     * Figure フィギュア
     * -------------------------------------------
     */
    public static IFigureManager figureManager;

    //エディションの名前
    public static final String FIGURE_BEGINNER = "figure_beginner";
    public static final String ORE_FESTIVAL = "ore_festival";
    public static final String MAGIC_PUMPKIN = "magic_pumpkin";
    public static final String REDSTONE_FACTORY = "redstone_factory";

}
