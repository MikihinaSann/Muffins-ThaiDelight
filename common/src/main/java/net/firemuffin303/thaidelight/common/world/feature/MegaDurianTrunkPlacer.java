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

public class MegaDurianTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<MegaDurianTrunkPlacer> CODEC = MapCodec.unit(
            new MegaDurianTrunkPlacer(9, 3, 0, 2, UniformInt.of(-2, 0), UniformInt.of(2, 4), UniformInt.of(1, 3))
    );

    int branchSections;
    UniformInt branchSectionOffset;
    IntProvider branchLength;
    IntProvider branchCountPerSection;


    public MegaDurianTrunkPlacer(int baseHeight, int heightRanA, int heightRanB,
                                 int branchSections,
                                 UniformInt branchSectionOffset,
                                 IntProvider branchLength,
                                 IntProvider branchCountPerSection) {
        super(baseHeight, heightRanA,heightRanB);
        this.branchSections = branchSections;
        this.branchSectionOffset = branchSectionOffset;
        this.branchLength = branchLength;
        this.branchCountPerSection = branchCountPerSection;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModFeatures.MEGA_DURIAN_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, int treeHeight, BlockPos blockPos, TreeConfiguration treeConfiguration) {
        List<FoliagePlacer.FoliageAttachment> list = new ArrayList<>();


        //Setting Dirt
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below(), treeConfiguration);
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below().north(), treeConfiguration);
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below().east(), treeConfiguration);
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below().west(), treeConfiguration);
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below().south(), treeConfiguration);

        for(int n = 0;n < treeHeight; ++n){
            BlockPos currentPosition = blockPos.above(n);

            if(TreeFeature.validTreePos(levelSimulatedReader, currentPosition)){
                this.placeLog(levelSimulatedReader, biConsumer, randomSource, currentPosition, treeConfiguration);
            }
        }

        for(Direction direction: Direction.Plane.HORIZONTAL){
            for (int base = 0 ; base < (treeHeight/4) - randomSource.nextInt(3); ++ base){
                BlockPos currentPosition = blockPos.relative(direction).above(base);
                if (TreeFeature.validTreePos(levelSimulatedReader,currentPosition)){
                    this.placeLog(levelSimulatedReader, biConsumer, randomSource, currentPosition, treeConfiguration);
                }
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(blockPos.above(treeHeight),1,false));


        List<Direction> directions = Direction.Plane.HORIZONTAL.shuffledCopy(randomSource);

        BlockPos pos = blockPos.above(treeHeight);

        for(int branchRow = 0; branchRow < this.branchSections; ++branchRow){
            int branchCount = this.branchCountPerSection.sample(randomSource);
            int branchOffset = this.branchSectionOffset.sample(randomSource);
            pos = pos.above(branchOffset);
            for(int branchCountIndex = 0;branchCountIndex < branchCount; ++branchCountIndex){
                Direction direction = directions.get(branchCountIndex);
                int branchLengthSampled = this.branchLength.sample(randomSource);
                BlockPos foliagePos = pos.relative(direction,branchLengthSampled).above(2);
                for(int length = 0; length < branchLengthSampled; ++length){
                    BlockPos currentPos = pos.relative(direction,length+1);
                    if(TreeFeature.validTreePos(levelSimulatedReader,currentPos)){
                        this.placeLog(levelSimulatedReader,biConsumer,randomSource,currentPos,treeConfiguration, blockState -> blockState.trySetValue(RotatedPillarBlock.AXIS,direction.getAxis()));
                    }

                    if(length+1 == branchLengthSampled && TreeFeature.validTreePos(levelSimulatedReader,currentPos.above(1)) && randomSource.nextBoolean()){
                        foliagePos = currentPos.above(2);
                        this.placeLog(levelSimulatedReader,biConsumer,randomSource,currentPos.above(1),treeConfiguration);
                    }
                }

                list.add(new FoliagePlacer.FoliageAttachment(foliagePos,0,false));
            }
        }

        return list;
    }
}
