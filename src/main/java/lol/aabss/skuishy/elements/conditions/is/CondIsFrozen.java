package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
@Name("TickManager - Is Frozen")
@Description("Returns true if the server is frozen.")
@Examples({
        "if the server's tickrate is frozen:",
        "if target entity is not frozen:"
})
@Since("1.9")
public class CondIsFrozen extends Condition {

    static {
        if (Skript.classExists("org.bukkit.ServerTickManager")){
            Skript.registerCondition(CondIsFrozen.class,
                    "[the] ((server|game)['s] [tick[(s|[( |-)]rate)]]|%-entity%) (is|are) frozen",
                    "[the] ((server|game)['s] [tick[(s|[( |-)]rate)]]|%-entity%) (is|are)( not|n't) frozen"
            );
        }
    }

    private boolean is;
    private Expression<Entity> entity;

    @Override
    public boolean check(@NonNull Event e) {
        Entity en = entity.getSingle(e);
        if (en != null){
            if (is){
                return Bukkit.getServer().getServerTickManager().isFrozen(en);
            }
            return !Bukkit.getServer().getServerTickManager().isFrozen(en);
        }
        if (is){
            return Bukkit.getServer().getServerTickManager().isFrozen();
        }
        return !Bukkit.getServer().getServerTickManager().isFrozen();
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return ((entity != null) ? "entity" : "ticks") + "ticks are frozen";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        is = matchedPattern == 0;
        entity = (Expression<Entity>) exprs[0];
        return true;
    }
}
