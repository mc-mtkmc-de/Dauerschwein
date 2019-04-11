package de.dauerschwein.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class FoodPig implements CommandExecutor, Listener {
	
	private final String PIG_TITLE = "§6§lEssensschwein";
	
	private void spawnFoodPig(Location location) {
		Pig pig = (Pig) location.getWorld().spawnEntity(location, EntityType.PIG);
		pig.setCustomName(PIG_TITLE);
		pig.setCustomNameVisible(true);
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			spawnFoodPig(player.getLocation());
			player.sendMessage("§aDu hast ein neus §6Foodpig §aerschaffen");
			
		}
		return false;
	}
	
	
	@EventHandler
	public void handleFoodPigDeath(EntityDeathEvent event) {
		if(!(event.getEntity() instanceof Pig)) return;
		Pig pig = (Pig) event.getEntity();
		if(!pig.getCustomName().equals(PIG_TITLE)) return;
		
		event.getDrops().clear();
		ItemStack foodDrop = new ItemStack(Material.COOKED_BEEF, 3);
		pig.getWorld().dropItemNaturally(pig.getLocation(), foodDrop);
		
		Player player = pig.getKiller();
		if(player.hasPermission("Kill.schwein") && (player.getItemInHand().getType() == Material.GOLD_AXE)) return;
		spawnFoodPig(pig.getLocation());
		
	}

}
