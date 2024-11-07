/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ASUS
 */
public class aplikasiManagemen extends javax.swing.JFrame {
    Connection conn;
    private DefaultTableModel modelKaryawan, modelProyek, modelTransaksi;
    /**
     * Creates new form aplikasiManagemen
     */
    public aplikasiManagemen() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        conn = koneksi.getConnection();
        
        tabelKaryawan();
        tabelProyek();
        tabelTransaksi();
        
        loadDataKaryawan();
        loadDataProyek();
        loadDataTransaksi();
        loadComboBoxKaryawan();
        loadComboBoxProyek();
        
         KeyListener keyListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                checkButtonKaryawan();
                checkButtonProyek();
                checkButtonTransaksi();
            }
        };
         
        idK.addKeyListener(keyListener);
        namaK.addKeyListener(keyListener);
        departemen.addKeyListener(keyListener);
        jabatan.addKeyListener(keyListener);
        
        idP.addKeyListener(keyListener);
        namaP.addKeyListener(keyListener);
        durasiP.addKeyListener(keyListener);
        
        comboIdK.addKeyListener(keyListener);
        comboIdP.addKeyListener(keyListener);
        durasiT.addKeyListener(keyListener);
         
        checkButtonKaryawan();
        checkButtonProyek();
        checkButtonTransaksi();
        
        koreksi(idK);
        koreksi(idP);
        koreksi(durasiP);
        koreksi(durasiT);
    }
    
         private void checkButtonKaryawan() {
        boolean id_isi = !idK.getText().trim().isEmpty();
        boolean nama_isi = !namaK.getText().trim().isEmpty();
        boolean departemen_isi = !departemen.getText().trim().isEmpty();
        boolean jabatan_isi = !jabatan.getText().trim().isEmpty();

  
        if (id_isi && !nama_isi && !departemen_isi && !jabatan_isi) {
            deleteK.setEnabled(true);
            updateK.setEnabled(false);
            tambahK.setEnabled(false);
        } else if (id_isi && nama_isi && departemen_isi && jabatan_isi) {
            deleteK.setEnabled(false);
            updateK.setEnabled(true);
            tambahK.setEnabled(false);
        } else if (!id_isi && nama_isi && departemen_isi && jabatan_isi) {
            deleteK.setEnabled(false);
            updateK.setEnabled(false);
            tambahK.setEnabled(true);
        } else {
            deleteK.setEnabled(false);
            updateK.setEnabled(false);
            tambahK.setEnabled(false);
        }
    }
    
    private void checkButtonProyek() {
        boolean ID_isi = !idP.getText().trim().isEmpty();
        boolean nama_proyek_isi = !namaP.getText().trim().isEmpty();
        boolean durasi_isi = !durasiP.getText().trim().isEmpty();

 
        if (ID_isi && !nama_proyek_isi && !durasi_isi ) {
            deleteP.setEnabled(true);
            updateP.setEnabled(false);
            tambahP.setEnabled(false);
        } else if (ID_isi && nama_proyek_isi && durasi_isi ) {
            deleteP.setEnabled(false);
            updateP.setEnabled(true);
            tambahP.setEnabled(false);
        } else if (!ID_isi && nama_proyek_isi && durasi_isi ) {
            deleteP.setEnabled(false);
            updateP.setEnabled(false);
            tambahP.setEnabled(true);
        } else {
            deleteP.setEnabled(false);
            updateP.setEnabled(false);
            tambahP.setEnabled(false);
        }
    }

    private void checkButtonTransaksi() {
        boolean IdK_isi = comboIdK.getSelectedItem() != null; 
        boolean idP_isi = comboIdP.getSelectedItem() != null; 
        boolean peran_isi = !durasiT.getText().trim().isEmpty(); 

        if (IdK_isi && idP_isi && !peran_isi) {
            deleteT.setEnabled(true);
            updateT.setEnabled(false);
            tambahT.setEnabled(false);
        } else if (IdK_isi && idP_isi && peran_isi) {
            deleteT.setEnabled(false);
            updateT.setEnabled(true);
            tambahT.setEnabled(true);
        } else {
            deleteT.setEnabled(false);
            updateT.setEnabled(false);
            tambahT.setEnabled(false);
        }
    }
    
        private void tabelKaryawan(){
        modelKaryawan = new DefaultTableModel();
        tableK.setModel(modelKaryawan);
        
        modelKaryawan.addColumn("ID");
        modelKaryawan.addColumn("Nama");
        modelKaryawan.addColumn("Jabatan");
        modelKaryawan.addColumn("Departemen");
    }
    private void tabelProyek(){
        modelProyek = new DefaultTableModel();
        tabelP.setModel(modelProyek);
        
        modelProyek.addColumn("ID");
        modelProyek.addColumn("Nama Proyek");
        modelProyek.addColumn("Durasi Pengerjaan");
    }
    private void tabelTransaksi(){
        modelTransaksi = new DefaultTableModel();
        tabelT.setModel(modelTransaksi);
        
        modelTransaksi.addColumn("Karyawan");
        modelTransaksi.addColumn("Proyek");
        modelTransaksi.addColumn("Durasi");
    }

     private void loadDataKaryawan(){
        modelKaryawan.setRowCount(0);

      try {
          String sql = "SELECT * FROM karyawan";
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
             modelKaryawan.addRow(new Object[]{
             rs.getInt("id"),
             rs.getString("nama"),
             rs.getString("jabatan"),
             rs.getString("departemen")
           });
          }
      } catch (SQLException e) {
         System.out.println("Error Save Data" + e.getMessage());
       }
    }
    
    private void loadDataProyek() {
      modelProyek.setRowCount(0);

      try {
          String sql = "SELECT * FROM proyek ";
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
             modelProyek.addRow(new Object[]{
             rs.getInt("id"),
             rs.getString("nama_proyek"),
             String.format("%d Minggu",rs.getInt("durasi_pengerjaan"))
           });
          }
      } catch (SQLException e) {
         System.out.println("Error Save Data" + e.getMessage());
       }
    }
    
    private void loadDataTransaksi(){
         modelTransaksi.setRowCount(0);
        try {
            String sql = "SELECT karyawan.nama AS nama_karyawan, proyek.nama_proyek, transaksi.durasi " +
                        "FROM transaksi " +
                        "JOIN karyawan ON transaksi.id_karyawan = karyawan.id " +
                        "JOIN proyek ON transaksi.id_proyek = proyek.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                modelTransaksi.addRow(new Object[]{
                    rs.getString("nama_karyawan"),
                    rs.getString("nama_proyek"),
                    rs.getString("durasi")
            });
        }
        } catch (SQLException e) {
            System.out.println("Error loading Transaksi data: " + e.getMessage());
        }      
    }
    
    private void loadComboBoxKaryawan (){
         comboIdK.removeAllItems();
        try {
            String sql = "SELECT id, nama FROM karyawan";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                String idNama = rs.getInt("id") + " - " + rs.getString("nama");
                comboIdK.addItem(idNama);
            }
        } catch (SQLException e) {
            System.out.println("Error loading Karyawan ComboBox: " + e.getMessage());
        }
    }
    
    private void loadComboBoxProyek(){
          comboIdP.removeAllItems();
        try {
            String sql = "SELECT id, nama_proyek FROM proyek";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String idNamaProyek = rs.getInt("id") + " - " + rs.getString("nama_proyek");
                comboIdP.addItem(idNamaProyek); 
            }
        } catch (SQLException e) {
            System.out.println("Error loading Proyek ComboBox: " + e.getMessage());
        }    
    }
 
        // ======================================Karyawan
        //Simpan Data
    private void saveDataKaryawan() {
      try{
         String sql = "INSERT INTO karyawan (nama, jabatan, departemen) VALUES (?, ?, ?)";
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, namaK.getText());
         ps.setString(3, jabatan.getText());
         ps.setString(2, departemen.getText());
         ps.executeUpdate();
         JOptionPane.showMessageDialog(this, "Data saved successfully");
         //
         loadDataKaryawan();
         loadComboBoxKaryawan();
       } catch (SQLException e) {
         System.out.println("Error Save Data" + e.getMessage());
       }
     }
    //End Simpan Data
    
      //Update Data
    private void updateDataKaryawan() {
    try {
          String sql = "UPDATE karyawan SET nama = ?, jabatan = ?, departemen = ? WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, namaK.getText());
          ps.setString(2, jabatan.getText());
          ps.setString(3, departemen.getText());
          ps.setInt(4, Integer.parseInt(idK.getText()));
          
          if (ps.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(this, "Data updated successfully");
            loadDataKaryawan();
            loadComboBoxKaryawan();
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan untuk ID tersebut!", "Update Error", JOptionPane.WARNING_MESSAGE);
        }
      }  catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }
     }
    //End Simpan Data

    //Delete Data
    private void deleteDataKaryawan() {
     try  {
          String sql = "DELETE FROM karyawan WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(idK.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataKaryawan();
          loadComboBoxKaryawan();
     } catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }
    }
    //End Delete Data
    // =============================================Karyawan
    
    //===================================Proyek
    
      //Simpan Data
    private void saveDataProyek() {
    try{
         int Durasi = Integer.parseInt(durasiP.getText());
         if (Durasi < 0) {
            JOptionPane.showMessageDialog(this, "Durasi Pengerjaan tidak boleh kurang dari 0!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
         }
         String sql = "INSERT INTO proyek (nama_proyek, durasi_pengerjaan) VALUES (?, ?)";
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, namaP.getText());
         ps.setInt(2, Durasi);
         ps.executeUpdate();
         JOptionPane.showMessageDialog(this, "Data saved successfully");
         //
         loadDataProyek();
         loadComboBoxProyek();
       } catch (SQLException e) {
         System.out.println("Error Save Data" + e.getMessage());
       } 
     }
    //End Simpan Data
    
    //Update Data
    private void updateDataProyek() {
    try {
           int Durasi = Integer.parseInt(durasiP.getText());
           if (Durasi < 0) {
               JOptionPane.showMessageDialog(this, "Durasi Pengerjaan tidak boleh kurang dari 0!", "Input Error", JOptionPane.WARNING_MESSAGE);
               return;
            }
          String sql = "UPDATE proyek SET nama_proyek = ?, durasi_pengerjaan = ? WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, namaP.getText());
          ps.setInt(2, Durasi);
          ps.setInt(3, Integer.parseInt(idP.getText()));
          if (ps.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(this, "Data updated successfully");
            loadDataProyek();
            loadComboBoxProyek();
        } else {
            JOptionPane.showMessageDialog(this, "Data dengan ID tersebut tidak ditemukan!", "Update Error", JOptionPane.WARNING_MESSAGE);
        }
      }  catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }
     }
    //End Simpan Data

    //Delete Data
    private void deleteDataProyek() {
     try  {
          String sql = "DELETE FROM proyek WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(idP.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataProyek();
          loadComboBoxProyek();
     } catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }
    }
    //End Delete Data
 
    private void tambahDataTransaksi() {
        try {
            int durasi = Integer.parseInt(durasiT.getText());
            if (durasi < 1) {
                JOptionPane.showMessageDialog(this, "Durasi Pengerjaan tidak boleh kurang dari 1!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String sql = "INSERT INTO transaksi (id_karyawan, id_proyek, durasi) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, Integer.parseInt(((String) comboIdK.getSelectedItem()).split(" - ")[0].trim()));
            ps.setInt(2, Integer.parseInt(((String) comboIdP.getSelectedItem()).split(" - ")[0].trim()));
            ps.setInt(3, durasi);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data transaksi berhasil ditambahkan!");
            loadDataTransaksi();
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(this, "Data transaksi dengan ID Karyawan dan ID Proyek ini sudah ada.", "Duplicate Entry Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error saat menambahkan data transaksi: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("Error saat menambahkan data transaksi: " + e.getMessage());
        }
    }
    
    
    private void updateDataTransaksi() {
        
        try {
            int durasi = Integer.parseInt(durasiT.getText());
            if (durasi < 1) {
                JOptionPane.showMessageDialog(this, "Durasi Pengerjaan tidak boleh kurang dari 1!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "UPDATE transaksi SET durasi = ? WHERE id_karyawan = ? AND id_proyek = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, durasi);
            ps.setInt(2, Integer.parseInt(comboIdK.getSelectedItem().toString().split(" - ")[0].trim()));
            ps.setInt(3, Integer.parseInt(comboIdP.getSelectedItem().toString().split(" - ")[0].trim()));
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(this, "Data transaksi berhasil diupdate!");
            } else {
                JOptionPane.showMessageDialog(this, "Data transaksi tidak ditemukan!", "Update Data", JOptionPane.WARNING_MESSAGE);
            }
            loadDataTransaksi(); 
        } catch (SQLException e) {
            System.out.println("Error saat mengupdate data transaksi: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error konversi ID: " + e.getMessage());
        }
    }
    
    private void deleteDataTransaksi() {
try {
            String sql = "DELETE FROM transaksi WHERE id_karyawan = ? AND id_proyek = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(((String) comboIdK.getSelectedItem()).split(" - ")[0].trim()));
            ps.setInt(2, Integer.parseInt(((String) comboIdP.getSelectedItem()).split(" - ")[0].trim()));

             if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(this, "Data transaksi berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Transaksi tidak ditemukan!", "Delete Data", JOptionPane.WARNING_MESSAGE);
            }

            loadDataTransaksi();
        } catch (SQLException e) {
            System.out.println("Error saat menghapus data transaksi: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error konversi ID: " + e.getMessage());
        }
    }  
// ===================TRANSAKSI
    
    private void koreksi(JTextField textField){
        textField.addKeyListener(new KeyAdapter(){
            public void keyTyped (KeyEvent e){
                if (!Character.isDigit(e.getKeyChar())){
                    e.consume ();
                    JOptionPane.showMessageDialog(null, "hanya boleh diisi dengan angka",
                            "input error",JOptionPane.ERROR_MESSAGE);
                }
            }
            });       
    }
    private void resetK(){
        idK.setText("");
        namaK.setText("");
        departemen.setText("");
        jabatan.setText("");
        
        deleteK.setEnabled(false);
        updateK.setEnabled(false);
        tambahK.setEnabled(false);
    }
    
    private void resetP(){
        idP.setText("");
        namaP.setText("");
        durasiP.setText("");
        
        deleteP.setEnabled(false);
        updateP.setEnabled(false);
        tambahP.setEnabled(false);
    }
    private void resetT(){
        comboIdK.setSelectedItem(null);
        comboIdP.setSelectedItem(null);
        durasiT.setText("");
        
        deleteT.setEnabled(false);
        updateT.setEnabled(false);
        tambahT.setEnabled(false);
    }
    
    private void Exit(){
        int exit = JOptionPane.showConfirmDialog(null,"Keluar permainan?","Keluar",JOptionPane.YES_NO_OPTION);
        if (exit == JOptionPane.YES_OPTION) {
            System.exit(0);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        tabelK = new javax.swing.JScrollPane();
        tableK = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idK = new javax.swing.JTextField();
        namaK = new javax.swing.JTextField();
        jabatan = new javax.swing.JTextField();
        departemen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tambahK = new javax.swing.JButton();
        updateK = new javax.swing.JButton();
        deleteK = new javax.swing.JButton();
        resetK = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelP = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        idP = new javax.swing.JTextField();
        namaP = new javax.swing.JTextField();
        durasiP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tambahP = new javax.swing.JButton();
        deleteP = new javax.swing.JButton();
        updateP = new javax.swing.JButton();
        resetP = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelT = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        durasiT = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        comboIdK = new javax.swing.JComboBox<>();
        comboIdP = new javax.swing.JComboBox<>();
        tambahT = new javax.swing.JButton();
        updateT = new javax.swing.JButton();
        deleteT = new javax.swing.JButton();
        resetT = new javax.swing.JButton();
        exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 101, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Georgia", 3, 28)); // NOI18N
        jLabel1.setText("APLIKASI MANAGEMEN KARYAWAN DAN PROYEK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(40, 40, 40))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(658, 10));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 907, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(10, 310));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.LINE_END);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setPreferredSize(new java.awt.Dimension(10, 310));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(11, 25, 44));
        jPanel3.setPreferredSize(new java.awt.Dimension(870, 433));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(30, 62, 98));

        tableK.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelK.setViewportView(tableK);

        jLabel2.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 244, 183));
        jLabel2.setText("FORM KARYAWAN");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("ID                   ");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("NAMA           ");

        idK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idKActionPerformed(evt);
            }
        });

        jabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jabatanActionPerformed(evt);
            }
        });

        departemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departemenActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("JABATAN       ");

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("DEPARTEMEN ");

        tambahK.setText("TAMBAH");
        tambahK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahKActionPerformed(evt);
            }
        });

        updateK.setText("UPDATE");
        updateK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateKActionPerformed(evt);
            }
        });

        deleteK.setText("DELETE");
        deleteK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteKActionPerformed(evt);
            }
        });

        resetK.setText("RESET");
        resetK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(tambahK, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetK)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(namaK, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(departemen, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idK, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)))
                .addComponent(tabelK)
                .addGap(16, 16, 16))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(namaK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(departemen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateK)
                            .addComponent(deleteK)
                            .addComponent(tambahK)
                            .addComponent(resetK)))
                    .addComponent(tabelK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Karyawan", jPanel7);

        jPanel8.setBackground(new java.awt.Color(0, 106, 103));

        jPanel9.setBackground(new java.awt.Color(30, 62, 98));

        tabelP.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelP);

        jLabel7.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 244, 183));
        jLabel7.setText("FORM PROYEK");

        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("ID");

        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("NAMA PROJEK");

        idP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPActionPerformed(evt);
            }
        });

        durasiP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durasiPActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("DURASI");

        tambahP.setText("TAMBAH");
        tambahP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahPActionPerformed(evt);
            }
        });

        deleteP.setText("DELETE");
        deleteP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePActionPerformed(evt);
            }
        });

        updateP.setText("UPDATE");
        updateP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePActionPerformed(evt);
            }
        });

        resetP.setText("RESET");
        resetP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(durasiP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(namaP, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idP, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(tambahP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addGap(16, 16, 16))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(idP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(namaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(durasiP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambahP)
                            .addComponent(updateP)
                            .addComponent(deleteP)
                            .addComponent(resetP)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Proyek", jPanel8);

        jPanel6.setBackground(new java.awt.Color(0, 106, 103));

        jPanel11.setBackground(new java.awt.Color(30, 62, 98));

        tabelT.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tabelT);

        jLabel16.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 244, 183));
        jLabel16.setText("FORM TRANSAKSI");

        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("ID KARYAWAN");

        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("ID PROYEK");

        durasiT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durasiTActionPerformed(evt);
            }
        });

        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("DURASI");

        comboIdK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboIdK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIdKActionPerformed(evt);
            }
        });

        comboIdP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tambahT.setText("TAMBAH");
        tambahT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahTActionPerformed(evt);
            }
        });

        updateT.setText("UPDATE");
        updateT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTActionPerformed(evt);
            }
        });

        deleteT.setText("DELETE");
        deleteT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTActionPerformed(evt);
            }
        });

        resetT.setText("RESET");
        resetT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboIdP, 0, 208, Short.MAX_VALUE)
                            .addComponent(comboIdK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(durasiT, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(tambahT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetT, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addGap(16, 16, 16))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboIdK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(comboIdP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(durasiT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambahT)
                            .addComponent(updateT)
                            .addComponent(deleteT)
                            .addComponent(resetT)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Transaksi", jPanel6);

        jPanel3.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 9, 810, 380));
        jTabbedPane1.getAccessibleContext().setAccessibleName("tab1");

        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel3.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 400, 80, -1));

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idKActionPerformed

    private void jabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jabatanActionPerformed

    private void idPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPActionPerformed

    private void durasiPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durasiPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_durasiPActionPerformed

    private void departemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departemenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departemenActionPerformed

    private void durasiTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durasiTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_durasiTActionPerformed

    private void comboIdKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIdKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboIdKActionPerformed

    private void updateKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateKActionPerformed
        // TODO add your handling code here:
        updateDataKaryawan();
        resetK();
    }//GEN-LAST:event_updateKActionPerformed

    private void tambahPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahPActionPerformed
        // TODO add your handling code here:
        saveDataProyek();
        resetP();
    }//GEN-LAST:event_tambahPActionPerformed

    private void updateTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateTActionPerformed
        // TODO add your handling code here:
        updateDataTransaksi();
        resetT();
    }//GEN-LAST:event_updateTActionPerformed

    private void tambahKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahKActionPerformed
        // TODO add your handling code here:
        saveDataKaryawan();
        resetK();
    }//GEN-LAST:event_tambahKActionPerformed

    private void deleteKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteKActionPerformed
        // TODO add your handling code here:
        deleteDataKaryawan();
        resetK();
    }//GEN-LAST:event_deleteKActionPerformed

    private void updatePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePActionPerformed
        // TODO add your handling code here:
        updateDataProyek();
        resetP();
    }//GEN-LAST:event_updatePActionPerformed

    private void deletePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePActionPerformed
        // TODO add your handling code here:
        deleteDataProyek();
        resetP();
    }//GEN-LAST:event_deletePActionPerformed

    private void tambahTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahTActionPerformed
        // TODO add your handling code here:
        tambahDataTransaksi();
        resetT();
    }//GEN-LAST:event_tambahTActionPerformed

    private void deleteTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTActionPerformed
        // TODO add your handling code here:
        deleteDataTransaksi();
        resetT();
    }//GEN-LAST:event_deleteTActionPerformed

    private void resetKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetKActionPerformed
        // TODO add your handling code here:
        resetK();
    }//GEN-LAST:event_resetKActionPerformed

    private void resetPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPActionPerformed
        // TODO add your handling code here:
        resetP();
    }//GEN-LAST:event_resetPActionPerformed

    private void resetTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetTActionPerformed
        // TODO add your handling code here
        resetT();
        
    }//GEN-LAST:event_resetTActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        Exit();
    }//GEN-LAST:event_exitActionPerformed

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
            java.util.logging.Logger.getLogger(aplikasiManagemen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(aplikasiManagemen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(aplikasiManagemen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(aplikasiManagemen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new aplikasiManagemen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboIdK;
    private javax.swing.JComboBox<String> comboIdP;
    private javax.swing.JButton deleteK;
    private javax.swing.JButton deleteP;
    private javax.swing.JButton deleteT;
    private javax.swing.JTextField departemen;
    private javax.swing.JTextField durasiP;
    private javax.swing.JTextField durasiT;
    private javax.swing.JButton exit;
    private javax.swing.JTextField idK;
    private javax.swing.JTextField idP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jabatan;
    private javax.swing.JTextField namaK;
    private javax.swing.JTextField namaP;
    private javax.swing.JButton resetK;
    private javax.swing.JButton resetP;
    private javax.swing.JButton resetT;
    private javax.swing.JScrollPane tabelK;
    private javax.swing.JTable tabelP;
    private javax.swing.JTable tabelT;
    private javax.swing.JTable tableK;
    private javax.swing.JButton tambahK;
    private javax.swing.JButton tambahP;
    private javax.swing.JButton tambahT;
    private javax.swing.JButton updateK;
    private javax.swing.JButton updateP;
    private javax.swing.JButton updateT;
    // End of variables declaration//GEN-END:variables
}
