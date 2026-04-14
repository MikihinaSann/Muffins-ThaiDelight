package net.firemuffin303.thaidelight.common.world.trees;

import java.util.Optional;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

public final class DurianTreeGrower {
    public static final TreeGrower GROWER = new TreeGrower(
        "thaidelight_durian",
        Optional.of(ModFeatures.FEATURE_TALL_DURIAN_TREE),
        Optional.of(ModFeatures.FEATURE_DURIAN_TREE),
        Optional.of(ModFeatures.FEATURE_DURAIN_TREE_BEE)
    );

    private DurianTreeGrower() {
    }
}
