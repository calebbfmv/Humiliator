package me.calebbfmv.Humiliator;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Humilator extends JavaPlugin implements Listener {

	public static Logger log = Logger.getLogger("Minecraft");

	public static boolean canDo(Player p) {
		return p.hasPermission("Humilator.CanDo");
	}

	public enum MsgType {
		APPROVED(ChatColor.BLUE + "[Humilator] " + ChatColor.LIGHT_PURPLE), DENIED(
				ChatColor.RED + "[Humilator] " + ChatColor.DARK_RED), ERROR(
				ChatColor.LIGHT_PURPLE + "[Humiliator Error!]"
						+ ChatColor.DARK_PURPLE);

		public String prefix;
		@SuppressWarnings("serial")
		private static HashMap<MsgType, ChatColor> color = new HashMap<MsgType, ChatColor>() {
			{
				put(APPROVED, ChatColor.LIGHT_PURPLE);
				put(DENIED, ChatColor.DARK_PURPLE);
				put(ERROR, ChatColor.DARK_RED);
			}
		};

		MsgType(String prefix) {
			this.prefix = prefix;
		}

		@Override
		public String toString() {
			return this.prefix;
		}

		public ChatColor getColor() {
			return color.get(this);
		}
	}

	@Override
	public void onEnable() {
		log.info("[Humiliator] is now enabled!");
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		getServer().getPluginCommand("h").setExecutor(new CommandH());
	}
}