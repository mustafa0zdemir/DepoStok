package model;

import java.util.Date;

public class Parti {

    private int partiId;
    private int urunId;  // FK

    private int toplamMiktar;
    private int kalanMiktar;

    private Date sonKullanmaTarihi;

    public Parti() {}

    public Parti(int partiId, int urunId, int toplamMiktar, int kalanMiktar, Date sonKullanmaTarihi) {
        this.partiId = partiId;
        this.urunId = urunId;
        this.toplamMiktar = toplamMiktar;
        this.kalanMiktar = kalanMiktar;
        this.sonKullanmaTarihi = sonKullanmaTarihi;
    }

    public Parti(int urunId, int toplamMiktar, Date sonKullanmaTarihi) {
        this.urunId = urunId;
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
                ", toplamMiktar=" + toplamMiktar +
                ", kalanMiktar=" + kalanMiktar +
                ", sonKullanmaTarihi=" + sonKullanmaTarihi +
                '}';
    }
}