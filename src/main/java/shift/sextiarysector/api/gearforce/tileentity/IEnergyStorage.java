package shift.sextiarysector.api.gearforce.tileentity;

/**
 * IEnergyStorage エネルギー用のインターフェース <br>
 * TileEntityに実装はしないでください。
 * @see EnergyStorage
 * @version 1.0.0
 * @author Shift02
 */
public interface IEnergyStorage {

	/**
	 * addEnergy エレルギーの追加
	 * @param maxPower 加える力の量
	 * @param maxSpeed 加える速度の量
	 * @param simulate シミュレーションかどうか
	 * @return 実際に加えれた速度
	 */
	int addEnergy(int maxPower ,int maxSpeed, boolean simulate);


	/**
	 * drawEnergy エレルギーの減少
	 * @param maxPower 減らす力の量
	 * @param maxSpeed 減らす速度の量
	 * @param simulate シミュレーションかどうか
	 * @return 実際に減らせれた速度
	 */
	int drawEnergy(int maxPower ,int maxSpeed, boolean simulate);

	/**
	 * @return  変速可能かどうか
	 */
	boolean isTransmissionGear();

	/**
	 * @return 現在の力
	 */
	int getPowerStored();

	/**
	 * @return 現在の速度
	 */
	int getSpeedStored();

	/**
	 * @return 最大の力の値
	 */
	int getMaxPower();

	/**
	 * @return 最大の速度の値
	 */
	int getMaxSpeed();

}
