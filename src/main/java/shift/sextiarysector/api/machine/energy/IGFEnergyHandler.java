package shift.sextiarysector.api.machine.energy;

import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.EnergyStorage;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;

/**
 *
 * パッケージが変更になりました {@link IGearForceHandler}<br>
 * 注 AMT2とエンチャント交換MOD用に残してあります<br>
 * <br>
 * <s>
 * IGFEnergyHandler TileEntity用のエネルギーインターフェース <br>
 * TileEntityに実装して使ってください。
 * </s>
 * @see IEnergyStorage
 * @see EnergyStorage
 * @version 1.1.0
 * @author Shift02
 * @Deprecated パッケージが変更になりました {@link IGearForceHandler}
 */
@Deprecated
public interface IGFEnergyHandler {

	/**
	 * addEnergy エレルギーの追加
	 * @param from エネルギーを加える方角
	 * @param power 加える力の量
	 * @param speed 加える速度の量
	 * @param simulate シミュレーションかどうか
	 * @return 実際に加えれた速度
	 */
	int addEnergy(ForgeDirection from, int power, int speed, boolean simulate);

	/**
	 * drawEnergy エレルギーの減少
	 * @param from エネルギーを減らす方角
	 * @param power 減らす力の量
	 * @param speed 減らす速度の量
	 * @param simulate シミュレーションかどうか
	 * @return 実際に減らせれた速度
	 */
	int drawEnergy(ForgeDirection from, int power, int speed, boolean simulate);

	/**
	 * canInterface 繋がるか
	 * @param from 方角
	 * @return その方角に動力を入力または出力出来る場合はtrue
	 */
	boolean canInterface(ForgeDirection from);

	/**
	 * getPowerStored 力の取得
	 * @param from 方角
	 * @return その方角の現在の力の量
	 */
	int getPowerStored(ForgeDirection from);

	/**
	 * getSpeedStored 速度の取得
	 * @param from 方角
	 * @return その方角の現在の速度の量
	 */
	int getSpeedStored(ForgeDirection from);

	/**
	 * getMaxPowerStored 力の最大値(容量)の取得
	 * @param from 方角
	 * @return 最大値(容量)
	 */
	int getMaxPowerStored(ForgeDirection from);

	/**
	 * getMaxSpeedStored 速度の最大値(容量)の取得
	 * @param from 方角
	 * @return 最大値(容量)
	 */
	int getMaxSpeedStored(ForgeDirection from);

}
