package com.vntly.rebud.core.screen.slot;


import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModResultSlot extends SlotItemHandler {
    private int removeCount;

    public ModResultSlot(IItemHandler handler, int p_39544_, int p_39545_, int p_39546_) {
        super(handler, p_39544_, p_39545_, p_39546_);
    }

    public boolean mayPlace(ItemStack p_39553_) {
        return false;
    }

    public ItemStack remove(int p_39548_) {
        if (this.hasItem()) {
            this.removeCount += Math.min(p_39548_, this.getItem().getCount());
        }

        return super.remove(p_39548_);
    }

    public void onTake(Player p_150563_, ItemStack p_150564_) {
        super.onTake(p_150563_, p_150564_);
    }

    protected void onQuickCraft(ItemStack p_39555_, int p_39556_) {
        this.removeCount += p_39556_;
    }
}