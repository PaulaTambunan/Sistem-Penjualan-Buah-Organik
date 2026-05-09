package repository;

import model.Promo;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromoRepositoryImpl implements PromoRepository {

    private Promo mapToPromo(ResultSet rs) throws SQLException {
        Promo promo = new Promo();
        promo.setId(rs.getInt("id"));
        promo.setJudul(rs.getString("judul"));
        promo.setDeskripsi(rs.getString("deskripsi"));
        promo.setPersenDiskon(rs.getDouble("persen_diskon"));
        promo.setTanggalMulai(rs.getString("tanggal_mulai"));
        promo.setTanggalSelesai(rs.getString("tanggal_selesai"));
        return promo;
    }

    @Override
    public void tambah(Promo promo) {
        String sql = "INSERT INTO promo (judul, deskripsi, persen_diskon, tanggal_mulai, tanggal_selesai) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, promo.getJudul());
            ps.setString(2, promo.getDeskripsi());
            ps.setDouble(3, promo.getPersenDiskon());
            ps.setString(4, promo.getTanggalMulai());
            ps.setString(5, promo.getTanggalSelesai());
            ps.executeUpdate();
            System.out.println("Promo berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Promo> ambilSemua() {
        List<Promo> daftarPromo = new ArrayList<>();
        String sql = "SELECT * FROM promo";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                daftarPromo.add(mapToPromo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarPromo;
    }

    @Override
    public Promo ambilById(int id) {
        String sql = "SELECT * FROM promo WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapToPromo(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void hapus(int id) {
        String sql = "DELETE FROM promo WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Promo berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}