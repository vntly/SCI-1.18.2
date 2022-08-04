package com.vntly.rebud.core.block.entity.custom;

import com.vntly.rebud.core.block.entity.ModBlockEntities;
import com.vntly.rebud.core.item.ModItems;
import com.vntly.rebud.core.recipe.CleaningTableRecipe;
import com.vntly.rebud.core.screen.CleaningTableMenu;
import com.vntly.rebud.core.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;

public class CleaningTableBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int Slot){
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int progressHard = 0;
    private int maxProgress = 30;

    public CleaningTableBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.CLEANING_TABLE_BLOCK_ENTITY.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return CleaningTableBlockEntity.this.progress;
                    case 1: return CleaningTableBlockEntity.this.maxProgress;
                    case 2: return CleaningTableBlockEntity.this.progressHard;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: CleaningTableBlockEntity.this.progress = value; break;
                    case 1: CleaningTableBlockEntity.this.maxProgress = value; break;
                    case 2: CleaningTableBlockEntity.this.progressHard = value; break;
                }
            }

            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Cleaning Table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new CleaningTableMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("cleaning.progress", progress);
        tag.putInt("cleaning_hard.progress", progressHard);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("cleaning.progress");
        progressHard = nbt.getInt("cleaning_hard.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, CleaningTableBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity)) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else if(hasHardRecipe(pBlockEntity)) {
            pBlockEntity.progressHard++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progressHard > (2*pBlockEntity.maxProgress)) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(@NotNull CleaningTableBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CleaningTableRecipe> match = level.getRecipeManager()
                .getRecipeFor(CleaningTableRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasToolsInToolSlot(entity) &&
                !isHardClean(inventory);
    }

    public static boolean hasHardRecipe(@NotNull CleaningTableBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }


        Optional<CleaningTableRecipe> match = level.getRecipeManager()
                .getRecipeFor(CleaningTableRecipe.Type.INSTANCE, inventory, level);


        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, match.get().getResultItem()) &&
                hasToolsInToolSlot(entity) &&
                isHardClean(inventory);
    }


    private static boolean hasToolsInToolSlot(CleaningTableBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(1).is(ModTags.Items.CHISELS);

    }

    private static void craftItem(CleaningTableBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CleaningTableRecipe> match = level.getRecipeManager()
                .getRecipeFor(CleaningTableRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);
            if (entity.itemHandler.getStackInSlot(1).hurt(1, new Random(), null)) {
                entity.itemHandler.extractItem(1, 1, false);
            }

            entity.itemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(2).getCount() + 1));

            entity.resetProgress();
        }
    }


    private void resetProgress() {
        this.progress = 0;
        this.progressHard = 0;
    }
    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(2).isEmpty();
    }
    private static boolean isHardClean(SimpleContainer inventory) {
        return inventory.getItem(0).is(ModTags.Items.HARD_FOSSILS);
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}