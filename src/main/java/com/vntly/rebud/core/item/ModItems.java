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

	// SHAVINGS Iron ore shavings

	public static final RegistryObject<Item> IRON_SHAVINGS = ITEMS.register("iron_shavings",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> GOLD_SHAVINGS = ITEMS.register("gold_shavings",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));
	public static final RegistryObject<Item> COPPER_SHAVINGS = ITEMS.register("copper_shavings",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));


	// Fossil ores

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


	// ENDER STAFF

	public static final RegistryObject<Item> RENDER_TEST_ITEM = ITEMS.register("pearl_oo",
			() -> new PearlOOItem(new Item.Properties().tab(ModCreativeTab.REBUD).durability(64)));

	// OTHER

	public static final RegistryObject<Item> CARBON = ITEMS.register("carbon",
			() -> new Item(new Item.Properties().tab(ModCreativeTab.REBUD)));

	public static final RegistryObject<Item> DIGGER_HUMMER = ITEMS.register("digger_hummer",
			() -> new DiggingHummerItem(Tiers.IRON, 2, 2f, new Item.Properties().tab(ModCreativeTab.REBUD)));


	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}


