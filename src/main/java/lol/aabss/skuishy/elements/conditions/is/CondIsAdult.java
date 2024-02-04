package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.eclipse.jdt.annotation.NonNull;

@Name("Entity - Is Adult")
@Description("Returns true if the entity is an adult.")
@Examples({
        "if event-entity is an adult:"
})
@Since("2.0")
public class CondIsAdult extends PropertyCondition<Entity> {

    static {
        register(CondIsAdult.class,
                PropertyType.BE,
                "[a[n]] adult",
                "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Ageable){
            return ((Ageable) entity).isAdult();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "adult";
    }
}
