package info.ppservers.ac.blocks.alchfurnace;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;

/**
 * Created by Tim on 5/15/2014.
 */
public class SlotAlchFurnace extends Slot {

    private EntityPlayer thePlayer;
    private int anInt;

    public SlotAlchFurnace(EntityPlayer player, IInventory iInventory, int x, int y, int z) {
        super(iInventory, x, y, z);
        this.thePlayer = player;
    }

    public boolean isItemValid(ItemStack itemStack) {
        return false;
    }

    public ItemStack decrStackSize(int par1) {
        if (this.getHasStack()) {
            this.anInt += Math.min(par1, this.getStack().stackSize);
        }
        return super.decrStackSize(par1);
    }

    public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack) {
        this.onCrafting(itemStack);
        super.onPickupFromSlot(player, itemStack);
    }

    protected void onCrafting(ItemStack itemStack, int par2) {
        this.anInt += par2;
        this.onCrafting(itemStack);
    }

    protected void onCrafting(ItemStack itemStack) {
        itemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.anInt);
        if (!this.thePlayer.worldObj.isRemote) {
            int i = this.anInt;
            float f = AlchFurnaceRecipes.smelting().getExperience(itemStack);
            int j;
            if (f == 0.0F) {
                i = 0;
            } else if (f < 1.0F) {
                j = MathHelper.floor_float((float) i * f);
                if (j < MathHelper.ceiling_float_int((float) i * f) && (float) Math.random() < (float) i * f - (float) j) {
                    ++j;
                }
                i = j;
            }
            while (i > 0) {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(thePlayer.worldObj, thePlayer.posX, thePlayer.posY + 0.5D, thePlayer.posZ + 0.5D, j));
            }
        }
        this.anInt = 0;

        FMLCommonHandler.instance().firePlayerSmeltedEvent(thePlayer, itemStack);

        if (itemStack.getItem() == Items.iron_ingot) {
            this.thePlayer.addStat(AchievementList.acquireIron, 1);
        }

        if (itemStack.getItem() == Items.cooked_fished) {
            this.thePlayer.addStat(AchievementList.cookFish, 1);
        }
    }
}
