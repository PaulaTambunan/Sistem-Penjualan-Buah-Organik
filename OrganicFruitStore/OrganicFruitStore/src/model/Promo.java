package model;

public class Promo extends Entity {
    private String judul;
    private String deskripsi;
    private double persenDiskon;
    private String tanggalMulai;
    private String tanggalSelesai;

    public Promo() {}

    public Promo(String judul, String deskripsi, double persenDiskon, String tanggalMulai, String tanggalSelesai) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.persenDiskon = persenDiskon;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public double getPersenDiskon() { return persenDiskon; }
    public void setPersenDiskon(double persenDiskon) { this.persenDiskon = persenDiskon; }

    public String getTanggalMulai() { return tanggalMulai; }
    public void setTanggalMulai(String tanggalMulai) { this.tanggalMulai = tanggalMulai; }

    public String getTanggalSelesai() { return tanggalSelesai; }
    public void setTanggalSelesai(String tanggalSelesai) { this.tanggalSelesai = tanggalSelesai; }

    @Override
    public String toString() {
        return String.format("[%d] %s | Diskon: %.0f%% | %s s/d %s",
            id, judul, persenDiskon, tanggalMulai, tanggalSelesai);
    }
}