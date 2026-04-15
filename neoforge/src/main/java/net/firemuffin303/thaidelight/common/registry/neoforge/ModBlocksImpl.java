package net.firemuffin303.thaidelight.common.registry.neoforge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.neoforge.common.block.feast.*;
import net.firemuffin303.thaidelight.neoforge.common.block.vegetation.NeoForgeBuddingButterflyPeaBlock;
import net.firemuffin303.thaidelight.neoforge.common.block.vegetation.pepper.NeoForgeBuddingPepperBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import java.util.function.Supplier;

public class ModBlocksImpl {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(Registries.BLOCK, ThaiDelightCommon.MOD_ID);

    public static Supplier<Block> createSomtamFeast() {
        return SomtamFeastBlock::new;
    }

    public static Supplier<Block> createLarbFeast() {
        return LarbFeastBlock::new;
    }

    public static Supplier<Block> createCrabFriedRice() {
        return CrabFriedRiceFeastBlock::new;
    }

    public static Supplier<Block> createCoconutPieBlock() {
        return CoconutPieBlock::new;
    }

    public static Supplier<Block> createPhatKaphraoBlock() {
        return PhatKaphraoFeastBlock::new;
    }

    public static Supplier<Block> createMangoStickyRiceBlock() {
        return MangoStickyRiceFeastBlock::new;
    }

    public static Supplier<Block> createOmeletteBlock(Supplier<Item> itemSupplier) {
        return () -> new OmeletteFeastBlock(itemSupplier);
    }

    public static Supplier<Block> createPineappleFeastBlock() {
        return PineappleFriedRiceFeastBlock::new;
    }

    public static Supplier<Block> createWildCropBlock(Holder<MobEffect> stewEffect, int effectDuration, BlockBehaviour.Properties properties) {
        return () -> new WildCropBlock(stewEffect, effectDuration, properties);
    }

    public static Supplier<Block> register(String id, Supplier<Block> block) {
        return BLOCK.register(id, block);
    }

    public static Supplier<Block> cabinetBlock(BlockBehaviour.Properties properties) {
        return () -> new CabinetBlock(properties);
    }

    public static Supplier<Block> createBuddingPepperBlock(BlockBehaviour.Properties properties) {
        return () -> new NeoForgeBuddingPepperBlock(properties);
    }

    public static Supplier<Block> createPieBlock(BlockBehaviour.Properties properties, Supplier<Item> supplier) {
        return () -> new PieBlock(properties, supplier);
    }

    public static Supplier<Block> createButterflyPeaBlock() {
        return NeoForgeBuddingButterflyPeaBlock::new;
    }

    public static Supplier<Block> createStandingSignBlock(ResourceLocation resourceLocation, BlockBehaviour.Properties properties) {
        return () -> new StandingSignBlock(WoodType.OAK, properties);
    }

    public static Supplier<Block> createWallSignBlock(ResourceLocation resourceLocation, BlockBehaviour.Properties properties) {
        return () -> new WallSignBlock(WoodType.OAK, properties);
    }

    public static Supplier<Block> createHangingSignBlock(ResourceLocation id, ResourceLocation id2, BlockBehaviour.Properties properties) {
        return () -> new CeilingHangingSignBlock(WoodType.OAK, properties);
    }

    public static Supplier<Block> createHangingWallSignBlock(ResourceLocation id, ResourceLocation id2, BlockBehaviour.Properties properties) {
        return () -> new WallHangingSignBlock(WoodType.OAK, properties);
    }

    public static Supplier<Block> getThaiDelightBlock(String id, Supplier<Block> blockSupplier) {
        return DeferredHolder.create(Registries.BLOCK, ThaiDelightCommon.modid(id));
    }

    public static BlockBehaviour.Properties dropLike(Supplier<Block> blockSupplier) {
        return BlockBehaviour.Properties.of().lootFrom(blockSupplier);
    }
}
