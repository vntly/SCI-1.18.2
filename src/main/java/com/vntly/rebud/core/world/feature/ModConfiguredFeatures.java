package com.vntly.rebud.core.world.feature;

import com.vntly.rebud.core.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

import static net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.*;

public class ModConfiguredFeatures {
    public static final List<
            TargetBlockState> OVERWORLD_SCAPOLITE_ORES = List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SCAPOLITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SCAPOLITE_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> SCAPOLITE_ORE_ALL = FeatureUtils.register("scapolite_ore_all",
            Feature.ORE, new OreConfiguration(OVERWORLD_SCAPOLITE_ORES, 4));//4
}
