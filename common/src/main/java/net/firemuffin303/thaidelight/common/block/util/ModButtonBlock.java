package net.firemuffin303.thaidelight.common.block.util;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModButtonBlock extends ButtonBlock {
    public ModButtonBlock(BlockSetType blockSetType, int ticksToStayPressed, BlockBehaviour.Properties properties) {
        super(blockSetType, ticksToStayPressed, properties);
    }
}
