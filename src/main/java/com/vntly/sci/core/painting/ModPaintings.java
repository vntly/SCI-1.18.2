package com.vntly.sci.core.painting;

import com.vntly.sci.SCIMod;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<Motive> PAINTING_MOVTIES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, SCIMod.MODID);


    public static final RegistryObject<Motive> UKRAINE =
            PAINTING_MOVTIES.register("ukraine", () -> new Motive(64, 32));


    public static void register(IEventBus eventBus) {
        PAINTING_MOVTIES.register(eventBus);
    }
}