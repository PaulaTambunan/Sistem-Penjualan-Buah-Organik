package repository;
import model.Buah;
import java.util.List;


public interface BuahRepository {
    void tambah(Buah buah);
    List<Buah> ambilSemua();
    Buah ambilById(int id);
    void update(Buah buah);
    void hapus(int id);
}