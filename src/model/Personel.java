package model;

public class Personel {

    private int personelId;
    private String ad;
    private String soyad;
    private String telefonNo;

    public Personel() {}

    public Personel(int personelId, String ad, String soyad, String telefonNo) {
        this.personelId = personelId;
        this.ad = ad;
        this.soyad = soyad;
        this.telefonNo = telefonNo;
    }

    public Personel(String ad, String soyad, String telefonNo) {
        this.ad = ad;
        this.soyad = soyad;
        this.telefonNo = telefonNo;
    }

    public int getPersonelId() {
        return personelId;
    }

    public void setPersonelId(int personelId) {
        this.personelId = personelId;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelefonNo() {
        return telefonNo;
    }

    public void setTelefonNo(String telefonNo) {
        this.telefonNo = telefonNo;
    }

    public String getAdSoyad() {
        return ad + " " + soyad;
    }
}