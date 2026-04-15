package net.firemuffin303.thaidelight.neoforge.mixin.dyeableItem;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.ArmorDyeRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArmorDyeRecipe.class)
public abstract class RecipeMixin {
    @WrapWithCondition(method = "getRemainingItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;set(ILjava/lang/Object;)Ljava/lang/Object;"), remap = false)
    private static <E> boolean muffins$dyeCoconutMilkIcecreamWithBowlPlease(NonNullList<ItemStack> instance, int i, E object) {
        return instance.get(i).getItem() != ModItems.COCONUT_MILK_ICE_CREAM.get();
    }
}
