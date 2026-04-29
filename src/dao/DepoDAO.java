package dao;

import db.DBConnection;
import model.Depo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepoDAO {

    // Depo ekleme
    public void add(Depo depo) {
        String sql = "INSERT INTO depo (depo_adi) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, depo.getDepoAdi());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Depo eklenemedi", e);
        }
    }

    // Tüm depolar
    public List<Depo> getAll() {
        List<Depo> list = new ArrayList<>();
        String sql = "SELECT * FROM depo";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Depo depo = new Depo(
                        rs.getInt("depo_id"),
                        rs.getString("depo_adi")
                );
                list.add(depo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Depolar getirilemedi", e);
        }

        return list;
    }

    // ID ile depo getirme
    public Depo getById(int id) {
        String sql = "SELECT * FROM depo WHERE depo_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Depo(
                        rs.getInt("depo_id"),
                        rs.getString("depo_adi")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Depo getirilemedi: " + id, e);
        }

        throw new RuntimeException("Depo bulunamadı: " + id);
    }

    // Güncelleme
    public void update(Depo depo) {
        String sql = "UPDATE depo SET depo_adi = ? WHERE depo_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, depo.getDepoAdi());
            ps.setInt(2, depo.getDepoId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Depo güncellenemedi: " + depo.getDepoId(), e);
        }
    }

    // Silme
    public void delete(int id) {
        String sql = "DELETE FROM depo WHERE depo_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Depo silinemedi: " + id, e);
        }
    }
}