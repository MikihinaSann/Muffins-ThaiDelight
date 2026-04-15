package net.firemuffin303.thaidelight.datagen;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.datagen.provider.ThaiDelightBiomeModifierProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ThaiDelightCommon.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (event.includeServer()) {
            event.createProvider((output, lookupProvider) -> new ThaiDelightBiomeModifierProvider(output, lookupProvider));
        }
    }
}
