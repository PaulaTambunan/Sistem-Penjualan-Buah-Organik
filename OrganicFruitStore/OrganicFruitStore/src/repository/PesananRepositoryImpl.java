package repository;

import model.Pesanan;
import model.DetailPesanan;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PesananRepositoryImpl implements PesananRepository {

    private Pesanan mapToPesanan(ResultSet rs) throws SQLException {
        Pesanan p = new Pesanan();
        p.setId(rs.getInt("id"));
        p.setPelangganId(rs.getInt("pelanggan_id"));
        p.setTanggal(rs.getString("tanggal"));
        p.setAlamatPengiriman(rs.getString("alamat_pengiriman"));
        p.setTotal(rs.getDouble("total"));
        p.setStatus(rs.getString("status"));
        return p;
    }

    @Override
    public int tambah(Pesanan pesanan) {
        String sql = "INSERT INTO pesanan (pelanggan_id, alamat_pengiriman, total, status) " +
                     "OUTPUT INSERTED.id VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pesanan.getPelangganId());
            ps.setString(2, pesanan.getAlamatPengiriman());
            ps.setDouble(3, pesanan.getTotal());
            ps.setString(4, pesanan.getStatus());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Pesanan> ambilSemua() {
        List<Pesanan> list = new ArrayList<>();
        String sql = "SELECT * FROM pesanan";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapToPesanan(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Pesanan ambilById(int id) {
        String sql = "SELECT * FROM pesanan WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapToPesanan(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pesanan> ambilByPelangganId(int pelangganId) {
        List<Pesanan> list = new ArrayList<>();
        String sql = "SELECT * FROM pesanan WHERE pelanggan_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pelangganId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapToPesanan(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateStatus(int id, String status) {
        String sql = "UPDATE pesanan SET status=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Status pesanan diupdate: " + status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}