package info.ppservers.ac.liquid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.AlchemicalCombination;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

/**
 * Created by Tim on 5/18/2014.
 */
public class AlchFluid extends BlockFluidClassic {

    public String texture;
    boolean alpha;
    public IIcon stillIcon;
    public IIcon flowIcon;

    public AlchFluid(Fluid fluid, Material material, String texture) {
        super(fluid, material);
        this.texture = texture;
        this.setCreativeTab(AlchemicalCombination.ACTab);
    }

    public AlchFluid(Fluid fluid, Material material, String texture, boolean alpha) {
        this(fluid, material, texture);
        this.alpha = alpha;
    }

    @Override
    public int getRenderBlockPass() {
        return alpha ? 1 : 0;
    }

    @Override
    public void registerBlockIcons(IIconRegister register){
        stillIcon = register.registerIcon("alchcom:"+texture);
        flowIcon = register.registerIcon("alchcom:"+texture+"_flow");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        if(side==0 || side==1){
            return stillIcon;
        }
        return flowIcon;
    }
}
