package com.vntly.rebud.core.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

public class ModPlacedFeatures {

     public static final Holder<PlacedFeature> SCAPOLITE_ORE_PLACED = PlacementUtils.register("scapolite_ore_placed",
      ModConfiguredFeatures.SCAPOLITE_ORE_ALL, ModOrePlacement.commonOrePlacement(3, // VeinsPerChunk
        HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(10))));
}