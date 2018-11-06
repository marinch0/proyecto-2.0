/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import controlador.TecnomecanicaDAO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.TecnomecanicaVO;
import modelo.VehiculoVO;

/**
 *
 * @author SFS
 */
public class Tecnomecanica extends javax.swing.JFrame {

    String codigoTecno,cenDiag,idFuncTecno,nombFunc,rutaFotoTecno;
    int fila,filaP;
    int eleccionFoto=1;//1 rutaFotoCond 2 rutaFotoLic
    boolean estadoAseg,datoVen=false;;
    Date fechaExp,fechaVig;
    TecnomecanicaVO transDatosTecno;
    VehiculoVO transDatosVeh;
    TecnomecanicaDAO DBTecno;
    DefaultTableModel modelo;
    String datos[];
    ArrayList<String> listaTecno;
    ArrayList<String> listaAseg;
    boolean validacionGuardar=false;
    
    private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivo de Imagen", "jpg");
    
    
    public Tecnomecanica(String idFunc,String nomFunc,VehiculoVO datosVeh,boolean datoVentana) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        jTTecno.setBackground(Color.WHITE);
                
        DBTecno = new TecnomecanicaDAO();
        idFuncTecno = idFunc;
        nombFunc=nomFunc;
        jLFunc.setText("Funcionario: "+nombFunc);
        bloquear();
        datos = new String[4];
        listaTecno = new ArrayList<>();
        listaAseg = new ArrayList<>();
        modelo = new DefaultTableModel();
        llamarDatosTabla();
        
        datoVen=datoVentana;
        
        if(datoVentana==true){
        transDatosVeh=datosVeh;
        }
        
        //permite colocar el icono 
        try{
             Image img=ImageIO.read(new File("icoescu.png"));
             this.setIconImage(img);
         } catch(Exception e){
             System.err.println(e);
         }
    }

    public void bloquear(){
        jTFCodigo.setEnabled(false);
        jTFCenDiag.setEnabled(false);
        jDCExp.setEnabled(false);
        jDCVig.setEnabled(false);
        jBFotoLic.setEnabled(false);
        jBVer.setEnabled(false);
        jBGuardar.setEnabled(false);
        jBEditar.setEnabled(true);
        jBBuscar.setEnabled(true);
        jBEliminar.setEnabled(true);
               
    }
     
     public void activar(){
        jTFCodigo.setEnabled(true);
        jTFCenDiag.setEnabled(true);
        jDCExp.setEnabled(true);
        jDCVig.setEnabled(true);
        jBFotoLic.setEnabled(true);
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBBuscar.setEnabled(false);
        jBEliminar.setEnabled(false);
        
        
    }
     
    public void llamarDatosTabla(){
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Centro Diag");
        modelo.addColumn("Fecha Exp");
        modelo.addColumn("Fecha Vig");
        this.jTTecno.setModel(modelo);
        this.jTTecno.getColumn(jTTecno.getColumnName(0)).setMaxWidth(100);
        
        listaTecno=DBTecno.listaTecno();
        for (int i = 0; i < listaTecno.size(); i=i+4) {
            
            datos[0]=listaTecno.get(i);
            datos[1]=listaTecno.get(i+1);
            datos[2]=listaTecno.get(i+2);
            datos[3]=listaTecno.get(i+3);
            
            modelo.addRow(datos);
            
        }
    }
     
      public boolean validarDatos(){
        
        if(jTFCodigo.getText().equals("")||jTFCodigo.getText().equals(" ")||jTFCodigo.getText().equals(null)){
            JOptionPane.showMessageDialog(null, "No Ingreso Código", "Error de selección",JOptionPane.ERROR_MESSAGE);
            jTFCodigo.requestFocus();
            return false;
        }else if(jTFCenDiag.getText().equals("")||jTFCenDiag.getText().equals(" ")||jTFCenDiag.getText().equals(null)){
            JOptionPane.showMessageDialog(null, "No ingreso centro de diagnostico", "Error de selección",JOptionPane.ERROR_MESSAGE);
            jTFCenDiag.requestFocus();
            return false;
        }else{
            return true;
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
            
            Image newImg = img.getScaledInstance(227, 109,
                    java.awt.Image.SCALE_SMOOTH);
            //se genera un imagenIcon con la nueva imagen
            ImageIcon newIcon = new ImageIcon(newImg);
            
            rutaFotoTecno=file;
            jBVer.setEnabled(true);
            
            return newIcon;
        }
        return null;
    }  
      
     public void MostrarLicencia(){
        VerLicencia linc=new VerLicencia();
        linc.cargarImg(cargarFoto(rutaFotoTecno, 2));
        linc.setVisible(true);  
              
    }   
    
    public ImageIcon cargarFoto(String ruta, int eleccion) {
        ImageIcon icon = new ImageIcon(ruta);
        //extrae la imagen del icono
        Image img = icon.getImage();
        //cambiando el tamaño a la imagen
        int ancho=227,alto=109;
        
        if(eleccion==1){
            ancho=227;
            alto=109;
        }else if(eleccion==2){
            ancho=666;
            alto=266;
        }
        
        Image newImg = img.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
        //se genera un imagenIcon con la nueva imagen
        ImageIcon newIcon = new ImageIcon(newImg);
        
        rutaFotoTecno=ruta;  
        jBVer.setEnabled(true);
        
        return newIcon;
    }
    public void fechaExp(){
            String formato = jDCExp.getDateFormatString();
            java.util.Date date = jDCExp.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            String fecha1 = String.valueOf(sdf.format(date));
            String fechaMo[] = fecha1.split("/");
            fecha1 = fechaMo[2]+"-"+fechaMo[1]+"-"+fechaMo[0];
            fechaExp=java.sql.Date.valueOf(fecha1);
    }
    
    public boolean fechaVig(){
            String formato = jDCVig.getDateFormatString();
            java.util.Date date = jDCVig.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            String fecha = String.valueOf(sdf.format(date));
            String fechaMo[] = fecha.split("/");
            fecha = fechaMo[2]+"-"+fechaMo[1]+"-"+fechaMo[0];
            
            //fecha actual para comparar vigencia
            //Creamos un objeto de la clase Calendar.
            Calendar fechaActual = new GregorianCalendar();
            //Obtenemos el valor del año, mes, día, hora, minuto y segundo del sistema.
            //Usando el método get y el parámetro correspondiente.
            int anio = fechaActual.get(Calendar.YEAR);
            int mes = fechaActual.get(Calendar.MONTH)+1;
            int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
            
            if(Integer.parseInt(fechaMo[2])<anio){
                   JOptionPane.showMessageDialog(null, "Vigencia de Licencia no valida", "Error", 0);
                   return false;
            }else if(Integer.parseInt(fechaMo[2])==anio && Integer.parseInt(fechaMo[1])<mes){
                   JOptionPane.showMessageDialog(null, "Vigencia de Licencia no valida", "Error", 0);
                   return false;
            }else if(Integer.parseInt(fechaMo[2])==anio && Integer.parseInt(fechaMo[1])==mes && Integer.parseInt(fechaMo[0])<=dia){
                    JOptionPane.showMessageDialog(null, "Vigencia de Licencia no valida", "Error", 0);
                   return false;
            }else{
                    fechaVig=java.sql.Date.valueOf(fecha);       
                    return true;
            }
                                 
        }          
      public boolean guardarDatos(){
        
        boolean validar= validarDatos();
        
        if(validar!=false)
        {
            codigoTecno = jTFCodigo.getText();
            cenDiag=jTFCenDiag.getText();
            fechaExp();
            boolean validarFecha=true;
            validarFecha=fechaVig();
            estadoAseg = true;
            if(validarFecha==true){
            transDatosTecno = new TecnomecanicaVO(codigoTecno, cenDiag, fechaExp,fechaVig, rutaFotoTecno,idFuncTecno, estadoAseg);
            DBTecno.ingresarTecno(transDatosTecno);
            llamarDatosTabla();
            return true;        
            }
        }
        return false;
    }
    
     public void limpiarCajas(){
         jTFCodigo.setText(null);
         jTFCenDiag.setText(null);
         jLFotoVig.setIcon(null);
         jDCExp.setDate(null);
         jDCVig.setDate(null);
                  
     }  
      
    public boolean editarDatos(){
         boolean validar= validarDatos();
        
        if(validar!=false)
        {
            codigoTecno = jTFCodigo.getText();
            cenDiag = jTFCenDiag.getText();
                 
            fechaExp();
            fechaVig();
            estadoAseg = true;
            transDatosTecno = new TecnomecanicaVO(codigoTecno, cenDiag, fechaExp,fechaVig, rutaFotoTecno,idFuncTecno, estadoAseg);
            DBTecno.modificaTecno(transDatosTecno);
            llamarDatosTabla();
            return true;        
        }
        return false;
    }
    
    public void mostrarDatos(){
            jTFCodigo.setText(jTTecno.getValueAt(fila, 0).toString());
            jTFCenDiag.setText(jTTecno.getValueAt(fila, 1).toString());
            fechaExp=java.sql.Date.valueOf(jTTecno.getValueAt(fila, 2).toString());
            fechaVig=java.sql.Date.valueOf(jTTecno.getValueAt(fila, 3).toString());
            jDCExp.setDate(fechaExp);
            jDCVig.setDate(fechaVig);                  
            
            
        try {
            jLFotoVig.setIcon(cargarFoto(DBTecno.buscarRutaFoto(jTTecno.getValueAt(fila, 0).toString()),1));
        } catch (SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
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
        jLCodigo = new javax.swing.JLabel();
        jTFCodigo = new javax.swing.JTextField();
        jLCenDiag = new javax.swing.JLabel();
        jTFCenDiag = new javax.swing.JTextField();
        jLFeExp = new javax.swing.JLabel();
        jLFeVig = new javax.swing.JLabel();
        jDCVig = new com.toedter.calendar.JDateChooser();
        jDCExp = new com.toedter.calendar.JDateChooser();
        jLFotoVig = new javax.swing.JLabel();
        jBFotoLic = new javax.swing.JButton();
        jBVer = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTTecno = new javax.swing.JTable();
        jBCerrar = new javax.swing.JButton();
        jLFunc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ingresar Tecnomecanica");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resgistro Tecnomecanica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLCodigo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCodigo.setText("Código:");

        jTFCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCodigoKeyTyped(evt);
            }
        });

        jLCenDiag.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCenDiag.setText("Centro Diagnostico:");

        jTFCenDiag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCenDiagKeyTyped(evt);
            }
        });

        jLFeExp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLFeExp.setText("Fecha Expedición:");

        jLFeVig.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLFeVig.setText("Fecha Vigencia:");

        jLFotoVig.setBackground(new java.awt.Color(255, 255, 255));
        jLFotoVig.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jBFotoLic.setBackground(new java.awt.Color(255, 0, 0));
        jBFotoLic.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jBFotoLic.setForeground(new java.awt.Color(255, 255, 255));
        jBFotoLic.setText("Subir Tecno");
        jBFotoLic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFotoLicActionPerformed(evt);
            }
        });

        jBVer.setBackground(new java.awt.Color(255, 255, 255));
        jBVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscardet.png"))); // NOI18N
        jBVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLFeExp)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDCVig, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(jDCExp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCenDiag)
                            .addComponent(jLCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFCenDiag, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(jTFCodigo)))
                    .addComponent(jLFeVig))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jBFotoLic, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBVer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLFotoVig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCodigo)
                    .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCenDiag)
                    .addComponent(jTFCenDiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLFeExp)
                    .addComponent(jDCExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLFeVig)
                    .addComponent(jDCVig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLFotoVig, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBVer)
                    .addComponent(jBFotoLic))
                .addContainerGap())
        );

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

        jTTecno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTTecno);

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLFunc)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(193, 193, 193)
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBNuevo)
                            .addComponent(jBGuardar)
                            .addComponent(jBEditar)
                            .addComponent(jBBuscar)
                            .addComponent(jBEliminar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLFunc))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCodigoKeyTyped
        char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
            && (caracter != '\b'/*corresponde a Back_space*/)
            && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }
    }//GEN-LAST:event_jTFCodigoKeyTyped

    private void jTFCenDiagKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCenDiagKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCenDiagKeyTyped

    private void jBFotoLicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFotoLicActionPerformed
        eleccionFoto=1;
        jLFotoVig.setIcon(subirFoto());
    }//GEN-LAST:event_jBFotoLicActionPerformed

    private void jBVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerActionPerformed
        MostrarLicencia();
    }//GEN-LAST:event_jBVerActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        String idBuscar = JOptionPane.showInputDialog("Digite Código del Tecnomecanica:");
        ArrayList<String> datosBusqueda = new ArrayList<>();

        if(idBuscar!=null)
        {
            datosBusqueda = DBTecno.buscarTecno(idBuscar);

            if(datosBusqueda==null || datosBusqueda.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);
            }else{
                String aseguradora;
                
                    String est=datosBusqueda.get(5).equals("1")?"Activo":"Inactivo";
                    JOptionPane.showMessageDialog(null, "Tecno:\nCodigo: "+idBuscar+"\nCentro Diagnostico: "+datosBusqueda.get(1)+"\nFecha Expedición: "+datosBusqueda.get(2)
                        +"\nFecha Vigencia: "+datosBusqueda.get(3)+"\nRuta Foto: "+datosBusqueda.get(4)+"\nEstado: "+est);
                
            }
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        fila=jTTecno.getSelectedRow();
        if(fila!=-1){
            int id=Integer.parseInt(jTTecno.getValueAt(fila, 0).toString());
            boolean validarEliminar = DBTecno.modificarEstadoTecno(id);
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
        jTFCodigo.requestFocus();
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
        fila=jTTecno.getSelectedRow();
        if(fila!=-1){
            activar();
            mostrarDatos();
            jTFCodigo.setEnabled(false);

        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea editar", "Error", 0);
        }
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        if(datoVen==true){
                 int opcion=Integer.parseInt(JOptionPane.showInputDialog("Seleccionar Código Tecnomecanica:\n1. Ultimo registro\n2. Seleccionar un Registro de la tabla"));
                 switch (opcion) {
                   case 1:
                       transDatosVeh.setCod_tecno(codigoTecno);
                       Vehiculo miVeh = new Vehiculo(idFuncTecno, nombFunc,transDatosVeh);
                       miVeh.setVisible(true);
                       dispose();
                       break;
                   case 2:
                       fila=jTTecno.getSelectedRow();
                       if(fila!=-1){
                       transDatosVeh.setCod_tecno(jTTecno.getValueAt(fila, 0).toString());
                       Vehiculo miVeh1 = new Vehiculo(idFuncTecno, nombFunc,transDatosVeh);
                       miVeh1.setVisible(true);
                       dispose();
                       }else{
                           JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea agregar", "Error", 0);
                       }
                       break;
                   default:
                       JOptionPane.showMessageDialog(null, "Selecciona una de las opciones");
               }
                 
           }else{
            dispose();   
           }
    }//GEN-LAST:event_jBCerrarActionPerformed

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
//            java.util.logging.Logger.getLogger(Tecnomecanica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Tecnomecanica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Tecnomecanica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Tecnomecanica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Tecnomecanica().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBFotoLic;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jBVer;
    private com.toedter.calendar.JDateChooser jDCExp;
    private com.toedter.calendar.JDateChooser jDCVig;
    private javax.swing.JLabel jLCenDiag;
    private javax.swing.JLabel jLCodigo;
    private javax.swing.JLabel jLFeExp;
    private javax.swing.JLabel jLFeVig;
    private javax.swing.JLabel jLFotoVig;
    private javax.swing.JLabel jLFunc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCenDiag;
    private javax.swing.JTextField jTFCodigo;
    private javax.swing.JTable jTTecno;
    // End of variables declaration//GEN-END:variables
}
