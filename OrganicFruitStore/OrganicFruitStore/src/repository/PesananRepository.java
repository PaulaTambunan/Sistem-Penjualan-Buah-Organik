package repository;

import model.Pesanan;
import java.util.List;

public interface PesananRepository {
    int tambah(Pesanan pesanan);
    List<Pesanan> ambilSemua();
    Pesanan ambilById(int id);
    List<Pesanan> ambilByPelangganId(int pelangganId);
    void updateStatus(int id, String status);
}