package dao;

import db.DBConnection;
import model.Urun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UrunDAO {

    // Ürün ekleme
    public void add(Urun urun) {
        String findSql = "SELECT urun_id FROM urun WHERE urun_adi = ?";
        String insertSql = "INSERT INTO urun (urun_adi, birim_fiyat, kategori_id) VALUES (?, ?, ?)";
        String updateSql = "UPDATE urun SET birim_fiyat = ?, kategori_id = ? WHERE urun_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            Integer existingId = null;

            try (PreparedStatement findPs = conn.prepareStatement(findSql)) {
                findPs.setString(1, urun.getUrunAdi());
                try (ResultSet rs = findPs.executeQuery()) {
                    if (rs.next()) {
                        existingId = rs.getInt("urun_id");
                    }
                }
            }

            if (existingId == null) {
                try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
                    insertPs.setString(1, urun.getUrunAdi());
                    insertPs.setDouble(2, urun.getBirimFiyat());
                    insertPs.setInt(3, urun.getKategoriId());
                    insertPs.executeUpdate();
                }
            } else {
                try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                    updatePs.setDouble(1, urun.getBirimFiyat());
                    updatePs.setInt(2, urun.getKategoriId());
                    updatePs.setInt(3, existingId);
                    updatePs.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ürün eklenemedi", e);
        }
    }

    // Ürün listeleme
    public List<Urun> getAll() {
        List<Urun> urunList = new ArrayList<>();
        String sql = "SELECT * FROM urun";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Urun urun = new Urun(
                        rs.getInt("urun_id"),
                        rs.getString("urun_adi"),
                        rs.getDouble("birim_fiyat"),
                        rs.getInt("kategori_id")
                );
                urunList.add(urun);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ürünler getirilemedi", e);
        }

        return urunList;
    }

    // id ile getirme
    public Urun getById(int id) {
        String sql = "SELECT * FROM urun WHERE urun_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Urun(
                        rs.getInt("urun_id"),
                        rs.getString("urun_adi"),
                        rs.getDouble("birim_fiyat"),
                        rs.getInt("kategori_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ürün getirilemedi: " + id, e);
        }

        throw new RuntimeException("Ürün bulunamadı: " + id);
    }

    public Urun getByUrunAdi(String urunAdi) {
        String sql = "SELECT * FROM urun WHERE urun_adi = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, urunAdi);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Urun(
                        rs.getInt("urun_id"),
                        rs.getString("urun_adi"),
                        rs.getDouble("birim_fiyat"),
                        rs.getInt("kategori_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ürün getirilemedi: " + urunAdi, e);
        }

        throw new RuntimeException("Ürün bulunamadı: " + urunAdi);
    }

    // Ürün güncelleme
    public void update(Urun urun) {
        String sql = "UPDATE urun SET urun_adi = ?, birim_fiyat = ?, kategori_id = ? WHERE urun_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, urun.getUrunAdi());
            ps.setDouble(2, urun.getBirimFiyat());
            ps.setInt(3, urun.getKategoriId());
            ps.setInt(4, urun.getUrunId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ürün güncellenemedi: " + urun.getUrunId(), e);
        }
    }

    // Ürün silme
    public void delete(int id) {
        String sql = "DELETE FROM urun WHERE urun_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ürün silinemedi: " + id, e);
        }
    }
}
