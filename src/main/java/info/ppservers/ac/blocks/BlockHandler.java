package info.ppservers.ac.blocks;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.AlchemicalCombination;
import info.ppservers.ac.blocks.renders.RenderAlchFurnace;
import info.ppservers.ac.tileentity.TileEntityAlchFurnace;
import net.minecraft.block.Block;

/**
 * Created by Tim on 5/15/2014.
 */
public abstract class BlockHandler {

    public static Block furnace;
    public static Block furnaceBurning;

    public static void init() {
        furnace = new AlchFurnace(false).setBlockName("alchFurnace").setCreativeTab(AlchemicalCombination.ACTab);
        furnaceBurning = new AlchFurnace(true).setBlockName("alchFurnaceBurning").setLightLevel(0.875F);
    }

    public static void registerBlocks() {
        GameRegistry.registerBlock(furnace,Info.FURNACE_NAME);
        GameRegistry.registerBlock(furnaceBurning,Info.FURNACE_NAME+"Burning");
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityAlchFurnace.class, Info.FURNACE_KEY);
    }

    public static void registerRenders(){
        RenderingRegistry.registerBlockHandler(2108, RenderAlchFurnace.INSTANCE);
    }

    public static void registerRecipes() {
    }

    private BlockHandler() {
    }

    ;
}
