package info.ppservers.ac;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by Tim on 5/15/2014.
 */
public class ACTab extends CreativeTabs{
    public ACTab(int id, String name){
        super(id,name);
    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem(){
        return Items.blaze_powder;
    }

    public String getTranslatedTabLabel(){
        return "Alchemical Combination";
    }
}
