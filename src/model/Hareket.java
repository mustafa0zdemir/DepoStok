package model;

import java.util.Date;

public class Hareket {

    private int hareketId;
    private Date tarih;

    private int personelId;
    private int depoId;
    private int miktar;
    private String tip; // giriş / çıkış

    public Hareket() {}

    public Hareket(int hareketId, Date tarih, int personelId, int depoId, int miktar, String tip) {
        this.hareketId = hareketId;
        this.tarih = tarih;
        this.personelId = personelId;
        this.depoId = depoId;
        this.miktar = miktar;
        this.tip = tip;
    }

    public Hareket(Date tarih, int personelId, int depoId, int miktar, String tip) {
        this.tarih = tarih;
        this.personelId = personelId;
        this.depoId = depoId;
        this.miktar = miktar;
        this.tip = tip;
    }

    public int getHareketId() {
        return hareketId;
    }

    public void setHareketId(int hareketId) {
        this.hareketId = hareketId;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public int getPersonelId() {
        return personelId;
    }

    public void setPersonelId(int personelId) {
        this.personelId = personelId;
    }

    public int getDepoId() {
        return depoId;
    }

    public void setDepoId(int depoId) {
        this.depoId = depoId;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Hareket{" +
                "hareketId=" + hareketId +
                ", tarih=" + tarih +
                ", personelId=" + personelId +
                ", depoId=" + depoId +
                ", miktar=" + miktar +
                ", tip='" + tip + '\'' +
                '}';
    }
}