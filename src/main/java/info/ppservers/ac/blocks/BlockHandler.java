package info.ppservers.ac.blocks;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.AlchemicalCombination;
import info.ppservers.ac.blocks.alchfurnace.AlchFurnace;
import info.ppservers.ac.blocks.alchfurnace.RenderAlchFurnace;
import info.ppservers.ac.blocks.alchfurnace.TileEntityAlchFurnace;
import info.ppservers.ac.blocks.alchinfuser.AlchInfuser;
import info.ppservers.ac.blocks.alchinfuser.RenderAlchInfuser;
import info.ppservers.ac.blocks.alchinfuser.TileEntityAlchInfuser;
import info.ppservers.ac.items.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 5/15/2014.
 */
public abstract class BlockHandler {

    public static Block furnace;
    public static Block furnaceBurning;

    public static Block alchCoalBlock;

    public static Block alchStone;

    public static Block elementalLog;
    public static ElementalLeaves elementalLeaves;
    public static Block elementalPlanks;
    public static ElementalSapling elementalSapling;

    public static Block trolliumOre;

    public static Block infuser;
    public static Block infuserBurning;

    public static void init() {
        furnace = new AlchFurnace(false).setBlockName("alchFurnace").setCreativeTab(AlchemicalCombination.ACTab);
        furnaceBurning = new AlchFurnace(true).setBlockName("alchFurnaceBurning").setLightLevel(0.875F);

        infuser = new AlchInfuser(false).setBlockName("alchInfuser").setCreativeTab(AlchemicalCombination.ACTab);
        infuserBurning = new AlchInfuser(true).setBlockName("alchInfuserBurning").setLightLevel(0.875F);

        alchCoalBlock = new AlchCoalBlock().setBlockName("alchCoalBlock").setLightLevel(0.875F).setCreativeTab(AlchemicalCombination.ACTab);

        alchStone = new AlchStone().setBlockName("alchStone").setCreativeTab(AlchemicalCombination.ACTab);

        elementalLog = new ElementalLog().setBlockName("elementalLog").setCreativeTab(AlchemicalCombination.ACTab);
        elementalLeaves = (ElementalLeaves) new ElementalLeaves().setBlockName("elementalLeaves").setCreativeTab(AlchemicalCombination.ACTab);
        elementalPlanks = new ElementalPlanks().setBlockName("elementalPlank").setCreativeTab(AlchemicalCombination.ACTab);
        elementalSapling = (ElementalSapling) new ElementalSapling().setBlockName("elementalSapling");

        trolliumOre = new TrolliumOre().setBlockName("trolliumOre").setCreativeTab(AlchemicalCombination.ACTab);
    }

    public static void registerBlocks() {
        GameRegistry.registerBlock(furnace, Info.FURNACE_NAME);
        GameRegistry.registerBlock(furnaceBurning, Info.FURNACE_NAME + "Burning");

        GameRegistry.registerBlock(infuser, Info.INFUSER_NAME);
        GameRegistry.registerBlock(infuserBurning, Info.INFUSER_NAME + "Burning");

        GameRegistry.registerBlock(alchCoalBlock, Info.ALCHCOAL_NAME);

        GameRegistry.registerBlock(alchStone, Info.ALCHSTONE_NAME);

        GameRegistry.registerBlock(elementalLog, Info.ELEMENTALLOG_NAME);
        GameRegistry.registerBlock(elementalLeaves, Info.ELEMENTALLEAVES_NAME);
        GameRegistry.registerBlock(elementalPlanks, Info.ELEMENTALPLANKS_NAME);
        GameRegistry.registerBlock(elementalSapling, "ElementalSapling");

        GameRegistry.registerBlock(trolliumOre, Info.TROLLIUM_ORE_NAME);
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityAlchFurnace.class, Info.FURNACE_KEY);
        GameRegistry.registerTileEntity(TileEntityAlchInfuser.class, Info.INFUSER_KEY);
    }

    public static void registerRenders() {
        RenderingRegistry.registerBlockHandler(2108, RenderAlchFurnace.INSTANCE);
        RenderingRegistry.registerBlockHandler(2109, RenderAlchInfuser.INSTANCE);
    }

    public static void registerRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.elementalPlanks, 4), new Object[]{
                new ItemStack(BlockHandler.elementalLog)
        });
        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.alchCoalBlock), "CCC", "CCC", "CCC", 'C', ItemHandler.alchCoal);

        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.furnace), "EEE", "EFE", "EEE", 'E', BlockHandler.elementalLog, 'F', Blocks.furnace);

        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.infuser), "SSS","EAE","SSS",'S',Blocks.stone_slab,'E',BlockHandler.elementalLog,'A',BlockHandler.furnace);

        GameRegistry.addSmelting(new ItemStack(BlockHandler.elementalLog), new ItemStack(ItemHandler.alchCoal), 0.1F);
    }

    private BlockHandler() {
    }

    ;
}
