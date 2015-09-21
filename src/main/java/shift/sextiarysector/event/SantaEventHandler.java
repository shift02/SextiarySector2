package shift.sextiarysector.event;

import java.util.Calendar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * クリスマス用のEvent <br>
 * FMLPreInitializationEvent内でMinecraftForge.EVENT_BUSに登録すると <br>
 * サンタさんが枕元にプレゼントを置きに来ます
 *
 * @version 1.0.0
 * @see FMLPreInitializationEvent
 * @author Shift02
 */
public class SantaEventHandler {

    /** クリスマスの月 */
    private static final int CHRISTMAS_MONTH = 12;

    /** クリスマスの日 */
    private static final int CHRISTMAS_DATE = 25;

    /**
     * プレイヤーが寝ているかの監視Event
     * @param event
     */
    @SubscribeEvent
    public void LivingSleepingEvent(LivingUpdateEvent event) {

        //Clientはキャンセル
        if (event.entityLiving.worldObj.isRemote) {
            return;
        }

        //クリスマス以外はキャンセル
        if (!this.isChristmas()) {
            return;
        }

        //プレイヤー以外はキャンセル
        //村人にあげるプレゼントはありません┃━┏┃
        if (!(event.entityLiving instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entityLiving;

        //すでに受け取っている場合はキャンセル
        if (this.hasPresent(player)) {
            return;
        }

        if (!event.entityLiving.worldObj.isRemote) {

            if (player.isPlayerFullyAsleep()) {

                int x = (int) player.posX;
                int y = (int) player.posY;
                int z = (int) player.posZ;
                World world = player.worldObj;

                int range = 2;
                for (int i = -range; i < range; i++) {
                    for (int j = -range; j < range; j++) {
                        for (int k = -range; k < range; k++) {
                            if (world.isAirBlock(x + i, y + k, z + j)) {
                                if (generateChest(world, x + i, y + k, z + j)) {
                                    this.receivePresent(player);
                                    return;
                                }
                            }
                        }
                    }
                }

            }

        }

    }

    /**
     * プレゼント用チェストの生成
     * @param world ワールド
     * @param x x座標
     * @param y y座標
     * @param z z座標
     * @return 生成できたらtrue
     */
    protected boolean generateChest(World world, int x, int y, int z) {
        boolean isGen = world.setBlock(x, y, z, Blocks.chest);

        if (isGen) {
            TileEntityChest tileEntityChest = (TileEntityChest) world.getTileEntity(x, y, z);
            if (tileEntityChest != null) {

                for (int i = 0; i < 27; i++) {
                    tileEntityChest.setInventorySlotContents(i, new ItemStack(Blocks.gravel, 64));
                }

                return true;

            }
        }

        return false;
    }

    /**
     * 今日がクリスマスかどうか調べる
     * @return クリスマスならtrue
     */
    private boolean isChristmas() {

        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.DATE) == CHRISTMAS_DATE && calendar.get(calendar.MONTH) == CHRISTMAS_MONTH - 1;

    }

    /**
     * サンタさんからプレゼントを受け取ったかどうか <br>
     * 一人一個ですよ！
     * @param player 調べるプレイヤー
     * @return 受けとっている場合はtrue
     */
    private boolean hasPresent(EntityPlayer player) {
        return player.getEntityData().hasKey("santa:" + Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * プレゼントを渡す時に呼ぶメソッド
     * @param player 渡すプレイヤー
     */
    private void receivePresent(EntityPlayer player) {
        player.getEntityData().setBoolean("santa:" + Calendar.getInstance().get(Calendar.YEAR), true);
    }

}
