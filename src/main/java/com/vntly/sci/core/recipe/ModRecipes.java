package com.vntly.sci.core.recipe;

import com.vntly.sci.SCIMod;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SCIMod.MODID);

    public static final RegistryObject<RecipeSerializer<CleaningTableRecipe>> CLEANING_SERIALIZERS =
            SERIALIZERS.register("cleaning", () -> CleaningTableRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<MixingRecipe>> MIXING_SERIALIZERS =
            SERIALIZERS.register("mixing", () -> MixingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}