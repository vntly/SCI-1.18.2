package com.vntly.rebud.core.block.entity;

import com.vntly.rebud.RebudMod;
import com.vntly.rebud.core.block.ModBlocks;
import com.vntly.rebud.core.block.entity.custom.CleaningTableBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, RebudMod.MODID);

    public static final RegistryObject<BlockEntityType<CleaningTableBlockEntity>> CLEANING_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("cleaning_table_block_entity", () ->
                    BlockEntityType.Builder.of(CleaningTableBlockEntity::new,
                            ModBlocks.CLEANING_TABLE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}