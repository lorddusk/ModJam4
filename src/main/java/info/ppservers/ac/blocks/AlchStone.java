package info.ppservers.ac.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created by Tim on 5/16/2014.
 */
public class AlchStone extends Block{
    protected AlchStone() {
        super(Material.rock);
        
    }
    
    public void registerBlockIcons(IIconRegister register){
        this.blockIcon = register.registerIcon("alchcom:alchstone");
    }
}
