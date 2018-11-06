/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import controlador.FuncionarioDAO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.FuncionarioVO;

/**
 *
 * @author SFS
 */
public class Funcionario extends javax.swing.JFrame {

    //declaración de variables   
    String idFunc,nombreFunc,apellidoFunc,celularFunc,direccionFunc,correoFunc,usuarioFunc,ClaveFunc;
    int fila,eleccion;
    boolean estadoFunc;
    FuncionarioVO transDatosFunc;
    FuncionarioDAO DBFuncionario;
    DefaultTableModel modelo;
    String datos[];
    ArrayList<String> listaFunc;
    boolean validacionGuardar=false;
    
    
    
    //metodo constructor
    public Funcionario(String idFunc,String nomFunc) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        jTFuncionario.setBackground(Color.WHITE);
        
        DBFuncionario = new FuncionarioDAO();
        this.nombreFunc=nomFunc;
        jLFunc.setText("Funcionario: "+this.nombreFunc);
        bloquear();
        datos = new String[8];
        listaFunc = new ArrayList<>();
        modelo = new DefaultTableModel();
        llamarDatosTabla();
         try{
             Image img=ImageIO.read(new File("icoescu.png"));
             this.setIconImage(img);
         } catch(Exception e){
             System.err.println(e);
         }
    }

     public void bloquear(){
        jTFID.setEnabled(false);
        jTFNombre.setEnabled(false);
        jTFApellido.setEnabled(false);
        jTFCelular.setEnabled(false);
        jTFDireccion.setEnabled(false);
        jTFCorreo.setEnabled(false);
        jTFUsuario.setEnabled(false);
        jPFClave.setEnabled(false);
        jPFReClave.setEnabled(false);
        jBGuardar.setEnabled(false);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);
        jBBuscar.setEnabled(true);
    }
    
    public void activar(){
        jTFID.setEnabled(true);
        jTFNombre.setEnabled(true);
        jTFApellido.setEnabled(true);
        jTFCelular.setEnabled(true);
        jTFDireccion.setEnabled(true);
        jTFCorreo.setEnabled(true);
        jTFUsuario.setEnabled(true);
        jBGuardar.setEnabled(true);
        jPFClave.setEnabled(true);
        jPFReClave.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);
        jBBuscar.setEnabled(false);
        
    }
    
     public void llamarDatosTabla(){
        
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Celular");
        modelo.addColumn("Correo");
        modelo.addColumn("Dirección");
        modelo.addColumn("Usuario");
        modelo.addColumn("Clave");
        this.jTFuncionario.setModel(modelo);
        this.jTFuncionario.getColumn(jTFuncionario.getColumnName(0)).setMaxWidth(90);
        this.jTFuncionario.getColumn(jTFuncionario.getColumnName(1)).setMaxWidth(200);
        this.jTFuncionario.getColumn(jTFuncionario.getColumnName(2)).setMaxWidth(200);
        this.jTFuncionario.getColumn(jTFuncionario.getColumnName(3)).setMaxWidth(90);
        this.jTFuncionario.getColumn(jTFuncionario.getColumnName(6)).setMaxWidth(80);
        this.jTFuncionario.getColumn(jTFuncionario.getColumnName(7)).setWidth(0);
        this.jTFuncionario.getColumn(jTFuncionario.getColumnName(7)).setMinWidth(0);
        this.jTFuncionario.getColumn(jTFuncionario.getColumnName(7)).setMaxWidth(0);
        
        listaFunc = DBFuncionario.listaFuncionario();
        
        for (int i = 0; i < listaFunc.size(); i=i+8) {
            
            datos[0]=listaFunc.get(i);
            datos[1]=listaFunc.get(i+1);
            datos[2]=listaFunc.get(i+2);
            datos[3]=listaFunc.get(i+3);
            datos[4]=listaFunc.get(i+4);
            datos[5]=listaFunc.get(i+5);
            datos[6]=listaFunc.get(i+6);
            datos[7]=listaFunc.get(i+7);
            modelo.addRow(datos);
            
        }
    }
     
      public boolean guardarDatos(){
        
        boolean validar= validarDatos();
        
         if(validar!=false)
        {
            idFunc = jTFID.getText();
            nombreFunc = jTFNombre.getText();
            apellidoFunc = jTFApellido.getText();
            celularFunc = jTFCelular.getText();
            direccionFunc = jTFDireccion.getText();
            correoFunc = jTFCorreo.getText();
            usuarioFunc = jTFUsuario.getText();
            estadoFunc = true;
            ClaveFunc = jPFClave.getText();
            
            transDatosFunc = new FuncionarioVO(idFunc, nombreFunc,apellidoFunc,celularFunc,correoFunc,direccionFunc, usuarioFunc,ClaveFunc,estadoFunc);
            DBFuncionario.ingresarFuncionario(transDatosFunc);
            llamarDatosTabla();
            return true;        
        }
        return false;
     }
      
      public boolean editarDatos(){
         boolean validar= validarDatos();
        
        if(validar!=false)
        {
            idFunc = jTFID.getText();
            nombreFunc = jTFNombre.getText();
            apellidoFunc = jTFApellido.getText();
            celularFunc = jTFCelular.getText();
            direccionFunc = jTFDireccion.getText();
            correoFunc = jTFCorreo.getText();
            usuarioFunc = jTFUsuario.getText();
            ClaveFunc = jPFClave.getText();
            transDatosFunc = new FuncionarioVO(idFunc, nombreFunc, apellidoFunc, celularFunc, correoFunc, direccionFunc, usuarioFunc, ClaveFunc, estadoFunc);
            DBFuncionario.modificarFuncionario(transDatosFunc);
            llamarDatosTabla();
            return true;        
        }
        return false;
    }
      
      public void mostrarDatos(){
            jTFID.setText(jTFuncionario.getValueAt(fila, 0).toString());
            jTFNombre.setText(jTFuncionario.getValueAt(fila, 1).toString());
            jTFApellido.setText(jTFuncionario.getValueAt(fila, 2).toString());
            jTFCelular.setText(jTFuncionario.getValueAt(fila, 3).toString());
            jTFDireccion.setText(jTFuncionario.getValueAt(fila, 4).toString());
            jTFCorreo.setText(jTFuncionario.getValueAt(fila, 5).toString());
            jTFUsuario.setText(jTFuncionario.getValueAt(fila, 6).toString());
      }
      
       public boolean validarDatos(){
         
         if(jTFID.getText().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Ingresar Identificación", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFID.requestFocus();
             return false;
         } else if (jTFNombre.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Nombre", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFNombre.requestFocus();
             return false;
         }else if(jTFApellido.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Apellido", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFApellido.requestFocus();
             return false;
         }else if(jTFCelular.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Celular", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFCelular.requestFocus();
             return false;
         }else if(jTFDireccion.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Dirección", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFDireccion.requestFocus();
             return false;
         }else if(jTFCorreo.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Correo", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFCorreo.requestFocus();
             return false;
         }else if(jTFUsuario.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Usuario", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFUsuario.requestFocus();
             return false;
         }else if(jPFClave.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Clave", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jPFClave.requestFocus();
             return false;
         }else if(jPFReClave.getText().equals(jPFClave.getText())){
             return true;
         }else{
             JOptionPane.showMessageDialog(null, "Las Claves son diferentes", "Datos diferentes",JOptionPane.ERROR_MESSAGE);
             jPFClave.setText(null);
             jPFReClave.setText(null);
             jPFClave.requestFocus();
             return false;
         }
         
     }
       
       public void limpiarCajas(){
         jTFID.setText(null);
         jTFNombre.setText(null);
         jTFApellido.setText(null);
         jTFCelular.setText(null);
         jTFDireccion.setText(null);
         jTFCorreo.setText(null);
         jTFUsuario.setText(null);
         jPFClave.setText(null);
         jPFReClave.setText(null);
         
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPFunc = new javax.swing.JPanel();
        jLID = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLApellido = new javax.swing.JLabel();
        jLCelular = new javax.swing.JLabel();
        jLCorreo = new javax.swing.JLabel();
        jLdireccion = new javax.swing.JLabel();
        jLUsuario = new javax.swing.JLabel();
        jLClave = new javax.swing.JLabel();
        jLReClave = new javax.swing.JLabel();
        jTFID = new javax.swing.JTextField();
        jTFNombre = new javax.swing.JTextField();
        jTFApellido = new javax.swing.JTextField();
        jTFCelular = new javax.swing.JTextField();
        jTFCorreo = new javax.swing.JTextField();
        jTFDireccion = new javax.swing.JTextField();
        jTFUsuario = new javax.swing.JTextField();
        jPFClave = new javax.swing.JPasswordField();
        jPFReClave = new javax.swing.JPasswordField();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTFuncionario = new javax.swing.JTable();
        jBEliminar = new javax.swing.JButton();
        jLFunc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar Funcionario");

        jPFunc.setBackground(new java.awt.Color(255, 255, 255));
        jPFunc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Funcionario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLID.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLID.setText("Identificación:");

        jLNombre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLNombre.setText("Nombre:");

        jLApellido.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLApellido.setText("Apellido:");

        jLCelular.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCelular.setText("Celular:");

        jLCorreo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCorreo.setText("E-mail:");

        jLdireccion.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLdireccion.setText("Dirección:");

        jLUsuario.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLUsuario.setText("Usuario:");

        jLClave.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLClave.setText("Contraseña:");

        jLReClave.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLReClave.setText("Re-Contraseña:");

        jTFID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFIDKeyTyped(evt);
            }
        });

        jTFCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCelularKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPFuncLayout = new javax.swing.GroupLayout(jPFunc);
        jPFunc.setLayout(jPFuncLayout);
        jPFuncLayout.setHorizontalGroup(
            jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFuncLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLID)
                    .addComponent(jLNombre)
                    .addComponent(jLApellido)
                    .addComponent(jLCelular)
                    .addComponent(jLCorreo)
                    .addComponent(jLdireccion)
                    .addComponent(jLUsuario)
                    .addComponent(jLClave)
                    .addComponent(jLReClave))
                .addGap(27, 27, 27)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFUsuario)
                    .addComponent(jTFDireccion)
                    .addComponent(jTFCorreo)
                    .addComponent(jTFCelular)
                    .addComponent(jTFApellido)
                    .addComponent(jTFNombre)
                    .addComponent(jTFID)
                    .addComponent(jPFClave)
                    .addComponent(jPFReClave, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPFuncLayout.setVerticalGroup(
            jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLID)
                    .addComponent(jTFID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombre)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLApellido)
                    .addComponent(jTFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCelular)
                    .addComponent(jTFCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCorreo)
                    .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLdireccion)
                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLUsuario)
                    .addComponent(jTFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLClave)
                    .addComponent(jPFClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLReClave)
                    .addComponent(jPFReClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jBCerrar.setBackground(new java.awt.Color(255, 255, 255));
        jBCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        jBCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarActionPerformed(evt);
            }
        });

        jTFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTFuncionario);

        jBEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLFunc)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBNuevo)
                            .addComponent(jBGuardar)
                            .addComponent(jBEditar)
                            .addComponent(jBBuscar)
                            .addComponent(jBEliminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLFunc))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoActionPerformed
        activar();
        jTFID.requestFocus();
    }//GEN-LAST:event_jBNuevoActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
          if(validacionGuardar==false){
        
       boolean validarGuardar = guardarDatos();
       if(validarGuardar!=false){
           bloquear();
           limpiarCajas();
           jBNuevo.setEnabled(true);
           JOptionPane.showMessageDialog(null, "Registro Guardado!");
       }
       }else{
        boolean validarGuardarEditar = editarDatos();  
        if(validarGuardarEditar!=false){
           bloquear();
           limpiarCajas();
           jBNuevo.setEnabled(true);
           JOptionPane.showMessageDialog(null, "Registro Actualizado!");
           validacionGuardar=false;
       }
         }
    
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
       validacionGuardar=true;
        fila=jTFuncionario.getSelectedRow();
        if(fila!=-1){
            eleccion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opción para editar:\n1. Editar datos\n2. Editar Clave"));
            switch (eleccion) {
                case 1:
            mostrarDatos();
            jTFID.setEnabled(false);
            jPFClave.setText(jTFuncionario.getValueAt(fila, 7).toString());
            jPFReClave.setText(jTFuncionario.getValueAt(fila, 7).toString());
            activar();
            jPFClave.setEnabled(false);
            jPFReClave.setEnabled(false);
            break;        
           
                case 2:
           mostrarDatos();
           jPFClave.setEnabled(false);
           jPFReClave.setEnabled(false);
           ClaveFunc = jTFuncionario.getValueAt(fila, 7).toString();
           String nuevaClave = JOptionPane.showInputDialog("Su Clave es:\n" + ClaveFunc + "\nDigite Nueva Clave:");
           modelo.setValueAt(nuevaClave, fila, 7);
           jPFClave.setText(jTFuncionario.getValueAt(fila, 7).toString());
           jPFReClave.setText(jTFuncionario.getValueAt(fila, 7).toString());
           editarDatos();
           limpiarCajas();
           break;

                default:
                    JOptionPane.showMessageDialog(null, "Número no valido");
                    break;         
            }         
        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\\nSeleccione en la tabla la fila que desea editar", "Error", 0);
        }
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        String idBuscar = JOptionPane.showInputDialog("Digite Codigo del Funcionario:");
        ArrayList<String> datosBusqueda = new ArrayList<>();
        
        if(idBuscar!=null)
        {
        datosBusqueda = DBFuncionario.buscarFuncionario(idBuscar);
        
        
        if(datosBusqueda==null || datosBusqueda.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);
        }else{
        String est=datosBusqueda.get(7).equals("1")?"Activo":"Inactivo";
        JOptionPane.showMessageDialog(null, "Funcionario:\nCodigo: "+idBuscar+"\nNombre: "+datosBusqueda.get(1)+"\nApellido: "+datosBusqueda.get(2)
        +"\nCelular: "+datosBusqueda.get(3)+"\nCorreo: "+datosBusqueda.get(4)+"\nDirección: "+datosBusqueda.get(5)+"\nUsuario: "+datosBusqueda.get(6)+"\nEstado: "+est);
        }
        }       
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
       dispose();
    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        fila=jTFuncionario.getSelectedRow();
        if(fila!=-1){
            int id=Integer.parseInt(jTFuncionario.getValueAt(fila, 0).toString());
            boolean validarEliminar = DBFuncionario.modificarEstadoFuncionario(String.valueOf(id));
            if(validarEliminar!=false)
            {
                llamarDatosTabla();
                JOptionPane.showMessageDialog(null, "Registro Eliminado!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea eliminar", "Error", 0);
        }
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jTFIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFIDKeyTyped
        char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }
    }//GEN-LAST:event_jTFIDKeyTyped

    private void jTFCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCelularKeyTyped
        char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }
    }//GEN-LAST:event_jTFCelularKeyTyped

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
//            java.util.logging.Logger.getLogger(Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Funcionario().setVisible(true);
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
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLCelular;
    private javax.swing.JLabel jLClave;
    private javax.swing.JLabel jLCorreo;
    private javax.swing.JLabel jLFunc;
    private javax.swing.JLabel jLID;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLReClave;
    private javax.swing.JLabel jLUsuario;
    private javax.swing.JLabel jLdireccion;
    private javax.swing.JPasswordField jPFClave;
    private javax.swing.JPasswordField jPFReClave;
    private javax.swing.JPanel jPFunc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFApellido;
    private javax.swing.JTextField jTFCelular;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFID;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFUsuario;
    private javax.swing.JTable jTFuncionario;
    // End of variables declaration//GEN-END:variables
}
