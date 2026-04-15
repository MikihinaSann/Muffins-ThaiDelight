package net.firemuffin303.thaidelight.common.item.equipments;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;

public class DurianHelmetItem extends ArmorItem {
    public DurianHelmetItem() {
        super(ArmorMaterials.TURTLE, Type.HELMET, new Properties().stacksTo(1));
    }
}
