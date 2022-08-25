package com.vntly.sci.core.screen;

import com.vntly.sci.core.block.ModBlocks;
import com.vntly.sci.core.block.entity.custom.MixingBlockEntity;
import com.vntly.sci.core.recipe.MixingRecipe;
import com.vntly.sci.core.screen.slot.MixingFuelSlot;
import com.vntly.sci.core.screen.slot.ModResultSlot;
import com.vntly.sci.core.util.ModTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class MixingMenu extends AbstractContainerMenu {
    private final MixingBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    private final RecipeType<?> recipeType;

    public MixingMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraDate) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraDate.readBlockPos()), new SimpleContainerData(4));
    }

    public MixingMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.MIXING_MENU.get(), pContainerId);
        checkContainerSize(inv, 4);
        blockEntity = ((MixingBlockEntity) entity);
        this.level = inv.player.level;
        this.data = data;
        this.recipeType = MixingRecipe.Type.INSTANCE;


        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 56, 17) {
                @Override
                public boolean mayPlace(@Nonnull ItemStack stack) {
                    return canSmelt(stack);
                }
            });
            this.addSlot(new SlotItemHandler(handler, 3, 30, 35) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return isAddition(stack);
                }
            });
            this.addSlot(new MixingFuelSlot(this, handler,1, 56, 53));

            this.addSlot(new ModResultSlot(handler, 2, 116, 35));
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
        }

        this.addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player p_38986_, int p_38987_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_38987_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_38987_ == 2) {
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_38987_ != 1 && p_38987_ != 0) {
                if (this.canSmelt(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isAddition(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 3, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_38987_ >= 4 && p_38987_ < 31) {
                    if (!this.moveItemStackTo(itemstack1, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_38987_ >= 31 && p_38987_ < 40 && !this.moveItemStackTo(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_38986_, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.MIXING_BLOCK.get());
    }
    public boolean canSmelt(ItemStack p_38989_) {
        return p_38989_.is(ModTags.Items.BASE);
    }

    public boolean isFuel(ItemStack p_38989_) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(p_38989_, this.recipeType) > 0;
    }

    public boolean isAddition(ItemStack p_38989_) {
        return p_38989_.is(ModTags.Items.ADDITION);
    }

    public int getBurnProgress() {
        int i = this.data.get(2);
        int j = this.data.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public int getLitProgress() {
        int i = this.data.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.data.get(0) * 13 / i;
    }

    public boolean isLit() {
        return this.data.get(0) > 0;
    }

    public boolean shouldMoveToInventory(int p_150463_) {
        return p_150463_ != 1;
    }
}
