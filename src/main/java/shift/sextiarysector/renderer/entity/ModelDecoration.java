package shift.sextiarysector.renderer.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelDecoration extends ModelBase {

    public float onGround;
    public boolean isRiding;
    public List boxList = new ArrayList();
    public boolean isChild = true;
    private final Map modelTextureMap = new HashMap();
    public int textureWidth = 64;
    public int textureHeight = 32;

    public ModelBase base;

    public void setModel(ModelBase base) {

        this.base = base;
        this.onGround = base.onGround;
        this.isRiding = base.isRiding;
        this.boxList = base.boxList;
        this.isChild = base.isChild;
        this.textureWidth = base.textureWidth;
        this.textureHeight = base.textureHeight;
        //this.modelTextureMap = base.modelTextureMap;

    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.base.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        this.base.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        this.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
    }

    @Override
    public ModelRenderer getRandomModelBox(Random p_85181_1_) {
        return (ModelRenderer) this.boxList.get(p_85181_1_.nextInt(this.boxList.size()));
    }

    @Override
    protected void setTextureOffset(String p_78085_1_, int p_78085_2_, int p_78085_3_) {
        this.modelTextureMap.put(p_78085_1_, new TextureOffset(p_78085_2_, p_78085_3_));
    }

    @Override
    public TextureOffset getTextureOffset(String p_78084_1_) {
        return (TextureOffset) this.modelTextureMap.get(p_78084_1_);
    }

}
