package info.ppservers.ac.items.gems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.AlchemicalCombination;
import info.ppservers.ac.items.Info;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by Tim on 5/18/2014.
 */
public class WaterGem extends Item {
    public WaterGem() {
        super();
        this.setCreativeTab(AlchemicalCombination.ACTab);
        this.setMaxStackSize(1);
        this.setMaxDamage(3);
        this.setUnlocalizedName("waterGem");
    }

    @SideOnly(Side.CLIENT)
    private IIcon waterGem;

    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register){
        waterGem = register.registerIcon("alchcom:"+Info.WATERGEM_ICON);
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack){
        return true;
    }

    @Override
    public boolean getShareTag(){
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack){
        ItemStack copiedStack = itemStack.copy();
        copiedStack.setItemDamage(copiedStack.getItemDamage() + 1);
        copiedStack.stackSize = 1;
        return copiedStack;
    }
}
