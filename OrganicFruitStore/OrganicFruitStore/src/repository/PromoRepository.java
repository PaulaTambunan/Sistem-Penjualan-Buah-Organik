package repository;

import model.Promo;
import java.util.List;

public interface PromoRepository {
    void tambah(Promo promo);
    List<Promo> ambilSemua();
    Promo ambilById(int id);
    void hapus(int id);
}