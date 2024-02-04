package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.NonNull;

@Name("Item - Has Trim")
@Description("Returns true if the item has a trim.")
@Examples({
        "if target entity has a trim:"
})
@Since("2.0")
public class CondHasTrim extends PropertyCondition<ItemStack> {

    static {
        register(CondHasTrim.class, PropertyType.HAVE, "[a] trim", "itemstacks");
    }

    @Override
    public boolean check(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta instanceof ArmorMeta){
            return ((ArmorMeta) meta).hasTrim();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "trim";
    }
}
