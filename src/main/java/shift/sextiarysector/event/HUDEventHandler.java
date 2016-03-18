package shift.sextiarysector.event;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import shift.sextiarysector.SSConfig;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.MoistureStats;

public class HUDEventHandler {

    public static final ResourceLocation icons = new ResourceLocation("sextiarysector:textures/guis/icons.png");
    public static final ResourceLocation progress = new ResourceLocation("sextiarysector:textures/guis/progress.png");

    public static int left_height = 39;
    public static int right_height = 39;

    protected final Random rand = new Random();

    public static boolean visibleStamina = false;
    public static boolean visibleMoisture = false;
    //private boolean ARMOR = false;

    public static Minecraft mc = FMLClientHandler.instance().getClient();

    //public static boolean isPM = false;

    //描写のEvent
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderGameOverlayEventPre(RenderGameOverlayEvent.Pre event) {

        int width = event.resolution.getScaledWidth();
        int height = event.resolution.getScaledHeight();

        /*if(event.type == ElementType.FOOD && mc.playerController.shouldDrawHUD()){
        	renderMoisture( width, height);

        	renderStamina( width, height);

        	//renderSeason( width, height);

        }*/

        /*
        if(event.type == ElementType.ARMOR&&ForgeHooks.getTotalArmorValue(mc.thePlayer)>0){
        	//System.out.println("AIR");

            if(!ARMOR){

            	GuiIngameForge.left_height+=(left_height-29);

            	ARMOR =false;
            }else{
            	GuiIngameForge.left_height-=(left_height-29);
            	ARMOR =true;
            }
        }*/

        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            visibleStamina = GuiIngameForge.renderFood | GuiIngameForge.renderHealthMount | GuiIngameForge.renderHealth;
            visibleMoisture = GuiIngameForge.renderFood | GuiIngameForge.renderHealthMount | GuiIngameForge.renderHealth;
        } else if (event.type == RenderGameOverlayEvent.ElementType.ARMOR) {
            if (visibleStamina) {
                renderStamina(width, height);
                visibleStamina = false;
            }
        } else if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR && mc.playerController.shouldDrawHUD()) {
            if (visibleMoisture) {
                renderMoisture(width, height);
                visibleMoisture = false;
            }
            if (visibleStamina) {
                renderStamina(width, height);
                visibleStamina = false;
            }
        }

        //HUDMP.isRenderer = false;
        //isPM = true;

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderGameOverlayEventPost(RenderGameOverlayEvent.Post event) {

        int width = event.resolution.getScaledWidth();
        int height = event.resolution.getScaledHeight();

        if (event.type == RenderGameOverlayEvent.ElementType.FOOD || event.type == RenderGameOverlayEvent.ElementType.HEALTHMOUNT) {
            if (visibleMoisture) {
                renderMoisture(width, height);
                visibleMoisture = false;
            }
            if (visibleStamina) {
                renderStamina(width, height);
                visibleStamina = false;
            }

            /*
            if (!visibleMoisture && isPM) {
            	HUDMP.renderMoney(width, height);
            	HUDMP.renderAddMoney(width, height);
            	isPM = false;
            	HUDMP.isRenderer = false;
            }*/

        }

        if (event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            //renderProgress(width, height);
        }

    }

    //アイテムテキストずらし
    //@SubscribeEvent
    //public void onRenderGameOverlayEventPost(RenderGameOverlayEvent.Post event) {

    /*if(event.type == ElementType.EXPERIENCE||event.type == ElementType.JUMPBAR){

    }*/

    //}

    protected void renderMoisture(int width, int height) {

        if (!visibleMoisture || !SSConfig.statusMoisture) return;
        visibleMoisture = false;

        //mc.thePlayer.addStat(StatList.distanceByBoatStat, 2);
        //System.out.println("StatList");

        mc.mcProfiler.startSection("moisture");

        bind(icons);

        int updateCounter = mc.ingameGUI.getUpdateCounter() + 2;

        int left = width / 2 + 91;
        //int top = height - right_height -10;
        int top = height - GuiIngameForge.right_height;

        GuiIngameForge.right_height += 10;
        //right_height += 10;
        boolean unused = false;// Unused flag in vanilla, seems to be part of a 'fade out' mechanic

        MoistureStats stats = EntityPlayerManager.getMoistureStats(mc.thePlayer);
        int level = EntityPlayerManager.getPrevMoistureLevel(mc.thePlayer);
        //int levelLast = stats.getPrevFoodLevel();

        //System.out.println("BBB "+level+" : "+stats.getMoistureLevel()+" : "+stats.getSaturationLevel());

        for (int i = 0; i < 10; ++i) {
            int idx = i * 2 + 1;
            int x = left - i * 8 - 9;
            int y = top;
            int icon = 0;
            byte backgound = 0;

            int iconY = 0;
            int iconX = 0;

            if (mc.thePlayer.isPotionActive(Potion.hunger)) {
                iconX += 27;
                backgound = 3;
            }

            if (unused) backgound = 1; //Probably should be a += 1 but vanilla never uses this

            if (stats.getSaturationLevel() <= 0.0F && updateCounter % (level * 3 + 1) == 0) {
                y = top + rand.nextInt(3) - 1;
            }

            drawTexturedModalRect(x, y, iconX, iconY, 9, 9);

            /*
            if (unused)
            {
                if (idx < levelLast)
                    drawTexturedModalRect(x, y, icon , iconY, 9, 9);
                else if (idx == levelLast)
                    drawTexturedModalRect(x, y, icon + 9, iconY, 9, 9);
            }*/

            if (idx < level) {
                drawTexturedModalRect(x, y, iconX + 9, iconY, 9, 9);
            } else if (idx == level) {
                drawTexturedModalRect(x, y, iconX + 18, iconY, 9, 9);
            }
        }

        mc.mcProfiler.endSection();
        bind(Gui.icons);

    }

    protected void renderStamina(int width, int height) {

        if (!visibleStamina || !SSConfig.statusStamina) return;
        visibleStamina = false;

        mc.mcProfiler.startSection("stamina");

        bind(icons);

        int updateCounter = mc.ingameGUI.getUpdateCounter() + 1;

        int left = width / 2 - 82;
        int top = height - GuiIngameForge.left_height;//left_height -10;
        GuiIngameForge.left_height += 10;
        //right_height += 10;
        boolean unused = false;// Unused flag in vanilla, seems to be part of a 'fade out' mechanic

        int level = EntityPlayerManager.instance.getStaminaLevel(mc.thePlayer);//getPrevStaminaLevel(mc.thePlayer);

        for (int i = 0; i < 10; ++i) {
            //int idx = i * 2 + 1;
            int idx = (i + 1) * 10;
            int idx2 = level % 10;
            int x = left + i * 8 - 9;
            int y = top;
            int icon = 0;
            byte backgound = 0;

            int iconY = 9;
            int iconX = 0;

            /*
            if (mc.thePlayer.isPotionActive(Potion.hunger))
            {
                icon += 36;
                backgound = 13;
            }*/
            if (unused) backgound = 1; //Probably should be a += 1 but vanilla never uses this

            /*
            if (mc.thePlayer.getFoodStats().getSaturationLevel() <= 0.0F && updateCounter % (level * 3 + 1) == 0)
            {
                y = top + (rand.nextInt(3) - 1);
            }
            */

            drawTexturedModalRect(x, y, icon + backgound * 9, iconY, 9, 9);

            /*
            if (unused)
            {
                if (idx < levelLast)
                    drawTexturedModalRect(x, y, icon , iconY, 9, 9);
                else if (idx == levelLast)
                    drawTexturedModalRect(x, y, icon + 9, iconY, 9, 9);
            }*/

            if (idx <= level) {
                drawTexturedModalRect(x, y, icon + 9, iconY, 9, 9);
            } else if (idx - level < 10 && idx2 != 0) {
                drawTexturedModalRect(x, y, icon + 9 * (idx2 + 1), iconY, 9, 9);
            }
        }

        mc.mcProfiler.endSection();
        bind(Gui.icons);

    }

    protected void renderProgress(int width, int height) {

        mc.mcProfiler.startSection("SSProgress");

        bind(progress);

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        //OpenGlHelper.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef(-50.0f, -50.0f, 0);
        drawTexturedModalRect(width / 2, height / 2, 0, 0, 100, 100);
        //OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();

        mc.mcProfiler.endSection();
        bind(Gui.icons);

    }

    private void bind(ResourceLocation res) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }

    /** 描写位でのX Y テクスチャでのX Y 大きさ X Y */
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float zLevel = -90.0F;

        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(par1 + 0, par2 + par6, zLevel, (par3 + 0) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + par6, zLevel, (par3 + par5) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + 0, zLevel, (par3 + par5) * f, (par4 + 0) * f1);
        tessellator.addVertexWithUV(par1 + 0, par2 + 0, zLevel, (par3 + 0) * f, (par4 + 0) * f1);
        tessellator.draw();
    }

}
