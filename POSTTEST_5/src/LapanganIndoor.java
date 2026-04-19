class LapanganIndoor extends LapanganFutsal implements Sewable {

    private boolean tersediaAC;

    LapanganIndoor(int id, String nama, String lokasi, int harga, String status,
                   boolean tersediaAC) {
        super(id, nama, lokasi, harga, status);
        this.tersediaAC = tersediaAC;
    }

    public boolean isTersediaAC()               { return tersediaAC; }
    public void setTersediaAC(boolean tersediaAC) { this.tersediaAC = tersediaAC; }


    @Override
    public void tampilInfo() {
        super.tampilInfoDasar(); // panggil concrete method superclass
        System.out.println("Tipe    : " + getDetailTipe());
        System.out.println("AC      : " + (this.tersediaAC ? "Ada" : "Tidak Ada"));
        System.out.println("--------------------------------------");
    }

    @Override
    public String getDetailTipe() {
        return "Indoor";
    }

    @Override
    public int hitungTotalBiaya(int jamSewa) {
        if (jamSewa <= 0) {
            System.out.println("Jumlah jam sewa harus lebih dari 0.");
            return 0;
        }
        return this.getHarga() * jamSewa;
    }

    @Override
    public int hitungTotalBiaya(int jamSewa, double diskonPersen) {
        if (jamSewa <= 0) {
            System.out.println("Jumlah jam sewa harus lebih dari 0.");
            return 0;
        }
        if (diskonPersen < 0 || diskonPersen > 100) {
            System.out.println("Diskon tidak valid. Dihitung tanpa diskon.");
            return this.getHarga() * jamSewa;
        }
        int totalSebelumDiskon = this.getHarga() * jamSewa;
        int jumlahDiskon = (int) (totalSebelumDiskon * (diskonPersen / 100));
        return totalSebelumDiskon - jumlahDiskon;
    }
}