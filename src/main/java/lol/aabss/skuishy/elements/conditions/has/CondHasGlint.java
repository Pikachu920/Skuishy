package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Trident;
import org.eclipse.jdt.annotation.NonNull;

@Name("Trident - Has Glint")
@Description("Returns true if the trident (entity) has glint.")
@Examples({
        "if target entity has caravan trail:"
})
@Since("2.0")
public class CondHasGlint extends PropertyCondition<Entity> {

    static {
        register(CondHasGlint.class, PropertyType.HAVE, "glint", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Trident){
            return ((Trident) entity).hasGlint();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "glint";
    }
}
