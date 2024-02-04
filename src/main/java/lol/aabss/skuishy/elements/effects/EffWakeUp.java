package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Player - Wake Up")
@Description("Makes a player wake up and optionally sets their spawn.")
@Examples({
        "make player wake up and set their spawn"
})
@Since("2.0")
public class EffWakeUp extends Effect {

    static {
        Skript.registerEffect(EffWakeUp.class,
                "make %player% wake up [spawn:and set the[ir] spawn [location]]"
        );
    }

    boolean spawn;
    Expression<Player> player;

    @Override
    protected void execute(@NonNull Event e) {
        Player p = player.getSingle(e);
        if (p != null){
            p.wakeup(spawn);
        }
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "make player wakeup";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        spawn = parseResult.hasTag("spawn");
        player = (Expression<Player>) exprs[0];
        return false;
    }
}
