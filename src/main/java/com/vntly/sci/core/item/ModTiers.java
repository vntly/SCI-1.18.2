package com.vntly.sci.core.item;

import com.vntly.sci.core.util.ModTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier REINFORCED_COPPER = new ForgeTier(1, 70, 1f,
            0f, 1, ModTags.Blocks.DIGGER_VALUABLES,
            () -> Ingredient.of(ModItems.REINFORCED_COPPER.get()));
    public static final ForgeTier COPPER = new ForgeTier(0, 25, 1f,
            0f, 1, ModTags.Blocks.DIGGER_VALUABLES,
            () -> Ingredient.of(Items.COPPER_INGOT));
}