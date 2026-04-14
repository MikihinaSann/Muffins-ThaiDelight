package net.firemuffin303.thaidelight.neoforge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.neoforge.*;
import net.firemuffin303.thaidelight.neoforge.common.capabilities.ModAttachmentTypes;
import net.firemuffin303.thaidelight.neoforge.network.SpicyPayload;
import net.firemuffin303.thaidelight.neoforge.network.ThaiDelightPayloadHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;

@Mod(ThaiDelightCommon.MOD_ID)
public class ThaiDelightNeoForge {
    private static final DeferredRegister<?>[] REGISTERS = {
            ModSoundEventsImpl.SOUND_EVENT,
            ModBlockStateProviderTypesImpl.BLOCK_STATE_PROVIDER_TYPE,
            ModTreeDecoratorTypesImpl.TREE_DECORATOR_TYPE,
            ModFeaturesImpl.FOLIAGE_PLACER,
            ModFeaturesImpl.TRUNK_PLACER,
            ModEntityTypesImpl.ENTITY_TYPES,
            ModMobEffectsImpl.MOB_EFFECT,
            ModMobEffectsImpl.POTION,
            ModMenuTypeImpl.MENU,
            ModBlockEntityTypesImpl.BLOCK_ENTITY,
            ModBlocksImpl.BLOCK,
            ModItemsImpl.CREATIVE_TAB,
            ModItemsImpl.ITEMS,
            ModRecipesImpl.RECIPE_TYPE,
            ModRecipesImpl.RECIPE_SERIALIZER,
            ModAttachmentTypes.ATTACHMENT_TYPES
    };

    public ThaiDelightNeoForge(IEventBus eventBus) {
        ThaiDelightCommon.init();
        Arrays.stream(REGISTERS).forEach(r -> r.register(eventBus));
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::registerEntityAttribute);
        eventBus.addListener(this::registerPayloads);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ThaiDelightCommon::postInit);
    }

    private void registerEntityAttribute(EntityAttributeCreationEvent event) {
        ModEntityTypes.registerAttribute((entity, attribute) -> event.put(entity, attribute.build()));
    }

    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                SpicyPayload.TYPE,
                SpicyPayload.STREAM_CODEC,
                ThaiDelightPayloadHandler::handleSpicyPayload
        );
    }
}
