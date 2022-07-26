package com.vntly.rebud.core.item;

import com.vntly.rebud.core.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTab {
    public static final CreativeModeTab REBUD = new CreativeModeTab("rebudtab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModBlocks.SCAPOLITE_BLOCK.get());
        }
    };
}

