import dao.KategoriDAO;
import db.DatabaseInitializer;
import model.Kategori;
import model.Personel;
import model.Sube;
import model.Urun;
import service.DepoService;
import service.PersonelService;
import service.SubeService;
import service.UrunService;

import java.util.List;

public class TestFactoryMethodSimple {

    public static void main(String[] args) {
        DatabaseInitializer.init();
        DatabaseInitializer.clearAllData();

        KategoriDAO kategoriDAO = new KategoriDAO();
        int etKategoriId = createCategoryAndGetId(kategoriDAO, "Et Urunleri");
        int sutKategoriId = createCategoryAndGetId(kategoriDAO, "Sut Urunleri");

        UrunService urunService = new UrunService();
        DepoService depoService = new DepoService();
        SubeService subeService = new SubeService();
        PersonelService personelService = new PersonelService();

        Urun etUrunu = urunService.addFactoryUrun("ET", "Dana Kiyma", 189.90, etKategoriId, 10);
        Urun sutUrunu = urunService.addFactoryUrun("SUT", "Tam Yagli Sut", 39.90, sutKategoriId, 8);

        int sutStokOnce = urunService.getLatestStockByUrunAdi("Tam Yagli Sut");
        urunService.increaseStockByUrunAdi("Tam Yagli Sut", 2);
        int sutStokArtisSonrasi = urunService.getLatestStockByUrunAdi("Tam Yagli Sut");


        // depolar eklendi
        depoService.addDepo("Yedek Depo");
        subeService.addSube("Kadikoy Sube", "Istanbul");
        subeService.addSube("Balat Sube", "Istanbul");
        subeService.addSube("Bakırkoy Sube", "Istanbul");



        //personeller eklendi
        personelService.addPersonel("Ali", "Demir", "05551112243");
        personelService.addPersonel("Mustafa", "Ozdemir", "05551112234");
        personelService.addPersonel("Efekan", "Adali", "05551112244");
        personelService.addPersonel("Ahmet", "Yagli", "05551112245");

        int subeId = getLastSubeId(subeService);
        int personelIdMetot = getLastPersonelId(personelService);
        int personelIdManuel = 2;


        urunService.transferStockToSube("Tam Yagli Sut", 1, subeId, personelIdMetot);
        int sutStokTransfer1Sonrasi = urunService.getLatestStockByUrunAdi("Tam Yagli Sut");

        urunService.transferStockToSube("Tam Yagli Sut", 1, subeId, personelIdManuel);
        int sutStokTransfer2Sonrasi = urunService.getLatestStockByUrunAdi("Tam Yagli Sut");

        System.out.println("Factory + Service testi basarili.");
        System.out.println("Uretilen tip: " + etUrunu.getUrunAdi() + " -> " + etUrunu.getBirimFiyat());
        System.out.println("Uretilen tip: " + sutUrunu.getUrunAdi() + " -> " + sutUrunu.getBirimFiyat());
        System.out.println("Tam Yagli Sut stok (once): " + sutStokOnce);
        System.out.println("Tam Yagli Sut stok (+2 sonrasi): " + sutStokArtisSonrasi);
        System.out.println("Tam Yagli Sut stok (1. transfer - metot personel): " + sutStokTransfer1Sonrasi);
        System.out.println("Tam Yagli Sut stok (2. transfer - manuel personel=3): " + sutStokTransfer2Sonrasi);
    }

    private static int createCategoryAndGetId(KategoriDAO kategoriDAO, String kategoriAdi) {
        kategoriDAO.add(new Kategori(kategoriAdi));
        List<Kategori> kategoriler = kategoriDAO.getAll();
        if (kategoriler.isEmpty()) {
            throw new RuntimeException("Kategori olusturulamadi");
        }
        return kategoriler.get(kategoriler.size() - 1).getKategoriId();
    }

    private static int getLastSubeId(SubeService subeService) {
        List<Sube> subeler = subeService.getAllSubeler();
        if (subeler.isEmpty()) {
            throw new RuntimeException("Sube olusturulamadi");
        }
        return subeler.get(subeler.size() - 1).getSubeId();
    }

    private static int getLastPersonelId(PersonelService personelService) {
        List<Personel> personeller = personelService.getAllPersoneller();
        if (personeller.isEmpty()) {
            throw new RuntimeException("Personel olusturulamadi");
        }
        return personeller.get(personeller.size() - 1).getPersonelId();
    }
}
