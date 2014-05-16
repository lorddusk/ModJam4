package info.ppservers.ac.blocks.alchinfuser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.items.AlchGem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 5/15/2014.
 */
public class ContainerAlchInfuser extends Container {

    private TileEntityAlchInfuser tileEntityAlchFurnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerAlchInfuser(InventoryPlayer inventoryPlayer, TileEntityAlchInfuser entityAlchFurnace) {
        tileEntityAlchFurnace = entityAlchFurnace;
        addSlotToContainer(new Slot(entityAlchFurnace, TileEntityAlchInfuser.GEM_INVENTORY_INDEX, 56, 17));
        addSlotToContainer(new Slot(entityAlchFurnace, TileEntityAlchInfuser.FUEL_INVENTORY_INDEX, 56, 53));
        addSlotToContainer(new Slot(entityAlchFurnace, TileEntityAlchInfuser.INPUT_INVENTORY_INDEX, 36, 17));
        addSlotToContainer(new SlotAlchInfuser(entityAlchFurnace, TileEntityAlchInfuser.OUTPUT_INVENTORY_INDEX, 116, 35));
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

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return tileEntityAlchFurnace.isUseableByPlayer(var1);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            if (slotIndex < TileEntityAlchInfuser.INVENTORY_SIZE) {
                if (!mergeItemStack(slotItemStack, TileEntityAlchInfuser.INVENTORY_SIZE, inventorySlots.size(), false)) {
                    return null;
                }
            }
            else{
                if(TileEntityAlchInfuser.isItemFuel(slotItemStack))
                {
                    if(!mergeItemStack(slotItemStack, TileEntityAlchInfuser.FUEL_INVENTORY_INDEX, TileEntityAlchInfuser.OUTPUT_INVENTORY_INDEX, false)){
                        return null;
                    }
                }
                else if(slotItemStack.getItem() instanceof AlchGem){
                    if(!mergeItemStack(slotItemStack,TileEntityAlchInfuser.GEM_INVENTORY_INDEX,TileEntityAlchInfuser.OUTPUT_INVENTORY_INDEX,false))
                    {
                        return null;
                    }
                }
                else if(!mergeItemStack(slotItemStack,TileEntityAlchInfuser.INPUT_INVENTORY_INDEX,TileEntityAlchInfuser.GEM_INVENTORY_INDEX,false)){
                    return null;
                }
            }
            if (slotItemStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, tileEntityAlchFurnace.furnaceCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, tileEntityAlchFurnace.furnaceBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, tileEntityAlchFurnace.currentItemBurnTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (Object crafter : this.crafters) {
            ICrafting iCrafting = (ICrafting) crafter;
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

    @SideOnly(Side.CLIENT)
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




}
