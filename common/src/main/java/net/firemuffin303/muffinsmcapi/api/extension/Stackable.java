package net.firemuffin303.muffinsmcapi.api.extension;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public interface Stackable {
    IntegerProperty getStackProperty();

    ItemLike getPickUpItem();

    default InteractionResult takeItem(Level level, BlockPos blockPos, BlockState blockState, Player player, InteractionHand interactionHand) {
        if (player.getAbilities().mayBuild && player.getItemInHand(interactionHand).isEmpty()) {
            int stackCount = blockState.getValue(getStackProperty());
            ItemStack itemStack = new ItemStack(getPickUpItem());
            if (!player.getInventory().add(itemStack)) {
                player.drop(itemStack, false);
            }

            level.playSound(null, blockPos, SoundEvents.ARMOR_EQUIP_GENERIC.value(), SoundSource.BLOCKS);

            if (stackCount == 1) {
                level.removeBlock(blockPos, false);
                level.gameEvent(GameEvent.BLOCK_DESTROY, blockPos, GameEvent.Context.of(blockState));
                return InteractionResult.SUCCESS;
            }

            level.setBlockAndUpdate(blockPos, blockState.setValue(getStackProperty(), stackCount - 1));
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState));
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult);
}
