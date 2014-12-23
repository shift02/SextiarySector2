package shift.sextiarysector.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.item.IHammer;
import shift.sextiarysector.tileentity.TileEntityDirection;

public abstract class BlockMotor  extends BlockDirection{

	public BlockMotor() {
		super(Material.iron);
		this.setHardness(0.8F);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6,float par7, float par8, float par9) {

		ItemStack item =  par5EntityPlayer.getCurrentEquippedItem();

		if(item == null || !(item.getItem() instanceof IHammer))return false;

		if(!((IHammer)item.getItem()).canUse(item))return false;

		TileEntityDirection t = (TileEntityDirection) par1World.getTileEntity(x, y, z);

		ForgeDirection d = t.getDirection();

		if(d.ordinal()>5){
			t.direction = d.getOrientation(0);
		}else{
			t.direction = d.getOrientation(d.ordinal()+1);
		}

		((IHammer)item.getItem()).use(item);

		par1World.playSoundEffect(x, y, z, this.stepSound.getStepResourcePath(), 1.0F, par1World.rand.nextFloat() * 0.1F + 0.6F);
		par1World.markBlockForUpdate(x, y, z);

		return true;
	}

}
