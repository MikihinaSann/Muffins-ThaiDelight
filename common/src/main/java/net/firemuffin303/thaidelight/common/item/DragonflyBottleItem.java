package net.firemuffin303.thaidelight.common.item;

import net.firemuffin303.thaidelight.common.entity.DragonflyEntity;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.List;

public class DragonflyBottleItem extends MobBottleItem<DragonflyEntity> {
    public DragonflyBottleItem(Properties properties) {
        super(ModEntityTypes.DRAGONFLY, SoundEvents.BOTTLE_EMPTY, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
        CompoundTag compoundTag = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if(compoundTag != null && compoundTag.contains("Variant")){
            int varaint = compoundTag.getInt("Variant");
            ChatFormatting[] chatFormattings = new ChatFormatting[]{ChatFormatting.ITALIC,ChatFormatting.GRAY};
            String string = "dragonfly.variant.muffins_thaidelight." + DragonflyEntity.DragonflyVariant.byId(varaint).getName();
            list.add(Component.translatable(string).withStyle(chatFormattings));
        }
    }

    public static void setVariant(ItemStack itemStack, DragonflyEntity.DragonflyVariant variant){
        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, compoundTag -> compoundTag.putInt("Variant", variant.getId()));
    }

    public static int getVariant(ItemStack itemStack){
        return itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("Variant");
    }
}
