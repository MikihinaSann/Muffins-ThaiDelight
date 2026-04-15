package net.firemuffin303.muffinsmcapi.api;

import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CustomEffectRegistry {
    private static final List<CustomEffectRenderer> RENDERERS = new ArrayList<>();

    private CustomEffectRegistry() {
    }

    public static void register(CustomEffectRenderer renderer) {
        RENDERERS.add(renderer);
    }

    public static List<CustomEffectRenderer> getRenderers() {
        return Collections.unmodifiableList(RENDERERS);
    }
}
