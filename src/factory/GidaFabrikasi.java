package factory;

import model.EtUrunu;
import model.PaketliGida;
import model.Sebze;
import model.SutUrunu;
import model.Urun;

public class GidaFabrikasi {

    private GidaFabrikasi() {}

    public interface GidaUrunFactory {
        Urun createUrun();
    }

    public static final class EtUrunuFactory implements GidaUrunFactory {
        @Override
        public Urun createUrun() {
            return new EtUrunu();
        }
    }

    public static final class SutUrunuFactory implements GidaUrunFactory {
        @Override
        public Urun createUrun() {
            return new SutUrunu();
        }
    }

    public static final class SebzeUrunuFactory implements GidaUrunFactory {
        @Override
        public Urun createUrun() {
            return new Sebze();
        }
    }

    public static final class PaketliGidaFactory implements GidaUrunFactory {
        @Override
        public Urun createUrun() {
            return new PaketliGida();
        }
    }

    public static GidaUrunFactory getFactory(String tur) {
        if (tur == null) {
            throw new IllegalArgumentException("tur is null");
        }

        switch (tur.toUpperCase()) {
            case "ET":
                return new EtUrunuFactory();
            case "SUT":
                return new SutUrunuFactory();
            case "SEBZE":
                return new SebzeUrunuFactory();
            case "PAKETLI":
                return new PaketliGidaFactory();
            default:
                throw new IllegalArgumentException("Unsupported tur: " + tur);
        }
    }

    public static Urun gidaOlustur(String tur) {
        return getFactory(tur).createUrun();
    }
}
