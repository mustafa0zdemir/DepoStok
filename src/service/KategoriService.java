package service;

import dao.KategoriDAO;
import model.Kategori;

import java.util.List;

public class KategoriService {
    private final KategoriDAO kategoriDAO;

    public KategoriService() {
        this.kategoriDAO = new KategoriDAO();
    }

    public void addKategori(String kategoriAdi) {
        kategoriDAO.add(new Kategori(kategoriAdi));
    }

    public List<Kategori> getAllKategoriler() {
        return kategoriDAO.getAll();
    }

    public Kategori getKategoriById(int kategoriId) {
        return kategoriDAO.getById(kategoriId);
    }

    public void updateKategori(int kategoriId, String kategoriAdi) {
        Kategori kategori = kategoriDAO.getById(kategoriId);
        kategori.setKategoriAdi(kategoriAdi);
        kategoriDAO.update(kategori);
    }

    public void deleteKategori(int kategoriId) {
        kategoriDAO.delete(kategoriId);
    }
}
