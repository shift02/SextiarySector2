package shift.sextiarysector.api.gearforce.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * IGearForceItemを実装したアイテムのGFを実際に操作するClass
 * @version 1.0.0
 * @author Shift02
 *
 */
public class GearForceItemAPI {

	public static IGearForceItemManager manager;

	/**
	 *
	 * アイテムにGFを追加する
	 *
	 * @param itemStack GFを追加するアイテム
	 * @param power パワー
	 * @param speed スピード
	 * @param simulate シミュレーションかどうか trueだと追加はされません(結果は戻ってきます)
	 * @return 追加できたスピード
	 */
	public static int addEnergy(ItemStack itemStack, int power, int speed, boolean simulate) {
		return manager.addEnergy(itemStack, power, speed, simulate);
	}

	/**
	 *
	 * アイテムからGFを減らす
	 *
	 * @param itemStack GFを減らすアイテム
	 * @param power パワー
	 * @param speed スピード
	 * @param simulate シミュレーションかどうか trueだと追加はされません(結果は戻ってきます)
	 * @return 減らせれたスピード
	 */
	public static int reduceEnergy(ItemStack itemStack, int power, int speed, boolean simulate) {
		return manager.reduceEnergy(itemStack, power, speed, simulate);
	}

	/**
	 *
	 * アイテムのパワーの最大値を取得する
	 *
	 * @param itemStack 取得するアイテム
	 * @return パワー
	 */
	public static int getMaxPower(ItemStack itemStack) {
		return ((IGearForceItem) itemStack.getItem()).getMaxPower(itemStack);
	}

	/**
	 *
	 * アイテムのスピードの最大値(容量)を取得する
	 *
	 * @param itemStack 取得するアイテム
	 * @return スピード
	 */
	public static int getMaxSpeed(ItemStack itemStack) {
		return ((IGearForceItem) itemStack.getItem()).getMaxSpeed(itemStack);
	}

	/**
	 *
	 * アイテムの現在のパワーを取得する
	 *
	 * @param itemStack 取得するアイテム
	 * @return パワー
	 */
	public static int getPower(ItemStack itemStack) {
		return manager.getPower(itemStack);
	}

	/**
	 *
	 * アイテムの現在のスピードを取得する
	 *
	 * @param itemStack 取得するアイテム
	 * @return スピード
	 */
	public static int getSpeed(ItemStack itemStack) {
		return manager.getSpeed(itemStack);
	}

	/**
	 *
	 * アイテムにパワーとスピードをセットする
	 *
	 * @param itemStack
	 * @param power
	 * @param speed
	 * @return セット出来ればtrue
	 */
	public static boolean setEnergy(ItemStack itemStack, int power, int speed) {
		return manager.setEnergy(itemStack, power, speed);
	}

	/**
	 *
	 * アイテムがspeedを消費出来るか
	 *
	 * @param itemStack
	 * @param speed
	 * @return speed以上のエネルギーがあればtrue
	 */
	public static boolean canUse(ItemStack itemStack, int speed) {
		return manager.canUse(itemStack, speed);
	}

	/**
	 *
	 * アイテムを使用する時に呼ぶ
	 *
	 * @param itemStack
	 * @param speed
	 * @param entity
	 * @return
	 */
	public static boolean use(ItemStack itemStack, int speed, EntityLivingBase entity) {
		return manager.use(itemStack, speed, entity);
	}

	/**
	 *
	 * itemStackがGFを扱うことが出来るかどうか
	 *
	 * @param itemStack
	 * @return
	 */
	public static boolean isGearForceItem(ItemStack itemStack) {
		return manager.isGearForceItem(itemStack);
	}

}
