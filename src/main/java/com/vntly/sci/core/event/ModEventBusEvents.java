package com.vntly.sci.core.event;

import com.vntly.sci.SCIMod;
import com.vntly.sci.core.event.loot.*;
import com.vntly.sci.core.recipe.CleaningTableRecipe;
import com.vntly.sci.core.recipe.MixingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = SCIMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new ShavingsDropFromStoneAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(SCIMod.MODID,"shavings_from_stone")),
                new ShavingsDropFromStoneAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(SCIMod.MODID,"shavings_from_deepslate")),
                new PearDropAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(SCIMod.MODID,"pear_drop")),
                new LardDropAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(SCIMod.MODID,"lard_drop"))
        );
    }
    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, CleaningTableRecipe.Type.ID, CleaningTableRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, MixingRecipe.Type.ID, MixingRecipe.Type.INSTANCE);
    }
}