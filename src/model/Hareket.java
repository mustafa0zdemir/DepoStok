package model;

import java.util.Date;

public class Hareket {

    private int hareketId;
    private Date tarih;

    private int partiId;
    private int personelId;
    private int subeId;
    private int miktar;
    private String tip; // giriş / çıkış

    public Hareket() {}

    public Hareket(int hareketId, int partiId, int personelId, int subeId, int miktar, String tip, Date tarih) {
        this.hareketId = hareketId;
        this.partiId = partiId;
        this.personelId = personelId;
        this.subeId = subeId;
        this.miktar = miktar;
        this.tip = tip;
        this.tarih = tarih;
    }

    public Hareket(int partiId, int personelId, int subeId, int miktar, String tip, Date tarih) {
        this.partiId = partiId;
        this.personelId = personelId;
        this.subeId = subeId;
        this.miktar = miktar;
        this.tip = tip;
        this.tarih = tarih;
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

    public int getPartiId() {
        return partiId;
    }

    public void setPartiId(int partiId) {
        this.partiId = partiId;
    }

    public int getSubeId() {
        return subeId;
    }

    public void setSubeId(int subeId) {
        this.subeId = subeId;
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
                ", partiId=" + partiId +
                ", personelId=" + personelId +
                ", subeId=" + subeId +
                ", miktar=" + miktar +
                ", tip='" + tip + '\'' +
                ", tarih=" + tarih +
                '}';
    }
}