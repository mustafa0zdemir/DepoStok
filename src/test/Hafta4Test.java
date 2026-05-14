package test;

import dao.HareketDAO;
import dao.KategoriDAO;
import dao.PartiDAO;
import dao.PersonelDAO;
import dao.SubeDAO;
import db.DatabaseInitializer;
import facade.StokOperasyonFacade;
import model.Hareket;
import model.Kategori;
import model.Parti;
import model.Personel;
import model.Sube;
import model.Urun;
import service.UrunService;

import java.util.List;

public class Hafta4Test {
    public static void main(String[] args) {
        DatabaseInitializer.init();

        KategoriDAO kategoriDAO = new KategoriDAO();
        PersonelDAO personelDAO = new PersonelDAO();
        SubeDAO subeDAO = new SubeDAO();
        PartiDAO partiDAO = new PartiDAO();
        HareketDAO hareketDAO = new HareketDAO();
        UrunService urunService = new UrunService();

        int kategoriId = getOrCreateKategoriId(kategoriDAO, "Hafta4TestKategori");
        int personelId = getOrCreatePersonelId(personelDAO, "Test", "Personel", "0000000000");
        int subeId = getOrCreateSubeId(subeDAO, "Hafta4TestSube", "Test Adres");

        String urunAdi = "TestUrun_" + System.currentTimeMillis();
        Urun urun = urunService.addFactoryUrun("SEBZE", urunAdi, 10.0, kategoriId, 10);
        Parti parti = partiDAO.getLatestPartiByUrunIdOrNull(urun.getUrunId());
        if (parti == null) {
            throw new RuntimeException("Test icin parti bulunamadi");
        }

        System.out.println("Stok cikis oncesi: " + parti.getKalanMiktar());

        StokOperasyonFacade facade = new StokOperasyonFacade();
        facade.stokCikisSureci(parti.getPartiId(), 3, personelId, subeId);

        Parti guncelParti = partiDAO.getById(parti.getPartiId());
        System.out.println("Stok cikis sonrasi: " + guncelParti.getKalanMiktar());

        List<Hareket> hareketler = hareketDAO.getAll();
        if (!hareketler.isEmpty()) {
            Hareket sonHareket = hareketler.get(hareketler.size() - 1);
            System.out.println("Son hareket tipi: " + sonHareket.getTip());
        }

        System.out.println("Hafta4 test tamamlandi");
    }

    private static int getOrCreateKategoriId(KategoriDAO kategoriDAO, String kategoriAdi) {
        List<Kategori> kategoriler = kategoriDAO.getAll();
        for (Kategori kategori : kategoriler) {
            if (kategoriAdi.equals(kategori.getKategoriAdi())) {
                return kategori.getKategoriId();
            }
        }
        kategoriDAO.add(new Kategori(kategoriAdi));
        List<Kategori> guncel = kategoriDAO.getAll();
        return guncel.get(guncel.size() - 1).getKategoriId();
    }

    private static int getOrCreatePersonelId(PersonelDAO personelDAO, String ad, String soyad, String tel) {
        List<Personel> personeller = personelDAO.getAll();
        for (Personel personel : personeller) {
            if (ad.equals(personel.getAd()) && soyad.equals(personel.getSoyad())) {
                return personel.getPersonelId();
            }
        }
        personelDAO.add(new Personel(ad, soyad, tel));
        List<Personel> guncel = personelDAO.getAll();
        return guncel.get(guncel.size() - 1).getPersonelId();
    }

    private static int getOrCreateSubeId(SubeDAO subeDAO, String subeAdi, String adres) {
        List<Sube> subeler = subeDAO.getAll();
        for (Sube sube : subeler) {
            if (subeAdi.equals(sube.getSubeAdi())) {
                return sube.getSubeId();
            }
        }
        subeDAO.add(new Sube(subeAdi, adres));
        List<Sube> guncel = subeDAO.getAll();
        return guncel.get(guncel.size() - 1).getSubeId();
    }
}
