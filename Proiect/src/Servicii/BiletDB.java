package Servicii;

import Entitati.Bilet;
import Entitati.Festival;
import java.sql.*;

public class BiletDB extends GenDB<Bilet> {

    @Override
    public void create(Bilet bilet) {
        String sql = "INSERT IGNORE INTO bilet(cod, festival_id, pret) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bilet.getCod());
            stmt.setInt(2, getFestivalId(bilet.getFestival()));
            stmt.setDouble(3, bilet.getPret());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bilet read(int id) {
        String sql = "SELECT b.*, f.nume as festival_nume FROM bilet b JOIN festival f ON b.festival_id = f.id WHERE b.id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Festival festival = new Festival(
                        rs.getString("festival_nume"),
                        null, null, null, null
                );
                return new Bilet(
                        rs.getString("cod"),
                        festival,
                        rs.getDouble("pret")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Bilet bilet) {
        String sql = "UPDATE bilet SET pret=?, festival_id=? WHERE cod=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, bilet.getPret());
            stmt.setInt(2, getFestivalId(bilet.getFestival()));
            stmt.setString(3, bilet.getCod());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM bilet WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getFestivalId(Festival festival) throws SQLException {
        String sql = "SELECT id FROM festival WHERE nume=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, festival.getNume());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }


    public void updatePriceByCod(String cod, double newPrice) {
        String sql = "UPDATE IGNORE bilet SET pret = ? WHERE cod = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, newPrice);
            stmt.setString(2, cod);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Nicio modificare. Fie nu există codul " + cod + ", fie același preț deja există.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}