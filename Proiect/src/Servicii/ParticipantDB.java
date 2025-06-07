package Servicii;

import Database.DBConnection;
import Entitati.Participant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDB {
    private Connection connection;

    public ParticipantDB() {
        this.connection = DBConnection.getConnection();
    }





    public void insertParticipant(Participant participant) {
        ParticipantService.verificaVarsta(participant.getVarsta());
        String sql = "INSERT IGNORE INTO participant(nume, email, varsta) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, participant.getNume());
            stmt.setString(2, participant.getEmail());
            stmt.setInt(3, participant.getVarsta());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Participant getParticipantById(int id) {
        String sql = "SELECT nume, email, varsta FROM participant WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nume = rs.getString("nume");
                    String email = rs.getString("email");
                    int varsta = rs.getInt("varsta");
                    return new Participant(nume, email, varsta, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateParticipant(int id, String newEmail, int newVarsta) {
        if (newVarsta < 18) {
            throw new IllegalArgumentException("Varsta trebuie să fie >= 18 ani");
        }
        String sql = "UPDATE participant SET email = ?, varsta = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newEmail);
            stmt.setInt(2, newVarsta);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteParticipant(int id) {
        String sql = "DELETE FROM participant WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Participant> getAllParticipants() {
        List<Participant> lista = new ArrayList<>();
        String sql = "SELECT id, nume, email, varsta FROM participant";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nume = rs.getString("nume");
                String email = rs.getString("email");
                int varsta = rs.getInt("varsta");
                lista.add(new Participant(nume, email, varsta, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
