package shift.sextiarysector.world.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSpringWater extends WorldGenerator {

    private Block water;

    public WorldGenSpringWater(Block water) {
        this.water = water;
    }

    @Override
    public boolean generate(World p_76484_1_, Random p_76484_2_, int x, int y, int z) {

        if (p_76484_2_.nextInt(6) != 0) return false;

        for (int i = 0; i < 50 && (y + i) <= 255; i++) {
            if (p_76484_1_.getBlock(x, y + i, z) == Blocks.dirt || p_76484_1_.getBlock(x, y + i, z) == Blocks.grass) {
                y = y + i;
                break;
            }
        }

        if (!this.canGenerate(p_76484_1_, p_76484_2_, x, y, z)) return false;

        int h = 1;

        for (int i = h * -1; i <= h; i++) {
            for (int j = h * -1; j <= h; j++) {

                p_76484_1_.setBlock(x + i, y, z + j, this.water);

            }
        }

        System.out.println("AAA :" + x + " : " + y + " : " + z);

        return true;
    }

    private boolean canGenerate(World p_76484_1_, Random p_76484_2_, int x, int y, int z) {

        int h = 1;

        for (int i = h * -1; i <= h; i++) {
            for (int j = h * -1; j <= h; j++) {

                if (p_76484_1_.getBlock(x + i, y, z + j) != Blocks.dirt && p_76484_1_.getBlock(x + i, y, z + j) != Blocks.grass) return false;

            }
        }

        return true;
    }

}
