package com.kumo0621.github.onlyplayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class OnlyPlayer extends JavaPlugin {
    private String PlayerName = null;
    private double explosionDistance = 3.0; // デフォルトの爆発範囲を3に設定
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("setplayer").setExecutor(new SetExplosivePlayerCommand(this));
        // TabCompleterをコマンドに設定
        this.getCommand("setplayer").setTabCompleter(new SetPlayerTabCompleter());
            startExplosionCheck();
        }

        private void startExplosionCheck() {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(PlayerName==null){
                        return;
                    }
                    Player explosivePlayer = Bukkit.getServer().getPlayer(PlayerName);
                    if (explosivePlayer != null) {
                        Location explosivePlayerLocation = explosivePlayer.getLocation();

                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            if (!player.equals(explosivePlayer) && explosivePlayerLocation.distance(player.getLocation()) > explosionDistance) {
                                player.getWorld().createExplosion(player.getLocation(), 4F, false, false); // 火をつけず、ブロックを壊さない
                                // ターゲットプレイヤーの現在位置を取得
                                Location targetLocation = explosivePlayer.getLocation();
                                Location spawnLocation = targetLocation.clone().add(0, 2, 0); // Y座標を1増やして新しいLocationを作成

                                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                    onlinePlayer.setBedSpawnLocation(spawnLocation, true);
                                }
                            }
                        }
                    }
                }
            }.runTaskTimer(this, 0L, 20L); // 0Lは初期遅延なし、20Lは20ティック（1秒）ごとに実行
        }

    public void setExplosivePlayerName(String explosivePlayerName) {
        this.PlayerName = explosivePlayerName;
    }

    public void setExplosionDistance(double explosionDistance) {
        this.explosionDistance = explosionDistance;
    }
}
