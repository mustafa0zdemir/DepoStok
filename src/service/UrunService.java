package service;

import dao.DepoDAO;
import dao.HareketDAO;
import dao.PartiDAO;
import dao.UrunDAO;
import factory.GidaFabrikasi;
import model.Depo;
import model.Hareket;
import model.Parti;
import model.Urun;

import java.util.Date;
import java.util.List;

public class UrunService {
    private static final String DEFAULT_DEPO_ADI = "Merkez Depo";
    private final UrunDAO urunDAO;
    private final DepoDAO depoDAO;
    private final PartiDAO partiDAO;
    private final HareketDAO hareketDAO;

    public UrunService() {
        this.urunDAO = new UrunDAO();
        this.depoDAO = new DepoDAO();
        this.partiDAO = new PartiDAO();
        this.hareketDAO = new HareketDAO();
    }

    public Urun addFactoryUrun(String tur, String ad, double fiyat, int kategoriId, int ilkStok) {
        Urun urun = GidaFabrikasi.gidaOlustur(tur);
        urun.setUrunAdi(ad);
        urun.setBirimFiyat(fiyat);
        urun.setKategoriId(kategoriId);

        urunDAO.add(urun);
        Urun kayitliUrun = urunDAO.getByUrunAdi(ad);

        int depoId = getOrCreateDefaultDepoId();
        Parti parti = partiDAO.getLatestPartiByUrunIdOrNull(kayitliUrun.getUrunId());
        if (parti == null) {
            partiDAO.add(new Parti(kayitliUrun.getUrunId(), depoId, ilkStok, new Date()));
        } else {
            partiDAO.increaseStock(parti.getPartiId(), ilkStok);
        }
        return kayitliUrun;
    }

    public List<Urun> getAllUrunler() {
        return urunDAO.getAll();
    }

    public int getTotalUrunCount() {
        return urunDAO.getAll().size();
    }

    public void increaseStockByUrunAdi(String urunAdi, int miktar) {
        Urun urun = urunDAO.getByUrunAdi(urunAdi);
        Parti parti = partiDAO.getLatestPartiByUrunIdOrNull(urun.getUrunId());
        if (parti == null) {
            int depoId = getOrCreateDefaultDepoId();
            partiDAO.add(new Parti(urun.getUrunId(), depoId, miktar, new Date()));
            return;
        }
        partiDAO.increaseStock(parti.getPartiId(), miktar);
    }

    public void reduceStockByUrunAdi(String urunAdi, int miktar) {
        Urun urun = urunDAO.getByUrunAdi(urunAdi);
        Parti parti = partiDAO.getLatestPartiByUrunIdOrNull(urun.getUrunId());
        if (parti == null) {
            throw new RuntimeException("Stok azaltilamadi: urune ait parti bulunamadi -> " + urunAdi);
        }
        if (parti.getKalanMiktar() < miktar) {
            throw new RuntimeException("Stok yetersiz: " + urunAdi);
        }
        partiDAO.reduceStock(parti.getPartiId(), miktar);
    }

    public void transferStockToSube(String urunAdi, int miktar, int subeId, int personelId) {
        Urun urun = urunDAO.getByUrunAdi(urunAdi);
        Parti parti = partiDAO.getLatestPartiByUrunIdOrNull(urun.getUrunId());
        if (parti == null) {
            throw new RuntimeException("Transfer basarisiz: urune ait parti bulunamadi -> " + urunAdi);
        }
        if (parti.getKalanMiktar() < miktar) {
            throw new RuntimeException("Transfer basarisiz: yetersiz stok -> " + urunAdi);
        }

        partiDAO.reduceStock(parti.getPartiId(), miktar);
        hareketDAO.add(new Hareket(
                parti.getPartiId(),
                personelId,
                subeId,
                miktar,
                "CIKIS_SUBE",
                new Date()
        ));
    }

    public int getLatestStockByUrunAdi(String urunAdi) {
        Urun urun = urunDAO.getByUrunAdi(urunAdi);
        Parti parti = partiDAO.getLatestPartiByUrunIdOrNull(urun.getUrunId());
        return parti == null ? 0 : parti.getKalanMiktar();
    }

    private int getOrCreateDefaultDepoId() {
        List<Depo> depolar = depoDAO.getAll();
        for (Depo depo : depolar) {
            if (DEFAULT_DEPO_ADI.equals(depo.getDepoAdi())) {
                return depo.getDepoId();
            }
        }

        depoDAO.add(new Depo(DEFAULT_DEPO_ADI));
        List<Depo> guncelDepolar = depoDAO.getAll();
        if (guncelDepolar.isEmpty()) {
            throw new RuntimeException("Depo olusturulamadi");
        }
        return guncelDepolar.get(guncelDepolar.size() - 1).getDepoId();
    }
}
