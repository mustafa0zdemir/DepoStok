package factory;

import model.EtUrunu;
import model.PaketliGida;
import model.Sebze;
import model.SutUrunu;
import model.Urun;

public class GidaFabrikasi {

    private GidaFabrikasi() {}

    public static Urun gidaOlustur(String tur) {
        if (tur == null) {
            throw new IllegalArgumentException("tur is null");
        }

        switch (tur.toUpperCase()) {
            case "ET":
                return new EtUrunu();
            case "SUT":
                return new SutUrunu();
            case "SEBZE":
                return new Sebze();
            case "PAKETLI":
                return new PaketliGida();
            default:
                throw new IllegalArgumentException("Unsupported tur: " + tur);
        }
    }
}
