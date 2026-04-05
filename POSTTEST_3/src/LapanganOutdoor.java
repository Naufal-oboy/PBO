class LapanganOutdoor extends LapanganFutsal {

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
        super.tampilInfo();
        System.out.println("Tipe    : Outdoor");
        System.out.println("Sintetis: " + this.jenisSintetis);
        System.out.println("--------------------------------------");
    }
}