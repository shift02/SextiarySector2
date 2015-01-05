package shift.sextiarysector.event;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.machine.energy.IGearForceGrid;
import shift.sextiarysector.api.machine.item.IGearForceGridItem;
import shift.sextiarysector.gui.GuiAchievementsNext;
import shift.sextiarysector.gui.GuiStatsNext;
import shift.sextiarysector.item.TextureSeason;
import shift.sextiarysector.module.FertilizerManager;
import shift.sextiarysector.module.SeasonManager;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.EquipmentType;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientEventHandler {

	@SideOnly(Side.CLIENT)
    public static Minecraft mc = FMLClientHandler.instance().getClient();

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onGuiOpenEvent(GuiOpenEvent event) {

    	if(event.gui instanceof GuiStats&&!(event.gui instanceof GuiStatsNext)){

    		/*
    		Class<GuiStats> c = GuiStats.class;

    		Field f1;
    		GuiScreen m = null;
			try {
				f1 = c.getDeclaredField( "parentGui" );
				f1.setAccessible( true );
				m = (GuiScreen) f1.get( event.gui );
			} catch (NoSuchFieldException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}*/

				event.gui = new GuiStatsNext(new GuiIngameMenu(),mc.thePlayer.getStatFileWriter());



    		//System.out.println("GuiOpenEvent");
    	}

    	if(event.gui instanceof GuiAchievements){

    		GuiScreen gui =  ObfuscationReflectionHelper.getPrivateValue(GuiAchievements.class, (GuiAchievements)event.gui, "field_146562_a");
    		event.gui = new GuiAchievementsNext(gui ,mc.thePlayer.getStatFileWriter());

    	}

    }

    @SubscribeEvent
    public void onItemTooltipEvent(ItemTooltipEvent event) {


    	ItemStack itemStack = event.itemStack;
    	List<String> toolTip = event.toolTip;

    	if(itemStack.getItem()==Items.clock){
    		toolTip.add(SeasonManager.getInstance().getTime2(this.mc.theWorld));
    	}

    }

    public static IIcon slotGF;
    public static IIcon slotFluid;

    public static IIcon[] itemGF;

    public static IIcon waterFlow;
    public static IIcon waterStill;

    public static IIcon portal;

    @SubscribeEvent
	public void PreTextureStitchEvent(TextureStitchEvent.Pre event){

    	//Item
    	if(event.map.getTextureType()==1){


    		slotGF = event.map.registerIcon("sextiarysector:gui/slot_gf");
    		slotFluid = event.map.registerIcon("sextiarysector:gui/slot_fluid");

    		itemGF = new IIcon[2];
    		itemGF[0] = event.map.registerIcon("sextiarysector:damage/damage_0");
    		itemGF[1] = event.map.registerIcon("sextiarysector:damage/damage_1");

    		for(EquipmentType type :EquipmentType.values()){
    			type.registerIcon(event.map);
    		}


    	}else{
    		waterFlow = event.map.registerIcon("sextiarysector:fluid/water_flow");
    		waterStill = event.map.registerIcon("sextiarysector:fluid/water_still");
    		portal = event.map.registerIcon("sextiarysector:fluid/portal");
    	}

    }

    @SubscribeEvent
	public void preFertilizerTextureStitchEvent(TextureStitchEvent.Pre event){

    	if(event.map.getTextureType()==0){

    		for(IFertilizer f : ((FertilizerManager)AgricultureAPI.fertilizerManager).fertilizerIcons){
    			f.registerFertilizerIcons(event.map);
    		}

    	}

    }

    @SubscribeEvent
	public void preVanillaTextureStitchEvent(TextureStitchEvent.Pre event){

    	//Item
    	if(event.map.getTextureType()==0){

    		IIcon icon = new TextureSeason("sextiarysector:vanilla/leaves_birch");

    		((TextureMap)event.map).setTextureEntry("sextiarysector:vanilla/leaves_birch", (TextureAtlasSprite)icon);



			try {

				Field f;

				BlockLeaves birch = (BlockLeaves) Blocks.leaves;
	    		Class<BlockLeaves> c = BlockLeaves.class;
				f = c.getDeclaredField("field_150129_M");

				f.setAccessible(true);

				IIcon[] a = (IIcon[])Array.get(f.get(birch), 0);

	    		Array.set(a, 2, icon);

	    		//System.out.println("AAAAA");

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



  //車軸の線の描写
    @SubscribeEvent
    public void onDrawBlockHighlight(DrawBlockHighlightEvent event) {

    	EntityPlayer player = event.player;
        ItemStack itemstack = event.currentItem;
        MovingObjectPosition target = event.target;

        if(player == null)
        {
        	return;
        }


        //if(itemstack == null)
        //{
        //	return;
       // }

        if(EntityPlayerManager.getCustomPlayerData(player).getEquipmentStats().inventory.getStackInSlot(EquipmentType.Face.getSlot()[0]) == null){
        	return;
        }

        if(!(EntityPlayerManager.getCustomPlayerData(player).getEquipmentStats().inventory.getStackInSlot(EquipmentType.Face.getSlot()[0]).getItem() instanceof IGearForceGridItem))
        {
        	return;
        }

        if(target.typeOfHit != MovingObjectType.BLOCK)
        {
        	return;
        }
//
        if(!(player.worldObj.getTileEntity(target.blockX, target.blockY, target.blockZ) instanceof IGearForceGrid)){
        	return;
        }


        double x = target.blockX;
    	double y = target.blockY;
    	double z = target.blockZ;

    	IGearForceGrid tileEntity = (IGearForceGrid)player.worldObj.getTileEntity(target.blockX, target.blockY, target.blockZ);

		//if(tileEntity==null){
		//	return  ;
		//}/

		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);

		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.partialTicks;
        double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.partialTicks;
        double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.partialTicks;

        float f1 = 0.002F;

        for(ForgeDirection d:ForgeDirection.VALID_DIRECTIONS){
        	if(tileEntity.canIn(d)){
        		this.drawInFromDirection((int)x, (int)y, (int)z, d0, d1, d2, d);
        	}
        }

        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

        for(ForgeDirection d:ForgeDirection.VALID_DIRECTIONS){
        	if(tileEntity.canOut(d)){
        		this.drawOutFromDirection((int)x, (int)y, (int)z, d0, d1, d2, d);
        	}
        }



        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);


    }

    private void drawOutFromDirection(int x, int y, int z, double d0, double d1, double d2, ForgeDirection d){

    	float f1 = 0.002F;

        GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.8F);
        double x1 = x + d.offsetX;
		double y1 = y + d.offsetY;
		double z1 = z + d.offsetZ;
		AxisAlignedBB axisAlignedBB =  AxisAlignedBB.getBoundingBox(x1, y1, z1, x1 + 1.0D, y1 + 1.0D, z1 + 1.0D);
        axisAlignedBB = axisAlignedBB.expand(f1, f1, f1).getOffsetBoundingBox(-d0, -d1, -d2);
        this.drawOutlinedBoundingBox(axisAlignedBB);

        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

    }

    private void drawInFromDirection(int x, int y, int z, double d0, double d1, double d2, ForgeDirection d){

    	float f1 = 0.002F;

    	GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.7F);
        double x2 = x + d.offsetX;
		double y2 = y + d.offsetY;
		double z2 = z + d.offsetZ;
		AxisAlignedBB axisAlignedBB2 =  AxisAlignedBB.getBoundingBox(x2, y2, z2, x2 + 1.0D, y2 + 1.0D, z2 + 1.0D);
        axisAlignedBB2 = axisAlignedBB2.expand(f1, f1, f1).getOffsetBoundingBox(-d0, -d1, -d2);
        this.drawOutlinedBoundingBox(axisAlignedBB2);

        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

    }


    private void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB)
    {
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
