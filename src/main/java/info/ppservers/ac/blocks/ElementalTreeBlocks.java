package info.ppservers.ac.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import info.ppservers.ac.blocks.BlockHandler;

public class ElementalTreeBlocks extends Block{
	
	public void elementalLog(){
		super(Material.wood);
		this.setStepSound(soundTypeWood);
	}

}
