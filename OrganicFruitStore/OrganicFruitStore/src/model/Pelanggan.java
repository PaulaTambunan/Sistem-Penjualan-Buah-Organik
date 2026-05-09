package model;

public class Pelanggan extends Entity {
    private String nama;
    private String email;
    private String telepon;
    private String alamat;

    // Constructor
    public Pelanggan() {}

    public Pelanggan(String nama, String email, String telepon, String alamat) {
        this.nama = nama;
        this.email = email;
        this.telepon = telepon;
        this.alamat = alamat;
    }

    // Getter & Setter
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelepon() { return telepon; }
    public void setTelepon(String telepon) { this.telepon = telepon; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %s", id, nama, email, telepon);
    }
}