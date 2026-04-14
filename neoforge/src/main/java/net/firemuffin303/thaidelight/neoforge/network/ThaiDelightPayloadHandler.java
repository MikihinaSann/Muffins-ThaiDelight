package net.firemuffin303.thaidelight.neoforge.network;

import net.firemuffin303.thaidelight.neoforge.common.capabilities.ModAttachmentTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ThaiDelightPayloadHandler {
    public static void handleSpicyPayload(SpicyPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                player.getData(ModAttachmentTypes.SPICY.get()).setTimer(payload.timer());
            }
        });
    }
}
