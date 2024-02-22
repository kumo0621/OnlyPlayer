package com.kumo0621.github.onlyplayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetExplosivePlayerCommand implements CommandExecutor {
    private OnlyPlayer plugin;

    public SetExplosivePlayerCommand(OnlyPlayer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            try {
                plugin.setExplosivePlayerName(args[0]);
                plugin.setExplosionDistance(Double.parseDouble(args[1]));
                sender.sendMessage("集合対象" + args[0] + "爆発範囲: " + args[1]);
                return true;
            } catch (NumberFormatException e) {
                sender.sendMessage("引数が正しくありません");
                return false;
            }
        }
        sender.sendMessage("使い方: /setplayer　<集合対象> <爆発までの範囲>");
        return false;
    }
}
