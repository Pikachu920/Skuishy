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
import org.bukkit.event.Event;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Permissions - Edit Permission Attachment")
@Description("Sets/Removes a permission of a permission attachment or deletes a permission attachment.")
@Examples({
        "set permission of last permission attachment to \"essentials.fly\" with value true",
        "remove \"essentials.fly\" from last permission attachment",
        "delete last permission attachment"
})
@Since("2.1")
public class EffPermissionAttachment extends Effect {

    static {
        Skript.registerEffect(EffPermissionAttachment.class,
                "set permission of [perm[ission] attachment] %permissionattachment% to [perm[ission]] %string% with value %boolean%",
                "(remove|unset) [perm[ission]] %string% (of|from) %permissionattachment%",
                "(remove|delete) [perm[ission] attachment] %permissionattachment%",
                "add [perm[ission]] %string% with value %boolean% to permissions of [perm[ission] attachment] %permissionattachment%"
        );
    }

    private Expression<PermissionAttachment> attach;

    private Expression<String> perm;
    private Expression<Boolean> value;
    private boolean set;


    @Override
    protected void execute(@NotNull Event e) {
        PermissionAttachment attach = this.attach.getSingle(e);
        if (attach != null) {
            if (this.value == null) {
                if (this.perm == null) {
                    attach.remove();
                } else{
                    String perm = this.perm.getSingle(e);
                    if (perm != null) {
                        attach.unsetPermission(perm);
                    }
                }
            } else{
                if (this.perm != null) {
                    String perm = this.perm.getSingle(e);
                    Boolean value = this.value.getSingle(e);
                    if (perm != null && value != null) {
                        attach.setPermission(perm, value);
                    }
                }
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "permission attachment";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            attach = (Expression<PermissionAttachment>) exprs[0];
            perm = (Expression<String>) exprs[1];
            value = (Expression<Boolean>) exprs[2];
            set = true;
            return true;
        } else if (matchedPattern == 1){
            perm = (Expression<String>) exprs[0];
            attach = (Expression<PermissionAttachment>) exprs[1];
            return true;
        } else if (matchedPattern == 2) {
            attach = (Expression<PermissionAttachment>) exprs[0];
            return true;
        }
        perm = (Expression<String>) exprs[0];
        value = (Expression<Boolean>) exprs[1];
        attach = (Expression<PermissionAttachment>) exprs[2];
        return true;
    }
}
