package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String URL = "jdbc:postgresql://localhost:5433/depo_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static void init() {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
                    FOREIGN KEY (kategori_id) REFERENCES kategori(kategori_id) ON DELETE CASCADE
                )
            """);

            // Eski kurulumlarda urun->kategori FK'sini ON DELETE CASCADE olacak sekilde guncelle
            stmt.execute("""
                DO $$
                BEGIN
                    IF EXISTS (
                        SELECT 1
                        FROM pg_constraint
                        WHERE conname = 'urun_kategori_id_fkey'
                    ) THEN
                        ALTER TABLE urun DROP CONSTRAINT urun_kategori_id_fkey;
                    END IF;

                    ALTER TABLE urun
                    ADD CONSTRAINT urun_kategori_id_fkey
                    FOREIGN KEY (kategori_id) REFERENCES kategori(kategori_id) ON DELETE CASCADE;
                END $$;
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
                    sube_id INT,
                    miktar INT,
                    tip VARCHAR(20),
                    tarih TIMESTAMP,
                    FOREIGN KEY (parti_id) REFERENCES parti(parti_id),
                    FOREIGN KEY (personel_id) REFERENCES personel(personel_id),
                    FOREIGN KEY (sube_id) REFERENCES sube(sube_id)
                )
            """);

            // Eski kurulumlarda hareket tablosunda sube_id yoksa ekle
            stmt.execute("""
                ALTER TABLE hareket
                ADD COLUMN IF NOT EXISTS sube_id INT
            """);

            // Eski kurulumlarda sube FK yoksa ekle
            stmt.execute("""
                DO $$
                BEGIN
                    IF NOT EXISTS (
                        SELECT 1
                        FROM pg_constraint
                        WHERE conname = 'hareket_sube_id_fkey'
                    ) THEN
                        ALTER TABLE hareket
                        ADD CONSTRAINT hareket_sube_id_fkey
                        FOREIGN KEY (sube_id) REFERENCES sube(sube_id);
                    END IF;
                END $$;
            """);

            System.out.println("tablolar hazır");

        } catch (Exception e) {
            throw new RuntimeException("DB init hatası", e);
        }
    }

    public static void clearAllData() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.execute("TRUNCATE TABLE hareket, parti, urun, kategori, sube, personel, depo RESTART IDENTITY CASCADE");
            System.out.println("tum veriler temizlendi");

        } catch (Exception e) {
            throw new RuntimeException("DB temizleme hatasi", e);
        }
    }
}
