/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author cgome
 */
public class MenuPrincipal extends javax.swing.JFrame {

    String idFun,nomFun;
    
    public MenuPrincipal(String idFunc,String nomFunc) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        
        try{
             Image img=ImageIO.read(new File("icoescu.png"));
             this.setIconImage(img);
         } catch(Exception e){
             System.err.println(e);
         }
        
        idFun=idFunc;
        nomFun=nomFunc;
        jLFunc.setText("Funcionario: "+nomFun);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jBVehiculo = new javax.swing.JButton();
        jBConductor = new javax.swing.JButton();
        jBAseguradora = new javax.swing.JButton();
        jBReporte = new javax.swing.JButton();
        jBEstudiante = new javax.swing.JButton();
        jBFuncionario = new javax.swing.JButton();
        jBAsistente = new javax.swing.JButton();
        jBRuta = new javax.swing.JButton();
        jLFunc = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMRegistro = new javax.swing.JMenu();
        jMIFuncionario = new javax.swing.JMenuItem();
        jMIAseguradora = new javax.swing.JMenuItem();
        jMIEstudiante = new javax.swing.JMenuItem();
        jMIConductor = new javax.swing.JMenuItem();
        jMIAsistente = new javax.swing.JMenuItem();
        jMIVehiculo = new javax.swing.JMenuItem();
        jMISOAT = new javax.swing.JMenuItem();
        jMITecno = new javax.swing.JMenuItem();
        jMRuta = new javax.swing.JMenu();
        jMConsulta = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal - Control e Ingreso de Busetas CIB");

        jBVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        jBVehiculo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jBVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bus.png"))); // NOI18N
        jBVehiculo.setText("Vehiculo");
        jBVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBVehiculo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBVehiculo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVehiculoActionPerformed(evt);
            }
        });

        jBConductor.setBackground(new java.awt.Color(255, 255, 255));
        jBConductor.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jBConductor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conductor.png"))); // NOI18N
        jBConductor.setText("Conductor");
        jBConductor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBConductor.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBConductor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConductorActionPerformed(evt);
            }
        });

        jBAseguradora.setBackground(new java.awt.Color(255, 255, 255));
        jBAseguradora.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jBAseguradora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/aseguradora.png"))); // NOI18N
        jBAseguradora.setText("Aseguradora");
        jBAseguradora.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBAseguradora.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBAseguradora.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBAseguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAseguradoraActionPerformed(evt);
            }
        });

        jBReporte.setBackground(new java.awt.Color(255, 255, 255));
        jBReporte.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jBReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lista.png"))); // NOI18N
        jBReporte.setText("Reportes");
        jBReporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBReporte.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBReporte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBReporteActionPerformed(evt);
            }
        });

        jBEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        jBEstudiante.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jBEstudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/estudiante.png"))); // NOI18N
        jBEstudiante.setText("Estudiante");
        jBEstudiante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBEstudiante.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBEstudiante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEstudianteActionPerformed(evt);
            }
        });

        jBFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        jBFuncionario.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jBFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/funcionario.png"))); // NOI18N
        jBFuncionario.setText("Funcionario");
        jBFuncionario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBFuncionario.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBFuncionario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFuncionarioActionPerformed(evt);
            }
        });

        jBAsistente.setBackground(new java.awt.Color(255, 255, 255));
        jBAsistente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jBAsistente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/asistente.png"))); // NOI18N
        jBAsistente.setText("Asistente");
        jBAsistente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBAsistente.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBAsistente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBAsistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAsistenteActionPerformed(evt);
            }
        });

        jBRuta.setBackground(new java.awt.Color(255, 255, 255));
        jBRuta.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jBRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ruta.png"))); // NOI18N
        jBRuta.setText("Ruta");
        jBRuta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBRuta.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBRuta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRutaActionPerformed(evt);
            }
        });

        jLFunc.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLFunc.setText("Funcionario");

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMRegistro.setText("Registro");

        jMIFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        jMIFuncionario.setText("Funcionario");
        jMIFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIFuncionarioActionPerformed(evt);
            }
        });
        jMRegistro.add(jMIFuncionario);

        jMIAseguradora.setBackground(new java.awt.Color(255, 255, 255));
        jMIAseguradora.setText("Aseguradora");
        jMIAseguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIAseguradoraActionPerformed(evt);
            }
        });
        jMRegistro.add(jMIAseguradora);

        jMIEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        jMIEstudiante.setText("Estudiante");
        jMIEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIEstudianteActionPerformed(evt);
            }
        });
        jMRegistro.add(jMIEstudiante);

        jMIConductor.setBackground(new java.awt.Color(255, 255, 255));
        jMIConductor.setText("Conductor");
        jMIConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIConductorActionPerformed(evt);
            }
        });
        jMRegistro.add(jMIConductor);

        jMIAsistente.setBackground(new java.awt.Color(255, 255, 255));
        jMIAsistente.setText("Asistente");
        jMIAsistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIAsistenteActionPerformed(evt);
            }
        });
        jMRegistro.add(jMIAsistente);

        jMIVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        jMIVehiculo.setText("Vehiculo");
        jMIVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIVehiculoActionPerformed(evt);
            }
        });
        jMRegistro.add(jMIVehiculo);

        jMISOAT.setBackground(new java.awt.Color(255, 255, 255));
        jMISOAT.setText("SOAT");
        jMISOAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMISOATActionPerformed(evt);
            }
        });
        jMRegistro.add(jMISOAT);

        jMITecno.setBackground(new java.awt.Color(255, 255, 255));
        jMITecno.setText("Tecnomecanica");
        jMITecno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMITecnoActionPerformed(evt);
            }
        });
        jMRegistro.add(jMITecno);

        jMenuBar1.add(jMRegistro);

        jMRuta.setText("Ruta");
        jMenuBar1.add(jMRuta);

        jMConsulta.setText("Consulta");
        jMenuBar1.add(jMConsulta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLFunc)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBAseguradora, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBAseguradora, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jLFunc))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMIFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIFuncionarioActionPerformed
        Funcionario miVent = new Funcionario(idFun, nomFun);
        miVent.setVisible(true);
    }//GEN-LAST:event_jMIFuncionarioActionPerformed

    private void jBVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVehiculoActionPerformed
        Vehiculo miVent = new Vehiculo(idFun, nomFun, null);
        miVent.setVisible(true);
    }//GEN-LAST:event_jBVehiculoActionPerformed

    private void jBConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConductorActionPerformed
        Conductor miVent = new Conductor(idFun, nomFun);
        miVent.setVisible(true);
    }//GEN-LAST:event_jBConductorActionPerformed

    private void jBAseguradoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAseguradoraActionPerformed
        Aseguradora miVent;
        try {
            miVent = new Aseguradora(idFun, nomFun);
            miVent.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jBAseguradoraActionPerformed

    private void jBReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBReporteActionPerformed
        
    }//GEN-LAST:event_jBReporteActionPerformed

    private void jBEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEstudianteActionPerformed
        Estudiante miVent = new Estudiante(idFun, nomFun);
        miVent.setVisible(true);
    }//GEN-LAST:event_jBEstudianteActionPerformed

    private void jBFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFuncionarioActionPerformed
        Funcionario miVent = new Funcionario(idFun, nomFun);
        miVent.setVisible(true);
    }//GEN-LAST:event_jBFuncionarioActionPerformed

    private void jBAsistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAsistenteActionPerformed
        Asistente miVent = new Asistente(idFun, nomFun);
        miVent.setVisible(true);
    }//GEN-LAST:event_jBAsistenteActionPerformed

    private void jBRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBRutaActionPerformed

    private void jMIAseguradoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIAseguradoraActionPerformed
        Aseguradora miVent;
        try {
            miVent = new Aseguradora(idFun, nomFun);
            miVent.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMIAseguradoraActionPerformed

    private void jMIEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEstudianteActionPerformed
        Estudiante miVent = new Estudiante(idFun, nomFun);
        miVent.setVisible(true);
    }//GEN-LAST:event_jMIEstudianteActionPerformed

    private void jMIConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIConductorActionPerformed
        Conductor miVent = new Conductor(idFun, nomFun);
        miVent.setVisible(true);
    }//GEN-LAST:event_jMIConductorActionPerformed

    private void jMIAsistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIAsistenteActionPerformed
        Asistente miVent = new Asistente(idFun, nomFun);
        miVent.setVisible(true);
    }//GEN-LAST:event_jMIAsistenteActionPerformed

    private void jMIVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIVehiculoActionPerformed
        Vehiculo miVent = new Vehiculo(idFun, nomFun,null);
        miVent.setVisible(true);
    }//GEN-LAST:event_jMIVehiculoActionPerformed

    private void jMISOATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMISOATActionPerformed
        SOAT miVent = new SOAT(idFun, nomFun,null,false);
        miVent.setVisible(true);
    }//GEN-LAST:event_jMISOATActionPerformed

    private void jMITecnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMITecnoActionPerformed
        Tecnomecanica miVent = new Tecnomecanica(idFun, nomFun,null,false);
        miVent.setVisible(true);
    }//GEN-LAST:event_jMITecnoActionPerformed

    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MenuPrincipal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAseguradora;
    private javax.swing.JButton jBAsistente;
    private javax.swing.JButton jBConductor;
    private javax.swing.JButton jBEstudiante;
    private javax.swing.JButton jBFuncionario;
    private javax.swing.JButton jBReporte;
    private javax.swing.JButton jBRuta;
    private javax.swing.JButton jBVehiculo;
    private javax.swing.JLabel jLFunc;
    private javax.swing.JMenu jMConsulta;
    private javax.swing.JMenuItem jMIAseguradora;
    private javax.swing.JMenuItem jMIAsistente;
    private javax.swing.JMenuItem jMIConductor;
    private javax.swing.JMenuItem jMIEstudiante;
    private javax.swing.JMenuItem jMIFuncionario;
    private javax.swing.JMenuItem jMISOAT;
    private javax.swing.JMenuItem jMITecno;
    private javax.swing.JMenuItem jMIVehiculo;
    private javax.swing.JMenu jMRegistro;
    private javax.swing.JMenu jMRuta;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}
