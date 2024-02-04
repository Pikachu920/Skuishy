package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Goat;
import org.eclipse.jdt.annotation.NonNull;

@Name("Goat - Has Right Horn")
@Description("Returns true if the goat has the right horn.")
@Examples({
        "if last spawned goat has right horn:"
})
@Since("2.0")
public class CondHasRightHorn extends PropertyCondition<Entity> {

    static {
        register(CondHasRightHorn.class, PropertyType.HAVE, "[the] right horn", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Goat){
            return ((Goat) entity).hasRightHorn();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "right horn";
    }
}
