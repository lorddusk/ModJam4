package info.ppservers.ac.blocks.alchfurnace;

import info.ppservers.ac.blocks.BlockHandler;
import info.ppservers.ac.items.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.lang.Float;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Tim on 5/15/2014.
 */
public class AlchFurnaceRecipes {

    private static final AlchFurnaceRecipes smeltingBase = new AlchFurnaceRecipes();

    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();

    public static AlchFurnaceRecipes smelting() {
        return smeltingBase;
    }

    private AlchFurnaceRecipes() {
        this.addBlockSmelting(Blocks.stone, new ItemStack(BlockHandler.alchStone), 0.1F);
        this.addBlockSmelting(BlockHandler.trolliumOre, new ItemStack(ItemHandler.trolliumIngot), 0.375F);
    }

    public void addBlockSmelting(Block block, ItemStack itemStack, float xp) {
        this.addItemSmelting(Item.getItemFromBlock(block), itemStack, xp);
    }

    public void addItemSmelting(Item item, ItemStack itemStack, float xp) {
        this.addSmelting(new ItemStack(item, 1, 32767), itemStack, xp);
    }

    public void addSmelting(ItemStack input, ItemStack output, float xp) {
        this.smeltingList.put(input, output);
        this.experienceList.put(output, Float.valueOf(xp));
    }

    public ItemStack getSmeltingResult(ItemStack itemStack) {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }
            entry = (Entry) iterator.next();
        }
        while (!this.functionA(itemStack, (ItemStack) entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    private boolean functionA(ItemStack input, ItemStack output) {
        return output.getItem() == input.getItem() && (output.getItemDamage() == 32767 || output.getItemDamage() == input.getItemDamage());
    }

    public Map getSmeltingList() {
        return this.smeltingList;
    }

    public float getExperience(ItemStack itemStack) {
        float ret = itemStack.getItem().getSmeltingExperience(itemStack);
        if (ret != -1) {
            return ret;
        }

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return 0.0F;
            }
            entry = (Entry) iterator.next();
        }
        while (!this.functionA(itemStack, (ItemStack) entry.getKey()));

        return ((Float) entry.getValue()).floatValue();
    }
}
