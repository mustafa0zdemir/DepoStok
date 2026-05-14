package facade;

import dao.HareketDAO;
import dao.PartiDAO;
import model.Hareket;
import model.Parti;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class StokOperasyonFacade {
    private final PartiDAO partiDAO;
    private final HareketDAO hareketDAO;

    public StokOperasyonFacade() {
        this.partiDAO = new PartiDAO();
        this.hareketDAO = new HareketDAO();
    }

    public void stokCikisOperasyonu(int partiId, int miktar, int personelId, int subeId, int depoId) {
        if (miktar <= 0) {
            throw new IllegalArgumentException("Cikis miktari sifirdan buyuk olmali");
        }

        Parti parti = partiDAO.getById(partiId);
        if (parti.getDepoId() != depoId) {
            throw new RuntimeException("Depo uyumsuz: parti " + partiId + " depo " + depoId);
        }

        if (parti.getKalanMiktar() < miktar) {
            throw new RuntimeException("Stok yetersiz: parti " + partiId);
        }

        partiDAO.reduceStock(partiId, miktar);
        hareketDAO.add(new Hareket(partiId, personelId, subeId, miktar, "ÇIKIŞ", new Date()));

        yazTeknikOzet();
    }

    public void stokCikisSureci(int partiId, int miktar, int personelId, int subeId) {
        Parti parti = partiDAO.getById(partiId);
        stokCikisOperasyonu(partiId, miktar, personelId, subeId, parti.getDepoId());
    }

    private void yazTeknikOzet() {
        Path hedef = Paths.get(System.getProperty("user.dir"), "hafta4_teknik_ozet.txt");
        String icerik = String.join(System.lineSeparator(),
            "Uygulanan Yapısal Kalıp: Facade.",
            "Çözülen Özellik: 2 Nolu Özellik (Stok Hareket Yönetimi)."
        );

        try {
            Files.writeString(hedef, icerik, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Teknik ozet yazilamadi", e);
        }
    }
}
