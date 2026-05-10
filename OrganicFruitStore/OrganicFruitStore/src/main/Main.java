package main;
import model.*;
import service.*;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static BuahService buahService = new BuahService();
    static PelangganService pelangganService = new PelangganService();
    static PesananService pesananService = new PesananService();
    static PromoService promoService = new PromoService();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  SELAMAT DATANG DI TOKO BUAH ORGANIK  ");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            tampilMenuUtama();
            int pilihan = inputAngka();
            switch (pilihan) {
                case 1 -> menuBuah();
                case 2 -> menuPelanggan();
                case 3 -> menuPesanan();
                case 4 -> menuPromo();
                case 0 -> {
                    System.out.println("Terima kasih! Sampai jumpa!");
                    running = false;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // =================== MENU UTAMA ===================
    static void tampilMenuUtama() {
        System.out.println("\n========== MENU UTAMA ==========");
        System.out.println("1. Kelola Buah");
        System.out.println("2. Kelola Pelanggan");
        System.out.println("3. Kelola Pesanan");
        System.out.println("4. Kelola Promo & Diskon");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    // =================== MENU BUAH ===================
    static void menuBuah() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU BUAH =====");
            System.out.println("1. Lihat Daftar Buah");
            System.out.println("2. Tambah Buah");
            System.out.println("3. Update Buah");
            System.out.println("4. Hapus Buah");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilihan = inputAngka();
            switch (pilihan) {
                case 1 -> tampilDaftarBuah();
                case 2 -> tambahBuah();
                case 3 -> updateBuah();
                case 4 -> hapusBuah();
                case 0 -> back = true;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void tampilDaftarBuah() {
        System.out.println("\n===== DAFTAR BUAH ORGANIK =====");
        List<Buah> list = buahService.getDaftarBuah();
        if (list.isEmpty()) {
            System.out.println("Belum ada buah tersedia.");
        } else {
            for (Buah b : list) System.out.println(b);
        }
    }

    static void tambahBuah() {
        System.out.println("\n===== TAMBAH BUAH =====");
        System.out.print("Nama buah: ");
        String nama = scanner.nextLine();
        System.out.print("Kategori: ");
        String kategori = scanner.nextLine();
        System.out.print("Harga: ");
        double harga = Double.parseDouble(scanner.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(scanner.nextLine());
        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();
        buahService.tambahBuah(nama, kategori, harga, stok, deskripsi);
    }

    static void updateBuah() {
        tampilDaftarBuah();
        System.out.print("\nMasukkan ID buah yang akan diupdate: ");
        int id = inputAngka();
        Buah buah = buahService.getBuahById(id);
        if (buah == null) {
            System.out.println("Buah tidak ditemukan!");
            return;
        }
        System.out.print("Nama baru (" + buah.getNama() + "): ");
        String nama = scanner.nextLine();
        System.out.print("Kategori baru (" + buah.getKategori() + "): ");
        String kategori = scanner.nextLine();
        System.out.print("Harga baru (" + buah.getHarga() + "): ");
        double harga = Double.parseDouble(scanner.nextLine());
        System.out.print("Stok baru (" + buah.getStok() + "): ");
        int stok = Integer.parseInt(scanner.nextLine());
        System.out.print("Deskripsi baru: ");
        String deskripsi = scanner.nextLine();
        buah.setNama(nama);
        buah.setKategori(kategori);
        buah.setHarga(harga);
        buah.setStok(stok);
        buah.setDeskripsi(deskripsi);
        buahService.updateBuah(buah);
    }

    static void hapusBuah() {
        tampilDaftarBuah();
        System.out.print("\nMasukkan ID buah yang akan dihapus: ");
        int id = inputAngka();
        buahService.hapusBuah(id);
    }

    // =================== MENU PELANGGAN ===================
    static void menuPelanggan() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU PELANGGAN =====");
            System.out.println("1. Lihat Daftar Pelanggan");
            System.out.println("2. Daftar Pelanggan Baru");
            System.out.println("3. Hapus Pelanggan");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilihan = inputAngka();
            switch (pilihan) {
                case 1 -> tampilDaftarPelanggan();
                case 2 -> daftarPelanggan();
                case 3 -> hapusPelanggan();
                case 0 -> back = true;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void tampilDaftarPelanggan() {
        System.out.println("\n===== DAFTAR PELANGGAN =====");
        List<Pelanggan> list = pelangganService.getDaftarPelanggan();
        if (list.isEmpty()) {
            System.out.println("Belum ada pelanggan terdaftar.");
        } else {
            for (Pelanggan p : list) System.out.println(p);
        }
    }

    static void daftarPelanggan() {
        System.out.println("\n===== DAFTAR PELANGGAN BARU =====");
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telepon: ");
        String telepon = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();
        pelangganService.daftarPelanggan(nama, email, telepon, alamat);
    }

    static void hapusPelanggan() {
        tampilDaftarPelanggan();
        System.out.print("\nMasukkan ID pelanggan yang akan dihapus: ");
        int id = inputAngka();
        pelangganService.hapusPelanggan(id);
    }

    // =================== MENU PESANAN ===================
    static void menuPesanan() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU PESANAN =====");
            System.out.println("1. Buat Pesanan Baru");
            System.out.println("2. Lihat Semua Pesanan");
            System.out.println("3. Lihat Pesanan Per Pelanggan");
            System.out.println("4. Lihat Detail Pesanan");
            System.out.println("5. Update Status Pesanan");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilihan = inputAngka();
            switch (pilihan) {
                case 1 -> buatPesanan();
                case 2 -> tampilSemuaPesanan();
                case 3 -> tampilPesananPelanggan();
                case 4 -> tampilDetailPesanan();
                case 5 -> updateStatusPesanan();
                case 0 -> back = true;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void buatPesanan() {
        System.out.println("\n===== BUAT PESANAN BARU =====");
        tampilDaftarPelanggan();
        System.out.print("Masukkan ID pelanggan: ");
        int pelangganId = inputAngka();

        System.out.print("Alamat pengiriman: ");
        String alamat = scanner.nextLine();

        List<int[]> items = new ArrayList<>();
        boolean addMore = true;
        while (addMore) {
            tampilDaftarBuah();
            System.out.print("Masukkan ID buah: ");
            int buahId = inputAngka();
            System.out.print("Jumlah: ");
            int jumlah = inputAngka();
            items.add(new int[]{buahId, jumlah});
            System.out.print("Tambah buah lagi? (y/n): ");
            String lagi = scanner.nextLine();
            addMore = lagi.equalsIgnoreCase("y");
        }
        pesananService.buatPesanan(pelangganId, alamat, items);
    }

    static void tampilSemuaPesanan() {
        System.out.println("\n===== SEMUA PESANAN =====");
        List<Pesanan> list = pesananService.getDaftarPesanan();
        if (list.isEmpty()) {
            System.out.println("Belum ada pesanan.");
        } else {
            for (Pesanan p : list) System.out.println(p);
        }
    }

    static void tampilPesananPelanggan() {
        tampilDaftarPelanggan();
        System.out.print("Masukkan ID pelanggan: ");
        int id = inputAngka();
        List<Pesanan> list = pesananService.getPesananByPelanggan(id);
        if (list.isEmpty()) {
            System.out.println("Pelanggan belum memiliki pesanan.");
        } else {
            for (Pesanan p : list) System.out.println(p);
        }
    }

    static void tampilDetailPesanan() {
        tampilSemuaPesanan();
        System.out.print("Masukkan ID pesanan: ");
        int id = inputAngka();
        List<DetailPesanan> list = pesananService.getDetailPesanan(id);
        System.out.println("\n===== DETAIL PESANAN #" + id + " =====");
        if (list.isEmpty()) {
            System.out.println("Detail tidak ditemukan.");
        } else {
            for (DetailPesanan d : list) System.out.println(d);
        }
    }

    static void updateStatusPesanan() {
        tampilSemuaPesanan();
        System.out.print("Masukkan ID pesanan: ");
        int id = inputAngka();
        System.out.println("Status: 1.Menunggu 2.Diproses 3.Dikirim 4.Selesai");
        System.out.print("Pilih status: ");
        int pilihan = inputAngka();
        String status = switch (pilihan) {
            case 1 -> "Menunggu";
            case 2 -> "Diproses";
            case 3 -> "Dikirim";
            case 4 -> "Selesai";
            default -> "Menunggu";
        };
        pesananService.updateStatusPesanan(id, status);
    }

    // =================== MENU PROMO ===================
    static void menuPromo() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU PROMO & DISKON =====");
            System.out.println("1. Lihat Daftar Promo");
            System.out.println("2. Tambah Promo");
            System.out.println("3. Hapus Promo");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilihan = inputAngka();
            switch (pilihan) {
                case 1 -> tampilDaftarPromo();
                case 2 -> tambahPromo();
                case 3 -> hapusPromo();
                case 0 -> back = true;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void tampilDaftarPromo() {
        System.out.println("\n===== PROMO & DISKON TERSEDIA =====");
        List<Promo> list = promoService.getDaftarPromo();
        if (list.isEmpty()) {
            System.out.println("Belum ada promo tersedia.");
        } else {
            for (Promo p : list) System.out.println(p);
        }
    }

    static void tambahPromo() {
        System.out.println("\n===== TAMBAH PROMO =====");
        System.out.print("Judul promo: ");
        String judul = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Persen diskon (1-100): ");
        double persen = Double.parseDouble(scanner.nextLine());
        System.out.print("Tanggal mulai (YYYY-MM-DD): ");
        String mulai = scanner.nextLine();
        System.out.print("Tanggal selesai (YYYY-MM-DD): ");
        String selesai = scanner.nextLine();
        promoService.tambahPromo(judul, deskripsi, persen, mulai, selesai);
    }

    static void hapusPromo() {
        tampilDaftarPromo();
        System.out.print("\nMasukkan ID promo yang akan dihapus: ");
        int id = inputAngka();
        promoService.hapusPromo(id);
    }

    // =================== HELPER ===================
    static int inputAngka() {
        try {
            int angka = Integer.parseInt(scanner.nextLine());
            return angka;
        } catch (NumberFormatException e) {
            System.out.println("Input harus angka!");
            return -1;
        }
    }
}