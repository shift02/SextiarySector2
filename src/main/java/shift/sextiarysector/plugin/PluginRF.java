package shift.sextiarysector.plugin;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.block.BlockGFDynamo;
import shift.sextiarysector.item.ItemBlockDirection;
import shift.sextiarysector.renderer.block.RendererGFDynamo;
import shift.sextiarysector.tileentity.TileEntityGFDynamo;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PluginRF implements IPlugin {

    @Override
    public String getModName() {
        return "RF";
    }

    public static int gfDynamoType;
    public static Block gfDynamo;

    @Override
    public void prePlugin(FMLPreInitializationEvent event) {

        gfDynamo = new BlockGFDynamo().setBlockName("ss.gf_dynamo").setBlockTextureName("stone").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(gfDynamo, ItemBlockDirection.class, "GFDynamo");
        GameRegistry.registerTileEntity(TileEntityGFDynamo.class, "GFDynamo");

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preClientPlugin(FMLPreInitializationEvent event) {

        gfDynamoType = cpw.mods.fml.client.registry.RenderingRegistry.getNextAvailableRenderId();
        cpw.mods.fml.client.registry.RenderingRegistry.registerBlockHandler(new RendererGFDynamo());
        cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGFDynamo.class, new RendererGFDynamo());

    }

    @Override
    public void initPlugin(FMLInitializationEvent event) {

        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(this.gfDynamo, 1),
                new Object[] { " z ", "yxy", "xax",
                        Character.valueOf('x'), "ingotSteel",
                        Character.valueOf('y'), SSItems.energyReactor,
                        Character.valueOf('z'), SSItems.redGel,
                        Character.valueOf('a'), "gearSteel",
                }));

    }

    @Override
    public void postPlugin(FMLPostInitializationEvent event) {

    }

}
