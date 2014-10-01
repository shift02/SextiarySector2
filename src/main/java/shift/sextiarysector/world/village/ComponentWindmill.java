package shift.sextiarysector.world.village;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.tileentity.TileEntityDirection;

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

		//System.out.println("AA*"+p1+" : " + p3);
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

		//System.out.println("AAAAA");

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 6, 10, 6, Blocks.air, Blocks.air, false);

		//床
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 5, 0, 5, Blocks.planks, Blocks.planks, false);

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 0, 0, 6,  Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 0, 0, 6, 0, 6,  Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 0, 5, 0, 0,  Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 6, 5, 0, 6,  Blocks.cobblestone, Blocks.cobblestone, false);

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 2, 0, -1, p_74875_3_);

		//柱
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 4, 0,  Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 1, 0, 6, 4, 0,  Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 1, 6, 6, 4, 6,  Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 6, 0, 4, 6,  Blocks.log, Blocks.log, false);

		//壁
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 4, 5,  Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 1, 1, 6, 4, 5,  Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 0, 5, 4, 0,  Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 6, 5, 4, 6,  Blocks.planks, Blocks.planks, false);

		this.placeDoorAtCurrentPosition(p_74875_1_, p_74875_3_, p_74875_2_, 2, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 0, 3, 3, p_74875_3_);

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 2, 3, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 3, 3, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 3, 6, p_74875_3_);

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 6, 3, 3, p_74875_3_);

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 3, 0, p_74875_3_);


		//二階
		//床
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 5, 1, 5, 5, 5, Blocks.planks, Blocks.planks, false);

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 5, 0, 0, 5, 6,  Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 5, 0, 6, 5, 6,  Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 5, 0, 5, 5, 0,  Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 5, 6, 5, 5, 6,  Blocks.cobblestone, Blocks.cobblestone, false);

		//柱
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 6, 0, 0, 7, 0,  Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 6, 0, 6, 7, 0,  Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 6, 6, 6, 7, 6,  Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 6, 6, 0, 7, 6,  Blocks.log, Blocks.log, false);

		//壁
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 6, 1, 0, 7, 5,  Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 6, 1, 6, 7, 5,  Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 6, 0, 5, 7, 0,  Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 6, 6, 5, 7, 6,  Blocks.planks, Blocks.planks, false);

		//屋根
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 8, 0, 5, 8, 0,  Blocks.fence, Blocks.fence, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 8, 6, 5, 8, 6,  Blocks.fence, Blocks.fence, false);

		int i = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
        int j = this.getMetadataWithOffset(Blocks.oak_stairs, 0);

		for (int l = 0; l <= 6; ++l)
        {
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, j, 0, 8, l, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, j, 1, 9, l, p_74875_3_);
        }

		for (int l = 0; l <= 6; ++l)
        {
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, i, 6, 8, l, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, i, 5, 9, l, p_74875_3_);
        }

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 2, 9, 0, 4, 9, 6,  Blocks.planks, Blocks.planks, false);

		//一階内装

		i = this.getMetadataWithOffset(Blocks.ladder, 4);

		for (int l = 1; l <= 5; ++l)
        {
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.ladder, i, 5, l, 4, p_74875_3_);
        }

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, this.getMetadataWithOffset(Blocks.torch, 1), 1, 3, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, this.getMetadataWithOffset(Blocks.torch, 0), 5, 3, 2, p_74875_3_);


		for (int l = 2; l <= 5; ++l)
        {
			this.placeBlockAtCurrentPosition(p_74875_1_, SSBlocks.shaft, 0, 3, l, 5, p_74875_3_);
			this.setForgeDirection(p_74875_1_, 3, l, 5, ForgeDirection.getOrientation(0));
        }

		this.placeBlockAtCurrentPosition(p_74875_1_, SSBlocks.millstone, 0, 3, 1, 5, p_74875_3_);

		i = this.getMetadataWithOffset(Blocks.ladder, 3);
		this.setForgeDirection(p_74875_1_, 3, 1, 5, ForgeDirection.getOrientation(i));

		//二階内装

		this.placeBlockAtCurrentPosition(p_74875_1_, SSBlocks.smallWindmill, 0, 3, 6, -1, p_74875_3_);

		i = this.getMetadataWithOffset(Blocks.ladder, 3);
		this.setForgeDirection(p_74875_1_, 3, 6, -1, ForgeDirection.getOrientation(i));

		//this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 6, 0, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, SSBlocks.woodGearBox, 0, 3, 6, 0, p_74875_3_);
		this.setForgeDirection(p_74875_1_, 3, 6, 0, ForgeDirection.getOrientation(i));

		this.placeBlockAtCurrentPosition(p_74875_1_, SSBlocks.woodGearBox, 0, 3, 6, 5, p_74875_3_);
		this.setForgeDirection(p_74875_1_, 3, 6, 5, ForgeDirection.getOrientation(i));

		for (int l = 1; l <= 4; ++l)
        {
			this.placeBlockAtCurrentPosition(p_74875_1_, SSBlocks.shaft, 0, 3, 6, l, p_74875_3_);
			this.setForgeDirection(p_74875_1_, 3, 6, l, ForgeDirection.getOrientation(i).getOpposite());
        }

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 3, 7, 5, p_74875_3_);

		return true;
	}

	private void setForgeDirection(World p_74879_1_, int p_74879_4_, int p_74879_5_, int p_74879_6_ ,ForgeDirection d){

		int i1 = this.getXWithOffset(p_74879_4_, p_74879_6_);
        int j1 = this.getYWithOffset(p_74879_5_);
        int k1 = this.getZWithOffset(p_74879_4_, p_74879_6_);

        TileEntity t = p_74879_1_.getTileEntity(i1, j1, k1);
        if(t instanceof TileEntityDirection){
        	((TileEntityDirection) t).direction = d;
        }

	}

}
