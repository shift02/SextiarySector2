package shift.sextiarysector.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleTrap implements IModule {

    private static ModuleTrap instance;

    private ModuleTrap() {
    }

    public static ModuleTrap getInstance() {
        if (instance == null) {
            instance = new ModuleTrap();
        }
        return instance;
    }

    static final List<TrapEntry> smallTrapList = new ArrayList<TrapEntry>();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void load(FMLInitializationEvent event) {

        addTrap(new ItemStack(Items.feather), 50);
        addTrap(new ItemStack(Items.egg), 20);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    public static void addTrap(ItemStack seed, int weight) {
        addTrap(new TrapEntry(seed, weight));
    }

    public static void addTrap(TrapEntry shellEntry) {
        smallTrapList.add(shellEntry);
    }

    //public static void addTrap(Class<? extends Entity> entity, int weight) {
    //	smallTrapList.add(new TrapEntry(entity, weight));
    //}

    public static class TrapEntry extends WeightedRandom.Item {
        public final ItemStack item;
        //public final Class<? extends Entity> entity;
        private float damage;
        private boolean enchant;

        public TrapEntry(ItemStack seed, int weight) {
            super(weight);
            this.item = seed;
            //this.entity = null;
        }

        public TrapEntry(Class<? extends Entity> entity, int weight) {
            super(weight);
            this.item = null;
            //this.entity = entity;
        }

        public ItemStack addCustom(Random p_150708_1_) {
            ItemStack itemstack = this.item.copy();

            if (this.damage > 0.0F) {
                int i = (int) (this.damage * this.item.getMaxDamage());
                int j = itemstack.getMaxDamage() - p_150708_1_.nextInt(p_150708_1_.nextInt(i) + 1);

                if (j > i) {
                    j = i;
                }

                if (j < 1) {
                    j = 1;
                }

                itemstack.setItemDamage(j);
            }

            if (this.enchant) {
                EnchantmentHelper.addRandomEnchantment(p_150708_1_, itemstack, 30);
            }

            return itemstack;
        }

        public TrapEntry setDamage(float p_150709_1_) {
            this.damage = p_150709_1_;
            return this;
        }

        public TrapEntry setEnchant() {
            this.enchant = true;
            return this;
        }

    }

    public static ItemStack getTrapItem(World world) {

        TrapEntry entry = (TrapEntry) WeightedRandom.getRandomItem(world.rand, smallTrapList);

        if (entry == null) return null;

        if (entry.item != null) {
            return entry.addCustom(world.rand);
        }

        return null;

    }

    public static void spawnTrap(World world, int x, int y, int z) {

        Entity entity = null;

        TrapEntry entry = (TrapEntry) WeightedRandom.getRandomItem(world.rand, smallTrapList);

        if (entry == null) return;

        if (entry.item != null) {
            entity = new EntityItem(world, x + 0.5d, y + 0.5d, z + 0.5d, entry.addCustom(world.rand));
        }

        /*if (entry.entity != null)
        {
        	entity = createEntityByClass(entry.entity, world);
        	if (entity != null && entity instanceof EntityLivingBase)
        	{
        		EntityLiving entityliving = (EntityLiving) entity;
        		entity.setLocationAndAngles(x + 0.5d, y + 0.5d, z + 0.5d, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
        		entityliving.rotationYawHead = entityliving.rotationYaw;
        		entityliving.renderYawOffset = entityliving.rotationYaw;
        		//entityliving.onSpawnWithEgg((IEntityLivingData) null);
        		entityliving.playLivingSound();
        	}
        }*/

        if (entity != null) world.spawnEntityInWorld(entity);

    }

    public static Entity createEntityByClass(Class<? extends Entity> entityClass, World p_75616_1_) {
        Entity entity = null;

        try {
            Class oclass = entityClass;

            if (oclass != null) {
                entity = (Entity) oclass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { p_75616_1_ });
            }
        } catch (Exception exception) {
            SextiarySector.Log.catching(exception);
        }

        return entity;
    }

}
