package net.firemuffin303.muffinsmcapi.api.extension;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public interface DirectionalBonemeal extends BonemealableBlock {
    boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, Direction clickedFace);

    boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState, Direction clickedFace);

    void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState, Direction clickedFace);

    @Override
    default boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return isValidBonemealTarget(levelReader, blockPos, blockState, Direction.UP);
    }

    @Override
    default boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return isBonemealSuccess(level, randomSource, blockPos, blockState, Direction.UP);
    }

    @Override
    default void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        performBonemeal(serverLevel, randomSource, blockPos, blockState, Direction.UP);
    }
}
