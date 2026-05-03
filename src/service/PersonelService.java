package service;

import dao.PersonelDAO;
import model.Personel;

import java.util.List;

public class PersonelService {
    private final PersonelDAO personelDAO;

    public PersonelService() {
        this.personelDAO = new PersonelDAO();
    }

    public void addPersonel(String ad, String soyad, String telefonNo) {
        Personel personel = new Personel(ad, soyad, telefonNo);
        personelDAO.add(personel);
    }

    public List<Personel> getAllPersoneller() {
        return personelDAO.getAll();
    }

    public Personel getPersonelById(int personelId) {
        return personelDAO.getById(personelId);
    }

    public void updatePersonel(int personelId, String ad, String soyad, String telefonNo) {
        Personel mevcut = personelDAO.getById(personelId);
        mevcut.setAd(ad);
        mevcut.setSoyad(soyad);
        mevcut.setTelefonNo(telefonNo);
        personelDAO.update(mevcut);
    }

    public void deletePersonel(int personelId) {
        personelDAO.delete(personelId);
    }
}
