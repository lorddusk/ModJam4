package info.ppservers.ac.moditems;

import info.ppservers.ac.AlchemicalCombination;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class AlchApple extends ItemFood{

	public AlchApple(int healammount, boolean Wolfmeat) {
		super(healammount, Wolfmeat);
		this.setCreativeTab(AlchemicalCombination.ACTab);
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setMaxStackSize(16);
		this.setAlwaysEdible();
		this.setUnlocalizedName("Alchapple");
		this.setMaxDamage(3);
	}
	
	 public EnumRarity getRarity(ItemStack stack){
		return EnumRarity.epic;
	 
	 }
	 
	  @Override
	public  ItemStack onEaten(ItemStack stack, World world,
			EntityPlayer player) {
		if(!world.isRemote){
			player.addPotionEffect(new PotionEffect(Potion.heal.id, 50 , 1));
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id ,50,1));
			 stack.setItemDamage(stack.getItemDamage() -1);
			 if(stack.getItemDamage() == 0){
				 stack.stackSize --;
			 }
		}
		else
        {
            super.onFoodEaten(stack, world, player);
            stack.setItemDamage(stack.getItemDamage() -1);
            if(stack.getItemDamage() == 0){
				 stack.stackSize --;
			 }
        }
		return stack;
	}

}
