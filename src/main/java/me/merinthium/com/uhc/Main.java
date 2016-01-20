package me.merinthium.com.uhc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import tracker.TrackerTask;

public class Main extends JavaPlugin implements Listener
{

	@SuppressWarnings("unused")
	private static Plugin plugin;
	
	public void onEnable()
	{
		Bukkit.getPluginManager().registerEvents(this, this);
		for(Player p : Bukkit.getOnlinePlayers())
		{
			new TrackerTask (p).runTaskTimer(this, 0L, 10L);
		}
		
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		if(p.getInventory().contains(Material.COMPASS))
		{
			new TrackerTask (p).runTaskTimer(this, 0L, 10L);
		}
		else
		{
			new TrackerTask (p).runTaskTimer(this, 0L, 10L);
		}
	}	
	
	public void onDisable()
	{
		getLogger().info("UHC_Compass has been Disabled");
		plugin = null;
	}
}
