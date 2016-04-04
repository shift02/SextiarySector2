package shift.sextiarysector.event;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import shift.sextiarysector.SSConfig;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.api.gearforce.item.IGearForceGridItem;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceGrid;
import shift.sextiarysector.gui.GuiAchievementsNext;
import shift.sextiarysector.gui.GuiStatsNext;
import shift.sextiarysector.item.TextureSeason;
import shift.sextiarysector.module.SeasonManager;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.EquipmentStats;
import shift.sextiarysector.renderer.entity.ModelDecoration;

public class ClientEventHandler {

    @SideOnly(Side.CLIENT)
    public static Minecraft mc = FMLClientHandler.instance().getClient();

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onConfigChanged(ConfigChangedEvent event) {

        if (event.modID.equals(SextiarySector.MODID)) {

            SSConfig.syncConfig();
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onGuiOpenEvent(GuiOpenEvent event) {

        if (event.gui instanceof GuiStats && !(event.gui instanceof GuiStatsNext)) {

            /*
             * Class<GuiStats> c = GuiStats.class;
             *
             * Field f1; GuiScreen m = null; try { f1 = c.getDeclaredField(
             * "parentGui" ); f1.setAccessible( true ); m = (GuiScreen) f1.get(
             * event.gui ); } catch (NoSuchFieldException e1) { // TODO 自動生成された
             * catch ブロック e1.printStackTrace(); } catch (SecurityException e1) {
             * // TODO 自動生成された catch ブロック e1.printStackTrace(); } catch
             * (IllegalArgumentException e) { // TODO 自動生成された catch ブロック
             * e.printStackTrace(); } catch (IllegalAccessException e) { // TODO
             * 自動生成された catch ブロック e.printStackTrace(); }
             */

            event.gui = new GuiStatsNext(new GuiIngameMenu(), mc.thePlayer.getStatFileWriter());

            // System.out.println("GuiOpenEvent");
        }

        if (event.gui instanceof GuiAchievements && !(event.gui instanceof GuiAchievementsNext)) {

            ((IProgressMeter) event.gui).func_146509_g();
            GuiScreen gui = ObfuscationReflectionHelper.getPrivateValue(GuiAchievements.class,
                    (GuiAchievements) event.gui, "field_146562_a");
            event.gui = new GuiAchievementsNext(gui, mc.thePlayer.getStatFileWriter());

        }

    }

    @SubscribeEvent
    public void onItemTooltipEvent(ItemTooltipEvent event) {

        ItemStack itemStack = event.itemStack;
        List<String> toolTip = event.toolTip;

        if (itemStack.getItem() == Items.clock) {
            toolTip.add(SeasonManager.getInstance().getTime2(this.mc.theWorld));
        }

    }

    public static IIcon slotArrow;

    public static IIcon slotGF;
    public static IIcon slotFluid;

    public static IIcon[] itemGF;

    public static IIcon waterFlow;
    public static IIcon waterStill;

    public static IIcon portal;

    public static IIcon lavaFlow;
    public static IIcon lavaStill;

    public static IIcon breakIcon;

    @SubscribeEvent
    public void PreTextureStitchEvent(TextureStitchEvent.Pre event) {

        // Item
        if (event.map.getTextureType() == 1) {

            slotGF = event.map.registerIcon("sextiarysector:gui/slot_gf");
            slotFluid = event.map.registerIcon("sextiarysector:gui/slot_fluid");

            itemGF = new IIcon[2];
            itemGF[0] = event.map.registerIcon("sextiarysector:damage/damage_0");
            itemGF[1] = event.map.registerIcon("sextiarysector:damage/damage_1");

            slotArrow = event.map.registerIcon("sextiarysector:gui/slot_arrow");

            for (EquipmentType type : EquipmentType.values()) {
                type.registerIcon(event.map);
            }

        } else if (event.map.getTextureType() == 0) {
            waterFlow = event.map.registerIcon("sextiarysector:fluid/water_flow");
            waterStill = event.map.registerIcon("sextiarysector:fluid/water_still");
            portal = event.map.registerIcon("sextiarysector:fluid/portal");
            lavaFlow = event.map.registerIcon("sextiarysector:fluid/lava_flow");
            lavaStill = event.map.registerIcon("sextiarysector:fluid/lava_still");

            breakIcon = event.map.registerIcon("sextiarysector:break");

        }

    }

    @SubscribeEvent
    public void preVanillaTextureStitchEvent(TextureStitchEvent.Pre event) {

        // Item
        if (event.map.getTextureType() == 0) {

            IIcon icon = new TextureSeason("sextiarysector:vanilla/leaves_birch");

            event.map.setTextureEntry("sextiarysector:vanilla/leaves_birch", (TextureAtlasSprite) icon);

            try {

                Field f;

                BlockLeaves birch = Blocks.leaves;
                Class<BlockLeaves> c = BlockLeaves.class;
                f = c.getDeclaredField("field_150129_M");

                f.setAccessible(true);

                IIcon[] a = (IIcon[]) Array.get(f.get(birch), 0);

                Array.set(a, 2, icon);

                // System.out.println("AAAAA");

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }

        }

    }

    // 水の中
    @SubscribeEvent
    public void onFogDensity(FogDensity event) {

        if (event.block.getMaterial() == Material.water) {

            if (event.entity instanceof EntityPlayer) {

                EntityPlayer player = (EntityPlayer) event.entity;

                EquipmentStats e = EntityPlayerManager.getEquipmentStats(player);

                if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0)) != null) {

                    if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0))
                            .getItem() == SSItems.waterContactLenses) {
                        event.density = 0.0F;
                        event.setCanceled(true);
                    }

                }
            }

        }
    }

    // 水の中2
    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {

        if (event.overlayType == RenderBlockOverlayEvent.OverlayType.WATER) {

            if (event.player instanceof EntityPlayer) {

                EntityPlayer player = event.player;

                EquipmentStats e = EntityPlayerManager.getEquipmentStats(player);

                if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0)) != null) {

                    if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0))
                            .getItem() == SSItems.waterContactLenses) {
                        event.setCanceled(true);
                    }

                }
            }

        }

    }

    // 水の中3
    @SubscribeEvent
    public void onFogColors(FogColors event) {

        if (!(event.entity instanceof EntityPlayer))
            return;

        if (event.block.getMaterial() != Material.water)
            return;

        EntityPlayer player = (EntityPlayer) event.entity;

        EquipmentStats e = EntityPlayerManager.getEquipmentStats(player);

        if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0)) != null) {

            if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0)).getItem() == SSItems.waterContactLenses) {

                if (!player.isPotionActive(Potion.nightVision)) {
                    float f11;
                    float f6;

                    f11 = 200;// event.renderer.getNightVisionBrightness(this.mc.thePlayer,
                              // (float) event.renderPartialTicks);
                    f6 = 1.0F / event.red;

                    if (f6 > 1.0F / event.green) {
                        f6 = 1.0F / event.green;
                    }

                    if (f6 > 1.0F / event.blue) {
                        f6 = 1.0F / event.blue;
                    }

                    event.red = event.red * (1.0F - f11) + event.red * f6 * f11;
                    event.green = event.green * (1.0F - f11) + event.green * f6 * f11;
                    event.blue = event.blue * (1.0F - f11) + event.blue * f6 * f11;
                }

            }

        }

    }

    public static boolean hasWater;

    // 水の中4
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {

        EntityPlayer player = SextiarySector.proxy.getClientPlayer();
        if (player == null) return;

        if (!player.isInsideOfMaterial(Material.water)) return;

        EquipmentStats e = EntityPlayerManager.getEquipmentStats(player);

        if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0)) != null) {

            if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0)).getItem() == SSItems.waterContactLenses) {

                if (event.phase == Phase.START) {

                    if (player.isPotionActive(Potion.nightVision)) return;
                    hasWater = true;
                    player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1000));

                } else {

                    if (hasWater) {

                        hasWater = false;
                        player.removePotionEffect(Potion.nightVision.id);

                    }

                }

            }
        }

    }

    // 水の中5
    @SubscribeEvent
    public void onDrawScreenEvent(DrawScreenEvent.Pre event) {

        EntityPlayer player = SextiarySector.proxy.getClientPlayer();
        if (player == null) return;

        if (!player.isInsideOfMaterial(Material.water)) return;

        EquipmentStats e = EntityPlayerManager.getEquipmentStats(player);

        if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0)) != null) {

            if (e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0)).getItem() == SSItems.waterContactLenses) {

                if (hasWater) {
                    hasWater = false;
                    player.removePotionEffect(Potion.nightVision.id);
                }

            }
        }

    }

    public static ModelDecoration decoration;

    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation(
            "textures/misc/enchanted_item_glint.png");

    //@SubscribeEvent
    public void setArmorModel(SetArmorModel event) {

        ModelBase renderPassModel = this.setArmorModel2(event);
        int i = 3 - event.slot;

        int j = event.result;

        if (j > 0) {

            EntityPlayer p_77032_1_ = event.entityPlayer;
            float p_76986_9_ = event.partialRenderTick;

            float f2 = this.interpolateRotation(p_77032_1_.prevRenderYawOffset, p_77032_1_.renderYawOffset, p_76986_9_);
            float f3 = this.interpolateRotation(p_77032_1_.prevRotationYawHead, p_77032_1_.rotationYawHead, p_76986_9_);
            float f4;

            if (p_77032_1_.isRiding() && p_77032_1_.ridingEntity instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase) p_77032_1_.ridingEntity;
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset,
                        p_76986_9_);
                f4 = MathHelper.wrapAngleTo180_float(f3 - f2);

                if (f4 < -85.0F) {
                    f4 = -85.0F;
                }

                if (f4 >= 85.0F) {
                    f4 = 85.0F;
                }

                f2 = f3 - f4;

                if (f4 * f4 > 2500.0F) {
                    f2 += f4 * 0.2F;
                }
            }

            float f13 = p_77032_1_.prevRotationPitch
                    + (p_77032_1_.rotationPitch - p_77032_1_.prevRotationPitch) * p_76986_9_;
            f4 = this.handleRotationFloat(p_77032_1_, p_76986_9_);
            float f5 = 0.0625F;
            float f6 = p_77032_1_.prevLimbSwingAmount
                    + (p_77032_1_.limbSwingAmount - p_77032_1_.prevLimbSwingAmount) * p_76986_9_;
            float f7 = p_77032_1_.limbSwing - p_77032_1_.limbSwingAmount * (1.0F - p_76986_9_);

            if (p_77032_1_.isChild()) {
                f7 *= 3.0F;
            }

            if (f6 > 1.0F) {
                f6 = 1.0F;
            }

            renderPassModel.setLivingAnimations(p_77032_1_, f7, f6, p_76986_9_);
            renderPassModel.render(p_77032_1_, f7, f6, f4, f3 - f2, f13, f5);

            // int j;
            float f8;
            float f9;
            float f10;

            if ((j & 240) == 16) {
                this.func_82408_c(p_77032_1_, i, p_76986_9_);
                renderPassModel.render(p_77032_1_, f7, f6, f4, f3 - f2, f13, f5);
            }

            if ((j & 15) == 15) {
                f8 = p_77032_1_.ticksExisted + p_76986_9_;
                RenderManager.instance.renderEngine.bindTexture(this.RES_ITEM_GLINT);
                GL11.glEnable(GL11.GL_BLEND);
                f9 = 0.5F;
                GL11.glColor4f(f9, f9, f9, 1.0F);
                GL11.glDepthFunc(GL11.GL_EQUAL);
                GL11.glDepthMask(false);

                for (int k = 0; k < 2; ++k) {
                    GL11.glDisable(GL11.GL_LIGHTING);
                    f10 = 0.76F;
                    GL11.glColor4f(0.5F * f10, 0.25F * f10, 0.8F * f10, 1.0F);
                    GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                    GL11.glMatrixMode(GL11.GL_TEXTURE);
                    GL11.glLoadIdentity();
                    float f11 = f8 * (0.001F + k * 0.003F) * 20.0F;
                    float f12 = 0.33333334F;
                    GL11.glScalef(f12, f12, f12);
                    GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, f11, 0.0F);
                    GL11.glMatrixMode(GL11.GL_MODELVIEW);
                    renderPassModel.render(p_77032_1_, f7, f6, f4, f3 - f2, f13, f5);
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glDepthMask(true);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glDepthFunc(GL11.GL_LEQUAL);
            }

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
        }

        if (event.result != -1)
            event.result = -2;

    }

    // shiftの帽子
    public ModelBase setArmorModel2(SetArmorModel event) {

        if (decoration == null)
            decoration = new ModelDecoration();

        EquipmentStats e = EntityPlayerManager.getEquipmentStats(event.entityPlayer);
        int p_77032_2_ = 3 - event.slot;
        float p_76986_9_ = event.partialRenderTick;

        // Item item = SSItems.shiftHat;
        EntityPlayer p_77032_1_ = event.entityPlayer;
        ItemStack itemstack = e.inventory.getStackInSlot(p_77032_2_);
        if (itemstack == null)
            return null;
        Item item = itemstack.getItem();

        if (item instanceof ItemArmor) {
            ItemArmor itemarmor = (ItemArmor) item;
            // event.renderer.bindTexture(RenderBiped.getArmorResource(p_77032_1_,
            // itemstack, p_77032_2_, null));
            ResourceLocation resource = RenderBiped.getArmorResource(p_77032_1_, itemstack, p_77032_2_, null);

            RenderManager.instance.renderEngine.bindTexture(resource);
            ModelBiped modelbiped = p_77032_2_ == 2 ? event.renderer.modelArmor : event.renderer.modelArmorChestplate;
            modelbiped.bipedHead.showModel = p_77032_2_ == 0;
            modelbiped.bipedHeadwear.showModel = p_77032_2_ == 0;
            modelbiped.bipedBody.showModel = p_77032_2_ == 1 || p_77032_2_ == 2;
            modelbiped.bipedRightArm.showModel = p_77032_2_ == 1;
            modelbiped.bipedLeftArm.showModel = p_77032_2_ == 1;
            modelbiped.bipedRightLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
            modelbiped.bipedLeftLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
            modelbiped = net.minecraftforge.client.ForgeHooksClient.getArmorModel(p_77032_1_, itemstack, p_77032_2_,
                    modelbiped);
            event.renderer.setRenderPassModel(modelbiped);
            modelbiped.onGround = event.renderer.modelBipedMain.onGround;
            modelbiped.isRiding = event.renderer.modelBipedMain.isRiding;
            modelbiped.isChild = event.renderer.modelBipedMain.isChild;

            // Move outside if to allow for more then just CLOTH
            int j = itemarmor.getColor(itemstack);
            if (j != -1) {

                float f1 = (j >> 16 & 255) / 255.0F;
                float f2 = (j >> 8 & 255) / 255.0F;
                float f3 = (j & 255) / 255.0F;
                GL11.glColor3f(f1, f2, f3);

                if (itemstack.isItemEnchanted()) {
                    event.result = 31;
                    return modelbiped;
                }

                event.result = 16;
                return modelbiped;

                /*
                 * GL11.glColor3f(1.0F, 1.0F, 1.0F);
                 *
                 * float f2 =
                 * this.interpolateRotation(p_77032_1_.prevRenderYawOffset,
                 * p_77032_1_.renderYawOffset, p_76986_9_); float f3 =
                 * this.interpolateRotation(p_77032_1_.prevRotationYawHead,
                 * p_77032_1_.rotationYawHead, p_76986_9_); float f4;
                 *
                 * if (p_77032_1_.isRiding() && p_77032_1_.ridingEntity
                 * instanceof EntityLivingBase) { EntityLivingBase
                 * entitylivingbase1 = (EntityLivingBase)
                 * p_77032_1_.ridingEntity; f2 =
                 * this.interpolateRotation(entitylivingbase1.
                 * prevRenderYawOffset, entitylivingbase1.renderYawOffset,
                 * p_76986_9_); f4 = MathHelper.wrapAngleTo180_float(f3 - f2);
                 *
                 * if (f4 < -85.0F) { f4 = -85.0F; }
                 *
                 * if (f4 >= 85.0F) { f4 = 85.0F; }
                 *
                 * f2 = f3 - f4;
                 *
                 * if (f4 * f4 > 2500.0F) { f2 += f4 * 0.2F; } }
                 *
                 * float f13 = p_77032_1_.prevRotationPitch +
                 * (p_77032_1_.rotationPitch - p_77032_1_.prevRotationPitch) *
                 * p_76986_9_; f4 = this.handleRotationFloat(p_77032_1_,
                 * p_76986_9_); //this.rotateCorpse((AbstractClientPlayer)
                 * p_77032_1_, f4, f2, p_76986_9_); float f5 = 0.0625F;
                 * //GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                 * //GL11.glScalef(-1.0F, -1.0F, 1.0F);
                 * //this.preRenderCallback(p_77032_1_, p_76986_9_);
                 * //GL11.glTranslatef(0.0F, -24.0F * f5 - 0.0078125F, 0.0F);
                 * float f6 = p_77032_1_.prevLimbSwingAmount +
                 * (p_77032_1_.limbSwingAmount - p_77032_1_.prevLimbSwingAmount)
                 * * p_76986_9_; float f7 = p_77032_1_.limbSwing -
                 * p_77032_1_.limbSwingAmount * (1.0F - p_76986_9_);
                 *
                 * if (p_77032_1_.isChild()) { f7 *= 3.0F; }
                 *
                 * if (f6 > 1.0F) { f6 = 1.0F; }
                 *
                 * this.func_82408_c(p_77032_1_, p_77032_2_, itemstack);
                 *
                 * modelbiped.render(p_77032_1_, f7, f6, f4, f3 - f2, f13, f5);
                 *
                 * RenderManager.instance.renderEngine.bindTexture(resource);
                 * //this.func_82408_c(p_77032_1_, p_77032_2_, itemstack);
                 * //event.renderer.setRenderPassModel(modelbiped);
                 *
                 * float c1 = (j >> 16 & 255) / 255.0F; float c2 = (j >> 8 &
                 * 255) / 255.0F; float c3 = (j & 255) / 255.0F;
                 * GL11.glColor3f(c1, c2, c3);
                 *
                 * //GL11.glColor3f(1.0F, 1.0F, 1.0F);
                 *
                 * if (itemstack.isItemEnchanted()) { event.result = 15; return
                 * modelbiped; }
                 *
                 * //return 1; event.result = 1; return modelbiped;
                 */

            }

            GL11.glColor3f(1.0F, 1.0F, 1.0F);

            if (itemstack.isItemEnchanted()) {
                event.result = 15;
                return modelbiped;
            }

            // return 1;
            event.result = 1;

            return modelbiped;
        }

        return null;

    }

    protected void func_82408_c(EntityLivingBase p_82408_1_, int p_82408_2_, float p_82408_3_) {
        // ItemStack itemstack = p_82408_1_.inventory.armorItemInSlot(3 -
        // p_82408_2_);
        EquipmentStats e = EntityPlayerManager.getEquipmentStats((EntityPlayer) p_82408_1_);
        // ItemStack itemstack = e.inventory.getStackInSlot(3 - p_82408_2_);
        ItemStack itemstack = e.inventory.getStackInSlot(p_82408_2_);

        if (itemstack != null) {
            Item item = itemstack.getItem();

            if (item instanceof ItemArmor) {
                RenderManager.instance.renderEngine
                        .bindTexture(RenderBiped.getArmorResource(p_82408_1_, itemstack, p_82408_2_, "overlay"));
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }
        }
    }

    private float interpolateRotation(float p_77034_1_, float p_77034_2_, float p_77034_3_) {
        float f3;

        for (f3 = p_77034_2_ - p_77034_1_; f3 < -180.0F; f3 += 360.0F) {
            ;
        }

        while (f3 >= 180.0F) {
            f3 -= 360.0F;
        }

        return p_77034_1_ + p_77034_3_ * f3;
    }

    protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_) {
        return p_77044_1_.ticksExisted + p_77044_2_;
    }

    protected void rotateCorpse(AbstractClientPlayer p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        if (p_77043_1_.isEntityAlive() && p_77043_1_.isPlayerSleeping()) {
            GL11.glRotatef(p_77043_1_.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.getDeathMaxRotation(p_77043_1_), 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        } else {
            this.rotateCorpse2(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
        }
    }

    protected void rotateCorpse2(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        GL11.glRotatef(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);

        if (p_77043_1_.deathTime > 0) {
            float f3 = (p_77043_1_.deathTime + p_77043_4_ - 1.0F) / 20.0F * 1.6F;
            f3 = MathHelper.sqrt_float(f3);

            if (f3 > 1.0F) {
                f3 = 1.0F;
            }

            GL11.glRotatef(f3 * this.getDeathMaxRotation(p_77043_1_), 0.0F, 0.0F, 1.0F);
        } else {
            String s = EnumChatFormatting.getTextWithoutFormattingCodes(p_77043_1_.getCommandSenderName());

            if ((s.equals("Dinnerbone") || s.equals("Grumm"))
                    && (!(p_77043_1_ instanceof EntityPlayer) || !((EntityPlayer) p_77043_1_).getHideCape())) {
                GL11.glTranslatef(0.0F, p_77043_1_.height + 0.1F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            }
        }
    }

    protected float getDeathMaxRotation(EntityLivingBase p_77037_1_) {
        return 90.0F;
    }

    // 車軸の線の描写
    @SubscribeEvent
    public void onDrawBlockHighlight(DrawBlockHighlightEvent event) {

        EntityPlayer player = event.player;
        ItemStack itemstack = event.currentItem;
        MovingObjectPosition target = event.target;

        if (player == null) {
            return;
        }

        // if(itemstack == null)
        // {
        // return;
        // }

        if (EntityPlayerManager.getCustomPlayerData(player).getEquipmentStats().inventory
                .getStackInSlot(EquipmentType.Face.getSlots()[0]) == null) {
            return;
        }

        if (!(EntityPlayerManager.getCustomPlayerData(player).getEquipmentStats().inventory
                .getStackInSlot(EquipmentType.Face.getSlots()[0]).getItem() instanceof IGearForceGridItem)) {
            return;
        }

        if (target.typeOfHit != MovingObjectType.BLOCK) {
            return;
        }
        //
        if (!(player.worldObj.getTileEntity(target.blockX, target.blockY, target.blockZ) instanceof IGearForceGrid)) {
            return;
        }

        double x = target.blockX;
        double y = target.blockY;
        double z = target.blockZ;

        IGearForceGrid tileEntity = (IGearForceGrid) player.worldObj.getTileEntity(target.blockX, target.blockY,
                target.blockZ);

        // if(tileEntity==null){
        // return ;
        // }/

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        // GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);

        double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.partialTicks;
        double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.partialTicks;
        double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.partialTicks;

        float f1 = 0.002F;

        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {
            if (tileEntity.canIn(d)) {
                this.drawInFromDirection((int) x, (int) y, (int) z, d0, d1, d2, d);
            }
        }

        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {
            if (tileEntity.canOut(d)) {
                this.drawOutFromDirection((int) x, (int) y, (int) z, d0, d1, d2, d);
            }
        }

        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

    }

    private void drawOutFromDirection(int x, int y, int z, double d0, double d1, double d2, ForgeDirection d) {

        float f1 = 0.002F;

        GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.8F);
        double x1 = x + d.offsetX;
        double y1 = y + d.offsetY;
        double z1 = z + d.offsetZ;
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.getBoundingBox(x1, y1, z1, x1 + 1.0D, y1 + 1.0D, z1 + 1.0D);
        axisAlignedBB = axisAlignedBB.expand(f1, f1, f1).getOffsetBoundingBox(-d0, -d1, -d2);
        this.drawOutlinedBoundingBox(axisAlignedBB);

        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

    }

    private void drawInFromDirection(int x, int y, int z, double d0, double d1, double d2, ForgeDirection d) {

        float f1 = 0.002F;

        GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.7F);
        double x2 = x + d.offsetX;
        double y2 = y + d.offsetY;
        double z2 = z + d.offsetZ;
        AxisAlignedBB axisAlignedBB2 = AxisAlignedBB.getBoundingBox(x2, y2, z2, x2 + 1.0D, y2 + 1.0D, z2 + 1.0D);
        axisAlignedBB2 = axisAlignedBB2.expand(f1, f1, f1).getOffsetBoundingBox(-d0, -d1, -d2);
        this.drawOutlinedBoundingBox(axisAlignedBB2);

        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

    }

    private void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawing(3);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.draw();
        tessellator.startDrawing(3);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.draw();
        tessellator.startDrawing(1);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        tessellator.draw();
    }

}
