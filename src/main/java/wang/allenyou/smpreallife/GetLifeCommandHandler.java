package wang.allenyou.smpreallife;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GetLifeCommandHandler implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (!(commandSender.hasPermission("life.get"))) {
			Map<String, String> tempMap = new HashMap<>();
			tempMap.put("permission_name", "life.get");
			String message = MessageRender.renderMessage(SMPRealLife.instance.getConfig().getString("messages.dontHavePermission", "403: You don't have the permission \"${permission_name}\""), tempMap);
		}
		if (args.length > 1) {
			return false;
		}
		String targetName;
		if (args.length == 0) {
			if (commandSender instanceof Player) {
				targetName = ((Player) commandSender).getDisplayName();
			} else {
				return false;
			}
		} else {
			targetName = args[0];
			if (!(SMPRealLife.instance.dataTable.contains(targetName))) {
				Map<String, String> tempMap = new HashMap<>();
				tempMap.put("player_name", args[0]);
				commandSender.sendMessage(MessageRender.renderMessage(SMPRealLife.instance.getConfig().getString("messages.playerNotFound", "404: Player \"${player_name}\" not found!"), tempMap));
				return true;
			}
		}
		int correctLife = SMPRealLife.instance.dataTable.getLife(targetName);
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("player_name", targetName);
		tempMap.put("correct_life", Integer.toString(correctLife));
		String message = MessageRender.renderMessage(SMPRealLife.instance.getConfig().getString("messages.getLife", "${player_name} has ${correct_life} remaining lives."), tempMap);
		commandSender.sendMessage(message);
		return true;
	}
}
