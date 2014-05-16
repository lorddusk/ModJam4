package info.ppservers.ac.blocks;

import info.ppservers.ac.world.ElementalTreeGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

public class ElementalSapling extends BlockSapling{
	public ElementalSapling() {
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
	}
	
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon("ac:ElementalSapling");
	}	
	
	public void func_149878_d(World world, int x, int y, int z, Random random) {
		int meta = world.getBlockMetadata(x, y, z);
		Object obj = null;
		int rnd = random.nextInt(8);
		
		if (obj == null) {
			obj = new ElementalTreeGen(new ElementalLog(), new ElementalLeaves(), false, 6, 0, 0, false);
		}
		if (obj != null) {
			world.setBlockToAir(x, y, z);
			if (!((WorldGenerator)obj).generate(world, random, x, y, z)) {
				world.setBlock(x, y, z, this, meta, 2);
			}
	}
}
}
