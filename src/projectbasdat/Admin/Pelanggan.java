/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectbasdat.Admin;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author tridi
 */
public class Pelanggan extends javax.swing.JFrame {

    String idPelanggan;
    MainAdmin parentForm;
    ArrayList<String> daftarIdOrder;
    /**
     * Creates new form Pelanggan
     */
    public Pelanggan() {
        initComponents();
    }
    
    public Pelanggan(MainAdmin parentForm, String idPelanggan) {
        initComponents();
        this.idPelanggan = idPelanggan;
        this.parentForm = parentForm;
        
        buttonBatalEdit.setVisible(false);
        
        refresh();
        parentForm.setVisible(false);
        
        // Listener untuk ketika window di close parentForm diperlihatkan kembali
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                parentForm.refresh();
                parentForm.setVisible(true);
            }
        });
        
        // Listener untuk saat tabel di produk di select
        ListSelectionModel model = tableDaftarOrder.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!model.isSelectionEmpty()) {
                    int index = model.getMinSelectionIndex();
                    String orderId = daftarIdOrder.get(index);
                    populateDaftarProdukOrder(orderId);
                }
            }
        });
    }
    
    private void refresh() {
        populateDataPelanggan();
        populateRiwayatPesanan();
        populateDaftarProdukOrder(null);
    }
    
    private void populateDaftarProdukOrder(String idOrder) {
        fillTotal(idOrder);
        
        DefaultTableModel tableModel = (DefaultTableModel)tableDaftarProdukOrder.getModel();
        tableModel.setRowCount(0);
        
        if (idOrder == null) return;
        
        String query = String.format("exec get_list_product_from_order %s", idOrder);

        try {
            ResultSet rs = parentForm.getDatabaseTools().runQuery(query);
            
            while (rs.next()) {
                tableModel.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getString("kategori"),
                    rs.getString("kuantitas"),
                    rs.getString("harga_product"),
                    rs.getString("subtotal")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal mendapatkan data produk dari order");
        }
    }
    
    private void fillTotal(String orderId) {
        if (orderId == null) {
            textTotal.setText("");
            return;
        }
        
        String query = String.format("select * from total where order_id = %s", orderId);
        try {
            ResultSet rs = parentForm.getDatabaseTools().runQuery(query);
            
            while (rs.next()) {
                textTotal.setText(rs.getString("total"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan total");
        }
    }
    
    private void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
    
    private void populateDataPelanggan() {
        String query = String.format("exec get_specific_pelanggan %s", this.idPelanggan);
        
        try {
            ResultSet rs = parentForm.getDatabaseTools().runQuery(query);
            
            while (rs.next()) {
                textNamaPelangganHeader.setText(rs.getString("nama_pelanggan"));
                textNama.setText(rs.getString("nama_pelanggan"));
                
                textAlamatEmailHeader.setText(rs.getString("email"));
                textEmail.setText(rs.getString("email"));
                
                textPassword.setText(rs.getString("password"));
                textTanggalLahir.setText(rs.getString("tanggal_lahir"));
                textNomorHp.setText(rs.getString("nomor_hp"));
                textNomorRumah.setText(rs.getString("nomor_rumah"));
                textDesaKecamatan.setText(rs.getString("desa_kecamatan"));
                textKabupatenKota.setText(rs.getString("kabupaten_kota"));
                textJalan.setText(rs.getString("jalan"));
                cbJenisKelamin.setSelectedItem(rs.getString("jenis_kelamin"));
                textKodePos.setText(rs.getString("kode_pos"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan data pelanggan");
        }
    }

    private void populateRiwayatPesanan() {
        String query = String.format("exec get_order_pelanggan %s", this.idPelanggan);
        
        try {
            ResultSet rs = parentForm.getDatabaseTools().runQuery(query);
            daftarIdOrder = new ArrayList();
            DefaultTableModel tableModel = (DefaultTableModel)tableDaftarOrder.getModel();
            tableModel.setRowCount(0);
            
            int count = 1;
            while (rs.next()) {
                daftarIdOrder.add(rs.getString("order_id"));
                tableModel.addRow(new Object[] {
                    count++,
                    rs.getString("tanggal_kirim"),
                    (rs.getString("status_order").equals("1") ? "Selesai":"Belum"),
                    rs.getString("karyawan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapat data riwayat pesanan");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textAlamatEmailHeader = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        textNama = new javax.swing.JTextField();
        textTanggalLahir = new javax.swing.JTextField();
        textNomorHp = new javax.swing.JTextField();
        textNomorRumah = new javax.swing.JTextField();
        textDesaKecamatan = new javax.swing.JTextField();
        textKabupatenKota = new javax.swing.JTextField();
        textJalan = new javax.swing.JTextField();
        textKodePos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textEmail = new javax.swing.JTextField();
        textPassword = new javax.swing.JTextField();
        buttonEditData = new javax.swing.JButton();
        cbJenisKelamin = new javax.swing.JComboBox<>();
        buttonBatalEdit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDaftarOrder = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDaftarProdukOrder = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        textTotal = new javax.swing.JTextField();
        buttonHapusRiwayat = new javax.swing.JButton();
        buttonPrintRiwayat = new javax.swing.JButton();
        textNamaPelangganHeader = new javax.swing.JTextField();
        buttonKembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Pelanggan");

        jLabel1.setText("Nama Pelanggan");

        jLabel2.setText("Alamat Email");

        textAlamatEmailHeader.setEditable(false);

        jLabel9.setText("Desa/Kecamatan");

        jLabel10.setText("Kabupaten/Kota");

        jLabel11.setText("Jalan");

        jLabel12.setText("Jenis Kelamin");

        jLabel13.setText("Kode Pos");

        textNama.setEditable(false);

        textTanggalLahir.setEditable(false);

        textNomorHp.setEditable(false);

        textNomorRumah.setEditable(false);

        textDesaKecamatan.setEditable(false);

        textKabupatenKota.setEditable(false);

        textJalan.setEditable(false);

        textKodePos.setEditable(false);

        jLabel5.setText("Nama");

        jLabel6.setText("Tanggal Lahir");

        jLabel7.setText("Nomor HP");

        jLabel8.setText("Nomor Rumah");

        jLabel15.setText("Email");

        jLabel16.setText("Password");

        textEmail.setEditable(false);

        textPassword.setEditable(false);

        buttonEditData.setText("Edit Data");
        buttonEditData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditDataActionPerformed(evt);
            }
        });

        cbJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "L", "P" }));

        buttonBatalEdit.setText("Batal");
        buttonBatalEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBatalEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(377, 377, 377)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonEditData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel12)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textKodePos)
                            .addComponent(textJalan)
                            .addComponent(textKabupatenKota)
                            .addComponent(textDesaKecamatan)
                            .addComponent(textNomorRumah)
                            .addComponent(textNama)
                            .addComponent(textTanggalLahir)
                            .addComponent(textNomorHp)
                            .addComponent(textEmail)
                            .addComponent(textPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(cbJenisKelamin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(buttonBatalEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(469, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textNomorHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textNomorRumah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textDesaKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(textKabupatenKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(textJalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(textKodePos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(buttonEditData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonBatalEdit)
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Data Pelanggan", jPanel1);

        tableDaftarOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nomor", "tanggal kirim", "status order", "karyawan"
            }
        ));
        jScrollPane1.setViewportView(tableDaftarOrder);

        tableDaftarProdukOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Produk", "Kategori", "Kuantitas", "Harga", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(tableDaftarProdukOrder);

        jLabel3.setText("Total");

        textTotal.setEditable(false);

        buttonHapusRiwayat.setText("Hapus Riwayat");
        buttonHapusRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusRiwayatActionPerformed(evt);
            }
        });

        buttonPrintRiwayat.setText("Print Riwayat Order");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHapusRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textTotal))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(buttonPrintRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonHapusRiwayat)
                    .addComponent(buttonPrintRiwayat))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Riwayat Pesanan", jPanel2);

        textNamaPelangganHeader.setEditable(false);

        buttonKembali.setText("Kembali");
        buttonKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textNamaPelangganHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textAlamatEmailHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(buttonKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textAlamatEmailHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNamaPelangganHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKembaliActionPerformed
        close();
    }//GEN-LAST:event_buttonKembaliActionPerformed

    private void buttonEditDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditDataActionPerformed
        if (buttonEditData.getText().equals("Edit Data")) {
            setDataEditMode(true);
            buttonEditData.setText("Simpan Perubahan");
            buttonBatalEdit.setVisible(true);

        } else {
            updateDataPelanggan();
            refresh();
            setDataEditMode(false);
            buttonEditData.setText("Edit Data");
            buttonBatalEdit.setVisible(false);
        }
    }//GEN-LAST:event_buttonEditDataActionPerformed

    private void buttonBatalEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalEditActionPerformed
        setDataEditMode(false);
        buttonEditData.setText("Edit Data");
        buttonBatalEdit.setVisible(false);
    }//GEN-LAST:event_buttonBatalEditActionPerformed

    private void buttonHapusRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusRiwayatActionPerformed
        String id = getSelectedRiwayatFromTable();
        
        String status = getOrderStatus(id);
        
        if (id == null) return;
        if (status.equals("1") || status == null) {
            JOptionPane.showMessageDialog(null, "Tidak bisa dihapus");
            return;
        }
        
        try {
            String query = String.format("exec delete_order %s", id);
            parentForm.getDatabaseTools().runUpdateQuery(query);
            refresh();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menghapus");
        }
    }//GEN-LAST:event_buttonHapusRiwayatActionPerformed

    private String getOrderStatus(String id) {
        String query = String.format("select status_order from order_product where order_id=%s", id);
        String result = null;
        try {
            ResultSet rs = parentForm.getDatabaseTools().runQuery(query);
            
            while (rs.next()) {
                result = rs.getString("status_order");
            }
            return result;
            
        } catch (Exception e) {
        }
        
        return null;
    }
    
    private String getSelectedRiwayatFromTable() {
        ListSelectionModel model = tableDaftarOrder.getSelectionModel();
        
        if (!model.isSelectionEmpty()) {
            int index = model.getMinSelectionIndex();
            String id = daftarIdOrder.get(index);
            return id;
        }
        
        return null;
    }
    
    private void updateDataPelanggan() {

        String nama = textNama.getText();
        String email = textEmail.getText();
        String password = textPassword.getText();
        String tanggalLahir = textTanggalLahir.getText();
        String nomorHp = textNomorHp.getText();
        String nomorRumah = textNomorRumah.getText();
        String desaKecamatan = textDesaKecamatan.getText();
        String kabupatenKota = textKabupatenKota.getText();
        String jalan = textJalan.getText();
        String jenisKelamin = cbJenisKelamin.getSelectedItem().toString();
        String kodePos = textKodePos.getText();
        
        String query_update_akun = String.format("exec update_akun_pelanggan %s, '%s', '%s'", 
                this.idPelanggan, email, password);
        String query_update_profil = String.format("exec update_profil_pelanggan %s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s'", 
                this.idPelanggan, nama, tanggalLahir, nomorHp, nomorRumah, desaKecamatan, kabupatenKota, jalan, jenisKelamin, kodePos);
        try {
            parentForm.getDatabaseTools().runUpdateQuery(query_update_akun);
            parentForm.getDatabaseTools().runUpdateQuery(query_update_profil);
            JOptionPane.showMessageDialog(null, "Berhasil");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error mengupdate");
        }
    }
    
    private void setDataEditMode(boolean to) {
        textNama.setEditable(to);
        textEmail.setEditable(to);
        textPassword.setEditable(to);
        textTanggalLahir.setEditable(to);
        textNomorHp.setEditable(to);
        textNomorRumah.setEditable(to);
        textDesaKecamatan.setEditable(to);
        textKabupatenKota.setEditable(to);
        textJalan.setEditable(to);
        textKodePos.setEditable(to);
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
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBatalEdit;
    private javax.swing.JButton buttonEditData;
    private javax.swing.JButton buttonHapusRiwayat;
    private javax.swing.JButton buttonKembali;
    private javax.swing.JButton buttonPrintRiwayat;
    private javax.swing.JComboBox<String> cbJenisKelamin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tableDaftarOrder;
    private javax.swing.JTable tableDaftarProdukOrder;
    private javax.swing.JTextField textAlamatEmailHeader;
    private javax.swing.JTextField textDesaKecamatan;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textJalan;
    private javax.swing.JTextField textKabupatenKota;
    private javax.swing.JTextField textKodePos;
    private javax.swing.JTextField textNama;
    private javax.swing.JTextField textNamaPelangganHeader;
    private javax.swing.JTextField textNomorHp;
    private javax.swing.JTextField textNomorRumah;
    private javax.swing.JTextField textPassword;
    private javax.swing.JTextField textTanggalLahir;
    private javax.swing.JTextField textTotal;
    // End of variables declaration//GEN-END:variables
}
