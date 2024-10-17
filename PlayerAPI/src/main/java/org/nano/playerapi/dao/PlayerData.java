package org.nano.playerapi.dao;

import java.util.Date;
import java.util.UUID;

public class PlayerData {
    private final UUID uuid;
    private String playerName;
    private Date lastJoinDate;

    public PlayerData(UUID uuid, String playerName, Date lastJoinDate) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.lastJoinDate = lastJoinDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Date getLastJoinDate() {
        return lastJoinDate;
    }

    public void setLastJoinDate(Date lastJoinDate) {
        this.lastJoinDate = lastJoinDate;
    }
}
