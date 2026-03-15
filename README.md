# Sistem Manajemen Lapangan Futsal

| | |
|---|---|
| **Nama** | Muhammad Naufal Adi Brata Putra Suharizman Poerwo |
| **NIM** | 2409106049 |
| **Mata Kuliah** | Pemrograman Berorientasi Objek (PBO) |
| **Kelas** | B1-24 |

---

Program berbasis **Java** untuk mengelola data lapangan futsal menggunakan konsep **Object-Oriented Programming (OOP)**. Program ini menerapkan **Encapsulation** dengan Access Modifier `private` dan `public`, serta Getter dan Setter untuk mengakses data secara aman. Program mendukung operasi **CRUD** (Create, Read, Update, Delete) dengan penyimpanan data menggunakan `ArrayList` dan berjalan secara berulang hingga pengguna memilih menu keluar.

---

## Struktur File

```
project/
├── LapanganFutsal.java        # Class model data lapangan futsal (Encapsulation)
├── SistemLapanganFutsal.java  # Class utama (main program + CRUD)
└── README.md
```

Kedua file class dipisah sesuai prinsip OOP. `SistemLapanganFutsal` mengakses data `LapanganFutsal` **hanya melalui getter dan setter**, tidak pernah langsung ke property.

---

## Penerapan Encapsulation

### Access Modifier yang Digunakan

| Access Modifier | Diterapkan pada | Dapat Diakses |
|---|---|---|
| `private` | Semua property (`id`, `nama`, `lokasi`, `harga`, `status`) | Hanya di dalam class `LapanganFutsal` |
| `public` | Semua getter, setter, dan method | Dari mana saja, termasuk `SistemLapanganFutsal` |

### Mengapa Property Dibuat `private`?

Dengan menjadikan property `private`, data tidak bisa diakses atau diubah langsung dari luar class. Contoh:

```java
LapanganFutsal l = new LapanganFutsal();

l.harga = -50000;    // ERROR — tidak bisa, property private
l.setHarga(-50000);  // Aman — setter menolak nilai negatif
```

Setter bertugas memvalidasi data sebelum disimpan, sehingga data selalu terjaga keabsahannya.

---

## Detail Class

### `LapanganFutsal.java`

Class ini merepresentasikan satu data lapangan futsal dengan seluruh property-nya dibuat `private`.

**Property (`private`):**

| Property | Tipe Data | Keterangan |
|---|---|---|
| `id` | `int` | ID unik lapangan |
| `nama` | `String` | Nama lapangan |
| `lokasi` | `String` | Lokasi / alamat lapangan |
| `harga` | `int` | Harga sewa per jam (Rupiah) |
| `status` | `String` | `"Tersedia"` atau `"Tidak Tersedia"` |

**Constructor:**

```java
// 1. Non-argument constructor — semua property diisi nilai default
LapanganFutsal()

// 2. Parameterized constructor — memanggil setter agar validasi berjalan
LapanganFutsal(int id, String nama, String lokasi, int harga, String status)
```

**Getter (`public`):**

| Method | Return | Keterangan |
|---|---|---|
| `getId()` | `int` | Mengembalikan nilai `id` |
| `getNama()` | `String` | Mengembalikan nilai `nama` |
| `getLokasi()` | `String` | Mengembalikan nilai `lokasi` |
| `getHarga()` | `int` | Mengembalikan nilai `harga` |
| `getStatus()` | `String` | Mengembalikan nilai `status` |

**Setter (`public`) + Validasi:**

| Method | Validasi |
|---|---|
| `setId(int id)` | ID tidak boleh nol atau negatif |
| `setNama(String nama)` | Nama tidak boleh null atau kosong → default `"Tidak Diketahui"` |
| `setLokasi(String lokasi)` | Lokasi tidak boleh null atau kosong → default `"Tidak Diketahui"` |
| `setHarga(int harga)` | Harga tidak boleh negatif → diset ke `0` |
| `setStatus(String status)` | Hanya menerima `"Tersedia"` atau `"Tidak Tersedia"` → default `"Tersedia"` |

**Method (`public`):**

| Method | Return | Keterangan |
|---|---|---|
| `tampilInfo()` | `void` | Menampilkan seluruh data lapangan ke konsol |
| `hitungTotalBiaya(int jamSewa)` | `int` | Menghitung total biaya: `harga x jamSewa`, validasi jam > 0 |

---

### `SistemLapanganFutsal.java`

Class utama yang berisi `main()` dan seluruh logika menu CRUD. Semua akses ke data lapangan dilakukan **melalui getter dan setter**, bukan langsung ke property.

**Method CRUD:**

| Method | Menu | Keterangan |
|---|---|---|
| `createLapangan()` | 1 — Create | Input data lalu buat objek via constructor (setter dipanggil di dalamnya) |
| `readLapangan()` | 2 — Read | Tampilkan data via `tampilInfo()` |
| `updateLapangan()` | 3 — Update | Perbarui data via setter (`setNama()`, `setLokasi()`, `setHarga()`, `setStatus()`) |
| `deleteLapangan()` | 4 — Delete | Cari lapangan via `getId()`, hapus setelah konfirmasi |
| `hitungBiayaSewa()` | 5 — Hitung Biaya | Ambil data via `getNama()`, `getLokasi()`, `getHarga()`, `getStatus()` |
| `cariById(int id)` | *(helper)* | Bandingkan ID menggunakan `getId()` |

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

**Contoh validasi setter saat input harga negatif:**
```
Harga tidak boleh negatif. Harga diset ke 0.
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
