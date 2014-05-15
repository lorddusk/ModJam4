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

	private Object healAmount;
	private Object isWolfsFavoriteMeat;
	private Object saturationModifier;

	public AlchApple(int healamount, float f, boolean Wolfmeat) {
		super(healamount, Wolfmeat);
		this.setCreativeTab(AlchemicalCombination.ACTab);
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setMaxStackSize(16);
		this.setAlwaysEdible();
		this.setUnlocalizedName("Alchapple");
		this.setMaxDamage(3);
		 this.healAmount = healamount;
	        this.isWolfsFavoriteMeat = Wolfmeat; 
	        this.saturationModifier = f;
	}
	
	 public EnumRarity getRarity(ItemStack stack){
		return EnumRarity.epic;
	 
	 }
	 
	  @Override
	public  ItemStack onEaten(ItemStack stack, World world,
			EntityPlayer player) {
		if(!world.isRemote){
			player.getFoodStats().func_151686_a(this, stack);
			player.addPotionEffect(new PotionEffect(Potion.heal.id, 50 , 1));
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id ,50,1));
			 //stack.setItemDamage(-1);
			 onFoodEaten(stack, world, player);
		 }
//		else
//        {
//            super.onFoodEaten(stack, world, player);
//
//        }
		return stack;}
	
	  
	  @Override
	protected void onFoodEaten(ItemStack stack, World world,
			EntityPlayer player){ 
	  int D = stack.getItemDamage();
	  
		  if(D > 0){
				 stack.setItemDamage(D --);
				 if(D == 0){
					 stack.stackSize --;
				 }
			 }
		 // else{ stack.stackSize --;}
		super.onFoodEaten(stack, world, player);
	}

}

