package service;

import model.Buah;
import repository.BuahRepository;
import repository.BuahRepositoryImpl;

import java.util.List;

public class BuahService {

    // SOLID: Dependency Injection
    private BuahRepository buahRepository;

    public BuahService() {
        this.buahRepository = new BuahRepositoryImpl();
    }

    public void tambahBuah(String nama, String kategori, double harga, int stok, String deskripsi) {
        if (nama == null || nama.isEmpty()) {
            System.out.println("Nama buah tidak boleh kosong!");
            return;
        }
        if (harga <= 0) {
            System.out.println("Harga harus lebih dari 0!");
            return;
        }
        if (stok < 0) {
            System.out.println("Stok tidak boleh negatif!");
            return;
        }
        Buah buah = new Buah(nama, kategori, harga, stok, deskripsi);
        buahRepository.tambah(buah);
    }

    public List<Buah> getDaftarBuah() {
        return buahRepository.ambilSemua();
    }

    public Buah getBuahById(int id) {
        return buahRepository.ambilById(id);
    }

    public void updateBuah(Buah buah) {
        buahRepository.update(buah);
    }

    public void hapusBuah(int id) {
        buahRepository.hapus(id);
    }

    // Logika bisnis: cek apakah buah tersedia
    public boolean cekKetersediaan(int id, int jumlahDiminta) {
        Buah buah = buahRepository.ambilById(id);
        if (buah == null) {
            System.out.println("Buah tidak ditemukan!");
            return false;
        }
        return buah.getStok() >= jumlahDiminta;
    }
}