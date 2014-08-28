package com.connorlinfoot.playerlist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public class Main extends JavaPlugin {

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();


        console.sendMessage(ChatColor.GREEN + "============ PlayerList ============");
        console.sendMessage(ChatColor.GREEN + "=========== VERSION: 1.0 ===========");
        console.sendMessage(ChatColor.GREEN + "======== BY CONNOR LINFOOT! ========");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> donors = new ArrayList<String>();
        ArrayList<String> staff = new ArrayList<String>();
        ArrayList<String> normal = new ArrayList<String>();

        for( Player p : Bukkit.getOnlinePlayers() ){
            if( p.hasPermission("playerlist.staff") ){
                staff.add(p.getDisplayName() + ChatColor.RESET);
            } else if( p.hasPermission("playerlist.donor") ){
                donors.add(p.getDisplayName() + ChatColor.RESET);
            } else {
                normal.add(p.getDisplayName() + ChatColor.RESET);
            }
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',getConfig().getString("TITLE_MESSAGE")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',getConfig().getString("NORMAL_PLAYERS_MESSAGE")));

        for( String s : normal ){
            sender.sendMessage(s);
        }
        sender.sendMessage("");

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',getConfig().getString("DONOR_PLAYERS_MESSAGE")));

        for( String s : donors ){
            sender.sendMessage(s);
        }
        sender.sendMessage("");

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',getConfig().getString("STAFF_PLAYERS_MESSAGE")));

        for( String s : staff ){
            sender.sendMessage(s);
        }
        sender.sendMessage("");


        // Clear lists
        donors.clear();
        staff.clear();
        normal.clear();
        return true;
    }

    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

}
