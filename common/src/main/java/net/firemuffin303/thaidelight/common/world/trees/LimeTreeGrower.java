package net.firemuffin303.thaidelight.common.world.trees;

import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import java.util.Optional;
import net.minecraft.world.level.block.grower.TreeGrower;

public final class LimeTreeGrower {
    public static final TreeGrower GROWER = new TreeGrower(
        "thaidelight_lime",
        Optional.empty(),
        Optional.of(ModFeatures.FEATURE_LIME_TREE),
        Optional.empty()
    );

    private LimeTreeGrower() {
    }
}
