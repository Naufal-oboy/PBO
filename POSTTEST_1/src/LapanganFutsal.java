class LapanganFutsal {
    // --- Property ---
    int id;
    String nama;
    String lokasi;
    int harga;
    String status;

    LapanganFutsal() {
        this.id = 0;
        this.nama = "Lapangan Default";
        this.lokasi = "Lokasi Default";
        this.harga = 0;
        this.status = "Tersedia";
    }

    LapanganFutsal(int id, String nama, String lokasi, int harga, String status) {
        this.id = id;
        this.nama = nama;
        this.lokasi = lokasi;
        this.harga = harga;
        this.status = status;
    }

    void tampilInfo() {
        System.out.println("--------------------------------------");
        System.out.println("ID      : " + this.id);
        System.out.println("Nama    : " + this.nama);
        System.out.println("Lokasi  : " + this.lokasi);
        System.out.println("Harga   : Rp " + this.harga + " / jam");
        System.out.println("Status  : " + this.status);
        System.out.println("--------------------------------------");
    }

    int hitungTotalBiaya(int jamSewa) {
        return this.harga * jamSewa;
    }

    void ubahStatus(String statusBaru) {
        this.status = statusBaru;
    }
}
