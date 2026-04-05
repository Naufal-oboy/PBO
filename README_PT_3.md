# Sistem Manajemen Lapangan Futsal

| | |
|---|---|
| **Nama** | Muhammad Naufal Adi Brata Putra Suharizman Poerwo |
| **NIM** | 2409106049 |
| **Mata Kuliah** | Pemrograman Berorientasi Objek (PBO) |
| **Kelas** | B1-24 |

---

Program berbasis **Java** untuk mengelola data lapangan futsal menggunakan konsep **Object-Oriented Programming (OOP)**. Program ini menerapkan **Encapsulation** dan **Inheritance** dengan struktur class yang terbagi menjadi superclass dan dua subclass. Program mendukung operasi **CRUD** (Create, Read, Update, Delete) dengan penyimpanan data menggunakan `ArrayList` dan berjalan secara berulang hingga pengguna memilih menu keluar.

---

## Struktur File

```
project/
├── LapanganFutsal.java        # Superclass (Parent) — property dan method umum
├── LapanganIndoor.java        # Subclass (Child) — lapangan indoor
├── LapanganOutdoor.java       # Subclass (Child) — lapangan outdoor
├── SistemLapanganFutsal.java  # Class utama (main program + CRUD)
└── README.md
```

---

## Penerapan Inheritance

### Tipe Inheritance: Hierarchical Inheritance

Satu superclass (`LapanganFutsal`) diturunkan ke dua subclass (`LapanganIndoor` dan `LapanganOutdoor`).

```
         LapanganFutsal          ← Superclass
         /            \
LapanganIndoor    LapanganOutdoor  ← Subclass
```

### Relasi is-a

| Subclass | Relasi | Keterangan |
|---|---|---|
| `LapanganIndoor` | is a `LapanganFutsal` | Lapangan indoor adalah lapangan futsal |
| `LapanganOutdoor` | is a `LapanganFutsal` | Lapangan outdoor adalah lapangan futsal |

### Keyword yang Digunakan

| Keyword | Digunakan pada | Fungsi |
|---|---|---|
| `extends` | `LapanganIndoor`, `LapanganOutdoor` | Menghubungkan subclass ke superclass |
| `super(...)` | Constructor subclass | Memanggil constructor superclass |
| `super.tampilInfo()` | Method `tampilInfo()` subclass | Memanggil method superclass sebelum menambah info subclass |
| `@Override` | `tampilInfo()` di kedua subclass | Menandai method yang di-override dari superclass |

---

## Penerapan Encapsulation

Semua property di seluruh class dibuat `private` dan hanya dapat diakses melalui getter dan setter yang bersifat `public`.

| Access Modifier | Diterapkan pada | Dapat Diakses |
|---|---|---|
| `private` | Semua property di semua class | Hanya di dalam class masing-masing |
| `public` | Semua getter, setter, dan method | Dari mana saja |

---

## Detail Class

### `LapanganFutsal.java` — Superclass

Class induk yang mendefinisikan property dan method umum yang diwariskan ke kedua subclass.

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
LapanganFutsal()                                              // Non-argument
LapanganFutsal(int id, String nama, String lokasi,
               int harga, String status)                      // Parameterized
```

**Getter (`public`):** `getId()`, `getNama()`, `getLokasi()`, `getHarga()`, `getStatus()`

**Setter (`public`) + Validasi:**

| Setter | Validasi |
|---|---|
| `setNama(String nama)` | Tidak boleh null atau kosong → default `"Tidak Diketahui"` |
| `setLokasi(String lokasi)` | Tidak boleh null atau kosong → default `"Tidak Diketahui"` |
| `setHarga(int harga)` | Tidak boleh negatif → diset ke `0` |
| `setStatus(String status)` | Hanya `"Tersedia"` atau `"Tidak Tersedia"` → default `"Tersedia"` |

**Method (`public`):**

| Method | Return | Keterangan |
|---|---|---|
| `tampilInfo()` | `void` | Menampilkan data dasar lapangan |
| `hitungTotalBiaya(int jamSewa)` | `int` | Menghitung total biaya: `harga x jamSewa` |

---

### `LapanganIndoor.java` — Subclass

Mewarisi semua property dan method dari `LapanganFutsal`. Menambah property khas lapangan indoor.

**Property tambahan (`private`):**

| Property | Tipe Data | Keterangan |
|---|---|---|
| `tersediaAC` | `boolean` | Ketersediaan AC di lapangan |

**Constructor:**

```java
LapanganIndoor(int id, String nama, String lokasi,
               int harga, String status, boolean tersediaAC)
```

**Getter:** `isTersediaAC()`

**Setter:** `setTersediaAC(boolean tersediaAC)`

**Override Method:**

```java
@Override
public void tampilInfo() {
    super.tampilInfo();   // tampilkan data dari superclass
    // tambah: Tipe Indoor dan info AC
}
```

---

### `LapanganOutdoor.java` — Subclass

Mewarisi semua property dan method dari `LapanganFutsal`. Menambah property khas lapangan outdoor.

**Property tambahan (`private`):**

| Property | Tipe Data | Keterangan |
|---|---|---|
| `jenisSintetis` | `String` | Jenis rumput sintetis (Premium / Standard) |

**Constructor:**

```java
LapanganOutdoor(int id, String nama, String lokasi,
                int harga, String status, String jenisSintetis)
```

**Getter:** `getJenisSintetis()`

**Setter + Validasi:** `setJenisSintetis(String jenisSintetis)` — tidak boleh null atau kosong

**Override Method:**

```java
@Override
public void tampilInfo() {
    super.tampilInfo();   // tampilkan data dari superclass
    // tambah: Tipe Outdoor dan info jenis sintetis
}
```

---

### `SistemLapanganFutsal.java` — Class Utama

Class utama yang berisi `main()` dan seluruh logika menu CRUD. ArrayList bertipe superclass (`LapanganFutsal`) sehingga dapat menampung objek dari kedua subclass sekaligus.

**Method CRUD:**

| Method | Menu | Keterangan |
|---|---|---|
| `createLapangan()` | 1 — Create | Pilih tipe (Indoor/Outdoor), input data, buat objek subclass |
| `readLapangan()` | 2 — Read | Tampilkan semua lapangan via `tampilInfo()` |
| `updateLapangan()` | 3 — Update | Edit data via setter, cek tipe dengan `instanceof` |
| `deleteLapangan()` | 4 — Delete | Hapus lapangan by ID setelah konfirmasi |
| `hitungBiayaSewa()` | 5 — Hitung Biaya | Hitung total biaya sewa berdasarkan jam |
| `cariById(int id)` | *(helper)* | Cari lapangan berdasarkan ID menggunakan `getId()` |

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

**Contoh output LapanganIndoor:**
```
--------------------------------------
ID      : 1
Nama    : Lapangan A
Lokasi  : Jl. Sudirman No. 1
Harga   : Rp 150000 / jam
Status  : Tersedia
Tipe    : Indoor
AC      : Ada
--------------------------------------
```

**Contoh output LapanganOutdoor:**
```
--------------------------------------
ID      : 2
Nama    : Lapangan B
Lokasi  : Jl. Ahmad Yani No. 5
Harga   : Rp 100000 / jam
Status  : Tersedia
Tipe    : Outdoor
Sintetis: Premium
--------------------------------------
```
