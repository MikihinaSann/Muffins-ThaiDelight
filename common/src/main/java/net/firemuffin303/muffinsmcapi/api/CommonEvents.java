package net.firemuffin303.muffinsmcapi.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

public final class CommonEvents {
    private CommonEvents() {
    }

    // Kept for compatibility with existing crop growth logic.
    public static float getGrowthSpeed(Block block, BlockGetter blockGetter, BlockPos blockPos) {
        float growth = 1.0F;
        BlockPos belowPos = blockPos.below();

        for (int x = -1; x <= 1; ++x) {
            for (int z = -1; z <= 1; ++z) {
                float farmlandBonus = 0.0F;
                BlockState blockState = blockGetter.getBlockState(belowPos.offset(x, 0, z));
                if (blockState.is(Blocks.FARMLAND)) {
                    farmlandBonus = 1.0F;
                    if (blockState.getValue(FarmBlock.MOISTURE) > 0) {
                        farmlandBonus = 3.0F;
                    }
                }

                if (x != 0 || z != 0) {
                    farmlandBonus /= 4.0F;
                }

                growth += farmlandBonus;
            }
        }

        BlockPos north = blockPos.north();
        BlockPos south = blockPos.south();
        BlockPos west = blockPos.west();
        BlockPos east = blockPos.east();
        boolean xAxisNeighbor = blockGetter.getBlockState(west).is(block) || blockGetter.getBlockState(east).is(block);
        boolean zAxisNeighbor = blockGetter.getBlockState(north).is(block) || blockGetter.getBlockState(south).is(block);

        if (xAxisNeighbor && zAxisNeighbor) {
            growth /= 2.0F;
        } else {
            boolean diagonalNeighbor =
                    blockGetter.getBlockState(west.north()).is(block) ||
                            blockGetter.getBlockState(east.north()).is(block) ||
                            blockGetter.getBlockState(east.south()).is(block) ||
                            blockGetter.getBlockState(west.south()).is(block);
            if (diagonalNeighbor) {
                growth /= 2.0F;
            }
        }

        return growth;
    }
}
