package net.totobirdcreations.mobbossbars.predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.totobirdcreations.mobbossbars.MobBossBar;

import java.util.UUID;


public class AngerableBossBarPredicate implements MobBossBar.Predicate {

    @Override
    public boolean appliesTo(Entity entity) {return entity instanceof Angerable && ! (entity instanceof GoatEntity);}

    @Override
    public boolean hideFromPlayer(Entity entity, ServerPlayerEntity player) {
        if (entity instanceof net.minecraft.entity.mob.Angerable angerable) {
            UUID angryAt = angerable.getAngryAt();
            return ! player.getUuid().equals(angryAt);
        }
        return false;
    }

}
