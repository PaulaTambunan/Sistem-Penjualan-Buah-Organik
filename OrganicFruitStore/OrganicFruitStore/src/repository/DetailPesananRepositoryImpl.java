package repository;

import model.DetailPesanan;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailPesananRepositoryImpl {

    public void tambah(DetailPesanan detail) {
        String sql = "INSERT INTO detail_pesanan (pesanan_id, buah_id, jumlah, harga_satuan) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getPesananId());
            ps.setInt(2, detail.getBuahId());
            ps.setInt(3, detail.getJumlah());
            ps.setDouble(4, detail.getHargaSatuan());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DetailPesanan> ambilByPesananId(int pesananId) {
        List<DetailPesanan> list = new ArrayList<>();
        String sql = "SELECT dp.*, b.nama as nama_buah FROM detail_pesanan dp " +
                     "JOIN buah b ON dp.buah_id = b.id WHERE dp.pesanan_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pesananId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetailPesanan d = new DetailPesanan();
                d.setId(rs.getInt("id"));
                d.setPesananId(rs.getInt("pesanan_id"));
                d.setBuahId(rs.getInt("buah_id"));
                d.setNamaBuah(rs.getString("nama_buah"));
                d.setJumlah(rs.getInt("jumlah"));
                d.setHargaSatuan(rs.getDouble("harga_satuan"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}