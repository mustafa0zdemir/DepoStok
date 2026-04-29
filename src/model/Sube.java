package model;

public class Sube {

    private int subeId;
    private String subeAdi;
    private String adres;

    public Sube() {}

    public Sube(int subeId, String subeAdi, String adres) {
        this.subeId = subeId;
        this.subeAdi = subeAdi;
        this.adres = adres;
    }

    public Sube(String subeAdi, String adres) {
        this.subeAdi = subeAdi;
        this.adres = adres;
    }

    public int getSubeId() {
        return subeId;
    }

    public void setSubeId(int subeId) {
        this.subeId = subeId;
    }

    public String getSubeAdi() {
        return subeAdi;
    }

    public void setSubeAdi(String subeAdi) {
        this.subeAdi = subeAdi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Sube{" +
                "subeId=" + subeId +
                ", subeAdi='" + subeAdi + '\'' +
                ", adres='" + adres + '\'' +
                '}';
    }
}