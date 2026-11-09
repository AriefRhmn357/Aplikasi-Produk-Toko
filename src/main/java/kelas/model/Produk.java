/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas.model;

/**
 *
 * @author arief
 */
public class Produk {
    private String kode;
    private String nama;
    private String kategori;
    private double harga;
    private String uri;

    public Produk(String kode, String nama, String kategori, double harga, String uri) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong.");
        }
        
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga produk harus lebih dari nol.");
        }
        
        this.kode = kode;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.uri = uri;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public double getHarga() {
        return harga;
    }

    public String getUri() {
        return uri;
    }
    
    
    
}
