package de.crafttogether;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EggRespawn extends JavaPlugin {
    private static EggRespawn plugin;
    
    public void onEnable() {
    	plugin = this;
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        System.out.println(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " enabled");
    }

    public void onDisable() {
    	System.out.println(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " disabled");
    }
    
    public static EggRespawn getInstance() {
        return plugin;
    }
}