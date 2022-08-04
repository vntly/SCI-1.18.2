package com.vntly.rebud.core.item.custom.digging_hummer;

import com.vntly.rebud.core.util.ModTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class DiggingItem extends DiggerItem {
    public DiggingItem(Tier tier, int integer, float fl, Item.Properties i) {
        super((float)integer, fl, tier, ModTags.Blocks.DIGGER_VALUABLES, i);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }
}
