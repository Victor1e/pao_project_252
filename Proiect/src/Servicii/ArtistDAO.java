package Servicii;

import Entitati.Artist;

import java.sql.*;

public class ArtistDAO extends GenericDAO<Artist> {

    @Override
    public void create(Artist artist) {
        String sql = "INSERT INTO artist(nume, gen_muzical, tara_origine) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, artist.getNume());
            stmt.setString(2, artist.getGenMuzical());
            stmt.setString(3, artist.getTaraOrigine());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Artist read(int id) {
        String sql = "SELECT * FROM artist WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Artist(
                        rs.getString("nume"),
                        rs.getString("gen_muzical"),
                        rs.getString("tara_origine"),
                        null // poți adăuga concertele separat
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Artist artist) {
        String sql = "UPDATE artist SET gen_muzical=?, tara_origine=? WHERE nume=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, artist.getGenMuzical());
            stmt.setString(2, artist.getTaraOrigine());
            stmt.setString(3, artist.getNume());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM artist WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
