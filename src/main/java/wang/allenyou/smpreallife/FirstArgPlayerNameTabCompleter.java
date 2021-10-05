package wang.allenyou.smpreallife;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FirstArgPlayerNameTabCompleter implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
		int argLength = args.length;
		if (argLength > 1) {
			return null;
		}
		List<String> ret = new ArrayList<>();
		if (argLength == 0) {
			Bukkit.getOnlinePlayers().stream().toList().forEach((Consumer<Player>) player -> ret.add(player.getDisplayName()));
		} else {
			Bukkit.getOnlinePlayers().stream().toList().forEach((Consumer<Player>) player -> {
				String name = player.getDisplayName();
				if (name.startsWith(args[0])) {
					ret.add(name);
				}
			});
		}
		if (ret.isEmpty()) return null;
		return ret;
	}
}
