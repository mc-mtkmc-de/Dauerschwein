package de.dauerschwein.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.dauerschwein.util.FoodPig;


public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	public void onEnable() {
		plugin = this;
		FoodPig foodPig = new FoodPig();
		
		getCommand("foodpig").setExecutor(foodPig);
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(foodPig, this);
	}
	public static Main getPlugin() {
		return plugin;
	}
	
}
