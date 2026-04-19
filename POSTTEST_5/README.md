# Sistem Manajemen Lapangan Futsal

| | |
|---|---|
| **Nama** | Muhammad Naufal Adi Brata Putra Suharizman Poerwo |
| **NIM** | 2409106049 |
| **Mata Kuliah** | Pemrograman Berorientasi Objek (PBO) |
| **Kelas** | B1-24 |

---

Program berbasis **Java** untuk mengelola data lapangan futsal menggunakan konsep **Object-Oriented Programming (OOP)**. Program ini menerapkan **Encapsulation**, **Inheritance**, **Polymorphism**, dan **Abstraction** (Abstract Class + Interface). Program mendukung operasi **CRUD** (Create, Read, Update, Delete) dengan penyimpanan data menggunakan `ArrayList` dan berjalan secara berulang hingga pengguna memilih menu keluar.

---

## Struktur File

```
project/
├── Sewable.java               # Interface — kontrak method sewa
├── LapanganFutsal.java        # Abstract Class — superclass utama
├── LapanganIndoor.java        # Subclass — extends + implements
├── LapanganOutdoor.java       # Subclass — extends + implements
├── SistemLapanganFutsal.java  # Class utama (main program + CRUD)
└── README.md
```

---

## Penerapan Abstraction

### 1. Abstract Class — `LapanganFutsal`

`LapanganFutsal` dideklarasikan sebagai abstract class menggunakan keyword `abstract`. Class ini **tidak bisa dibuat objeknya secara langsung** (`new LapanganFutsal()` akan menghasilkan error). Berfungsi sebagai template yang mendefinisikan property umum, concrete method, dan abstract method yang wajib diimplementasikan oleh setiap subclass.

```java
abstract class LapanganFutsal {
    // concrete method — sudah punya isi, langsung bisa diwariskan
    public void tampilInfoDasar() { ... }

    // abstract method — tanpa isi, wajib diimplementasikan subclass
    public abstract void tampilInfo();
    public abstract String getDetailTipe();
}
```

| Jenis | Method | Keterangan |
|---|---|---|
| Concrete method | `tampilInfoDasar()` | Menampilkan data umum lapangan, diwarisi subclass |
| Abstract method | `tampilInfo()` | Wajib diimplementasikan setiap subclass |
| Abstract method | `getDetailTipe()` | Wajib mengembalikan tipe lapangan (Indoor/Outdoor) |

### 2. Interface — `Sewable`

`Sewable` adalah interface yang menjadi **kontrak** bagi setiap lapangan yang bisa disewa. Dibuat dengan keyword `interface` dan diimplementasikan dengan keyword `implements`.

```java
interface Sewable {
    public int hitungTotalBiaya(int jamSewa);
    public int hitungTotalBiaya(int jamSewa, double diskonPersen);
}
```

| Kontrak | Parameter | Fungsi |
|---|---|---|
| `hitungTotalBiaya(int)` | 1 | Hitung total biaya sewa tanpa diskon |
| `hitungTotalBiaya(int, double)` | 2 | Hitung total biaya sewa dengan potongan diskon |

### Perbandingan Abstract Class vs Interface pada Program

| | `LapanganFutsal` (Abstract Class) | `Sewable` (Interface) |
|---|---|---|
| Jenis | Template hierarki | Kontrak kemampuan |
| Isi | Property, getter/setter, concrete method, abstract method | Method tanpa body |
| Keyword | `abstract class` | `interface` |
| Digunakan dengan | `extends` | `implements` |
| Bisa constructor | Ya | Tidak |

---

## Penerapan Inheritance

Tipe **Hierarchical Inheritance** — satu abstract class diturunkan ke dua subclass, dan keduanya sekaligus mengimplementasikan interface `Sewable`.

```
        LapanganFutsal (abstract)      Sewable (interface)
              /            \           /          \
    LapanganIndoor      LapanganOutdoor
  (extends + implements)  (extends + implements)
```

| Subclass | Deklarasi | Property Tambahan |
|---|---|---|
| `LapanganIndoor` | `extends LapanganFutsal implements Sewable` | `tersediaAC` |
| `LapanganOutdoor` | `extends LapanganFutsal implements Sewable` | `jenisSintetis` |

---

## Penerapan Polymorphism

### Method Overriding — Dynamic Polymorphism

Kedua subclass mengimplementasikan abstract method dari superclass dengan isi yang berbeda.

| Class | Method | Implementasi |
|---|---|---|
| `LapanganIndoor` | `tampilInfo()` | Info dasar + Tipe Indoor + AC |
| `LapanganIndoor` | `getDetailTipe()` | Mengembalikan `"Indoor"` |
| `LapanganOutdoor` | `tampilInfo()` | Info dasar + Tipe Outdoor + Sintetis |
| `LapanganOutdoor` | `getDetailTipe()` | Mengembalikan `"Outdoor"` |

### Method Overloading — Static Polymorphism

Kedua subclass mengimplementasikan dua versi `hitungTotalBiaya()` dari interface `Sewable` dengan parameter berbeda.

| Method | Parameter | Fungsi |
|---|---|---|
| `hitungTotalBiaya(int jamSewa)` | 1 | Hitung biaya tanpa diskon |
| `hitungTotalBiaya(int jamSewa, double diskonPersen)` | 2 | Hitung biaya dengan potongan diskon |

---

## Penerapan Encapsulation

Semua property di seluruh class dibuat `private` dan diakses melalui getter dan setter `public`.

| Access Modifier | Diterapkan pada | Dapat Diakses |
|---|---|---|
| `private` | Semua property di semua class | Hanya di dalam class masing-masing |
| `public` | Getter, setter, dan semua method | Dari mana saja |

---

## Detail Class

### `Sewable.java` — Interface

```java
interface Sewable {
    public int hitungTotalBiaya(int jamSewa);
    public int hitungTotalBiaya(int jamSewa, double diskonPersen);
}
```

---

### `LapanganFutsal.java` — Abstract Class

**Property (`private`):**

| Property | Tipe Data | Keterangan |
|---|---|---|
| `id` | `int` | ID unik lapangan |
| `nama` | `String` | Nama lapangan |
| `lokasi` | `String` | Lokasi (lantai) lapangan |
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

**Method:**

| Jenis | Method | Return | Keterangan |
|---|---|---|---|
| Concrete | `tampilInfoDasar()` | `void` | Tampilkan data umum, diwariskan ke subclass |
| Abstract | `tampilInfo()` | `void` | Wajib diimplementasikan subclass |
| Abstract | `getDetailTipe()` | `String` | Wajib mengembalikan tipe lapangan |

---

### `LapanganIndoor.java` — Subclass

**Deklarasi:** `class LapanganIndoor extends LapanganFutsal implements Sewable`

**Property tambahan (`private`):**

| Property | Tipe Data | Keterangan |
|---|---|---|
| `tersediaAC` | `boolean` | Ketersediaan AC di lapangan |

**Constructor:**
```java
LapanganIndoor(int id, String nama, String lokasi, int harga, String status, boolean tersediaAC)
```

**Getter:** `isTersediaAC()` | **Setter:** `setTersediaAC(boolean tersediaAC)`

**Implementasi abstract method dari `LapanganFutsal`:**
```java
@Override public void tampilInfo()       // tampil info + Tipe Indoor + AC
@Override public String getDetailTipe()  // return "Indoor"
```

**Implementasi kontrak dari interface `Sewable`:**
```java
@Override public int hitungTotalBiaya(int jamSewa)
@Override public int hitungTotalBiaya(int jamSewa, double diskonPersen)
```

---

### `LapanganOutdoor.java` — Subclass

**Deklarasi:** `class LapanganOutdoor extends LapanganFutsal implements Sewable`

**Property tambahan (`private`):**

| Property | Tipe Data | Keterangan |
|---|---|---|
| `jenisSintetis` | `String` | Jenis rumput sintetis (Premium / Standard) |

**Constructor:**
```java
LapanganOutdoor(int id, String nama, String lokasi, int harga, String status, String jenisSintetis)
```

**Getter:** `getJenisSintetis()` | **Setter:** `setJenisSintetis(String)` (validasi tidak boleh kosong)

**Implementasi abstract method dari `LapanganFutsal`:**
```java
@Override public void tampilInfo()       // tampil info + Tipe Outdoor + Sintetis
@Override public String getDetailTipe()  // return "Outdoor"
```

**Implementasi kontrak dari interface `Sewable`:**
```java
@Override public int hitungTotalBiaya(int jamSewa)
@Override public int hitungTotalBiaya(int jamSewa, double diskonPersen)
```

---

### `SistemLapanganFutsal.java` — Class Utama

**Method CRUD:**

| Method | Menu | Keterangan |
|---|---|---|
| `createLapangan()` | 1 — Create | Pilih tipe, input data, buat objek subclass |
| `readLapangan()` | 2 — Read | Tampilkan semua lapangan via `tampilInfo()` |
| `updateLapangan()` | 3 — Update | Edit data via setter, cek tipe dengan `instanceof` |
| `deleteLapangan()` | 4 — Delete | Hapus lapangan by ID setelah konfirmasi |
| `hitungBiayaSewa()` | 5 — Hitung Biaya | Cast ke `Sewable`, panggil overloading sesuai pilihan |
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

**Contoh output Read:**
```
--------------------------------------
ID      : 1
Nama    : Lapangan A
Lokasi  : Lantai 1 (lantai)
Harga   : Rp 150000 / jam
Status  : Tersedia
Tipe    : Indoor
AC      : Ada
--------------------------------------
--------------------------------------
ID      : 2
Nama    : Lapangan B
Lokasi  : Lantai 2 (lantai)
Harga   : Rp 100000 / jam
Status  : Tersedia
Tipe    : Outdoor
Sintetis: Premium
--------------------------------------
```

**Contoh output Hitung Biaya dengan diskon:**
```
Rincian Sewa:
   Lapangan       : Lapangan A
   Lokasi         : Lantai 1 (lantai)
   Tipe           : Indoor
   Durasi         : 2 jam
   Harga/Jam      : Rp 150000
   Total Sebelum  : Rp 300000
   Diskon         : 10.0%
   TOTAL BAYAR    : Rp 270000
```
