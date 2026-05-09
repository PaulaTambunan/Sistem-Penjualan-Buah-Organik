package repository;

import model.Pelanggan;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganRepositoryImpl implements PelangganRepository {

    private Pelanggan mapToPelanggan(ResultSet rs) throws SQLException {
        Pelanggan p = new Pelanggan();
        p.setId(rs.getInt("id"));
        p.setNama(rs.getString("nama"));
        p.setEmail(rs.getString("email"));
        p.setTelepon(rs.getString("telepon"));
        p.setAlamat(rs.getString("alamat"));
        return p;
    }

    @Override
    public void tambah(Pelanggan pelanggan) {
        String sql = "INSERT INTO pelanggan (nama, email, telepon, alamat) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getEmail());
            ps.setString(3, pelanggan.getTelepon());
            ps.setString(4, pelanggan.getAlamat());
            ps.executeUpdate();
            System.out.println("Pelanggan berhasil didaftarkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pelanggan> ambilSemua() {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapToPelanggan(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Pelanggan ambilById(int id) {
        String sql = "SELECT * FROM pelanggan WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapToPelanggan(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pelanggan ambilByEmail(String email) {
        String sql = "SELECT * FROM pelanggan WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapToPelanggan(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Pelanggan pelanggan) {
        String sql = "UPDATE pelanggan SET nama=?, email=?, telepon=?, alamat=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getEmail());
            ps.setString(3, pelanggan.getTelepon());
            ps.setString(4, pelanggan.getAlamat());
            ps.setInt(5, pelanggan.getId());
            ps.executeUpdate();
            System.out.println("Data pelanggan berhasil diupdate!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapus(int id) {
        String sql = "DELETE FROM pelanggan WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Pelanggan berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}