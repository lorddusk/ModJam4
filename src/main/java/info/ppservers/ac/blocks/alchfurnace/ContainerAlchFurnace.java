package info.ppservers.ac.blocks.alchfurnace;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 5/15/2014.
 */
public class ContainerAlchFurnace extends Container {

    private TileEntityAlchFurnace tileEntityAlchFurnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerAlchFurnace(InventoryPlayer inventoryPlayer, TileEntityAlchFurnace entityAlchFurnace) {
        tileEntityAlchFurnace = entityAlchFurnace;
        addSlotToContainer(new Slot(entityAlchFurnace, 0, 56, 17));
        addSlotToContainer(new Slot(entityAlchFurnace, 1, 56, 53));
        addSlotToContainer(new SlotAlchFurnace(inventoryPlayer.player, entityAlchFurnace, 2, 116, 35));
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, tileEntityAlchFurnace.furnaceCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, tileEntityAlchFurnace.furnaceBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, tileEntityAlchFurnace.currentItemBurnTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < crafters.size(); ++i) {
            ICrafting iCrafting = (ICrafting) crafters.get(i);
            if (lastCookTime != tileEntityAlchFurnace.furnaceCookTime) {
                iCrafting.sendProgressBarUpdate(this, 0, tileEntityAlchFurnace.furnaceCookTime);
            }
            if (lastBurnTime != tileEntityAlchFurnace.furnaceBurnTime) {
                iCrafting.sendProgressBarUpdate(this, 1, tileEntityAlchFurnace.furnaceBurnTime);
            }
            if (lastItemBurnTime != tileEntityAlchFurnace.currentItemBurnTime) {
                iCrafting.sendProgressBarUpdate(this, 2, tileEntityAlchFurnace.currentItemBurnTime);
            }
        }

        lastCookTime = tileEntityAlchFurnace.furnaceCookTime;
        lastBurnTime = tileEntityAlchFurnace.furnaceBurnTime;
        lastItemBurnTime = tileEntityAlchFurnace.currentItemBurnTime;
    }

    @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            tileEntityAlchFurnace.furnaceCookTime = par2;
        }
        if (par1 == 1) {
            tileEntityAlchFurnace.furnaceBurnTime = par2;
        }
        if (par1 == 2) {
            tileEntityAlchFurnace.currentItemBurnTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return tileEntityAlchFurnace.isUseableByPlayer(var1);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (par2 == 2) {
                if (!mergeItemStack(itemStack1, 3, 39, true)) {
                    return null;
                }
                slot.onSlotChange(itemStack1, itemStack);
            } else if (par2 != 1 && par2 != 0) {
                if (AlchFurnaceRecipes.smelting().getSmeltingResult(itemStack1) != null) {
                    if (!mergeItemStack(itemStack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityAlchFurnace.isItemFuel(itemStack1)) {
                    if (!mergeItemStack(itemStack1, 1, 2, false)) {
                        return null;
                    }
                } else if (par2 >= 3 && par2 < 30) {
                    if (!mergeItemStack(itemStack1, 30, 39, false)) {
                        return null;
                    }
                } else if (par2 >= 30 && par2 < 39 && !mergeItemStack(itemStack1, 3, 30, false)) {
                    return null;
                }
            } else if (!mergeItemStack(itemStack1, 3, 39, false)) {
                return null;
            }
            if (itemStack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }
            if (itemStack1.stackSize == itemStack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemStack1);
        }
        return itemStack;
    }
}
