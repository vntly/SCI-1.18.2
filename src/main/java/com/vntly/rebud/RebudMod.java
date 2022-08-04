package com.vntly.rebud;

import com.vntly.rebud.core.block.ModBlocks;
import com.vntly.rebud.core.block.entity.ModBlockEntities;
import com.vntly.rebud.core.item.ModItems;

import com.vntly.rebud.core.recipe.ModRecipes;
import com.vntly.rebud.core.screen.CleaningTableScreen;
import com.vntly.rebud.core.screen.ModMenuTypes;
import com.vntly.rebud.core.world.feature.ModConfiguredFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = RebudMod.MODID) //INCREASE
public class RebudMod {
	public static final String MODID = "rebud";

	public RebudMod() {
		var eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModBlocks.register(eventBus);
		ModItems.register(eventBus);

		ModBlockEntities.register(eventBus);
		ModMenuTypes.register(eventBus);

		ModRecipes.register(eventBus);

		eventBus.addListener(this::clientSetup);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void clientSetup(final FMLClientSetupEvent event){

		MenuScreens.register(ModMenuTypes.CLEANING_TABLE_MENU.get(), CleaningTableScreen::new);

	}
}
