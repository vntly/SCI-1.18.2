package com.vntly.sci.core.block.entity.custom;

import com.vntly.sci.core.block.custom.MixingBlock;
import com.vntly.sci.core.block.entity.ModBlockEntities;
import com.vntly.sci.core.recipe.CleaningTableRecipe;
import com.vntly.sci.core.recipe.MixingRecipe;
import com.vntly.sci.core.screen.CleaningTableMenu;
import com.vntly.sci.core.screen.MixingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class MixingBlockEntity extends BlockEntity implements  MenuProvider {

    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_FUEL = 1;
    protected static final int SLOT_RESULT = 2;
    protected static final int SLOT_ADDITION = 3;
    public static final int DATA_LIT_TIME = 0;
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;
    public static final int NUM_DATA_VALUES = 4;
    public static final int BURN_TIME_STANDARD = 200;
    public static final int BURN_COOL_SPEED = 2;
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int Slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

    protected final ContainerData data;

    int litTime;
    int litDuration;
    int cookingProgress;
    int cookingTotalTime;

    private final RecipeType<MixingRecipe> recipeType;

    public MixingBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.MIXING_BLOCK_ENTITY.get(), p_155229_, p_155230_);
        this.recipeType = MixingRecipe.Type.INSTANCE;
        this.data = new ContainerData() {
            public int get(int p_58431_) {
                switch (p_58431_) {
                    case 0:
                        return MixingBlockEntity.this.litTime;
                    case 1:
                        return MixingBlockEntity.this.litDuration;
                    case 2:
                        return MixingBlockEntity.this.cookingProgress;
                    case 3:
                        return MixingBlockEntity.this.cookingTotalTime;
                    default:
                        return 0;
                }
            }

            public void set(int p_58433_, int p_58434_) {
                switch (p_58433_) {
                    case 0:
                        MixingBlockEntity.this.litTime = p_58434_;
                    case 1:
                        MixingBlockEntity.this.litDuration = p_58434_;
                    case 2:
                        MixingBlockEntity.this.cookingProgress = p_58434_;
                    case 3:
                        MixingBlockEntity.this.cookingTotalTime = p_58434_;
                }

            }

            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Mixing Block");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MixingMenu(pContainerId, pInventory, this, this.data);
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
        ContainerHelper.saveAllItems(tag, items);
        tag.putInt("BurnTime", litTime);
        tag.putInt("CookTime", cookingProgress);
        tag.putInt("CookTimeTotal", cookingTotalTime);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        cookingProgress = nbt.getInt("CookTime");
        cookingTotalTime = nbt.getInt("CookTimeTotal");
        litDuration = getBurnDuration(items.get(2));
    }


    public int getContainerSize() {
        return this.items.size();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    protected int getBurnDuration(ItemStack p_58343_) {
//        if (p_58343_.isEmpty()) {
//            return 200;
//        } else {
//            Item item = p_58343_.getItem();
//            return item.getBurnTime(p_58343_, this.recipeType);
//        }
        return 200;
    }
    private static int getTotalCookTime(@NotNull MixingBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<MixingRecipe> match = level.getRecipeManager()
                .getRecipeFor(MixingRecipe.Type.INSTANCE, inventory, level);
//        if(!match.isPresent()) {return match.map(MixingRecipe::getCookingTime).orElse(400);}
//        else return 400;
        return 400;
    }
    private boolean isLit() {
        return this.litTime > 0;
    }

//    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, MixingBlockEntity pBlockEntity) {
//        if (pBlockEntity.isLit()) {
//            --pBlockEntity.litTime;
//            System.out.println("F");
//        }
//
//        boolean flag = pBlockEntity.isLit();
//        boolean flag1 = false;
//        ItemStack itemstack = pBlockEntity.items.get(1);
//        if(pBlockEntity.isLit() || !itemstack.isEmpty()) {
//            if (!pBlockEntity.isLit() && pBlockEntity.canBurn(pBlockEntity)) {
//                pBlockEntity.litTime = pBlockEntity.getBurnDuration(itemstack);
//                pBlockEntity.litDuration = pBlockEntity.litTime;
//                timeSet(pBlockEntity);
//                //TRUE
//                if (pBlockEntity.isLit()) {
//                    System.out.println('A');
//                    flag1 = true;
//                    if (itemstack.hasContainerItem())
//                        pBlockEntity.items.set(1, itemstack.getContainerItem());
//                    else if (!itemstack.isEmpty()) {
//                        //Item item = itemstack.getItem();
//                        itemstack.shrink(1);
//                        if (itemstack.isEmpty()) {
//                            pBlockEntity.items.set(1, itemstack.getContainerItem());
//                        }
//                    }
//                }
//            }
//            if (pBlockEntity.isLit() && pBlockEntity.canBurn(pBlockEntity) && hasRecipe(pBlockEntity)) {
//                System.out.println('B');
//                ++pBlockEntity.cookingProgress;
//                if (pBlockEntity.cookingProgress == pBlockEntity.cookingTotalTime) {
//                    pBlockEntity.cookingProgress = 0;
//                    craftItem(pBlockEntity);
//                    timeSet(pBlockEntity);
//                    flag1 = true;
//                }
//            }
//        } else if (!pBlockEntity.isLit() && pBlockEntity.cookingProgress > 0) {
//            System.out.println('J');
//            pBlockEntity.cookingProgress = Mth.clamp(pBlockEntity.cookingProgress - 2, 0, pBlockEntity.cookingTotalTime);
//        }
//        if (flag1) {
//            setChanged(pLevel, pPos, pState);
//        }
//    }

    public static void serverTick(Level p_155014_, BlockPos p_155015_, BlockState p_155016_, MixingBlockEntity p_155017_) {
        boolean flag = p_155017_.isLit();
        boolean flag1 = false;
        if (p_155017_.isLit()) {
            --p_155017_.litTime;
        }

        ItemStack itemstack = p_155017_.items.get(1);
        if (p_155017_.isLit() || !itemstack.isEmpty() && !p_155017_.items.get(0).isEmpty()) {
            if (!p_155017_.isLit() && p_155017_.canBurn(p_155017_)) {
                p_155017_.litTime = p_155017_.getBurnDuration(itemstack);
                p_155017_.litDuration = p_155017_.litTime;
                if (p_155017_.isLit()) {
                    flag1 = true;
                    if (itemstack.hasContainerItem())
                        p_155017_.items.set(1, itemstack.getContainerItem());
                    else
                    if (!itemstack.isEmpty()) {
                        Item item = itemstack.getItem();
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            p_155017_.items.set(1, itemstack.getContainerItem());
                        }
                    }
                }
            }

            if (p_155017_.isLit() && p_155017_.canBurn(p_155017_)) {
                ++p_155017_.cookingProgress;
                if (p_155017_.cookingProgress == p_155017_.cookingTotalTime) {
                    p_155017_.cookingProgress = 0;
                    p_155017_.cookingTotalTime = getTotalCookTime(p_155017_);

                    flag1 = true;
                }
            } else {
                p_155017_.cookingProgress = 0;
            }
        } else if (!p_155017_.isLit() && p_155017_.cookingProgress > 0) {
            p_155017_.cookingProgress = Mth.clamp(p_155017_.cookingProgress - 2, 0, p_155017_.cookingTotalTime);
        }

        if (flag != p_155017_.isLit()) {
            flag1 = true;
            p_155016_ = p_155016_.setValue(AbstractFurnaceBlock.LIT, Boolean.valueOf(p_155017_.isLit()));
            p_155014_.setBlock(p_155015_, p_155016_, 3);
        }

        if (flag1) {
            setChanged(p_155014_, p_155015_, p_155016_);
        }

    }

    public static void timeSet(@NotNull MixingBlockEntity pBlockEntity){
        pBlockEntity.cookingTotalTime = getTotalCookTime(pBlockEntity);
    }
    private static boolean hasRecipe(@NotNull MixingBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<MixingRecipe> match = level.getRecipeManager()
                .getRecipeFor(MixingRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }


    private boolean canBurn(@NotNull MixingBlockEntity entity) {
//        NonNullList<ItemStack> items = this.items;
//        return !items.get(1).isEmpty() && !items.get(0).isEmpty() &&
//                !items.get(3).isEmpty() && hasRecipe(entity);
        return true;
    }

    private static void craftItem(MixingBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<MixingRecipe> match = level.getRecipeManager()
                .getRecipeFor(MixingRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);

            entity.itemHandler.extractItem(3,1, false);


            entity.itemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(2).getCount() + 1));

            entity.resetProgress();
        }
    }


    private void resetProgress() {
        this.cookingProgress = 0;
    }
    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}