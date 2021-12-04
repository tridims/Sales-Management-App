/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectbasdat.Customer;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import projectbasdat.DatabaseTools;

/**
 *
 * @author Varel dan Gaffy
 */
public class MainCustomer extends javax.swing.JFrame {
    DatabaseTools db = new DatabaseTools();
    int idPel, id_pelanggan;
    String nama, email,password;
    ArrayList<String> daftarIdProduk,daftarOrderId,daftarOrderIdAktif;
    /**
     * Creates new form Main
     */
    public MainCustomer(String email, String password) {
        initComponents();
        populateProfil(email, password);
        this.email = email;
        this.password = password;
        initId_Pelanggan();
        refresh();
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                db.close();
            }
        });
        
        //listener untuk tabel order
        ListSelectionModel modelDetail = tableOrder.getSelectionModel();
        modelDetail.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!modelDetail.isSelectionEmpty()) {
                    int index = modelDetail.getMinSelectionIndex();
                    String id = daftarOrderId.get(index);
                    populateTableDetailPesanan(id);
                }
            }
        });
        
        //listener untuk tabel order aktif
        ListSelectionModel modelDetail1 = TableOrderAktif.getSelectionModel();
        modelDetail1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!modelDetail1.isSelectionEmpty()) {
                    int index = modelDetail1.getMinSelectionIndex();
                    String id = daftarOrderIdAktif.get(index);
                    populateTableDetailPesananAktif(id);
                }
            }
        });
        
    }
    
    private void initId_Pelanggan(){
        try {
            String query = String.format("select id_pelanggan from customer_account where email = %s",email);
            System.out.println(query);
            ResultSet rs = db.runQuery(query);
            while (rs.next()){
                id_pelanggan = Integer.parseInt(rs.getString("id_pelanggan"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getEmail(){
        return email;
    }
    
    private void refresh(){
        populateTableOrder();
        pupulateTableOrderAktif();
    }
    
//    private void refresh2(){
//        populateTableOrder();
//        pupulateTableOrderAktif();
//        DefaultTableModel tableModel = (DefaultTableModel)TableDetailPesanan.getModel();
//        tableModel.setRowCount(0);
//        DefaultTableModel tableModelPA = (DefaultTableModel)TableDetailPesananAktif.getModel();
//        tableModelPA.setRowCount(0);
//    }
    
    private void populateTableOrder(){
        try {
            String query = String.format("exec get_riwayatPesanan %s",idPel);
            System.out.println(query);
            ResultSet rs = db.runQuery(query);
            
            DefaultTableModel tableModel = (DefaultTableModel)tableOrder.getModel();
            tableModel.setRowCount(0);
            daftarOrderId = new ArrayList();
            int count = 1;
            String status;
            while (rs.next()) {
                daftarOrderId.add(rs.getString("order_id"));
                if(rs.getString("status_order").equals("1")){
                    status = "Selesai";
                }else{
                    status = "Diproses";
                }
                tableModel.addRow(new Object[] {
                    count,
                    rs.getString("tanggal_kirim"),
                    status
                });
                count++;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "populateTableOrder error");
        }
    }
    
    private void populateTableDetailPesanan(String order_id) {
        try {
            String query = String.format("exec get_detailpesanan %s", order_id);
            System.out.println(query);
            ResultSet rs = db.runQuery(query);
            DefaultTableModel tableModel = (DefaultTableModel)TableDetailPesanan.getModel();
            tableModel.setRowCount(0);
            int total = 0;
            int temp;
            while (rs.next()) {
                temp = Integer.parseInt(rs.getString("subtotal"));
                total += temp;
                tableModel.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getString("kategori"),
                    rs.getString("kuantitas"),
                    rs.getString("harga_product"),
                    rs.getString("subtotal")
                });
            }
            totalText.setText(String.valueOf(total));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "populateTableDetailPesanan error");
        }
    }
    
    private void pupulateTableOrderAktif(){
        try {
            String query = String.format("exec get_pesananaktif %s",idPel);
            System.out.println(query);
            ResultSet rs = db.runQuery(query);
            
            DefaultTableModel tableModel = (DefaultTableModel)TableOrderAktif.getModel();
            tableModel.setRowCount(0);
            daftarOrderIdAktif = new ArrayList();
            int count = 1;
            while (rs.next()) {
                daftarOrderIdAktif.add(rs.getString("order_id"));               
                tableModel.addRow(new Object[] {
                    count,
                    rs.getString("tanggal_kirim")
                });
                count++;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "pupulateTableOrderAktif error");
        }
    }
    
    private void populateTableDetailPesananAktif(String order_id) {
        try {
            String query = String.format("exec get_detailpesanan %s", order_id);
            System.out.println(query);
            ResultSet rs = db.runQuery(query);
            DefaultTableModel tableModel = (DefaultTableModel)TableDetailPesananAktif.getModel();
            tableModel.setRowCount(0);
            int total = 0;
            int temp;
            while (rs.next()) {
                temp = Integer.parseInt(rs.getString("subtotal"));
                total += temp;
                tableModel.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getString("kategori"),
                    rs.getString("kuantitas"),
                    rs.getString("harga_product"),
                    rs.getString("subtotal")
                });
            }
            TotalTextAktif.setText(String.valueOf(total));

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "populateTableDetailPesananAktif error");
        }
    }
    
    private void populateProfil(String email, String password){
        String query = String.format("exec get_profil_pelanggan %s, %s", email, password);
        ResultSet rs;
        try {
            rs = db.runQuery(query);
            while(rs.next()){
            idPel=rs.getInt("id_pelanggan");
            nama=rs.getString("nama_pelanggan");
            textUsername.setText(nama);
            textEmail.setText(rs.getString("email"));
            textPassword.setText(rs.getString("password"));
            textNama.setText(nama);
            comboboxJenisKelamin.setSelectedItem(rs.getString("jenis_kelamin"));
            textTanggalLahir.setText(rs.getString("tanggal_lahir"));
            textNomorHp.setText(rs.getString("nomor_hp"));
            textNomorRumah.setText(rs.getString("nomor_rumah"));
            textDesaKecamatan.setText(rs.getString("desa_kecamatan"));
            textKabupatenKota.setText(rs.getString("kabupaten_kota"));
            textJalan.setText(rs.getString("jalan"));
            textKodePos.setText(rs.getString("kode_pos"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateProfil(){;
        String nama="'"+textNama.getText()+"'";
        String email="'"+textEmail.getText()+"'";
        String password="'"+textPassword.getText()+"'";
        String jk="'"+(String)comboboxJenisKelamin.getSelectedItem()+"'";
        String tgl="'"+textTanggalLahir.getText()+"'";
        String nomorHp=textNomorHp.getText();
        String nomorRumah="'"+textNomorRumah.getText()+"'";
        String desaKec="'"+textDesaKecamatan.getText()+"'";
        String kabKota="'"+textKabupatenKota.getText()+"'";
        String jalan="'"+textJalan.getText()+"'";
        String kodePos="'"+textKodePos.getText()+"'";
        
        String query = String.format("exec get_profil_pelanggan %s, %s", email, password);
        ResultSet rs;
        try{
            rs = db.runQuery(query);
            while(rs.next()){
                String id=rs.getString("id_pelanggan");
                String query2=String.format("exec edit_profile_customer %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                        id, email, password, nama, tgl, nomorHp, nomorRumah, desaKec, kabKota, jalan, jk, kodePos);
                db.runUpdateQuery(query2);
                JOptionPane.showMessageDialog(null, "Berhasil");
            }
        }catch(SQLException ex){
            Logger.getLogger(MainCustomer.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal mengupdate");
        }
        populateProfil(email, password);
    }

    private void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
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
        textUsername = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textNomorRumah = new javax.swing.JTextField();
        textTanggalLahir = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        textKodePos = new javax.swing.JTextField();
        textDesaKecamatan = new javax.swing.JTextField();
        textKabupatenKota = new javax.swing.JTextField();
        textNama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textNomorHp = new javax.swing.JTextField();
        textPassword = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textEmail = new javax.swing.JTextField();
        textJalan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboboxJenisKelamin = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDetailPesanan = new javax.swing.JTable();
        totalText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        printRiwayatOrderButton = new javax.swing.JButton();
        printRiwayatProduk = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableOrderAktif = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableDetailPesananAktif = new javax.swing.JTable();
        TotalTextAktif = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        BatalPesananButton = new javax.swing.JButton();
        buttonPesan = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        buttonKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Customer");

        jLabel1.setText("Nama Anda");

        textUsername.setEditable(false);

        jLabel9.setText("Desa/Kecamatan");

        jLabel12.setText("Jenis Kelamin");

        jLabel16.setText("Password");

        jLabel13.setText("Kode Pos");

        jButton2.setText("Edit Data");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Nama");

        jLabel11.setText("Jalan");

        jLabel6.setText("Tanggal Lahir");

        jLabel15.setText("Email");

        jLabel7.setText("Nomor HP");

        jLabel10.setText("Kabupaten/Kota");

        jLabel8.setText("Nomor Rumah");

        comboboxJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "L", "P" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(490, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
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
                    .addComponent(comboboxJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(40, 40, 40)
                .addComponent(jButton2)
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("Profil", jPanel1);

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nomor", "tanggal kirim", "status order"
            }
        ));
        jScrollPane1.setViewportView(tableOrder);

        TableDetailPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Produk", "Kategori", "Kuantitas", "Harga", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(TableDetailPesanan);

        jLabel3.setText("Total");

        printRiwayatOrderButton.setText("Print Riwayat Order");
        printRiwayatOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printRiwayatOrderButtonActionPerformed(evt);
            }
        });

        printRiwayatProduk.setText("Print Riwayat Pemesanan Produk");
        printRiwayatProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printRiwayatProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(printRiwayatProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 590, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(totalText))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(printRiwayatOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(4, 4, 4)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(522, Short.MAX_VALUE)
                .addComponent(printRiwayatProduk)
                .addGap(63, 63, 63))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(totalText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))))
                    .addGap(18, 18, 18)
                    .addComponent(printRiwayatOrderButton)
                    .addContainerGap(56, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Riwayat Pesanan", jPanel2);

        TableOrderAktif.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nomor", "tanggal kirim"
            }
        ));
        jScrollPane3.setViewportView(TableOrderAktif);

        TableDetailPesananAktif.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Produk", "Kategori", "Kuantitas", "Harga", "Subtotal"
            }
        ));
        jScrollPane4.setViewportView(TableDetailPesananAktif);

        jLabel4.setText("Total");

        jButton5.setText("Print Data Pesanan");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        BatalPesananButton.setText("Batalkan Pesanan");
        BatalPesananButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BatalPesananButtonMouseClicked(evt);
            }
        });
        BatalPesananButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatalPesananButtonActionPerformed(evt);
            }
        });

        buttonPesan.setText("Buat Pesanan");
        buttonPesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonPesanMouseClicked(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(BatalPesananButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TotalTextAktif))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalTextAktif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(BatalPesananButton)
                    .addComponent(buttonPesan)
                    .addComponent(refreshButton))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pesanan Aktif", jPanel4);

        buttonKeluar.setText("Keluar");
        buttonKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonKeluar))
                .addGap(31, 31, 31)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKeluarActionPerformed
        close();
    }//GEN-LAST:event_buttonKeluarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        updateProfil();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttonPesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPesanMouseClicked
        String query=String.format("select dbo.get_tanggal_kirim() as tanggal_kirim");
        try {
            // get tanggal kirim (1 minggu kemudian)
            ResultSet rs = db.runQuery(query);
            rs.next();
            String date = "'" + rs.getString("tanggal_kirim") + "'";
            
            // get id karyawan yang memiliki orderan paling sedikit
            rs = db.runQuery("select dbo.get_karyawan() as id_karyawan");
            rs.next();
            String idKaryawan = rs.getString("id_karyawan");
            
            String query2=String.format("exec new_order_product %s, %s, %s, %s", date, 0, idPel, idKaryawan);
            db.runUpdateQuery(query2);
            String query3=String.format("select max(order_id) as order_id from order_product");
            ResultSet rs3=db.runQuery(query3);
            rs3.next();
            int order_id=rs3.getInt("order_id");
            
//            customerPesan.main(null, order_id, nama);
            customerPesan cp = new customerPesan(order_id, nama);
            cp.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MainCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_buttonPesanMouseClicked

    private void BatalPesananButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BatalPesananButtonMouseClicked
        // TODO add your handling code here:
        //String query=String.format("select * from ordered_product where order_id=%s",order_id);
        String selectedOrderId = daftarOrderIdAktif.get(TableOrderAktif.getSelectedRow());
        // daftarOrderIdAktif.remove(TableOrderAktif.getSelectedRow());
        try {
            db.beginTransaction();
            String detailPesananQuery = String.format("exec get_detailpesanan %s", selectedOrderId);
            ResultSet detailPesananRS = db.runQuery(detailPesananQuery);
            while(detailPesananRS.next()){
                int productId = detailPesananRS.getInt("product_id");
                int qty = detailPesananRS.getInt("kuantitas");
                String returnStockQuery = String.format("exec update_stok_produk %s, %s", productId, (qty*-1));
                db.runUpdateQuery(returnStockQuery);
                String deleteOrderedProductQuery = String.format("exec delete_ordered_product %s, %s", selectedOrderId, productId);
                db.runUpdateQuery(deleteOrderedProductQuery);
            }
            String deleteOrder = String.format("exec delete_order_product %s", selectedOrderId);
            db.runUpdateQuery(deleteOrder);
            db.commitTransaction();
        } catch (Exception ex){ 
            try {
                db.rollbackTransaction();
            } catch (SQLException exs) {
                Logger.getLogger(MainCustomer.class.getName()).log(Level.SEVERE, null, exs);
            }
            Logger.getLogger(MainCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        pupulateTableOrderAktif();
    }//GEN-LAST:event_BatalPesananButtonMouseClicked

    private void printRiwayatProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printRiwayatProdukActionPerformed
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("resource/RiwayatPembelian.jrxml");
              
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id_pelanggan", this.idPel);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, db.getConnection());
            
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
//            e.printStackTrace();
        }
    }//GEN-LAST:event_printRiwayatProdukActionPerformed

    private void printRiwayatOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printRiwayatOrderButtonActionPerformed

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("resource/notaCustomer.jrxml");
            String selectedOrderId = daftarOrderId.get(tableOrder.getSelectedRow());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("order_id", Integer.parseInt(selectedOrderId));
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, db.getConnection());
            
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error, Pilih ordernya dulu!");
//            e.printStackTrace();
        }
    }//GEN-LAST:event_printRiwayatOrderButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("resource/notaCustomer.jrxml");
            String selectedOrderId = daftarOrderIdAktif.get(TableOrderAktif.getSelectedRow());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("order_id", Integer.parseInt(selectedOrderId));
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, db.getConnection());
            
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void BatalPesananButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatalPesananButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BatalPesananButtonActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[], String email, String password) {
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
            java.util.logging.Logger.getLogger(MainCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainCustomer(email, password).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BatalPesananButton;
    private javax.swing.JTable TableDetailPesanan;
    private javax.swing.JTable TableDetailPesananAktif;
    private javax.swing.JTable TableOrderAktif;
    private javax.swing.JTextField TotalTextAktif;
    private javax.swing.JButton buttonKeluar;
    private javax.swing.JButton buttonPesan;
    private javax.swing.JComboBox<String> comboboxJenisKelamin;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton printRiwayatOrderButton;
    private javax.swing.JButton printRiwayatProduk;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTable tableOrder;
    private javax.swing.JTextField textDesaKecamatan;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textJalan;
    private javax.swing.JTextField textKabupatenKota;
    private javax.swing.JTextField textKodePos;
    private javax.swing.JTextField textNama;
    private javax.swing.JTextField textNomorHp;
    private javax.swing.JTextField textNomorRumah;
    private javax.swing.JTextField textPassword;
    private javax.swing.JTextField textTanggalLahir;
    private javax.swing.JTextField textUsername;
    private javax.swing.JTextField totalText;
    // End of variables declaration//GEN-END:variables
}
