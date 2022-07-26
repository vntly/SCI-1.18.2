package com.vntly.sci.core.screen;

import com.vntly.sci.SCIMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, SCIMod.MODID);

    public static final RegistryObject<MenuType<CleaningTableMenu>> CLEANING_TABLE_MENU =
            registerMenuType(CleaningTableMenu::new, "cleaning_table_menu");
    public static final RegistryObject<MenuType<MixingMenu>> MIXING_MENU =
                registerMenuType(MixingMenu::new, "mixing_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
