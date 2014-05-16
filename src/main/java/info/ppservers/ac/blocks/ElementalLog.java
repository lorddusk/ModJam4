package info.ppservers.ac.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import info.ppservers.ac.ModInformation;
import info.ppservers.ac.blocks.BlockHandler;

public class ElementalLog extends Block{
	
	public ElementalLog() {
		super(Material.wood);
		this.setStepSound(soundTypeWood);
		this.setBlockName(Info.ELEMENTALLOG_NAME);
		//this.setBlockTextureName(ModInformation.BLOCK_TEXTURE_LOC + ".png");
		
		
		}
	
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.blockIcon = p_149651_1_.registerIcon("alchcom:ElementalLog");
	}

}

