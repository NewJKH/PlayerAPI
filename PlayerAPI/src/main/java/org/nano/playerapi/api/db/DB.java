package org.nano.playerapi.api.db;

import org.bukkit.entity.Player;
import org.nano.playerapi.dao.PlayerData;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

import static org.nano.playerapi.api.db.DBConnector.connection;

public class DB {
    /*
        UUID String Date
        22323 Nanoins date

     */

    public DB() {
        if ( connection != null ){
            initializeTable();
        }
    }

    public void initializeTable(){
        String createTableSQL = "CREATE TABLE IF NOT EXISTS PlayerData (" +
                "uuid VARCHAR(36) PRIMARY KEY, " +
                "playerName VARCHAR(255), " +
                "lastJoinDate DATETIME)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("PlayerData 테이블이 성공적으로 생성되었습니다.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updatePlayer(Player player){
        String updateSQL = "UPDATE PlayerData SET playerName = ?, lastJoinDate = ? WHERE uuid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateSQL)) {
            stmt.setString(1, player.getName());
            stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            stmt.setString(3, player.getUniqueId().toString());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected <= 0) {
                insertPlayer(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertPlayer(Player player){
        String insertSQL = "INSERT INTO PlayerData (uuid, playerName, lastJoinDate) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertSQL)) {
            stmt.setString(1, player.getUniqueId().toString());
            stmt.setString(2, player.getName());
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static PlayerData getPlayerDate(UUID uuid){
        String selectSQL = "SELECT * FROM PlayerData WHERE uuid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(selectSQL)) {
            stmt.setString(1, uuid.toString());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String playerName = rs.getString("playerName");
                    Date lastJoinDate = rs.getDate("lastJoinDate");

                    return new PlayerData(uuid, playerName, lastJoinDate);
                } else {
                    System.out.println("해당 UUID의 플레이어 데이터를 찾을 수 없습니다.");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static PlayerData getPlayerDate(String playername){
        String selectSQL = "SELECT * FROM PlayerData WHERE playerName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(selectSQL)) {
            stmt.setString(1, playername);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UUID uuid = UUID.fromString(rs.getString("uuid"));
                    Date lastJoinDate = rs.getDate("lastJoinDate");

                    return new PlayerData(uuid, playername, lastJoinDate);
                } else {
                    System.out.println("해당 플레이어 데이터를 찾을 수 없습니다.");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
