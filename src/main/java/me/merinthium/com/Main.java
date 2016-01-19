package me.merinthium.com;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
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
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		p.getInventory().addItem(new ItemStack(Material.COMPASS));
		new TrackerTask (p).runTaskTimer(this, 0L, 10L);
	}
	
	public void onDisable()
	{
		getLogger().info("UHC_Compass has been Disabled");
		plugin = null;
	}
}
