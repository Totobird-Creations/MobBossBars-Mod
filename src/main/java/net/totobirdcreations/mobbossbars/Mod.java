package net.totobirdcreations.mobbossbars;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.totobirdcreations.mobbossbars.predicate.AngerableBossBarPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Mod implements ModInitializer {
    public static final String ID     = "mobbossbars";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    @Override
    public void onInitialize() {

        // Set up config.
        ModConfig.bossBars.put(EntityType.getId(EntityType. GIANT          ).toString(), new ModConfig.ModConfigMobBossBar(false , BossBar.Color. GREEN  , BossBar.Style. PROGRESS   ));
        ModConfig.bossBars.put(EntityType.getId(EntityType. WARDEN         ).toString(), new ModConfig.ModConfigMobBossBar(false , BossBar.Color. BLUE   , BossBar.Style. NOTCHED_20 ));
        ModConfig.bossBars.put(EntityType.getId(EntityType. ENDER_DRAGON   ).toString(), new ModConfig.ModConfigMobBossBar(true  , BossBar.Color. PURPLE , BossBar.Style. PROGRESS   , false, true, true ));
        ModConfig.bossBars.put(EntityType.getId(EntityType. RAVAGER        ).toString(), new ModConfig.ModConfigMobBossBar(false , BossBar.Color. YELLOW , BossBar.Style. NOTCHED_20 ));
        ModConfig.bossBars.put(EntityType.getId(EntityType. ILLUSIONER     ).toString(), new ModConfig.ModConfigMobBossBar(false , BossBar.Color. YELLOW , BossBar.Style. NOTCHED_6  ));
        ModConfig.bossBars.put(EntityType.getId(EntityType. WITHER         ).toString(), new ModConfig.ModConfigMobBossBar(true  , BossBar.Color. PURPLE , BossBar.Style. PROGRESS   , true, false, false ));
        ModConfig.bossBars.put(EntityType.getId(EntityType. IRON_GOLEM     ).toString(), new ModConfig.ModConfigMobBossBar(false , BossBar.Color. WHITE  , BossBar.Style. NOTCHED_12 ));
        ModConfig.bossBars.put(EntityType.getId(EntityType. ELDER_GUARDIAN ).toString(), new ModConfig.ModConfigMobBossBar(false , BossBar.Color. WHITE  , BossBar.Style. PROGRESS   ));
        MidnightConfig.init(ID, ModConfig.class);

        // Register default predicates.
        MobBossBar.addPredicate(new AngerableBossBarPredicate());

    }

}