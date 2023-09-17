package com.fletchplugins.customdeathevents;

import com.fletchplugins.customdeathevents.commands.CommandClass;
import com.fletchplugins.customdeathevents.commands.DeathEventTabCompleter;
import com.fletchplugins.customdeathevents.files.CustomConfigClass;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class CustomDeathEvents extends JavaPlugin {
    DeathEventListener deathEventListener=new DeathEventListener();
    @Override
    public void onEnable() {
        System.out.println("Startup message for CustomDeathEvents");
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        try{
            CustomConfigClass.setup();
            CustomConfigClass.get().options().copyDefaults(true);
            CustomConfigClass.save();
        }
        catch (IOException e){
            //ignore
        }

        this.getCommand("setdeatheffect").setExecutor(new CommandClass());
        this.getCommand("resetdeatheffects").setExecutor(new CommandClass());
        this.getServer().getPluginManager().registerEvents(deathEventListener,this);
        this.getCommand("setdeatheffect").setTabCompleter(new DeathEventTabCompleter());
        this.getCommand("resetdeatheffects").setTabCompleter(new DeathEventTabCompleter());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
