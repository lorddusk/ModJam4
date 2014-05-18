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
public abstract class ItemHandler {

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
        waterGem = new WaterGem().setUnlocalizedName(Info.WATERGEM_NAME);
        waterGem.setContainerItem(waterGem);

        //Food
        AlchApple = new AlchApple(6, 0.6f, false);
        Alchbread = new Alchbread(1, 0.6f, false);

        trolliumIngot = new TrolliumIngot().setUnlocalizedName(Info.TROLLIUM_INGOT_NAME);

        elementalStick = new ElementalStick().setUnlocalizedName(Info.ELEMENTAL_STICK_NAME);

        alchChisel = new AlchChisel().setUnlocalizedName(Info.ALCHAPPLE_NAME);
        alchChisel.setContainerItem(alchChisel);
    }

    public static void registerItems() {
        GameRegistry.registerItem(alchCoal, Info.COAL_UNLOCALIZED_NAME);

        //Gems
        GameRegistry.registerItem(alchGem, Info.GEM_KEY);
        GameRegistry.registerItem(waterGem, Info.WATERGEM_NAME);

        //Food
        GameRegistry.registerItem(AlchApple, Info.ALCHAPPLE_NAME);
        GameRegistry.registerItem(Alchbread, Info.ALCHBREAD_NAME);

        GameRegistry.registerItem(trolliumIngot, Info.TROLLIUM_INGOT_NAME);

        GameRegistry.registerItem(elementalStick, Info.ELEMENTAL_STICK_NAME);

        GameRegistry.registerItem(alchChisel, Info.ALCHCHISEL_NAME);
    }

    public static void registerRecipes() {
        //Elemental Sticks
        GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.elementalStick, 2),new Object[]{
               new ItemStack(BlockHandler.elementalPlanks), new ItemStack(BlockHandler.elementalPlanks)
        });

        //Alchemical Chisel
        GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.alchChisel), "  E", "IE ", "TT ", 'E', ItemHandler.elementalStick, 'I', Items.iron_ingot, 'T', ItemHandler.trolliumIngot);
    }

    private ItemHandler() {

    }
}
