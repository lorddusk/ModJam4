package info.ppservers.ac.items;

import java.util.List;

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
		this.setCreativeTab(AlchemicalCombination.ACTab );
		this.setMaxStackSize(16);
		this.setUnlocalizedName("Alchapple");
		this.setMaxDamage(3);
		 this.healAmount = healamount;
	        this.isWolfsFavoriteMeat = Wolfmeat; 
	        this.saturationModifier = f;
	}
	
	 public EnumRarity getRarity(ItemStack stack){
		return EnumRarity.epic;
	 
	 }
	 
	 public boolean hasEffect(ItemStack par1ItemStack)
	    {
	        return true;
	    }
	 
	  @Override
	public  ItemStack onEaten(ItemStack stack, World world,
			EntityPlayer player) {
		 
		if(!world.isRemote){
			player.getFoodStats().func_151686_a(this, stack);
			player.addPotionEffect(new PotionEffect(Potion.heal.id, 50 , 1));
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id ,50,1));
			 
			 onFoodEaten(stack, world, player);
		 }
		return stack;
		}
	
	  
	  @Override
	protected void onFoodEaten(ItemStack stack, World world,
			EntityPlayer player){ 
	  int D = stack.getItemDamage();
	  
		  if(D > 0){
				 stack.setItemDamage(D --);
				 			 }
		  else{ stack.stackSize --;}
		super.onFoodEaten(stack, world, player);
	}
	  
	  @Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		  
		par3List.add("Eating this will ");
		par3List.add("make you feel");
		par3List.add("balanced");
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}
	  
}

