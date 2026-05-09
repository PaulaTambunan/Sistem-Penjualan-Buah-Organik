package service;

import model.*;
import repository.*;

import java.util.List;

public class PesananService {

    private PesananRepository pesananRepository;
    private DetailPesananRepositoryImpl detailRepository;
    private BuahRepository buahRepository;

    public PesananService() {
        this.pesananRepository = new PesananRepositoryImpl();
        this.detailRepository = new DetailPesananRepositoryImpl();
        this.buahRepository = new BuahRepositoryImpl();
    }

    public boolean buatPesanan(int pelangganId, String alamat, List<int[]> itemList) {
        // itemList: setiap item adalah {buahId, jumlah}
        double total = 0;

        // Hitung total
        for (int[] item : itemList) {
            Buah buah = buahRepository.ambilById(item[0]);
            if (buah == null) {
                System.out.println("Buah ID " + item[0] + " tidak ditemukan!");
                return false;
            }
            if (buah.getStok() < item[1]) {
                System.out.println("Stok " + buah.getNama() + " tidak cukup!");
                return false;
            }
            total += buah.getHarga() * item[1];
        }

        // Simpan pesanan
        Pesanan pesanan = new Pesanan(pelangganId, alamat);
        pesanan.setTotal(total);
        int pesananId = pesananRepository.tambah(pesanan);

        if (pesananId == -1) {
            System.out.println("Gagal membuat pesanan!");
            return false;
        }

        // Simpan detail & update stok
        for (int[] item : itemList) {
            Buah buah = buahRepository.ambilById(item[0]);
            DetailPesanan detail = new DetailPesanan(pesananId, item[0], item[1], buah.getHarga());
            detail.setNamaBuah(buah.getNama());
            detailRepository.tambah(detail);

            // Update stok
            buah.setStok(buah.getStok() - item[1]);
            buahRepository.update(buah);
        }

        System.out.println("Pesanan berhasil dibuat! ID: " + pesananId);
        System.out.println("Total: Rp" + total);
        return true;
    }

    public List<Pesanan> getDaftarPesanan() {
        return pesananRepository.ambilSemua();
    }

    public List<Pesanan> getPesananByPelanggan(int pelangganId) {
        return pesananRepository.ambilByPelangganId(pelangganId);
    }

    public List<DetailPesanan> getDetailPesanan(int pesananId) {
        return detailRepository.ambilByPesananId(pesananId);
    }

    public void updateStatusPesanan(int id, String status) {
        pesananRepository.updateStatus(id, status);
    }
}