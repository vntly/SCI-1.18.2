package com.vntly.rebud.core.screen;

import com.vntly.rebud.core.block.ModBlocks;
import com.vntly.rebud.core.block.entity.custom.CleaningTableBlockEntity;
import com.vntly.rebud.core.event.ModEventBusEvents;
import com.vntly.rebud.core.item.ModItems;
import com.vntly.rebud.core.recipe.CleaningTableRecipe;
import com.vntly.rebud.core.recipe.ModRecipes;
import com.vntly.rebud.core.screen.slot.ModResultSlot;
import com.vntly.rebud.core.util.ModTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Optional;

public class CleaningTableMenu extends AbstractContainerMenu {
    private final CleaningTableBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public CleaningTableMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraDate) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraDate.readBlockPos()), new SimpleContainerData(3));
    }

    public CleaningTableMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.CLEANING_TABLE_MENU.get(), pContainerId);
        checkContainerSize(inv, 3);
        blockEntity = ((CleaningTableBlockEntity) entity);
        this.level = inv.player.level;
        this.data = data;


        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 34, 22){
                @Override
                public boolean mayPlace(@Nonnull ItemStack stack){
                    return (stack.is(ModTags.Items.FOSSILS) || stack.is(ModTags.Items.HARD_FOSSILS));
                }
            });
            this.addSlot(new SlotItemHandler(handler, 1, 34, 51) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return (stack.is(ModTags.Items.CHISELS));
                }
            });

            this.addSlot(new ModResultSlot(handler, 2, 114, 37));
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 144));
        }
        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public boolean isHardCrafting() {
        return data.get(2) > 0;
    }


    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 24; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
    public int getScaledProgressHard() {
        int progress = this.data.get(2);
        int maxProgress = 2 * this.data.get(1);  // Max Progress
        int progressArrowSize = 24; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMoveStack(Player p_38986_, int p_38987_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_38987_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_38987_ == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_38987_ != 1 && p_38987_ != 0) {
                if (this.canBeCleaned(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isChisel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_38987_ >= 3 && p_38987_ < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_38987_ >= 30 && p_38987_ < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
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

    protected boolean canBeCleaned(ItemStack p_38978_) {
        return p_38978_.is(ModTags.Items.FOSSILS) || p_38978_.is(ModTags.Items.HARD_FOSSILS);
    }

    protected boolean isChisel(ItemStack p_38989_) {
        return p_38989_.is(ModTags.Items.CHISELS);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.CLEANING_TABLE.get());
    }
}
