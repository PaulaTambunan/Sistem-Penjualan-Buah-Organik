package repository;

import model.Buah;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuahRepositoryImpl implements BuahRepository {

    // DATA MAPPER — mengubah ResultSet jadi object Buah
    private Buah mapToBuah(ResultSet rs) throws SQLException {
        Buah buah = new Buah();
        buah.setId(rs.getInt("id"));
        buah.setNama(rs.getString("nama"));
        buah.setKategori(rs.getString("kategori"));
        buah.setHarga(rs.getDouble("harga"));
        buah.setStok(rs.getInt("stok"));
        buah.setDeskripsi(rs.getString("deskripsi"));
        return buah;
    }

    @Override
    public void tambah(Buah buah) {
        String sql = "INSERT INTO buah (nama, kategori, harga, stok, deskripsi) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, buah.getNama());
            ps.setString(2, buah.getKategori());
            ps.setDouble(3, buah.getHarga());
            ps.setInt(4, buah.getStok());
            ps.setString(5, buah.getDeskripsi());
            ps.executeUpdate();
            System.out.println("Buah berhasil ditambahkan!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Buah> ambilSemua() {
        List<Buah> daftarBuah = new ArrayList<>();
        String sql = "SELECT * FROM buah";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                daftarBuah.add(mapToBuah(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarBuah;
    }

    @Override
    public Buah ambilById(int id) {
        String sql = "SELECT * FROM buah WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapToBuah(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Buah buah) {
        String sql = "UPDATE buah SET nama=?, kategori=?, harga=?, stok=?, deskripsi=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, buah.getNama());
            ps.setString(2, buah.getKategori());
            ps.setDouble(3, buah.getHarga());
            ps.setInt(4, buah.getStok());
            ps.setString(5, buah.getDeskripsi());
            ps.setInt(6, buah.getId());
            ps.executeUpdate();
            System.out.println("Buah berhasil diupdate!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapus(int id) {
        String sql = "DELETE FROM buah WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Buah berhasil dihapus!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}