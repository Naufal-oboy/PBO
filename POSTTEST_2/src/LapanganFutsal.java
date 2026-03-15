
class LapanganFutsal {

    private int id;
    private String nama;
    private String lokasi;
    private int harga;
    private String status;

    LapanganFutsal() {
        this.id = 0;
        this.nama = "Lapangan Default";
        this.lokasi = "Lokasi Default";
        this.harga = 0;
        this.status = "Tersedia";
    }

    LapanganFutsal(int id, String nama, String lokasi, int harga, String status) {
        this.id = id;
        this.setNama(nama);
        this.setLokasi(lokasi);
        this.setHarga(harga);
        this.setStatus(status);
    }


    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public int getHarga() {
        return harga;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        if (id <= 0) {
            System.out.println("ID tidak boleh nol atau negatif.");
        } else {
            this.id = id;
        }
    }

    public void setNama(String nama) {
        if (nama == null || nama.isEmpty()) {
            System.out.println("Nama lapangan tidak boleh kosong.");
            this.nama = "Tidak Diketahui";
        } else {
            this.nama = nama;
        }
    }

    public void setLokasi(String lokasi) {
        if (lokasi == null || lokasi.isEmpty()) {
            System.out.println("Lokasi tidak boleh kosong.");
            this.lokasi = "Tidak Diketahui";
        } else {
            this.lokasi = lokasi;
        }
    }

    public void setHarga(int harga) {
        if (harga < 0) {
            System.out.println("Harga tidak boleh negatif. Harga diset ke 0.");
            this.harga = 0;
        } else {
            this.harga = harga;
        }
    }

    public void setStatus(String status) {
        if (!status.equals("Tersedia") && !status.equals("Tidak Tersedia")) {
            System.out.println("Status tidak valid. Gunakan 'Tersedia' atau 'Tidak Tersedia'.");
            this.status = "Tersedia";
        } else {
            this.status = status;
        }
    }


    public void tampilInfo() {
        System.out.println("--------------------------------------");
        System.out.println("ID      : " + this.id);
        System.out.println("Nama    : " + this.nama);
        System.out.println("Lokasi  : " + this.lokasi);
        System.out.println("Harga   : Rp " + this.harga + " / jam");
        System.out.println("Status  : " + this.status);
        System.out.println("--------------------------------------");
    }

    public int hitungTotalBiaya(int jamSewa) {
        if (jamSewa <= 0) {
            System.out.println("Jumlah jam sewa harus lebih dari 0.");
            return 0;
        }
        return this.harga * jamSewa;
    }
}