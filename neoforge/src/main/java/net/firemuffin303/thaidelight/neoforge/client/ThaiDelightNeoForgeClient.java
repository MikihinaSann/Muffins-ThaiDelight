package net.firemuffin303.thaidelight.neoforge.client;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.ThaiDelightCommonClient;
import net.firemuffin303.thaidelight.client.renderer.component.SackTooltipComponent;
import net.firemuffin303.thaidelight.client.sceens.MortarScreen;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;

import java.util.List;

@EventBusSubscriber(modid = ThaiDelightCommon.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ThaiDelightNeoForgeClient {

    public ThaiDelightNeoForgeClient() {
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(ModMenuType.MORTAR.get(), MortarScreen::new));
    }

    @SubscribeEvent
    public static void registerEntityModelLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ThaiDelightCommonClient.entityModelRegister(event::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        ThaiDelightCommonClient.entityRendererRegister(event::registerEntityRenderer);
        ThaiDelightCommonClient.blockEntityRenderRegister(event::registerBlockEntityRenderer);
    }

    @SubscribeEvent
    public static void registerColorItem(RegisterColorHandlersEvent.Item event) {
        event.register((itemStack, i) -> i > 0 ? -1 : ModUtils.getColor(itemStack), ModItems.COCONUT_MILK_ICE_CREAM.get());
        event.register((itemStack, i) -> ModUtils.getColor(itemStack), ModItems.KHANOM_CHAN.get());
        event.register((itemStack, i) -> FoliageColor.getDefaultColor(), ModItems.DURIAN_LEAVES.get(), ModItems.MANGO_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerRecipeBook(RegisterRecipeBookCategoriesEvent event) {
        final RecipeBookType MORTAR_BOOK_TYPE = RecipeBookType.create("MORTAR_RECIPE_BOOK_TYPE");
        final RecipeBookCategories MORTAR_SEARCH = RecipeBookCategories.create("MORTAR_SEARCH", new ItemStack(Items.COMPASS));
        final RecipeBookCategories MORTAR_MEALS = RecipeBookCategories.create("MORTAR_MEALS", new ItemStack(ModItems.SOMTAM_FEAST.get()));
        final RecipeBookCategories MORTAR_MISC = RecipeBookCategories.create("MORTAR_MISC", new ItemStack(ModItems.PEPPER.get()), new ItemStack(Items.BONE_MEAL));

        event.registerBookCategories(MORTAR_BOOK_TYPE, List.of(MORTAR_SEARCH, MORTAR_MEALS, MORTAR_MISC));
        event.registerAggregateCategory(MORTAR_SEARCH, List.of(MORTAR_MEALS, MORTAR_MISC));
        event.registerRecipeCategoryFinder(ModRecipes.MORTAR.get(), recipe -> {
            if (recipe instanceof MortarRecipe mortarRecipe) {
                MortarRecipeBookTab mortarRecipeBookTab = mortarRecipe.getRecipeBookTab();
                if (mortarRecipeBookTab != null) {
                    return switch (mortarRecipeBookTab) {
                        case MEALS -> MORTAR_MEALS;
                        case MISC -> MORTAR_MISC;
                    };
                }
            }
            return MORTAR_MISC;
        });
    }

    @SubscribeEvent
    public static void registerTooltipComponent(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(SackTooltipComponent.SackToolTip.class, SackTooltipComponent::new);
    }
}
