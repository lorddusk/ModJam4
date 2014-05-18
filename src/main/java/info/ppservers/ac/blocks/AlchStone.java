package info.ppservers.ac.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by Tim on 5/16/2014.
 */
public class AlchStone extends Block{
    protected AlchStone() {
        super(Material.rock);
        
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        return blockIcon;
    }

    public String getUnlocalizedName(ItemStack itemStack){
        return "alchStone";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register){
        this.blockIcon = register.registerIcon("alchcom:alchstone");
    }
}
