package net.swofty.types.generic.item.items.combat.slayer.zombie.craftable;

import net.swofty.types.generic.gems.Gemstone;
import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.item.MaterialQuantifiable;
import net.swofty.types.generic.item.ReforgeType;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.*;
import net.swofty.types.generic.item.impl.recipes.ShapedRecipe;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.user.statistics.ItemStatistic;
import net.swofty.types.generic.user.statistics.ItemStatistics;

import java.util.*;

public class AxeOfTheShreeded implements CustomSkyBlockItem, Craftable, Reforgable, ExtraRarityDisplay, GemstoneItem {
    @Override
    public SkyBlockRecipe<?> getRecipe() {
        Map<Character, MaterialQuantifiable> ingredientMap = new HashMap<>();
        ingredientMap.put('A', new MaterialQuantifiable(ItemType.REVENANT_VISCERA, 64));
        ingredientMap.put('B', new MaterialQuantifiable(ItemType.SHARD_OF_THE_SHREDDED, 1));
        ingredientMap.put('C', new MaterialQuantifiable(ItemType.REAPER_FALCHION, 1));
        List<String> pattern = List.of(
                "ABA",
                "BCB",
                "ABA");

        return new ShapedRecipe(SkyBlockRecipe.RecipeType.SLAYER, new SkyBlockItem(ItemType.AXE_OF_THE_SHREDDED), ingredientMap, pattern);
    }

    @Override
    public ItemStatistics getStatistics() {
        return ItemStatistics.builder()
                .with(ItemStatistic.DAMAGE, 140D)
                .with(ItemStatistic.STRENGTH, 115D)
                .build();
    }

    @Override
    public ArrayList<String> getLore(SkyBlockPlayer player, SkyBlockItem item) {
        return new ArrayList<>(Arrays.asList(
                "§7Heal §c50❤ §7per hit.",
                "§7Deal §a+250% §7damage to Zombies§7.",
                "§7§7Receive §a25% §7less damage",
                "§7from Zombies§7 when held.",
                "",
                "§6Ability: Throw §e§lRIGHT CLICK",
                "§7Throw your axe damaging all",
                "§7enemies in its path dealing",
                "§7§c10%§7 melee damage.",
                "§7Consecutive throws stack §c2x",
                "§c§7damage but cost §92x §7mana up",
                "§7to 16x",
                "§8Mana Cost: §320"));
    }

    @Override
    public ReforgeType getReforgeType() {
        return ReforgeType.SWORDS;
    }

    @Override
    public String getExtraRarityDisplay() {
        return " SWORD";
    }

    @Override
    public Map<Gemstone.Slots, Integer> getGemstoneSlots() {
        return Map.of(
                Gemstone.Slots.JASPER, 50000, //+20 Fine Jasper
                Gemstone.Slots.COMBAT, 100000 //+40 Fine Jasper, Sapphire, Ruby and Amethyst
        );
    }
}
