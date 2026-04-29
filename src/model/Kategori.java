package model;

public class Kategori {
    private int kategoriId;
    private String kategoriAdi;

    public Kategori(){}

    public Kategori(int kategoriId, String kategoriAdi){
        this.kategoriId = kategoriId;
        this.kategoriAdi = kategoriAdi;
    }
    public Kategori(String kategoriAdi){
        this.kategoriAdi = kategoriAdi;
    }

    public int getKategoriId(){
        return kategoriId;
    }
    public String getKategoriAdi(){
        return kategoriAdi;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }
    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdi = kategoriAdi;
    }

    @Override
    public String toString() {
        return "Kategori{" +
                "kategoriId=" + kategoriId +
                ", kategoriAdi='" + kategoriAdi + '\'' +
                '}';
    }
}
