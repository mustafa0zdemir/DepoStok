package service;

import dao.HareketDAO;
import model.Hareket;

import java.util.Date;
import java.util.List;

public class HareketService {
    private final HareketDAO hareketDAO;

    public HareketService() {
        this.hareketDAO = new HareketDAO();
    }

    public void addHareket(int partiId, int personelId, int subeId, int miktar, String tip, Date tarih) {
        hareketDAO.add(new Hareket(partiId, personelId, subeId, miktar, tip, tarih));
    }

    public List<Hareket> getAllHareketler() {
        return hareketDAO.getAll();
    }

    public Hareket getHareketById(int hareketId) {
        return hareketDAO.getById(hareketId);
    }

    public void deleteHareket(int hareketId) {
        hareketDAO.delete(hareketId);
    }
}
