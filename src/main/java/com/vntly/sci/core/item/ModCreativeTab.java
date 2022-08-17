package com.vntly.sci.core.item;

import com.vntly.sci.core.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTab {
    public static final CreativeModeTab SCI = new CreativeModeTab("scitab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModBlocks.SCAPOLITE_ORE.get());
        }
    };
}

