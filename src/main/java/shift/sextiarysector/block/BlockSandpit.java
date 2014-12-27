package shift.sextiarysector.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;

public class BlockSandpit extends Block{

	public BlockSandpit() {
		super(Material.sand);

		this.setCreativeTab(SextiarySectorAPI.TabSSFishery);
		this.setHarvestLevel("scoop", 0);
		this.setHardness(0.5F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (1.0f/16.0f)*1 , 1.0F);
	    this.setStepSound(soundTypeSand);
	    this.setLightOpacity(0);
	    this.useNeighborBrightness = true;
	}


	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (world.rand.nextInt(3) != 0) return ret;
        ItemStack shell = getShell(world);
        if (shell != null) ret.add(shell);
        return ret;
    }

	public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean canBlockStay(World p_149718_1_, int x, int y, int z)
    {
        return  p_149718_1_.getBlock(x, y - 1, z).isOpaqueCube() && !p_149718_1_.getBlock(x, y - 1, z).isAir(p_149718_1_, x, y-1, z);
    }

    public boolean canPlaceBlockAt(World p_149742_1_, int x, int y, int z)
    {
        return p_149742_1_.getBlock(x, y - 1, z).isOpaqueCube() && !p_149742_1_.getBlock(x, y - 1, z).isAir(p_149742_1_, x, y-1, z);
    }

    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        this.func_150155_m(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
    }

    private boolean func_150155_m(World p_150155_1_, int p_150155_2_, int p_150155_3_, int p_150155_4_)
    {
        if (!this.canPlaceBlockAt(p_150155_1_, p_150155_2_, p_150155_3_, p_150155_4_))
        {
            p_150155_1_.setBlockToAir(p_150155_2_, p_150155_3_, p_150155_4_);
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {

    	ItemStack stack = player.inventory.getCurrentItem();
        String tool = this.getHarvestTool(meta);
        if (stack == null || tool == null)
        {
            return player.canHarvestBlock(this);
        }

        int toolLevel = stack.getItem().getHarvestLevel(stack, tool);
        if (toolLevel < 0)
        {
            return player.canHarvestBlock(this);
        }

        return toolLevel >= this.getHarvestLevel(meta);

    }

    public int getMobilityFlag()
    {
        return 1;
    }

	public static void addShell(ItemStack seed, int weight)
    {
		addShell(new ShellEntry(seed, weight));
    }

	public static void addShell(ShellEntry shellEntry){
		shellList.add(shellEntry);
	}

	public static class ShellEntry extends WeightedRandom.Item
    {
        public final ItemStack shell;
        private float damage;
        private boolean enchant;

        public ShellEntry(ItemStack seed, int weight)
        {
            super(weight);
            this.shell = seed;
        }

        public ItemStack addCustom(Random p_150708_1_)
        {
            ItemStack itemstack = this.shell.copy();

            if (this.damage > 0.0F)
            {
                int i = (int)(this.damage * (float)this.shell.getMaxDamage());
                int j = itemstack.getMaxDamage() - p_150708_1_.nextInt(p_150708_1_.nextInt(i) + 1);

                if (j > i)
                {
                    j = i;
                }

                if (j < 1)
                {
                    j = 1;
                }

                itemstack.setItemDamage(j);
            }

            if (this.enchant)
            {
                EnchantmentHelper.addRandomEnchantment(p_150708_1_, itemstack, 30);
            }

            return itemstack;
        }

        public ShellEntry setDamage(float p_150709_1_)
        {
            this.damage = p_150709_1_;
            return this;
        }

        public ShellEntry setEnchant()
        {
            this.enchant = true;
            return this;
        }

    }
    static final List<ShellEntry> shellList = new ArrayList<ShellEntry>();

    public static ItemStack getShell(World world)
    {
    	ShellEntry entry = (ShellEntry)WeightedRandom.getRandomItem(world.rand, shellList);
        if (entry == null || entry.shell == null)
        {
            return null;
        }

        return entry.addCustom(world.rand);

    }

}
