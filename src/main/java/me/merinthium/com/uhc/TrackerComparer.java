package me.merinthium.com.uhc;

import java.util.Comparator;

import org.bukkit.entity.Player;

public class TrackerComparer implements Comparator<Player> 
{
	private Player player;
	
	
	public TrackerComparer (Player player)
	{
		this.player = player;
	}


	@Override
	public int compare(Player target1, Player target2) 
	{
		return compare (
				target1.getLocation().distance(player.getLocation()),
				target2.getLocation().distance(player.getLocation()));
	}
	
	private int compare(double a, double b)
	{
		return a> b ? -1 : a > b ? 1 : 0;
	}

}
