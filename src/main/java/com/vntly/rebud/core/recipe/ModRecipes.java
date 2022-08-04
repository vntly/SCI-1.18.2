package com.vntly.rebud.core.recipe;

import com.vntly.rebud.RebudMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RebudMod.MODID);

    public static final RegistryObject<RecipeSerializer<CleaningTableRecipe>> CLEANING_SERIALIZERS =
            SERIALIZERS.register("cleaning  ", () -> CleaningTableRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}