package service;

import model.Promo;
import repository.PromoRepository;
import repository.PromoRepositoryImpl;

import java.util.List;

public class PromoService {

    private PromoRepository promoRepository;

    public PromoService() {
        this.promoRepository = new PromoRepositoryImpl();
    }

    public void tambahPromo(String judul, String deskripsi, double persen, String mulai, String selesai) {
        if (judul == null || judul.isEmpty()) {
            System.out.println("Judul promo tidak boleh kosong!");
            return;
        }
        if (persen <= 0 || persen > 100) {
            System.out.println("Persen diskon harus antara 1-100!");
            return;
        }
        Promo promo = new Promo(judul, deskripsi, persen, mulai, selesai);
        promoRepository.tambah(promo);
    }

    public List<Promo> getDaftarPromo() {
        return promoRepository.ambilSemua();
    }

    public Promo getPromoById(int id) {
        return promoRepository.ambilById(id);
    }

    public void hapusPromo(int id) {
        promoRepository.hapus(id);
    }

    // Hitung harga setelah diskon
    public double hitungHargaDiskon(double hargaAsli, int promoId) {
        Promo promo = promoRepository.ambilById(promoId);
        if (promo == null) return hargaAsli;
        double diskon = hargaAsli * (promo.getPersenDiskon() / 100);
        return hargaAsli - diskon;
    }
}