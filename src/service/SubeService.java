package service;

import dao.SubeDAO;
import model.Sube;

import java.util.List;

public class SubeService {
    private final SubeDAO subeDAO;

    public SubeService() {
        this.subeDAO = new SubeDAO();
    }

    public void addSube(String subeAdi, String adres) {
        subeDAO.add(new Sube(subeAdi, adres));
    }

    public List<Sube> getAllSubeler() {
        return subeDAO.getAll();
    }

    public Sube getSubeById(int subeId) {
        return subeDAO.getById(subeId);
    }

    public void updateSube(int subeId, String subeAdi, String adres) {
        Sube sube = subeDAO.getById(subeId);
        sube.setSubeAdi(subeAdi);
        sube.setAdres(adres);
        subeDAO.update(sube);
    }

    public void deleteSube(int subeId) {
        subeDAO.delete(subeId);
    }
}
