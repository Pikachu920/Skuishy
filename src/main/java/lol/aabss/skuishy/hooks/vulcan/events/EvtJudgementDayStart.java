package lol.aabss.skuishy.hooks.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import me.frep.vulcan.api.event.VulcanJudgementDayStartEvent;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Vulcan - On Judgement Day Start")
@Description("Called when the judgement day starts.")
@Examples({
        "on judge day start:"
})
@Since("1.9")
public class EvtJudgementDayStart extends SkriptEvent {

    static {
        Skript.registerEvent("on vulcan judgement day start event", EvtJudgementDayStart.class, VulcanJudgementDayStartEvent.class,
                "[vulcan] judge[ment] [day] start[ed]"
        );
    }

    @Override
    public boolean init(Literal<?> @NonNull [] args, int matchedPattern, SkriptParser.@NonNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NonNull Event event) {
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        assert e != null;
        return e.getEventName();
    }
}
