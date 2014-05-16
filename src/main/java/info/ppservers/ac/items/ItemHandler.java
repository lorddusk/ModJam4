package info.ppservers.ac.items;

import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.blocks.BlockHandler;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 5/15/2014.
 */
public abstract class ItemHandler {

    public static Item alchCoal;
    public static Item alchGem;

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
        alchCoal = new AlchCoal().setUnlocalizedName(Info.COAL_UNLOCALIZED_NAME);
        alchGem = new AlchGem();

        AlchApple = new AlchApple(6, 0.6f, false);
        Alchbread = new Alchbread(1, 0.6f, false);

        trolliumIngot = new TrolliumIngot().setUnlocalizedName(Info.TROLLIUM_INGOT_NAME);

        elementalStick = new ElementalStick().setUnlocalizedName(Info.ELEMENTAL_STICK_NAME);

        alchChisel = new AlchChisel().setUnlocalizedName(Info.ALCHAPPLE_NAME);
        alchChisel.setContainerItem(alchChisel);
    }

    public static void registerItems() {
        GameRegistry.registerItem(alchCoal, "Alchemical Coal");
        GameRegistry.registerItem(alchGem, "Gems");

        GameRegistry.registerItem(AlchApple, Info.ALCHAPPLE_NAME);
        GameRegistry.registerItem(Alchbread, "Alchemical Bread");

        GameRegistry.registerItem(trolliumIngot, Info.TROLLIUM_INGOT_NAME);

        GameRegistry.registerItem(elementalStick, Info.ELEMENTAL_STICK_NAME);

        GameRegistry.registerItem(alchChisel, Info.ALCHCHISEL_NAME);
    }

    public static void registerRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.elementalStick, 2),new Object[]{
               new ItemStack(BlockHandler.elementalPlanks), new ItemStack(BlockHandler.elementalPlanks)
        });
        GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.alchChisel), "  E", "IE ", "TT ", 'E', ItemHandler.elementalStick, 'I', Items.iron_ingot, 'T', ItemHandler.trolliumIngot);

        /*GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.dullGem), new Object[]{
                new ItemStack(ItemHandler.alchChisel), new ItemStack(BlockHandler.alchStone)
        });*/
    }

    private ItemHandler() {

    }
}
