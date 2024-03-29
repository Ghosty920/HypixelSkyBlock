package net.swofty.types.generic.enchantment.impl;

import net.swofty.types.generic.enchantment.EnchantmentType;
import net.swofty.types.generic.enchantment.abstr.Ench;
import net.swofty.types.generic.enchantment.abstr.EnchFromTable;
import net.swofty.types.generic.event.value.SkyBlockValueEvent;
import net.swofty.types.generic.event.value.ValueUpdateEvent;
import net.swofty.types.generic.event.value.events.MiningValueUpdateEvent;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.attribute.AttributeHandler;
import net.swofty.types.generic.user.PlayerEnchantmentHandler;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.utility.groups.EnchantItemGroups;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnchantmentEfficiency extends SkyBlockValueEvent implements Ench, EnchFromTable {
    @Override
    public String getDescription(int level) {
        return "§7Grants §6+" + (30 + ((level - 1) * 20) + " Mining Speed§7.");
    }

    @Override
    public ApplyLevels getLevelsToApply(SkyBlockPlayer player) {
        return new ApplyLevels(new HashMap<>(Map.of(
                1, 9,
                2, 13,
                3, 18,
                4, 23,
                5, 27
        )));
    }

    @Override
    public List<EnchantItemGroups> getGroups() {
        return Collections.singletonList(EnchantItemGroups.PICKAXE);
    }

    @Override
    public TableLevels getLevelsFromTableToApply(SkyBlockPlayer player) {
        return new TableLevels(new HashMap<>(Map.of(
                1, 10,
                2, 15,
                3, 20,
                4, 25,
                5, 30
        )));
    }

    @Override
    public int getRequiredBookshelfPower() {
        return 0;
    }

    @Override
    public Class<? extends ValueUpdateEvent> getValueEvent() {
        return MiningValueUpdateEvent.class;
    }

    @Override
    public void run(ValueUpdateEvent event) {
        PlayerEnchantmentHandler enchantmentHandler = event.getPlayer().getEnchantmentHandler();

        PlayerEnchantmentHandler.EnchantmentHandlerResponse response = enchantmentHandler.getItemWithHighestLevelOf(
                EnchantmentType.EFFICIENCY,
                PlayerEnchantmentHandler.EnchantedItemSource.HAND
        );

        if (response == null) return;

        int enchantLevel = response.level();
        double initialValue = (double) event.getValue();

        double newValue = initialValue - (initialValue * (0.3 + ((enchantLevel - 1) * 0.2)));
        event.setValue(newValue);
    }
}
