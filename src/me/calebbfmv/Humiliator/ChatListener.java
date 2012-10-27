package me.calebbfmv.Humiliator;

import me.calebbfmv.Humiliator.Humilator.MsgType;

import org.bukkit.ChatColor;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ChatListener extends JavaPlugin implements Listener {
	Player p;

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if (CommandH.isMute(player)) {
			e.setMessage(ChatColor.DARK_AQUA + "I am an idiot!");
		}
	}

	@EventHandler
	public void onExplosion(ItemSpawnEvent drop) {
		if (drop instanceof Explosive) {
			drop.setCancelled(true);
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event2) {
		Player p = event2.getPlayer();
		LivingEntity play = p;
		if (CommandH.isMute(p)) {
			play.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
					20 * 2, 1));
			p.sendMessage(ChatColor.BLUE + "You have been muted!");
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (CommandH.isMute(p)) {
			e.setCancelled(true);
			p.sendMessage(MsgType.DENIED
					+ "You are not allowed to break blocks!");
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (CommandH.isMute(p)) {
			e.setCancelled(true);
			p.sendMessage(MsgType.DENIED + "You cannot place blocks!");
		}
	}

	@EventHandler
	public void onSOmething/* Change */(AsyncPlayerChatEvent pm) {
		Player p = pm.getPlayer();
		if (CommandH.isMute(p)) {
			p.setDisplayName(MsgType.DENIED + "[MUTED]");
		} else {
			if (!CommandH.isMute(p)) {
				p.setDisplayName(p.getName());
			}
			String msg = pm.getMessage().toLowerCase();
			if (msg.contains("i am a noob")) {
				pm.setCancelled(true);
				p.sendMessage(MsgType.APPROVED + "Yes, yes you are....");
			}
		}
	}
}
