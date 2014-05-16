package info.ppservers.ac.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

/**
 * Created by Tim on 5/15/2014.
 */
public abstract class ItemHandler {

    public static Item alchCoal;
    public static Item dullGem;

    public static Item AlchApple;
    public static ItemFood Alchbread;
    public static Item AlchCarrot;
    public static Item AlchPototatoes;
    public static Item AlchSugar;
    public static Item AlchWheat;

    public static void init() {
        alchCoal = new AlchCoal().setUnlocalizedName(Info.COAL_UNLOCALIZED_NAME);
        dullGem = new DullGem().setUnlocalizedName(Info.DULL_UNLOCALIZED_NAME);

        AlchApple = new info.ppservers.ac.items.AlchApple(6, 0.6f, false);
        Alchbread = new info.ppservers.ac.items.Alchbread(1, 0.6f, false);
    }

    public static void registerItems() {
        GameRegistry.registerItem(alchCoal,"Alchemical Coal");
        GameRegistry.registerItem(dullGem,"Dull Gem");

        GameRegistry.registerItem(AlchApple, Info.ALCHAPPLE_NAME);
        GameRegistry.registerItem(Alchbread, "Alchemical Choal");
    }

    public static void registerRecipes(){
    }

    private ItemHandler() {

    }
}
