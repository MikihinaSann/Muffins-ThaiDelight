package net.firemuffin303.thaidelight.common.world.trees;

import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import java.util.Optional;
import net.minecraft.world.level.block.grower.TreeGrower;

public final class MangoTreeGrower {
    public static final TreeGrower GROWER = new TreeGrower(
        "thaidelight_mango",
        0.1F,
        Optional.empty(),
        Optional.empty(),
        Optional.of(ModFeatures.FEATURE_MANGO_TREE),
        Optional.of(ModFeatures.FEATURE_FANCY_MANGO_TREE),
        Optional.of(ModFeatures.FEATURE_MANGO_TREE_BEE),
        Optional.of(ModFeatures.FEATURE_FANCY_MANGO_TREE_BEE)
    );

    private MangoTreeGrower() {
    }
}
