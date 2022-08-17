package com.vntly.sci.core.item.custom.pearloo;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PearlOOItem extends Item {

    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {//
        return new ItemStack(Items.ENDER_PEARL);//
    }
    protected Item getDefaultItem() {
        return Items.ENDER_PEARL;
    }
    public PearlOOItem(Item.Properties prop) {
        super(prop);
    }
    public InteractionResultHolder<ItemStack> use(Level lvl, Player p, InteractionHand hand) {
        ItemStack itemstack = p.getItemInHand(hand);
        lvl.playSound((Player)null, p.getX(), p.getY(), p.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (lvl.getRandom().nextFloat() * 0.4F + 0.8F));
        p.getCooldowns().addCooldown(this, 120);
        if (!lvl.isClientSide){
           ThrownEnderpearl thrownenderpearl = new ThrownEnderpearl(lvl, p);
            thrownenderpearl.setItem(itemstack);
            thrownenderpearl.shootFromRotation(p, p.getXRot(), p.getYRot(), 0.0F, 1.5F, 1.0F);
            lvl.addFreshEntity(thrownenderpearl);
        }
        p.awardStat(Stats.ITEM_USED.get(this));
        if (!p.getAbilities().instabuild) {
            itemstack.hurtAndBreak(1, p, (player -> player.broadcastBreakEvent(player.getUsedItemHand())));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, lvl.isClientSide());
    }
}