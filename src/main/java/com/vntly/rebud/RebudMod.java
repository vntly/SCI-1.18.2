package com.vntly.rebud;

import com.vntly.rebud.core.block.ModBlocks;
import com.vntly.rebud.core.item.ModItems;

import com.vntly.rebud.core.world.feature.ModConfiguredFeatures;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = RebudMod.MODID) //INCREASE
public class RebudMod {
	public static final String MODID = "rebud";

	public RebudMod() {
		var room = FMLJavaModLoadingContext.get().getModEventBus();

		ModBlocks.BLOCKS.register(room);
		ModItems.ITEMS.register(room);
	}
}
