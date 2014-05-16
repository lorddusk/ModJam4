package info.ppservers.ac.blocks.alchinfuser;

import info.ppservers.ac.items.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 5/16/2014.
 */
public class AlchInfuserRecipes {

    private static AlchInfuserRecipes alchInfuserRecipe = null;
    private List<AlchInfuserRecipe> infuserRecipes;

    private AlchInfuserRecipes(){
        infuserRecipes = new ArrayList<AlchInfuserRecipe>();
    }

    public static AlchInfuserRecipes getInstance(){
        if(alchInfuserRecipe == null){
            alchInfuserRecipe = new AlchInfuserRecipes();
            alchInfuserRecipe.init();
        }
        return alchInfuserRecipe;
    }

    private void init(){
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,1), new ItemStack(Items.redstone), new ItemStack(ItemHandler.alchGem,1,0));
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,1), new ItemStack(Items.dye,1,4), new ItemStack(ItemHandler.alchGem,1,0));
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,2), new ItemStack(Items.quartz), new ItemStack(ItemHandler.alchGem,1,1));
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,3), new ItemStack(Items.diamond), new ItemStack(ItemHandler.alchGem,1,2));
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,3), new ItemStack(Items.emerald), new ItemStack(ItemHandler.alchGem,1,2));
    }

    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputGem){
        addRecipe(new AlchInfuserRecipe(recipeOutput,recipeInputStack,recipeInputGem));
    }

    public void addRecipe(AlchInfuserRecipe recipe){
        if(!infuserRecipes.contains(recipe)){
            infuserRecipes.add(recipe);
            System.out.println(recipe.toString());
        }
        else
        {
            System.out.println(String.format("Attempted to add Recipe : '%s' but already exists", recipe));
        }
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputGem){
        for(AlchInfuserRecipe recipe : infuserRecipes){
            if(recipe.matches(recipeInputStack,recipeInputGem)){
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public AlchInfuserRecipe getRecipe(ItemStack recipeInputStack, ItemStack recipeInputGem){
        for(AlchInfuserRecipe recipe : infuserRecipes){
            if(recipe.matches(recipeInputStack,recipeInputGem)){
                return recipe;
            }
        }
        return null;
    }

    public List<AlchInfuserRecipe> getInfuserRecipes(){
        return infuserRecipes;
    }
}
