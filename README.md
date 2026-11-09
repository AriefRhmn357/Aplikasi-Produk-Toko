# Aplikasi Daftar Produk Toko (JavaFX)

Aplikasi desktop berbasis JavaFX untuk pencatatan dan pengelolaan daftar produk toko (kategori Makanan dan Minuman). Proyek ini dikembangkan dengan arsitektur MVC (Model-View-Controller) serta memanfaatkan `ObservableList` untuk penyimpanan data selama aplikasi berjalan.

## Tangkapan Layar Aplikasi

| Halaman Daftar Produk | Form Tambah Produk |
| :---: | :---: |
| <img width="1354" height="1067" alt="image" src="https://github.com/user-attachments/assets/5ee68761-f6a0-430f-8b57-bbe9d1e77ecb" /> | <img width="1354" height="1067" alt="image" src="https://github.com/user-attachments/assets/ed8c5c2b-3cce-479b-a0ad-5045b3d0eb48" /> |

| Halaman Detail Produk |
| :---: |
| <img width="1354" height="1067" alt="image" src="https://github.com/user-attachments/assets/1111f3fc-a970-497d-99b6-40958f50a61f" /> |

## Fitur Aplikasi

1. **Form Tambah Produk**
   * Pengisian data produk: Kode otomatis (`PR001`, `PR002`, dst.), Nama Produk, Kategori (Makanan/Minuman), Harga Produk, dan lampiran gambar produk lokal (PNG/JPEG).
   * Validasi input: Nama produk tidak boleh kosong dan harga harus berupa angka lebih dari nol.
2. **Tabel Daftar Produk**
   * Menampilkan kolom Kode, Nama Produk, Kategori, dan Harga Produk.
   * Opsi untuk menyembunyikan atau menampilkan kolom harga.
3. **Pencarian dan Filter**
   * Pencarian data berdasarkan nama atau kode produk melalui tombol Cari.
   * Filter tampilan berdasarkan kategori (Semua, Makanan, Minuman) serta tombol Reset pencarian.
4. **Halaman Detail Produk**
   * Menampilkan informasi lengkap dan gambar dari produk yang dipilih pada tabel melalui pengiriman objek `Produk` antar-controller.
5. **Hapus Produk**
   * Menghapus baris produk terpilih dengan dialog konfirmasi.

## Struktur Folder Proyek

```text
src/
└── main/
    ├── java/
    │   └── kelas/
    │       ├── MainApp.java
    │       ├── Navigator.java
    │       ├── controller/
    │       │   ├── DaftarController.java
    │       │   ├── DetailController.java
    │       │   └── FormController.java
    │       ├── data/
    │       │   └── DataProduk.java
    │       └── model/
    │           └── Produk.java
    └── resources/
        └── kelas/
            ├── daftar.fxml
            ├── detail.fxml
            ├── form.fxml
            └── styles/
                └── app.css
```

## Prasyarat Sistem

* JDK 21 atau versi yang kompatibel
* Apache Maven
* Apache NetBeans / IDE Java lainnya

## Cara Menjalankan Aplikasi

1. Clone repositori ini:
   ```bash
   git clone https://github.com/AriefRhmn357/Aplikasi-Produk-Toko.git
   cd Aplikasi-Produk-Toko
   ```
2. Jalankan melalui Maven:
   ```bash
   mvn clean javafx:run
   ```
   Atau buka folder proyek di Apache NetBeans, lalu klik **Run Project (F6)**.
