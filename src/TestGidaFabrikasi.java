import dao.UrunDAO;
import factory.GidaFabrikasi;
import model.EtUrunu;
import model.Urun;

import java.util.List;

public class TestGidaFabrikasi {

    public static void main(String[] args) {
        testFactoryCreatesCorrectType();
        testDatabaseAddIncreasesCount();
        System.out.println("All tests passed.");
    }

    private static void testFactoryCreatesCorrectType() {
        Urun urun = GidaFabrikasi.gidaOlustur("ET");
        if (!(urun instanceof EtUrunu)) {
            throw new AssertionError("Factory did not create EtUrunu for ET");
        }
    }

    private static void testDatabaseAddIncreasesCount() {
        UrunDAO urunDAO = new UrunDAO();
        List<Urun> before = urunDAO.getAll();
        int beforeCount = before.size();

        Urun urun = GidaFabrikasi.gidaOlustur("ET");
        urun.setUrunAdi("Test Uru" + System.currentTimeMillis());
        urun.setBirimFiyat(10.5);
        urun.setKategoriId(1);

        urunDAO.add(urun);

        List<Urun> after = urunDAO.getAll();
        int afterCount = after.size();

        if (afterCount <= beforeCount) {
            throw new AssertionError("Database count did not increase after add");
        }
    }
}
