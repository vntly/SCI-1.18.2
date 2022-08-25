package com.vntly.sci.core.screen.slot;

import com.vntly.sci.core.screen.MixingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class MixingFuelSlot  extends SlotItemHandler {
    private final MixingMenu menu;

    public MixingFuelSlot (MixingMenu p_39520_, IItemHandler handler, int p_39522_, int p_39523_, int p_39524_) {
        super(handler, p_39522_, p_39523_, p_39524_);
        this.menu = p_39520_;
    }

    public boolean mayPlace(ItemStack p_39526_) {
        return this.menu.isFuel(p_39526_) || isBucket(p_39526_);
    }

    public int getMaxStackSize(ItemStack p_39528_) {
        return isBucket(p_39528_) ? 1 : super.getMaxStackSize(p_39528_);
    }

    public static boolean isBucket(ItemStack p_39530_) {
        return p_39530_.is(Items.BUCKET);
    }
}