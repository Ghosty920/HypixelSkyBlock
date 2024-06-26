package net.swofty.types.generic.item.impl;

import net.swofty.types.generic.item.ReforgeType;
import net.swofty.types.generic.utility.groups.EnchantItemGroups;

import java.util.List;

public interface HelmetImpl extends ExtraRarityDisplay, Reforgable, Enchantable {
    default String getExtraRarityDisplay() {
        return " HELMET";
    }

    default ReforgeType getReforgeType() {
        return ReforgeType.ARMOR;
    }

    default boolean showEnchantLores() {
        return true;
    }

    default List<EnchantItemGroups> getEnchantItemGroups() {
        return List.of(EnchantItemGroups.ARMOR);
    }
}
