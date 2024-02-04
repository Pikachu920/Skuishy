package lol.aabss.skuishy.hooks.vivecraft;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.NonNull;
import org.skriptlang.skript.lang.converter.Converters;
import org.vivecraft.VivePlayer;

public class Types {
    static{
        if (Bukkit.getServer().getPluginManager().getPlugin("Vivecraft-Spigot-Extensions") != null) {
            Classes.registerClass(new ClassInfo<>(VivePlayer.class, "viveplayer")
                    .user("vive ?players?")
                    .name("ViveCraft - Vive Player")
                    .description("Represents a ViveCraft player.")
                    .since("1.9")
                    .parser(new Parser<>() {

                        @Override
                        public boolean canParse(@NonNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NonNull String toVariableNameString(VivePlayer player) {
                            return player.player.getName();
                        }

                        @Override
                        public @NonNull String toString(VivePlayer player, int flags) {
                            return toVariableNameString(player);
                        }
                    })
            );
            Converters.registerConverter(VivePlayer.class, Player.class, e -> e.player);
        }
    }
}
