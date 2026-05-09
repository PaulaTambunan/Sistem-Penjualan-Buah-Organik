package model;

public class DetailPesanan extends Entity {
    private int pesananId;
    private int buahId;
    private String namaBuah;
    private int jumlah;
    private double hargaSatuan;

    public DetailPesanan() {}

    public DetailPesanan(int pesananId, int buahId, int jumlah, double hargaSatuan) {
        this.pesananId = pesananId;
        this.buahId = buahId;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
    }

    public int getPesananId() { return pesananId; }
    public void setPesananId(int pesananId) { this.pesananId = pesananId; }

    public int getBuahId() { return buahId; }
    public void setBuahId(int buahId) { this.buahId = buahId; }

    public String getNamaBuah() { return namaBuah; }
    public void setNamaBuah(String namaBuah) { this.namaBuah = namaBuah; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public double getHargaSatuan() { return hargaSatuan; }
    public void setHargaSatuan(double hargaSatuan) { this.hargaSatuan = hargaSatuan; }

    public double getSubtotal() { return jumlah * hargaSatuan; }

    @Override
    public String toString() {
        return String.format("  - %s x%d @ Rp%.2f = Rp%.2f",
            namaBuah, jumlah, hargaSatuan, getSubtotal());
    }
}