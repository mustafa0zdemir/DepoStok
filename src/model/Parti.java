package model;

import java.util.Date;

public class Parti {

    private int partiId;
    private int urunId;  // FK
    private int depoId;  // FK

    private int toplamMiktar;
    private int kalanMiktar;

    private Date sonKullanmaTarihi;

    public Parti() {}

    public Parti(int partiId, int urunId, int depoId, int kalanMiktar, Date sonKullanmaTarihi) {
        this.partiId = partiId;
        this.urunId = urunId;
        this.depoId = depoId;
        this.toplamMiktar = kalanMiktar;
        this.kalanMiktar = kalanMiktar;
        this.sonKullanmaTarihi = sonKullanmaTarihi;
    }

    public Parti(int urunId, int depoId, int toplamMiktar, Date sonKullanmaTarihi) {
        this.urunId = urunId;
        this.depoId = depoId;
        this.toplamMiktar = toplamMiktar;
        this.kalanMiktar = toplamMiktar;
        this.sonKullanmaTarihi = sonKullanmaTarihi;
    }

    public int getPartiId() {
        return partiId;
    }

    public int getUrunId() {
        return urunId;
    }

    public int getDepoId() {
        return depoId;
    }

    public int getToplamMiktar() {
        return toplamMiktar;
    }

    public int getKalanMiktar() {
        return kalanMiktar;
    }

    public Date getSonKullanmaTarihi() {
        return sonKullanmaTarihi;
    }

    public void setPartiId(int partiId) {
        this.partiId = partiId;
    }

    public void setUrunId(int urunId) {
        this.urunId = urunId;
    }

    public void setDepoId(int depoId) {
        this.depoId = depoId;
    }

    public void setToplamMiktar(int toplamMiktar) {
        this.toplamMiktar = toplamMiktar;
    }

    public void setKalanMiktar(int kalanMiktar) {
        this.kalanMiktar = kalanMiktar;
    }

    public void setSonKullanmaTarihi(Date sonKullanmaTarihi) {
        this.sonKullanmaTarihi = sonKullanmaTarihi;
    }

    @Override
    public String toString() {
        return "Parti{" +
                "partiId=" + partiId +
                ", urunId=" + urunId +
                ", depoId=" + depoId +
                ", toplamMiktar=" + toplamMiktar +
                ", kalanMiktar=" + kalanMiktar +
                ", sonKullanmaTarihi=" + sonKullanmaTarihi +
                '}';
    }
}