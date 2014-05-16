package info.ppservers.ac.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created by Tim on 5/15/2014.
 */
public class AlchCoalBlock extends Block {

    protected AlchCoalBlock() {
        super(Material.iron);
        this.setStepSound(soundTypeStone);
        this.setBlockName("alchCoalBlock");
    }

    public void registerBlockIcons(IIconRegister register){
        this.blockIcon = register.registerIcon("alchCoalBlock");
    }
}
