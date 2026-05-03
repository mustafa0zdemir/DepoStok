package legacy;

import db.DatabaseInitializer;
import model.Depo;
import model.Hareket;
import model.Kategori;
import model.Parti;
import model.Personel;
import model.Sube;
import model.Urun;
import service.DepoService;
import service.HareketService;
import service.KategoriService;
import service.PartiService;
import service.PersonelService;
import service.SubeService;
import service.UrunService;

import java.util.Date;
import java.util.List;

public class TestServiceAddMethods {

    // Legacy: aktif test giris noktasi degildir.
    public static void mainLegacy(String[] args) {
        DatabaseInitializer.init();
        DatabaseInitializer.clearAllData();

        KategoriService kategoriService = new KategoriService();
        DepoService depoService = new DepoService();
        SubeService subeService = new SubeService();
        PersonelService personelService = new PersonelService();
        UrunService urunService = new UrunService();
        PartiService partiService = new PartiService();
        HareketService hareketService = new HareketService();

        testKategoriAdd(kategoriService);
        int kategoriId = getLastKategoriId(kategoriService);

        testDepoAdd(depoService);
        int depoId = getLastDepoId(depoService);

        testSubeAdd(subeService);
        int subeId = getLastSubeId(subeService);

        testPersonelAdd(personelService);
        int personelId = getLastPersonelId(personelService);

        testUrunAdd(urunService, kategoriId);
        int urunId = getLastUrunId(urunService);

        testPartiAdd(partiService, urunId, depoId);
        int partiId = getLastPartiId(partiService);

        testHareketAdd(hareketService, partiId, personelId, subeId);

        System.out.println("All add-service tests passed.");
    }

    private static void testKategoriAdd(KategoriService kategoriService) {
        int before = kategoriService.getAllKategoriler().size();
        kategoriService.addKategori("Test Kategori");
        int after = kategoriService.getAllKategoriler().size();
        assertIncreased(before, after, "Kategori add testi basarisiz");
    }

    private static void testDepoAdd(DepoService depoService) {
        int before = depoService.getAllDepolar().size();
        depoService.addDepo("Test Depo");
        int after = depoService.getAllDepolar().size();
        assertIncreased(before, after, "Depo add testi basarisiz");
    }

    private static void testSubeAdd(SubeService subeService) {
        int before = subeService.getAllSubeler().size();
        subeService.addSube("Test Sube", "Test Adres");
        int after = subeService.getAllSubeler().size();
        assertIncreased(before, after, "Sube add testi basarisiz");
    }

    private static void testPersonelAdd(PersonelService personelService) {
        int before = personelService.getAllPersoneller().size();
        personelService.addPersonel("Ali", "Yilmaz", "05550000000");
        int after = personelService.getAllPersoneller().size();
        assertIncreased(before, after, "Personel add testi basarisiz");
    }

    private static void testUrunAdd(UrunService urunService, int kategoriId) {
        int before = urunService.getTotalUrunCount();
        String suffix = String.valueOf(System.currentTimeMillis());
        urunService.addFactoryUrun("ET", "Test Et " + suffix, 120.0, kategoriId, 10);
        int after = urunService.getTotalUrunCount();
        assertIncreased(before, after, "Urun add testi basarisiz");
    }

    private static void testPartiAdd(PartiService partiService, int urunId, int depoId) {
        int before = partiService.getAllPartiler().size();
        partiService.addParti(urunId, depoId, 20, new Date());
        int after = partiService.getAllPartiler().size();
        assertIncreased(before, after, "Parti add testi basarisiz");
    }

    private static void testHareketAdd(HareketService hareketService, int partiId, int personelId, int subeId) {
        int before = hareketService.getAllHareketler().size();
        hareketService.addHareket(partiId, personelId, subeId, 1, "GIRIS", new Date());
        int after = hareketService.getAllHareketler().size();
        assertIncreased(before, after, "Hareket add testi basarisiz");
    }

    private static int getLastKategoriId(KategoriService kategoriService) {
        List<Kategori> list = kategoriService.getAllKategoriler();
        return list.get(list.size() - 1).getKategoriId();
    }

    private static int getLastDepoId(DepoService depoService) {
        List<Depo> list = depoService.getAllDepolar();
        return list.get(list.size() - 1).getDepoId();
    }

    private static int getLastSubeId(SubeService subeService) {
        List<Sube> list = subeService.getAllSubeler();
        return list.get(list.size() - 1).getSubeId();
    }

    private static int getLastPersonelId(PersonelService personelService) {
        List<Personel> list = personelService.getAllPersoneller();
        return list.get(list.size() - 1).getPersonelId();
    }

    private static int getLastUrunId(UrunService urunService) {
        List<Urun> list = urunService.getAllUrunler();
        return list.get(list.size() - 1).getUrunId();
    }

    private static int getLastPartiId(PartiService partiService) {
        List<Parti> list = partiService.getAllPartiler();
        return list.get(list.size() - 1).getPartiId();
    }

    private static void assertIncreased(int before, int after, String message) {
        if (after <= before) {
            throw new AssertionError(message);
        }
    }
}
