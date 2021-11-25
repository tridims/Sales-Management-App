package old;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Varel Yonathan Simangunsong
 * @nim 205150207111029
 */
public class FrameKaryawan extends javax.swing.JFrame {
    Connection connection;
    String connectionUrl = 
                "jdbc:sqlserver://localhost;" +
                "database=Project; user=sa; password=12345;" +
                "loginTimeout=30;";
    
    /**
     * Creates new form FrameManageCustomer
     */
    public FrameKaryawan() {
        initComponents();
        ConnectToSqlServer();
        populateTableAccount();
        populateJabatan();
        populateCabang();
        
        // Listener ketika ada baris di tabel yang di pilih
        ListSelectionModel model = tableAccount.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!model.isSelectionEmpty()) {
                    int index = model.getMinSelectionIndex();
                    String id = tableAccount.getModel().getValueAt(index, 0).toString();
                    String jabatan = tableAccount.getModel().getValueAt(index, 3).toString();
                    String id_cabang = tableAccount.getModel().getValueAt(index, 2).toString();
                    populateProfilKaryawan(id);
                    populateGajiAccount(jabatan);
                    populateCabangAccount(id_cabang);
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
        try {
            String query_sql = "select * from karyawan";
            ResultSet rs = runQuery(query_sql);
            DefaultTableModel tableModel = (DefaultTableModel)tableAccount.getModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                tableModel.addRow(new Object[]{rs.getInt("id_karyawan"), rs.getString("nama"), rs.getString("id_cabang"), rs.getString("jabatan")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void populateProfilKaryawan(String id_karyawan) {
        try {
            String qr = String.format("select * from karyawan where id_karyawan = %s", id_karyawan);
            ResultSet rs = runQuery(qr);
            
            rs.next();
            textAlamat.setText(rs.getString("alamat"));
            textId.setText(rs.getString("id_karyawan"));
            textEmail.setText(rs.getString("email"));
            textNama.setText(rs.getString("nama"));
            textNomorHp.setText(rs.getString("nomor_hp"));
            textTanggalLahir.setText(rs.getString("tanggal_lahir"));
            comboboxIDCabang.setSelectedItem(rs.getString("id_cabang"));
            comboboxJabatan.setSelectedItem(rs.getString("jabatan"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    List<String> cbg=new ArrayList<>();
    private void populateCabang(){
        ResultSet rs=null;
        cbg.clear();
        try {
            connection = DriverManager.getConnection(connectionUrl);
            Statement statement=connection.createStatement();
            String select = "select * from cabang";
            rs=statement.executeQuery(select);
            while (rs.next()) {      
              cbg.add(rs.getString("id_cabang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboboxIDCabang.removeAllItems();
        for(String val: cbg){
            comboboxIDCabang.addItem(val);
        }
    }
    
    List<String> jbt=new ArrayList<>();
    private void populateJabatan(){
        ResultSet rs=null;
        jbt.clear();
        try {
            connection = DriverManager.getConnection(connectionUrl);
            Statement statement=connection.createStatement();
            String select = "select * from jabatan";
            rs=statement.executeQuery(select);
            while (rs.next()) {      
              jbt.add(rs.getString("nama_jabatan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboboxJabatan.removeAllItems();
        for(String val: jbt){
            comboboxJabatan.addItem(val);
        }
    }
    private void populateCabangAccount(String id_cabang) {
        String qr = String.format("select * from cabang where id_cabang = %s", id_cabang);
        ResultSet rs = runQuery(qr);
        
        try {
            rs.next();
            textNamaCabang.setText(rs.getString("nama_cabang"));
            textAlamatCabang.setText(rs.getString("alamat_cabang"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void populateGajiAccount(String jabatan) {
        String qr = String.format("select * from jabatan where nama_jabatan = '%s'", jabatan);
        ResultSet rs = runQuery(qr);
        
        try {
            rs.next();
            textGaji.setText(rs.getString("gaji"));
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
        }
        return 0;
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
        jLabel9 = new javax.swing.JLabel();
        textNama = new javax.swing.JTextField();
        textId = new javax.swing.JTextField();
        textTanggalLahir = new javax.swing.JTextField();
        textNomorHp = new javax.swing.JTextField();
        textAlamat = new javax.swing.JTextField();
        buttonSaveProfile = new javax.swing.JButton();
        buttonDeletAccount = new javax.swing.JButton();
        buttonNewAccount = new javax.swing.JButton();
        textSearch = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        textEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textGaji = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        textNamaCabang = new javax.swing.JTextField();
        textAlamatCabang = new javax.swing.JTextField();
        toJabatan = new javax.swing.JButton();
        toCabang = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        comboboxIDCabang = new javax.swing.JComboBox<>();
        comboboxJabatan = new javax.swing.JComboBox<>();

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
        jLabel1.setText("Manajemen Karyawan");

        jLabel2.setText("Daftar Akun Karyawan");

        tableAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "ID Cabang", "Jabatan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jLabel3.setText("Profil Karyawan");

        jLabel4.setText("ID");

        jLabel5.setText("Nama");

        jLabel6.setText("Tanggal Lahir");

        jLabel7.setText("Nomor HP");

        jLabel9.setText("Alamat");

        textId.setEditable(false);

        buttonSaveProfile.setText("Save Profile Changes");
        buttonSaveProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveProfileActionPerformed(evt);
            }
        });

        buttonDeletAccount.setText("Delete Account");
        buttonDeletAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeletAccountActionPerformed(evt);
            }
        });

        buttonNewAccount.setText("New Account");
        buttonNewAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewAccountActionPerformed(evt);
            }
        });

        jLabel15.setText("Email");

        jLabel8.setText("Id Cabang");

        jLabel10.setText("Jabatan");

        textGaji.setEditable(false);

        jLabel11.setText("Gaji");

        jLabel13.setText("Nama Cabang");

        jLabel14.setText("Alamat Cabang");

        textNamaCabang.setEditable(false);

        textAlamatCabang.setEditable(false);

        toJabatan.setText("Daftar Jabatan");
        toJabatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toJabatanMouseClicked(evt);
            }
        });

        toCabang.setText("Daftar Cabang");
        toCabang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toCabangMouseClicked(evt);
            }
        });

        jLabel12.setText("Search");

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });

        comboboxIDCabang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboboxJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonNewAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(buttonDeletAccount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refreshButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonSaveProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(textAlamat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                            .addComponent(textNama, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textTanggalLahir, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textNomorHp, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textId)
                                            .addComponent(textEmail))
                                        .addGap(51, 51, 51)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(toJabatan)
                                            .addComponent(toCabang)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel13)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(textNamaCabang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addComponent(jLabel14)
                                                            .addGap(18, 18, 18))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addComponent(jLabel11)
                                                            .addGap(73, 73, 73)))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(textAlamatCabang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(comboboxIDCabang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboboxJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(543, 543, 543)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12))
                                    .addComponent(textId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(textNamaCabang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(textAlamatCabang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(textGaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(textNomorHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toCabang))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(comboboxIDCabang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(comboboxJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(buttonSaveProfile)
                        .addGap(36, 36, 36)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNewAccount)
                    .addComponent(buttonDeletAccount)
                    .addComponent(refreshButton)
                    .addComponent(exitButton))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveProfileActionPerformed
        
        String employee_id = textId.getText();
        // Get every text item
        String nama = textNama.getText();
        String nomor_hp = textNomorHp.getText();
        String email = textEmail.getText();
        String alamat = textAlamat.getText();
        String id_cabang = comboboxIDCabang.getSelectedItem().toString();
        String jabatan = comboboxJabatan.getSelectedItem().toString();
        String tanggalLahir = textTanggalLahir.getText();
        
        String sql = String.format(
            "update karyawan " +  
            "set " +
            "nama_pelanggan = '%s', " +
            "nomor_hp= '%s'" +
            "tanggal_lahir = '%s', " +
            "email = '%s', " +
            "alamat = '%s', " +
            "id_cabang = '%s', " +
            "jabatan = '%s', " +
            "where id_karyawan = %s ",
            nama, nomor_hp, tanggalLahir, email, alamat, id_cabang, jabatan, employee_id
        );
        
        System.out.println(sql);
        int numMod = runUpdateQuery(sql);
        populateTableAccount();
        System.out.printf("%s row updated\n", numMod);
    }//GEN-LAST:event_buttonSaveProfileActionPerformed

    private void buttonDeletAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeletAccountActionPerformed
        String employee_id = textId.getText();
        String sql = String.format(
        "delete from karyawan where id_karyawan"
                + " = '%s'", employee_id
        );
        try{
            Statement statement = connection.createStatement();
            System.out.println(sql);
            int numMod = statement.executeUpdate(sql);
            populateTableAccount();
            System.out.printf("%s row deleted\n", numMod);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Ada error!");
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonDeletAccountActionPerformed

    private void buttonNewAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewAccountActionPerformed
        newKaryawan nk=new newKaryawan();
        nk.show();
    }//GEN-LAST:event_buttonNewAccountActionPerformed

    private void tableAccountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAccountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableAccountKeyPressed

    private void toCabangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toCabangMouseClicked
        FrameCabang cb=new FrameCabang();
        cb.show();
    }//GEN-LAST:event_toCabangMouseClicked

    private void toJabatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toJabatanMouseClicked
        FrameJabatan jb=new FrameJabatan();
        jb.show();
    }//GEN-LAST:event_toJabatanMouseClicked

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        populateJabatan();
        populateCabang();
        populateTableAccount();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        try {connection.close();
            } catch (SQLException ex) {
        }
        this.dispose();
    }//GEN-LAST:event_exitButtonMouseClicked

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
            java.util.logging.Logger.getLogger(FrameKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameKaryawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDeletAccount;
    private javax.swing.JButton buttonNewAccount;
    private javax.swing.JButton buttonSaveProfile;
    private javax.swing.JComboBox<String> comboboxIDCabang;
    private javax.swing.JComboBox<String> comboboxJabatan;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JButton refreshButton;
    private javax.swing.JTable tableAccount;
    private javax.swing.JTextField textAlamat;
    private javax.swing.JTextField textAlamatCabang;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textGaji;
    private javax.swing.JTextField textId;
    private javax.swing.JTextField textNama;
    private javax.swing.JTextField textNamaCabang;
    private javax.swing.JTextField textNomorHp;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextField textTanggalLahir;
    private javax.swing.JButton toCabang;
    private javax.swing.JButton toJabatan;
    // End of variables declaration//GEN-END:variables
}
