package net.totobirdcreations.mobbossbars.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.totobirdcreations.mobbossbars.MobBossBar;
import net.totobirdcreations.mobbossbars.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;


@Mixin(Entity.class)
abstract class EntityMixin {

    @Unique
    private final Entity self = (Entity)(Object) this;

    @Unique
    private ModConfig.ModConfigMobBossBar mobBossBarConfig = null;
    @Unique
    private MobBossBar                    mobBossBar       = null;

    @Unique
    private void createMobBossBarIfNeeded() {
        if (this.mobBossBarConfig == null) {
            this.mobBossBarConfig = ModConfig.getModConfigMobBossBar(EntityType.getId(self.getType()));
        }
        if (this.mobBossBar == null) {
            this.mobBossBar = this.mobBossBarConfig.toMobBossBar(self.getDisplayName());
        }
    }


    @Unique
    private boolean canOperate() {
        return this.mobBossBarConfig != null && this.mobBossBar != null && this.mobBossBarConfig.enabled;
    }

    @Inject(method = "setCustomName", at = @At("RETURN"))
    private void setCustomName(Text name, CallbackInfo ci) {
        if (this.canOperate()) {
            this.mobBossBar.setName(self.getDisplayName());
        }
    }


    @Unique
    private final List<ServerPlayerEntity> trackingPlayers = new ArrayList<>();

    @Inject(method = "onStartedTrackingBy", at = @At("RETURN"))
    private void onStartedTrackingBy(ServerPlayerEntity player, CallbackInfo ci) {
        this.createMobBossBarIfNeeded();
        trackingPlayers.add(player);
    }

    @Inject(method = "onStoppedTrackingBy", at = @At("RETURN"))
    private void onStoppedTrackingBy(ServerPlayerEntity player, CallbackInfo ci) {
        trackingPlayers.remove(player);
        if (this.canOperate()) {
            this.mobBossBar.removePlayer(player);
        }
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(CallbackInfo ci) {
        if (this.canOperate()) {
            for (ServerPlayerEntity trackingPlayer : this.trackingPlayers) {
                if (MobBossBar.testShouldHide(self, trackingPlayer)) {
                    this.mobBossBar.removePlayer(trackingPlayer);
                } else {
                    this.mobBossBar.addPlayer(trackingPlayer);
                }
            }
            if (self instanceof LivingEntity selfLiving) {
                this.mobBossBar.setPercent(selfLiving.getHealth() / selfLiving.getMaxHealth());
            } else {
                this.mobBossBar.setPercent(0.0f);
            }
        }
    }

}
