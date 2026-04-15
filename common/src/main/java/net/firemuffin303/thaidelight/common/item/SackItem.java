package net.firemuffin303.thaidelight.common.item;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModCriteriaTriggers;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class SackItem extends BlockItem {
    public SackItem(Properties properties) {
        super(ModBlocks.SACK.get(), properties);
    }

    public int getUseDuration(ItemStack itemStack) {
        return 1200;
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return PlatformUtil.getDurianCatcherUseAnim();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (isFull(itemStack)) {
            return InteractionResultHolder.pass(itemStack);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        return itemStack;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack itemStack) {
        return Optional.empty();
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    public static boolean isFull(ItemStack itemStack) {
        return itemStack.is(ModItems.SACK.get()) && itemStack.getCount() >= itemStack.getMaxStackSize();
    }

    public static boolean onCatchingFallingBlock(ItemStack sackItem, Item item, ServerPlayer serverPlayer) {
        ItemStack caught = new ItemStack(item);
        ModCriteriaTriggers.SACK_CATCH.trigger(serverPlayer, caught);
        return true;
    }
}
