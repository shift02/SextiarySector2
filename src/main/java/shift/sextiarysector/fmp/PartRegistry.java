package shift.sextiarysector.fmp;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityShaft;
import codechicken.lib.packet.PacketCustom;
import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.MultipartGenerator;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;

public class PartRegistry implements IPartFactory, IPartConverter {

    public static PartRegistry instance = new PartRegistry();

    @Override
    public Iterable<Block> blockTypes() {
        return Arrays.asList(
                SSBlocks.woodShaft,
                SSBlocks.stoneShaft,
                SSBlocks.steelShaft,
                SSBlocks.ninjaShaft,
                SSBlocks.orichalcumShaft);
    }

    public static void init() {
        //PartRegistry p = new PartRegistry();
        MinecraftForge.EVENT_BUS.register(new ShaftEventHandler());
        PacketCustom.assignHandler(SextiarySector.instance, new SSMultipartSPH());
        MultiPartRegistry.registerConverter(instance);

        MultipartGenerator.registerPassThroughInterface("shift.sextiarysector.fmp.IShaft");
        MultiPartRegistry.registerParts(instance, new String[] {
                "sextiarysector:wood_shaft",
                "sextiarysector:stone_shaft",
                "sextiarysector:steel_shaft",
                "sextiarysector:ninja_shaft",
                "sextiarysector:orichalcum_shaft"
        });
    }

    @Override
    public TMultiPart convert(World world, BlockCoord pos) {
        Block b = world.getBlock(pos.x, pos.y, pos.z);
        int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
        TileEntity tile = world.getTileEntity(pos.x, pos.y, pos.z);
        if (b == SSBlocks.woodShaft) return new ShaftPart((TileEntityShaft) tile);
        if (b == SSBlocks.stoneShaft) return new ShaftPart((TileEntityShaft) tile);
        if (b == SSBlocks.steelShaft) return new ShaftPart((TileEntityShaft) tile);
        if (b == SSBlocks.ninjaShaft) return new ShaftPart((TileEntityShaft) tile);
        if (b == SSBlocks.orichalcumShaft) return new ShaftPart((TileEntityShaft) tile);
        return null;
    }

    @Override
    public TMultiPart createPart(String name, boolean client) {

        if (name.equals("sextiarysector:wood_shaft")) return new ShaftPart(1);
        if (name.equals("sextiarysector:stone_shaft")) return new ShaftPart(2);
        if (name.equals("sextiarysector:steel_shaft")) return new ShaftPart(3);
        if (name.equals("sextiarysector:ninja_shaft")) return new ShaftPart(4);
        if (name.equals("sextiarysector:orichalcum_shaft")) return new ShaftPart(5);

        return null;
    }

    public static boolean canPlacePart(World world, BlockCoord pos, ShaftPart part) {
        return TileMultipart.canPlacePart(world, pos, part);
    }

    public static void addPart(World world, BlockCoord pos, ShaftPart part) {
        TileMultipart.addPart(world, pos, part);
    }

}
