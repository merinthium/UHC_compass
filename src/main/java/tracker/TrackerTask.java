package tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.merinthium.com.Main;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TrackerTask extends BukkitRunnable 
{
	private Player player;

	public TrackerTask(Player player)
	{
		this.player = player;
	}
	
	@Override
	public void run() 
	{
		String message = "" + ChatColor.RED + ChatColor.BOLD + "NO Target Near";
		
		List<Player> players = new ArrayList<> ();
		for (Player p : player.getWorld().getPlayers())
		{
			if(!(p.getUniqueId().equals(player.getUniqueId()))
				|| (player.canSee(p))
				|| !(p.getGameMode().equals(GameMode.SPECTATOR)))
			{
					players.add(p);
			}		
		}
		
		Collections.sort(players, new TrackerComparer (player));
		Player nearest = null; 
		
		if (players.size() > 0)
		{
			nearest = players.get(0);
			message = "" + ChatColor.WHITE + "TARGET " + ChatColor.GREEN + nearest.getName() 
					+ "" + ChatColor.WHITE + "DISTANCE: " + ChatColor.GREEN + "" +((int) nearest.getLocation().distance(player.getLocation()));
		}
		
		((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer) player).getHandle().playerConnection.sendPacket (new net.minecraft.server.v1_8_R3.PacketPlayOutChat(
				net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer.a ("{\"text\" : \"" + (
						player.getItemInHand().getType().equals(Material.COMPASS) ? message : " ") + "\"}"), (byte) 2));
		try 
		{
			player.setCompassTarget(nearest != null ? nearest.getLocation() : null);
		
		} catch (NullPointerException ignore){}
	}
}
