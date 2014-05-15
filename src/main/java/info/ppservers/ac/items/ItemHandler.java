package info.ppservers.ac.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Tim on 5/15/2014.
 */
public abstract class ItemHandler {

    public static Item alchCoal;
    public static Item dullGem;

    public static void init() {
        alchCoal = new AlchCoal().setUnlocalizedName(Info.COAL_UNLOCALIZED_NAME);
        dullGem = new DullGem().setUnlocalizedName(Info.DULL_UNLOCALIZED_NAME);
    }

    public static void registerItems() {
        GameRegistry.registerItem(alchCoal,"Alchemical Coal");
        GameRegistry.registerItem(dullGem,"Dull Gem");
    }

    public static void registerRecipes(){
    }

    private ItemHandler() {

    }
}
