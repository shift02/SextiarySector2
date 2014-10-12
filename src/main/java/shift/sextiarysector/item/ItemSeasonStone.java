package shift.sextiarysector.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.api.season.SeasonAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSeasonStone extends Item{

	private IIcon sIcons[];

	public ItemSeasonStone(){
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	}

	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
		if(p_77648_2_.isSneaking() &&  p_77648_1_.getItemDamage()==0 &&p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_)==SSBlocks.chunkLoader){

			if(p_77648_3_.isRemote){
				return true ;
			}

			int i = SeasonAPI.getSeason(p_77648_3_).ordinal()+1;
			p_77648_1_.setItemDamage(i);

			System.out.println("AAA"+i+" : "+p_77648_1_.getItemDamage());
			return true;
		}

        return false;
    }

	@SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
		for(int i = 0; i<5; i++){
			p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
		}

    }

	public String getUnlocalizedName(ItemStack p_77667_1_)
    {
		if(0<p_77667_1_.getItemDamage()&&p_77667_1_.getItemDamage()<5){
			return this.getUnlocalizedName()+"_"+Season.values()[p_77667_1_.getItemDamage() -1].name;
		}
        return this.getUnlocalizedName();
    }

	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack, int pass)
    {
		return par1ItemStack.getItemDamage()!=0;
    }

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {

		if(p_77624_1_.getItemDamage()==0){
			p_77624_3_.add("["+StatCollector.translateToLocal("tooltip.season")+"] "+SeasonAPI.getSeason(p_77624_2_.worldObj).getTranslatedName() );
		}

	}

    public String getItemStackDisplayName(ItemStack p_77653_1_)
    {

    	if(0<p_77653_1_.getItemDamage()&&p_77653_1_.getItemDamage()<5){

    		String c = "";

    		switch(p_77653_1_.getItemDamage()){
    		case 1 : c = EnumChatFormatting.LIGHT_PURPLE.toString();break;
    		case 2 : c = EnumChatFormatting.GREEN.toString();break;
    		case 3 : c = EnumChatFormatting.GOLD.toString();break;
    		case 4 : c = EnumChatFormatting.BLUE.toString();break;
    		}

    		return c + super.getItemStackDisplayName(p_77653_1_);

    	}

    	return super.getItemStackDisplayName(p_77653_1_);
    }

	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
		if(0<p_77617_1_&&p_77617_1_<5){
			return sIcons[p_77617_1_-1];
		}

		return itemIcon;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		sIcons = new IIcon[Season.values().length];

		for(int i = 0;i<Season.values().length;i++){
			sIcons[i] = par1IconRegister.registerIcon(getIconString()+"_"+Season.values()[i].name);
		}

		this.itemIcon = new TextureSeason(this.getIconString());

		((TextureMap)par1IconRegister).setTextureEntry(this.getIconString(), (TextureAtlasSprite)this.itemIcon);

	}

}
