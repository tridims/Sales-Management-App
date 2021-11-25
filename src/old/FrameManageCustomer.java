package old;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Dimas Tri Mustakim
 * @nim 205150200111049
 */
public class FrameManageCustomer extends javax.swing.JFrame {
    Connection connection;
    String connectionUrl = 
                "jdbc:sqlserver://localhost;" +
                "database=Project; user=sa; password=DimasTri049;" +
                "loginTimeout=30;";
    
    /**
     * Creates new form FrameManageCustomer
     */
    public FrameManageCustomer() {
        initComponents();
        ConnectToSqlServer();
        populateTableAccount();
        
        // Listener ketika ada baris di tabel yang di pilih
        ListSelectionModel model = tableAccount.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!model.isSelectionEmpty()) {
                    int index = model.getMinSelectionIndex();
                    String id = tableAccount.getModel().getValueAt(index, 0).toString();
                    populateProfilPelanggan(id);
                    populateDetailAccount(id);
                }
            }
        });
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing");
                try {
                    connection.close();
                } catch (SQLException ex) {
                }
            }
        });
    }
    
    private void ConnectToSqlServer() {
        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void populateTableAccount() {

        String query_sql = "select * from customer_account";
        ResultSet rs = runQuery(query_sql);
        fillTableAccountFromRS(rs);
 
    }
    
    private void fillTableAccountFromRS(ResultSet rs) {
        try {
            DefaultTableModel tableModel = (DefaultTableModel)tableAccount.getModel();
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id_pelanggan"), 
                    rs.getString("email"), 
                    rs.getString("password")
                });
            }
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
    }
    
    private void populateProfilPelanggan(String id_pelanggan) {
        try {
            String qr = String.format("select * from customer_profile where id_pelanggan = %s", id_pelanggan);
            ResultSet rs = runQuery(qr);
            
            rs.next();
            textDesaKecamatan.setText(rs.getString("desa_kecamatan"));
            textId.setText(rs.getString("id_pelanggan"));
            textJalan.setText(rs.getString("jalan"));
            textJenisKelamin.setText(rs.getString("jenis_kelamin"));
            textKabupatenKota.setText(rs.getString("kabupaten_kota"));
            textKodePos.setText(rs.getString("kode_pos"));
            textNama.setText(rs.getString("nama_pelanggan"));
            textNomorHp.setText(rs.getString("nomor_hp"));
            textNomorRumah.setText(rs.getString("nomor_rumah"));
            textTanggalLahir.setText(rs.getString("tanggal_lahir"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void populateDetailAccount(String id_pelanggan) {
        String qr = String.format("select * from customer_account where id_pelanggan = %s", id_pelanggan);
        ResultSet rs = runQuery(qr);
        
        try {
            rs.next();
            textEmail.setText(rs.getString("email"));
            textPassword.setText(rs.getString("password"));
        } catch (Exception e) {
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
    
    private int runUpdateQuery(String query_sql) {
        try {
            Statement statement = connection.createStatement();
            int updateCount = statement.executeUpdate(query_sql);
            return updateCount;
        } catch (SQLException e) {
            System.out.println("Ada error di run Query");
            e.printStackTrace();
        }
        return 0;
    }
    
    private void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
    
    private void clearEditComponent() {
        textDesaKecamatan.setText("");
        textEmail.setText("");
        textId.setText("");
        textJalan.setText("");
        textJenisKelamin.setText("");
        textKabupatenKota.setText("");
        textKodePos.setText("");
        textNama.setText("");
        textNomorHp.setText("");
        textNomorRumah.setText("");
        textPassword.setText("");
        textSearch.setText("");
        textTanggalLahir.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableAccount = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        textNama = new javax.swing.JTextField();
        textId = new javax.swing.JTextField();
        textJenisKelamin = new javax.swing.JTextField();
        textTanggalLahir = new javax.swing.JTextField();
        textNomorHp = new javax.swing.JTextField();
        textNomorRumah = new javax.swing.JTextField();
        textDesaKecamatan = new javax.swing.JTextField();
        textKabupatenKota = new javax.swing.JTextField();
        textJalan = new javax.swing.JTextField();
        textKodePos = new javax.swing.JTextField();
        buttonSaveProfile = new javax.swing.JButton();
        buttonDeletAccount = new javax.swing.JButton();
        buttonNewAccount = new javax.swing.JButton();
        textSearch = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textEmail = new javax.swing.JTextField();
        textPassword = new javax.swing.JTextField();
        buttonAccountChanges = new javax.swing.JButton();
        buttonSearch = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Manajemen Customer");

        jLabel2.setText("Daftar Akun Pelanggan");

        tableAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Email", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAccount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableAccountKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableAccount);

        jLabel3.setText("Profil Pelanggan");

        jLabel4.setText("ID");

        jLabel5.setText("Nama");

        jLabel6.setText("Tanggal Lahir");

        jLabel7.setText("Nomor HP");

        jLabel8.setText("Nomor Rumah");

        jLabel9.setText("Desa/Kecamatan");

        jLabel10.setText("Kabupaten/Kota");

        jLabel11.setText("Jalan");

        jLabel12.setText("Jenis Kelamin");

        jLabel13.setText("Kode Pos");

        textId.setEditable(false);

        buttonSaveProfile.setText("Simpan Perubahan Profil");
        buttonSaveProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveProfileActionPerformed(evt);
            }
        });

        buttonDeletAccount.setText("Hapus Pelanggan");
        buttonDeletAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeletAccountActionPerformed(evt);
            }
        });

        buttonNewAccount.setText("Tambah Pelanggan");
        buttonNewAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewAccountActionPerformed(evt);
            }
        });

        textSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textSearchKeyReleased(evt);
            }
        });

        jLabel14.setText("Akun Pelanggan");

        jLabel15.setText("Email");

        jLabel16.setText("Password");

        buttonAccountChanges.setText("Simpan Perubahan Akun");
        buttonAccountChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAccountChangesActionPerformed(evt);
            }
        });

        buttonSearch.setText("Search");
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
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonNewAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDeletAccount))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textSearch)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(textKodePos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(textJalan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textKabupatenKota, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textDesaKecamatan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textNomorRumah, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textNama, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textJenisKelamin, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textTanggalLahir, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textNomorHp, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textId)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGap(206, 206, 206))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(buttonSaveProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAccountChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 486, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textPassword)
                            .addComponent(textEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel14))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonSearch))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(59, 59, 59)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(textPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(textJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(textNomorHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(textNomorRumah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textDesaKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(textKabupatenKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(textJalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(textKodePos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNewAccount)
                    .addComponent(buttonDeletAccount)
                    .addComponent(buttonSaveProfile)
                    .addComponent(buttonAccountChanges))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveProfileActionPerformed
        
        String customer_id = textId.getText();
        // Get every text item
        String desaKecamatan = textDesaKecamatan.getText();
        String jalan = textJalan.getText();
        String jenisKelamin = textJenisKelamin.getText();
        String kabupatenKota = textKabupatenKota.getText();
        String kodePos = textKodePos.getText();
        String nama = textNama.getText();
        String nomorHp = textNomorHp.getText();
        String nomorRumah = textNomorRumah.getText();
        String tanggalLahir = textTanggalLahir.getText();
        
        String sql = String.format(
            "update customer_profile " +  
            "set " +
            "nama_pelanggan = '%s', " +
            "tanggal_lahir = '%s', " +
            "nomor_rumah = %s, " +
            "desa_kecamatan = '%s', " +
            "kabupaten_kota = '%s', " +
            "jalan = '%s', " +
            "jenis_kelamin = '%s', " +
            "kode_pos = '%s' " +
            "where id_pelanggan = %s ",
            nama, tanggalLahir, nomorRumah, desaKecamatan, kabupatenKota, jalan, jenisKelamin, kodePos, customer_id
        );
        
        System.out.println(sql);
        int numMod = runUpdateQuery(sql);
        System.out.printf("%s row updated\n", numMod);
    }//GEN-LAST:event_buttonSaveProfileActionPerformed

    private void buttonDeletAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeletAccountActionPerformed
        ListSelectionModel model = tableAccount.getSelectionModel();
        if (!model.isSelectionEmpty()) {
            int index = model.getMinSelectionIndex();
            String id = tableAccount.getModel().getValueAt(index, 0).toString();
            
            String qr = String.format("delete from customer_profile where id_pelanggan = %s", id);
            try {
                runUpdateQuery(qr);
                populateTableAccount();
                clearEditComponent();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Bisa, terdapat item yang direferensi !");
            }
        }
    }//GEN-LAST:event_buttonDeletAccountActionPerformed

    private void buttonNewAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewAccountActionPerformed
        close();
        NewCustomer.main(null);

    }//GEN-LAST:event_buttonNewAccountActionPerformed

    private void tableAccountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAccountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableAccountKeyPressed

    private void buttonAccountChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAccountChangesActionPerformed
        String query = String.format("update customer_account set email='%s', password='%s' where id_pelanggan=%s",
                textEmail.getText(), textPassword.getText(), textId.getText());
        int numUpdated = runUpdateQuery(query);
        populateTableAccount();
        System.out.println("Num updated : " + numUpdated + " query : " + query);
    }//GEN-LAST:event_buttonAccountChangesActionPerformed

    private void textSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textSearchKeyReleased
        // Digunakan untuk search tanpa query (langsung search data di tabelnya)
//        DefaultTableModel tableModel = (DefaultTableModel)tableAccount.getModel();
//        String search = textSearch.getText();
//        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
//        tableAccount.setRowSorter(tr);
//        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_textSearchKeyReleased

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from customer_account\n" +
                    "where email like ? or password like ?");
            String pola = String.format("%s%s%s", "%", textSearch.getText(), "%");
            statement.setString(1, pola);
            statement.setString(2, pola);
            
            ResultSet rs = statement.executeQuery();
            fillTableAccountFromRS(rs);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameManageCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameManageCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameManageCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameManageCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameManageCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAccountChanges;
    private javax.swing.JButton buttonDeletAccount;
    private javax.swing.JButton buttonNewAccount;
    private javax.swing.JButton buttonSaveProfile;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tableAccount;
    private javax.swing.JTextField textDesaKecamatan;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textId;
    private javax.swing.JTextField textJalan;
    private javax.swing.JTextField textJenisKelamin;
    private javax.swing.JTextField textKabupatenKota;
    private javax.swing.JTextField textKodePos;
    private javax.swing.JTextField textNama;
    private javax.swing.JTextField textNomorHp;
    private javax.swing.JTextField textNomorRumah;
    private javax.swing.JTextField textPassword;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextField textTanggalLahir;
    // End of variables declaration//GEN-END:variables
}
