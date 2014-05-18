package info.ppservers.ac.items;

import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.blocks.BlockHandler;
import info.ppservers.ac.items.gems.WaterGem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 5/15/2014.
 */
public class ItemHandler {

    public static Item alchCoal;

    //Gems
    public static Item alchGem;
    public static Item waterGem;

    //Food
    public static Item AlchApple;
    public static ItemFood Alchbread;
    public static Item AlchCarrot;
    public static Item AlchPototatoes;
    public static Item AlchSugar;
    public static Item AlchWheat;

    public static Item trolliumIngot;

    public static Item elementalStick;

    public static Item alchChisel;

    public static void init() {
        alchCoal = new AlchCoal();

        //Gems
        alchGem = new AlchGem();
        waterGem = new WaterGem();
        waterGem.setContainerItem(waterGem);

        //Food
        AlchApple = new AlchApple(6, 0.6f, false);
        Alchbread = new Alchbread(1, 0.6f, false);

        trolliumIngot = new TrolliumIngot();

        elementalStick = new ElementalStick();

        alchChisel = new AlchChisel();
        alchChisel.setContainerItem(alchChisel);
    }

    public static void registerItems() {
        GameRegistry.registerItem(alchCoal, "Alchemical Coal");

        //Gems
        GameRegistry.registerItem(alchGem, "Gems");
        GameRegistry.registerItem(waterGem, "Water Gem");

        //Food
        GameRegistry.registerItem(AlchApple, "Alchemical Apple");
        GameRegistry.registerItem(Alchbread, "Alchemical Bread");

        GameRegistry.registerItem(trolliumIngot, "Trollium Ingot");

        GameRegistry.registerItem(elementalStick, "Elemental Stick");

        GameRegistry.registerItem(alchChisel, "Alchemical Chisel");
    }

    public static void registerRecipes() {
        //Elemental Sticks
        GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.elementalStick, 2),new Object[]{
               new ItemStack(BlockHandler.elementalPlanks), new ItemStack(BlockHandler.elementalPlanks)
        });

        //Alchemical Chisel
        GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.alchChisel), "  E", "IE ", "TT ", 'E', ItemHandler.elementalStick, 'I', Items.iron_ingot, 'T', ItemHandler.trolliumIngot);
    }
}
