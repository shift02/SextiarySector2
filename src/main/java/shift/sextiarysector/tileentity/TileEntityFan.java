package shift.sextiarysector.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityFan extends TileEntityDirection{

	public float rotateStep = 360;

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (this.worldObj.isRemote) {
			this.updateClientEntity();
		} else {

			this.updateServerEntity();

		}


		AxisAlignedBB aabb = getDirectionAABB();

		List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, aabb, null);

		for(Entity e :list){
			e.motionX+=(this.direction.offsetX/10.0f);
			e.motionY+=(this.direction.offsetY/10.0f);
			e.motionZ+=(this.direction.offsetZ/10.0f);
		}

	}

	public void updateClientEntity() {

		this.rotateStep += 12;

	}

	private void updateServerEntity() {



	}

	private AxisAlignedBB getDirectionAABB(){

		int minX = 0, minY = 0, minZ = 0;
		int maxX = 1, maxY = 1, maxZ = 1;

		switch(direction){
		case DOWN: minY = -5; break;
		case UP: maxY = 6; break;
		case NORTH: minZ = -5; break;
		case SOUTH: maxZ = 6; break;
		case WEST: minX = -5; break;
		case EAST: maxX = 6; break;
		default:break;
		}

		return AxisAlignedBB.getBoundingBox(this.xCoord + minX, this.yCoord + minY, this.zCoord + minZ, this.xCoord + maxX, this.yCoord + maxY, this.zCoord + maxZ);

	}

	public float getRotateStep() {
		return rotateStep;
	}

}
