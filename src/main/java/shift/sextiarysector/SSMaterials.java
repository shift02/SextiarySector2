package shift.sextiarysector;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class SSMaterials extends Material {

	public SSMaterials(MapColor p_i2116_1_) {
		super(p_i2116_1_);
	}

	public static Material machine;

	public static void preInitMaterial() {
		machine = (new SSMaterials(MapColor.ironColor)).setRequiresTool();
	}

}
