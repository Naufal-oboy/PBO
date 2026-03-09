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

        LapanganFutsal lapanganBaru = new LapanganFutsal(idCounter++, nama, lokasi, harga, status);
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
        if (!namaBaru.isBlank()) target.nama = namaBaru;

        System.out.print("Lokasi baru (Enter=skip): ");
        String lokasiBaru = scanner.nextLine();
        if (!lokasiBaru.isBlank()) target.lokasi = lokasiBaru;

        System.out.print("Harga baru per jam (0=skip): ");
        int hargaBaru = scanner.nextInt();
        scanner.nextLine();
        if (hargaBaru > 0) target.harga = hargaBaru;

        System.out.print("Status baru (1=Tersedia / 0=Tidak Tersedia / -1=skip): ");
        int statusInput = scanner.nextInt();
        scanner.nextLine();
        if (statusInput == 1) target.ubahStatus("Tersedia");
        else if (statusInput == 0) target.ubahStatus("Tidak Tersedia");

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

        if (target.status.equals("Tidak Tersedia")) {
            System.out.println("Lapangan sedang tidak tersedia.");
            return;
        }

        System.out.print("Jumlah jam sewa: ");
        int jam = scanner.nextInt();
        scanner.nextLine();

        int total = target.hitungTotalBiaya(jam);
        System.out.println("\nRincian Sewa:");
        System.out.println("   Lapangan  : " + target.nama);
        System.out.println("   Lokasi    : " + target.lokasi);
        System.out.println("   Durasi    : " + jam + " jam");
        System.out.println("   Harga/Jam : Rp " + target.harga);
        System.out.println("   TOTAL     : Rp " + total);
    }

    static LapanganFutsal cariById(int id) {
        for (LapanganFutsal lapangan : daftarLapangan) {
            if (lapangan.id == id) {
                return lapangan;
            }
        }
        return null;
    }
}