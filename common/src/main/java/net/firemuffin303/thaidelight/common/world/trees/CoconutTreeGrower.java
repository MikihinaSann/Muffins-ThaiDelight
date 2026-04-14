package net.firemuffin303.thaidelight.common.world.trees;

import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import java.util.Optional;
import net.minecraft.world.level.block.grower.TreeGrower;

public final class CoconutTreeGrower {
    public static final TreeGrower GROWER = new TreeGrower(
        "thaidelight_coconut",
        Optional.empty(),
        Optional.of(ModFeatures.FEATURE_COCONUT_TREE),
        Optional.of(ModFeatures.FEATURE_COCONUT_TREE_BEE)
    );

    private CoconutTreeGrower() {
    }
}
