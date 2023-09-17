package com.fletchplugins.customdeathevents.commands;

import com.fletchplugins.customdeathevents.files.CustomConfigClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("customdeathevents.seteffect")){
                if (command.getName().equalsIgnoreCase("setdeatheffect")) {
                    String[] validEffects = {"none", "lightning", "explosion", "firework", "fire", "dragon","use_generic"};
                    List<String> validEffectsList = new ArrayList<>(Arrays.asList(validEffects));

                    String[] causes = {"GENERIC", "DRAGON_BREATH", "ENTITY_ATTACK", "BLOCK_EXPLOSION", "CONTACT", "CRAMMING", "CUSTOM", "DROWNING", "DRYOUT",
                            "ENTITY_EXPLOSION", "ENTITY_SWEEP_ATTACK", "FALL", "FALLING_BLOCK", "FIRE", "FIRE_TICK", "FLY_INTO_WALL", "FREEZE",
                            "HOT_FLOOR", "LAVA", "LIGHTNING", "MAGIC", "MELTING", "POISON", "PROJECTILE", "STARVATION", "SUFFOCATION", "SUICIDE",
                            "THORNS", "VOID", "WITHER"};
                    List<String> validCausesList = new ArrayList<>(Arrays.asList(causes));

                    try {
                        //World setWorld= Bukkit.getServer().getWorld(args[0]);
                        if (validCausesList.contains(args[1].toUpperCase())) {
                            if (validEffectsList.contains(args[2].toLowerCase())) {
                                String path = "";
                                if (args[0].equalsIgnoreCase("ALL")) {
                                    World[] worlds = new World[Bukkit.getServer().getWorlds().size()];
                                    Bukkit.getServer().getWorlds().toArray(worlds);
                                    for (int i = 0; i < worlds.length; i++) {
                                        String worldName = worlds[i].getName();
                                        path = "DeathEvents." + worldName + "." + args[1].toUpperCase();
                                        String deathEffect=args[2];
                                        if(deathEffect.equalsIgnoreCase("use_generic")){
                                            deathEffect="";
                                        }
                                        CustomConfigClass.get().set(path, deathEffect);
                                    }
                                } else {
                                    path = "DeathEvents." + args[0] + "." + args[1].toUpperCase();
                                    String deathEffect=args[2];
                                    if(deathEffect.equalsIgnoreCase("use_generic")){
                                        deathEffect="";
                                    }
                                    CustomConfigClass.get().set(path, deathEffect);
                                }
                                player.sendMessage(ChatColor.GREEN + "Set the " + args[1] + " death effect of " + args[0] + " to " + args[2] + "!");
                                CustomConfigClass.save();
                            } else {
                                player.sendMessage(ChatColor.RED + "Not a valid death effect!");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Not a valid death cause!");
                        }

                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Not a valid world!");
                    }
                }


                else if(command.getName().equalsIgnoreCase("resetdeatheffects")) {
                    if (args.length == 1){
                        String path = "";
                    if (args[0].equalsIgnoreCase("ALL")) {
                        World[] worlds = new World[Bukkit.getServer().getWorlds().size()];
                        Bukkit.getServer().getWorlds().toArray(worlds);
                        for (int i = 0; i < worlds.length; i++) {
                            String worldName = worlds[i].getName();
                            path = "DeathEvents." + worldName;
                            CustomConfigClass.get().set(path, "");
                        }
                        player.sendMessage(ChatColor.GREEN + "Reset all death effects in all worlds!");
                    } else {
                        try {
                            World setWorld = Bukkit.getServer().getWorld(args[0]);
                            path = "DeathEvents." + args[0];
                            CustomConfigClass.get().set(path, "");
                            player.sendMessage(ChatColor.GREEN + "Reset all death effects in world: "+args[0]);

                        } catch (Exception e) {
                            player.sendMessage(ChatColor.RED + "Not a valid world!");
                        }
                    }
                }
                    else{
                        player.sendMessage(ChatColor.RED+"Not enough arguments! Specify a world to clear effects!");
                    }
                }
        }
            else{
                player.sendMessage(ChatColor.RED + "You don't have the perms to do that friend!");
            }
        }
        return true;
    }
}
