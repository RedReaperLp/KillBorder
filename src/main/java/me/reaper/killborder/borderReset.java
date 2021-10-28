package me.reaper.killborder;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class borderReset implements CommandExecutor {

    private KillBorder killBorder;

    public borderReset(KillBorder killBorder) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(args.length == 0)) {
            sender.sendMessage(ChatColor.RED + "to much arguments");
            return true;
        }else{
            int startSize = killBorder.getConfig().getInt("startSize");
            killBorder.getConfig().set("size", startSize);
            killBorder.getConfig().set("teleported-players", new ArrayList<String>());
            killBorder.getConfig().set("startSize", new ArrayList<String>());
            killBorder.getConfig().set("startSize", startSize);
            killBorder.saveConfig();

        }return true;

    }
}
