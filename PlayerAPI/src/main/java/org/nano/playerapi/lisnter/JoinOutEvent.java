package org.nano.playerapi.lisnter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.nano.playerapi.PlayerAPI;
import org.nano.playerapi.api.db.DB;
import org.nano.playerapi.construct.PlayersAPI;
import org.nano.playerapi.dao.PlayerData;

import java.util.Date;

public class JoinOutEvent implements Listener {
    private final PlayersAPI api = PlayersAPI.getInstance();
    private final DB db;

    public JoinOutEvent(DB db) {
        this.db = db;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Bukkit.getScheduler().runTask(PlayerAPI.getProvidingPlugin(PlayerAPI.class), () -> db.updatePlayer(e.getPlayer()));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Bukkit.getScheduler().runTask(PlayerAPI.getProvidingPlugin(PlayerAPI.class), () -> db.updatePlayer(e.getPlayer()));
    }

}
