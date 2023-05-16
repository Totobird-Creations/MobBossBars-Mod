package net.totobirdcreations.mobbossbars;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;


public class MobBossBar extends ServerBossBar {

    private final static List<Predicate> predicates = new ArrayList<>();

    public static void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public static boolean testShouldHide(Entity entity, ServerPlayerEntity player) {
        return predicates.stream().anyMatch(predicate -> predicate.appliesTo(entity) && predicate.hideFromPlayer(entity, player));
    }

    public interface Predicate {
        boolean appliesTo(Entity entity);
        boolean hideFromPlayer(Entity entity, ServerPlayerEntity player);
    }



    public MobBossBar(Text displayName, Color color, Style style) {
        super(displayName, color, style);
    }

}
