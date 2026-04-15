package net.firemuffin303.thaidelight.common.registry.neoforge;

import com.mojang.serialization.MapCodec;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

public class ModBlockStateProviderTypesImpl {
    public static final DeferredRegister<BlockStateProviderType<?>> BLOCK_STATE_PROVIDER_TYPE = DeferredRegister.create(Registries.BLOCK_STATE_PROVIDER_TYPE, ThaiDelightCommon.MOD_ID);

    public static <P extends BlockStateProvider> Supplier<BlockStateProviderType<P>> register(String id, MapCodec<P> codec) {
        return BLOCK_STATE_PROVIDER_TYPE.register(id, () -> createType(codec));
    }

    @SuppressWarnings("unchecked")
    private static <P extends BlockStateProvider> BlockStateProviderType<P> createType(MapCodec<P> codec) {
        try {
            Constructor<BlockStateProviderType> ctor = BlockStateProviderType.class.getDeclaredConstructor(MapCodec.class);
            ctor.setAccessible(true);
            return (BlockStateProviderType<P>) ctor.newInstance(codec);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to construct BlockStateProviderType for codec registration", e);
        }
    }
}
