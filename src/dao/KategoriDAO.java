package dao;

import db.DBConnection;
import model.Kategori;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KategoriDAO {

    // Kategori ekleme
    public void add(Kategori kategori) {
        String sql = "INSERT INTO kategori (kategori_adi) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kategori.getKategoriAdi());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Kategori eklenemedi", e);
        }
    }

    // Tüm kategoriler
    public List<Kategori> getAll() {
        List<Kategori> list = new ArrayList<>();
        String sql = "SELECT * FROM kategori";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Kategori kategori = new Kategori(
                        rs.getInt("kategori_id"),
                        rs.getString("kategori_adi")
                );
                list.add(kategori);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Kategoriler getirilemedi", e);
        }

        return list;
    }

    // ID ile kategori getirme
    public Kategori getById(int id) {
        String sql = "SELECT * FROM kategori WHERE kategori_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Kategori(
                        rs.getInt("kategori_id"),
                        rs.getString("kategori_adi")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Kategori getirilemedi: " + id, e);
        }

        throw new RuntimeException("Kategori bulunamadı: " + id);
    }

    // Güncelleme
    public void update(Kategori kategori) {
        String sql = "UPDATE kategori SET kategori_adi = ? WHERE kategori_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kategori.getKategoriAdi());
            ps.setInt(2, kategori.getKategoriId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Kategori güncellenemedi: " + kategori.getKategoriId(), e);
        }
    }

    // Silme
    public void delete(int id) {
        String sql = "DELETE FROM kategori WHERE kategori_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Kategori silinemedi: " + id, e);
        }
    }
}