package net.totobirdcreations.mobbossbars.mixin;

import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.totobirdcreations.mobbossbars.util.IServerBossBarMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(ServerBossBar.class)
abstract class ServerBossBarMixin implements IServerBossBarMixin {

    private final ServerBossBar self = ((ServerBossBar)(Object)this);

    private final boolean isBase        = self.getClass().equals(ServerBossBar.class);
    private       boolean isDragonFight = false;

    @Override
    public void setIsDragonFight(boolean value) {
        this.isDragonFight = value;
    }

    @Redirect(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V"))
    private void sendPacket(ServerPlayNetworkHandler instance, Packet<?> packet) {
        if (! (this.isBase || this.isDragonFight)) {
            instance.sendPacket(packet);
        }
    }

}
