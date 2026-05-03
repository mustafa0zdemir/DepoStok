package service;

import dao.DepoDAO;
import model.Depo;

import java.util.List;

public class DepoService {
    private final DepoDAO depoDAO;

    public DepoService() {
        this.depoDAO = new DepoDAO();
    }

    public void addDepo(String depoAdi) {
        depoDAO.add(new Depo(depoAdi));
    }

    public List<Depo> getAllDepolar() {
        return depoDAO.getAll();
    }

    public Depo getDepoById(int depoId) {
        return depoDAO.getById(depoId);
    }

    public void updateDepo(int depoId, String depoAdi) {
        Depo depo = depoDAO.getById(depoId);
        depo.setDepoAdi(depoAdi);
        depoDAO.update(depo);
    }

    public void deleteDepo(int depoId) {
        depoDAO.delete(depoId);
    }
}
