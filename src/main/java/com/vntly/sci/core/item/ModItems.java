package com.vntly.sci.core.item;

import com.vntly.sci.SCIMod;
import com.vntly.sci.core.item.custom.digging_hummer.DiggingItem;
import com.vntly.sci.core.item.custom.pearloo.PearlOOItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SCIMod.MODID);


	/** SCAPOLITE **/

	public static final RegistryObject<Item> SCAPOLITE_DUST = ITEMS.register("scapolite_dust",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));



	public static final RegistryObject<Item> SCAPOLITE_RAW = ITEMS.register("scapolite_raw",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));

	public static final RegistryObject<Item> SCAPOLITE = ITEMS.register("scapolite",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));

	public static final RegistryObject<Item> SCAPOLITE_RUBY = ITEMS.register("scapolite_ruby",
    		() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI).stacksTo(16)));

	/** SHAVINGS **/

	public static final RegistryObject<Item> SHAVINGS_IRON = ITEMS.register("shavings_iron",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> SHAVINGS_GOLD = ITEMS.register("shavings_gold",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> SHAVINGS_COPPER = ITEMS.register("shavings_copper",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));


	/** FOSSILS **/

	public static final RegistryObject<Item> FOSSIL_IRON = ITEMS.register("fossil_iron",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> FOSSIL_GOLD = ITEMS.register("fossil_gold",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> FOSSIL_COPPER = ITEMS.register("fossil_copper",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> FOSSIL_DIAMOND = ITEMS.register("fossil_diamond",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> FOSSIL_EMERALD = ITEMS.register("fossil_emerald",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));

	// Deepslate
	public static final RegistryObject<Item> DEEPSLATE_FOSSIL_IRON = ITEMS.register("deepslate_fossil_iron",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> DEEPSLATE_FOSSIL_GOLD = ITEMS.register("deepslate_fossil_gold",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> DEEPSLATE_FOSSIL_COPPER = ITEMS.register("deepslate_fossil_copper",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> DEEPSLATE_FOSSIL_DIAMOND = ITEMS.register("deepslate_fossil_diamond",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> DEEPSLATE_FOSSIL_EMERALD = ITEMS.register("deepslate_fossil_emerald",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));


	/** FOOD **/

	public static final RegistryObject<Item> FRIED_EGG = ITEMS.register("food_fried_egg",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI).food(ModFood.FRIED_EGG)));

	public static final RegistryObject<Item> PEAR = ITEMS.register("food_pear",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI).food(ModFood.PEAR)));

	public static final RegistryObject<Item> LARD = ITEMS.register("food_lard",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI).food(ModFood.LARD)));

	public static final RegistryObject<Item> LARD_BREAD = ITEMS.register("food_lard_bread",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI).food(ModFood.LARD_BREAD)));



	/** OTHER **/

	public static final RegistryObject<Item> CARBON = ITEMS.register("carbon",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> PEBBLE = ITEMS.register("pebble",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> PEBBLE_DEEPSLATE = ITEMS.register("pebble_deepslate",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));

	public static final RegistryObject<Item> REINFORCED_COPPER= ITEMS.register("reinforced_copper",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));
	public static final RegistryObject<Item> OBSIDIAN_SHARD= ITEMS.register("obsidian_shard",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI)));


	/** TOOLS **/
	// CHISELS
	public static final RegistryObject<Item> CHISEL_FLINT= ITEMS.register("chisel_flint",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI).durability(32)));

	public static final RegistryObject<Item> CHISEL_COPPER= ITEMS.register("chisel_copper",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI).durability(96)));

	public static final RegistryObject<Item> CHISEL_REINFORCED_COPPER= ITEMS.register("chisel_reinforced_copper",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.SCI).durability(256)));



	// DIGGERS
	public static final RegistryObject<Item> DIGGER = ITEMS.register("digger",
			() -> new DiggingItem(ModTiers.COPPER, 0, 0.6f,
					new Item.Properties().tab(ModCreativeTab.SCI).durability(36)));

	public static final RegistryObject<DiggingItem> DIGGER_HUMMER = ITEMS.register("digger_hummer",
			() -> new DiggingItem(ModTiers.REINFORCED_COPPER, 1, 0.8f,
					new Item.Properties().tab(ModCreativeTab.SCI).durability(128)));




	/** ENDER STAFF **/
	public static final RegistryObject<Item> PEARL_OF_OPPORTUNITY = ITEMS.register("pearl_oo",
			() -> new PearlOOItem(new Item.Properties().tab(ModCreativeTab.SCI).durability(128)));


	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}


