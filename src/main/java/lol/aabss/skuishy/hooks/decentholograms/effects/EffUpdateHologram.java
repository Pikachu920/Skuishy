package lol.aabss.skuishy.hooks.decentholograms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Decent Holograms - Update Hologram")
@Description("Updates a hologram.")
@Examples({
        "update hologram {_holo}"
})
@Since("1.7")
@RequiredPlugins("DecentHolograms")

public class EffUpdateHologram extends Effect {

    static{
        if (Bukkit.getServer().getPluginManager().getPlugin("DecentHolograms") != null) {
            Skript.registerEffect(EffUpdateHologram.class,
                    "update [(decent [hologram[s]]|dh)] [hologram] %hologram%"
            );
        }
    }

    private Expression<Hologram> holo;

    @Override
    protected void execute(@NonNull Event e) {
        Hologram holo = this.holo.getSingle(e);
        if (holo != null) {
            DHAPI.updateHologram(holo.getName());
        }
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "update hologram";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        holo = (Expression<Hologram>) exprs[0];
        return true;
    }
}
