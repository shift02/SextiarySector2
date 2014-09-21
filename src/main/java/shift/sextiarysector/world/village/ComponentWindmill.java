package shift.sextiarysector.world.village;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class ComponentWindmill extends StructureVillagePieces.Village{

	public ComponentWindmill(){

	}

	public ComponentWindmill(StructureVillagePieces.Start p_i2107_1_, int p_i2107_2_, Random p_i2106_3_, StructureBoundingBox p_i2106_4_, int p_i2106_5_)
    {
		super(p_i2107_1_,p_i2107_2_);
		this.coordBaseMode = p_i2106_5_;
        this.boundingBox = p_i2106_4_;
    }

	public static Object buildComponent(StructureVillagePieces.Start startPiece,List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {

		System.out.println("AA*"+p1+" : " + p3);
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 5, 10, 5, p4);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new ComponentWindmill(startPiece, p5, random, structureboundingbox, p4) : null;

	}

	@Override
	public boolean addComponentParts(World p_74875_1_, Random p_74875_2_,StructureBoundingBox p_74875_3_) {



		if (this.field_143015_k < 0)
        {
            this.field_143015_k = this.getAverageGroundLevel(p_74875_1_, p_74875_3_);

            if (this.field_143015_k < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 10 - 1, 0);
        }

		System.out.println("AAAAA");

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 6, 10, 6, Blocks.air, Blocks.air, false);

		//床
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 5, 10, 5, Blocks.planks, Blocks.planks, false);

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 0, 0, 6,  Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 0, 0, 6, 0, 6,  Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 0, 5, 0, 0,  Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 6, 5, 0, 6,  Blocks.cobblestone, Blocks.cobblestone, false);

		//柱
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 5, 0,  Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 1, 0, 6, 5, 0,  Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 1, 6, 6, 5, 6,  Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 6, 0, 5, 6,  Blocks.log, Blocks.log, false);

		//this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 6, 0, 6, p_74875_3_);


		return true;
	}

}
