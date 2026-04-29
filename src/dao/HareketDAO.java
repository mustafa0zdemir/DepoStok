package dao;

import db.DBConnection;
import model.Hareket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HareketDAO {

    // Hareket ekleme (stok hareketi)
    public void add(Hareket hareket) {

        String sql = "INSERT INTO hareket (parti_id, personel_id, sube_id, miktar, tip, tarih) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hareket.getPartiId());
            ps.setInt(2, hareket.getPersonelId());
            ps.setInt(3, hareket.getSubeId());
            ps.setInt(4, hareket.getMiktar());
            ps.setString(5, hareket.getTip()); // GİRİŞ / ÇIKIŞ
            ps.setDate(6, new java.sql.Date(hareket.getTarih().getTime()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Hareket eklenemedi", e);
        }
    }

    // Tüm hareketleri listele
    public List<Hareket> getAll() {

        List<Hareket> list = new ArrayList<>();
        String sql = "SELECT * FROM hareket";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Hareket h = new Hareket(
                        rs.getInt("hareket_id"),
                        rs.getInt("parti_id"),
                        rs.getInt("personel_id"),
                        rs.getInt("sube_id"),
                        rs.getInt("miktar"),
                        rs.getString("tip"),
                        rs.getDate("tarih")
                );

                list.add(h);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Hareketler getirilemedi", e);
        }

        return list;
    }

    // ID ile getir
    public Hareket getById(int id) {

        String sql = "SELECT * FROM hareket WHERE hareket_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Hareket(
                        rs.getInt("hareket_id"),
                        rs.getInt("parti_id"),
                        rs.getInt("personel_id"),
                        rs.getInt("sube_id"),
                        rs.getInt("miktar"),
                        rs.getString("tip"),
                        rs.getDate("tarih")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Hareket bulunamadı: " + id, e);
        }

        throw new RuntimeException("Hareket bulunamadı: " + id);
    }

    // Silme (çok dikkat: stok geçmişi silmek genelde önerilmez)
    public void delete(int id) {

        String sql = "DELETE FROM hareket WHERE hareket_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Hareket silinemedi", e);
        }
    }
}