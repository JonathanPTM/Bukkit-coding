package me.DutchplayXL.tutorial6;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void ping(ServerListPingEvent e) {
        String motd = getConfig().getString("motd");
        e.setMotd(ChatColor.translateAlternateColorCodes('&', motd));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("motd")) {
            if (!sender.hasPermission("motd.permission")) {
                sender.sendMessage(ChatColor.RED +"Je hebt hier geen toegang tot!");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED +"Wees wast duidelijker met de MOTD");
                return true;
            }
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                str.append(args[i] + " ");
            }
            String motd = str.toString();
            getConfig().set("motd", motd);
            sender.sendMessage(ChatColor.RED +"MOTD aangepast!");
            saveConfig();
            return true;
        }
        return false;
    }
}
