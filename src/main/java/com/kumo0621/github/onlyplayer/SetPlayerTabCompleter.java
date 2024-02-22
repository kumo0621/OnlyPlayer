package com.kumo0621.github.onlyplayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SetPlayerTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // 最初の引数の補完（オンラインプレイヤーの名前）
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .collect(Collectors.toList());
        }
        // 二番目の引数の補完（予め設定した数値リスト）
        else if (args.length == 2) {
            List<String> distances = new ArrayList<>();
            distances.add("<爆発範囲>");
            // 他にも追加可能
            return distances;
        }
        return null; // それ以外の引数では補完しない
    }
}
