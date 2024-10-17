package org.nano.playerapi.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.nano.playerapi.PlayerAPI;
import org.nano.playerapi.api.db.DBConnector;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.nano.playerapi.api.db.DBConnector.*;

public class Config {
    private File file;
    private FileConfiguration configuration;

    public Config() {
        setup();
    }
    public void setup() {
        File pluginFolder = Objects.requireNonNull(PlayerAPI.getProvidingPlugin(PlayerAPI.class)).getDataFolder();
        File folder = new File(pluginFolder, "config");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        file = new File(folder, "database.yaml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }
        configuration = YamlConfiguration.loadConfiguration(file);
    }
    public void load() {
        setup();

        sw = configuration.getBoolean("DB Connect", false);
        if (!sw) {
            configuration.set("DB Connect", true);
            save();
            sw = configuration.getBoolean("DB Connect", false);
        }
        URL = configuration.getString("URL");
        if (URL == null) {
            configuration.set("URL", "jdbc:mysql://localhost:3306/playerAPI?createDatabaseIfNotExist=true");
            save();
            URL = configuration.getString("URL");
        }
        USER = configuration.getString("ID");
        if (USER == null) {
            configuration.set("ID", "root");
            save();
            USER = configuration.getString("ID");
        }
        PASSWORD = configuration.getString("Password");
        if (PASSWORD == null) {
            configuration.set("Password", "0000");
            save();
            PASSWORD = configuration.getString("Password");
        }
        new DBConnector().getConnection();

    }
    private void save(){
        try {
            configuration.save(file);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
