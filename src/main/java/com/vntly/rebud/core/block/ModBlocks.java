package com.vntly.rebud.core.block;

import com.vntly.rebud.RebudMod;


import com.vntly.rebud.core.block.custom.CleaningTableBlock;
import com.vntly.rebud.core.block.entity.custom.CleaningTableBlockEntity;
import com.vntly.rebud.core.item.ModCreativeTab;
import com.vntly.rebud.core.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;


public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS =
			DeferredRegister.create(ForgeRegistries.BLOCKS, RebudMod.MODID);


	//  BLOCKS
	public static final RegistryObject<Block> SCAPOLITE_ORE = registerBlock("scapolite_ore",
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE)
					.strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.REBUD);
	public static final RegistryObject<Block> DEEPSLATE_SCAPOLITE_ORE = registerBlock("deepslate_scapolite_ore",
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE)
					.strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.REBUD);
	public static final RegistryObject<Block> SCAPOLITE_BLOCK = registerBlock("scapolite_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6F).requiresCorrectToolForDrops()), ModCreativeTab.REBUD);

	public static final RegistryObject<Block> CLEANING_TABLE = registerBlock("cleaning_table",
			() -> new CleaningTableBlock(BlockBehaviour.Properties.copy(Blocks.FURNACE)),
			ModCreativeTab.REBUD );
	//  PRIVATE STATICS

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn, tab);
		return toReturn;
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
																			CreativeModeTab tab) {
		return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().tab(tab)));
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}


