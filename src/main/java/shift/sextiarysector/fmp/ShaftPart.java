package shift.sextiarysector.fmp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.JIconHitEffects;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.minecraft.McBlockPart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.api.gearforce.item.IHammer;
import shift.sextiarysector.api.gearforce.tileentity.EnergyStorage;
import shift.sextiarysector.block.BlockShaft;
import shift.sextiarysector.renderer.block.RendererShaft;
import shift.sextiarysector.renderer.model.ModelShaft;
import shift.sextiarysector.tileentity.TileEntityShaft;

public class ShaftPart extends McBlockPart implements JNormalOcclusion, JIconHitEffects, IShaft {

    //public TileEntityShaft shaft = new TileEntityShaft();

    //public ForgeDirection direction = ForgeDirection.UNKNOWN;
    public TileEntityShaft tShaft = null;
    public int sType = 0;

    double minX = 0.25D;
    double minY = 0.25D;
    double minZ = 0.25D;
    double maxX = 0.75D;
    double maxY = 0.75D;
    double maxZ = 0.75D;
    private final Cuboid6 minmam = new Cuboid6(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D);

    public ShaftPart(int type, ForgeDirection d, World w, int x, int y, int z) {
        //this.direction = d;
        TileEntityShaft shaft = new TileEntityShaft(type);
        shaft.setDirection(d);
        shaft.setWorldObj(w);
        shaft.xCoord = x;
        shaft.yCoord = y;
        shaft.zCoord = z;

        this.tShaft = shaft;
        this.sType = type;
        this.directionChange();

    }

    public ShaftPart(TileEntityShaft t) {
        this.tShaft = t;
        this.sType = t.getStorage().getMaxPower();
        this.directionChange();
    }

    public ShaftPart(int type) {
        sType = type;
    }

    @Override
    public Block getBlock() {
        switch (this.sType) {
            case 1:
                return SSBlocks.woodShaft;
            case 2:
                return SSBlocks.stoneShaft;
            case 3:
                return SSBlocks.steelShaft;
            case 4:
                return SSBlocks.ninjaShaft;
            case 5:
                return SSBlocks.orichalcumShaft;
        }
        return SSBlocks.woodShaft;
    }

    public int rotateStep = 360;

    @Override
    public void update() {

        //if (rotateStep <= 0) {
        //	rotateStep += 360;
        //}

        //rotateStep -= 2.2;
        this.tShaft.setWorldObj(this.world());
        this.tShaft.xCoord = this.x();
        this.tShaft.yCoord = this.y();
        this.tShaft.zCoord = this.z();
        if (this.sType != 0 && this.tShaft.getStorage().getMaxPower() != this.sType) this.tShaft.getStorage().setPowerCapacity(this.sType);

        if (!this.tShaft.getWorldObj().isRemote & this.tShaft.getStorage().getSpeedStored() != this.tShaft.lastSpeed) {
            this.tShaft.lastSpeed = (this.tShaft.getStorage().getSpeedStored());

            sendDescUpdate();
            tile().notifyPartChange(this);
            tile().markDirty();

        }

        this.tShaft.updateEntity();

    }

    @Override
    public boolean activate(EntityPlayer player, MovingObjectPosition part, ItemStack item) {

        if (item == null || !(item.getItem() instanceof IHammer)) return false;

        World world = world();
        if (world.isRemote) return true;

        //IShaft t = (IShaft) this.world().getTileEntity(this.x(), this.y(), this.z());

        if (!player.isSneaking()) {
            this.changeToHammer(player, part, item);
        } else {
            //System.out.println("AA");
            //this.breakToHammer(player, part, item);
        }

        //this.world().markBlockForUpdate(this.x(), this.y(), this.z());

        sendDescUpdate();
        tile().notifyPartChange(this);
        tile().markDirty();

        return true;

    }

    private void changeToHammer(EntityPlayer player, MovingObjectPosition part, ItemStack item) {

        if (!((IHammer) item.getItem()).canUse(item, player, 2)) return;

        IShaft t = (IShaft) this.world().getTileEntity(this.x(), this.y(), this.z());

        ForgeDirection d = t.getDirection();

        if (d.ordinal() > 4) {
            t.setDirection(d.getOrientation(0));
        } else {
            t.setDirection(d.getOrientation(d.ordinal() + 1));
        }

        ((IHammer) item.getItem()).use(item, player, 2);

        directionChange();
        this.world().playSoundEffect(this.x(), this.y(), this.z(), this.getBlock().stepSound.getStepResourcePath(), 1.0F, this.world().rand.nextFloat() * 0.1F + 0.6F);

    }

    private void breakToHammer(EntityPlayer player, MovingObjectPosition part, ItemStack item) {

        if (!((IHammer) item.getItem()).canUse(item, player, 10)) return;

        List<TMultiPart> l = this.tile().jPartList();

        Iterator<TMultiPart> i = l.iterator();

        while (i.hasNext()) {

            if (i == this) {

                EntityItem eItem = new EntityItem(player.worldObj, this.x() + 0.5d, this.y() + 0.5d, this.z() + 0.5d, new ItemStack(this.getBlock(), 1));

                player.worldObj.spawnEntityInWorld(eItem);

                ((IHammer) item.getItem()).use(item, player, 10);

                //i.remove();
                this.tile().remPart(this);

            }

        }

    }

    public static McBlockPart placement(int type, World world, BlockCoord pos, EntityPlayer player, int side) {
        //pos = pos.copy().offset(side ^ 1);
        //if (!world.isSideSolid(pos.x, pos.y, pos.z, ForgeDirection.getOrientation(side)))
        //	return null;

        //int meta = sideMetaMap[side ^ 1];
        //if (side < 2 && (MathHelper.floor_double(player.rotationYaw / 90 + 0.5) & 1) == 0)
        //	meta = metaSwapMap[side ^ 1];

        return new ShaftPart(type, ForgeDirection.getOrientation(side), world, pos.x, pos.y, pos.z);

    }

    private static final ResourceLocation shaftTextures = new ResourceLocation("sextiarysector:textures/models/shaft.png");
    static public ModelShaft modelShaft = new ModelShaft();

    @Override
    public void renderDynamic(Vector3 pos, float frame, int pass) {

        /*this.tShaft.setWorldObj(this.world());
        this.tShaft.xCoord = this.x();
        this.tShaft.yCoord = this.y();
        this.tShaft.zCoord = this.z();*/

        getShaft();

        this.directionChange();

        RendererShaft.renderTileEntityAt2(this.world().getTileEntity(this.x(), this.y(), this.z()), pos.x, pos.y, pos.z, frame);

    }

    @Override
    public Cuboid6 getBounds() {

        /*this.tShaft.setWorldObj(this.world());
        this.tShaft.xCoord = this.x();
        this.tShaft.yCoord = this.y();
        this.tShaft.zCoord = this.z();*/

        getShaft();

        directionChange();
        return new Cuboid6(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
    }

    @Override
    public Iterable<Cuboid6> getOcclusionBoxes() {

        return Arrays.asList(new Cuboid6[] { this.getBounds() });

    }

    @Override
    public Iterable<Cuboid6> getCollisionBoxes() {
        return Arrays.asList(new Cuboid6[] { this.getBounds() });
    }

    @Override
    public String getType() {

        switch (this.sType) {
            case 1:
                return "sextiarysector:wood_shaft";
            case 2:
                return "sextiarysector:stone_shaft";
            case 3:
                return "sextiarysector:steel_shaft";
            case 4:
                return "sextiarysector:ninja_shaft";
            case 5:
                return "sextiarysector:orichalcum_shaft";
        }
        return "sextiarysector:wood_shaft";
    }

    private void directionChange() {
        if (tShaft.getDirection().ordinal() == 0 || tShaft.getDirection().ordinal() == 1) {
            minY = 0;
            maxY = 1;
            minZ = 0.25D;
            maxZ = 0.75D;
            minX = 0.25D;
            maxX = 0.75D;
        } else if (tShaft.getDirection().ordinal() == 2 || tShaft.getDirection().ordinal() == 3) {
            minZ = 0;
            maxZ = 1;
            minX = 0.25D;
            maxX = 0.75D;
            minY = 0.25D;
            maxY = 0.75D;
        } else if (tShaft.getDirection().ordinal() == 4 || tShaft.getDirection().ordinal() == 5) {
            minX = 0;
            maxX = 1;
            minZ = 0.25D;
            maxZ = 0.75D;
            minY = 0.25D;
            maxY = 0.75D;
        }
    }

    @Override
    public Iterable<ItemStack> getDrops() {
        return Arrays.asList(new ItemStack[] { new ItemStack(this.getBlock(), 1, 0) });
    }

    @Override
    public ItemStack pickItem(MovingObjectPosition hit) {
        return new ItemStack(this.getBlock(), 1, 0);
    }

    @Override
    public IIcon getBreakingIcon(Object arg0, int arg1) {
        return Blocks.stone.getIcon(0, 0);
    }

    @Override
    public IIcon getBrokenIcon(int arg0) {
        return Blocks.stone.getIcon(0, 0);
    }

    public void randomDisplayTick(Random random) {

        this.getShaft();

        if (this.tShaft == null) return;
        if (this.tShaft.lastSpeed == 0) return;

        BlockShaft.sparkle(this.world(), this.x(), this.y(), this.z());

    }

    private TileEntityShaft getShaft() {

        if (tShaft == null) tShaft = new TileEntityShaft();

        try {

            tShaft.setWorldObj(this.world());
            this.tShaft.xCoord = this.x();
            this.tShaft.yCoord = this.y();
            this.tShaft.zCoord = this.z();

        } catch (NullPointerException e) {
        }

        directionChange();

        return tShaft;
    }

    @Override
    public void save(NBTTagCompound tag) {
        tag.setByte("stype", (byte) this.sType);
        NBTTagCompound s = new NBTTagCompound();

        tShaft.setWorldObj(this.world());
        this.tShaft.xCoord = this.x();
        this.tShaft.yCoord = this.y();
        this.tShaft.zCoord = this.z();

        if (tShaft != null) tShaft.writeToNBT(s);
        tag.setTag("shaft", s);

    }

    @Override
    public void load(NBTTagCompound tag) {
        this.sType = tag.getByte("stype");

        NBTTagCompound s = tag.getCompoundTag("shaft");
        if (tShaft == null) tShaft = new TileEntityShaft();
        tShaft.readFromNBT(s);
        try {
            this.world().markBlockForUpdate(this.x(), this.y(), this.z());
        } catch (NullPointerException e) {
        }

        //tShaft.setWorldObj(this.world());
        //this.tShaft.xCoord = this.x();
        //this.tShaft.yCoord = this.y();
        //this.tShaft.zCoord = this.z();

    }

    @Override
    public void writeDesc(MCDataOutput packet) {
        packet.writeByte((byte) this.sType);

        NBTTagCompound s = new NBTTagCompound();

        /*this.tShaft.setWorldObj(this.world());
        this.tShaft.xCoord = this.x();
        this.tShaft.yCoord = this.y();
        this.tShaft.zCoord = this.z();*/

        //getShaft();

        if (tShaft != null) tShaft.writeToNBT(s);
        packet.writeNBTTagCompound(s);

    }

    @Override
    public void readDesc(MCDataInput packet) {
        this.sType = packet.readByte();

        NBTTagCompound s = packet.readNBTTagCompound();
        if (tShaft == null) tShaft = new TileEntityShaft();
        tShaft.readFromNBT(s);
        try {
            this.world().markBlockForUpdate(this.x(), this.y(), this.z());
        } catch (NullPointerException e) {
        }
        //tShaft.setWorldObj(this.world());
        //this.tShaft.xCoord = this.x();
        //this.tShaft.yCoord = this.y();
        //this.tShaft.zCoord = this.z();

    }

    @Override
    public void setDirection(ForgeDirection d) {
        this.tShaft.setDirection(d);
    }

    @Override
    public ForgeDirection getDirection() {
        return this.getShaft().getDirection();
    }

    @Override
    public void setRotateStep(float r) {
        this.getShaft().setRotateStep(r);
    }

    @Override
    public float getRotateStep() {
        return this.getShaft().getRotateStep();
    }

    //GF
    @Override
    public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {
        return this.getShaft().addEnergy(from, power, speed, simulate);
    }

    @Override
    public int drawEnergy(ForgeDirection from, int power, int speed, boolean simulate) {
        return this.getShaft().drawEnergy(from, power, speed, simulate);
    }

    @Override
    public boolean canInterface(ForgeDirection from) {
        return this.getShaft().canInterface(from);
    }

    @Override
    public int getPowerStored(ForgeDirection from) {
        return this.getShaft().getPowerStored(from);
    }

    @Override
    public int getSpeedStored(ForgeDirection from) {
        return this.getShaft().getSpeedStored(from);
    }

    @Override
    public int getMaxPowerStored(ForgeDirection from) {
        return this.getShaft().getMaxPowerStored(from);
    }

    @Override
    public int getMaxSpeedStored(ForgeDirection from) {
        return this.getShaft().getMaxSpeedStored(from);
    }

    @Override
    public EnergyStorage getStorage() {
        return this.getShaft().getStorage();
    }

    @Override
    public IShaft getInTileEntityShaft() {
        return this.getShaft().getInTileEntityShaft();
    }

    @Override
    public IShaft getOutTileEntityShaft() {
        return this.getShaft().getOutTileEntityShaft();
    }

}
