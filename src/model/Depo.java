package model;

public class Depo {

    private int depoId;
    private String depoAdi;

    public Depo() {}

    public Depo(int depoId, String depoAdi) {
        this.depoId = depoId;
        this.depoAdi = depoAdi;
    }

    public Depo(String depoAdi) {
        this.depoAdi = depoAdi;
    }

    public int getDepoId() {
        return depoId;
    }

    public void setDepoId(int depoId) {
        this.depoId = depoId;
    }

    public String getDepoAdi() {
        return depoAdi;
    }

    public void setDepoAdi(String depoAdi) {
        this.depoAdi = depoAdi;
    }

    @Override
    public String toString() {
        return "Depo{" +
                "depoId=" + depoId +
                ", depoAdi='" + depoAdi + '\'' +
                '}';
    }
}