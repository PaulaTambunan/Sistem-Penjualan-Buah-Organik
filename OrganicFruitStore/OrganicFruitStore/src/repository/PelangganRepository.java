package repository;

import model.Pelanggan;
import java.util.List;

public interface PelangganRepository {
    void tambah(Pelanggan pelanggan);
    List<Pelanggan> ambilSemua();
    Pelanggan ambilById(int id);
    Pelanggan ambilByEmail(String email);
    void update(Pelanggan pelanggan);
    void hapus(int id);
}