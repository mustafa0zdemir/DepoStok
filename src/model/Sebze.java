package model;

public class Sebze extends Urun {

    public Sebze() {
        super();
    }

    public Sebze(int urunId, String urunAdi, double birimFiyat, int kategoriId) {
        super(urunId, urunAdi, birimFiyat, kategoriId);
    }

    public Sebze(String urunAdi, double birimFiyat, int kategoriId) {
        super(urunAdi, birimFiyat, kategoriId);
    }
}
