package info.ppservers.ac.liquid;

import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.items.FilledBucket;
import info.ppservers.ac.items.ItemHandler;
import mantle.blocks.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Tim on 5/19/2014.
 */
public abstract class FluidHandler {

    public static Fluid mojoFluid;
    public static Block mojo;

    public static Fluid[] fluids = new Fluid[1];
    public static Block[] fluidBlocks = new Block[1];

    public static void init() {
        mojoFluid = new Fluid("mojo");
        if (!FluidRegistry.registerFluid(mojoFluid)) {
            mojoFluid = FluidRegistry.getFluid("mojo");
        }

        mojo = new MojoWaterBlock(mojoFluid, Material.water, "mojoWater").setBlockName("liquid.mojo");
        GameRegistry.registerBlock(mojo, "liquid.mojo");
        mojoFluid.setBlock(mojo).setDensity(3000).setViscosity(6000).setTemperature(1300);
        FluidContainerRegistry.registerFluidContainer(new FluidContainerRegistry.FluidContainerData(new FluidStack(mojoFluid, 1000), new ItemStack(ItemHandler.buckets, 1, 0), new ItemStack(Items.bucket)));

        fluids = new Fluid[]{
                mojoFluid
        };

        fluidBlocks = new Block[]{
                mojo
        };
    }

    private FluidHandler() {

    }

}
