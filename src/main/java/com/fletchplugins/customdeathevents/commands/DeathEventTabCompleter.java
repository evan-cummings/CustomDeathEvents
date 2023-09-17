package com.fletchplugins.customdeathevents.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeathEventTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("customdeathevents.seteffect")) {
            if (command.getName().equalsIgnoreCase("setdeatheffect")){
                if (args.length == 1) {
                    List<String> worldNames = new ArrayList<>();
                    World[] worlds = new World[Bukkit.getServer().getWorlds().size()];
                    Bukkit.getServer().getWorlds().toArray(worlds);

                    List<String> worldResults = new ArrayList<>();
                    worldNames.add("ALL");
                    for (int i = 0; i < worlds.length; i++) {
                        worldNames.add(worlds[i].getName());
                    }
                    for (String a : worldNames) {
                        if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                            worldResults.add(a);
                        }
                    }
                    return worldResults;
                } else if (args.length == 2) {
                    List<String> deathCauses = new ArrayList<>();
                    String[] causes = {"GENERIC", "DRAGON_BREATH", "ENTITY_ATTACK", "BLOCK_EXPLOSION", "CONTACT", "CRAMMING", "CUSTOM", "DROWNING", "DRYOUT",
                            "ENTITY_EXPLOSION", "ENTITY_SWEEP_ATTACK", "FALL", "FALLING_BLOCK", "FIRE", "FIRE_TICK", "FLY_INTO_WALL", "FREEZE",
                            "HOT_FLOOR", "LAVA", "LIGHTNING", "MAGIC", "MELTING", "POISON", "PROJECTILE", "STARVATION", "SUFFOCATION", "SUICIDE",
                            "THORNS", "VOID", "WITHER"};

                    List<String> causeResult = new ArrayList<>();
                    for (int k = 0; k < causes.length; k++) {
                        deathCauses.add(causes[k]);
                    }
                    for (String c : deathCauses) {
                        if (c.toLowerCase().startsWith(args[1].toLowerCase())) {
                            causeResult.add(c);
                        }
                    }
                    return causeResult;
                } else if (args.length == 3) {
                    List<String> effectOptions = new ArrayList<>();
                    String[] effects = {"none", "lightning", "explosion", "firework", "fire", "dragon", "use_generic"};

                    List<String> effectResult = new ArrayList<>();
                    for (int j = 0; j < effects.length; j++) {
                        effectOptions.add(effects[j]);
                    }
                    for (String b : effectOptions) {
                        if (b.toLowerCase().startsWith(args[2].toLowerCase())) {
                            effectResult.add(b);
                        }
                    }
                    return effectResult;
                }
        }
        else if(command.getName().equalsIgnoreCase("resetdeatheffects")){
            if(args.length==1){
                List<String> worldNames = new ArrayList<>();
                World[] worlds = new World[Bukkit.getServer().getWorlds().size()];
                Bukkit.getServer().getWorlds().toArray(worlds);

                List<String> worldResults = new ArrayList<>();
                worldNames.add("ALL");
                for (int i = 0; i < worlds.length; i++) {
                    worldNames.add(worlds[i].getName());
                }
                for (String a : worldNames) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        worldResults.add(a);
                    }
                }
                return worldResults;
            }
        }
    }
        return null;
    }
}
