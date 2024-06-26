package net.swofty.types.generic.item.items.combat.slayer.enderman.craftable;

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

public class Terminator implements CustomSkyBlockItem, Craftable, BowImpl, NotFinishedYet {
    @Override
    public SkyBlockRecipe<?> getRecipe() {
        Map<Character, MaterialQuantifiable> ingredientMap = new HashMap<>();
        ingredientMap.put('A', new MaterialQuantifiable(ItemType.TESSELLATED_ENDER_PEARL, 4));
        ingredientMap.put('B', new MaterialQuantifiable(ItemType.NULL_BLADE, 1));
        ingredientMap.put('C', new MaterialQuantifiable(ItemType.TARANTULA_SILK, 64));
        ingredientMap.put('D', new MaterialQuantifiable(ItemType.JUDGEMENT_CORE, 1));
        ingredientMap.put('E', new MaterialQuantifiable(ItemType.BRAIDED_GRIFFIN_FEATHER, 4));
        List<String> pattern = List.of(
                "ABC",
                "BDE",
                "ABC");

        return new ShapedRecipe(SkyBlockRecipe.RecipeType.SLAYER, new SkyBlockItem(ItemType.TERMINATOR), ingredientMap, pattern);
    }

    @Override
    public ItemStatistics getStatistics() {
        return ItemStatistics.builder()
                .with(ItemStatistic.DAMAGE, 310D)
                .with(ItemStatistic.STRENGTH, 50D)
                .with(ItemStatistic.CRIT_DAMAGE, 250D)
                //.with(ItemStatistic.BONUS_ATTACK_SPEED, 40D)
                //.with(ItemStatistic.SHOT_COOLDOWN, 0.5s)
                .build();
    }

    @Override
    public ArrayList<String> getLore(SkyBlockPlayer player, SkyBlockItem item) {
        return new ArrayList<>(Arrays.asList(
                "§7Shoots §b3 §7arrows at once.",
                "§7Can damage endermen.",
                "",
                "§cDivides your §9☣ Crit Chance §cby 4!",
                "",
                "§6Ability: Salvation  §e§lLEFT CLICK",
                "§7Can be casted after landing §63 §7hits.",
                "§7§7Shoot a beam, penetrating up",
                "§7to §e5 §7enemies.",
                "§7The beam always crits.",
                "§8Soulflow Cost: §3§31⸎",
                "",
                "§6Shortbow: Instantly shoots!"));
    }

    @Override
    public boolean shouldBeArrow() {
        return false;
    }

    @Override
    public void onBowShoot(SkyBlockPlayer player, SkyBlockItem item) {

    }
}
