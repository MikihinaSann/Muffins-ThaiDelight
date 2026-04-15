package net.firemuffin303.thaidelight.common.recipe.mortar;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class MortarSerializer implements RecipeSerializer<RegularMortarRecipe> {
    @Override
    public MapCodec<RegularMortarRecipe> codec() {
        throw new UnsupportedOperationException("MortarSerializer codec migration is not implemented yet.");
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, RegularMortarRecipe> streamCodec() {
        throw new UnsupportedOperationException("MortarSerializer stream codec migration is not implemented yet.");
    }
}
