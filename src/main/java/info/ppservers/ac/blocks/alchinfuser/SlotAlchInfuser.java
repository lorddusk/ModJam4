package info.ppservers.ac.blocks.alchinfuser;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

/**
 * Created by Tim on 5/15/2014.
 */
public class SlotAlchInfuser extends Slot {

    public SlotAlchInfuser(IInventory inventory, int x, int y, int z){
        super(inventory,x,y,z);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack){
        return false;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack){
        super.onPickupFromSlot(player, itemStack);

    }

}
