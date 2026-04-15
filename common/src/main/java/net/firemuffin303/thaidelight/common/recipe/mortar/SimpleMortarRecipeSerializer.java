package net.firemuffin303.thaidelight.common.recipe.mortar;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class SimpleMortarRecipeSerializer<T extends MortarRecipe> implements RecipeSerializer<T> {
    public SimpleMortarRecipeSerializer(Factory<T> constructor) {
    }

    @FunctionalInterface
    public interface Factory<T extends MortarRecipe> {
        T create(net.minecraft.resources.ResourceLocation resourceLocation);
    }

    @Override
    public MapCodec<T> codec() {
        throw new UnsupportedOperationException("SimpleMortarRecipeSerializer codec migration is not implemented yet.");
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
        throw new UnsupportedOperationException("SimpleMortarRecipeSerializer stream codec migration is not implemented yet.");
    }
}
