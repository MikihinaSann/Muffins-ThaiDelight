package net.firemuffin303.thaidelight.util.neoforge;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.neoforge.common.capabilities.ModAttachmentTypes;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModSounds;

import java.util.function.Supplier;

public class PlatformUtilImpl {

    public static TagKey<Item> shearTag() {
        return Tags.Items.TOOLS_SHEAR;
    }

    public static Block richSoilBlock() {
        return ModBlocks.RICH_SOIL.get();
    }

    public static Block richSoilFarmBlock() {
        return ModBlocks.RICH_SOIL_FARMLAND.get();
    }

    public static Item.Properties bowlFoodItem(FoodProperties foodProperties) {
        return ModItems.bowlItem(foodProperties);
    }

    public static Supplier<Block> cabinetBlock(BlockBehaviour.Properties properties) {
        return () -> new CabinetBlock(properties);
    }

    public static Block farmerDelightRope() {
        return ModBlocks.ROPE.get();
    }

    public static boolean tomatoVineConfig() {
        return Configuration.ENABLE_TOMATO_VINE_CLIMBING_TAGGED_ROPES.get();
    }

    public static String defaultTomatoVineConfig() {
        return Configuration.DEFAULT_TOMATO_VINE_ROPE.get();
    }

    public static SoundEvent tomatoPickSound() {
        return ModSounds.ITEM_TOMATO_PICK_FROM_BUSH.get();
    }

    public static RecipeBookType getMortarBookType() {
        return RecipeBookType.valueOf("MORTAR_RECIPE_BOOK_TYPE");
    }

    public static Supplier<MobEffect> getComfort() {
        return DeferredHolder.create(net.minecraft.core.registries.Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath("farmersdelight", "comfort"));
    }

    public static Supplier<MobEffect> getNourishmentEffect() {
        return DeferredHolder.create(net.minecraft.core.registries.Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath("farmersdelight", "nourishment"));
    }

    public static Supplier<Item> getTreeBarkItem() {
        return DeferredHolder.create(net.minecraft.core.registries.Registries.ITEM, ResourceLocation.fromNamespaceAndPath("farmersdelight", "tree_bark"));
    }

    public static UseAnim getDurianCatcherUseAnim() {
        return UseAnim.NONE;
    }

    public static HumanoidModel.ArmPose getSackShoulderPose() {
        return HumanoidModel.ArmPose.ITEM;
    }

    public static HumanoidModel.ArmPose getDurianCatcherHoldArmPose() {
        return HumanoidModel.ArmPose.ITEM;
    }

    public static HumanoidModel.ArmPose getDurianCatcherSwingArmPose() {
        return HumanoidModel.ArmPose.ITEM;
    }

    public static void setSpicyTime(int value, LivingEntity livingEntity) {
        ((IAttachmentHolder) livingEntity).getData(ModAttachmentTypes.SPICY.get()).setTimer(value);
    }

    public static void addSpicyTime(int value, LivingEntity livingEntity) {
        ((IAttachmentHolder) livingEntity).getData(ModAttachmentTypes.SPICY.get()).addTimer(value);
    }

    public static int getSpicyTime(LivingEntity livingEntity) {
        return ((IAttachmentHolder) livingEntity).getData(ModAttachmentTypes.SPICY.get()).getTimer();
    }

    public static ModUtils.DurianComponentSupplier getDurianHeatComponent(LivingEntity livingEntity) {
        var durianHeat = ((IAttachmentHolder) livingEntity).getData(ModAttachmentTypes.DURIAN_HEAT.get());
        return new ModUtils.DurianComponentSupplier(durianHeat.getTimer(), durianHeat.isHeatUp());
    }

    public static void setDurianHeat(boolean value, LivingEntity livingEntity) {
        ((IAttachmentHolder) livingEntity).getData(ModAttachmentTypes.DURIAN_HEAT.get()).setHeat(value);
    }

    public static void addDurianHeatTime(int i, LivingEntity livingEntity) {
        ((IAttachmentHolder) livingEntity).getData(ModAttachmentTypes.DURIAN_HEAT.get()).addTimer(i);
    }
}
