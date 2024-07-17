package app.dao.impl;

import app.dao.UserDao;
import main.java.entity.CommonUser;
import main.java.entity.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(CommonUser user) {
        String query = "INSERT INTO users (id, name, latitude, longitude, creationTime) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setDouble(3, user.getLocation().getLatitude());
            stmt.setDouble(4, user.getLocation().getLongitude());
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(user.getCreationTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommonUser findById(int userId) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CommonUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new Location(rs.getDouble("latitude"), rs.getDouble("longitude")),
                        rs.getTimestamp("creationTime").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CommonUser> findAll() {
        List<CommonUser> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new CommonUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new Location(rs.getDouble("latitude"), rs.getDouble("longitude")),
                        rs.getTimestamp("creationTime").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(CommonUser user) {
        String query = "UPDATE users SET name = ?, latitude = ?, longitude = ?, creationTime = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setDouble(2, user.getLocation().getLatitude());
            stmt.setDouble(3, user.getLocation().getLongitude());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(user.getCreationTime()));
            stmt.setInt(5, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
