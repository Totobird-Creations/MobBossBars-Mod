package net.totobirdcreations.mobbossbars.mixin;

import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.totobirdcreations.mobbossbars.Mod;
import net.totobirdcreations.mobbossbars.ModConfig;
import net.totobirdcreations.mobbossbars.util.IServerBossBarMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(ServerBossBar.class)
abstract class ServerBossBarMixin implements IServerBossBarMixin {

    @Unique
    private final ServerBossBar self = ((ServerBossBar)(Object)this);

    @Unique
    private final boolean isBase        = self.getClass().equals(ServerBossBar.class);
    @Unique
    private       boolean isDragonFight = false;
    @Unique
    private       boolean isRaid        = false;

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Override
    public void setIsDragonFight(boolean value) {
        this.isDragonFight = value;
    }

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Override
    public void setIsRaid(boolean value) {
        this.isRaid = value;
    }

    @Redirect(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V"))
    private void sendPacket(ServerPlayNetworkHandler instance, Packet<?> packet) {
        if (! (this.isBase || this.isDragonFight) || (this.isRaid && ModConfig.getModConfigMobBossBar(Mod.RAID_BAR_ID).enabled)) {
            instance.sendPacket(packet);
        }
    }

}
