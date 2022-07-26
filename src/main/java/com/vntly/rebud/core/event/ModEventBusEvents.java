package com.vntly.rebud.core.event;

import com.vntly.rebud.RebudMod;
import com.vntly.rebud.core.event.loot.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = RebudMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new ShavingsDropFromStoneAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(RebudMod.MODID,"shavings_from_stone")),
                new PearDropAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(RebudMod.MODID,"pear_drop")),
                new ShavingsDropFromStoneAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(RebudMod.MODID,"shavings_from_deepslate"))


        );
    }
}