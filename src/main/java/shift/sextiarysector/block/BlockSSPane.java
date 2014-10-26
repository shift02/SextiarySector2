package shift.sextiarysector.block;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSSPane extends BlockPane{

	private int type;

	public BlockSSPane(String p_i45432_1_, String p_i45432_2_,Material p_i45432_3_, boolean p_i45432_4_,int type ) {
		super(p_i45432_1_, p_i45432_2_, p_i45432_3_, p_i45432_4_);
		this.type = type;
	}


	public int getRenderType()
    {
		return this.type == 1 ? 41 : 18;
    }

	@SideOnly(Side.CLIENT)
    public String getItemIconName()
    {
		return this.type == 1 ? this.textureName : null;
    }

}
