public class Main {
    public static void main(String[] args) {
        dao.UrunDAO urunDAO = new dao.UrunDAO();
        model.Urun urun = factory.GidaFabrikasi.gidaOlustur("ET");

        urun.setUrunAdi("Dana Kiyma");
        urun.setBirimFiyat(189.90);
        urun.setKategoriId(1);

        urunDAO.add(urun);

        System.out.println("Urun eklendi. Toplam urun sayisi: " + urunDAO.getAll().size());
    }
}