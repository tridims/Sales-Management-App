package old;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dimas Tri Mustakim
 * @nim 205150200111049
 */
public class ManajemenProduk extends javax.swing.JFrame {

    Connection connection;
    String connectionUrl = 
                "jdbc:sqlserver://localhost;" +
                "database=Project; user=sa; password=DimasTri049;" +
                "loginTimeout=30;";
    
    private void connectToSqlServer() {
        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void populateTableProduct() {
        String query = "select * from produk";
        ResultSet rs = runQuery(query);
        
        DefaultTableModel tableModel = (DefaultTableModel)tableProduk.getModel();
        tableModel.setRowCount(0);
        
        try {
            while (rs.next()) {
                tableModel.addRow(new Object[] {
                    rs.getInt("product_id"),
                    rs.getString("nama_produk"),
                    rs.getInt("jumlah_stok"),
                    rs.getInt("harga_satuan"),
                    rs.getString("deskripsi"),
                    rs.getString("nutrition_facts"),
                    rs.getString("kategori")
                });
            }
        } catch(Exception e) {
            System.out.println("Ada error saat populate table");
            e.printStackTrace();
        }
        
    }
    
    private ResultSet runQuery(String query_sql) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query_sql);
            return rs;
        } catch (SQLException e) {
            System.out.println("Ada error di run Query");
        }
        return null;
    }
    
    private void populateComboBoxKategori() {
        String qr = "select distinct kategori from produk";
        ResultSet rs = runQuery(qr);
        
        try {
            while (rs.next()) {
                comboBoxKategori.addItem(rs.getString("kategori"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void populateDetailProduk(String id_produk) {
        try {
            String qr = String.format("select * from produk where product_id = %s", id_produk);
            ResultSet rs = runQuery(qr);
            
            rs.next();
            textDeskripsi.setText(rs.getString("deskripsi"));
            textHargaProduct.setText(rs.getString("harga_satuan"));
            textJumlahStok.setText(rs.getString("jumlah_stok"));
            textNamaProduk.setText(rs.getString("nama_produk"));
            textNutritionFacts.setText(rs.getString("nutrition_facts"));
            textProductID.setText(rs.getString("product_id"));
            comboBoxKategori.setSelectedItem(rs.getString("kategori"));
            
        } catch (Exception e) {
            System.out.println("Ada Erros saat populate Detail Product");
            e.printStackTrace();
        }
    }
    
    private int runUpdateQuery(String query_sql) {
        try {
            Statement statement = connection.createStatement();
            int updateCount = statement.executeUpdate(query_sql);
            return updateCount;
        } catch (SQLException e) {
            System.out.println("Ada error di runUpdateQuery");
            e.printStackTrace();
        }
        return 0;
    }
    
    private void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
        try {
            connection.close();
        } catch (Exception e) {}
    }
    
    /**
     * Creates new form ManajemenProduk
     */
    public ManajemenProduk() {
        initComponents();
        connectToSqlServer();
        populateTableProduct();
        populateComboBoxKategori();
        
        // Listener ketika ada baris di tabel yang di pilih
        ListSelectionModel model = tableProduk.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!model.isSelectionEmpty()) {
                    int index = model.getMinSelectionIndex();
                    String id = tableProduk.getModel().getValueAt(index, 0).toString();
                    populateDetailProduk(id);
                }
            }
        });
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                }
            }
        });
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduk = new javax.swing.JTable();
        buttonNewProduct = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonSaveChanges = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textProductID = new javax.swing.JTextField();
        textNamaProduk = new javax.swing.JTextField();
        textJumlahStok = new javax.swing.JTextField();
        textHargaProduct = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        textDeskripsi = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        textNutritionFacts = new javax.swing.JTextArea();
        comboBoxKategori = new javax.swing.JComboBox<>();
        textSearch = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel1.setText("Manajemen Produk");

        tableProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Produk", "Jumlah Stok", "Harga", "Deskripsi", "Nutrisi", "Kategori"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableProduk);

        buttonNewProduct.setText("Tambah Produk");
        buttonNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewProductActionPerformed(evt);
            }
        });

        buttonDelete.setText("Hapus Produk");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonSaveChanges.setText("Simpan perubahan");
        buttonSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveChangesActionPerformed(evt);
            }
        });

        jLabel2.setText("Product ID");

        jLabel3.setText("Nama Produk");

        jLabel4.setText("Jumlah Stok");

        jLabel5.setText("Harga");

        jLabel6.setText("Deskripsi");

        jLabel7.setText("Nutrition Facts");

        jLabel8.setText("Kategori");

        textProductID.setEditable(false);

        textDeskripsi.setColumns(20);
        textDeskripsi.setRows(5);
        jScrollPane2.setViewportView(textDeskripsi);

        textNutritionFacts.setColumns(20);
        textNutritionFacts.setRows(5);
        jScrollPane3.setViewportView(textNutritionFacts);

        buttonSearch.setText("Cari");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addComponent(jLabel6))
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 48, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(textHargaProduct)
                            .addComponent(textJumlahStok)
                            .addComponent(textNamaProduk)
                            .addComponent(textProductID)
                            .addComponent(jScrollPane3)
                            .addComponent(comboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(buttonSearch))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonDelete)
                                .addGap(693, 693, 693)
                                .addComponent(buttonSaveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(501, 501, 501))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textJumlahStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textHargaProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearch))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNewProduct)
                    .addComponent(buttonDelete)
                    .addComponent(buttonSaveChanges))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewProductActionPerformed
        AddNewProduct.main(null);
        close();
    }//GEN-LAST:event_buttonNewProductActionPerformed

    private void buttonSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveChangesActionPerformed
        String deskripsi = textDeskripsi.getText();
        String harga = textHargaProduct.getText();
        String jumlahStok = textJumlahStok.getText();
        String namaProduk = textNamaProduk.getText();
        String nutrisi = textNutritionFacts.getText();
        String kategori = comboBoxKategori.getSelectedItem().toString();
        try {
            String qr = String.format("update produk set " +
                    "nama_produk = '%s', jumlah_stok = %s, harga_satuan = %s, deskripsi= '%s', " +
                    "nutrition_facts='%s', kategori = '%s' where product_id = %s",
                    namaProduk,
                    jumlahStok,
                    harga,
                    deskripsi,
                    nutrisi,
                    kategori,
                    textProductID.getText()
            );
            
            runUpdateQuery(qr);
            populateTableProduct();

            
        } catch (Exception e) {
            System.out.println("Ada error saat save");
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonSaveChangesActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        ListSelectionModel model = tableProduk.getSelectionModel();
        if (!model.isSelectionEmpty()) {
            int index = model.getMinSelectionIndex();
            String id = tableProduk.getModel().getValueAt(index, 0).toString();
            
            String qr = String.format("delete from produk where product_id = %s", id);
            try {
                runUpdateQuery(qr);
                populateTableProduct();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa, terdapat item yang direferensi !");
            }
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchActionPerformed

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
            java.util.logging.Logger.getLogger(ManajemenProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManajemenProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManajemenProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManajemenProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManajemenProduk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonNewProduct;
    private javax.swing.JButton buttonSaveChanges;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JComboBox<String> comboBoxKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableProduk;
    private javax.swing.JTextArea textDeskripsi;
    private javax.swing.JTextField textHargaProduct;
    private javax.swing.JTextField textJumlahStok;
    private javax.swing.JTextField textNamaProduk;
    private javax.swing.JTextArea textNutritionFacts;
    private javax.swing.JTextField textProductID;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables
}
