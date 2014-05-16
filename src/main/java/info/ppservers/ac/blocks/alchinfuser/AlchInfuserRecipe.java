package info.ppservers.ac.blocks.alchinfuser;

import info.ppservers.ac.util.OreStack;
import info.ppservers.ac.util.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.*;

/**
 * Created by Tim on 5/15/2014.
 */
public class AlchInfuserRecipe {
    private ItemStack recipeOutput;
    private WrappedStack inputStack;
    private ItemStack gemStack;

    public AlchInfuserRecipe(ItemStack recipeOutput, ItemStack inputStack, ItemStack gemStack) {
        this.recipeOutput = recipeOutput.copy();
        this.inputStack = new WrappedStack(inputStack);
        this.gemStack = gemStack.copy();
    }

    public boolean matches(AlchInfuserRecipe alchInfuserRecipes) {
        return compareItemStacks(this.recipeOutput, alchInfuserRecipes.recipeOutput) && matches(alchInfuserRecipes.inputStack, alchInfuserRecipes.gemStack);
    }

    public boolean matches(ItemStack inputStack, ItemStack gemStack) {
        if (OreDictionary.getOreID(inputStack) != -1) {
            if (matches(new WrappedStack(new OreStack(inputStack)), gemStack)) {
                return matches(new WrappedStack(new OreStack(inputStack)), gemStack);
            }
        }
        return matches(new WrappedStack(inputStack),gemStack);
    }

    public boolean matches(WrappedStack inputStack, ItemStack gemStack) {
        return compareStacks(this.inputStack, inputStack) && compareItemStacks(this.gemStack, gemStack);
    }

    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    public WrappedStack[] getRecipeInputs() {
        return new WrappedStack[]{inputStack, new WrappedStack(gemStack)};
    }

    public List<WrappedStack> getRecipeInputsAsWrappedStacks() {
        List<WrappedStack> recipeInputs = new ArrayList<WrappedStack>();
        recipeInputs.add(new WrappedStack(inputStack));
        recipeInputs.add(new WrappedStack(gemStack));
        return recipeInputs;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof AlchInfuserRecipe) {
            return matches((AlchInfuserRecipe) object);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Output: %s, Input: %s, Gem: %s", recipeOutput, inputStack, gemStack);
    }

    private static boolean compareStacks(WrappedStack wrappedStack1, WrappedStack wrappedStack2) {
        if (wrappedStack1 != null && wrappedStack1.getWrappedStack() != null && wrappedStack2 != null && wrappedStack2.getWrappedStack() != null) {
            if (wrappedStack1.getWrappedStack() instanceof ItemStack && wrappedStack2.getWrappedStack() instanceof ItemStack) {
                ItemStack itemStack1 = (ItemStack) wrappedStack1.getWrappedStack();
                itemStack1.stackSize = wrappedStack1.getStackSize();
                ItemStack itemStack2 = (ItemStack) wrappedStack2.getWrappedStack();
                itemStack2.stackSize = wrappedStack2.getStackSize();

                return compareItemStacks(itemStack1, itemStack2);
            }
        }
        return false;
    }

    private static boolean compareItemStacks(ItemStack itemStack1, ItemStack itemStack2) {
        if (itemStack1 != null && itemStack2 != null) {
            if (itemStack1.getItem() == itemStack2.getItem()) {
                if (itemStack1.getItemDamage() == itemStack2.getItemDamage() || itemStack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || itemStack2.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
                    if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound()) {
                        if (itemStack1.getTagCompound().hashCode() == itemStack2.getTagCompound().hashCode()) {
                            return itemStack2.stackSize >= itemStack1.stackSize;
                        }
                    } else if (!itemStack1.hasTagCompound() && !itemStack2.hasTagCompound()) {
                        return itemStack2.stackSize >= itemStack1.stackSize;
                    }
                }
            }
        }

        return false;
    }
}
