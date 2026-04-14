package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.advancement.SackCatchTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class ModCriteriaTriggers {
    public static final SackCatchTrigger SACK_CATCH = CriteriaTriggers.register(ThaiDelightCommon.modid("sack_catch").toString(), new SackCatchTrigger());

    public static void init(){}
}
