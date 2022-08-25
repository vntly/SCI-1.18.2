package com.vntly.sci;

import com.vntly.sci.core.block.ModBlocks;
import com.vntly.sci.core.block.entity.ModBlockEntities;
import com.vntly.sci.core.item.ModItems;

import com.vntly.sci.core.painting.ModPaintings;
import com.vntly.sci.core.recipe.ModRecipes;
import com.vntly.sci.core.screen.CleaningTableScreen;
import com.vntly.sci.core.screen.MixingScreen;
import com.vntly.sci.core.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = SCIMod.MODID) //INCREASE
public class SCIMod {
	public static final String MODID = "sci";

	public SCIMod() {
		var eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModBlocks.register(eventBus);
		ModItems.register(eventBus);

		ModBlockEntities.register(eventBus);
		ModMenuTypes.register(eventBus);

		ModPaintings.register(eventBus);

		ModRecipes.register(eventBus);

		eventBus.addListener(this::clientSetup);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void clientSetup(final FMLClientSetupEvent event){

		MenuScreens.register(ModMenuTypes.CLEANING_TABLE_MENU.get(), CleaningTableScreen::new);
		MenuScreens.register(ModMenuTypes.MIXING_MENU.get(), MixingScreen::new);

	}
}
