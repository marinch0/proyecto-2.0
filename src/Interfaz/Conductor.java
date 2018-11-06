/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import controlador.ConductorDAO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.ConductorVO;

/**
 *
 * @author SFS
 */
public class Conductor extends javax.swing.JFrame {

    String idFunc,idCond,nombreCond,apellidoCond,celularCond,direccionCond,correoCond,rutaFotoCond,nombreFunc,catLic,ciuLic,resLic,rutaFotoLic;
    int fila;
    boolean estadoCond;
    int eleccionFoto=1;//1 rutaFotoCond 2 rutaFotoLic
    Date vigLic,fechaEdit;
    ConductorVO transDatosCond;
    ConductorDAO DBConductor;
    DefaultTableModel modelo;
    String datos[];
    ArrayList<String> listaCond;
    boolean validacionGuardar=false;
    
    
    private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivo de Imagen", "jpg");
    
    
    
    public Conductor(String idFunc,String nomFunc) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        jTCond.setBackground(Color.WHITE);
       
        // variables 
        DBConductor = new ConductorDAO();
        this.nombreFunc=nomFunc;
        this.idFunc=idFunc;
        jLFunc.setText("Funcionario: "+this.nombreFunc);
        bloquear();
        datos = new String[11];
        listaCond = new ArrayList<>();
        modelo = new DefaultTableModel();
        llamarDatosTabla();
         try{
             Image img=ImageIO.read(new File("icoescu.png"));
             this.setIconImage(img);
         } catch(Exception e){
             System.err.println(e);
         }
         
         
    }

    public void bloquear(){// bloquea las cajas para evitar que sean usadas
        jTFID.setEnabled(false);
        jTFNombre.setEnabled(false);
        jTFApellido.setEnabled(false);
        jTFCelular.setEnabled(false);
        jTFDireccion.setEnabled(false);
        jTFCorreo.setEnabled(false);
        jBFoto.setEnabled(false);
        jCBCatLic.setEnabled(false);
        jCBCiudad.setEnabled(false);
        jDCVigLic.setEnabled(false);
        jTAResLic.setEnabled(false);
        jBFotoLic.setEnabled(false);
        jBVer.setEnabled(false);
        
        jBGuardar.setEnabled(false);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);
        jBBuscar.setEnabled(true);
    }
    
    public void activar(){ // se activan las cajas para permitir su modificacion
        jTFID.setEnabled(true);
        jTFNombre.setEnabled(true);
        jTFApellido.setEnabled(true);
        jTFCelular.setEnabled(true);
        jTFDireccion.setEnabled(true);
        jTFCorreo.setEnabled(true);
        jBFoto.setEnabled(true);
        jCBCatLic.setEnabled(true);
        jCBCiudad.setEnabled(true);
        jDCVigLic.setEnabled(true);
        jTAResLic.setEnabled(true);
        jBFotoLic.setEnabled(true);
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);
        jBBuscar.setEnabled(false);
        
    }
    
     public void llamarDatosTabla(){
        
        modelo = new DefaultTableModel();// crea las columna con los respectivos nombres 
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Celular");
        modelo.addColumn("Correo");
        modelo.addColumn("Dirección");
        modelo.addColumn("CatLic");
        modelo.addColumn("VigLic");
        modelo.addColumn("CiuLic");
        modelo.addColumn("RestLic");
        modelo.addColumn("VerLic");
        this.jTCond.setModel(modelo);
        this.jTCond.getColumn(jTCond.getColumnName(0)).setMaxWidth(90);
        this.jTCond.getColumn(jTCond.getColumnName(3)).setMaxWidth(90);
        this.jTCond.getColumn(jTCond.getColumnName(6)).setMaxWidth(50);
        this.jTCond.getColumn(jTCond.getColumnName(7)).setMaxWidth(80);
        this.jTCond.getColumn(jTCond.getColumnName(8)).setWidth(0);
        this.jTCond.getColumn(jTCond.getColumnName(8)).setMinWidth(0);
        this.jTCond.getColumn(jTCond.getColumnName(8)).setMaxWidth(0);
        this.jTCond.getColumn(jTCond.getColumnName(10)).setWidth(0);
        this.jTCond.getColumn(jTCond.getColumnName(10)).setMinWidth(0);
        this.jTCond.getColumn(jTCond.getColumnName(10)).setMaxWidth(0);
              
        
        listaCond = DBConductor.listaConductor();//llama el metodo dentro de BDconductor
        
        for (int i = 0; i < listaCond.size(); i=i+12) {
            
            datos[0]=listaCond.get(i);//rellena la tabla con los datos de la base de datos 
            datos[1]=listaCond.get(i+1);
            datos[2]=listaCond.get(i+2);
            datos[3]=listaCond.get(i+4);
            datos[4]=listaCond.get(i+5);
            datos[5]=listaCond.get(i+6);
            datos[6]=listaCond.get(i+7);
            datos[7]=listaCond.get(i+8);
            datos[8]=listaCond.get(i+9);
            datos[9]=listaCond.get(i+10);
            datos[10]=listaCond.get(i+11);
            modelo.addRow(datos);
            
        }
    }
     
    public boolean fecha(){
            String formato = jDCVigLic.getDateFormatString();
            java.util.Date date = jDCVigLic.getDate();
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
                    vigLic=Date.valueOf(fecha);       
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
            int ancho=120,alto=144;
            
            if(eleccionFoto==1){
                ancho=120;
                alto=144;
            }else if(eleccionFoto==2){
                ancho=187;
                alto=144;
            }else{
                ancho=187;
                alto=144;
            }
            Image newImg = img.getScaledInstance(ancho, alto,
                    java.awt.Image.SCALE_SMOOTH);
            //se genera un imagenIcon con la nueva imagen
            ImageIcon newIcon = new ImageIcon(newImg);
            if(eleccionFoto==1){
            rutaFotoCond=file;
            }else{
            rutaFotoLic=file; 
            jBVer.setEnabled(true);
            }
            return newIcon;
        }
        return null;
    }
     
      public boolean guardarDatos(){
        
        boolean validar= validarDatos();
        
         if(validar!=false)
        {// toma los datos de las cajas y los manda a  transdatos 
            idCond = jTFID.getText();
            nombreCond = jTFNombre.getText();
            apellidoCond = jTFApellido.getText();
            celularCond = jTFCelular.getText();
            direccionCond = jTFDireccion.getText();
            correoCond = jTFCorreo.getText();
            catLic = (String)jCBCatLic.getSelectedItem();
            boolean validarFecha=true;
            validarFecha=fecha();
            ciuLic = (String)jCBCiudad.getSelectedItem();
            resLic = jTAResLic.getText();            
            estadoCond = true;
            if(validarFecha==true){
            transDatosCond = new ConductorVO(idCond, nombreCond, apellidoCond, rutaFotoCond, celularCond, correoCond, direccionCond,catLic,vigLic, ciuLic, resLic,rutaFotoLic,idFunc,estadoCond);
            DBConductor.ingresarConductor(transDatosCond);
            llamarDatosTabla();//llama el metodo para meter los datos en la tabla
            return true;
            }
        }
        return false;
     }
      
      public boolean editarDatos(){
         boolean validar= validarDatos();
        
        if(validar!=false)// se toman los datos de las variables y se menten en las casillas
        {
            idCond = jTFID.getText();
            nombreCond = jTFNombre.getText();
            apellidoCond = jTFApellido.getText();
            celularCond = jTFCelular.getText();
            direccionCond = jTFDireccion.getText();
            correoCond = jTFCorreo.getText();
            catLic = (String)jCBCatLic.getSelectedItem();
            ciuLic = (String)jCBCiudad.getSelectedItem();
            resLic = jTAResLic.getText();    
            fecha();
            estadoCond = true;
            transDatosCond = new ConductorVO(idCond, nombreCond, apellidoCond, rutaFotoCond, celularCond, correoCond, direccionCond,catLic,vigLic, ciuLic, resLic,rutaFotoLic,idFunc,estadoCond);
            DBConductor.modificarConductor(transDatosCond);
            llamarDatosTabla();//se llaman los datos denuevo a la tabla 
            return true;        
        }
        return false;
    }
      
      public void mostrarDatos(){//pasa los datos a las casillas 
            jTFID.setText(jTCond.getValueAt(fila, 0).toString());
            jTFNombre.setText(jTCond.getValueAt(fila, 1).toString());
            jTFApellido.setText(jTCond.getValueAt(fila, 2).toString());
            jTFCelular.setText(jTCond.getValueAt(fila, 3).toString());
            jTFCorreo.setText(jTCond.getValueAt(fila, 4).toString());
            jTFDireccion.setText(jTCond.getValueAt(fila, 5).toString());
            jCBCatLic.setSelectedItem(jTCond.getValueAt(fila, 6).toString());
            vigLic=java.sql.Date.valueOf(jTCond.getValueAt(fila, 7).toString());
            jCBCiudad.setSelectedItem(jTCond.getValueAt(fila, 8).toString());
            jTAResLic.setText(jTCond.getValueAt(fila, 9).toString());
            jLFotoVig.setIcon(cargarFoto(jTCond.getValueAt(fila, 10).toString(),2));
  
            jDCVigLic.setDate(vigLic);
            
        try {
            jLFoto.setIcon(cargarFoto(DBConductor.buscarRutaFoto(jTCond.getValueAt(fila, 0).toString()),1));
        } catch (SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
      }
      
      public ImageIcon cargarFoto(String ruta, int eleccion) {
        ImageIcon icon = new ImageIcon(ruta);
        //extrae la imagen del icono
        Image img = icon.getImage();
        //cambiando el tamaño a la imagen
        int ancho=120,alto=144;
        
        if(eleccion==1){
            ancho=120;
            alto=144;
        }else if(eleccion==2){
            ancho=187;
            alto=144;
        }else{
            ancho=666;
            alto=266;
        }
        Image newImg = img.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
        //se genera un imagenIcon con la nueva imagen
        ImageIcon newIcon = new ImageIcon(newImg);
        if(eleccion==1){
        rutaFotoCond=ruta;
        }else{
        rutaFotoLic=ruta;  
        jBVer.setEnabled(true);
        }
        return newIcon;
    }
      
       public boolean validarDatos(){//valida que ninguna caja este vacia 
         
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
         }else if(jCBCatLic.getSelectedIndex()==0){
             JOptionPane.showMessageDialog(null, "Seleccionar Categoria", "Error de selección",JOptionPane.ERROR_MESSAGE);
             jCBCatLic.requestFocus();
             return false;
         }else if(jCBCiudad.getSelectedIndex()==0){
             JOptionPane.showMessageDialog(null, "Seleccionar Ciudad", "Error de selección",JOptionPane.ERROR_MESSAGE);
             jCBCiudad.requestFocus();
             return false;
         }else{
             return true;
         }
         
     }
       
       public void limpiarCajas(){//este metodo pone las cajas en ceros 
         jTFID.setText(null);     //borra todos los datos que esten en ella
         jTFNombre.setText(null);
         jTFApellido.setText(null);
         jTFCelular.setText(null);
         jTFDireccion.setText(null);
         jTFCorreo.setText(null);
         jTAResLic.setText(null);
         jDCVigLic.setDate(null);
         jCBCiudad.setSelectedIndex(0);
         jCBCatLic.setSelectedIndex(0);
         jLFoto.setIcon(null);
         jLFotoVig.setIcon(null);
                  
     }
       
    public void MostrarLicencia(){//se llama una ventana en la cual se carga la foto 
        VerLicencia linc=new VerLicencia();
        linc.cargarImg(cargarFoto(rutaFotoLic, 3));
        linc.setVisible(true);  
        
        
    }   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPCond = new javax.swing.JPanel();
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
        jPanel1 = new javax.swing.JPanel();
        jLCatLic = new javax.swing.JLabel();
        jLVigLic = new javax.swing.JLabel();
        jLCiuLic = new javax.swing.JLabel();
        jLResLic = new javax.swing.JLabel();
        jCBCatLic = new javax.swing.JComboBox<>();
        jDCVigLic = new com.toedter.calendar.JDateChooser();
        jCBCiudad = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAResLic = new javax.swing.JTextArea();
        jLFotoVig = new javax.swing.JLabel();
        jBFotoLic = new javax.swing.JButton();
        jBVer = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBNuevo = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jLFunc = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTCond = new javax.swing.JTable();
        jBCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar Conductor");

        jPCond.setBackground(new java.awt.Color(255, 255, 255));
        jPCond.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Conductor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

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

        javax.swing.GroupLayout jPCondLayout = new javax.swing.GroupLayout(jPCond);
        jPCond.setLayout(jPCondLayout);
        jPCondLayout.setHorizontalGroup(
            jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCondLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLID)
                    .addComponent(jLNombre)
                    .addComponent(jLApellido)
                    .addComponent(jLCelular)
                    .addComponent(jLCorreo)
                    .addComponent(jLDireccion))
                .addGap(18, 18, 18)
                .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCondLayout.createSequentialGroup()
                        .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jBFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPCondLayout.createSequentialGroup()
                        .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFID, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPCondLayout.setVerticalGroup(
            jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCondLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCondLayout.createSequentialGroup()
                        .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLID)
                            .addComponent(jTFID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLNombre)
                            .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLApellido)
                            .addComponent(jTFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCelular)
                            .addComponent(jTFCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCorreo)
                            .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPCondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDireccion)
                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBFoto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Licencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLCatLic.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCatLic.setText("Categoría Licencia:");

        jLVigLic.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLVigLic.setText("Vigencia Licencia:");

        jLCiuLic.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCiuLic.setText("Ciudad Licencia:");

        jLResLic.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLResLic.setText("Restricción Licencia:");

        jCBCatLic.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBCatLic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "B1", "B2", "B3", "C1", "C2", "C3" }));

        jCBCiudad.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Armenia", "Barranquilla", "Bogotá", "Bucaramanga", "Buenaventura", "Caicedonia", "Calarcá", "Cali", "Cartagena", "Circasia", "Génova", "Ibagué", "Manizales", "Medellín", "Pereira", "Puerto Colombia", "Santa Marta", "Tebaida", "Tuluá" }));

        jTAResLic.setColumns(20);
        jTAResLic.setRows(5);
        jScrollPane1.setViewportView(jTAResLic);

        jLFotoVig.setBackground(new java.awt.Color(255, 255, 255));
        jLFotoVig.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jBFotoLic.setBackground(new java.awt.Color(255, 0, 0));
        jBFotoLic.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jBFotoLic.setForeground(new java.awt.Color(255, 255, 255));
        jBFotoLic.setText("Subir Licencia");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCatLic)
                            .addComponent(jLVigLic)
                            .addComponent(jLCiuLic))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCBCatLic, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDCVigLic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBCiudad, 0, 230, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLResLic)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBFotoLic, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBVer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(jLFotoVig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLCatLic)
                                    .addComponent(jCBCatLic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLVigLic))
                            .addComponent(jDCVigLic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCiuLic)
                            .addComponent(jCBCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLResLic)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLFotoVig, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBVer)
                            .addComponent(jBFotoLic))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

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

        jBEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

        jLFunc.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLFunc.setText("Funcionario");

        jTCond.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTCond);

        jBCerrar.setBackground(new java.awt.Color(255, 255, 255));
        jBCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        jBCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPCond, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(182, 182, 182)
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
                                .addComponent(jLFunc)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPCond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBNuevo)
                            .addComponent(jBGuardar)
                            .addComponent(jBEditar)
                            .addComponent(jBBuscar)
                            .addComponent(jBEliminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jLFunc))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFotoActionPerformed
        eleccionFoto=1;//ruta de la foto del cunductor 
        jLFoto.setIcon(subirFoto());//se sube la foto al label
    }//GEN-LAST:event_jBFotoActionPerformed

    private void jBFotoLicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFotoLicActionPerformed
        eleccionFoto=2;//ruta foto de la licencia 
        jLFotoVig.setIcon(subirFoto());//se sube la foto al label
    }//GEN-LAST:event_jBFotoLicActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        String idBuscar = JOptionPane.showInputDialog("Digite Codigo del Conductor:");
        ArrayList<String> datosBusqueda = new ArrayList<>();

        if(idBuscar!=null)
        {//buscar el dato en condctor con el id 
            datosBusqueda = DBConductor.buscarConductor(idBuscar);

            if(datosBusqueda==null || datosBusqueda.isEmpty())//si no se encuentra algun dato 
            {                                                 //salta la validacion
                JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);
            }else{// si se encuentra el dato no salta un mensaje en el cual nos salen todos los datos de el conductor 
                String est=datosBusqueda.get(12).equals("1")?"Activo":"Inactivo";
                JOptionPane.showMessageDialog(null, "Conductor:\nCodigo: "+idBuscar+"\nNombre: "+datosBusqueda.get(1)+"\nApellido: "+datosBusqueda.get(2)
                    +"\nRuta FotoC: "+datosBusqueda.get(3)+"\nCelular: "+datosBusqueda.get(4)+"\nCorreo: "+datosBusqueda.get(5)+"\nDirección: "+datosBusqueda.get(6)+"\nCat Lic: "+datosBusqueda.get(7)
                        +"\nVigencia Lic: "+datosBusqueda.get(8)+"\nCiudad Lic: "+datosBusqueda.get(9)+"\nRestricción: "+datosBusqueda.get(10)+"\nRuta FotoL: "+datosBusqueda.get(11)+"\nEstado: "+est);
            }
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
        validacionGuardar=true;
        fila=jTCond.getSelectedRow();//toma los datos de la tabla los llama a las cajas, activa las cajas y el boton de guardar se activa
        if(fila!=-1){
            mostrarDatos();
            activar();
            jTFID.setEnabled(false);//se bloquea la casilla de id para impedir la modificacion 
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
        activar();//activa las tablas 
        jTFID.requestFocus();//bloquea el id para que no pueda ser modificado
    }//GEN-LAST:event_jBNuevoActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        fila=jTCond.getSelectedRow();//fija la fila a la cual se le dio click
        if(fila!=-1){
            int id=Integer.parseInt(jTCond.getValueAt(fila, 0).toString());
            boolean validarEliminar = DBConductor.modificarEstadoConductor(String.valueOf(id));//modifica el estado poniendolo en 0
            if(validarEliminar!=false)
            {
                llamarDatosTabla();//se actualiza la tabla omitiendo los que tienen estado 0
                JOptionPane.showMessageDialog(null, "Registro Eliminado!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea eliminar", "Error", 0);
        }
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_jBCerrarActionPerformed

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

    private void jBVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerActionPerformed
        MostrarLicencia();
    }//GEN-LAST:event_jBVerActionPerformed

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
//            java.util.logging.Logger.getLogger(Conductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Conductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Conductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Conductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //jPCOndr-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Conductor().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBFoto;
    private javax.swing.JButton jBFotoLic;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jBVer;
    private javax.swing.JComboBox<String> jCBCatLic;
    private javax.swing.JComboBox<String> jCBCiudad;
    private com.toedter.calendar.JDateChooser jDCVigLic;
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLCatLic;
    private javax.swing.JLabel jLCelular;
    private javax.swing.JLabel jLCiuLic;
    private javax.swing.JLabel jLCorreo;
    private javax.swing.JLabel jLDireccion;
    private javax.swing.JLabel jLFoto;
    private javax.swing.JLabel jLFotoVig;
    private javax.swing.JLabel jLFunc;
    private javax.swing.JLabel jLID;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLResLic;
    private javax.swing.JLabel jLVigLic;
    private javax.swing.JPanel jPCond;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTAResLic;
    private javax.swing.JTable jTCond;
    private javax.swing.JTextField jTFApellido;
    private javax.swing.JTextField jTFCelular;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFID;
    private javax.swing.JTextField jTFNombre;
    // End of variables declaration//GEN-END:variables
}
