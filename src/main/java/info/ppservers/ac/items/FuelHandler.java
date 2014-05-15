package info.ppservers.ac.items;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 5/15/2014.
 */
public class FuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.getItem() == ItemHandler.alchCoal) {
            return 3200;
        } else {
            return 0;
        }
    }
}
