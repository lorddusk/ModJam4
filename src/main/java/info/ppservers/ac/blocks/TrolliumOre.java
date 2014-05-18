package info.ppservers.ac.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created by Tim on 5/16/2014.
 */
public class TrolliumOre extends Block{
    protected TrolliumOre() {
        super(Material.rock);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("pickaxe",2);
    }

    public void registerBlockIcons(IIconRegister register){
        this.blockIcon = register.registerIcon("alchcom:trolliumOre");
    }

}
