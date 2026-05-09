package model;

import java.util.List;
import java.util.ArrayList;

public class Pesanan extends Entity {
    private int pelangganId;
    private String tanggal;
    private String alamatPengiriman;
    private double total;
    private String status;
    private List<DetailPesanan> details;

    public Pesanan() {
        this.details = new ArrayList<>();
    }

    public Pesanan(int pelangganId, String alamatPengiriman) {
        this.pelangganId = pelangganId;
        this.alamatPengiriman = alamatPengiriman;
        this.status = "Menunggu";
        this.details = new ArrayList<>();
    }

    public int getPelangganId() { return pelangganId; }
    public void setPelangganId(int pelangganId) { this.pelangganId = pelangganId; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public String getAlamatPengiriman() { return alamatPengiriman; }
    public void setAlamatPengiriman(String alamatPengiriman) { this.alamatPengiriman = alamatPengiriman; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<DetailPesanan> getDetails() { return details; }
    public void setDetails(List<DetailPesanan> details) { this.details = details; }

    @Override
    public String toString() {
        return String.format("[%d] Pelanggan ID: %d | Total: Rp%.2f | Status: %s | Alamat: %s",
            id, pelangganId, total, status, alamatPengiriman);
    }
}