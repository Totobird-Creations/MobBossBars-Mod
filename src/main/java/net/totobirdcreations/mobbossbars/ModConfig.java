package net.totobirdcreations.mobbossbars;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;


public class ModConfig extends MidnightConfig {

    @MidnightConfig.Entry()
    public static Map<String, ModConfigMobBossBar> bossBars = new HashMap<>();

    public static final class ModConfigMobBossBar extends MidnightConfig {
        public static final ModConfigMobBossBar DEFAULT = new ModConfigMobBossBar(false, BossBar.Color.WHITE, BossBar.Style.PROGRESS);

        @MidnightConfig.Entry() public boolean       enabled;
        @MidnightConfig.Entry() public BossBar.Color colour;
        @MidnightConfig.Entry() public BossBar.Style style;
        @MidnightConfig.Entry() public boolean       darkenSky   = false;
        @MidnightConfig.Entry() public boolean       dragonMusic = false;
        @MidnightConfig.Entry() public boolean       thickenFog  = false;

        public ModConfigMobBossBar(
                boolean       enabled,
                BossBar.Color colour,
                BossBar.Style style
        ) {
            this.enabled = enabled;
            this.colour  = colour;
            this.style   = style;
        }

        public ModConfigMobBossBar(
                boolean       enabled,
                BossBar.Color colour,
                BossBar.Style style,
                boolean       darkenSky,
                boolean       dragonMusic,
                boolean       thickenFog
        ) {
            this.enabled     = enabled;
            this.colour      = colour;
            this.style       = style;
            this.darkenSky   = darkenSky;
            this.dragonMusic = dragonMusic;
            this.thickenFog  = thickenFog;
        }

        public ModConfigMobBossBar correctAndGet() {
            if (this.colour == null) { this.colour = DEFAULT.colour; }
            if (this.style  == null) { this.style  = DEFAULT.style;  }
            return this;
        }

        public MobBossBar toMobBossBar(Text name) {
            return (MobBossBar) new MobBossBar(name, this.colour, this.style)
                    .setDarkenSky(this.darkenSky)
                    .setDragonMusic(this.dragonMusic)
                    .setThickenFog(this.thickenFog);
        }

    }



    public static ModConfigMobBossBar getModConfigMobBossBar(Identifier identifier) {
        for (String key : bossBars.keySet()) {
            if (new Identifier(key).equals(identifier)) {
                return bossBars.get(key).correctAndGet();
            }
        }
        return ModConfigMobBossBar.DEFAULT;
    }

}
