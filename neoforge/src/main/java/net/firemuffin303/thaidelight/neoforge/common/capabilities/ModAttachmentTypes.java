package net.firemuffin303.thaidelight.neoforge.common.capabilities;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ThaiDelightCommon.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<SpicyData>> SPICY =
            ATTACHMENT_TYPES.register("spicy", () ->
                    AttachmentType.builder(SpicyData::new).serialize(SpicyData.CODEC).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<DurianHeatData>> DURIAN_HEAT =
            ATTACHMENT_TYPES.register("durian_heat", () ->
                    AttachmentType.builder(DurianHeatData::new).serialize(DurianHeatData.CODEC).build());
}
