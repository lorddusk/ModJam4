package info.ppservers.ac.blocks.alchinfuser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.blocks.BlockHandler;
import info.ppservers.ac.items.AlchGem;
import info.ppservers.ac.items.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Tim on 5/15/2014.
 */
public class TileEntityAlchInfuser extends TileEntity implements ISidedInventory {

    public static final int INVENTORY_SIZE = 4;
    public static final int FUEL_INVENTORY_INDEX = 0;
    public static final int INPUT_INVENTORY_INDEX = 1;
    public static final int GEM_INVENTORY_INDEX = 2;
    public static final int OUTPUT_INVENTORY_INDEX = 3;

    private int speedIncrease = 1;

    private ItemStack[] furnaceStack;

    public TileEntityAlchInfuser() {
        furnaceStack = new ItemStack[INVENTORY_SIZE];
    }

    public int furnaceBurnTime;

    public int currentItemBurnTime;

    public int furnaceCookTime;

    private String name;

    public void func_145951_a(String p_145951_1_) {
        this.name = p_145951_1_;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == ForgeDirection.DOWN.ordinal() ? new int[]{FUEL_INVENTORY_INDEX,OUTPUT_INVENTORY_INDEX} : new int[]{INPUT_INVENTORY_INDEX, GEM_INVENTORY_INDEX,OUTPUT_INVENTORY_INDEX};
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side) {
        if(worldObj.getTileEntity(xCoord,yCoord,zCoord) instanceof TileEntityAlchInfuser){
            return isItemValidForSlot(slotIndex, itemStack);
        }else{
            return false;
        }
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side) {
        return slotIndex == OUTPUT_INVENTORY_INDEX;
    }

    @Override
    public int getSizeInventory() {
        return furnaceStack.length;
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return this.furnaceStack[par1];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            if (itemStack.stackSize <= decrementAmount) {
                setInventorySlotContents(slotIndex, null);
            } else {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slotIndex, null
                    );
                }
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        this.furnaceStack[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.name : "Alchemical Infuser";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.name != null && this.name.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgessScaled(int time) {
        return this.furnaceCookTime * time / (200 / getSpeedIncrease());
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int time) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = (200 / getSpeedIncrease());
        }
        return this.furnaceBurnTime * time / this.currentItemBurnTime;
    }

    public boolean isBurning() {
        if (furnaceBurnTime > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateEntity() {
        boolean isBurning = this.furnaceBurnTime > 0;
        boolean sendUpdate = false;

        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.furnaceBurnTime == 0 && this.canInfuse()) {
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceStack[FUEL_INVENTORY_INDEX]);
                if (this.furnaceBurnTime > 0) {
                    sendUpdate = true;
                    if (this.furnaceStack[FUEL_INVENTORY_INDEX] != null) {
                        --this.furnaceStack[FUEL_INVENTORY_INDEX].stackSize;
                        if (this.furnaceStack[FUEL_INVENTORY_INDEX].stackSize == 0) {
                            this.furnaceStack[FUEL_INVENTORY_INDEX] = furnaceStack[FUEL_INVENTORY_INDEX].getItem().getContainerItem(furnaceStack[FUEL_INVENTORY_INDEX]);
                        }
                    }
                }
            }
            if (this.isBurning() && this.canInfuse()) {
                ++this.furnaceCookTime;
                if (this.furnaceCookTime == (200 / getSpeedIncrease())) {
                    this.furnaceCookTime = 0;
                    this.infuseItem();
                    sendUpdate = true;
                }
            } else {
                this.furnaceCookTime = 0;
            }


            if (isBurning != this.furnaceBurnTime > 0) {
                sendUpdate = true;
                AlchInfuser.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (sendUpdate) {
            this.markDirty();
        }
    }

    private boolean canInfuse() {
        if (this.furnaceStack[0] == null) {
            return false;
        } else {
            ItemStack itemStack = InfuserRecipes.getInstance().getResult(this.furnaceStack[INPUT_INVENTORY_INDEX], this.furnaceStack[GEM_INVENTORY_INDEX]);
            if (itemStack == null) {
                return false;
            }
            if (this.furnaceStack[OUTPUT_INVENTORY_INDEX] == null) {
                return true;
            } else {
                boolean outputEquals = this.furnaceStack[OUTPUT_INVENTORY_INDEX].isItemEqual(itemStack);
                int merge = this.furnaceStack[OUTPUT_INVENTORY_INDEX].stackSize + itemStack.stackSize;
                if (outputEquals) {
                    return merge <= getInventoryStackLimit() && merge <= itemStack.getMaxStackSize();
                }
            }
        }
        return false;
    }

    public void infuseItem() {
        if (this.canInfuse()) {
            InfuserRecipeHandler itemStack = InfuserRecipes.getInstance().getRecipe(furnaceStack[INPUT_INVENTORY_INDEX], furnaceStack[GEM_INVENTORY_INDEX]);
            if (this.furnaceStack[OUTPUT_INVENTORY_INDEX] == null) {
                this.furnaceStack[OUTPUT_INVENTORY_INDEX] = itemStack.getRecipeOutput().copy();
            } else if (this.furnaceStack[OUTPUT_INVENTORY_INDEX].isItemEqual(itemStack.getRecipeOutput())) {
                this.furnaceStack[OUTPUT_INVENTORY_INDEX].stackSize += itemStack.getRecipeOutput().stackSize;
            }
            decrStackSize(INPUT_INVENTORY_INDEX, itemStack.getRecipeInputs()[0].getStackSize());
            decrStackSize(GEM_INVENTORY_INDEX, itemStack.getRecipeInputs()[1].getStackSize());
        }
    }

    public static int getItemBurnTime(ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        } else {
            Item item = itemStack.getItem();
            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);
                if (block == BlockHandler.alchCoalBlock) {
                    return 2000;
                }
            }
            if (item == ItemHandler.alchCoal) {
                return 200;
            }
        }
        return 0;
    }

    public int getSpeedIncrease() {
        return speedIncrease;
    }

    public static boolean isItemFuel(ItemStack itemStack) {
        return getItemBurnTime(itemStack) > 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack) {
        switch (slotIndex) {
            case FUEL_INVENTORY_INDEX: {
                return TileEntityAlchInfuser.isItemFuel(itemStack);
            }
            case INPUT_INVENTORY_INDEX: {
                return true;
            }
            case GEM_INVENTORY_INDEX: {
                return itemStack.getItem() instanceof AlchGem;
            }
            default: {
                return false;
            }
        }
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList nbtTagList = tagCompound.getTagList("Items", 10);
        this.furnaceStack = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound nbtTagCompound = nbtTagList.getCompoundTagAt(i);
            byte b0 = nbtTagCompound.getByte("Slot");
            if (b0 >= 0 && b0 < this.furnaceStack.length) {
                this.furnaceStack[b0] = ItemStack.loadItemStackFromNBT(nbtTagCompound);
            }
        }
        this.furnaceBurnTime = tagCompound.getShort("BurnTime");
        this.furnaceCookTime = tagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceStack[1]);

        if (tagCompound.hasKey("CustomName", 8)) {
            this.name = tagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setShort("BurnTime", (short) this.furnaceBurnTime);
        tagCompound.setShort("CookTime", (short) this.furnaceCookTime);
        NBTTagList nbtTagList = new NBTTagList();

        for (int i = 0; i < this.furnaceStack.length; ++i) {
            if (this.furnaceStack[i] != null) {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Slot", (byte) i);
                this.furnaceStack[i].writeToNBT(nbtTagCompound);
                nbtTagList.appendTag(nbtTagCompound);
            }
        }
        tagCompound.setTag("Items", nbtTagList);
        if (this.hasCustomInventoryName()) {
            tagCompound.setString("CustomName", this.name);
        }
    }
}
