package dao;

import db.DBConnection;
import model.Personel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonelDAO {

    // Personel ekleme
    public void add(Personel personel) {
        String sql = "INSERT INTO personel (ad, soyad, telefon_no) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, personel.getAd());
            ps.setString(2, personel.getSoyad());
            ps.setString(3, personel.getTelefonNo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Personel eklenemedi", e);
        }
    }

    // Tüm personeller
    public List<Personel> getAll() {
        List<Personel> list = new ArrayList<>();
        String sql = "SELECT * FROM personel";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Personel personel = new Personel(
                        rs.getInt("personel_id"),
                        rs.getString("ad"),
                        rs.getString("soyad"),
                        rs.getString("telefon_no")
                );
                list.add(personel);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Personeller getirilemedi", e);
        }

        return list;
    }

    // ID ile getirme
    public Personel getById(int id) {
        String sql = "SELECT * FROM personel WHERE personel_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Personel(
                        rs.getInt("personel_id"),
                        rs.getString("ad"),
                        rs.getString("soyad"),
                        rs.getString("telefon_no")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Personel getirilemedi: " + id, e);
        }

        throw new RuntimeException("Personel bulunamadı: " + id);
    }

    // Güncelleme
    public void update(Personel personel) {
        String sql = "UPDATE personel SET ad = ?, soyad = ?, telefon_no = ? WHERE personel_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, personel.getAd());
            ps.setString(2, personel.getSoyad());
            ps.setString(3, personel.getTelefonNo());
            ps.setInt(4, personel.getPersonelId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Personel güncellenemedi: " + personel.getPersonelId(), e);
        }
    }

    // Silme
    public void delete(int id) {
        String sql = "DELETE FROM personel WHERE personel_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Personel silinemedi: " + id, e);
        }
    }
}