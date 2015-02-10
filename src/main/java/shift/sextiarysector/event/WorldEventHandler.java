package shift.sextiarysector.event;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import shift.sextiarysector.SSBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldEventHandler {

	private WorldGenMinable bluestoneGen;
	private WorldGenMinable yellowstoneGen;

	private WorldGenMinable copperOreGen;
	private WorldGenMinable zincOreGen;
	private WorldGenMinable silverOreGen;

	private WorldGenMinable mithrilOreGen;
	private WorldGenMinable orichalcumOreGen;

	private WorldGenMinable coaLargeGen;
	private WorldGenMinable ironLarge;
	private WorldGenMinable goldLarge;

	private WorldGenLakes springWater;

	private int chunk_X;
	private int chunk_Z;
	private World currentWorld;
	private BiomeGenBase biome;

	public Random randomGenerato;

	@SubscribeEvent
    public void onOreGenEvent(OreGenEvent.Pre event) {

		this.randomGenerato = event.rand;
		this.chunk_X = event.worldX;
		this.chunk_Z = event.worldZ;
		this.currentWorld = event.world;
		this.biome = event.world.getBiomeGenForCoords(chunk_X, chunk_Z);

		this.bluestoneGen = new WorldGenMinable(SSBlocks.blueStoneOre, 7);
		this.yellowstoneGen = new WorldGenMinable(SSBlocks.yellowStoneOre, 7);

		copperOreGen = new WorldGenMinable(SSBlocks.copperOre, 14);
		zincOreGen = new WorldGenMinable(SSBlocks.zincOre, 8);
		silverOreGen = new WorldGenMinable(SSBlocks.silverOre, 8);

		mithrilOreGen = new WorldGenMinable(SSBlocks.mithrilOre, 8);
		orichalcumOreGen = new WorldGenMinable(SSBlocks.orichalcumOre, 7);

		coaLargeGen = new WorldGenMinable(SSBlocks.coalLargeOre, 16);
		ironLarge = new WorldGenMinable(SSBlocks.ironLargeOre, 8);
		goldLarge = new WorldGenMinable(SSBlocks.goldLargeOre, 8);


		this.genStandardOre1(8, this.bluestoneGen, 0, 16);
		this.genStandardOre1(8, this.yellowstoneGen, 0, 16);

		this.genStandardOre1(20, this.copperOreGen, 0, 64);
		this.genStandardOre1(12, this.zincOreGen, 0, 64);
		this.genStandardOre1(2, this.silverOreGen, 0, 32);

		if(BiomeDictionary.isBiomeOfType(biome, MAGICAL)){
			this.genStandardOre1(4, mithrilOreGen, 0, 32);
			this.genStandardOre1(1, orichalcumOreGen, 0, 18);
		}

		this.genStandardOre1(10, this.coaLargeGen, 0, 128);
		this.genStandardOre1(10, this.ironLarge, 0, 64);
		this.genStandardOre1(1, this.goldLarge, 0, 32);
		//System.out.println("onOreGenEvent");

	}

	@SubscribeEvent
    public void onPopulateChunkEvent(PopulateChunkEvent.Post event) {

		if(event.world.rand.nextInt(32)!=0)return;

		this.randomGenerato = event.rand;
		this.chunk_X = event.chunkX *16;
		this.chunk_Z = event.chunkZ *16;
		this.currentWorld = event.world;
		this.biome = event.world.getBiomeGenForCoords(chunk_X +16, chunk_Z +16);

		this.springWater = new WorldGenLakes(SSBlocks.drinkingWater);
		if(BiomeDictionary.isBiomeOfType(biome, Type.FOREST)){
			this.genStandard(1, springWater, 60, 126);

		}

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

	protected void genStandard(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
		for (int l = 0; l < par1; ++l)
        {
            int i1 = this.chunk_X + this.randomGenerato.nextInt(16);
            int j1 = this.randomGenerato.nextInt(par4 - par3) + par3;
            int k1 = this.chunk_Z + this.randomGenerato.nextInt(16);
            if(par2WorldGenerator.generate(this.currentWorld, this.randomGenerato, i1, j1, k1)){
            }

        }
    }

}
