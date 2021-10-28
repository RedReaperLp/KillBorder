package me.reaper.killborder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.bukkit.entity.EntityType.*;


public final class KillBorder extends JavaPlugin implements Listener {


    public Location spawnLocation = new Location(Bukkit.getWorld("world"), 0, 0, 0);

    public void resetSpawnLocation() {
        spawnLocation = Bukkit.getWorld("world").getHighestBlockAt(0, 0).getRelative(0, 1, 0).getLocation().add(0.5, 0, 0.5);
    }
    public boolean isPaused;
    public int startSize;
    public int size;

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskLater(this, this::resetSpawnLocation, 1);
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("borderreset").setExecutor(this);
        startSize = getConfig().getInt("startSize");
        getConfig().set("teleported-players", new ArrayList<String>());
        getConfig().set("startSize", new ArrayList<String>());
        getConfig().set("startSize", startSize);
        int size = getConfig().getInt("startSize");
        size = Math.max(size, getConfig().getInt("size", 0));

        if (getConfig().getInt("size") < size) {
            getConfig().set("size", size);
            Bukkit.getScheduler().runTaskAsynchronously(this,
                    () -> saveConfig()
            );
        } else {
            size = getConfig().getInt("size");
        }
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(args.length == 0)) {
            sender.sendMessage(ChatColor.RED + "to much arguments");
            return true;
        }else{
            int startSize = getConfig().getInt("startSize");
            getConfig().set("size", startSize);
            getConfig().set("teleported-players", new ArrayList<String>());
            getConfig().set("startSize", new ArrayList<String>());
            getConfig().set("entitys", new ArrayList<String>());
            getConfig().set("startSize", startSize);
            saveConfig();
        }return true;
    }
        @EventHandler
        public void onEntityDeath(EntityDeathEvent e) {
            int entitys;
            entitys = getConfig().getInt("entitys");
            if (entitys <= 0) {
                entitys = 0;
            }
            Entity dead = e.getEntity();
            Entity killer = e.getEntity().getKiller();
            EntityType droppedItem = DROPPED_ITEM;
            if (!(dead.getType() == DROPPED_ITEM)) {
                getConfig().getInt("startSize");
                entitys = entitys + 1;
                getConfig().set("entitys", entitys);
                getConfig().set("size", startSize + entitys );
                saveConfig();
                dead = null;
            }
    }
}