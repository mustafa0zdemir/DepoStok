package dao;

import db.DBConnection;
import model.Sube;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubeDAO {

    // Şube ekleme
    public void add(Sube sube) {
        String sql = "INSERT INTO sube (sube_adi, adres) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sube.getSubeAdi());
            ps.setString(2, sube.getAdres());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Şube eklenemedi", e);
        }
    }

    // Tüm şubeler
    public List<Sube> getAll() {
        List<Sube> list = new ArrayList<>();
        String sql = "SELECT * FROM sube";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sube sube = new Sube(
                        rs.getInt("sube_id"),
                        rs.getString("sube_adi"),
                        rs.getString("adres")
                );
                list.add(sube);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Şubeler getirilemedi", e);
        }

        return list;
    }

    // ID ile getirme
    public Sube getById(int id) {
        String sql = "SELECT * FROM sube WHERE sube_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Sube(
                        rs.getInt("sube_id"),
                        rs.getString("sube_adi"),
                        rs.getString("adres")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Şube getirilemedi: " + id, e);
        }

        throw new RuntimeException("Şube bulunamadı: " + id);
    }

    // Güncelleme
    public void update(Sube sube) {
        String sql = "UPDATE sube SET sube_adi = ?, adres = ? WHERE sube_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sube.getSubeAdi());
            ps.setString(2, sube.getAdres());
            ps.setInt(3, sube.getSubeId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Şube güncellenemedi: " + sube.getSubeId(), e);
        }
    }

    // Silme
    public void delete(int id) {
        String sql = "DELETE FROM sube WHERE sube_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Şube silinemedi: " + id, e);
        }
    }
}