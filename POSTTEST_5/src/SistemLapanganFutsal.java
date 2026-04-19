import java.util.ArrayList;
import java.util.Scanner;

class SistemLapanganFutsal {

    static ArrayList<LapanganFutsal> daftarLapangan = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int idCounter = 1;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            tampilMenu();
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> createLapangan();
                case 2 -> readLapangan();
                case 3 -> updateLapangan();
                case 4 -> deleteLapangan();
                case 5 -> hitungBiayaSewa();
                case 0 -> {
                    System.out.println("\nTerima kasih! Program selesai.");
                    running = false;
                }
                default -> System.out.println("\nPilihan tidak valid. Coba lagi.");
            }
        }

        scanner.close();
    }

    static void tampilMenu() {
        System.out.println("\n========================================");
        System.out.println("   SISTEM MANAJEMEN LAPANGAN FUTSAL    ");
        System.out.println("========================================");
        System.out.println("  1. Tambah Lapangan  (Create)");
        System.out.println("  2. Lihat Lapangan   (Read)");
        System.out.println("  3. Edit Lapangan    (Update)");
        System.out.println("  4. Hapus Lapangan   (Delete)");
        System.out.println("  5. Hitung Biaya Sewa");
        System.out.println("  0. Keluar");
        System.out.println("========================================");
    }

    static void createLapangan() {
        System.out.println("\n== TAMBAH LAPANGAN BARU ==");
        System.out.println("Pilih tipe lapangan:");
        System.out.println("  1. Indoor");
        System.out.println("  2. Outdoor");
        System.out.print("Pilihan: ");
        int tipe = scanner.nextInt();
        scanner.nextLine();

        if (tipe != 1 && tipe != 2) {
            System.out.println("Tipe tidak valid.");
            return;
        }

        System.out.print("Nama Lapangan : ");
        String nama = scanner.nextLine();

        System.out.print("Lokasi        : ");
        String lokasi = scanner.nextLine();

        System.out.print("Harga per Jam : Rp ");
        int harga = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Status (1=Tersedia / 0=Tidak Tersedia): ");
        String status = scanner.nextInt() == 1 ? "Tersedia" : "Tidak Tersedia";
        scanner.nextLine();

        LapanganFutsal lapanganBaru;

        if (tipe == 1) {
            System.out.print("Tersedia AC? (1=Ada / 0=Tidak Ada): ");
            boolean ac = scanner.nextInt() == 1;
            scanner.nextLine();

            lapanganBaru = new LapanganIndoor(idCounter++, nama, lokasi, harga, status, ac);

        } else {
            System.out.print("Jenis Sintetis (Premium/Standard): ");
            String sintetis = scanner.nextLine();

            lapanganBaru = new LapanganOutdoor(idCounter++, nama, lokasi, harga, status, sintetis);
        }

        daftarLapangan.add(lapanganBaru);
        System.out.println("\nLapangan berhasil ditambahkan!");
        lapanganBaru.tampilInfo();
    }

    static void readLapangan() {
        System.out.println("\n== DAFTAR LAPANGAN FUTSAL ==");

        if (daftarLapangan.isEmpty()) {
            System.out.println("Belum ada data lapangan.");
            return;
        }

        for (LapanganFutsal lapangan : daftarLapangan) {
            lapangan.tampilInfo();
        }
    }

    static void updateLapangan() {
        System.out.println("\n== EDIT DATA LAPANGAN ==");
        readLapangan();

        if (daftarLapangan.isEmpty()) return;

        System.out.print("Masukkan ID lapangan yang ingin diedit: ");
        int idCari = scanner.nextInt();
        scanner.nextLine();

        LapanganFutsal target = cariById(idCari);

        if (target == null) {
            System.out.println("Lapangan dengan ID " + idCari + " tidak ditemukan.");
            return;
        }

        System.out.println("\nData saat ini:");
        target.tampilInfo();

        System.out.print("Nama baru (Enter=skip): ");
        String namaBaru = scanner.nextLine();
        if (!namaBaru.isBlank()) target.setNama(namaBaru);

        System.out.print("Lokasi (lantai) baru (Enter=skip): ");
        String lokasiBaru = scanner.nextLine();
        if (!lokasiBaru.isBlank()) target.setLokasi(lokasiBaru);

        System.out.print("Harga baru per jam (0=skip): ");
        int hargaBaru = scanner.nextInt();
        scanner.nextLine();
        if (hargaBaru > 0) target.setHarga(hargaBaru);

        System.out.print("Status baru (1=Tersedia / 0=Tidak Tersedia / -1=skip): ");
        int statusInput = scanner.nextInt();
        scanner.nextLine();
        if (statusInput == 1) target.setStatus("Tersedia");
        else if (statusInput == 0) target.setStatus("Tidak Tersedia");

        if (target instanceof LapanganIndoor indoor) {
            System.out.print("Tersedia AC? (1=Ada / 0=Tidak Ada / -1=skip): ");
            int acBaru = scanner.nextInt();
            scanner.nextLine();
            if (acBaru == 1) indoor.setTersediaAC(true);
            else if (acBaru == 0) indoor.setTersediaAC(false);

        } else if (target instanceof LapanganOutdoor outdoor) {
            System.out.print("Jenis Sintetis baru (Enter=skip): ");
            String sintetisBaru = scanner.nextLine();
            if (!sintetisBaru.isBlank()) outdoor.setJenisSintetis(sintetisBaru);
        }

        System.out.println("\nData lapangan berhasil diperbarui!");
        target.tampilInfo();
    }

    static void deleteLapangan() {
        System.out.println("\n== HAPUS LAPANGAN ==");
        readLapangan();

        if (daftarLapangan.isEmpty()) return;

        System.out.print("Masukkan ID lapangan yang ingin dihapus: ");
        int idHapus = scanner.nextInt();
        scanner.nextLine();

        LapanganFutsal target = cariById(idHapus);

        if (target == null) {
            System.out.println("Lapangan dengan ID " + idHapus + " tidak ditemukan.");
            return;
        }

        System.out.println("\nLapangan yang akan dihapus:");
        target.tampilInfo();

        System.out.print("Konfirmasi hapus? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {
            daftarLapangan.remove(target);
            System.out.println("Lapangan berhasil dihapus.");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }


    static void hitungBiayaSewa() {
        System.out.println("\n== HITUNG BIAYA SEWA ==");
        readLapangan();

        if (daftarLapangan.isEmpty()) return;

        System.out.print("Masukkan ID lapangan: ");
        int idSewa = scanner.nextInt();
        scanner.nextLine();

        LapanganFutsal target = cariById(idSewa);

        if (target == null) {
            System.out.println("Lapangan tidak ditemukan.");
            return;
        }

        if (target.getStatus().equals("Tidak Tersedia")) {
            System.out.println("Lapangan sedang tidak tersedia.");
            return;
        }

        Sewable sewable = (Sewable) target;

        System.out.print("Jumlah jam sewa: ");
        int jam = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Gunakan diskon? (y/n): ");
        String pakaiDiskon = scanner.nextLine();

        int total;

        if (pakaiDiskon.equalsIgnoreCase("y")) {
            System.out.print("Masukkan diskon (%): ");
            double diskon = scanner.nextDouble();
            scanner.nextLine();

            total = sewable.hitungTotalBiaya(jam, diskon);

            if (total > 0) {
                int totalSebelum = target.getHarga() * jam;
                System.out.println("\nRincian Sewa:");
                System.out.println("   Lapangan       : " + target.getNama());
                System.out.println("   Lokasi         : " + target.getLokasi());
                System.out.println("   Tipe           : " + target.getDetailTipe());
                System.out.println("   Durasi         : " + jam + " jam");
                System.out.println("   Harga/Jam      : Rp " + target.getHarga());
                System.out.println("   Total Sebelum  : Rp " + totalSebelum);
                System.out.println("   Diskon         : " + diskon + "%");
                System.out.println("   TOTAL BAYAR    : Rp " + total);
            }
        } else {
            total = sewable.hitungTotalBiaya(jam);

            if (total > 0) {
                System.out.println("\nRincian Sewa:");
                System.out.println("   Lapangan  : " + target.getNama());
                System.out.println("   Lokasi  : " + target.getLokasi());
                System.out.println("   Tipe      : " + target.getDetailTipe());
                System.out.println("   Durasi    : " + jam + " jam");
                System.out.println("   Harga/Jam : Rp " + target.getHarga());
                System.out.println("   TOTAL     : Rp " + total);
            }
        }
    }

    static LapanganFutsal cariById(int id) {
        for (LapanganFutsal lapangan : daftarLapangan) {
            if (lapangan.getId() == id) {
                return lapangan;
            }
        }
        return null;
    }
}