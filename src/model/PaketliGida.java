package model;

public class PaketliGida extends Urun {

    public PaketliGida() {
        super();
    }

    public PaketliGida(int urunId, String urunAdi, double birimFiyat, int kategoriId) {
        super(urunId, urunAdi, birimFiyat, kategoriId);
    }

    public PaketliGida(String urunAdi, double birimFiyat, int kategoriId) {
        super(urunAdi, birimFiyat, kategoriId);
    }
}
