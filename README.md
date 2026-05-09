# Sistem-Penjualan-Buah-Organik
Sistem penjualan buah organik berbasis Java dengan penerapan JDBC, Java Collection Framework (JCF), inheritance, dan Data Mapper.

# Sistem Penjualan Buah Organik

Sistem informasi penjualan buah organik berbasis Java yang dirancang untuk mempermudah pengelolaan data buah, pelanggan, pesanan, serta promosi secara terstruktur. Sistem ini dibangun menggunakan pendekatan Object-Oriented Programming dengan penerapan JDBC, Java Collections Framework, Inheritance, ORM, Data Mapper, dan prinsip SOLID.

---

## Tim Pengembang

| Nama | Keterangan |
|------|------------|
| Paula Tambunan | Developer |
| Desnita | Developer |
| Enjel | Developer |
| Doy | Developer |

---

## Teknologi yang Digunakan

- **Java JDK 17**
- **Microsoft SQL Server**
- **JDBC** — koneksi database
- **Java Collections Framework** — pengelolaan data koleksi
- **OOP** — Inheritance, Encapsulation
- **Data Mapper & ORM** — pemetaan data antara objek Java dan tabel database
- **Prinsip SOLID** — pemisahan tanggung jawab antar class

---

## Struktur Project

```
OrganicFruitStore/
├── lib/
│   └── mssql-jdbc-13.4.0.jre11.jar
├── out/
├── sql/
│   └── schema.sql
└── src/
    ├── main/
    │   └── Main.java
    ├── model/
    │   ├── Entity.java
    │   ├── Buah.java
    │   ├── Pelanggan.java
    │   ├── Promo.java
    │   ├── Pesanan.java
    │   └── DetailPesanan.java
    ├── repository/
    │   ├── BuahRepository.java
    │   ├── BuahRepositoryImpl.java
    │   ├── PelangganRepository.java
    │   ├── PelangganRepositoryImpl.java
    │   ├── PromoRepository.java
    │   ├── PromoRepositoryImpl.java
    │   ├── PesananRepository.java
    │   ├── PesananRepositoryImpl.java
    │   └── DetailPesananRepositoryImpl.java
    ├── service/
    │   ├── BuahService.java
    │   ├── PelangganService.java
    │   ├── PromoService.java
    │   └── PesananService.java
    └── util/
        └── DatabaseConnection.java
```

---

## Cara Menjalankan Program

### 1. Persiapan Database

Jalankan script SQL berikut pada database server yang digunakan:

```sql
CREATE DATABASE OrganicFruitStore;
GO
USE OrganicFruitStore;
GO

CREATE TABLE pelanggan (
    id INT PRIMARY KEY IDENTITY(1,1),
    nama VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telepon VARCHAR(20),
    alamat TEXT
);

CREATE TABLE buah (
    id INT PRIMARY KEY IDENTITY(1,1),
    nama VARCHAR(100) NOT NULL,
    kategori VARCHAR(50),
    harga DECIMAL(10,2) NOT NULL,
    stok INT DEFAULT 0,
    deskripsi TEXT
);

CREATE TABLE promo (
    id INT PRIMARY KEY IDENTITY(1,1),
    judul VARCHAR(100) NOT NULL,
    deskripsi TEXT,
    persen_diskon DECIMAL(5,2),
    tanggal_mulai DATE,
    tanggal_selesai DATE
);

CREATE TABLE pesanan (
    id INT PRIMARY KEY IDENTITY(1,1),
    pelanggan_id INT FOREIGN KEY REFERENCES pelanggan(id),
    tanggal DATETIME DEFAULT GETDATE(),
    alamat_pengiriman TEXT,
    total DECIMAL(10,2),
    status VARCHAR(50) DEFAULT 'Menunggu'
);

CREATE TABLE detail_pesanan (
    id INT PRIMARY KEY IDENTITY(1,1),
    pesanan_id INT FOREIGN KEY REFERENCES pesanan(id),
    buah_id INT FOREIGN KEY REFERENCES buah(id),
    jumlah INT NOT NULL,
    harga_satuan DECIMAL(10,2) NOT NULL
);
```

### 2. Compile Program

Masuk ke direktori project, kemudian jalankan perintah berikut:

```
javac -cp "lib\mssql-jdbc-13.4.0.jre11.jar" -d out src\util\DatabaseConnection.java src\model\Entity.java src\model\Buah.java src\model\Pelanggan.java src\model\Promo.java src\model\Pesanan.java src\model\DetailPesanan.java src\repository\BuahRepository.java src\repository\BuahRepositoryImpl.java src\repository\PelangganRepository.java src\repository\PelangganRepositoryImpl.java src\repository\PromoRepository.java src\repository\PromoRepositoryImpl.java src\repository\PesananRepository.java src\repository\PesananRepositoryImpl.java src\repository\DetailPesananRepositoryImpl.java src\service\BuahService.java src\service\PelangganService.java src\service\PromoService.java src\service\PesananService.java src\main\Main.java
```

### 3. Jalankan Program

```
java -cp "out;lib\mssql-jdbc-13.4.0.jre11.jar" main.Main
```

---

## Fitur Sistem

### 1. Kelola Buah
Menampilkan daftar buah organik yang tersedia beserta harga dan stok. Administrator dapat menambah, memperbarui, maupun menghapus data buah.

### 2. Kelola Pelanggan
Pendaftaran dan pengelolaan data pelanggan. Setiap pelanggan diidentifikasi melalui alamat email yang unik.

### 3. Kelola Pesanan
Pelanggan dapat melakukan pemesanan buah dengan jumlah tertentu. Sistem akan memvalidasi ketersediaan stok secara otomatis dan menghitung total harga. Status pengiriman pesanan dapat diperbarui secara berkala.

### 4. Kelola Promo dan Diskon
Pengelolaan data promosi yang mencakup judul, keterangan diskon dalam persen, serta periode berlaku promo.

---

## Alur Penggunaan

```
1. Tambah data buah
         ↓
2. Daftarkan pelanggan
         ↓
3. Tambah promo (opsional)
         ↓
4. Buat pesanan
         ↓
5. Perbarui status pengiriman
```

---

## Ketentuan Input

| Field | Ketentuan |
|-------|-----------|
| Nama | Tidak boleh kosong |
| Email | Harus mengandung karakter `@` dan belum terdaftar sebelumnya |
| Harga | Harus berupa angka lebih dari 0 |
| Stok | Tidak boleh bernilai negatif |
| Persen Diskon | Nilai antara 1 hingga 100 |
| Tanggal | Format `YYYY-MM-DD`, contoh: `2026-05-10` |
| Jumlah Pesanan | Tidak boleh melebihi stok yang tersedia |

---

## Arsitektur Sistem

```
Entity (abstract)
├── Buah
├── Pelanggan
├── Promo
├── Pesanan
└── DetailPesanan
```

Setiap entitas memiliki repository sebagai lapisan akses data dan service sebagai lapisan logika bisnis, sesuai dengan prinsip Single Responsibility pada SOLID.

---

*Proyek Pemrograman Berorientasi Objek — 2026*
