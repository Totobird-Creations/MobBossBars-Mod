package net.totobirdcreations.mobbossbars.mixin;

import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.raid.Raid;
import net.totobirdcreations.mobbossbars.Mod;
import net.totobirdcreations.mobbossbars.ModConfig;
import net.totobirdcreations.mobbossbars.util.IServerBossBarMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Raid.class)
abstract class RaidMixin {

    @Unique
    private final Raid self = (Raid)(Object) this;

    @Inject(method = "<init>(ILnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "RETURN"))
    private void init0(int id, ServerWorld world, BlockPos pos, CallbackInfo ci) {
        this.setup();
    }
    @Inject(method = "<init>(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/nbt/NbtCompound;)V", at = @At(value = "RETURN"))
    private void init1(ServerWorld world, NbtCompound nbt, CallbackInfo ci) {
        this.setup();
    }

    @Unique
    private void setup() {
        ModConfig.ModConfigMobBossBar config = ModConfig.getModConfigMobBossBar(Mod.RAID_BAR_ID);
        config.applyToBossBar(self.bar);
        ((IServerBossBarMixin) self.bar).setIsRaid(true);
    }

}
