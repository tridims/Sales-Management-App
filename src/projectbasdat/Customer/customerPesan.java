/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectbasdat.Customer;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import projectbasdat.DatabaseTools;

/**
 *
 * @author Varel
 */
public class customerPesan extends javax.swing.JFrame {
    DatabaseTools db = new DatabaseTools();
    int order_id;
    int orderby;
    
    public customerPesan(int order_id, String nama) {
        this.db = db;
        initComponents();
        populateTabelProduk();
        populateComboboxKategori();
        this.order_id=order_id;
        orderby=1;
        textUsername.setText(nama);
        checkBoxOrder.setSelected(true);
        checkBoxMin.setSelected(true);
        checkBoxKategori.setSelected(true);
        checkBoxMax.setSelected(true);
        rbuttonTinggi.setSelected(true);
        tabelProduk.changeSelection(0, 0, false, false);
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                db.close();
            }
        });
        
        ListSelectionModel modelTabelProduk = tabelProduk.getSelectionModel();
        modelTabelProduk.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!modelTabelProduk.isSelectionEmpty()) {
                    int index = modelTabelProduk.getMinSelectionIndex();
                    String namaProduk ="'"+ tabelProduk.getModel().getValueAt(index, 0).toString()+"'";
                    String kategori ="'"+ tabelProduk.getModel().getValueAt(index, 3).toString()+"'";
                    String query=String.format("select product_id from produk where nama_produk =%s and kategori=%s",namaProduk, kategori);
                    try {
                        ResultSet rs=db.runQuery(query);
                        while(rs.next()){
                            populateDetailProduk(rs.getString("product_id"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(customerPesan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
    }
    
    private void populateTextTotal(int order_id){
        String queryView=String.format("select * from total where order_id=%s",order_id);
        try {
            ResultSet rsv = db.runQuery(queryView);
            while(rsv.next()){
                textTotal.setText(rsv.getString("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(customerPesan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void populateTablePesanan(int order_id){
        String query=String.format("exec get_ordered_product_detail %s",order_id);
        String queryView=String.format("select * from subtotal where order_id=%s",order_id);
        try {
            ResultSet rs = db.runQuery(query);
            ResultSet rsv = db.runQuery(queryView);
            DefaultTableModel tableModel = (DefaultTableModel)tabelPesanan.getModel();
            tableModel.setRowCount(0);
            while (rs.next()&&rsv.next()) {
                tableModel.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getInt("kuantitas"),
                    rs.getInt("harga_product"),
                    rsv.getInt("subtotal")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error saat mengisi daftar produk");
        }
    }
    
    private void populateTabelProduk(){
        try {
            String query = String.format("select * from produk");
            ResultSet rs = db.runQuery(query);
            DefaultTableModel tableModel = (DefaultTableModel)tabelProduk.getModel();
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getInt("jumlah_stok"),
                    rs.getInt("harga_satuan"),
                    rs.getString("kategori")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada error saat mengisi daftar produk");
        }
    }
    private void populateDetailProduk(String id){
        try {
            String qr = String.format("select * from produk where product_id = %s", id);
            ResultSet rs = db.runQuery(qr);
            while(rs.next()){
                textDeskripsi.setText(rs.getString("deskripsi"));
                textNutrisi.setText(rs.getString("nutrition_facts"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void populateComboboxKategori(){
        try {
            comboboxKategori.removeAll();
            String qr = String.format("select nama_kategori from kategori");
            ResultSet rs = db.runQuery(qr);
            comboboxKategori.addItem("Semua");
            while(rs.next()){
                comboboxKategori.addItem(rs.getString("nama_kategori"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getSearchString(){
        int column=tabelProduk.getSelectedColumn();
        String atribut="";
        String kondisi=textSearch.getText();
        switch(column){
            case 0:
                atribut="nama_produk";
                kondisi="'%"+kondisi+"%'";
                break;
            case 1:
                atribut="jumlah_stok";
                kondisi="'%"+kondisi+"%'";
                break;
            case 2:
                atribut="harga_satuan";
                kondisi="'%"+kondisi+"%'";
                break;
            case 3:
                atribut="kategori";
                kondisi="'%"+kondisi+"%'";
                break;
        }
        String query=String.format("select * from produk where %s like %s",atribut, kondisi);
//        System.out.println(query);
        return query;
    }
    
    //WIP
    private void refresh(){
        populateComboboxKategori();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        textUsername = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelProduk = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textDeskripsi = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPesanan = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        buttonKeluar = new javax.swing.JButton();
        buttonPesan = new javax.swing.JButton();
        buttonBatalPesan = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        textNutrisi = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        textJumlah = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textTotal = new javax.swing.JTextField();
        buttonMasuk = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        comboboxKategori = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textSearch = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        checkBoxOrder = new javax.swing.JCheckBox();
        rButtonRendah = new javax.swing.JRadioButton();
        rbuttonTinggi = new javax.swing.JRadioButton();
        checkBoxMin = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        textMinHarga = new javax.swing.JTextField();
        textMaxHarga = new javax.swing.JTextField();
        filterButton = new javax.swing.JButton();
        checkBoxKategori = new javax.swing.JCheckBox();
        checkBoxMax = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buat Pesanan");

        jLabel1.setText("Nama Anda");

        textUsername.setEditable(false);

        tabelProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Produk", "Jumlah Stok", "Harga", "Kategori"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelProduk);

        jLabel2.setText("Deskripsi Produk");

        textDeskripsi.setEditable(false);
        textDeskripsi.setColumns(20);
        textDeskripsi.setRows(5);
        jScrollPane2.setViewportView(textDeskripsi);

        jLabel3.setText("Pilih Produk");

        tabelPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama_Produk", "Kuantitas", "Harga Satuan", "Subtotal"
            }
        ));
        jScrollPane3.setViewportView(tabelPesanan);

        jLabel4.setText("Keranjang");

        buttonKeluar.setText("Keluarkan dari keranjang");
        buttonKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKeluarActionPerformed(evt);
            }
        });

        buttonPesan.setText("Pesan");
        buttonPesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonPesanMouseClicked(evt);
            }
        });

        buttonBatalPesan.setText("Batal Memesan");
        buttonBatalPesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonBatalPesanMouseClicked(evt);
            }
        });
        buttonBatalPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBatalPesanActionPerformed(evt);
            }
        });

        jLabel5.setText("Nutrition Facts");

        textNutrisi.setEditable(false);
        textNutrisi.setColumns(20);
        textNutrisi.setRows(5);
        jScrollPane4.setViewportView(textNutrisi);

        jLabel6.setText("Jumlah :");

        jLabel7.setText("Total :");

        textTotal.setEditable(false);

        buttonMasuk.setText("Masukkan ke keranjang");
        buttonMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMasukMouseClicked(evt);
            }
        });

        jLabel8.setText("Filter");

        jLabel9.setText("Kategori");

        jLabel11.setText("Search");

        searchButton.setText("Search");
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });

        checkBoxOrder.setText("Order by (Harga)");
        checkBoxOrder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxOrderItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rButtonRendah);
        rButtonRendah.setText("Terendah");
        rButtonRendah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rButtonRendahItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rbuttonTinggi);
        rbuttonTinggi.setText("Tertinggi");
        rbuttonTinggi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbuttonTinggiItemStateChanged(evt);
            }
        });

        checkBoxMin.setText("Min Harga");
        checkBoxMin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxMinItemStateChanged(evt);
            }
        });

        jLabel10.setText("Min");

        jLabel12.setText("Max");

        filterButton.setText("Filter");
        filterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filterButtonMouseClicked(evt);
            }
        });

        checkBoxKategori.setText("Kategori");
        checkBoxKategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxKategoriItemStateChanged(evt);
            }
        });

        checkBoxMax.setText("Max Harga");
        checkBoxMax.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxMaxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                    .addComponent(comboboxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel8)
                                .addComponent(jLabel11)
                                .addComponent(textSearch)
                                .addComponent(searchButton)
                                .addComponent(checkBoxOrder)
                                .addComponent(rButtonRendah)
                                .addComponent(rbuttonTinggi)
                                .addComponent(checkBoxMin)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel12))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textMaxHarga)
                                        .addComponent(textMinHarga))))
                            .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkBoxKategori)
                            .addComponent(checkBoxMax))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(327, 327, 327)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(jLabel3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonMasuk)
                                    .addComponent(buttonPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonBatalPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonKeluar)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                .addComponent(textJumlah))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(185, 185, 185)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkBoxKategori)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(comboboxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkBoxOrder)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rButtonRendah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbuttonTinggi)
                                .addGap(7, 7, 7))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(24, 24, 24)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonMasuk)
                            .addComponent(buttonKeluar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonPesan)
                            .addComponent(buttonBatalPesan))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(checkBoxMin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(textMinHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkBoxMax)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(textMaxHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filterButton))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKeluarActionPerformed
        int index = tabelPesanan.getSelectedRow();
        String namaProduk = "'"+tabelPesanan.getModel().getValueAt(index, 0).toString()+"'";
        String query1 = String.format("exec get_ordered_product_detail2 %s, %s", order_id, namaProduk);
        try {
            ResultSet rs=db.runQuery(query1);
            int product_id=-1;
            while(rs.next()){
                product_id=rs.getInt("product_id");
            }
            String query2=String.format("exec delete_ordered_product %s, %s", order_id, product_id);
            db.runUpdateQuery(query2);
            populateTablePesanan(order_id);
            populateTextTotal(order_id);

        } catch (SQLException ex) {
            Logger.getLogger(customerPesan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_buttonKeluarActionPerformed

    private void buttonPesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPesanMouseClicked
        String query=String.format("select * from ordered_product where order_id=%s",order_id);
        try {

            ResultSet rs=db.runQuery(query);
            while(rs.next()){
                int product_id=rs.getInt("product_id");
                int jumlah=rs.getInt("kuantitas");
                String queryUpdate = String.format("exec update_stok_produk %s, %s",product_id, jumlah);
                db.runUpdateQuery(queryUpdate);
            }
            populateTabelProduk();
            JOptionPane.showMessageDialog(null, "Berhasil melakukan pemesanan");
            close();
        } catch (SQLException ex) {
            Logger.getLogger(customerPesan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonPesanMouseClicked

    private void buttonBatalPesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBatalPesanMouseClicked
        String query=String.format("exec delete_order_product %s", order_id);
        try {
            db.runUpdateQuery(query);
            JOptionPane.showMessageDialog(null, "Berhasil membatalkan pemesanan");
            close();
        } catch (SQLException ex) {
            Logger.getLogger(customerPesan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonBatalPesanMouseClicked

    private void buttonMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMasukMouseClicked
        
        int index = tabelProduk.getSelectedRow();
        System.out.println(index);
        String namaProduk="'"+tabelProduk.getModel().getValueAt(index, 0).toString()+"'";
        String kategori="'"+tabelProduk.getModel().getValueAt(index, 3).toString()+"'";
        int harga=(int)tabelProduk.getModel().getValueAt(index, 2);
        String query1=String.format("select product_id from produk where nama_produk=%s and kategori=%s", namaProduk, kategori);
        try {
            ResultSet rs = db.runQuery(query1);
            int product_id=-1;
            while(rs.next()){
                product_id=rs.getInt("product_id");
            }
            String query2=String.format("select kuantitas from ordered_product where order_id=%s and product_id=%s",order_id, product_id);
            ResultSet rs2 = db.runQuery(query2);
            int jumlah=Integer.parseInt(textJumlah.getText());
            boolean flag=false;
            while(rs2.next()){
                flag=true;
                jumlah+=rs2.getInt("kuantitas");
            }
            int max=(int) tabelProduk.getModel().getValueAt(index, 1);
            if(jumlah<1){
                JOptionPane.showMessageDialog(null, "jumlah yang dibeli minimal 1");
            }else if(jumlah>max){
                JOptionPane.showMessageDialog(null, "Jumlah yang anda pesan melebihi stok yang tersedia");
            }else{
                if(flag==true){
                    String queryUpdate=String.format("update ordered_product set kuantitas=%s where "
                            + "order_id=%s and product_id=%s", jumlah, order_id, product_id);
                    db.runUpdateQuery(queryUpdate);
                    JOptionPane.showMessageDialog(null, "Berhasil menambahkan jumlah barang yang dipesan");
                }else{
                    String queryInsert=String.format("exec new_ordered_product %s, %s, %s, %s", order_id, product_id
                    , jumlah, harga);
                    db.runUpdateQuery(queryInsert);
                }
            populateTablePesanan(order_id);
            populateTextTotal(order_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(customerPesan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonMasukMouseClicked

    private void checkBoxOrderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBoxOrderItemStateChanged
        if(checkBoxOrder.isSelected()==true){
//            orderByFlag=true;
            rButtonRendah.setEnabled(true);
            rbuttonTinggi.setEnabled(true);
        }else{
//            orderByFlag=false;
            rButtonRendah.setEnabled(false);
            rbuttonTinggi.setEnabled(false);
        }
    }//GEN-LAST:event_checkBoxOrderItemStateChanged

    private void checkBoxKategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBoxKategoriItemStateChanged
        if(checkBoxKategori.isSelected()==true){
//            kategoriFlag=true;
            comboboxKategori.setEnabled(true);
        }else{
//            kategoriFlag=false;
            comboboxKategori.setEnabled(false);
        }
    }//GEN-LAST:event_checkBoxKategoriItemStateChanged

    private void checkBoxMinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBoxMinItemStateChanged
        if(checkBoxMin.isSelected()==true){
//            minFlag=true;
            textMinHarga.setEnabled(true);
        }else{
//            minFlag=false;
            textMinHarga.setEnabled(false);
        }
    }//GEN-LAST:event_checkBoxMinItemStateChanged
    
    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
        try {
            ResultSet rs=db.runQuery(getSearchString());
            DefaultTableModel tableModel = (DefaultTableModel)tabelProduk.getModel();
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getInt("jumlah_stok"),
                    rs.getInt("harga_satuan"),
                    rs.getString("kategori")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(customerPesan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonMouseClicked

    private void rButtonRendahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rButtonRendahItemStateChanged
        if(rButtonRendah.isSelected()){
            orderby=0;
        }
    }//GEN-LAST:event_rButtonRendahItemStateChanged

    private void rbuttonTinggiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbuttonTinggiItemStateChanged
        if(rbuttonTinggi.isSelected()){
            orderby=1;
        }
    }//GEN-LAST:event_rbuttonTinggiItemStateChanged

    private void filterButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterButtonMouseClicked
        try {
            String query=getSearchString();
//            if(checkBoxKategori.equals(true)){
            if(checkBoxKategori.isSelected()&&!comboboxKategori.getSelectedItem().equals("Semua")){
                query+= String.format(" and kategori = %s",("'"+comboboxKategori.getSelectedItem()+"'"));
            }
//            if(checkBoxMin.equals(true)){
            if(checkBoxMin.isSelected()  && !textMinHarga.getText().equals("")){
                query+= String.format(" and harga_satuan >= %s",textMinHarga.getText());
            }
            if(checkBoxMax.isSelected() && !textMaxHarga.getText().equals("")){
                query+= String.format(" and harga_satuan <= %s",textMaxHarga.getText());
            }
            if(checkBoxOrder.isSelected()){
                if(orderby==1){
                    query+= String.format(" order by harga_satuan desc");
                }else if(orderby==0){
                    query+= String.format(" order by harga_satuan asc");
                }
            }
            System.out.println(query);
            ResultSet rs=db.runQuery(query);
            DefaultTableModel tableModel = (DefaultTableModel)tabelProduk.getModel();
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[] {
                    rs.getString("nama_produk"),
                    rs.getInt("jumlah_stok"),
                    rs.getInt("harga_satuan"),
                    rs.getString("kategori")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(customerPesan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_filterButtonMouseClicked

    private void checkBoxMaxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBoxMaxItemStateChanged
        if(checkBoxMax.isSelected()==true){
//            maxFlag=true;
            textMaxHarga.setEnabled(true);
        }else{
//            maxFlag=false;
            textMaxHarga.setEnabled(false);
        }
    }//GEN-LAST:event_checkBoxMaxItemStateChanged

    private void buttonBatalPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalPesanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonBatalPesanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[], int id, String nama) {
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
            java.util.logging.Logger.getLogger(customerPesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customerPesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customerPesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customerPesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new customerPesan(id, nama).setVisible(true);
            }
        });
    }
    private void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBatalPesan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonKeluar;
    private javax.swing.JButton buttonMasuk;
    private javax.swing.JButton buttonPesan;
    private javax.swing.JCheckBox checkBoxKategori;
    private javax.swing.JCheckBox checkBoxMax;
    private javax.swing.JCheckBox checkBoxMin;
    private javax.swing.JCheckBox checkBoxOrder;
    private javax.swing.JComboBox<String> comboboxKategori;
    private javax.swing.JButton filterButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rButtonRendah;
    private javax.swing.JRadioButton rbuttonTinggi;
    private javax.swing.JButton searchButton;
    private javax.swing.JTable tabelPesanan;
    private javax.swing.JTable tabelProduk;
    private javax.swing.JTextArea textDeskripsi;
    private javax.swing.JTextField textJumlah;
    private javax.swing.JTextField textMaxHarga;
    private javax.swing.JTextField textMinHarga;
    private javax.swing.JTextArea textNutrisi;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextField textTotal;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
