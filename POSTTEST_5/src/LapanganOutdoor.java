class LapanganOutdoor extends LapanganFutsal implements Sewable {

    private String jenisSintetis;

    LapanganOutdoor(int id, String nama, String lokasi, int harga, String status,
                    String jenisSintetis) {
        super(id, nama, lokasi, harga, status);
        this.setJenisSintetis(jenisSintetis);
    }

    public String getJenisSintetis() { return jenisSintetis; }

    public void setJenisSintetis(String jenisSintetis) {
        if (jenisSintetis == null || jenisSintetis.isEmpty()) {
            System.out.println("Jenis sintetis tidak boleh kosong.");
            this.jenisSintetis = "Tidak Diketahui";
        } else this.jenisSintetis = jenisSintetis;
    }

    @Override
    public void tampilInfo() {
        super.tampilInfoDasar();
        System.out.println("Tipe    : " + getDetailTipe());
        System.out.println("Sintetis: " + this.jenisSintetis);
        System.out.println("--------------------------------------");
    }

    @Override
    public String getDetailTipe() {
        return "Outdoor";
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