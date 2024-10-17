package org.nano.playerapi.construct;

import org.nano.playerapi.api.db.DBConnector;
import org.nano.playerapi.dao.PlayerData;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.nano.playerapi.api.db.DB.getPlayerDate;

public class PlayersAPI {
    public static PlayersAPI instance;
    public static PlayersAPI getInstance(){
        if (instance == null) {
            instance = new PlayersAPI();
        }
        return instance;
    }
    private PlayerData getPlayer(UUID uuid){
        if ( DBConnector.connection != null){
            return getPlayerDate(uuid);
        } return null;
    }
    private PlayerData getPlayer(String playerName){
        if ( DBConnector.connection != null){
            return getPlayerDate(playerName);
        } return null;
    }

}
