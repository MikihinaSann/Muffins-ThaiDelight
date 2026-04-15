package net.firemuffin303.thaidelight.common.registry;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.ArrayList;
import java.util.List;

public class ModVillagerTrades {
    public static List<ModVillagerTrade> trades(){
        return new ArrayList<>();
    }

    public static List<MerchantOffer> wanderTrade(){
        return new ArrayList<>();
    }

    public record ModVillagerTrade(VillagerProfession villagerProfession, int level, MerchantOffer merchantOffer){

        public ModVillagerTrade(VillagerProfession villagerProfession, int level, MerchantOffer merchantOffer){
            this.villagerProfession = villagerProfession;
            this.level = level;
            this.merchantOffer = merchantOffer;
        }
    }
}
