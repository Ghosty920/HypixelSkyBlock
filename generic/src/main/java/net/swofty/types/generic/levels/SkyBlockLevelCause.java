package net.swofty.types.generic.levels;

import lombok.SneakyThrows;
import net.swofty.types.generic.collection.CollectionCategories;
import net.swofty.types.generic.collection.CollectionCategory;
import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.item.impl.Accessory;
import net.swofty.types.generic.levels.causes.*;
import net.swofty.types.generic.skill.SkillCategories;
import net.swofty.types.generic.user.fairysouls.FairySoulExchangeLevels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkyBlockLevelCause {
    private static final Map<String, net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr> CAUSES = new HashMap<>();

    @SneakyThrows
    public static void initializeCauses() {
        // Register all Skill causes
        for (SkillCategories category : SkillCategories.values()) {
            for (int i = 1; i <= category.asCategory().getHighestLevel(); i++) {
                CAUSES.put("skill-" + category.name() + "-" + i, new SkillLevelCause(category, i));
            }
        }

        // Register all accessory causes
        for (ItemType itemType : ItemType.values()) {
            if (itemType.clazz == null) continue;
            if (itemType.clazz.newInstance() instanceof Accessory) {
                CAUSES.put("accessory-" + itemType.name(), new NewAccessoryLevelCause(itemType));
            }
        }

        // Register all level causes up to 500
        for (int i = 0; i <= 500; i++) {
            CAUSES.put("level-" + i, new LevelCause(i));
        }

        // Register all collection causes
        for (ItemType itemType : ItemType.values()) {
            CollectionCategory category = CollectionCategories.getCategory(itemType);
            if (category == null) continue;

            for (CollectionCategory.ItemCollection collection : category.getCollections()) {
                for (CollectionCategory.ItemCollectionReward reward : collection.rewards()) {
                    CAUSES.put("collection-" + itemType.name() + "-" + collection.getPlacementOf(reward),
                            new CollectionLevelCause( itemType, collection.getPlacementOf(reward)));
                }
            }
        }

        // Register all Fairy Exchange rewards
        for (int i = 1; i <= FairySoulExchangeLevels.values().length; i++) {
            CAUSES.put("fairy-soul-exchange-" + i, new FairySoulExchangeLevelCause(i));
        }
    }

    public static Double getTotalXP() {
        double total = 0;
        for (net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            total += cause.xpReward();
        }
        return total;
    }

    public static FairySoulExchangeLevelCause getFairySoulExchangeCause(int exchangeCount) {
        for (net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof FairySoulExchangeLevelCause exchangeCause) {
                if (exchangeCause.getExchangeCount() == exchangeCount) {
                    return exchangeCause;
                }
            }
        }
        return null;
    }
    public static CollectionLevelCause getCollectionCause(ItemType itemType, int level) {
        for (net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof CollectionLevelCause collectionCause) {
                if (collectionCause.getType() == itemType && collectionCause.getLevel() == level) {
                    return collectionCause;
                }
            }
        }
        return null;
    }

    public static int getAmountOfCauses() {
        return CAUSES.size();
    }

    public static LevelCause getLevelCause(int level) {
        for (net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof LevelCause levelCause) {
                if (levelCause.getLevel() == level) {
                    return levelCause;
                }
            }
        }
        return null;
    }

    public static NewAccessoryLevelCause getAccessoryCause(ItemType itemType) {
        for (net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof NewAccessoryLevelCause accessoryCause) {
                if (accessoryCause.itemType == itemType) {
                    return accessoryCause;
                }
            }
        }
        return null;
    }

    public static SkillLevelCause getSkillCause(SkillCategories category, int level) {
        for (net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof SkillLevelCause skillCause) {
                if (skillCause.getCategory() == category && skillCause.getLevel() == level) {
                    return skillCause;
                }
            }
        }
        return null;
    }

    public static net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr getCause(String key) {
        return CAUSES.get(key);
    }

    public static String getKey(net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr cause) {
        for (Map.Entry<String, net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr> entry : CAUSES.entrySet()) {
            if (entry.getValue() == cause) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Cause not found " + cause.getClass().getSimpleName());
    }

    public static List<net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr> getSkillCauses(SkillCategories category, int levelUpToInclusive) {
        List<net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr> causes = new ArrayList<>();
        for (net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof SkillLevelCause skillCause) {
                if (skillCause.getCategory() == category && skillCause.getLevel() <= levelUpToInclusive) {
                    causes.add(skillCause);
                }
            }
        }
        return causes;
    }
}
