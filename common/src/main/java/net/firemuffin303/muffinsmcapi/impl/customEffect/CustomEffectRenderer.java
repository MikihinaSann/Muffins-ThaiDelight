package net.firemuffin303.muffinsmcapi.impl.customEffect;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/**
 * NeoForge-only local renderer contract replacing the Fabric-side external API.
 * These callbacks are kept so existing renderer classes continue to compile.
 */
public interface CustomEffectRenderer {
    boolean shouldRender(LocalPlayer localPlayer);

    Component getName(LocalPlayer localPlayer);

    Component getDetail(LocalPlayer localPlayer);

    ResourceLocation backgroundTextureWide(LocalPlayer localPlayer);

    ResourceLocation backgroundTextureShort(LocalPlayer localPlayer);

    ResourceLocation backgroundTextureHUD(LocalPlayer localPlayer);

    ResourceLocation iconTexture(LocalPlayer localPlayer);

    int color(LocalPlayer player);
}
