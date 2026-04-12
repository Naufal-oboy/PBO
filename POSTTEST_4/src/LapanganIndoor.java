class LapanganIndoor extends LapanganFutsal {

    private boolean tersediaAC;

    LapanganIndoor(int id, String nama, String lokasi, int harga, String status,
                   boolean tersediaAC) {
        super(id, nama, lokasi, harga, status);
        this.tersediaAC = tersediaAC;
    }

    public boolean isTersediaAC() { return tersediaAC; }

    public void setTersediaAC(boolean tersediaAC) {
        this.tersediaAC = tersediaAC;
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("Tipe    : Indoor");
        System.out.println("AC      : " + (this.tersediaAC ? "Ada" : "Tidak Ada"));
        System.out.println("--------------------------------------");
    }
}