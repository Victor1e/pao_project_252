package Servicii;

import Entitati.Concert;
import Entitati.Artist;
import Entitati.Scena;
import java.sql.*;

public class ConcertDB extends GenDB<Concert> {

    @Override
    public void create(Concert concert) {

        String sql = "INSERT IGNORE INTO concert(artist_id, scena_id, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, getArtistId(concert.getArtist()));
            stmt.setInt(2, getScenaId(concert.getScena()));
            stmt.setTimestamp(3, Timestamp.valueOf(concert.getStart()));
            stmt.setTimestamp(4, Timestamp.valueOf(concert.getEnd()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Concert read(int id) {
        String sql = "SELECT c.*, a.nume as artist_nume, s.nume as scena_nume " +
                "FROM concert c " +
                "JOIN artist a ON c.artist_id = a.id " +
                "JOIN scena s ON c.scena_id = s.id " +
                "WHERE c.id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("artist_nume"),
                        null, null, null
                );
                Scena scena = new Scena(
                        rs.getString("scena_nume"),
                        0, null
                );
                return new Concert(
                        artist,
                        scena,
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("end_time").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Concert concert) {
        String sql = "UPDATE concert SET artist_id=?, scena_id=?, start_time=?, end_time=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, getArtistId(concert.getArtist()));
            stmt.setInt(2, getScenaId(concert.getScena()));
            stmt.setTimestamp(3, Timestamp.valueOf(concert.getStart()));
            stmt.setTimestamp(4, Timestamp.valueOf(concert.getEnd()));
            stmt.setInt(5, getIdFromConcert(concert));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM concert WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getArtistId(Artist artist) throws SQLException {
        String sql = "SELECT id FROM artist WHERE nume=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, artist.getNume());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    private int getScenaId(Scena scena) throws SQLException {
        String sql = "SELECT id FROM scena WHERE nume=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, scena.getNume());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    private int getIdFromConcert(Concert concert) throws SQLException {
        String sql = "SELECT id FROM concert WHERE artist_id=? AND scena_id=? AND start_time=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, getArtistId(concert.getArtist()));
            stmt.setInt(2, getScenaId(concert.getScena()));
            stmt.setTimestamp(3, Timestamp.valueOf(concert.getStart()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }
}