/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;


import controlador.AseguradoraDAO;
import controlador.SOATDAO;
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
import modelo.SOATVO;
import modelo.VehiculoVO;

/**
 *
 * @author SFS
 */
public class SOAT extends javax.swing.JFrame {

    String codigoSoat,idFuncSoat,nombFunc,rutaFotoSoat;
    int fila,filaP,idAseg;
    int eleccionFoto=1;//1 rutaFotoCond 2 rutaFotoLic
    boolean estadoAseg,datoVen=false;
    Date fechaExp,fechaVig;
    SOATVO transDatosSoat;
    VehiculoVO transDatosVeh;
    SOATDAO DBSoat;
    AseguradoraDAO DBAseg;
    DefaultTableModel modelo;
    String datos[];
    ArrayList<String> listaSoat;
    ArrayList<String> listaAseg;
    boolean validacionGuardar=false;
    
    private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivo de Imagen", "jpg");
    
    public SOAT(String idFunc,String nomFunc,VehiculoVO datosVeh,boolean datoVentana) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        jTSoat.setBackground(Color.WHITE);
        jCBAseg.setBackground(Color.WHITE);
        
        DBSoat = new SOATDAO();
        DBAseg = new AseguradoraDAO();
        idFuncSoat = idFunc;
        nombFunc=nomFunc;
        jLFunc.setText("Funcionario: "+nombFunc);
        bloquear();
        datos = new String[4];
        listaSoat = new ArrayList<>();
        listaAseg = new ArrayList<>();
        modelo = new DefaultTableModel();
        cargarAseguradora();
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
        jCBAseg.setEnabled(false);
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
        jCBAseg.setEnabled(true);
        jDCExp.setEnabled(true);
        jDCVig.setEnabled(true);
        jBFotoLic.setEnabled(true);
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBBuscar.setEnabled(false);
        jBEliminar.setEnabled(false);
        
        
    }
     
    public void cargarAseguradora(){
        listaAseg = DBAseg.buscarListaAseguradora();
        for (int i = 0; i < listaAseg.size(); i+=2) {
            jCBAseg.addItem(listaAseg.get(i+1));
            
        }
    }
     
     public void llamarDatosTabla(){
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Adeguradora");
        modelo.addColumn("Fecha Exp");
        modelo.addColumn("Fecha Vig");
        this.jTSoat.setModel(modelo);
        this.jTSoat.getColumn(jTSoat.getColumnName(0)).setMaxWidth(100);
        
        listaSoat=DBSoat.listaSOAT();
        for (int i = 0; i < listaSoat.size(); i=i+4) {
            
            datos[0]=String.valueOf(Integer.parseInt(listaSoat.get(i)));
            
            try {
                datos[1]=DBAseg.buscarAseguradora(Integer.parseInt(listaSoat.get(i+1)));
            } catch (SQLException ex) {
                Logger.getLogger(SOAT.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            datos[2]=listaSoat.get(i+2);
            datos[3]=listaSoat.get(i+3);
            
            modelo.addRow(datos);
            
        }
    }
     
      public boolean validarDatos(){
        
        if(jTFCodigo.getText().equals("")||jTFCodigo.getText().equals(" ")||jTFCodigo.getText().equals(null)){
            JOptionPane.showMessageDialog(null, "No Ingreso Código", "Error de selección",JOptionPane.ERROR_MESSAGE);
            jTFCodigo.requestFocus();
            return false;
        }else if(jCBAseg.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Seleccionar Aseguradora", "Error de selección",JOptionPane.ERROR_MESSAGE);
            jCBAseg.requestFocus();
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
            
            Image newImg = img.getScaledInstance(242, 109,
                    java.awt.Image.SCALE_SMOOTH);
            //se genera un imagenIcon con la nueva imagen
            ImageIcon newIcon = new ImageIcon(newImg);
            
            rutaFotoSoat=file;
            jBVer.setEnabled(true);
            
            return newIcon;
        }
        return null;
    }  
      
     public void MostrarLicencia(){
        VerLicencia linc=new VerLicencia();
        linc.cargarImg(cargarFoto(rutaFotoSoat, 2));
        linc.setVisible(true);  
              
    }   
    
    public ImageIcon cargarFoto(String ruta, int eleccion) {
        ImageIcon icon = new ImageIcon(ruta);
        //extrae la imagen del icono
        Image img = icon.getImage();
        //cambiando el tamaño a la imagen
        int ancho=187,alto=144;
        
        if(eleccion==1){
            ancho=242;
            alto=109;
        }else if(eleccion==2){
            ancho=666;
            alto=266;
        }
        
        Image newImg = img.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
        //se genera un imagenIcon con la nueva imagen
        ImageIcon newIcon = new ImageIcon(newImg);
        
        rutaFotoSoat=ruta;  
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
            codigoSoat = jTFCodigo.getText();
            try {
                idAseg = DBAseg.buscarIdAseguradora((String)jCBAseg.getSelectedItem());
            } catch (SQLException ex) {
                Logger.getLogger(SOAT.class.getName()).log(Level.SEVERE, null, ex);
            }
            fechaExp();
            boolean validarFecha=true;
            validarFecha=fechaVig();
            estadoAseg = true;
            if(validarFecha==true){
            transDatosSoat = new SOATVO(codigoSoat, idAseg, fechaExp,fechaVig, rutaFotoSoat,idFuncSoat, estadoAseg);
            DBSoat.ingresarSOAT(transDatosSoat);
            llamarDatosTabla();
            return true;        
            }
        }
        return false;
    }
    
     public void limpiarCajas(){
         jTFCodigo.setText(null);
         jCBAseg.setSelectedIndex(0);
         jLFotoVig.setIcon(null);
         jDCExp.setDate(null);
         jDCVig.setDate(null);
                  
     }  
      
    public boolean editarDatos(){
         boolean validar= validarDatos();
        
        if(validar!=false)
        {
            codigoSoat = jTFCodigo.getText();
            
             try {
                 idAseg = DBAseg.buscarIdAseguradora(jCBAseg.getSelectedItem().toString());
             } catch (SQLException ex) {
                 Logger.getLogger(SOAT.class.getName()).log(Level.SEVERE, null, ex);
             }
           
            fechaExp();
            fechaVig();
            estadoAseg = true;
            transDatosSoat = new SOATVO(codigoSoat, idAseg, fechaExp,fechaVig, rutaFotoSoat,idFuncSoat, estadoAseg);
            DBSoat.modificaSOAT(transDatosSoat);
            llamarDatosTabla();
            return true;        
        }
        return false;
    }
    
    public void mostrarDatos(){
            jTFCodigo.setText(jTSoat.getValueAt(fila, 0).toString());
            jCBAseg.setSelectedItem(jTSoat.getValueAt(fila, 1).toString());
            fechaExp=java.sql.Date.valueOf(jTSoat.getValueAt(fila, 2).toString());
            fechaVig=java.sql.Date.valueOf(jTSoat.getValueAt(fila, 3).toString());
            jDCExp.setDate(fechaExp);
            jDCVig.setDate(fechaVig);                  
            
            
        try {
            jLFotoVig.setIcon(cargarFoto(DBSoat.buscarRutaFoto(jTSoat.getValueAt(fila, 0).toString()),1));
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

        jPSoat = new javax.swing.JPanel();
        jLCodigo = new javax.swing.JLabel();
        jLAseg = new javax.swing.JLabel();
        jLFeExp = new javax.swing.JLabel();
        jLFeVig = new javax.swing.JLabel();
        jTFCodigo = new javax.swing.JTextField();
        jCBAseg = new javax.swing.JComboBox<>();
        jDCExp = new com.toedter.calendar.JDateChooser();
        jDCVig = new com.toedter.calendar.JDateChooser();
        jLFotoVig = new javax.swing.JLabel();
        jBFotoLic = new javax.swing.JButton();
        jBVer = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBNuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTSoat = new javax.swing.JTable();
        jBCerrar = new javax.swing.JButton();
        jLFunc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ingreso SOAT");

        jPSoat.setBackground(new java.awt.Color(255, 255, 255));
        jPSoat.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro SOAT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLCodigo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCodigo.setText("Código:");

        jLAseg.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLAseg.setText("Aseguradora:");

        jLFeExp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLFeExp.setText("Fecha Expedición:");

        jLFeVig.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLFeVig.setText("Fecha Vigencia:");

        jTFCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCodigoKeyTyped(evt);
            }
        });

        jCBAseg.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBAseg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));

        jLFotoVig.setBackground(new java.awt.Color(255, 255, 255));
        jLFotoVig.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jBFotoLic.setBackground(new java.awt.Color(255, 0, 0));
        jBFotoLic.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jBFotoLic.setForeground(new java.awt.Color(255, 255, 255));
        jBFotoLic.setText("Subir SOAT");
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

        javax.swing.GroupLayout jPSoatLayout = new javax.swing.GroupLayout(jPSoat);
        jPSoat.setLayout(jPSoatLayout);
        jPSoatLayout.setHorizontalGroup(
            jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSoatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPSoatLayout.createSequentialGroup()
                        .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCodigo)
                            .addComponent(jLAseg)
                            .addComponent(jLFeExp))
                        .addGap(24, 24, 24)
                        .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDCVig, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTFCodigo)
                                .addComponent(jCBAseg, 0, 228, Short.MAX_VALUE)
                                .addComponent(jDCExp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLFeVig))
                .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPSoatLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jBFotoLic, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBVer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addGroup(jPSoatLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLFotoVig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPSoatLayout.setVerticalGroup(
            jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSoatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPSoatLayout.createSequentialGroup()
                        .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCodigo)
                            .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLAseg)
                            .addComponent(jCBAseg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLFeExp)
                            .addComponent(jDCExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLFeVig)
                            .addComponent(jDCVig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPSoatLayout.createSequentialGroup()
                        .addComponent(jLFotoVig, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPSoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBVer)
                            .addComponent(jBFotoLic))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

        jBBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jBEditar.setBackground(new java.awt.Color(255, 255, 255));
        jBEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        jBEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarActionPerformed(evt);
            }
        });

        jBGuardar.setBackground(new java.awt.Color(255, 255, 255));
        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jBNuevo.setBackground(new java.awt.Color(255, 255, 255));
        jBNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        jBNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNuevoActionPerformed(evt);
            }
        });

        jTSoat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTSoat);

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPSoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(185, 185, 185)
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLFunc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPSoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBNuevo)
                            .addComponent(jBGuardar)
                            .addComponent(jBEditar)
                            .addComponent(jBBuscar)
                            .addComponent(jBEliminar))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLFunc))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBFotoLicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFotoLicActionPerformed
        eleccionFoto=1;
        jLFotoVig.setIcon(subirFoto());
    }//GEN-LAST:event_jBFotoLicActionPerformed

    private void jBVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerActionPerformed
        MostrarLicencia();
    }//GEN-LAST:event_jBVerActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        fila=jTSoat.getSelectedRow();
        if(fila!=-1){
            int id=Integer.parseInt(jTSoat.getValueAt(fila, 0).toString());
            boolean validarEliminar = DBSoat.modificarEstadoSOAT(id);
            if(validarEliminar!=false)
            {
                llamarDatosTabla();
                JOptionPane.showMessageDialog(null, "Registro Eliminado!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea eliminar", "Error", 0);
        }
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
       String idBuscar = JOptionPane.showInputDialog("Digite Código del SOAT:");
        ArrayList<String> datosBusqueda = new ArrayList<>();

        if(idBuscar!=null)
        {
            datosBusqueda = DBSoat.buscarSoat(idBuscar);

            if(datosBusqueda==null || datosBusqueda.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);
            }else{
                String aseguradora;
                try {
                    String est=datosBusqueda.get(5).equals("1")?"Activo":"Inactivo";
                    aseguradora=DBAseg.buscarAseguradora(Integer.parseInt(datosBusqueda.get(1)));
                    JOptionPane.showMessageDialog(null, "SOAT:\nCodigo: "+idBuscar+"\nAseguradora: "+aseguradora+"\nFecha Expedición: "+datosBusqueda.get(2)
                            +"\nFecha Vigencia: "+datosBusqueda.get(3)+"\nRuta Foto: "+datosBusqueda.get(4)+"\nEstado: "+est);
                } catch (SQLException ex) {
                    Logger.getLogger(SOAT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
       validacionGuardar=true;
        fila=jTSoat.getSelectedRow();
        if(fila!=-1){
            activar();
            mostrarDatos();
            jTFCodigo.setEnabled(false);

        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea editar", "Error", 0);
        }
    }//GEN-LAST:event_jBEditarActionPerformed

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

    private void jBNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoActionPerformed
        activar();
        jTFCodigo.requestFocus();
    }//GEN-LAST:event_jBNuevoActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        
           if(datoVen==true){
                 int opcion=Integer.parseInt(JOptionPane.showInputDialog("Seleccionar Código SOAT:\n1. Ultimo registro\n2. Seleccionar un Registro de la tabla"));
                 switch (opcion) {
                   case 1:
                       transDatosVeh.setCod_SOAT(codigoSoat);
                       Vehiculo miVeh = new Vehiculo(idFuncSoat, nombFunc,transDatosVeh);
                       miVeh.setVisible(true);
                       dispose();
                       break;
                   case 2:
                       fila=jTSoat.getSelectedRow();
                       if(fila!=-1){
                       transDatosVeh.setCod_SOAT(jTSoat.getValueAt(fila, 0).toString());
                       Vehiculo miVeh1 = new Vehiculo(idFuncSoat, nombFunc,transDatosVeh);
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

    private void jTFCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCodigoKeyTyped
        char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }
    }//GEN-LAST:event_jTFCodigoKeyTyped

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
//            java.util.logging.Logger.getLogger(SOAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SOAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SOAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SOAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SOAT().setVisible(true);
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
    private javax.swing.JComboBox<String> jCBAseg;
    private com.toedter.calendar.JDateChooser jDCExp;
    private com.toedter.calendar.JDateChooser jDCVig;
    private javax.swing.JLabel jLAseg;
    private javax.swing.JLabel jLCodigo;
    private javax.swing.JLabel jLFeExp;
    private javax.swing.JLabel jLFeVig;
    private javax.swing.JLabel jLFotoVig;
    private javax.swing.JLabel jLFunc;
    private javax.swing.JPanel jPSoat;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCodigo;
    private javax.swing.JTable jTSoat;
    // End of variables declaration//GEN-END:variables
}
