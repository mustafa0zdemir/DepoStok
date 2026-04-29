import dao.KategoriDAO;
import db.DatabaseInitializer;
import model.Kategori;

public class Main {
    public static void main(String[] args) {

        KategoriDAO kategoriDAO = new KategoriDAO();

        Kategori kategori = new Kategori("Gıda");
        kategoriDAO.add(kategori);

        DatabaseInitializer.init();
        dao.UrunDAO urunDAO = new dao.UrunDAO();
        model.Urun urun = factory.GidaFabrikasi.gidaOlustur("ET");

        urun.setUrunAdi("Dana Kiyma");
        urun.setBirimFiyat(189.90);
        urun.setKategoriId(1);

        urunDAO.add(urun);

        System.out.println("Urun eklendi. Toplam urun sayisi: " + urunDAO.getAll().size());
    }
}