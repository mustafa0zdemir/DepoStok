package model;

public class Urun {

    private int urunId;
    private String urunAdi;
    private double birimFiyat;

    private int kategoriId; // FK

    public Urun() {}

    public Urun(int urunId, String urunAdi, double birimFiyat, int kategoriId) {
        this.urunId = urunId;
        this.urunAdi = urunAdi;
        this.birimFiyat = birimFiyat;
        this.kategoriId = kategoriId;
    }

    public Urun(String urunAdi, double birimFiyat, int kategoriId) {
        this.urunAdi = urunAdi;
        this.birimFiyat = birimFiyat;
        this.kategoriId = kategoriId;
    }

    public int getUrunId() {
        return urunId;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public double getBirimFiyat() {
        return birimFiyat;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public void setUrunId(int urunId) {
        this.urunId = urunId;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public void setBirimFiyat(double birimFiyat) {
        this.birimFiyat = birimFiyat;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }
}