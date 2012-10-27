package me.calebbfmv.Humiliator;

import java.util.HashSet;

import me.calebbfmv.Humiliator.Humilator.MsgType;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandH extends JavaPlugin implements CommandExecutor {

	public static HashSet<Player> mute = new HashSet<Player>();

	public static boolean isMute(Player p) {
		return mute.contains(p);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("h")) {
			if (args.length == 1) {
				if (sender.hasPermission("Humiliator.CanDo")) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						if (!mute.contains(target)) {
							target.getLocation().getWorld()
									.strikeLightning(target.getLocation());
							mute.add(target);
							sender.sendMessage(MsgType.APPROVED
									+ "Player muted!");
							target.getLocation()
									.getWorld()
									.spawnEntity(
											target.getLocation().add(2, 0, 0),
											EntityType.WOLF);
						} else {
							mute.remove(target);
							sender.sendMessage(MsgType.APPROVED
									+ target.getName() + " unmuted!");
						}
					} else {
						sender.sendMessage(MsgType.ERROR + "Who is this? I don't recognize him.");
					}
				} else {

					sender.sendMessage(MsgType.ERROR
							+ " Invalid Player, or you didn't specify a player!");
				}
			} else {
				sender.sendMessage(MsgType.ERROR
						+ "You dont have any permission to do this!");
			}
		}
			return true;
	}
}
