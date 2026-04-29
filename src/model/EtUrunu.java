package model;

public class EtUrunu extends Urun {

    public EtUrunu() {
        super();
    }

    public EtUrunu(int urunId, String urunAdi, double birimFiyat, int kategoriId) {
        super(urunId, urunAdi, birimFiyat, kategoriId);
    }

    public EtUrunu(String urunAdi, double birimFiyat, int kategoriId) {
        super(urunAdi, birimFiyat, kategoriId);
    }
}
