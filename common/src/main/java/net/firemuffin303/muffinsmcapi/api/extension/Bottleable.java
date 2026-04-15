package net.firemuffin303.muffinsmcapi.api.extension;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface Bottleable {
    boolean isFromBottle();

    void setFromBottle(boolean fromBottle);

    void copyDataToStack(ItemStack stack);

    void copyDataFromNbt(CompoundTag nbt);

    ItemStack getBottleItem();

    SoundEvent getBottleFillSound();

    static void copyDataToStack(Mob entity, ItemStack stack) {
        if (entity.hasCustomName()) {
            stack.set(DataComponents.CUSTOM_NAME, entity.getCustomName());
        }

        CustomData.update(DataComponents.CUSTOM_DATA, stack, compoundTag -> {
            if (entity.isNoAi()) {
                compoundTag.putBoolean("NoAI", entity.isNoAi());
            }

            if (entity.isSilent()) {
                compoundTag.putBoolean("Silent", entity.isSilent());
            }

            if (entity.isNoGravity()) {
                compoundTag.putBoolean("NoGravity", entity.isNoGravity());
            }

            if (entity.hasGlowingTag()) {
                compoundTag.putBoolean("Glowing", entity.isCurrentlyGlowing());
            }

            if (entity.isInvulnerable()) {
                compoundTag.putBoolean("Invulnerable", entity.isInvulnerable());
            }

            compoundTag.putFloat("Health", entity.getHealth());
        });
    }

    static void copyDataFromNbt(Mob entity, CompoundTag compoundTag) {
        if (compoundTag.contains("NoAI")) {
            entity.setNoAi(compoundTag.getBoolean("NoAI"));
        }

        if (compoundTag.contains("Silent")) {
            entity.setSilent(compoundTag.getBoolean("Silent"));
        }

        if (compoundTag.contains("NoGravity")) {
            entity.setNoGravity(compoundTag.getBoolean("NoGravity"));
        }

        if (compoundTag.contains("Glowing")) {
            entity.setGlowingTag(compoundTag.getBoolean("Glowing"));
        }

        if (compoundTag.contains("Invulnerable")) {
            entity.setInvulnerable(compoundTag.getBoolean("Invulnerable"));
        }

        if (compoundTag.contains("Health", 99)) {
            entity.setHealth(compoundTag.getFloat("Health"));
        }
    }

    static <T extends LivingEntity & Bottleable> Optional<InteractionResult> tryBottle(Player player, InteractionHand hand, T entity) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() == Items.GLASS_BOTTLE && entity.isAlive()) {
            entity.playSound(entity.getBottleFillSound(), 1.0F, 1.0F);
            ItemStack itemStack2 = entity.getBottleItem();
            entity.copyDataToStack(itemStack2);
            ItemStack itemStack3 = ItemUtils.createFilledResult(itemStack, player, itemStack2, false);
            player.setItemInHand(hand, itemStack3);
            Level level = entity.level();
            entity.discard();
            return Optional.of(InteractionResult.sidedSuccess(level.isClientSide));
        } else {
            return Optional.empty();
        }
    }

    SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag);
}
