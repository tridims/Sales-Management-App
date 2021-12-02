/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectbasdat.Admin;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import projectbasdat.DatabaseTools;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;



/**
 *
 * @author tridi
 */
public class MainAdmin extends javax.swing.JFrame {

    DatabaseTools db = new DatabaseTools();
    ArrayList<String> daftarIdPelanggan, daftarIdKaryawan, daftarIdSupplier, 
            daftarIdCabang, daftarIdProduk, daftarIdSuppliedProduct;
    
    /**
     * Creates new form Main
     */
    public MainAdmin() {
        initComponents();
        refresh();
        
        // closing listener untuk menutup koneksi database ketika window MainAdmin di close
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                db.close();
            }
        });
        
        // listener ketika tabel daftar supplier di klik
        ListSelectionModel modelDaftarSupplier = tableDaftarSupplier.getSelectionModel();
        modelDaftarSupplier.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!modelDaftarSupplier.isSelectionEmpty()) {
                    String id = getIdSelectedSupplier();
                    populateTableDaftarProdukSupply(id);
                }
            }
        });
        
        // Listener ketika tabel daftar cabang di klik
        ListSelectionModel modelDaftarCabang = tableDaftarCabang.getSelectionModel();
        modelDaftarCabang.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!modelDaftarCabang.isSelectionEmpty()) {
                    String id = getIdSelectedCabang();
                    populateTableDaftarKaryawanCabang(id);
                }
            }
        });
        
        // Listener untuk tabel daftar produk
        ListSelectionModel modelTabelDaftarProduk = tableDaftarProduk.getSelectionModel();
        modelTabelDaftarProduk.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!modelTabelDaftarProduk.isSelectionEmpty()) {
                    String id = getIdSelectedProduct();
                    populateDetailProduk(id);
                }
            }
        });
        
        ListSelectionModel modelTableOrderMasuk = tableDaftarOrderMasuk.getSelectionModel();
        modelTableOrderMasuk.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!modelTableOrderMasuk.isSelectionEmpty()) {
                    String id = getIdSelectedOrderMasuk();
                    populateDetailOrderMasuk(id);
                }
            }
        });
    }
    
    public DatabaseTools getDatabaseTools() {
        return db;
    }
    
    private String getIdSelectedOrderMasuk() {
        ListSelectionModel model = tableDaftarOrderMasuk.getSelectionModel();
        if (!model.isSelectionEmpty()) {
            int index = model.getMinSelectionIndex();
            String id = daftarIdDaftarOrderMasuk.get(index);
            return id;
        }
        return null;
    }
    
    private String getIdSelectedProduct() {
        ListSelectionModel modelTabelDaftarProduk = tableDaftarProduk.getSelectionModel();
        if (!modelTabelDaftarProduk.isSelectionEmpty()) {
            int index = modelTabelDaftarProduk.getMinSelectionIndex();
            String id = daftarIdProduk.get(index);
            return id;
        }
        return null;
    }

    private String getIdSelectedSupplier() {
        ListSelectionModel modelDaftarSupplier = tableDaftarSupplier.getSelectionModel();
        if (!modelDaftarSupplier.isSelectionEmpty()) {
            int index = modelDaftarSupplier.getMinSelectionIndex();
            String id = daftarIdSupplier.get(index);
            return id;
        }
        return null;
    }
    
    private String getIdSelectedCabang() {
        ListSelectionModel modelDaftarCabang = tableDaftarCabang.getSelectionModel();
        if (!modelDaftarCabang.isSelectionEmpty()) {
            int index = modelDaftarCabang.getMinSelectionIndex();
            String id = daftarIdCabang.get(index);
            return id;
        }
        return null;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tableKaryawanRajin = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableProdukPenjualanTertinggi = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableSupplierPalingMensupply = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableBarangPalingDibeli = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbBarangPalingBanyakDibeli = new javax.swing.JComboBox<>();
        buttonCariBarangPalingBanyakDibeliDenganSesuatu = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDaftarPelanggan = new javax.swing.JTable();
        buttonLihatDetailPelanggan = new javax.swing.JButton();
        buttonTambahPelanggan = new javax.swing.JButton();
        buttonHapusPelanggan = new javax.swing.JButton();
        buttonPrintDaftarPelanggan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        textPencarianPelanggan = new javax.swing.JTextField();
        buttonCariPelanggan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDaftarKaryawan = new javax.swing.JTable();
        buttonTambahKaryawan = new javax.swing.JButton();
        buttonHapusKaryawan = new javax.swing.JButton();
        buttonEditKaryawan = new javax.swing.JButton();
        textPencarianKaryawan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        buttonCariKaryawan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDaftarSupplier = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableDaftarProdukSupply = new javax.swing.JTable();
        buttonTambahSupplier = new javax.swing.JButton();
        buttonEditDataSupplier = new javax.swing.JButton();
        buttonHapusSupplier = new javax.swing.JButton();
        buttonTambahDataSupply = new javax.swing.JButton();
        buttonHapusDataSupply = new javax.swing.JButton();
        textPencarianSupplier = new javax.swing.JTextField();
        buttonCariSupplier = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableDaftarCabang = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableDaftarKaryawanCabang = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        buttonTambahCabang = new javax.swing.JButton();
        buttonHapusCabang = new javax.swing.JButton();
        textPencarianSupplier1 = new javax.swing.JTextField();
        buttonCariSupplier1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableDaftarProduk = new javax.swing.JTable();
        buttonTambahProduk = new javax.swing.JButton();
        buttonHapusProduk = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        textNutrisiProduk = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        textDeskripsiProduk = new javax.swing.JTextArea();
        comboBoxKategori = new javax.swing.JComboBox<>();
        textHargaProduk = new javax.swing.JTextField();
        textJumlahStokProduk = new javax.swing.JTextField();
        textNamaProduk = new javax.swing.JTextField();
        buttonEditDetailProduk = new javax.swing.JButton();
        textPencarianProduk = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        buttonCariProduk = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tableDaftarOrderMasuk = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        tableDaftarProdukOrder = new javax.swing.JTable();
        textTotalOrder = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbStatusOrder = new javax.swing.JComboBox<>();
        buttonSetStatusOrder = new javax.swing.JButton();
        buttonPrintRiwayat = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cbFilterStatusOrderMasuk = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplikasi Admin");

        tableKaryawanRajin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama", "Nama Cabang", "Jumlah Order"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(tableKaryawanRajin);

        tableProdukPenjualanTertinggi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kategori", "Nama Produk", "Jumlah Penjualan"
            }
        ));
        jScrollPane10.setViewportView(tableProdukPenjualanTertinggi);
        if (tableProdukPenjualanTertinggi.getColumnModel().getColumnCount() > 0) {
            tableProdukPenjualanTertinggi.getColumnModel().getColumn(2).setHeaderValue("Jumlah Penjualan");
        }

        tableSupplierPalingMensupply.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Supplier", "Jumlah Produk (kurun waktu terakhir)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tableSupplierPalingMensupply);

        tableBarangPalingDibeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama Produk", "Kategori", "Kuantitas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tableBarangPalingDibeli);

        jLabel11.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Informasi");

        jLabel15.setText("5 Produk Penjualan Tertinggi dari Setiap Kategori Produk");

        jLabel16.setText("5 Supplier yang paling banyak mensupply produk");

        jLabel17.setText("5 Karyawan yang paling banyak menangani order dalam sebulan terakhir");

        jLabel18.setText("3 Barang yang paling banyak dibeli dengan suatu produk");

        buttonCariBarangPalingBanyakDibeliDenganSesuatu.setText("Cari");
        buttonCariBarangPalingBanyakDibeliDenganSesuatu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariBarangPalingBanyakDibeliDenganSesuatuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
                        .addComponent(jScrollPane11)
                        .addComponent(jScrollPane13)
                        .addComponent(jScrollPane12)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cbBarangPalingBanyakDibeli, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCariBarangPalingBanyakDibeliDenganSesuatu)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBarangPalingBanyakDibeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCariBarangPalingBanyakDibeliDenganSesuatu))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        jScrollPane14.setViewportView(jPanel7);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 1241, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Gambaran", jPanel6);

        tableDaftarPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Email", "Jenis Kelamin", "Tanggal Lahir", "Nomor HP", "Alamat", "Jumlah Order"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableDaftarPelanggan);

        buttonLihatDetailPelanggan.setText("Lihat Detail Pelanggan");
        buttonLihatDetailPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLihatDetailPelangganActionPerformed(evt);
            }
        });

        buttonTambahPelanggan.setText("Tambah Pelanggan");
        buttonTambahPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahPelangganActionPerformed(evt);
            }
        });

        buttonHapusPelanggan.setText("Hapus Pelanggan");
        buttonHapusPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusPelangganActionPerformed(evt);
            }
        });

        buttonPrintDaftarPelanggan.setText("Print Daftar Lengkap");
        buttonPrintDaftarPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintDaftarPelangganActionPerformed(evt);
            }
        });

        jLabel12.setText("Pencarian :");

        buttonCariPelanggan.setText("Cari");
        buttonCariPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariPelangganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1145, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(buttonTambahPelanggan)
                            .addGap(18, 18, 18)
                            .addComponent(buttonLihatDetailPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(buttonHapusPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonPrintDaftarPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textPencarianPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(buttonCariPelanggan)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textPencarianPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLihatDetailPelanggan)
                    .addComponent(buttonTambahPelanggan)
                    .addComponent(buttonHapusPelanggan)
                    .addComponent(buttonPrintDaftarPelanggan))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pelanggan", jPanel1);

        tableDaftarKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Jenis Kelamin", "Tanggal Lahir", "Nomor HP", "Email", "Alamat", "Jabatan", "Cabang", "Gaji"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableDaftarKaryawan);

        buttonTambahKaryawan.setText("Tambah Karyawan");
        buttonTambahKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahKaryawanActionPerformed(evt);
            }
        });

        buttonHapusKaryawan.setText("Hapus Karyawan");
        buttonHapusKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusKaryawanActionPerformed(evt);
            }
        });

        buttonEditKaryawan.setText("Edit Karyawan");
        buttonEditKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditKaryawanActionPerformed(evt);
            }
        });

        jLabel13.setText("Pencarian :");

        buttonCariKaryawan.setText("Cari");
        buttonCariKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariKaryawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonTambahKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonHapusKaryawan)
                        .addGap(18, 18, 18)
                        .addComponent(buttonEditKaryawan))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textPencarianKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(buttonCariKaryawan))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCariKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textPencarianKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTambahKaryawan)
                    .addComponent(buttonHapusKaryawan)
                    .addComponent(buttonEditKaryawan))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Karyawan", jPanel2);

        tableDaftarSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Alamat", "Nomor HP", "Email", "Jumlah Produk"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableDaftarSupplier);

        tableDaftarProdukSupply.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Produk", "Jumlah Di Supply", "Tanggal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableDaftarProdukSupply);

        buttonTambahSupplier.setText("Tambah Supplier");
        buttonTambahSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahSupplierActionPerformed(evt);
            }
        });

        buttonEditDataSupplier.setText("Edit Data Supplier");
        buttonEditDataSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditDataSupplierActionPerformed(evt);
            }
        });

        buttonHapusSupplier.setText("Hapus Supplier");
        buttonHapusSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusSupplierActionPerformed(evt);
            }
        });

        buttonTambahDataSupply.setText("Tambah Data Supply");
        buttonTambahDataSupply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahDataSupplyActionPerformed(evt);
            }
        });

        buttonHapusDataSupply.setText("Hapus Data Supply");
        buttonHapusDataSupply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusDataSupplyActionPerformed(evt);
            }
        });

        buttonCariSupplier.setText("Cari");
        buttonCariSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariSupplierActionPerformed(evt);
            }
        });

        jLabel20.setText("Pencarian :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(buttonTambahSupplier)
                        .addGap(18, 18, 18)
                        .addComponent(buttonEditDataSupplier)
                        .addGap(18, 18, 18)
                        .addComponent(buttonHapusSupplier))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textPencarianSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(buttonCariSupplier))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(buttonTambahDataSupply)
                        .addGap(18, 18, 18)
                        .addComponent(buttonHapusDataSupply))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonCariSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textPencarianSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonTambahSupplier)
                        .addComponent(buttonEditDataSupplier)
                        .addComponent(buttonHapusSupplier))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonTambahDataSupply)
                        .addComponent(buttonHapusDataSupply)))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Supplier", jPanel3);

        tableDaftarCabang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Cabang", "Alamat", "Jumlah Karyawan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableDaftarCabang);

        tableDaftarKaryawanCabang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Jabatan", "Gaji"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tableDaftarKaryawanCabang);

        jLabel9.setText("Daftar Cabang");

        jLabel10.setText("Karyawan");

        buttonTambahCabang.setText("Tambah Cabang");
        buttonTambahCabang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahCabangActionPerformed(evt);
            }
        });

        buttonHapusCabang.setText("Hapus Cabang");
        buttonHapusCabang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusCabangActionPerformed(evt);
            }
        });

        buttonCariSupplier1.setText("Cari");
        buttonCariSupplier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariSupplier1ActionPerformed(evt);
            }
        });

        jLabel21.setText("Pencarian :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(buttonTambahCabang, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonHapusCabang, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textPencarianSupplier1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(buttonCariSupplier1)))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonCariSupplier1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textPencarianSupplier1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTambahCabang)
                    .addComponent(buttonHapusCabang))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cabang", jPanel5);

        tableDaftarProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Produk", "Jumlah Stok", "Harga", "Kategori"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tableDaftarProduk);
        if (tableDaftarProduk.getColumnModel().getColumnCount() > 0) {
            tableDaftarProduk.getColumnModel().getColumn(1).setResizable(false);
        }

        buttonTambahProduk.setText("Tambah Produk");
        buttonTambahProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahProdukActionPerformed(evt);
            }
        });

        buttonHapusProduk.setText("Hapus Produk");
        buttonHapusProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusProdukActionPerformed(evt);
            }
        });

        jLabel2.setText("Detail Produk");

        jLabel3.setText("Nama Produk");

        jLabel4.setText("Jumlah Stok");

        jLabel5.setText("Harga");

        jLabel6.setText("Kategori");

        jLabel7.setText("Deskripsi");

        jLabel8.setText("Nutrisi");

        textNutrisiProduk.setEditable(false);
        textNutrisiProduk.setColumns(20);
        textNutrisiProduk.setRows(5);
        jScrollPane9.setViewportView(textNutrisiProduk);

        textDeskripsiProduk.setEditable(false);
        textDeskripsiProduk.setColumns(20);
        textDeskripsiProduk.setRows(5);
        jScrollPane8.setViewportView(textDeskripsiProduk);

        textHargaProduk.setEditable(false);

        textJumlahStokProduk.setEditable(false);

        textNamaProduk.setEditable(false);

        buttonEditDetailProduk.setText("Edit");
        buttonEditDetailProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditDetailProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37)
                                    .addComponent(textJumlahStokProduk))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37)
                                    .addComponent(textHargaProduk))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(37, 37, 37)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane8)
                                        .addComponent(jScrollPane9)
                                        .addComponent(comboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37)
                                    .addComponent(textNamaProduk)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(buttonEditDetailProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textJumlahStokProduk)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textHargaProduk)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(buttonEditDetailProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel22.setText("Pencarian :");

        buttonCariProduk.setText("Cari");
        buttonCariProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(buttonTambahProduk)
                        .addGap(18, 18, 18)
                        .addComponent(buttonHapusProduk))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textPencarianProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(buttonCariProduk)))
                .addGap(35, 35, 35)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonCariProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textPencarianProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTambahProduk)
                    .addComponent(buttonHapusProduk))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Produk", jPanel4);

        tableDaftarOrderMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Pemesan", "tanggal", "status order", "Karyawan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(tableDaftarOrderMasuk);
        if (tableDaftarOrderMasuk.getColumnModel().getColumnCount() > 0) {
            tableDaftarOrderMasuk.getColumnModel().getColumn(0).setMinWidth(1);
            tableDaftarOrderMasuk.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableDaftarOrderMasuk.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        tableDaftarProdukOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Produk", "Kategori", "Kuantitas", "Harga", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(tableDaftarProdukOrder);

        textTotalOrder.setEditable(false);

        jLabel14.setText("Total");

        jLabel1.setText("Set Status Order");

        cbStatusOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Proses", "Selesai" }));

        buttonSetStatusOrder.setText("Set");
        buttonSetStatusOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSetStatusOrderActionPerformed(evt);
            }
        });

        buttonPrintRiwayat.setText("Print Order");

        jLabel19.setText("Tampilkan Order");

        cbFilterStatusOrderMasuk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Proses", "Selesai" }));
        cbFilterStatusOrderMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFilterStatusOrderMasukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cbStatusOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSetStatusOrder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonPrintRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(45, 45, 45)
                                .addComponent(cbFilterStatusOrderMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textTotalOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFilterStatusOrderMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTotalOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel1))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbStatusOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSetStatusOrder)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(buttonPrintRiwayat)))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Order Masuk", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1247, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTambahCabangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahCabangActionPerformed
        TambahCabang tc = new TambahCabang(this);
        tc.setVisible(true);
    }//GEN-LAST:event_buttonTambahCabangActionPerformed

    private void buttonHapusCabangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusCabangActionPerformed
        try {
            int index = tableDaftarCabang.getSelectionModel().getMinSelectionIndex();
            String id = daftarIdCabang.get(index);
            
            db.runUpdateQuery(String.format("delete_cabang %s", id));
            refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus");
        }
    }//GEN-LAST:event_buttonHapusCabangActionPerformed

    private void buttonTambahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahSupplierActionPerformed
        TambahSupplier ts = new TambahSupplier(this);
        ts.setVisible(true);
    }//GEN-LAST:event_buttonTambahSupplierActionPerformed

    private void buttonEditDataSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditDataSupplierActionPerformed
        int index = tableDaftarSupplier.getSelectionModel().getMinSelectionIndex();
        String id = daftarIdSupplier.get(index);
        EditSupplier es = new EditSupplier(this, id);
        es.setVisible(true);
    }//GEN-LAST:event_buttonEditDataSupplierActionPerformed

    private void buttonHapusSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusSupplierActionPerformed
        int index = tableDaftarSupplier.getSelectionModel().getMinSelectionIndex();
        String id = daftarIdSupplier.get(index);
        
        try {
            String query = String.format("exec delete_supplier %s", id);
            db.runUpdateQuery(query);
            refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus");
        }
    }//GEN-LAST:event_buttonHapusSupplierActionPerformed

    private void buttonTambahDataSupplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahDataSupplyActionPerformed
        String id = daftarIdSupplier.get(tableDaftarSupplier.getSelectionModel().getMinSelectionIndex());
        TambahDataSupply ts = new TambahDataSupply(this, id);
        ts.setVisible(true);
    }//GEN-LAST:event_buttonTambahDataSupplyActionPerformed

    private void buttonHapusDataSupplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusDataSupplyActionPerformed
        String id = daftarIdSuppliedProduct.get(tableDaftarProdukSupply.getSelectionModel().getMinSelectionIndex());
        
        try {
            String query = String.format("exec delete_supplied_product %s", id);
            db.runUpdateQuery(query);
            refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Saat menghapus data");
        }
    }//GEN-LAST:event_buttonHapusDataSupplyActionPerformed

    private void buttonTambahKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahKaryawanActionPerformed
        TambahKaryawan tk = new TambahKaryawan(this);
        tk.setVisible(true);
    }//GEN-LAST:event_buttonTambahKaryawanActionPerformed

    private void buttonHapusKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusKaryawanActionPerformed
        String id = getIdSelectedKaryawanInTable();
        
        if (!(id==null)) {
            try {
                String query = String.format("exec delete_karyawan %s", id);
                db.runUpdateQuery(query);
                refresh();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error menghapus");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_buttonHapusKaryawanActionPerformed

    private void buttonEditKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditKaryawanActionPerformed
        String id = getIdSelectedKaryawanInTable();
        
        if (id != null) {
            EditKaryawan ek = new EditKaryawan(this, id);
            ek.setVisible(true);
        }
    }//GEN-LAST:event_buttonEditKaryawanActionPerformed

    private void buttonLihatDetailPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLihatDetailPelangganActionPerformed
        String idPelanggan = getIdSelectedPelangganInTable();
        
        if (idPelanggan == null) return;
        
        Pelanggan p = new Pelanggan(this, idPelanggan);
        p.setVisible(true);
    }//GEN-LAST:event_buttonLihatDetailPelangganActionPerformed

    private void buttonHapusPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusPelangganActionPerformed
        String id = getIdSelectedPelangganInTable();
        
        if (id == null) return;
        
        try {
            String query = String.format("exec delete_customer %s", id);
            db.runUpdateQuery(query);
            refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tidak bisa dihapus");
        }
    }//GEN-LAST:event_buttonHapusPelangganActionPerformed

    private void buttonTambahPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahPelangganActionPerformed
        TambahPelanggan tp = new TambahPelanggan(this);
        tp.setVisible(true);
    }//GEN-LAST:event_buttonTambahPelangganActionPerformed

    private void buttonPrintDaftarPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintDaftarPelangganActionPerformed
        try {
            JasperReport jasperReport = getJasperReport("src/projectbasdat/Jasper/Daftar Pelanggan.jrxml");
            
            Map<String, Object> parameters = getParameters();
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, db.getConnection());
            
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonPrintDaftarPelangganActionPerformed

    private void buttonCariPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariPelangganActionPerformed
        String kataKunci = textPencarianPelanggan.getText();
        
        if (!kataKunci.equals("")) {
            String query = String.format("exec cari_pelanggan '%s'", kataKunci);
            populateTableDaftarPelanggan(query);
        } else {
            populateTableDaftarPelanggan();
        }
    }//GEN-LAST:event_buttonCariPelangganActionPerformed

    private void buttonCariKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariKaryawanActionPerformed
        String kataKunci = textPencarianKaryawan.getText();
        
        if (!kataKunci.equals("")) {
            String query = String.format("exec cari_karyawan '%s'", kataKunci);
            populateTableDaftarKaryawan(query);
        } else {
            populateTableDaftarKaryawan();
        }
    }//GEN-LAST:event_buttonCariKaryawanActionPerformed

    private void buttonCariSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariSupplierActionPerformed
        String kataKunci = textPencarianSupplier.getText();
        
        if (!kataKunci.equals("")) {
            String query = String.format("exec cari_supplier '%s'", kataKunci);
            populateTableSupplier(query);

        } else {
            populateTableSupplier();
        }
    }//GEN-LAST:event_buttonCariSupplierActionPerformed

    private void buttonCariSupplier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariSupplier1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCariSupplier1ActionPerformed

    private void buttonCariBarangPalingBanyakDibeliDenganSesuatuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariBarangPalingBanyakDibeliDenganSesuatuActionPerformed
        populateTableDaftarProdukPalingDibeliDengan(
                daftarIdCBPilihanBarang.get(cbBarangPalingBanyakDibeli.getSelectedIndex()));
    }//GEN-LAST:event_buttonCariBarangPalingBanyakDibeliDenganSesuatuActionPerformed

    private void cbFilterStatusOrderMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFilterStatusOrderMasukActionPerformed
        populateTableDaftarOrderMasuk();
        populateDetailOrderMasuk(null);
        fillTotal(null);
    }//GEN-LAST:event_cbFilterStatusOrderMasukActionPerformed

    private void buttonSetStatusOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSetStatusOrderActionPerformed
        String id = getIdSelectedOrderMasuk();
        
        if (id == null) return;
        
        int status = cbStatusOrder.getSelectedIndex();
        String query = String.format("exec set_status_order %s, %s", id, status);
        System.out.println("Query Update : " + query);
        
        try {
            db.runUpdateQuery(query);
            JOptionPane.showMessageDialog(null, "Sukses");
            refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengupdate");
        }
    }//GEN-LAST:event_buttonSetStatusOrderActionPerformed

    private void buttonEditDetailProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditDetailProdukActionPerformed

        if (buttonEditDetailProduk.getText().equals("Edit")) {
            textNamaProduk.setEditable(true);
            textJumlahStokProduk.setEditable(true);
            textHargaProduk.setEditable(true);
            textDeskripsiProduk.setEditable(true);
            textNutrisiProduk.setEditable(true);
            comboBoxKategori.setEditable(true);

            buttonEditDetailProduk.setText("Simpan");
        } else {
            textNamaProduk.setEditable(false);
            textJumlahStokProduk.setEditable(false);
            textHargaProduk.setEditable(false);
            textDeskripsiProduk.setEditable(false);
            textNutrisiProduk.setEditable(false);
            comboBoxKategori.setEditable(false);

            // edit recordnya
            editRecordDetailProduct();

            buttonEditDetailProduk.setText("Edit");
            resetDetailProduk();

            populateTableDaftarProduk();
        }
    }//GEN-LAST:event_buttonEditDetailProdukActionPerformed

    private void buttonHapusProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusProdukActionPerformed
        int index = tableDaftarProduk.getSelectionModel().getMinSelectionIndex();
        String id = daftarIdProduk.get(index);

        try {
            db.runUpdateQuery(String.format("exec delete_product %s", id));
            refresh();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus: kemungkinan ada supplier atau order yang mereferensi produk ini");
        }
    }//GEN-LAST:event_buttonHapusProdukActionPerformed

    private void buttonTambahProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahProdukActionPerformed
        TambahProduk tp = new TambahProduk(this);
        tp.setVisible(true);
    }//GEN-LAST:event_buttonTambahProdukActionPerformed

    private void buttonCariProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariProdukActionPerformed
        String kataKunci = textPencarianProduk.getText();
        if (kataKunci.equals("")) {
            populateTableDaftarProduk();
            return;
        }
        
        String query = String.format("exec cari_produk %s", kataKunci);
        populateTableDaftarProduk(query);
    }//GEN-LAST:event_buttonCariProdukActionPerformed

    private static JasperReport getJasperReport(String uri) throws FileNotFoundException, JRException {
        File template = Paths.get(uri).toFile();
        return JasperCompileManager.compileReport(template.getAbsolutePath());
    }
    
    private static Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("created By", "Dimas");
        return parameters;
    }
    
    private String getIdSelectedPelangganInTable() {
        ListSelectionModel model = tableDaftarPelanggan.getSelectionModel();
        
        if (!model.isSelectionEmpty()) {
            int index = model.getMinSelectionIndex();
            String id = daftarIdPelanggan.get(index);
            return id;
        }
        return null;
    }
    
    private String getIdSelectedKaryawanInTable() {
        ListSelectionModel model = tableDaftarKaryawan.getSelectionModel();
        
        if (!model.isSelectionEmpty()) {
            int index = model.getMinSelectionIndex();
            String id = daftarIdKaryawan.get(index);
            return id;
        }
        
        return null;
    }
    
    private void resetDetailProduk() {
        textNamaProduk.setText("");
        textJumlahStokProduk.setText("");
        textHargaProduk.setText("");
        textDeskripsiProduk.setText("");
        textNutrisiProduk.setText("");
        comboBoxKategori.setSelectedItem(null);
    }
    
    private void editRecordDetailProduct() {
        String nama = textNamaProduk.getText();
        String jumlah = textJumlahStokProduk.getText();
        String harga = textHargaProduk.getText();
        String deskripsi = textDeskripsiProduk.getText();
        String nutrisi = textNutrisiProduk.getText();
        String kategori = comboBoxKategori.getSelectedItem().toString();
        
        int index = tableDaftarProduk.getSelectionModel().getMinSelectionIndex();
        String id = daftarIdProduk.get(index);
        
        try {
            String query = String.format("exec update_detail_product\n" +
            "%s, '%s', %s, %s, '%s', '%s', '%s'", id, nama, jumlah, harga, deskripsi, nutrisi, kategori);
            db.runUpdateQuery(query);
            
            JOptionPane.showMessageDialog(null, "Berhasil");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengupdate");
        }
    }
    
    public void refresh() {
        populateTableDaftarPelanggan();
        populateTableDaftarKaryawan();
        populateTableSupplier();
        populateTableCabang();
        populateTableDaftarProduk();
        populateTableKaryawanTerajin();
        
        resetDetailProduk();
        populateTableDaftarProdukSupply(null);
        
        populateCBPilihanBarang();
        populateTableDaftarProdukPalingDibeliDengan(
                daftarIdCBPilihanBarang.get(cbBarangPalingBanyakDibeli.getSelectedIndex()));
        populateTableDaftarOrderMasuk();
        
        populateDetailOrderMasuk(null);
        fillTotal(null);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainAdmin().setVisible(true);
            }
        });
    }
    
    private void populateTableDaftarPelanggan() {
        String query = "exec get_data_pelanggan";
        populateTableDaftarPelanggan(query);
    }
    
    private void populateTableDaftarPelanggan(String query) {

        try {
            ResultSet rs = db.runQuery(query);
            
            DefaultTableModel tableModel = (DefaultTableModel)tableDaftarPelanggan.getModel();
            tableModel.setRowCount(0);
            daftarIdPelanggan = new ArrayList();
            
            while (rs.next()) {
                daftarIdPelanggan.add(rs.getString("id_pelanggan"));
                tableModel.addRow(new Object[] {
                    rs.getString("nama_pelanggan"),
                    rs.getString("email"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("tanggal_lahir"),
                    rs.getString("nomor_hp"),
                    rs.getString("alamat"),
                    rs.getString("jumlah_order")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error mengisi table daftar Pelanggan");
        }
    }
    
    private void populateTableDaftarKaryawan() {
        String query = "exec get_data_karyawan";
        populateTableDaftarKaryawan(query);
    }
    
    private void populateTableDaftarKaryawan(String query) {
        try {
            ResultSet rs = db.runQuery(query);
            
            DefaultTableModel tableModel = (DefaultTableModel)tableDaftarKaryawan.getModel();
            tableModel.setRowCount(0);
            daftarIdKaryawan = new ArrayList();
            
            while (rs.next()) {
                daftarIdKaryawan.add(rs.getString("id_karyawan"));
                tableModel.addRow(new Object[] {
                    rs.getString("nama"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("tanggal_lahir"),
                    rs.getString("nomor_hp"),
                    rs.getString("email"),
                    rs.getString("alamat"),
                    rs.getString("jabatan"),
                    rs.getString("nama_cabang"),
                    rs.getString("gaji")
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error mengisi table daftar karyawan");
        }
    }
    
    private void populateTableSupplier() {
        String query = "exec get_data_supplier";
        populateTableSupplier(query);
    }
    
    private void populateTableSupplier(String query) {
        try {
            ResultSet rs = db.runQuery(query);
            DefaultTableModel tableModel = (DefaultTableModel)tableDaftarSupplier.getModel();
            tableModel.setRowCount(0);
            daftarIdSupplier = new ArrayList();
            
            while (rs.next()) {
                daftarIdSupplier.add(rs.getString("id_supplier"));
                tableModel.addRow(new Object[] {
                    rs.getString("nama_supplier"),
                    rs.getString("alamat"),
                    rs.getString("nomor_hp"),
                    rs.getString("email"),
                    rs.getString("jumlah_produk")
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error mengisi table daftar Supplier");
        }
    }
    
    private void populateTableDaftarProdukSupply(String id) {
        DefaultTableModel tableModel = (DefaultTableModel)tableDaftarProdukSupply.getModel();
        tableModel.setRowCount(0);
        
        if (id==null) return;
        
        try {
            String query = String.format("exec get_supplied_product %s", id);
            ResultSet rs = db.runQuery(query);
            daftarIdSuppliedProduct = new ArrayList();
            
            while (rs.next()) {
                daftarIdSuppliedProduct.add(rs.getString("id"));
                tableModel.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getString("jumlah_produk"),
                    rs.getString("tanggal")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error saat mengisi daftar produk yang disupply");
        }
    }
    
    private void populateTableDaftarKaryawanCabang(String id) {
        try {
            String query = String.format("exec get_karyawan_at_cabang %s", id);
            ResultSet rs = db.runQuery(query);
            DefaultTableModel tableModel = (DefaultTableModel)tableDaftarKaryawanCabang.getModel();
            tableModel.setRowCount(0);
            
            while (rs.next()) {
                tableModel.addRow(new Object[] {
                    rs.getString("nama"),
                    rs.getString("jabatan"),
                    rs.getString("gaji")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error saat mengisi daftar produk yang disupply");
        }
    }
    
    private void populateTableCabang() {
        try {
            String query = "exec get_data_cabang";
            ResultSet rs = db.runQuery(query);
            DefaultTableModel tableModel = (DefaultTableModel)tableDaftarCabang.getModel();
            tableModel.setRowCount(0);
            daftarIdCabang = new ArrayList();
            
            while (rs.next()) {
                daftarIdCabang.add(rs.getString("id_cabang"));
                tableModel.addRow(new Object[] {
                    rs.getString("nama_cabang"),
                    rs.getString("alamat_cabang"),
                    rs.getString("jumlah_karyawan")
                });
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error mengisi table daftar Cabang");
        }
    }
    
    private void populateTableDaftarProduk() {
        String query = "exec get_data_tabel_produk";
        populateTableDaftarProduk(query);
    }
    
    private void populateTableDaftarProduk(String query) {
        try {
            System.out.println("query populate table daftar produk = " + query);
            ResultSet rs = db.runQuery(query);
            DefaultTableModel tableModel = (DefaultTableModel)tableDaftarProduk.getModel();
            
            // reset row count and daftar id
            tableModel.setRowCount(0);
            daftarIdProduk = new ArrayList();
            
            while (rs.next()) {
                daftarIdProduk.add(rs.getString("product_id"));
                tableModel.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getString("jumlah_stok"),
                    rs.getString("harga_satuan"),
                    rs.getString("kategori")
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error mengisi tabel daftar produk!");
        }
    }
    
    private void populateDetailProduk(String id) {
        try {
            String query = String.format("exec get_detail_produk %s", id);
            ResultSet rs = db.runQuery(query);
            populateComboBoxKategori();
            
            rs.next();
            textNamaProduk.setText(rs.getString("nama_produk"));
            textJumlahStokProduk.setText(rs.getString("jumlah_stok"));
            textHargaProduk.setText(rs.getString("harga_satuan"));
            textDeskripsiProduk.setText(rs.getString("deskripsi"));
            textNutrisiProduk.setText(rs.getString("nutrition_facts"));
            comboBoxKategori.setSelectedItem(rs.getString("kategori"));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error mendapat data");
            e.printStackTrace();
        }
    }
    
    private void populateComboBoxKategori() {
        try {
            comboBoxKategori.removeAllItems();
            ResultSet rs = db.runQuery("select * from kategori");
            
            while (rs.next()) {
                comboBoxKategori.addItem(rs.getString("nama_kategori"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengisi data CB Kategori");
        }
    }
    
    private void populateTableKaryawanTerajin() {
        // Tabel ketiga di bagian informasi
        try {
            String query = "exec karyawan_teraktif_sebulan_terakhir";
            ResultSet rs = db.runQuery(query);
            
            DefaultTableModel tableModel = (DefaultTableModel) tableKaryawanRajin.getModel();
            tableModel.setRowCount(0);
            
            while (rs.next()) {
                tableModel.addRow(new Object[] {
                    rs.getString("nama"),
                    rs.getString("nama_cabang"),
                    rs.getString("jumlah_order")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error mendapatkan data karyawan terajin");
        }
    }
    
    private void populateTableProdukPenjualanTertinggiKategori() {
        // Tabel Pertama di Bagian informasi
        // TODO
    }
    
    private void populateTableSupplierPalingBanyakSupplyProduk() {
        // Tabel kedua di bagian informasi
        // TODO
    }
    
    private ArrayList<String> daftarIdCBPilihanBarang;
    private void populateCBPilihanBarang() {
        // Combo box di Halaman Informasi, untuk memilih produk yang ingin dicari
        daftarIdCBPilihanBarang = new ArrayList();
        String query = "select product_id, nama_produk from produk";
        cbBarangPalingBanyakDibeli.removeAllItems();
        
        try {
            ResultSet rs = db.runQuery(query);
            
            while (rs.next()) {
                daftarIdCBPilihanBarang.add(rs.getString("product_id"));
                cbBarangPalingBanyakDibeli.addItem(rs.getString("nama_produk"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan data produk : CB-Informasi-Tabel terakhir");
        }
    }
    
    private void populateTableDaftarProdukPalingDibeliDengan(String id) {
        // tabel yang menampilkan produk yang paling banyak dibeli dengan suatu barang
        String query = String.format("exec tigaTertinggiCampur %s", id);
        
        try {
            ResultSet rs = db.runQuery(query);
            
            DefaultTableModel model = (DefaultTableModel)tableBarangPalingDibeli.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getString("kategori"),
                    rs.getString("kuantitas")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan data : 3 brang yang paling banyak dibeli");
        }
    }
    
    ArrayList<String> daftarIdDaftarOrderMasuk;
    private void populateTableDaftarOrderMasuk() {
        int pilihan = cbFilterStatusOrderMasuk.getSelectedIndex();
        daftarIdDaftarOrderMasuk = new ArrayList();
        
        String query = "";
        switch (pilihan) {
            case 0 -> {
                // Semua
                query = "exec get_data_order_masuk";
            }
            case 1 -> {
                // Proses
                query = "exec get_data_order_masuk_belum_selesai";
            }
            case 2 -> {
                // selesai
                query = "exec get_data_order_masuk_selesai";
            }
        }
        
        try {
            ResultSet rs = db.runQuery(query);
            
            DefaultTableModel model = (DefaultTableModel)tableDaftarOrderMasuk.getModel();
            model.setRowCount(0);
            
            int count = 1;
            while (rs.next()) {
                daftarIdDaftarOrderMasuk.add(rs.getString("order_id"));
                
                model.addRow(new Object[] {
                    count++,
                    rs.getString("nama_pelanggan"),
                    rs.getString("tanggal_kirim"),
                    (rs.getString("status_order").equals("0") ? "Proses":"Selesai"),
                    rs.getString("nama")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error mendapatkan data daftar order masuk");
        }
    }
    
    private void populateDetailOrderMasuk(String id) {
        DefaultTableModel model = (DefaultTableModel) tableDaftarProdukOrder.getModel();
        model.setRowCount(0);
        
        if (id == null) {
            return;
        }
        
        String query = String.format("get_list_product_from_order %s", id);
        
        try {
            ResultSet rs = db.runQuery(query);
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getString("kategori"),
                    rs.getString("kuantitas"),
                    rs.getString("harga_product"),
                    rs.getString("subtotal")
                });
            }
            fillTotal(id);
            
        } catch (Exception e) {
            
        }
    }
    
    private void fillTotal(String orderId) {
        if (orderId == null) {
            textTotalOrder.setText("");
            return;
        }
        
        String query = String.format("select * from total where order_id = %s", orderId);
        try {
            ResultSet rs = db.runQuery(query);
            
            while (rs.next()) {
                textTotalOrder.setText(rs.getString("total"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan total");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCariBarangPalingBanyakDibeliDenganSesuatu;
    private javax.swing.JButton buttonCariKaryawan;
    private javax.swing.JButton buttonCariPelanggan;
    private javax.swing.JButton buttonCariProduk;
    private javax.swing.JButton buttonCariSupplier;
    private javax.swing.JButton buttonCariSupplier1;
    private javax.swing.JButton buttonEditDataSupplier;
    private javax.swing.JButton buttonEditDetailProduk;
    private javax.swing.JButton buttonEditKaryawan;
    private javax.swing.JButton buttonHapusCabang;
    private javax.swing.JButton buttonHapusDataSupply;
    private javax.swing.JButton buttonHapusKaryawan;
    private javax.swing.JButton buttonHapusPelanggan;
    private javax.swing.JButton buttonHapusProduk;
    private javax.swing.JButton buttonHapusSupplier;
    private javax.swing.JButton buttonLihatDetailPelanggan;
    private javax.swing.JButton buttonPrintDaftarPelanggan;
    private javax.swing.JButton buttonPrintRiwayat;
    private javax.swing.JButton buttonSetStatusOrder;
    private javax.swing.JButton buttonTambahCabang;
    private javax.swing.JButton buttonTambahDataSupply;
    private javax.swing.JButton buttonTambahKaryawan;
    private javax.swing.JButton buttonTambahPelanggan;
    private javax.swing.JButton buttonTambahProduk;
    private javax.swing.JButton buttonTambahSupplier;
    private javax.swing.JComboBox<String> cbBarangPalingBanyakDibeli;
    private javax.swing.JComboBox<String> cbFilterStatusOrderMasuk;
    private javax.swing.JComboBox<String> cbStatusOrder;
    private javax.swing.JComboBox<String> comboBoxKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tableBarangPalingDibeli;
    private javax.swing.JTable tableDaftarCabang;
    private javax.swing.JTable tableDaftarKaryawan;
    private javax.swing.JTable tableDaftarKaryawanCabang;
    private javax.swing.JTable tableDaftarOrderMasuk;
    private javax.swing.JTable tableDaftarPelanggan;
    private javax.swing.JTable tableDaftarProduk;
    private javax.swing.JTable tableDaftarProdukOrder;
    private javax.swing.JTable tableDaftarProdukSupply;
    private javax.swing.JTable tableDaftarSupplier;
    private javax.swing.JTable tableKaryawanRajin;
    private javax.swing.JTable tableProdukPenjualanTertinggi;
    private javax.swing.JTable tableSupplierPalingMensupply;
    private javax.swing.JTextArea textDeskripsiProduk;
    private javax.swing.JTextField textHargaProduk;
    private javax.swing.JTextField textJumlahStokProduk;
    private javax.swing.JTextField textNamaProduk;
    private javax.swing.JTextArea textNutrisiProduk;
    private javax.swing.JTextField textPencarianKaryawan;
    private javax.swing.JTextField textPencarianPelanggan;
    private javax.swing.JTextField textPencarianProduk;
    private javax.swing.JTextField textPencarianSupplier;
    private javax.swing.JTextField textPencarianSupplier1;
    private javax.swing.JTextField textTotalOrder;
    // End of variables declaration//GEN-END:variables
}
