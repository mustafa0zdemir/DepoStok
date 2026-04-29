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
        String sql = "INSERT INTO urun (urun_adi, birim_fiyat, kategori_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, urun.getUrunAdi());
            ps.setDouble(2, urun.getBirimFiyat());
            ps.setInt(3, urun.getKategoriId());

            ps.executeUpdate();

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