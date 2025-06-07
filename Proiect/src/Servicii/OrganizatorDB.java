package Servicii;

import Entitati.Organizator;

import java.sql.*;

public class OrganizatorDB extends GenDB<Organizator> {

    @Override
    public void create(Organizator organizator) {
        String sql = "INSERT IGNORE INTO organizator(nume, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, organizator.getNume());
            stmt.setString(2, organizator.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Organizator read(int id) {
        String sql = "SELECT * FROM organizator WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Organizator(
                        rs.getString("nume"),
                        rs.getString("email"),
                        null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Organizator organizator) {
        String sql = "UPDATE organizator SET email = ? WHERE nume = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, organizator.getEmail());
            stmt.setString(2, organizator.getNume());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM organizator WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
