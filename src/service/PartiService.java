package service;

import dao.PartiDAO;
import model.Parti;

import java.util.Date;
import java.util.List;

public class PartiService {
    private final PartiDAO partiDAO;

    public PartiService() {
        this.partiDAO = new PartiDAO();
    }

    public void addParti(int urunId, int depoId, int miktar, Date sonKullanmaTarihi) {
        partiDAO.add(new Parti(urunId, depoId, miktar, sonKullanmaTarihi));
    }

    public List<Parti> getAllPartiler() {
        return partiDAO.getAll();
    }

    public Parti getPartiById(int partiId) {
        return partiDAO.getById(partiId);
    }

    public void updateParti(int partiId, int urunId, int depoId, int kalanMiktar, Date sonKullanmaTarihi) {
        Parti parti = partiDAO.getById(partiId);
        parti.setUrunId(urunId);
        parti.setDepoId(depoId);
        parti.setKalanMiktar(kalanMiktar);
        parti.setSonKullanmaTarihi(sonKullanmaTarihi);
        partiDAO.update(parti);
    }

    public void deleteParti(int partiId) {
        partiDAO.delete(partiId);
    }

    public void increasePartiStock(int partiId, int miktar) {
        partiDAO.increaseStock(partiId, miktar);
    }

    public void reducePartiStock(int partiId, int miktar) {
        partiDAO.reduceStock(partiId, miktar);
    }

    public void increaseStockByUrunId(int urunId, int miktar) {
        partiDAO.increaseStockByUrunId(urunId, miktar);
    }
}
