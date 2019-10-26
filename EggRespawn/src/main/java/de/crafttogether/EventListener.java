package de.crafttogether;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener
{
    private EggRespawn plugin;
    
    public EventListener() {
        plugin = EggRespawn.getInstance();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDeath(EntityDeathEvent e) {
    	final World world = e.getEntity().getLocation().getWorld();
    	
    	if (!e.getEntityType().equals(EntityType.ENDER_DRAGON))
    		return;
    	    	
    	if (!world.getEnvironment().equals(Environment.THE_END))
    		return;
    	
		new BukkitRunnable() {
			public void run() {				
	    		Location loc = new Location(world, 0, 0, 0);
				loc.setY(loc.getWorld().getHighestBlockYAt(0, 0));
    	    	Block highestBlock = world.getBlockAt(loc);
    	    	
				loc.setY(loc.getWorld().getHighestBlockYAt(0, 0) - 1);
    	    	Block secondHighestBlock = world.getBlockAt(loc);

    	    	if (highestBlock.getType().equals(Material.BEDROCK)) {
    	    		loc.setY(loc.getWorld().getHighestBlockYAt(0, 0) + 1);
    	    		Block topBlock = world.getBlockAt(loc);
    	    		topBlock.setType(Material.DRAGON_EGG);
    	    	}
    	    	else if (secondHighestBlock.getType().equals(Material.BEDROCK)) {
    	    		highestBlock.setType(Material.DRAGON_EGG);
    	    	}
    	    	else {
    	    		System.out.println("[" + plugin.getDescription().getName() + "] Error: Unable to respawn DragonEgg at world " + loc.getWorld().getName());
    	    		return;
    	    	}
    	    	
    	    	System.out.println("[" + plugin.getDescription().getName() + "] DragonEgg respawned at world " + loc.getWorld().getName());
			}
		}.runTaskLater(plugin, 20L*10);
    }
}