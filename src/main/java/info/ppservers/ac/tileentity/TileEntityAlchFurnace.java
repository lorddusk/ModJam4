package info.ppservers.ac.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.blocks.AlchFurnace;
import info.ppservers.ac.crafting.AlchFurnaceRecipes;
import info.ppservers.ac.items.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Created by Tim on 5/15/2014.
 */
public class TileEntityAlchFurnace extends TileEntity implements ISidedInventory {

    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{2, 1};
    private static final int[] slotsSides = new int[]{1};
    private static int speedIncrease = 2;

    private ItemStack[] furnaceStack = new ItemStack[3];

    public int furnaceBurnTime;

    public int currentItemBurnTime;

    public int furnaceCookTime;

    private String name;

    public void func_145951_a(String p_145951_1_) {
        this.name = p_145951_1_;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int par1) {
        return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int var1, ItemStack var2, int var3) {
        return this.isItemValidForSlot(var1, var2);
    }

    @Override
    public boolean canExtractItem(int var1, ItemStack var2, int var3) {
        return var3 != 0 || var1 != 1 || var2.getItem() == Items.bucket;
    }

    @Override
    public int getSizeInventory() {
        return this.furnaceStack.length;
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return this.furnaceStack[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.furnaceStack[par1] != null) {
            ItemStack itemStack;

            if (this.furnaceStack[par1].stackSize <= par2) {
                itemStack = this.furnaceStack[par1];
                this.furnaceStack[par1] = null;
                return itemStack;
            } else {
                itemStack = this.furnaceStack[par1].splitStack(par2);
                if (this.furnaceStack[par1].stackSize == 0) {
                    this.furnaceStack[par1] = null;
                }
                return itemStack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.furnaceStack[par1] != null) {
            ItemStack itemStack = this.furnaceStack[par1];
            this.furnaceStack[par1] = null;
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2) {
        this.furnaceStack[par1] = par2;
        if (par2 != null && par2.stackSize > this.getInventoryStackLimit()) {
            par2.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.name : "Alchemical Furnace";
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
        return this.furnaceCookTime * time / (200 / speedIncrease);
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int time) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = (200 / speedIncrease);
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

    public void updateEntity() {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;

        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.furnaceBurnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceStack[1]);
                if (this.furnaceBurnTime > 0) {
                    flag1 = true;
                    if (this.furnaceStack[1] != null) {
                        --this.furnaceStack[1].stackSize;
                        if (this.furnaceStack[1].stackSize == 0) {
                            this.furnaceStack[1] = furnaceStack[1].getItem().getContainerItem(furnaceStack[1]);
                        }
                    }
                }
            }
            if (this.isBurning() && this.canSmelt()) {
                ++this.furnaceCookTime;
                if (this.furnaceCookTime == (200 / speedIncrease)) {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.furnaceCookTime = 0;
            }
            if (flag != this.furnaceBurnTime > 0) {
                flag1 = true;
                AlchFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canSmelt() {
        if (this.furnaceStack[0] == null) {
            return false;
        } else {
            ItemStack itemStack = AlchFurnaceRecipes.smelting().getSmeltingResult(this.furnaceStack[0]);
            if (itemStack == null) {
                return false;
            }
            if (this.furnaceStack[2] == null) {
                return true;
            }
            if (!this.furnaceStack[2].isItemEqual(itemStack)) {
                return false;
            }
            int result = furnaceStack[2].stackSize + itemStack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.furnaceStack[2].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemStack = AlchFurnaceRecipes.smelting().getSmeltingResult(this.furnaceStack[0]);
            if (this.furnaceStack[2] == null) {
                this.furnaceStack[2] = itemStack.copy();
            } else if (this.furnaceStack[2].getItem() == itemStack.getItem()) {
                this.furnaceStack[2].stackSize += itemStack.stackSize;
            }
            --this.furnaceStack[0].stackSize;
            if (this.furnaceStack[0].stackSize <= 0) {
                this.furnaceStack[0] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        } else {
            Item item = itemStack.getItem();
            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);
            }
            if(item == ItemHandler.alchCoal){
                return 1600;
            }
        }
        return 0;
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
    public boolean isItemValidForSlot(int par1, ItemStack itemStack) {
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(itemStack) : true);
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
