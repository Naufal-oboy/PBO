# Sistem Manajemen Lapangan Futsal

| | |
|---|---|
| **Nama** | Muhammad Naufal Adi Brata Putra Suharizman Poerwo |
| **NIM** | 2409106049 |
| **Mata Kuliah** | Pemrograman Berorientasi Objek (PBO) |
| **Kelas** | B1-24 |

---

Program berbasis **Java** untuk mengelola data lapangan futsal menggunakan konsep **Object-Oriented Programming (OOP)**. Program ini menerapkan **Encapsulation**, **Inheritance**, dan **Polymorphism**. Program mendukung operasi **CRUD** (Create, Read, Update, Delete) dengan penyimpanan data menggunakan `ArrayList` dan berjalan secara berulang hingga pengguna memilih menu keluar.

---

## Struktur File

```
project/
├── LapanganFutsal.java        # Superclass — property, method, overloading
├── LapanganIndoor.java        # Subclass — lapangan indoor (overriding)
├── LapanganOutdoor.java       # Subclass — lapangan outdoor (overriding)
├── SistemLapanganFutsal.java  # Class utama (main program + CRUD)
└── README.md
```

---

## Penerapan Polymorphism

### 1. Method Overloading — Static Polymorphism

Diterapkan pada `LapanganFutsal` dengan method `hitungTotalBiaya()`. Nama method sama namun parameter berbeda, sehingga Java memilih versi yang tepat berdasarkan argumen yang diberikan saat pemanggilan.

```java
// Overloading 1 — tanpa diskon (1 parameter)
public int hitungTotalBiaya(int jamSewa)

// Overloading 2 — dengan diskon persen (2 parameter)
public int hitungTotalBiaya(int jamSewa, double diskonPersen)
```

| | Overloading 1 | Overloading 2 |
|---|---|---|
| Parameter | `int jamSewa` | `int jamSewa, double diskonPersen` |
| Fungsi | Hitung total biaya tanpa potongan | Hitung total biaya setelah dikurangi diskon |
| Validasi | Jam > 0 | Jam > 0, diskon 0–100% |

### 2. Method Overriding — Dynamic Polymorphism

Diterapkan pada kedua subclass. Method `tampilInfo()` yang diwarisi dari `LapanganFutsal` di-override di `LapanganIndoor` dan `LapanganOutdoor` dengan isi yang berbeda sesuai tipe lapangan masing-masing.

| Class | Override | Tambahan yang Ditampilkan |
|---|---|---|
| `LapanganIndoor` | `@Override tampilInfo()` | `Tipe: Indoor` dan info `AC` |
| `LapanganOutdoor` | `@Override tampilInfo()` | `Tipe: Outdoor` dan info `Sintetis` |

Kedua subclass memanggil `super.tampilInfo()` terlebih dahulu untuk menampilkan data umum dari superclass, lalu menambahkan info khasnya masing-masing.

---

## Penerapan Inheritance

Tipe **Hierarchical Inheritance** — satu superclass diturunkan ke dua subclass.

```
         LapanganFutsal          ← Superclass
         /            \
LapanganIndoor    LapanganOutdoor  ← Subclass
```

| Subclass | Relasi | Property Tambahan |
|---|---|---|
| `LapanganIndoor` | is a `LapanganFutsal` | `tersediaAC` |
| `LapanganOutdoor` | is a `LapanganFutsal` | `jenisSintetis` |

---

## Penerapan Encapsulation

Semua property di seluruh class dibuat `private` dan diakses melalui getter dan setter `public`.

| Access Modifier | Diterapkan pada | Dapat Diakses |
|---|---|---|
| `private` | Semua property di semua class | Hanya di dalam class masing-masing |
| `public` | Semua getter, setter, dan method | Dari mana saja |

---

## Detail Class

### `LapanganFutsal.java` — Superclass

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
LapanganFutsal()
LapanganFutsal(int id, String nama, String lokasi, int harga, String status)
```

**Getter (`public`):** `getId()`, `getNama()`, `getLokasi()`, `getHarga()`, `getStatus()`

**Setter (`public`) + Validasi:**

| Setter | Validasi |
|---|---|
| `setNama(String nama)` | Tidak boleh null atau kosong |
| `setLokasi(String lokasi)` | Tidak boleh null atau kosong |
| `setHarga(int harga)` | Tidak boleh negatif |
| `setStatus(String status)` | Hanya `"Tersedia"` atau `"Tidak Tersedia"` |

**Method Overloading (`public`):**

| Method | Parameter | Keterangan |
|---|---|---|
| `tampilInfo()` | — | Menampilkan data dasar lapangan |
| `hitungTotalBiaya(int jamSewa)` | 1 | Hitung biaya tanpa diskon |
| `hitungTotalBiaya(int jamSewa, double diskonPersen)` | 2 | Hitung biaya dengan diskon |

---

### `LapanganIndoor.java` — Subclass

**Property tambahan (`private`):**

| Property | Tipe Data | Keterangan |
|---|---|---|
| `tersediaAC` | `boolean` | Ketersediaan AC di lapangan |

**Constructor:**
```java
LapanganIndoor(int id, String nama, String lokasi, int harga, String status, boolean tersediaAC)
```

**Getter:** `isTersediaAC()` | **Setter:** `setTersediaAC(boolean tersediaAC)`

**Override:**
```java
@Override
public void tampilInfo() {
    super.tampilInfo(); // data dari superclass
    // tambah: Tipe Indoor + info AC
}
```

---

### `LapanganOutdoor.java` — Subclass

**Property tambahan (`private`):**

| Property | Tipe Data | Keterangan |
|---|---|---|
| `jenisSintetis` | `String` | Jenis rumput sintetis (Premium / Standard) |

**Constructor:**
```java
LapanganOutdoor(int id, String nama, String lokasi, int harga, String status, String jenisSintetis)
```

**Getter:** `getJenisSintetis()` | **Setter:** `setJenisSintetis(String jenisSintetis)` (validasi tidak boleh kosong)

**Override:**
```java
@Override
public void tampilInfo() {
    super.tampilInfo(); // data dari superclass
    // tambah: Tipe Outdoor + info jenis sintetis
}
```

---

### `SistemLapanganFutsal.java` — Class Utama

**Method CRUD:**

| Method | Menu | Keterangan |
|---|---|---|
| `createLapangan()` | 1 — Create | Pilih tipe (Indoor/Outdoor), input data, buat objek subclass |
| `readLapangan()` | 2 — Read | Tampilkan semua lapangan via `tampilInfo()` (overriding berjalan otomatis) |
| `updateLapangan()` | 3 — Update | Edit data via setter, cek tipe dengan `instanceof` |
| `deleteLapangan()` | 4 — Delete | Hapus lapangan by ID setelah konfirmasi |
| `hitungBiayaSewa()` | 5 — Hitung Biaya | Pilih tanpa/dengan diskon, memanggil overloading yang sesuai |
| `cariById(int id)` | *(helper)* | Cari lapangan berdasarkan ID |

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

**Contoh output Read (Overriding berjalan):**
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

**Contoh output Hitung Biaya tanpa diskon (Overloading 1):**
```
Rincian Sewa:
   Lapangan  : Lapangan A
   Lokasi    : Jl. Sudirman No. 1
   Durasi    : 2 jam
   Harga/Jam : Rp 150000
   TOTAL     : Rp 300000
```

**Contoh output Hitung Biaya dengan diskon (Overloading 2):**
```
Rincian Sewa:
   Lapangan       : Lapangan A
   Lokasi         : Jl. Sudirman No. 1
   Durasi         : 2 jam
   Harga/Jam      : Rp 150000
   Total Sebelum  : Rp 300000
   Diskon         : 10.0%
   TOTAL BAYAR    : Rp 270000
```
