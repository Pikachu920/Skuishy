package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.eclipse.jdt.annotation.NonNull;

@Name("Bee - Has Strung")
@Description("Returns true if the bee has stung.")
@Examples({
        "if target entity has stung:"
})
@Since("2.0")
public class CondHasStung extends PropertyCondition<Entity> {

    static{
        register(CondHasStung.class, PropertyType.HAVE, "stung", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Bee){
            return ((Bee) entity).hasStung();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "stung";
    }
}
