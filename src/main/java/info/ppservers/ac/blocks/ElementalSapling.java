package info.ppservers.ac.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;

public class ElementalSapling extends BlockSapling{
	public ElementalSapling() {
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
	}
	
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon("ac:ElementalSapling");
	}	
	
}
