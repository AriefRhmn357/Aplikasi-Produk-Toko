package kelas.controller;

import java.io.IOException;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class DaftarController {

    @FXML
    private TableView<Produk> tabel;
    @FXML
    private TableColumn<Produk, String> kodeCol;
    @FXML
    private TableColumn<Produk, String> namaCol;
    @FXML
    private TableColumn<Produk, String> jenisCol;
    @FXML
    private TableColumn<Produk, Double> totalCol;
    @FXML
    private TextField cariField;
    @FXML
    private ComboBox<String> filterJenisBox;
    @FXML
    private CheckBox totalCheck;
    @FXML
    private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;
    private final ObservableList<Produk> dataTampilan
            = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // KOLOM KODE
        PropertyValueFactory<Produk, String> sumberKode
                = new PropertyValueFactory<Produk, String>("kode");

        kodeCol.setCellValueFactory(sumberKode);

        // KOLOM NAMA PEMBELI
        PropertyValueFactory<Produk, String> sumberNama
                = new PropertyValueFactory<Produk, String>("nama");

        namaCol.setCellValueFactory(sumberNama);

        // KOLOM JENIS TIKET
        PropertyValueFactory<Produk, String> sumberJenis
                = new PropertyValueFactory<Produk, String>("kategori");

        jenisCol.setCellValueFactory(sumberJenis);

        // KOLOM TOTAL
        PropertyValueFactory<Produk, Double> sumberTotal
                = new PropertyValueFactory<Produk, Double>("harga");

        totalCol.setCellValueFactory(sumberTotal);

        filterJenisBox.getItems().add("Semua");
        filterJenisBox.getItems().add("Makanan");
        filterJenisBox.getItems().add("Minuman");
        filterJenisBox.setValue("Semua");

        tabel.setItems(dataTampilan);
        tabel.setPlaceholder(new Label("Belum ada data yang sesuai."));
        ubahTampilanTotal();
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
        cari();
    }

    @FXML
    private void cari() {
        if (data == null) {
            return;
        }
        dataTampilan.clear();
        String kata = cariField.getText().trim().toLowerCase(Locale.ROOT);
        String jenisDipilih = filterJenisBox.getValue();

        for (Produk produk : data.getDaftar()) {
            String kode = produk.getKode().toLowerCase(Locale.ROOT);
            String nama = produk.getNama().toLowerCase(Locale.ROOT);
            boolean cocokKata = false;
            boolean cocokJenis = false;

            if (kode.contains(kata) || nama.contains(kata)) {
                cocokKata = true;
            }
            if ("Semua".equals(jenisDipilih)) {
                cocokJenis = true;
            } else if (produk.getKategori().equals(jenisDipilih)) {
                cocokJenis = true;
            }
            if (cocokKata && cocokJenis) {
                dataTampilan.add(produk);
            }
        }
        tabel.sort();
        pesanLabel.setText("Jumlah data yang tampil: " + dataTampilan.size());
    }

    @FXML
    private void resetPencarian() {
        cariField.clear();
        filterJenisBox.setValue("Semua");
        cari();
    }

    @FXML
    private void ubahTampilanTotal() {
        if (totalCheck.isSelected()) {
            totalCol.setVisible(true);
        } else {
            totalCol.setVisible(false);
        }
    }

    @FXML
    private void tambah() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            pesanLabel.setText("Form gagal dibuka.");
            e.printStackTrace();
        }
    }

    @FXML
    private void detail() {
        Produk produk = tabel.getSelectionModel().getSelectedItem();
        if (produk == null) {
            pesanLabel.setText("Pilih satu baris dahulu.");
            return;
        }
        try {
            navigator.bukaDetail(produk);
        } catch (IOException e) {
            pesanLabel.setText("Detail gagal dibuka.");
            e.printStackTrace();
        }
    }

    @FXML
    private void hapus() {
        Produk produk = tabel.getSelectionModel().getSelectedItem();
        if (produk == null) {
            pesanLabel.setText("Pilih satu baris dahulu.");
            return;
        }
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION,
                "Hapus " + produk.getKode() + "?",
                ButtonType.YES, ButtonType.NO);
        dialog.initOwner(tabel.getScene().getWindow());
        ButtonType jawaban = dialog.showAndWait().orElse(ButtonType.NO);
        if (jawaban == ButtonType.YES) {
            data.hapus(produk);
            cari();
        }
    }
}
