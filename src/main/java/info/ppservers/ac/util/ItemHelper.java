package info.ppservers.ac.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Comparator;

/**
 * Created by Tim on 5/16/2014.
 */
public class ItemHelper {
    private static double rand;

    public static final String NBT_ITEM_DISPLAY = "display";
    public static final String NBT_ITEM_COLOR = "color";
    public static final String PURE_WHITE = "ffffff";

    /**
     * Compares two ItemStacks for equality, testing itemID, metaData, stackSize, and their NBTTagCompounds (if they are
     * present)
     *
     * @param first
     * The first ItemStack being tested for equality
     * @param second
     * The second ItemStack being tested for equality
     *
     * @return true if the two ItemStacks are equivalent, false otherwise
     */
    public static boolean equals(ItemStack first, ItemStack second)
    {
        return (comparator.compare(first, second) == 0);
    }

    public static int compare(ItemStack itemStack1, ItemStack itemStack2)
    {
        return comparator.compare(itemStack1, itemStack2);
    }

    public static String toString(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            return String.format("%sxitemStack[%s:%s:%s:%s]", itemStack.stackSize, itemStack.getItem(), itemStack.getItemDamage(), itemStack.getUnlocalizedName(), itemStack.getItem().getClass().getCanonicalName());
        }

        return "null";
    }

    public static boolean hasColor(ItemStack itemStack)
    {
        return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(NBT_ITEM_DISPLAY) && itemStack.getTagCompound().getCompoundTag(NBT_ITEM_DISPLAY).hasKey(NBT_ITEM_COLOR);
    }

    public static int getColor(ItemStack itemStack)
    {
        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound == null)
        {
            return Integer.parseInt(PURE_WHITE, 16);
        }
        else
        {
            NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(NBT_ITEM_DISPLAY);
            return displayTagCompound == null ? Integer.parseInt(PURE_WHITE, 16) : displayTagCompound.hasKey(NBT_ITEM_COLOR) ? displayTagCompound.getInteger(NBT_ITEM_COLOR) : Integer.parseInt(PURE_WHITE, 16);
        }
    }

    public static void setColor(ItemStack itemStack, int color)
    {
        if (itemStack != null)
        {
            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

            if (nbtTagCompound == null)
            {
                nbtTagCompound = new NBTTagCompound();
                itemStack.setTagCompound(nbtTagCompound);
            }

            NBTTagCompound colourTagCompound = nbtTagCompound.getCompoundTag(NBT_ITEM_DISPLAY);

            if (!nbtTagCompound.hasKey(NBT_ITEM_DISPLAY)) {
                nbtTagCompound.setTag(NBT_ITEM_DISPLAY, colourTagCompound);
            }

            colourTagCompound.setInteger(NBT_ITEM_COLOR, color);
        }
    }

    public static Comparator<ItemStack> comparator = new Comparator<ItemStack>()
    {
        public int compare(ItemStack itemStack1, ItemStack itemStack2) {
            if (itemStack1 != null && itemStack2 != null) {
                // Sort on itemID
                if (itemStack1.getItem() == itemStack2.getItem()) {

                    // Then sort on meta
                    if (itemStack1.getItemDamage() == itemStack2.getItemDamage()) {

                        // Then sort on NBT
                        if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound()) {

                            // Then sort on stack size
                            if (itemStack1.getTagCompound().equals(itemStack2.getTagCompound())) {
                                return (itemStack1.stackSize - itemStack2.stackSize);
                            } else {
                                return (itemStack1.getTagCompound().hashCode() - itemStack2.getTagCompound().hashCode());
                            }
                        } else if (!(itemStack1.hasTagCompound()) && itemStack2.hasTagCompound()) {
                            return -1;
                        } else if (itemStack1.hasTagCompound() && !(itemStack2.hasTagCompound())) {
                            return 1;
                        } else {
                            return (itemStack1.stackSize - itemStack2.stackSize);
                        }
                    } else {
                        return (itemStack1.getItemDamage() - itemStack2.getItemDamage());
                    }
                }
            } else if (itemStack1 != null && itemStack2 == null) {
                return -1;
            } else if (itemStack1 == null && itemStack2 != null) {
                return 1;
            } else {
                return 0;
            }
            return 0;
        }

    };
}
