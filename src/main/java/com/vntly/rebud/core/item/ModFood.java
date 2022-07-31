package com.vntly.rebud.core.item;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import javax.management.MalformedObjectNameException;

public class ModFood {
    public static final FoodProperties FRIED_EGG = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.7F).build();
    public static final FoodProperties PEAR = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F).build();
    public static final FoodProperties LARD = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.REGENERATION, 30, 1), 0.6f).meat().build();
    public static final FoodProperties LARD_BREAD = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.9F).effect(new MobEffectInstance(MobEffects.HEAL, 100, 1), 1f).build();
}

