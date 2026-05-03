package legacy;

import dao.KategoriDAO;
import db.DatabaseInitializer;
import factory.GidaFabrikasi;
import model.EtUrunu;
import model.Kategori;
import model.PaketliGida;
import model.Sebze;
import model.SutUrunu;
import model.Urun;
import service.UrunService;

import java.util.List;

public class TestGidaFabrikasi {

    // Legacy: aktif test giris noktasi degildir.
    public static void mainLegacy(String[] args) {
        DatabaseInitializer.init();
        DatabaseInitializer.clearAllData();

        testFactoryCreatesEtUrunu();
        testFactoryCreatesSutUrunu();
        testFactoryCreatesSebze();
        testFactoryCreatesPaketliGida();
        testFactoryProductsArePersistedInDatabase();
        testEtStockIncreaseByOne();
        testFactoryRejectsNullType();
        testFactoryRejectsUnsupportedType();

        System.out.println("All tests passed.");
    }

    private static void testFactoryCreatesEtUrunu() {
        Urun urun = GidaFabrikasi.gidaOlustur("ET");
        if (!(urun instanceof EtUrunu)) {
            throw new AssertionError("Factory did not create EtUrunu for ET");
        }
    }

    private static void testFactoryCreatesSutUrunu() {
        Urun urun = GidaFabrikasi.gidaOlustur("SUT");
        if (!(urun instanceof SutUrunu)) {
            throw new AssertionError("Factory did not create SutUrunu for SUT");
        }
    }

    private static void testFactoryCreatesSebze() {
        Urun urun = GidaFabrikasi.gidaOlustur("SEBZE");
        if (!(urun instanceof Sebze)) {
            throw new AssertionError("Factory did not create Sebze for SEBZE");
        }
    }

    private static void testFactoryCreatesPaketliGida() {
        Urun urun = GidaFabrikasi.gidaOlustur("PAKETLI");
        if (!(urun instanceof PaketliGida)) {
            throw new AssertionError("Factory did not create PaketliGida for PAKETLI");
        }
    }

    private static void testFactoryProductsArePersistedInDatabase() {
        UrunService urunService = new UrunService();
        KategoriDAO kategoriDAO = new KategoriDAO();
        int kategoriId = createCategoryAndGetId(kategoriDAO, "Factory Test Kategori");

        int beforeCount = urunService.getTotalUrunCount();
        String suffix = String.valueOf(System.currentTimeMillis());

        urunService.addFactoryUrun("ET", "Test ET " + suffix, 100.0, kategoriId, 5);
        urunService.addFactoryUrun("SUT", "Test SUT " + suffix, 50.0, kategoriId, 5);
        urunService.addFactoryUrun("SEBZE", "Test SEBZE " + suffix, 30.0, kategoriId, 5);
        urunService.addFactoryUrun("PAKETLI", "Test PAKETLI " + suffix, 20.0, kategoriId, 5);

        int afterCount = urunService.getTotalUrunCount();
        if (afterCount < beforeCount + 4) {
            throw new AssertionError("Factory ile olusturulan urunler DB'ye beklenen sayida kaydedilemedi");
        }
    }

    private static void testEtStockIncreaseByOne() {
        UrunService urunService = new UrunService();
        KategoriDAO kategoriDAO = new KategoriDAO();
        int kategoriId = createCategoryAndGetId(kategoriDAO, "Stok Test Kategori");

        urunService.addFactoryUrun("ET", "Dana Kiyma", 189.90, kategoriId, 10);

        int before = urunService.getLatestStockByUrunAdi("Dana Kiyma");
        urunService.increaseStockByUrunAdi("Dana Kiyma", 1);
        int after = urunService.getLatestStockByUrunAdi("Dana Kiyma");

        if (after != before + 1) {
            throw new AssertionError("ET urunu stogu 1 artirilamadi");
        }
    }

    private static void testFactoryRejectsNullType() {
        try {
            GidaFabrikasi.gidaOlustur(null);
            throw new AssertionError("Factory should throw for null type");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    private static void testFactoryRejectsUnsupportedType() {
        try {
            GidaFabrikasi.gidaOlustur("ICECEK");
            throw new AssertionError("Factory should throw for unsupported type");
        } catch (IllegalArgumentException e) {
            // expected
        }
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
