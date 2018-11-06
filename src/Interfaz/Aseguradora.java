/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import controlador.AseguradoraDAO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.AseguradoraVO;

/**
 *
 * @author SFS
 */
public class Aseguradora extends javax.swing.JFrame {

    //declaración de variables
    String nombreAseg,idFuncAseg,nombFunc;
    int idEd,fila,filaP;
    boolean estadoAseg;
    Date fechaEd;
    AseguradoraVO transDatosAseg;
    AseguradoraDAO DBAseguradora;
    DefaultTableModel modelo;
    String datos[];
    ArrayList<String> listaAseg;
    boolean validacionGuardar=false;
    
    
    
    //metodo constructor
    public Aseguradora(String idFunc,String nomFunc) throws SQLException{
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        jTAseguradora.setBackground(Color.WHITE);
        
        DBAseguradora = new AseguradoraDAO();
        idFuncAseg = idFunc;
        nombFunc=nomFunc;
        jLFunc.setText("Funcionario: "+nombFunc);
        bloquear();
        IDActual();
        datos = new String[2];
        listaAseg = new ArrayList<>();
        modelo = new DefaultTableModel();
        llamarDatosTabla();
        
        //permite colocar el icono 
        try{
             Image img=ImageIO.read(new File("icoescu.png"));
             this.setIconImage(img);
         } catch(Exception e){
             System.err.println(e);
         }
        
              
    }
     //toma el nombre del usuario para mostrarnos 
    // que usuario esta realizando las acciones 
    public void IDActual() throws SQLException{
        idEd = DBAseguradora.identificarIDAcual();
        jTFCodigo.setText(String.valueOf(idEd+1));
    }
    //bloquear las cajas
     public void bloquear(){
        jTFNombre.setEnabled(false);
        jBGuardar.setEnabled(false);
        jBEditar.setEnabled(true);
        jBBuscar.setEnabled(true);
        jBEliminar.setEnabled(true);
               
    }
     //activar cajas 
     public void activar(){
        jTFNombre.setEnabled(true);
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBBuscar.setEnabled(false);
        jBEliminar.setEnabled(false);
        
        
    }
     
         public void llamarDatosTabla(){
        //este toma los datos de la base de datos ademas crea la tabla en la cual se especifica los datos que se van a visualizar 
        modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        this.jTAseguradora.setModel(modelo);
        this.jTAseguradora.getColumn(jTAseguradora.getColumnName(0)).setMaxWidth(100);
        
        listaAseg=DBAseguradora.listaAseguradora();
        for (int i = 0; i < listaAseg.size(); i=i+2) {
            
            datos[0]=String.valueOf(Integer.parseInt(listaAseg.get(i)));
            datos[1]=listaAseg.get(i+1);
            modelo.addRow(datos);
            
        }
    }
     // validacion para que ninguna caja este vacia
      public boolean validarDatos(){
        
        if(jTFNombre.getText().equals("")||jTFNombre.getText().equals(" ")||jTFNombre.getText().equals(null)){
            JOptionPane.showMessageDialog(null, "Ingresar Nombre", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
            jTFNombre.requestFocus();
            return false;
        }else{
            return true;
        }
        
    }
      //ingresar los datos al transporte de datos 
      public boolean guardarDatos(){
        
        boolean validar= validarDatos();
        
        if(validar!=false)
        {
            idEd = Integer.parseInt(jTFCodigo.getText());
            nombreAseg = jTFNombre.getText();
            estadoAseg = true;
            transDatosAseg = new AseguradoraVO(idEd, nombreAseg, idFuncAseg, estadoAseg);
            DBAseguradora.ingresarAseguradora(transDatosAseg);
            llamarDatosTabla();//llama el metodo para meter los datos en la tabla
                           
            return true;        
        }
        return false;
    }
    
    public boolean editarDatos(){// en este metodo se sacan los datos de la tabla y los ingresa 
                                 // en las casillas para permitir la modificacion de los mismos 
         boolean validar= validarDatos();
         if(validar!=false)
        {
            idEd = Integer.parseInt(jTFCodigo.getText());
            nombreAseg = jTFNombre.getText();
            transDatosAseg = new AseguradoraVO(idEd, nombreAseg, idFuncAseg, estadoAseg);
            DBAseguradora.modificaAseguradora(transDatosAseg);
            llamarDatosTabla();
            return true;        
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLCodigo = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jTFCodigo = new javax.swing.JTextField();
        jTFNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAseguradora = new javax.swing.JTable();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jBCerrar = new javax.swing.JButton();
        jLFunc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar Aseguradora");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Aseguradora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLCodigo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCodigo.setText("Código:");

        jLNombre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLNombre.setText("Nombre:");

        jTFCodigo.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLCodigo)
                        .addGap(18, 18, 18)
                        .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFNombre)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCodigo)
                    .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombre)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTAseguradora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTAseguradora);

        jBNuevo.setBackground(new java.awt.Color(255, 255, 255));
        jBNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        jBNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNuevoActionPerformed(evt);
            }
        });

        jBGuardar.setBackground(new java.awt.Color(255, 255, 255));
        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jBEditar.setBackground(new java.awt.Color(255, 255, 255));
        jBEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        jBEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarActionPerformed(evt);
            }
        });

        jBBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jBEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

        jBCerrar.setBackground(new java.awt.Color(255, 255, 255));
        jBCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        jBCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarActionPerformed(evt);
            }
        });

        jLFunc.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLFunc.setText("Funcionario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLFunc)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBNuevo)
                    .addComponent(jBGuardar)
                    .addComponent(jBEditar)
                    .addComponent(jBBuscar)
                    .addComponent(jBEliminar))
                .addGap(94, 94, 94)
                .addComponent(jLFunc))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoActionPerformed
         activar();//activa las cajas 
        try {//muestra quien esta generando cambios 
            IDActual();
        } catch (SQLException ex) {
            Logger.getLogger(Aseguradora.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTFNombre.requestFocus();
    }//GEN-LAST:event_jBNuevoActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
          if(validacionGuardar==false){
        //
       boolean validarGuardar = guardarDatos();
       if(validarGuardar!=false){
           bloquear();
           jTFNombre.setText(null);
           jBNuevo.setEnabled(true);
           try {
               IDActual();
           } catch (SQLException ex) {
               Logger.getLogger(Aseguradora.class.getName()).log(Level.SEVERE, null, ex);
           }
           JOptionPane.showMessageDialog(null, "Registro Guardado!");
            
       }
       }else{
        boolean validarGuardarEditar = editarDatos();  
        if(validarGuardarEditar!=false){
           bloquear();
           jTFNombre.setText(null);
           jBNuevo.setEnabled(true);
           try {
               IDActual();
           } catch (SQLException ex) {
               Logger.getLogger(Aseguradora.class.getName()).log(Level.SEVERE, null, ex);
           }
           JOptionPane.showMessageDialog(null, "Registro Actualizado!");
            
       }
       }
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        dispose();//cierra la ventana 
    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
        validacionGuardar=true;
        fila=jTAseguradora.getSelectedRow();//toma los datos de la tabla los llama a las cajas, activa las cajas y el boton de guardar se activa
        if(fila!=-1){                       
            jTFCodigo.setText(jTAseguradora.getValueAt(fila, 0).toString());
            jTFNombre.setText(jTAseguradora.getValueAt(fila, 1).toString());
            activar();
        }else{//validacion de no seleccion 
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea editar", "Error", 0);
            
        }
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        int idBuscar = Integer.parseInt(JOptionPane.showInputDialog("Digite Código de la Aseguradora:"));//pide el codigo de la aseguradora
        
        String nombreEdit="";
        try {
       //buscar el dato en la aseguradora
            nombreEdit = DBAseguradora.buscarAseguradora(idBuscar);
            } catch (SQLException ex) {
                       //este baja el nombre de la aseguradora EN LA BASE DE DATOS  
            Logger.getLogger(Aseguradora.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(nombreEdit==null)
        {
            JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);// validacion 
        }else{
        
        JOptionPane.showMessageDialog(null, "Aseguradora:\nCodigo: "+idBuscar+"\nNombre: "+nombreEdit);//muestra una pequeña ventana con la informacion
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
      
        fila=jTAseguradora.getSelectedRow();//fija la fila a la cual se le dio click
        if(fila!=-1){
            int id=Integer.parseInt(jTAseguradora.getValueAt(fila, 0).toString());
            boolean validarEliminar = DBAseguradora.modificarEstadoAseguradora(id);//modifica el estado poniendolo en 0
            if(validarEliminar!=false)
            {
                llamarDatosTabla();//se actualiza la tabla omitiendo los que tienen estado 0
                JOptionPane.showMessageDialog(null, "Registro Eliminado!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea eliminar", "Error", 0);
        }
    }//GEN-LAST:event_jBEliminarActionPerformed

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
//            java.util.logging.Logger.getLogger(Aseguradora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Aseguradora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Aseguradora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Aseguradora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Aseguradora().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JLabel jLCodigo;
    private javax.swing.JLabel jLFunc;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTAseguradora;
    private javax.swing.JTextField jTFCodigo;
    private javax.swing.JTextField jTFNombre;
    // End of variables declaration//GEN-END:variables
}
