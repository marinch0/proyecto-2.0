/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import controlador.AsistenteDAO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.AsistenteVO;


/**
 *
 * @author SFS
 */
public class Asistente extends javax.swing.JFrame {

    //declaración de variables   
    String idFunc,idAsis,nombreAsis,apellidoAsis,celularAsis,direccionAsis,correoAsis,rutaFotoAsis,nombreFunc;
    int fila;
    boolean estadoAsis;
    AsistenteVO transDatosAsis;
    AsistenteDAO DBAsistente;
    DefaultTableModel modelo;
    String datos[];
    ArrayList<String> listaAsis;
    boolean validacionGuardar=false;
    
    private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivo de Imagen", "jpg");
    
    //metodo constructor
    public Asistente(String idFunc,String nomFunc) {
        initComponents();
        
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        jTAsis.setBackground(Color.WHITE);
        
        DBAsistente = new AsistenteDAO();
        this.nombreFunc=nomFunc;
        this.idFunc=idFunc;
        jLFunc.setText("Funcionario: "+this.nombreFunc);
        bloquear();
        datos = new String[8];
        listaAsis = new ArrayList<>();
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
        jBFoto.setEnabled(false);
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
        jBFoto.setEnabled(true);
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);
        jBBuscar.setEnabled(false);
        
    }
    
     public void llamarDatosTabla(){
        
        modelo = new DefaultTableModel();// especifica las colmnas usadas y la informacion presente debajo de cada una 
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Celular");
        modelo.addColumn("Correo");
        modelo.addColumn("Dirección");
        this.jTAsis.setModel(modelo);
        this.jTAsis.getColumn(jTAsis.getColumnName(0)).setMaxWidth(90);
        this.jTAsis.getColumn(jTAsis.getColumnName(3)).setMaxWidth(90);
              
        
        listaAsis = DBAsistente.listaAsistente();//se sacan los datos de la conexion 
        
        for (int i = 0; i < listaAsis.size(); i=i+7) {
            
            datos[0]=listaAsis.get(i);//se rellenan las filas con los datos 
            datos[1]=listaAsis.get(i+1);
            datos[2]=listaAsis.get(i+2);
            datos[3]=listaAsis.get(i+3);
            datos[4]=listaAsis.get(i+4);
            datos[5]=listaAsis.get(i+5);
            modelo.addRow(datos);
            
        }
    }
     
     public ImageIcon subirFoto() {
         
        //crea un objeto filechooser
        JFileChooser dlg = new JFileChooser();
        //del objeto creado vamos a llamar el metodo setFileFilter
        dlg.setFileFilter(filter);
        //abrimos la ventana de dialogo para escoger imagenes
        int opcion = dlg.showOpenDialog(this);
        //si hacemos clic en boton abrir
        if (opcion == JFileChooser.APPROVE_OPTION) {
            
            //obtener el nombre del archivo que hemos seleccionado
            String fil = dlg.getSelectedFile().getPath();
            //obtener la direccion donde se guarda la imagen
            String file = dlg.getSelectedFile().toString();
            //modiicamos la imagen~~~~~~~~
            ImageIcon icon = new ImageIcon(fil);
            //extrae la imagen del icono
            Image img = icon.getImage();
            //cambiando el tamaño a la imagen
            Image newImg = img.getScaledInstance(120, 144,
                    java.awt.Image.SCALE_SMOOTH);
            //se genera un imagenIcon con la nueva imagen
            ImageIcon newIcon = new ImageIcon(newImg);
            rutaFotoAsis=file;
                      
            return newIcon;
        }
        return null;
    }
     
      public boolean guardarDatos(){
        
        boolean validar= validarDatos();
        
         if(validar!=false)
        {
            idAsis = jTFID.getText();
            nombreAsis = jTFNombre.getText();
            apellidoAsis = jTFApellido.getText();
            celularAsis = jTFCelular.getText();
            direccionAsis = jTFDireccion.getText();
            correoAsis = jTFCorreo.getText();
            estadoAsis = true;
            
            
            transDatosAsis = new AsistenteVO(idAsis, nombreAsis,apellidoAsis,celularAsis,correoAsis,direccionAsis, rutaFotoAsis,idFunc,estadoAsis);
            DBAsistente.ingresarAsistente(transDatosAsis);
            llamarDatosTabla();
            return true;        
        }
        return false;
     }
      
      public boolean editarDatos(){
         boolean validar= validarDatos();
        
        if(validar!=false)
        {
            idAsis = jTFID.getText();
            nombreAsis = jTFNombre.getText();
            apellidoAsis = jTFApellido.getText();
            celularAsis = jTFCelular.getText();
            direccionAsis = jTFDireccion.getText();
            correoAsis = jTFCorreo.getText();
            transDatosAsis = new AsistenteVO(idAsis, nombreAsis, apellidoAsis, celularAsis, correoAsis, direccionAsis, rutaFotoAsis, idFunc, estadoAsis);
            DBAsistente.modificarAsistente(transDatosAsis);
            llamarDatosTabla();
            return true;        
        }
        return false;
    }
      
      public void mostrarDatos(){//carga los datos a las cajas 
            jTFID.setText(jTAsis.getValueAt(fila, 0).toString());
            jTFNombre.setText(jTAsis.getValueAt(fila, 1).toString());
            jTFApellido.setText(jTAsis.getValueAt(fila, 2).toString());
            jTFCelular.setText(jTAsis.getValueAt(fila, 3).toString());
            jTFCorreo.setText(jTAsis.getValueAt(fila, 4).toString());
            jTFDireccion.setText(jTAsis.getValueAt(fila, 5).toString());
        try {
            jLFoto.setIcon(cargarFoto(DBAsistente.buscarRutaFoto(jTAsis.getValueAt(fila, 0).toString())));
        } catch (SQLException ex) {
            Logger.getLogger(Asistente.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
      }
      
      public ImageIcon cargarFoto(String ruta) {
        ImageIcon icon = new ImageIcon(ruta);
        //extrae la imagen del icono
        Image img = icon.getImage();
        //cambiando el tamaño a la imagen
        Image newImg = img.getScaledInstance(120, 144, java.awt.Image.SCALE_SMOOTH);
        //se genera un imagenIcon con la nueva imagen
        ImageIcon newIcon = new ImageIcon(newImg);
        rutaFotoAsis=ruta;
        return newIcon;
    }
      
       public boolean validarDatos(){
         
         if(jTFID.getText().isEmpty()) //valida que ninguna caja este vacia 
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
         }else{
             return true;
         }
         
     }
       
       public void limpiarCajas(){//limpia las cajas de datos 
         jTFID.setText(null);
         jTFNombre.setText(null);
         jTFApellido.setText(null);
         jTFCelular.setText(null);
         jTFDireccion.setText(null);
         jTFCorreo.setText(null);
         jLFoto.setIcon(null);
                  
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPAsistente = new javax.swing.JPanel();
        jLID = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLApellido = new javax.swing.JLabel();
        jLCelular = new javax.swing.JLabel();
        jLCorreo = new javax.swing.JLabel();
        jLDireccion = new javax.swing.JLabel();
        jTFID = new javax.swing.JTextField();
        jTFNombre = new javax.swing.JTextField();
        jTFApellido = new javax.swing.JTextField();
        jTFCelular = new javax.swing.JTextField();
        jTFCorreo = new javax.swing.JTextField();
        jTFDireccion = new javax.swing.JTextField();
        jLFoto = new javax.swing.JLabel();
        jBFoto = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAsis = new javax.swing.JTable();
        jBCerrar = new javax.swing.JButton();
        jLFunc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar Aseguradora");
        setBackground(new java.awt.Color(255, 255, 255));

        jPAsistente.setBackground(new java.awt.Color(255, 255, 255));
        jPAsistente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Asistente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLID.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLID.setText("Identificación:");

        jLNombre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLNombre.setText("Nombre:");

        jLApellido.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLApellido.setText("Apellido:");

        jLCelular.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCelular.setText("Celular:");

        jLCorreo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCorreo.setText("E-Mail:");

        jLDireccion.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLDireccion.setText("Dirección:");

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

        jLFoto.setBackground(new java.awt.Color(255, 255, 255));
        jLFoto.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jBFoto.setBackground(new java.awt.Color(0, 153, 0));
        jBFoto.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jBFoto.setForeground(new java.awt.Color(255, 255, 255));
        jBFoto.setText("Subir Foto");
        jBFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPAsistenteLayout = new javax.swing.GroupLayout(jPAsistente);
        jPAsistente.setLayout(jPAsistenteLayout);
        jPAsistenteLayout.setHorizontalGroup(
            jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAsistenteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLID)
                    .addComponent(jLNombre)
                    .addComponent(jLApellido)
                    .addComponent(jLCelular)
                    .addComponent(jLCorreo)
                    .addComponent(jLDireccion))
                .addGap(18, 18, 18)
                .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFID, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPAsistenteLayout.setVerticalGroup(
            jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAsistenteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPAsistenteLayout.createSequentialGroup()
                        .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLID)
                            .addComponent(jTFID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLNombre)
                            .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLApellido)
                            .addComponent(jTFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCelular)
                            .addComponent(jTFCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCorreo)
                            .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPAsistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLDireccion)
                            .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPAsistenteLayout.createSequentialGroup()
                        .addComponent(jLFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBFoto)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jBEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

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

        jTAsis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTAsis);

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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
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
                        .addComponent(jPAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLFunc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBNuevo)
                            .addComponent(jBGuardar)
                            .addComponent(jBEditar)
                            .addComponent(jBBuscar)
                            .addComponent(jBEliminar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLFunc))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        fila=jTAsis.getSelectedRow();
        if(fila!=-1){
            int id=Integer.parseInt(jTAsis.getValueAt(fila, 0).toString());
            boolean validarEliminar = DBAsistente.modificarEstadoAsistente(String.valueOf(id));
            if(validarEliminar!=false)
            {
                llamarDatosTabla();
                JOptionPane.showMessageDialog(null, "Registro Eliminado!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea eliminar", "Error", 0);
        }
    }//GEN-LAST:event_jBEliminarActionPerformed

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
        fila=jTAsis.getSelectedRow();
        if(fila!=-1){
                mostrarDatos();
                activar();
                jTFID.setEnabled(false);
            
        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea editar", "Error", 0);
        }
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        String idBuscar = JOptionPane.showInputDialog("Digite Codigo del Asistente:");
        ArrayList<String> datosBusqueda = new ArrayList<>();

        if(idBuscar!=null)
        {
            datosBusqueda = DBAsistente.buscarAsistente(idBuscar);

            if(datosBusqueda==null || datosBusqueda.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);
            }else{
                String est=datosBusqueda.get(7).equals("1")?"Activo":"Inactivo";
                JOptionPane.showMessageDialog(null, "Asistente:\nCodigo: "+idBuscar+"\nNombre: "+datosBusqueda.get(1)+"\nApellido: "+datosBusqueda.get(2)
                    +"\nCelular: "+datosBusqueda.get(3)+"\nCorreo: "+datosBusqueda.get(4)+"\nDirección: "+datosBusqueda.get(5)+"\nRuta Foto: "+datosBusqueda.get(6)+"\nEstado: "+est);
            }
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jBFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFotoActionPerformed
        jLFoto.setIcon(subirFoto());
    }//GEN-LAST:event_jBFotoActionPerformed

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
//            java.util.logging.Logger.getLogger(Asistente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Asistente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Asistente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Asistente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Asistente().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBFoto;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLCelular;
    private javax.swing.JLabel jLCorreo;
    private javax.swing.JLabel jLDireccion;
    private javax.swing.JLabel jLFoto;
    private javax.swing.JLabel jLFunc;
    private javax.swing.JLabel jLID;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JPanel jPAsistente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTAsis;
    private javax.swing.JTextField jTFApellido;
    private javax.swing.JTextField jTFCelular;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFID;
    private javax.swing.JTextField jTFNombre;
    // End of variables declaration//GEN-END:variables
}
