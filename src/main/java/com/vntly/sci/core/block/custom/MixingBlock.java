package com.vntly.sci.core.block.custom;

import com.vntly.sci.core.block.entity.ModBlockEntities;
import com.vntly.sci.core.block.entity.custom.MixingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

//public class MixingBlock extends AbstractFurnaceBlock {
//
//    public static final BooleanProperty LIT = BlockStateProperties.LIT;
//    public MixingBlock(BlockBehaviour.Properties p_56439_) {
//        super(p_56439_);
//    }
//
//    public BlockEntity newBlockEntity(BlockPos p_154644_, BlockState p_154645_) {
//        return new SmokerBlockEntity(p_154644_, p_154645_);
//    }
//
//    @Nullable
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
//        return createTickerHelper(pBlockEntityType, ModBlockEntities.MIXING_BLOCK_ENTITY.get(),
//                MixingBlockEntity::serverTick);
//    }
//
//
//    @Override
//    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
//                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
//        if (!pLevel.isClientSide()) {
//            BlockEntity entity = pLevel.getBlockEntity(pPos);
//            if(entity instanceof MixingBlockEntity) {
//                NetworkHooks.openGui(((ServerPlayer)pPlayer), (MixingBlockEntity)entity, pPos);
//            } else {
//                throw new IllegalStateException("Our Container provider is missing!");
//            }
//        }
//
//        return InteractionResult.sidedSuccess(pLevel.isClientSide());
//    }
//
//    @Override
//    protected void openContainer(Level p_53631_, BlockPos p_53632_, Player p_53633_) {
//        BlockEntity blockentity = p_53631_.getBlockEntity(p_53632_);
//        if (blockentity instanceof MixingBlockEntity) {
//            p_53633_.openMenu((MenuProvider)blockentity);
//        }
//
//    }
//
//    public void animateTick(BlockState p_49781_, Level p_49782_, BlockPos p_49783_, Random p_49784_) {
//        if (p_49781_.getValue(LIT)) {
//            double d0 = (double)p_49783_.getX() + 0.5D;
//            double d1 = (double)p_49783_.getY();
//            double d2 = (double)p_49783_.getZ() + 0.5D;
//            if (p_49784_.nextDouble() < 0.1D) {
//                p_49782_.playLocalSound(d0, d1, d2, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
//            }
//
//            Direction direction = p_49781_.getValue(FACING);
//            Direction.Axis direction$axis = direction.getAxis();
//            double d3 = 0.52D;
//            double d4 = p_49784_.nextDouble() * 0.6D - 0.3D;
//            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
//            double d6 = p_49784_.nextDouble() * 9.0D / 16.0D;
//            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
//            p_49782_.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
//        }
//    }
//}

public class MixingBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    //public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public MixingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.FALSE));
    }


    public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
        return this.defaultBlockState().setValue(FACING, p_48689_.getHorizontalDirection().getOpposite());
    }

    public int getAnalogOutputSignal(BlockState p_48702_, Level p_48703_, BlockPos p_48704_) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_48703_.getBlockEntity(p_48704_));
    }

    public RenderShape getRenderShape(BlockState p_48727_) {
        return RenderShape.MODEL;
    }

    public BlockState rotate(BlockState p_48722_, Rotation p_48723_) {
        return p_48722_.setValue(FACING, p_48723_.rotate(p_48722_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_48719_, Mirror p_48720_) {
        return p_48719_.rotate(p_48720_.getRotation(p_48719_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
        p_48725_.add(FACING, LIT);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof MixingBlockEntity) {
                ((MixingBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof MixingBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer) pPlayer), (MixingBlockEntity) entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MixingBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.MIXING_BLOCK_ENTITY.get(),
                MixingBlockEntity::serverTick);
    }
}