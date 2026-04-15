package net.firemuffin303.thaidelight.common.world.feature;

import com.mojang.serialization.MapCodec;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class DurianTreeTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<DurianTreeTrunkPlacer> CODEC = MapCodec.unit(
            new DurianTreeTrunkPlacer(5, 2, 0, UniformInt.of(-2, 0), UniformInt.of(2, 4), UniformInt.of(1, 3))
    );

    private final UniformInt branchStartOffsetFromTop;
    private final IntProvider branchLength;
    private final IntProvider branchCount;

    public DurianTreeTrunkPlacer(int baseHeight, int heightRanA, int heightRanB,UniformInt branchStartOffsetFromTop,IntProvider branchLength,IntProvider branchCount) {
        super(baseHeight, heightRanA, heightRanB);
        this.branchStartOffsetFromTop = branchStartOffsetFromTop;
        this.branchLength = branchLength;
        this.branchCount = branchCount;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModFeatures.DURIAN_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, int treeHeight, BlockPos blockPos, TreeConfiguration treeConfiguration) {

        List<FoliagePlacer.FoliageAttachment> list = new ArrayList<>();

        int branchCount = this.branchCount.sample(randomSource);

        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below(), treeConfiguration);
        int j = Math.max(0,treeHeight -1 +this.branchStartOffsetFromTop.sample(randomSource));

        //place straight log
        for(int n = 0;n < treeHeight; ++n){
            BlockPos currentPosition = blockPos.above(n);

            if(TreeFeature.validTreePos(levelSimulatedReader, currentPosition)){
                this.placeLog(levelSimulatedReader, biConsumer, randomSource, currentPosition, treeConfiguration);
            }


        }

        list.add(new FoliagePlacer.FoliageAttachment(blockPos.above(treeHeight),1,false));

        List<Direction> directions = Direction.Plane.HORIZONTAL.shuffledCopy(randomSource);


        for(int bcount = 0; bcount < branchCount;++bcount){
            Direction direction = directions.get(bcount);
            int branchLength = this.branchLength.sample(randomSource);
            int branchOffset = this.branchStartOffsetFromTop.sample(randomSource);
            //place branch
            BlockPos tempPos = blockPos.above(treeHeight).mutable().above(branchOffset);
            BlockPos foliagePos = tempPos.mutable().move(direction,branchLength).above(2);
            for(int n = 0; n < branchLength; ++n){
                BlockPos currentPosition = tempPos.mutable().move(direction,n+1);
                if(TreeFeature.validTreePos(levelSimulatedReader,currentPosition)){
                    this.placeLog(levelSimulatedReader,biConsumer,randomSource,currentPosition,treeConfiguration, blockState -> blockState.trySetValue(RotatedPillarBlock.AXIS,direction.getAxis()));
                }

                if(n+1 == branchLength && TreeFeature.validTreePos(levelSimulatedReader,currentPosition.above(1)) && randomSource.nextBoolean()){
                    foliagePos = currentPosition.above(2);
                    this.placeLog(levelSimulatedReader,biConsumer,randomSource,currentPosition.above(1),treeConfiguration);
                }
            }

            list.add(new FoliagePlacer.FoliageAttachment(foliagePos,0,false));

        }

        return list;
    }
}
