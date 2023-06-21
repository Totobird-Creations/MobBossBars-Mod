package net.totobirdcreations.mobbossbars.mixin;

import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.totobirdcreations.mobbossbars.util.IServerBossBarMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {

    private final EnderDragonFight self = (EnderDragonFight)(Object) this;

    @Inject(method = "<init>(Lnet/minecraft/server/world/ServerWorld;JLnet/minecraft/entity/boss/dragon/EnderDragonFight$Data;Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "RETURN"))
    private void init(ServerWorld world, long gatewaysSeed, EnderDragonFight.Data data, BlockPos origin, CallbackInfo ci) {
        ((IServerBossBarMixin) self.bossBar).setIsDragonFight(true);
    }

}
