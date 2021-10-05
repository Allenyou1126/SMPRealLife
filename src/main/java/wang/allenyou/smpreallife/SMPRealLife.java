package wang.allenyou.smpreallife;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

// TODO 添加生命数系统
// TODO 添加生命数为零则变成旁观者设定

public class SMPRealLife extends JavaPlugin {
	public static SMPRealLife instance = null;
	public IDataTable dataTable = null;
	@Override
	public void onEnable() {
		instance = this;
		dataTable = new RuntimeDataTable();
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new EventListener(), this);
		if (!(Objects.isNull(Bukkit.getPluginCommand("getlife")))) {
			Objects.requireNonNull(Bukkit.getPluginCommand("getlife")).setExecutor(new GetLifeCommandHandler());
			Objects.requireNonNull(Bukkit.getPluginCommand("getlife")).setTabCompleter(new FirstArgPlayerNameTabCompleter());
		}
		if (!(Objects.isNull(Bukkit.getPluginCommand("setlife")))) {
			Objects.requireNonNull(Bukkit.getPluginCommand("setlife")).setExecutor(new SetLifeCommandHandler());
			Objects.requireNonNull(Bukkit.getPluginCommand("setlife")).setTabCompleter(new FirstArgPlayerNameTabCompleter());
		}
	}

	@Override
	public void onDisable() {
		saveConfig();
	}
}
