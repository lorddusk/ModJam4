package info.ppservers.ac.blocks;


import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.AlchemicalCombination;
import info.ppservers.ac.blocks.alchfurnace.AlchFurnace;
import info.ppservers.ac.blocks.alchfurnace.RenderAlchFurnace;
import info.ppservers.ac.blocks.alchfurnace.TileEntityAlchFurnace;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 5/15/2014.
 */
public class BlockHandler {
    public static Block furnace;
    public static Block furnaceBurning;

    public static Block alchCoalBlock;

    public static Block alchStone;

    public static Block elementalLog;
    public static Block elementalLeaves;
    public static Block elementalPlanks;

    public static void init() {
        furnace = new AlchFurnace(false).setBlockName("alchFurnace").setCreativeTab(AlchemicalCombination.ACTab);
        furnaceBurning = new AlchFurnace(true).setBlockName("alchFurnaceBurning").setLightLevel(0.875F);

        alchCoalBlock = new AlchCoalBlock().setLightLevel(0.875F).setCreativeTab(AlchemicalCombination.ACTab);

        alchStone = new AlchStone().setBlockName("alchStone").setCreativeTab(AlchemicalCombination.ACTab);

        elementalLog = new ElementalLog().setCreativeTab(AlchemicalCombination.ACTab);
        elementalLeaves = new ElementalLeaves().setCreativeTab(AlchemicalCombination.ACTab);
        elementalPlanks = new ElementalPlanks().setCreativeTab(AlchemicalCombination.ACTab);
    }


    public static void registerBlocks() {
        GameRegistry.registerBlock(furnace, Info.FURNACE_NAME);
        GameRegistry.registerBlock(furnaceBurning, Info.FURNACE_NAME + "Burning");

        GameRegistry.registerBlock(alchCoalBlock, Info.ALCHCOAL_NAME);

        GameRegistry.registerBlock(alchStone, Info.ALCHSTONE_NAME);

        GameRegistry.registerBlock(elementalLog, Info.ELEMENTALLOG_NAME);
        GameRegistry.registerBlock(elementalLeaves, Info.ELEMENTALLEAVES_NAME);
        GameRegistry.registerBlock(elementalPlanks, Info.ELEMENTALPLANKS_NAME);
    }

    public static void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntityAlchFurnace.class, Info.FURNACE_KEY);
    }

    public static void registerRenders() {
        RenderingRegistry.registerBlockHandler(2108, RenderAlchFurnace.INSTANCE);
    }

    public static void registerRecipes() {
        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.furnace), "eee", "efe", "eee", 'e', BlockHandler.elementalLog, 'f', Blocks.furnace);
    }

    private BlockHandler() {
    }

    ;

}
