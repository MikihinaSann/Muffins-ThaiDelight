package net.firemuffin303.muffinsmcapi.api;

import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;

public final class CustomEffectRegistry {
    private CustomEffectRegistry() {
    }

    public static void register(CustomEffectRenderer renderer) {
        net.firemuffin303.muffinseffectrenderapi.api.CustomEffectRegistry.register(renderer);
    }
}
