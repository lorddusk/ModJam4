package info.ppservers.ac.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import info.ppservers.ac.blocks.BlockHandler;

public class ElementalLeaves extends Block{
	
	public ElementalLeaves() {
		super(Material.wood);
		this.setStepSound(soundTypeWood);
		this.setBlockName(Info.ELEMENTALLEAVES_NAME);
	}
	
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.blockIcon = p_149651_1_.registerIcon("ac:ElementalLeaves");
	}	
}
