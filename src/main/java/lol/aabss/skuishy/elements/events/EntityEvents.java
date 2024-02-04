package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.*;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.SkriptColor;
import com.destroystokyo.paper.event.entity.*;
import io.papermc.paper.event.entity.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

public class EntityEvents extends SkriptEvent {

    static{
        // arrow body count change
        Skript.registerEvent("arrow body count change", EntityEvents.class, ArrowBodyCountChangeEvent.class,
                        "arrow body count change[d]"
                )
                .description("Called when an arrow enters or exists an entity's body.")
                .examples("on arrow body count change:")
                .since("2.0");
        EventValues.registerEventValue(ArrowBodyCountChangeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(ArrowBodyCountChangeEvent e) {
                return e.getEntity();
            }
        }, 0);
        EventValues.registerEventValue(ArrowBodyCountChangeEvent.class, Boolean.class, new Getter<>() {
            @Override
            public Boolean get(ArrowBodyCountChangeEvent e) {
                return e.isReset();
            }
        }, 0);
        EventValues.registerEventValue(ArrowBodyCountChangeEvent.class, Integer.class, new Getter<>() {
            @Override
            public Integer get(ArrowBodyCountChangeEvent e) {
                return e.getNewAmount();
            }
        }, 1);
        EventValues.registerEventValue(ArrowBodyCountChangeEvent.class, Integer.class, new Getter<>() {
            @Override
            public Integer get(ArrowBodyCountChangeEvent e) {
                return e.getOldAmount();
            }
        }, 0);
        // bat sleep
        Skript.registerEvent("bat sleep toggle", EntityEvents.class, BatToggleSleepEvent.class,
                "bat [(state|mode)] toggle[d] sleep",
                "bat sleep [(state|mode)] toggle[d]"
        )
                .description("Called when a bat attempts to sleep or wake up from its slumber.\n" +
                        "If a Bat Toggle Sleep event is cancelled, the Bat will not toggle its sleep state.")
                .examples("on bat toggle:\n"+
                        "\tbroadcast \"%event-entity% has toggled sleep mode\"")
                .since("2.0");
        EventValues.registerEventValue(BatToggleSleepEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(BatToggleSleepEvent e) {
                return e.getEntity();
            }
        }, 0);
        EventValues.registerEventValue(BatToggleSleepEvent.class, Boolean.class, new Getter<>() {
            @Override
            public Boolean get(BatToggleSleepEvent e) {
                return e.isAwake();
            }
        }, 0);

        // creeper ignite
        Skript.registerEvent("creeper ignite", EntityEvents.class, CreeperIgniteEvent.class,
                "creeper ignite[d]",
                "creeper blow[n] up",
                "creeper explode[d]"
        )
                .description("Called when a Creeper is ignited either by a flint and steel, Creeper.ignite() or Creeper.setIgnited(boolean).")
                .examples("on creeper ignite:")
                .since("2.0");
        EventValues.registerEventValue(CreeperIgniteEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(CreeperIgniteEvent e) {
                return e.getEntity();
            }
        }, 0);

        // dye
        if (Skript.classExists("io.papermc.paper.event.entity.EntityDyeEvent")) {
            Skript.registerEvent("entity dye", EntityEvents.class, EntityDyeEvent.class,
                            "[entity] dye[d]"
                    )
                    .description("Called when an entity is dyed. Currently, this is called for org.bukkit.entity.Sheep being dyed, and org.bukkit.entity.Wolf/org.bukkit.entity.Cat collars being dyed")
                    .examples("on entity dye:")
                    .since("2.0");
            EventValues.registerEventValue(EntityDyeEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(EntityDyeEvent e) {
                    return e.getEntity();
                }
            }, 0);
            EventValues.registerEventValue(EntityDyeEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(EntityDyeEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(EntityDyeEvent.class, Color.class, new Getter<>() {
                @Override
                public Color get(EntityDyeEvent e) {
                    return SkriptColor.fromDyeColor(e.getColor());
                }
            }, 0);
        }

        // elder guardian appearance
        Skript.registerEvent("elder guardian appearance", EntityEvents.class, ElderGuardianAppearanceEvent.class,
                "[elder] guardian appear[(ance|ed)]"
        )
                .description("Is called when an ElderGuardian appears in front of a Player.")
                .examples("on elder guardian appearance:")
                .since("2.0");
        EventValues.registerEventValue(ElderGuardianAppearanceEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(ElderGuardianAppearanceEvent e) {
                return e.getEntity();
            }
        }, 0);
        EventValues.registerEventValue(ElderGuardianAppearanceEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(ElderGuardianAppearanceEvent e) {
                return e.getAffectedPlayer();
            }
        }, 0);

        // ender dragon phase change
        Skript.registerEvent("ender dragon phase change", EntityEvents.class, EnderDragonChangePhaseEvent.class,
                "(entity|[ender[ ]]dragon) phase change[d]"
        )
                .description("Called when an EnderDragon switches controller phase.")
                .examples("on dragon phase change:")
                .since("2.0");
        EventValues.registerEventValue(EnderDragonChangePhaseEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EnderDragonChangePhaseEvent e) {
                return e.getEntity();
            }
        }, 0);
        EventValues.registerEventValue(EnderDragonChangePhaseEvent.class, String.class, new Getter<>() {
            @Override
            public String get(EnderDragonChangePhaseEvent e) {
                return e.getNewPhase().name().toLowerCase().replaceAll("_", " ");
            }
        }, 0);

        // ender dragon fireball shoot
        Skript.registerEvent("ender dragon fireball shoot", EntityEvents.class, EnderDragonShootFireballEvent.class,
                        "[ender[ ]]dragon fireball sho[o]t",
                        "[ender[ ]]dragon sho[o]t fireball"
                )
                .description("Fired when an EnderDragon shoots a fireball")
                .examples("on dragon shot fireball:")
                .since("2.0");
        EventValues.registerEventValue(EnderDragonShootFireballEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EnderDragonShootFireballEvent e) {
                return e.getEntity();
            }
        }, 0);

        // ender dragon fireball hit
        Skript.registerEvent("ender dragon fireball hit", EntityEvents.class, EnderDragonFireballHitEvent.class,
                        "[ender[ ]]dragon fireball hit",
                        "[ender[ ]]dragon hit fireball"
                )
                .description("Fired when a DragonFireball collides with a block/entity and spawns an AreaEffectCloud")
                .examples("on dragon hit fireball:")
                .since("2.0");
        EventValues.registerEventValue(EnderDragonFireballHitEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EnderDragonFireballHitEvent e) {
                return e.getEntity();
            }
        }, 0);

        // ender man escape event
        Skript.registerEvent("ender man escape", EntityEvents.class, EndermanEscapeEvent.class,
                        "ender[ ]man escape[d]"
                )
                .description("Fired when an ender man escapes")
                .examples("on ender man escape:")
                .since("2.0");
        EventValues.registerEventValue(EndermanEscapeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EndermanEscapeEvent e) {
                return e.getEntity();
            }
        }, 0);

        // enter block
        Skript.registerEvent("entity enter block", EntityEvents.class, EntityEnterBlockEvent.class,
                "[entity] enter[ed] block"
        )
                .description("Called when an Entity enters a block and is stored in that block.\n" +
                        "This event is called for bees entering a bee hive. It is not called when a silverfish \"enters\" a stone block. For that listen to the EntityChangeBlockEvent.")
                .examples("on entity enter block:")
                .since("2.0");
        EventValues.registerEventValue(EntityEnterBlockEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EntityEnterBlockEvent e) {
                return e.getEntity();
            }
        }, 0);
        EventValues.registerEventValue(EntityEnterBlockEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(EntityEnterBlockEvent e) {
                return e.getBlock();
            }
        }, 0);

        // enter love mode
        Skript.registerEvent("enter love mode", EntityEvents.class, EntityEnterLoveModeEvent.class,
                        "[entity] [enter] love (mode|state)",
                "[entity] [in] love[d]"
                )
                .description("Called when an entity enters love mode. This can be cancelled but the item will still be consumed that was used to make the entity enter into love mode.")
                .examples("on love mode:")
                .since("2.0");
        EventValues.registerEventValue(EntityEnterLoveModeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EntityEnterLoveModeEvent e) {
                return e.getEntity();
            }
        }, 0);
        EventValues.registerEventValue(EntityEnterLoveModeEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(EntityEnterLoveModeEvent e) {
                return (Player) e.getHumanEntity();
            }
        }, 0);
        // entity exhaustion
        Skript.registerEvent("entity exhaustion", EntityEvents.class, EntityExhaustionEvent.class,
                        "[entity] exhaust[(ion|ed)]"
                )
                .description("Called when a human entity experiences exhaustion. An exhaustion level greater than 4.0 causes a decrease in saturation by 1.")
                .examples("on entity exhaust:")
                .since("2.0");
        EventValues.registerEventValue(EntityExhaustionEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EntityExhaustionEvent e) {
                return e.getEntity();
            }
        }, 0);
        EventValues.registerEventValue(EntityExhaustionEvent.class, Number.class, new Getter<>() {
            @Override
            public Number get(EntityExhaustionEvent e) {
                return e.getExhaustion();
            }
        }, 0);
        // fertilize egg
        if (Skript.classExists("io.papermc.paper.event.entity.EntityFertilizeEggEvent")) {
            Skript.registerEvent("fertilize egg", EntityEvents.class, EntityFertilizeEggEvent.class,
                            "[entity] fertilize[d] egg",
                            "[entity] egg fertilize[d]"
                    )
                    .description("Called when two entities mate and the mating process results in a fertilization. Fertilization differs from normal breeding, as represented by the org.bukkit.event.entity.EntityBreedEvent, as it does not result in the immediate creation of the child entity in the world.\n" +
                            "An example of this would be:\n" +
                            "A frog being marked as \"is_pregnant\" and laying org.bukkit.Material.FROGSPAWN later.\n" +
                            "Sniffers producing the org.bukkit.Material.SNIFFER_EGG item, which needs to be placed before it can begin to hatch.\n" +
                            "A turtle being marked with \"HasEgg\" and laying a org.bukkit.Material.TURTLE_EGG later.\n" +
                            "The event hence only exposes the two parent entities in the fertilization process and cannot provide the child entity, as it will only exist at a later point in time.")
                    .examples("on fertilize egg:")
                    .since("2.0");

            EventValues.registerEventValue(EntityFertilizeEggEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(EntityFertilizeEggEvent e) {
                    return e.getEntity();
                }
            }, 0);
            EventValues.registerEventValue(EntityFertilizeEggEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(EntityFertilizeEggEvent e) {
                    return e.getBreeder();
                }
            }, 0);
            EventValues.registerEventValue(EntityFertilizeEggEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(EntityFertilizeEggEvent e) {
                    return e.getBredWith();
                }
            }, 0);
        }
        // entity load crossbow
        Skript.registerEvent("load crossbow", EntityEvents.class, EntityLoadCrossbowEvent.class,
                        "[entity] load crossbow",
                "[entity] crossbow load"
                )
                .description("Called when a LivingEntity loads a crossbow with a projectile.")
                .examples("on load crossbow:")
                .since("2.0");
        EventValues.registerEventValue(EntityLoadCrossbowEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EntityLoadCrossbowEvent e) {
                return e.getEntity();
            }
        }, 0);
        EventValues.registerEventValue(EntityLoadCrossbowEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(EntityLoadCrossbowEvent e) {
                return e.getCrossbow();
            }
        }, 0);
        // entity pose change
        Skript.registerEvent("entity pose change event", EntityEvents.class, EntityPoseChangeEvent.class,
                        "[entity] pose change[d]",
                "[entity] change[d] pose"
                )
                .description("Called when an entity changes its pose.")
                .examples("on pose change:")
                .since("2.0");
        EventValues.registerEventValue(EntityPoseChangeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EntityPoseChangeEvent e) {
                return e.getEntity();
            }
        }, 0);
        // entity toggle sit
        if (Skript.classExists("io.papermc.paper.event.entity.EntityToggleSitEvent")) {
            Skript.registerEvent("toggle sit", EntityEvents.class, EntityToggleSitEvent.class,
                            "[entity] toggle[d] sit (state|mode)",
                            "[entity] sit (state|mode) toggle[d]"
                    )
                    .description("s called when an entity sits down or stands up.")
                    .examples("on toggle sit:")
                    .since("2.0");

            EventValues.registerEventValue(EntityToggleSitEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(EntityToggleSitEvent e) {
                    return e.getEntity();
                }
            }, 0);
            EventValues.registerEventValue(EntityToggleSitEvent.class, Boolean.class, new Getter<>() {
                @Override
                public Boolean get(EntityToggleSitEvent e) {
                    return e.getSittingState();
                }
            }, 0);
        }
        // entity unleash
        Skript.registerEvent("entity unleash", EntityEvents.class, EntityUnleashEvent.class,
                        "[entity] unleash[ed]"
                )
                .description("Called when an entity is unleashed.")
                .examples("on entity unleash:")
                .since("2.0");
        EventValues.registerEventValue(EntityUnleashEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EntityUnleashEvent e) {
                return e.getEntity();
            }
        }, 0);

        // piglin barter
        Skript.registerEvent("piglin barter", EntityEvents.class, PiglinBarterEvent.class,
                        "piglin barter"
                )
                .description("Called when a Piglin starts bartering.")
                .examples("on piglin barter:")
                .since("2.0");
        EventValues.registerEventValue(PiglinBarterEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(PiglinBarterEvent e) {
                return e.getEntity();
            }
        }, 0);

        // pig zombie anger
        Skript.registerEvent("pig zombie anger", EntityEvents.class, PigZombieAngerEvent.class,
                        "pig[zombie] anger[ed]"
                )
                .description("Called when a PigZombie gets angry.")
                .examples("on pig zombie anger:")
                .since("2.0");
        EventValues.registerEventValue(PigZombieAngerEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(PigZombieAngerEvent e) {
                return e.getEntity();
            }
        }, 0);


        // slime pathfind
        Skript.registerEvent("slime pathfind", EntityEvents.class, SlimePathfindEvent.class,
                        "slime pathfind"
                )
                .description("Called when a slime starts pathfinding.")
                .examples("on slime pathfind:")
                .since("2.0");
        EventValues.registerEventValue(SlimePathfindEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(SlimePathfindEvent e) {
                return e.getEntity();
            }
        }, 0);

        // strider temperature change
        Skript.registerEvent("strider temperature change", EntityEvents.class, StriderTemperatureChangeEvent.class,
                        "strider temperature change[d]"
                )
                .description("Called when a Strider's temperature changes.")
                .examples("on strider temperature change:")
                .since("2.0");
        EventValues.registerEventValue(StriderTemperatureChangeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(StriderTemperatureChangeEvent e) {
                return e.getEntity();
            }
        }, 0);

        // thrown egg hatch
        Skript.registerEvent("thrown egg hatch", EntityEvents.class, ThrownEggHatchEvent.class,
                        "thrown egg hatch"
                )
                .description("Called when a thrown egg hatches.")
                .examples("on thrown egg hatch:")
                .since("2.0");
        EventValues.registerEventValue(ThrownEggHatchEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(ThrownEggHatchEvent e) {
                return e.getEgg();
            }
        }, 0);

        // turtle go home
        Skript.registerEvent("turtle go home", EntityEvents.class, TurtleGoHomeEvent.class,
                        "turtle go home"
                )
                .description("Called when a turtle goes home.")
                .examples("on turtle go home:")
                .since("2.0");
        EventValues.registerEventValue(TurtleGoHomeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(TurtleGoHomeEvent e) {
                return e.getEntity();
            }
        }, 0);

        // turtle lay egg
        Skript.registerEvent("turtle lay egg", EntityEvents.class, TurtleLayEggEvent.class,
                        "turtle lay egg"
                )
                .description("Called when a turtle lays an egg.")
                .examples("on turtle lay egg:")
                .since("2.0");
        EventValues.registerEventValue(TurtleLayEggEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(TurtleLayEggEvent e) {
                return e.getEntity();
            }
        }, 0);

        // turtle start digging
        Skript.registerEvent("turtle start digging", EntityEvents.class, TurtleStartDiggingEvent.class,
                        "turtle start digging"
                )
                .description("Called when a turtle starts digging.")
                .examples("on turtle start digging:")
                .since("2.0");
        EventValues.registerEventValue(TurtleStartDiggingEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(TurtleStartDiggingEvent e) {
                return e.getEntity();
            }
        }, 0);

        // villager acquire trade
        Skript.registerEvent("villager acquire trade", EntityEvents.class, VillagerAcquireTradeEvent.class,
                        "villager acquire trade"
                )
                .description("Called when a villager acquires a new trade.")
                .examples("on villager acquire trade:")
                .since("2.0");
        EventValues.registerEventValue(VillagerAcquireTradeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(VillagerAcquireTradeEvent e) {
                return e.getEntity();
            }
        }, 0);


        // villager career change
        Skript.registerEvent("villager career change", EntityEvents.class, VillagerCareerChangeEvent.class,
                        "villager career change[d]"
                )
                .description("Called when a villager changes its career.")
                .examples("on villager career change:")
                .since("2.0");
        EventValues.registerEventValue(VillagerCareerChangeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(VillagerCareerChangeEvent e) {
                return e.getEntity();
            }
        }, 0);


        // villager replenish trade
        Skript.registerEvent("villager replenish trade", EntityEvents.class, VillagerReplenishTradeEvent.class,
                        "villager replenish trade"
                )
                .description("Called when a villager replenishes its trade offers.")
                .examples("on villager replenish trade:")
                .since("2.0");
        EventValues.registerEventValue(VillagerReplenishTradeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(VillagerReplenishTradeEvent e) {
                return e.getEntity();
            }
        }, 0);

        // warden anger change
        if (Skript.classExists("io.papermc.paper.event.entity.WardenAngerChangeEvent")) {
            Skript.registerEvent("warden anger change", EntityEvents.class, WardenAngerChangeEvent.class,
                            "warden anger change[d]"
                    )
                    .description("Called when a Warden's anger level changes.")
                    .examples("on warden anger change:")
                    .since("2.0");
            EventValues.registerEventValue(WardenAngerChangeEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(WardenAngerChangeEvent e) {
                    return e.getEntity();
                }
            }, 0);
            EventValues.registerEventValue(WardenAngerChangeEvent.class, Integer.class, new Getter<>() {
                @Override
                public Integer get(WardenAngerChangeEvent e) {
                    return e.getNewAnger();
                }
            }, 1);
            EventValues.registerEventValue(WardenAngerChangeEvent.class, Integer.class, new Getter<>() {
                @Override
                public Integer get(WardenAngerChangeEvent e) {
                    return e.getOldAnger();
                }
            }, -1);
        }


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
        if (e != null) return e.getEventName();
        return "entity event";
    }
}