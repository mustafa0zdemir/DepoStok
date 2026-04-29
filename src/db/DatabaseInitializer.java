package db;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void init() {

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Kategori
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS kategori (
                    kategori_id SERIAL PRIMARY KEY,
                    kategori_adi VARCHAR(100) NOT NULL
                )
            """);

            // Ürün
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS urun (
                    urun_id SERIAL PRIMARY KEY,
                    urun_adi VARCHAR(100) NOT NULL,
                    birim_fiyat DOUBLE PRECISION,
                    kategori_id INT,
                    FOREIGN KEY (kategori_id) REFERENCES kategori(kategori_id)
                )
            """);

            // Depo
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS depo (
                    depo_id SERIAL PRIMARY KEY,
                    depo_adi VARCHAR(100),
                    adres TEXT
                )
            """);

            // Parti
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS parti (
                    parti_id SERIAL PRIMARY KEY,
                    urun_id INT,
                    depo_id INT,
                    kalan_miktar INT,
                    son_kullanma_tarihi DATE,
                    FOREIGN KEY (urun_id) REFERENCES urun(urun_id),
                    FOREIGN KEY (depo_id) REFERENCES depo(depo_id)
                )
            """);

            // Personel
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS personel (
                    personel_id SERIAL PRIMARY KEY,
                    ad VARCHAR(50),
                    soyad VARCHAR(50),
                    telefon_no VARCHAR(20)
                )
            """);

            // Şube
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS sube (
                    sube_id SERIAL PRIMARY KEY,
                    sube_adi VARCHAR(100),
                    adres TEXT
                )
            """);

            // Hareket
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS hareket (
                    hareket_id SERIAL PRIMARY KEY,
                    parti_id INT,
                    personel_id INT,
                    miktar INT,
                    tip VARCHAR(20),
                    tarih TIMESTAMP,
                    FOREIGN KEY (parti_id) REFERENCES parti(parti_id),
                    FOREIGN KEY (personel_id) REFERENCES personel(personel_id)
                )
            """);

            System.out.println("tablolar hazır");

        } catch (Exception e) {
            throw new RuntimeException("DB init hatası", e);
        }
    }
}