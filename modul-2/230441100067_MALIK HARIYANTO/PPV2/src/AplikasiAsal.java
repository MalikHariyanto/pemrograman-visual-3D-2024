
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class AplikasiAsal extends javax.swing.JFrame {

    /**
     * Creates new form AplikasiAsal
     */
    public AplikasiAsal() {
        initComponents();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Nama = new javax.swing.JTextField();
        Combo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Nim = new javax.swing.JTextField();
        Simpan = new javax.swing.JButton();
        Warning = new javax.swing.JButton();
        Message = new javax.swing.JButton();
        Error = new javax.swing.JButton();
        BTN = new javax.swing.JButton();
        BTNInput = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();
        Reset = new javax.swing.JButton();
        Next = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jLabel2.setText("Nama");

        Nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaActionPerformed(evt);
            }
        });

        Combo.setText("Combo");
        Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboActionPerformed(evt);
            }
        });

        jLabel3.setText("NIM");

        Simpan.setText("Simpan");
        Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanActionPerformed(evt);
            }
        });

        Warning.setText("Warning");
        Warning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WarningActionPerformed(evt);
            }
        });

        Message.setText("Message");
        Message.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MessageActionPerformed(evt);
            }
        });

        Error.setText("Error");
        Error.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ErrorActionPerformed(evt);
            }
        });

        BTN.setText("BTN");
        BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNActionPerformed(evt);
            }
        });

        BTNInput.setText("BTN Input");
        BTNInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNInputActionPerformed(evt);
            }
        });

        TextArea.setColumns(20);
        TextArea.setRows(5);
        jScrollPane1.setViewportView(TextArea);

        Reset.setText("Reset");

        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(Reset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Next)
                .addGap(76, 76, 76))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(Simpan)
                                .addGap(18, 18, 18)
                                .addComponent(Message)))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Nama)
                                    .addComponent(Nim, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                                .addGap(34, 34, 34)
                                .addComponent(Combo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Warning)
                                .addGap(18, 18, 18)
                                .addComponent(Error)
                                .addGap(18, 18, 18)
                                .addComponent(BTN)
                                .addGap(18, 18, 18)
                                .addComponent(BTNInput))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Combo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Warning)
                        .addComponent(Error)
                        .addComponent(BTN)
                        .addComponent(BTNInput))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Simpan)
                        .addComponent(Message)))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Reset)
                    .addComponent(Next))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaActionPerformed

    private void WarningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WarningActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "perhatian! data yang anda masukkan tidak valid.", 
                "peringatan",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_WarningActionPerformed

    private void SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanActionPerformed
        // TODO add your handling code here:
        String nama,nim;
        
        nama = Nama.getText();
        nim = Nim.getText();
        
        TextArea.setText("Nama : "+nama + "\nim : "+nim);
    }//GEN-LAST:event_SimpanActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
        new Frame2().setVisible(true);
    }//GEN-LAST:event_NextActionPerformed

    private void BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNActionPerformed
        // TODO add your handling code here:
        int pilihan = JOptionPane.showConfirmDialog(null, "apakah anda ingin melanjutkan ?","apakah yakin?",
        JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (pilihan == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Anda memilih Ya!");
        } else if (pilihan == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Anda membatalkan aksi!");
        }else if  (pilihan == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Anda memilih Ya!");
        }
    }//GEN-LAST:event_BTNActionPerformed

    private void BTNInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNInputActionPerformed
        // TODO add your handling code here:
        String nama = JOptionPane.showInputDialog(null, "Masukkan nama anda:","input",
                JOptionPane.QUESTION_MESSAGE);
                
        if (nama != null){
        JOptionPane.showMessageDialog(null,"Halo," + nama + "!");
        } else {
            JOptionPane.showMessageDialog(null, "anda tidak memasukkan nama.");
        }
    }//GEN-LAST:event_BTNInputActionPerformed

    private void ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Isi pesan");
        String Input = JOptionPane.showInputDialog("isi kolom dibawah");
        JOptionPane.showMessageDialog(this, Input);
        JOptionPane.showMessageDialog(null, "WARNING","judul pesan",JOptionPane.ERROR_MESSAGE);
        
    }//GEN-LAST:event_ComboActionPerformed

    private void ErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ErrorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ErrorActionPerformed

    private void MessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MessageActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "proses selesai dengan sukses!",
                "informasi",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_MessageActionPerformed

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
            java.util.logging.Logger.getLogger(AplikasiAsal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiAsal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiAsal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiAsal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiAsal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN;
    private javax.swing.JButton BTNInput;
    private javax.swing.JButton Combo;
    private javax.swing.JButton Error;
    private javax.swing.JButton Message;
    private javax.swing.JTextField Nama;
    private javax.swing.JButton Next;
    private javax.swing.JTextField Nim;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Simpan;
    private javax.swing.JTextArea TextArea;
    private javax.swing.JButton Warning;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
