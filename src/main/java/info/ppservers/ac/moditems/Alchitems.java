package info.ppservers.ac.moditems;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class Alchitems {

	
	public static Item AlchApple;
	public static Item Alchbread;
	public static Item AlchCarrot;
	public static Item AlchPototatoes;
	public static Item AlchSugar;
	public static Item AlchWheat;
	
	public static void init(){
		
		AlchApple = new AlchApple(6, 0.6f, false);
		
		
	}
	
	public static void RegisterItems(){
		GameRegistry.registerItem(AlchApple, ItemInfo.ALCHAPPLE_NAME);
		
		
	}
	
	
	
}
