package net.firemuffin303.thaidelight.common.registry.neoforge;

import com.mojang.serialization.MapCodec;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

public class ModTreeDecoratorTypesImpl {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPE = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, ThaiDelightCommon.MOD_ID);

    public static <T extends TreeDecorator> Supplier<TreeDecoratorType<T>> registerTreeDecorator(String id, MapCodec<T> codec) {
        return TREE_DECORATOR_TYPE.register(id, () -> createType(codec));
    }

    @SuppressWarnings("unchecked")
    private static <T extends TreeDecorator> TreeDecoratorType<T> createType(MapCodec<T> codec) {
        try {
            Constructor<TreeDecoratorType> ctor = TreeDecoratorType.class.getDeclaredConstructor(MapCodec.class);
            ctor.setAccessible(true);
            return (TreeDecoratorType<T>) ctor.newInstance(codec);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to construct TreeDecoratorType for codec registration", e);
        }
    }
}
