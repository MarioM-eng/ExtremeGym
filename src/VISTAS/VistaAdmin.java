/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTAS;

import java.awt.Color;

/**
 *
 * @author mario
 */
public class VistaAdmin extends javax.swing.JFrame {

    /**
     * Creates new form VistaAdmin
     */
    public VistaAdmin() {
        initComponents();
        
        jPpersonales.setVisible(true);
        jPclave.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPpersonales = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTnombre = new javax.swing.JTextField();
        jTapellido = new javax.swing.JTextField();
        jTuser = new javax.swing.JTextField();
        jBact = new javax.swing.JButton();
        jLverifiUsu = new javax.swing.JLabel();
        jLverifiAp = new javax.swing.JLabel();
        jLverifiNom = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jBclave = new javax.swing.JButton();
        jBpersonales = new javax.swing.JButton();
        jPclave = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jBactClave = new javax.swing.JButton();
        jPassclave = new javax.swing.JPasswordField();
        jLverifiClave = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(840, 450));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPpersonales.setBackground(new java.awt.Color(255, 255, 255));
        jPpersonales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setText("Actualizar datos personales");
        jPpersonales.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 46, -1, -1));

        jLabel2.setText("Nombre:");
        jPpersonales.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 135, -1, -1));

        jLabel3.setText("Apellido:");
        jPpersonales.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 195, -1, -1));

        jLabel4.setText("Usuario:");
        jPpersonales.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 253, -1, -1));

        jTnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTnombreActionPerformed(evt);
            }
        });
        jPpersonales.add(jTnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 128, 170, 28));
        jPpersonales.add(jTapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 188, 170, 28));
        jPpersonales.add(jTuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 246, 170, 28));

        jBact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/x32/005-editar.png"))); // NOI18N
        jBact.setText("Actualizar");
        jPpersonales.add(jBact, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, -1, -1));

        jLverifiUsu.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLverifiUsu.setForeground(new java.awt.Color(255, 0, 0));
        jPpersonales.add(jLverifiUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 190, 20));

        jLverifiAp.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLverifiAp.setForeground(new java.awt.Color(255, 0, 0));
        jPpersonales.add(jLverifiAp, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 190, 20));

        jLverifiNom.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLverifiNom.setForeground(new java.awt.Color(255, 0, 0));
        jPpersonales.add(jLverifiNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 190, 20));

        getContentPane().add(jPpersonales, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 590, 450));

        jPanel5.setBackground(new java.awt.Color(42, 42, 42));

        jButton6.setBackground(new java.awt.Color(42, 42, 42));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/x32/volver.png"))); // NOI18N
        jButton6.setToolTipText("Volver");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/004-informacion-personal.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Actualizar datos");

        jBclave.setBackground(new java.awt.Color(42, 42, 42));
        jBclave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBclave.setForeground(new java.awt.Color(255, 255, 255));
        jBclave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/x32/011-tarjeta-llave.png"))); // NOI18N
        jBclave.setText("Contrase??a");
        jBclave.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jBclave.setBorderPainted(false);
        jBclave.setFocusPainted(false);
        jBclave.setFocusable(false);
        jBclave.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBclave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBclaveActionPerformed(evt);
            }
        });

        jBpersonales.setBackground(new java.awt.Color(255, 255, 255));
        jBpersonales.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBpersonales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/x32/006-iniciar-la-sesion.png"))); // NOI18N
        jBpersonales.setText("Datos personales");
        jBpersonales.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jBpersonales.setBorderPainted(false);
        jBpersonales.setFocusPainted(false);
        jBpersonales.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBpersonales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBpersonalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBclave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBpersonales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel6)
                .addGap(0, 46, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(41, 41, 41)
                .addComponent(jBpersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBclave, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 450));

        jPclave.setBackground(new java.awt.Color(255, 255, 255));
        jPclave.setInheritsPopupMenu(true);
        jPclave.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel7.setText("Actualizar contrase??a");

        jLabel10.setText("Contrase??a:");

        jBactClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/x32/003-bucle.png"))); // NOI18N
        jBactClave.setText("Actualizar");

        jLverifiClave.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLverifiClave.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jBactClave)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(192, 192, 192))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLverifiClave, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPassclave, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPassclave, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLverifiClave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jBactClave)
                .addGap(85, 85, 85))
        );

        jPclave.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 31, -1, -1));

        getContentPane().add(jPclave, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 590, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        VistaInicio vista = new VistaInicio();
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jBclaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBclaveActionPerformed

        jPpersonales.setVisible(false);
        jPclave.setVisible(true);
        if(!jBclave.isSelected()){
            jBclave.setBackground(new Color(255, 255, 255));
            jBclave.setForeground(new Color(42, 42, 42));
            jBpersonales.setBackground(new Color(42, 42, 42));
            jBpersonales.setForeground(new Color(255, 255, 255));

        }
    }//GEN-LAST:event_jBclaveActionPerformed

    private void jBpersonalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBpersonalesActionPerformed

        jPpersonales.setVisible(true);
        jPclave.setVisible(false);
        if(!jBpersonales.isSelected()){
            jBclave.setBackground(new Color(42, 42, 42));
            jBclave.setForeground(new java.awt.Color(255, 255, 255));
            jBpersonales.setBackground(new Color(255, 255, 255));
            jBpersonales.setForeground(new Color(42, 42, 42));

        }
    }//GEN-LAST:event_jBpersonalesActionPerformed

    private void jTnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTnombreActionPerformed

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
            java.util.logging.Logger.getLogger(VistaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBact;
    public javax.swing.JButton jBactClave;
    public javax.swing.JButton jBclave;
    public javax.swing.JButton jBpersonales;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLverifiAp;
    public javax.swing.JLabel jLverifiClave;
    public javax.swing.JLabel jLverifiNom;
    public javax.swing.JLabel jLverifiUsu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JPasswordField jPassclave;
    private javax.swing.JPanel jPclave;
    private javax.swing.JPanel jPpersonales;
    public javax.swing.JTextField jTapellido;
    public javax.swing.JTextField jTnombre;
    public javax.swing.JTextField jTuser;
    // End of variables declaration//GEN-END:variables
}
