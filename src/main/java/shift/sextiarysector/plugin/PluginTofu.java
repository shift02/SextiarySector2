package shift.sextiarysector.plugin;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.block.BlockTofuMotor;
import shift.sextiarysector.item.ItemBlockDirection;
import shift.sextiarysector.renderer.block.RendererTofuMotor;
import shift.sextiarysector.tileentity.TileEntityTofuMotor;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PluginTofu implements IPlugin {

    @Override
    public String getModName() {
        return "TofuCraft";
    }

    public static int tofuMotorType;
    public static Block tofuMotor;

    public static Item filterCloth;

    public static Item tfCircuit;

    @Override
    public void prePlugin(FMLPreInitializationEvent event) {
        tofuMotor = new BlockTofuMotor().setBlockName("ss.tofu_motor").setBlockTextureName("glass").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(tofuMotor, ItemBlockDirection.class, "TofuMotor");
        GameRegistry.registerTileEntity(TileEntityTofuMotor.class, "TofuMotor");

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preClientPlugin(FMLPreInitializationEvent event) {

        tofuMotorType = cpw.mods.fml.client.registry.RenderingRegistry.getNextAvailableRenderId();
        cpw.mods.fml.client.registry.RenderingRegistry.registerBlockHandler(new RendererTofuMotor());
        cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTofuMotor.class, new RendererTofuMotor());

    }

    @Override
    public void initPlugin(FMLInitializationEvent event) {

        filterCloth = GameRegistry.findItem("TofuCraft", "filterCloth");
        OreDictionary.registerOre("craftingFilterCloth", filterCloth);

        tfCircuit = GameRegistry.findItem("TofuCraft", "materials");

        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(this.tofuMotor, 1),
                new Object[] { "xzx", "xyx", "pbp",
                        Character.valueOf('x'), "paneGlassColorless",
                        Character.valueOf('y'), SSItems.energyReactor,
                        Character.valueOf('z'), SSBlocks.stoneShaft,
                        Character.valueOf('b'), new ItemStack(tfCircuit, 1, 3),
                        Character.valueOf('p'), new ItemStack(tfCircuit, 1, 4)
                }));

    }

    @Override
    public void postPlugin(FMLPostInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
