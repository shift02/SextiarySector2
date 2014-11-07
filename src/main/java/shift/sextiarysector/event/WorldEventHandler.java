package shift.sextiarysector.event;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import shift.sextiarysector.SSBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldEventHandler {

	private WorldGenMinable bluestoneGen;
	private WorldGenMinable yellowstoneGen;

	private WorldGenMinable coaLargeGen;
	private WorldGenMinable ironLarge;
	private WorldGenMinable goldLarge;

	private int chunk_X;
	private int chunk_Z;
	private World currentWorld;

	public Random randomGenerato;

	@SubscribeEvent
    public void onOreGenEvent(OreGenEvent.Pre event) {

		this.randomGenerato = event.rand;
		this.chunk_X = event.worldX;
		this.chunk_Z = event.worldZ;
		this.currentWorld = event.world;

		this.bluestoneGen = new WorldGenMinable(SSBlocks.blueStoneOre, 7);
		this.yellowstoneGen = new WorldGenMinable(SSBlocks.yellowStoneOre, 7);

		coaLargeGen = new WorldGenMinable(SSBlocks.coalLargeOre, 16);
		ironLarge = new WorldGenMinable(SSBlocks.ironLargeOre, 8);
		goldLarge = new WorldGenMinable(SSBlocks.goldLargeOre, 8);


		this.genStandardOre1(8, this.bluestoneGen, 0, 16);
		this.genStandardOre1(8, this.yellowstoneGen, 0, 16);

		this.genStandardOre1(10, this.coaLargeGen, 0, 128);
		this.genStandardOre1(10, this.ironLarge, 0, 64);
		this.genStandardOre1(1, this.goldLarge, 0, 32);
		//System.out.println("onOreGenEvent");

	}

	protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int l = 0; l < par1; ++l)
        {
            int i1 = this.chunk_X + this.randomGenerato.nextInt(16);
            int j1 = this.randomGenerato.nextInt(par4 - par3) + par3;
            int k1 = this.chunk_Z + this.randomGenerato.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerato, i1, j1, k1);
        }
    }

}
