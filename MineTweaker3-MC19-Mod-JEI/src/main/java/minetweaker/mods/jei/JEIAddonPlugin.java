package minetweaker.mods.jei;

import mezz.jei.api.*;

import javax.annotation.Nonnull;

@mezz.jei.api.JEIPlugin
public class JEIAddonPlugin implements IModPlugin{

	public static IJeiHelpers jeiHelpers;
	public static IItemRegistry itemRegistry;
	public static IRecipeRegistry recipeRegistry;



	@Override
	public void register(IModRegistry registry) {

		this.jeiHelpers = registry.getJeiHelpers();
		this.itemRegistry = registry.getItemRegistry();

	}


	@Override
	public void onRuntimeAvailable(@Nonnull IJeiRuntime iJeiRuntime){
        this.recipeRegistry = iJeiRuntime.getRecipeRegistry();
    }

}
