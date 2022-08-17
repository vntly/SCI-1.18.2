package com.vntly.sci.core.event;

import com.vntly.sci.SCIMod;
import com.vntly.sci.core.item.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = SCIMod.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.EMERALD, 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(ModItems.PEAR.get(), 6),
                    stack,8,8,0.02F));
        }

//        if(event.getType() == VillagerProfession.TOOLSMITH) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
//            ItemStack stack = new ItemStack(ModItems.CITRINE_PICKAXE.get(), 1);
//            int villagerLevel = 3;
//
//            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 12),
//                    stack,4,12,0.09F));
//        }
    }
}