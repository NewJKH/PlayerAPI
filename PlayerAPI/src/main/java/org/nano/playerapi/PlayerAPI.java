package org.nano.playerapi;

import org.bukkit.plugin.java.JavaPlugin;
import org.nano.playerapi.api.db.DB;
import org.nano.playerapi.config.Config;
import org.nano.playerapi.lisnter.JoinOutEvent;

public final class PlayerAPI extends JavaPlugin {
    @Override
    public void onEnable() {
        Config config = new Config();
        config.load();

        DB db = new DB();
        getServer().getPluginManager().registerEvents(new JoinOutEvent(db),this);

    }

    @Override
    public void onDisable() {

    }
}
