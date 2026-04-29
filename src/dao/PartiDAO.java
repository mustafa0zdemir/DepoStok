package dao;

import db.DBConnection;
import model.Parti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartiDAO {

    // Parti ekleme (stok giriş)
    public void add(Parti parti) {
        String sql = "INSERT INTO parti (urun_id, depo_id, kalan_miktar, son_kullanma_tarihi) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, parti.getUrunId());
            ps.setInt(2, parti.getDepoId());
            ps.setInt(3, parti.getKalanMiktar());
            ps.setDate(4, new java.sql.Date(parti.getSonKullanmaTarihi().getTime()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Parti eklenemedi", e);
        }
    }

    // Tüm partiler
    public List<Parti> getAll() {
        List<Parti> list = new ArrayList<>();
        String sql = "SELECT * FROM parti";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Parti parti = new Parti(
                        rs.getInt("parti_id"),
                        rs.getInt("urun_id"),
                        rs.getInt("depo_id"),
                        rs.getInt("kalan_miktar"),
                        rs.getDate("son_kullanma_tarihi")
                );
                list.add(parti);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Partiler getirilemedi", e);
        }

        return list;
    }

    // ID ile getirme
    public Parti getById(int id) {
        String sql = "SELECT * FROM parti WHERE parti_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Parti(
                        rs.getInt("parti_id"),
                        rs.getInt("urun_id"),
                        rs.getInt("depo_id"),
                        rs.getInt("kalan_miktar"),
                        rs.getDate("son_kullanma_tarihi")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Parti getirilemedi: " + id, e);
        }

        throw new RuntimeException("Parti bulunamadı: " + id);
    }

    // Güncelleme (stok değişimi burada olur)
    public void update(Parti parti) {
        String sql = "UPDATE parti SET urun_id = ?, depo_id = ?, kalan_miktar = ?, son_kullanma_tarihi = ? WHERE parti_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, parti.getUrunId());
            ps.setInt(2, parti.getDepoId());
            ps.setInt(3, parti.getKalanMiktar());
            ps.setDate(4, new java.sql.Date(parti.getSonKullanmaTarihi().getTime()));
            ps.setInt(5, parti.getPartiId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Parti güncellenemedi", e);
        }
    }

    // Silme
    public void delete(int id) {
        String sql = "DELETE FROM parti WHERE parti_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Parti silinemedi: " + id, e);
        }
    }

    // Kritik: stok düşme işlemi (çekirdek mantık)
    public void reduceStock(int partiId, int miktar) {
        String sql = "UPDATE parti SET kalan_miktar = kalan_miktar - ? WHERE parti_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, miktar);
            ps.setInt(2, partiId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Stok düşürülemedi (parti): " + partiId, e);
        }
    }
}