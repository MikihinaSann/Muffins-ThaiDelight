package net.firemuffin303.thaidelight.neoforge.network;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record SpicyPayload(int timer) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SpicyPayload> TYPE =
            new CustomPacketPayload.Type<>(ThaiDelightCommon.modid("spicy_sync"));

    public static final StreamCodec<FriendlyByteBuf, SpicyPayload> STREAM_CODEC =
            StreamCodec.ofMember(
                    (payload, buf) -> buf.writeInt(payload.timer),
                    buf -> new SpicyPayload(buf.readInt())
            );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
