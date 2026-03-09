# Sistem Manajemen Lapangan Futsal

Program berbasis **Java** untuk mengelola data lapangan futsal menggunakan konsep **Object-Oriented Programming (OOP)**. Program mendukung operasi **CRUD** (Create, Read, Update, Delete) dengan penyimpanan data menggunakan `ArrayList` dan berjalan secara berulang hingga pengguna memilih menu keluar.

---

## Struktur File

```
project/
├── LapanganFutsal.java        # Class model data lapangan futsal
├── SistemLapanganFutsal.java  # Class utama (main program + CRUD)
└── README.md
```

Kedua file class dipisah sesuai prinsip OOP, di mana setiap class memiliki tanggung jawabnya masing-masing. `SistemLapanganFutsal` dapat menggunakan class `LapanganFutsal` secara langsung karena berada dalam satu folder (package) yang sama tanpa perlu `import`.

---

## Detail Class

### `LapanganFutsal.java`

Class ini merepresentasikan satu data lapangan futsal sebagai blueprint objek.

**Property:**

| Property | Tipe Data | Keterangan |
|---|---|---|
| `id` | `int` | ID unik lapangan (di-generate otomatis) |
| `nama` | `String` | Nama lapangan |
| `lokasi` | `String` | Lokasi / alamat lapangan |
| `harga` | `int` | Harga sewa per jam (Rupiah) |
| `status` | `String` | `"Tersedia"` atau `"Tidak Tersedia"` |

**Constructor:**

```java
// 1. Non-argument constructor — semua property diisi nilai default
LapanganFutsal()

// 2. Parameterized constructor — nilai ditentukan saat instansiasi
LapanganFutsal(int id, String nama, String lokasi, int harga, String status)
```

**Method:**

| Method | Return | Keterangan |
|---|---|---|
| `tampilInfo()` | `void` | Menampilkan seluruh data lapangan ke konsol |
| `hitungTotalBiaya(int jamSewa)` | `int` | Menghitung total biaya: `harga × jamSewa` |
| `ubahStatus(String statusBaru)` | `void` | Mengubah nilai property `status` |

---

### `SistemLapanganFutsal.java`

Class utama yang berisi `main()` dan seluruh logika menu CRUD. Program berjalan dalam perulangan `while` dan berhenti saat pengguna memilih menu **0**.

**Method CRUD:**

| Method | Menu | Keterangan |
|---|---|---|
| `createLapangan()` | 1 — Create | Input data lapangan baru, lalu tambahkan ke `ArrayList` |
| `readLapangan()` | 2 — Read | Tampilkan seluruh isi `ArrayList` |
| `updateLapangan()` | 3 — Update | Cari lapangan by ID, edit field yang dipilih (field lain bisa di-skip) |
| `deleteLapangan()` | 4 — Delete | Cari lapangan by ID, hapus setelah konfirmasi `y/n` |
| `hitungBiayaSewa()` | 5 — Hitung Biaya | Hitung total biaya sewa berdasarkan jumlah jam |
| `cariById(int id)` | *(helper)* | Mencari objek `LapanganFutsal` dalam `ArrayList` berdasarkan ID |

---

## Tampilan Program

```
========================================
   SISTEM MANAJEMEN LAPANGAN FUTSAL    
========================================
  1. Tambah Lapangan  (Create)
  2. Lihat Lapangan   (Read)
  3. Edit Lapangan    (Update)
  4. Hapus Lapangan   (Delete)
  5. Hitung Biaya Sewa
  0. Keluar
========================================
Pilih menu:
```

**Contoh output menu Read:**
```
== DAFTAR LAPANGAN FUTSAL ==
--------------------------------------
ID      : 1
Nama    : Lapangan A
Lokasi  : Jl. Sudirman No. 1
Harga   : Rp 150000 / jam
Status  : Tersedia
--------------------------------------
```

**Contoh output Hitung Biaya Sewa:**
```
Rincian Sewa:
   Lapangan  : Lapangan A
   Lokasi    : Jl. Sudirman No. 1
   Durasi    : 2 jam
   Harga/Jam : Rp 150000
   TOTAL     : Rp 300000
```
