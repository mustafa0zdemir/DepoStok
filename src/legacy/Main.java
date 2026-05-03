package legacy;

import dao.KategoriDAO;
import db.DatabaseInitializer;
import model.Kategori;
import model.Urun;
import service.UrunService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // Legacy: aktif demo giris noktasi degildir.
    public static void mainLegacy(String[] args) {
        DatabaseInitializer.init();

        KategoriDAO kategoriDAO = new KategoriDAO();
        UrunService urunService = new UrunService();
        int kategoriId = createCategoryAndGetId(kategoriDAO, "Factory Demo Kategori");

        List<Urun> urunler = new ArrayList<>();
        urunler.add(urunService.addFactoryUrun("ET", "Dana Kiyma", 189.90, kategoriId, 10));
        urunler.add(urunService.addFactoryUrun("SUT", "Tam Yagli Sut", 39.90, kategoriId, 10));
        urunler.add(urunService.addFactoryUrun("SEBZE", "Domates", 24.90, kategoriId, 10));
        urunler.add(urunService.addFactoryUrun("PAKETLI", "Makarna", 29.90, kategoriId, 10));

        System.out.println("Factory ile olusturulan ve DB'ye kaydedilen urunler:");
        for (Urun urun : urunler) {
            System.out.println(
                    urun.getClass().getSimpleName() + " -> " +
                    urun.getUrunAdi() + ", Fiyat: " + urun.getBirimFiyat()
            );
        }

        System.out.println("Toplam urun sayisi: " + urunService.getTotalUrunCount());
    }

    private static int createCategoryAndGetId(KategoriDAO kategoriDAO, String kategoriAdi) {
        kategoriDAO.add(new Kategori(kategoriAdi));
        List<Kategori> kategoriler = kategoriDAO.getAll();
        if (kategoriler.isEmpty()) {
            throw new RuntimeException("Kategori olusturulamadi");
        }
        return kategoriler.get(kategoriler.size() - 1).getKategoriId();
    }
}
