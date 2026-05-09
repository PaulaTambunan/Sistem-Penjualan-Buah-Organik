package service;

import model.Pelanggan;
import repository.PelangganRepository;
import repository.PelangganRepositoryImpl;

import java.util.List;

public class PelangganService {

    private PelangganRepository pelangganRepository;

    public PelangganService() {
        this.pelangganRepository = new PelangganRepositoryImpl();
    }

    public void daftarPelanggan(String nama, String email, String telepon, String alamat) {
        if (nama == null || nama.isEmpty()) {
            System.out.println("Nama tidak boleh kosong!");
            return;
        }
        if (email == null || !email.contains("@")) {
            System.out.println("Email tidak valid!");
            return;
        }
        // Cek email sudah terdaftar
        if (pelangganRepository.ambilByEmail(email) != null) {
            System.out.println("Email sudah terdaftar!");
            return;
        }
        Pelanggan p = new Pelanggan(nama, email, telepon, alamat);
        pelangganRepository.tambah(p);
    }

    public List<Pelanggan> getDaftarPelanggan() {
        return pelangganRepository.ambilSemua();
    }

    public Pelanggan getPelangganById(int id) {
        return pelangganRepository.ambilById(id);
    }

    public Pelanggan getPelangganByEmail(String email) {
        return pelangganRepository.ambilByEmail(email);
    }

    public void updatePelanggan(Pelanggan pelanggan) {
        pelangganRepository.update(pelanggan);
    }

    public void hapusPelanggan(int id) {
        pelangganRepository.hapus(id);
    }
}