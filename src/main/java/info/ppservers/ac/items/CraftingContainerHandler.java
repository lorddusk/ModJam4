package info.ppservers.ac.items;

import info.ppservers.ac.blocks.BlockHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by Tim on 5/16/2014.
 */
public class CraftingContainerHandler {

    public static final ItemStack ALCH_CHISEL = new ItemStack(ItemHandler.alchChisel, 1, OreDictionary.WILDCARD_VALUE);

    public static void init() {
        registerChiselRecipes(ALCH_CHISEL);
    }

    public static void registerChiselRecipes(ItemStack itemStack) {
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(ItemHandler.alchGem,1,0), itemStack, BlockHandler.alchStone));
    }
}
