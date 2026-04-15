package net.firemuffin303.thaidelight.mixin.cauldron;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractCauldronBlock.class)
public abstract class AbstractCauldronMixin {
    @ModifyReturnValue(method = "use", at = @At("RETURN"))
    public InteractionResult muffins$use(InteractionResult original) {
        return original;
    }
}
