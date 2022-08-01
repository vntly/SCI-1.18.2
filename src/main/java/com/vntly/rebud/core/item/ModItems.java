package com.vntly.rebud.core.item;

import com.vntly.rebud.RebudMod;
import com.vntly.rebud.core.item.custom.digging_hummer.DiggingHummerItem;
import com.vntly.rebud.core.item.custom.pearloo.PearlOOItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RebudMod.MODID);


	// SCAPOLITE

	public static final RegistryObject<Item> SCAPOLITE_DUST = ITEMS.register("scapolite_dust",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));



	public static final RegistryObject<Item> SCAPOLITE_RAW = ITEMS.register("scapolite_raw",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));

	public static final RegistryObject<Item> SCAPOLITE = ITEMS.register("scapolite",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));

	public static final RegistryObject<Item> SCAPOLITE_RUBY = ITEMS.register("scapolite_ruby",
    		() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD).stacksTo(16)));

	// SHAVINGS

	public static final RegistryObject<Item> SHAVINGS_IRON = ITEMS.register("shavings_iron",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> SHAVINGS_GOLD = ITEMS.register("shavings_gold",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> SHAVINGS_COPPER = ITEMS.register("shavings_copper",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));


	// FOSSILS

	public static final RegistryObject<Item> FOSSIL_IRON = ITEMS.register("fossil_iron",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> FOSSIL_GOLD = ITEMS.register("fossil_gold",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> FOSSIL_COPPER = ITEMS.register("fossil_copper",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> FOSSIL_DIAMOND = ITEMS.register("fossil_diamond",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> FOSSIL_EMERALD = ITEMS.register("fossil_emerald",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));

	// FOOD

	public static final RegistryObject<Item> FRIED_EGG = ITEMS.register("food_fried_egg",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD).food(ModFood.FRIED_EGG)));

	public static final RegistryObject<Item> PEAR = ITEMS.register("food_pear",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD).food(ModFood.PEAR)));

	public static final RegistryObject<Item> LARD = ITEMS.register("food_lard",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD).food(ModFood.LARD)));
	public static final RegistryObject<Item> LARD_BREAD = ITEMS.register("food_lard_bread",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD).food(ModFood.LARD_BREAD)));



	// OTHER

	public static final RegistryObject<Item> CARBON = ITEMS.register("carbon",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> PEBBLE = ITEMS.register("pebble",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> REINFORCED_COPPER= ITEMS.register("reinforced_copper",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> PEBBLE_DEEPSLATE = ITEMS.register("pebble_deepslate",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));


	public static final RegistryObject<DiggingHummerItem> DIGGER_HUMMER = ITEMS.register("digger_hummer",
			() -> new DiggingHummerItem(Tiers.STONE, 2, 2f, new Item.Properties().tab(ModCreativeTab.REBUD).durability(12)));


	/* ENDER STAFF */
	public static final RegistryObject<Item> RENDER_TEST_ITEM = ITEMS.register("pearl_oo",
			() -> new PearlOOItem(new Item.Properties().tab(ModCreativeTab.REBUD).durability(128)));


	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}


