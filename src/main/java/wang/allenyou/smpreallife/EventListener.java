package wang.allenyou.smpreallife;

import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class EventListener implements Listener {
	@EventHandler
	public void onPlayerDie(EntityDeathEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
			String playerName = player.getDisplayName();
			SMPRealLife.instance.dataTable.setLife(playerName, SMPRealLife.instance.dataTable.getLife(playerName));
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getDisplayName();
		if (SMPRealLife.instance.dataTable.contains(playerName)) {
			if (SMPRealLife.instance.dataTable.getLife(playerName) == 0) {
				player.setGameMode(GameMode.SPECTATOR);
			}
		} else {
			SMPRealLife.instance.dataTable.setLife(playerName, SMPRealLife.instance.getConfig().getInt("life.defaultLife", 5));
		}
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getDisplayName();
		if (SMPRealLife.instance.dataTable.getLife(playerName) == 0) {
			player.setGameMode(GameMode.SPECTATOR);
		}
	}
}
