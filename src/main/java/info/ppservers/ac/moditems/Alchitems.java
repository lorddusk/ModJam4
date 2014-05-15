package info.ppservers.ac.moditems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import cpw.mods.fml.common.registry.GameRegistry;

public class Alchitems {

	
	public static Item AlchApple;
	public static ItemFood Alchbread;
	public static Item AlchCarrot;
	public static Item AlchPototatoes;
	public static Item AlchSugar;
	public static Item AlchWheat;
	
	public static void init(){
		
		AlchApple = new AlchApple(6, 0.6f, false);
		Alchbread = new Alchbread(1, 0.6f, false);
		
		
	}
	
	public static void RegisterItems(){
		GameRegistry.registerItem(AlchApple, ItemInfo.ALCHAPPLE_NAME);
		
		
	}
	
	
	
}
