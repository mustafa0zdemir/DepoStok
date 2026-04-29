package model;

public class SutUrunu extends Urun {

    public SutUrunu() {
        super();
    }

    public SutUrunu(int urunId, String urunAdi, double birimFiyat, int kategoriId) {
        super(urunId, urunAdi, birimFiyat, kategoriId);
    }

    public SutUrunu(String urunAdi, double birimFiyat, int kategoriId) {
        super(urunAdi, birimFiyat, kategoriId);
    }
}
