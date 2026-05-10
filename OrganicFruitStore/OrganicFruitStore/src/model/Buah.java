package model;

public class Buah extends Entity {
    private String nama;
    private String kategori;
    private double harga;
    private int stok;
    private String deskripsi;

    
    public Buah() {}

    public Buah(String nama, String kategori, double harga, int stok, String deskripsi) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
        this.deskripsi = deskripsi;
    }

  
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | Rp%.2f | Stok: %d", 
            id, nama, kategori, harga, stok);
    }
}