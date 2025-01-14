package net.swofty.types.generic.levels;

import lombok.SneakyThrows;
import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.item.impl.Accessory;
import net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr;
import net.swofty.types.generic.levels.causes.LevelCause;
import net.swofty.types.generic.levels.causes.NewAccessoryLevelCause;
import net.swofty.types.generic.levels.causes.SkillLevelCause;
import net.swofty.types.generic.skill.SkillCategories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkyBlockLevelCause {
    private static final Map<String, SkyBlockLevelCauseAbstr> CAUSES = new HashMap<>();

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
    }

    public static Double getTotalXP() {
        double total = 0;
        for (SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            total += cause.xpReward();
        }
        return total;
    }

    public static int getAmountOfCauses() {
        return CAUSES.size();
    }

    public static LevelCause getLevelCause(int level) {
        for (SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof LevelCause levelCause) {
                if (levelCause.getLevel() == level) {
                    return levelCause;
                }
            }
        }
        return null;
    }

    public static NewAccessoryLevelCause getAccessoryCause(ItemType itemType) {
        for (SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof NewAccessoryLevelCause accessoryCause) {
                if (accessoryCause.itemType == itemType) {
                    return accessoryCause;
                }
            }
        }
        return null;
    }

    public static SkillLevelCause getSkillCause(SkillCategories category, int level) {
        for (SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof SkillLevelCause skillCause) {
                if (skillCause.getCategory() == category && skillCause.getLevel() == level) {
                    return skillCause;
                }
            }
        }
        return null;
    }

    public static SkyBlockLevelCauseAbstr getCause(String key) {
        return CAUSES.get(key);
    }

    public static String getKey(SkyBlockLevelCauseAbstr cause) {
        for (Map.Entry<String, SkyBlockLevelCauseAbstr> entry : CAUSES.entrySet()) {
            if (entry.getValue() == cause) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static List<SkyBlockLevelCauseAbstr> getSkillCauses(SkillCategories category, int levelUpToInclusive) {
        List<SkyBlockLevelCauseAbstr> causes = new ArrayList<>();
        for (SkyBlockLevelCauseAbstr cause : CAUSES.values()) {
            if (cause instanceof SkillLevelCause skillCause) {
                if (skillCause.getCategory() == category && skillCause.getLevel() <= levelUpToInclusive) {
                    causes.add(skillCause);
                }
            }
        }
        return causes;
    }
}
