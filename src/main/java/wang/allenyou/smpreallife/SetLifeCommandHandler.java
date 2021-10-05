package wang.allenyou.smpreallife;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class SetLifeCommandHandler implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (!(commandSender.hasPermission("life.set"))) {
			Map<String, String> tempMap = new HashMap<>();
			tempMap.put("permission_name", "life.set");
			String message = MessageRender.renderMessage(SMPRealLife.instance.getConfig().getString("messages.dontHavePermission", "403: You don't have the permission \"${permission_name}\""), tempMap);
		}
		if (args.length != 2) {
			return false;
		}
		String targetName = args[0];
		if (!(SMPRealLife.instance.dataTable.contains(targetName))) {
			Map<String, String> tempMap = new HashMap<>();
			tempMap.put("player_name", targetName);
			commandSender.sendMessage(MessageRender.renderMessage(SMPRealLife.instance.getConfig().getString("messages.playerNotFound", "404: Player \"${player_name}\" not found!"), tempMap));
			return true;
		}
		int targetLife;
		try {
			targetLife = Integer.getInteger(args[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		SMPRealLife.instance.dataTable.setLife(targetName, targetLife);
		return true;
	}
}
