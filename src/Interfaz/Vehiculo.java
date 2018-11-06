/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import controlador.AsistenteDAO;
import controlador.ConductorDAO;
import controlador.VehiculoDAO;
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
import modelo.VehiculoVO;

/**
 *
 * @author SFS
 */
public class Vehiculo extends javax.swing.JFrame {

    String idFunc,placaVeh,modeloVeh,tipoVeh,empresaVeh,marcaVeh,rutaFotoVeh,idCond,idAsis,codSoat,codTecno,nombreFunc;
    int fila,numVeh,capVeh;
    boolean estadoVeh;
    VehiculoVO transDatosVeh;
    VehiculoDAO DBVehiculo;
    AsistenteDAO DBAsistente;
    ConductorDAO DBConductor;
    DefaultTableModel modelo;
    String datos[];
    ArrayList<String> listaVeh;
    ArrayList<String> listaCond;
    ArrayList<String> listaAsis;
    boolean validacionGuardar=false;
    
    private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivo de Imagen", "jpg");
    
    public Vehiculo(String idFunc,String nomFunc,VehiculoVO datosVeh) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        jTVeh.setBackground(Color.WHITE);
        
        DBVehiculo = new VehiculoDAO();
        DBAsistente = new AsistenteDAO();
        DBConductor = new ConductorDAO();
        this.nombreFunc=nomFunc;
        this.idFunc=idFunc;
        jLFunc.setText("Funcionario: "+this.nombreFunc);
        bloquear();
        datos = new String[11];
        listaVeh = new ArrayList<>();
        listaCond = new ArrayList<>();
        listaAsis = new ArrayList<>();
        cargarConductor();
        cargarAsistente();
        modelo = new DefaultTableModel();
        llamarDatosTabla();
         try{
             Image img=ImageIO.read(new File("icoescu.png"));
             this.setIconImage(img);
         } catch(Exception e){
             System.err.println(e);
         }
         if(datosVeh!=null){
         transDatosVeh=datosVeh;//revisar
         datosDev();
         activar();
         }
    }
    //revisar
    public void datosDev(){
        String nombre = transDatosVeh.getPlaca();
        jTFPlaLetras.setText(nombre.substring(0, 3));
        jTFPlaNumeros.setText(nombre.substring(3, 6));
        jTFNumVeh.setText(transDatosVeh.getNumero()+"");
        jTFModelo.setText(transDatosVeh.getModelo());
        jCBTipVeh.setSelectedItem(transDatosVeh.getTipo());
        jSCap.setValue(transDatosVeh.getCapacidad());
        jTFEmpresa.setText(transDatosVeh.getEmpresa());
        jCBMarca.setSelectedItem(transDatosVeh.getMarca());
        jTFIDCond.setText(transDatosVeh.getId_cond());
        jTFIDAsis.setText(transDatosVeh.getId_asis());
        jTFNumSoat.setText(transDatosVeh.getCod_SOAT());
        jTFNumTecno.setText(transDatosVeh.getCod_tecno());
        rutaFotoVeh=transDatosVeh.getRuta_foto();
        
        try {
            jTFNomCond.setText(DBConductor.buscarConductorVeh(transDatosVeh.getId_cond()));
            jTFNomAsis.setText(DBAsistente.buscarAsistenteVeh(transDatosVeh.getId_asis()));
            jLFoto.setIcon(cargarFoto(rutaFotoVeh));
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }           
        
        
        
    }
    
    
    public void bloquear(){
        jTFPlaLetras.setEnabled(false);
        jTFPlaNumeros.setEnabled(false);
        jTFNumVeh.setEnabled(false);
        jTFModelo.setEnabled(false);
        jTFEmpresa.setEnabled(false);
        jCBTipVeh.setEnabled(false);
        jSCap.setEnabled(false);
        jCBMarca.setEnabled(false);
        jCBCond.setEnabled(false);
        jCBAsis.setEnabled(false);
        jBBuscarCond.setEnabled(false);
        jBBuscarAsis.setEnabled(false);
        jBSoat.setEnabled(false);
        jBTecno.setEnabled(false);
        jBFoto.setEnabled(false);
        jBGuardar.setEnabled(false);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);
        jBBuscar.setEnabled(true);
    }
    
    public void activar(){
        jTFPlaLetras.setEnabled(true);
        jTFPlaNumeros.setEnabled(true);
        jTFNumVeh.setEnabled(true);
        jTFEmpresa.setEnabled(true);
        jTFModelo.setEnabled(true);
        jCBTipVeh.setEnabled(true);
        jSCap.setEnabled(true);
        jCBMarca.setEnabled(true);
        jCBCond.setEnabled(true);
        jCBAsis.setEnabled(true);
        jBBuscarCond.setEnabled(true);
        jBBuscarAsis.setEnabled(true);
        jBSoat.setEnabled(true);
        jBTecno.setEnabled(true);
        jBFoto.setEnabled(true);
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);
        jBBuscar.setEnabled(false);
        
    }
    
    public void cargarConductor(){
        listaCond = DBConductor.buscarListaConductor();
        for (int i = 0; i < listaCond.size(); i++) {
            jCBCond.addItem(listaCond.get(i));
            
        }
    }
    
    public void cargarAsistente(){
        listaAsis = DBAsistente.buscarListaAsistente();
        for (int i = 0; i < listaAsis.size(); i++) {
            jCBAsis.addItem(listaAsis.get(i));
            
        }
    }
    
       
     public void llamarDatosTabla(){
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Placa");
        modelo.addColumn("No");
        modelo.addColumn("Modelo");
        modelo.addColumn("Tipo");
        modelo.addColumn("Cap");
        modelo.addColumn("Empresa");
        modelo.addColumn("Marca");
        modelo.addColumn("Conductor");
        modelo.addColumn("Asistente");
        modelo.addColumn("SOAT");
        modelo.addColumn("Tecno");
        this.jTVeh.setModel(modelo);
        this.jTVeh.getColumn(jTVeh.getColumnName(0)).setMaxWidth(50);
        this.jTVeh.getColumn(jTVeh.getColumnName(1)).setMaxWidth(40);
        this.jTVeh.getColumn(jTVeh.getColumnName(2)).setMaxWidth(50);
        this.jTVeh.getColumn(jTVeh.getColumnName(3)).setMaxWidth(50);
        this.jTVeh.getColumn(jTVeh.getColumnName(4)).setMaxWidth(50);
              
        
        listaVeh = DBVehiculo.listaVehiculo();
        
        for (int i = 0; i < listaVeh.size(); i=i+12) {
            
            datos[0]=listaVeh.get(i);
            datos[1]=listaVeh.get(i+1);
            datos[2]=listaVeh.get(i+2);
            datos[3]=listaVeh.get(i+3);
            datos[4]=listaVeh.get(i+4);
            datos[5]=listaVeh.get(i+5);
            datos[6]=listaVeh.get(i+6);
            try {
                datos[7]=DBConductor.buscarConductorVeh(listaVeh.get(i+8));
                datos[8]=DBAsistente.buscarAsistenteVeh(listaVeh.get(i+9));
            } catch (SQLException ex) {
                Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            datos[9]=listaVeh.get(i+10);
            datos[10]=listaVeh.get(i+11);
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
            Image newImg = img.getScaledInstance(170, 130,
                    java.awt.Image.SCALE_SMOOTH);
            //se genera un imagenIcon con la nueva imagen
            ImageIcon newIcon = new ImageIcon(newImg);
            rutaFotoVeh=file;
                      
            return newIcon;
        }
        return null;
    }
     
      public boolean guardarDatos(){
        
        boolean validar= validarDatos();
        
         if(validar!=false)
        {
            placaVeh = jTFPlaLetras.getText()+jTFPlaNumeros.getText();
            numVeh = Integer.parseInt(jTFNumVeh.getText());
            modeloVeh = jTFModelo.getText();
            tipoVeh = (String)jCBTipVeh.getSelectedItem();
            capVeh = Integer.parseInt(jSCap.getValue().toString());
            empresaVeh= jTFEmpresa.getText();
            marcaVeh= (String)jCBMarca.getSelectedItem();
            idCond=jTFIDCond.getText();
            idAsis=jTFIDAsis.getText();
            codSoat=jTFNumSoat.getText();
            codTecno=jTFNumTecno.getText();
            estadoVeh = true;
            
            
            transDatosVeh = new VehiculoVO(placaVeh, numVeh,modeloVeh,tipoVeh,capVeh,empresaVeh, marcaVeh,rutaFotoVeh,idCond,idAsis,codSoat,codTecno,idFunc,estadoVeh);
            DBVehiculo.ingresarVehiculo(transDatosVeh);
            llamarDatosTabla();
            return true;        
        }
        return false;
     }
      
      public boolean editarDatos(){
         boolean validar= validarDatos();
        
        if(validar!=false)
        {
            placaVeh = jTFPlaLetras.getText()+jTFPlaNumeros.getText();
            numVeh = Integer.parseInt(jTFNumVeh.getText());
            modeloVeh = jTFModelo.getText();
            tipoVeh = (String)jCBTipVeh.getSelectedItem();
            capVeh = Integer.parseInt(jSCap.getValue().toString());
            empresaVeh= jTFEmpresa.getText();
            marcaVeh= (String)jCBMarca.getSelectedItem();
            idCond=jTFIDCond.getText();
            idAsis=jTFIDAsis.getText();
            codSoat=jTFNumSoat.getText();
            codTecno=jTFNumTecno.getText();
                        
            transDatosVeh = new VehiculoVO(placaVeh, numVeh,modeloVeh,tipoVeh,capVeh,empresaVeh, marcaVeh,rutaFotoVeh,idCond,idAsis,codSoat,codTecno,idFunc,estadoVeh);
            DBVehiculo.modificarVehiculo(transDatosVeh);
            llamarDatosTabla();
            return true;        
        }
        return false;
    }
      
      public void mostrarDatos(){
            String divPlacas=jTVeh.getValueAt(fila, 0).toString();
            jTFPlaLetras.setText(divPlacas.substring(0, 3));
            jTFPlaNumeros.setText(divPlacas.substring(3, 6));
            jTFNumVeh.setText(jTVeh.getValueAt(fila, 1).toString());
            jTFModelo.setText(jTVeh.getValueAt(fila, 2).toString());
            jCBTipVeh.setSelectedItem(jTVeh.getValueAt(fila, 3).toString());
            jSCap.setValue(Integer.parseInt(jTVeh.getValueAt(fila, 4).toString()));
            jTFEmpresa.setText(jTVeh.getValueAt(fila, 5).toString());
            jCBMarca.setSelectedItem(jTVeh.getValueAt(fila, 6).toString());
            jTFNomCond.setText(jTVeh.getValueAt(fila, 7).toString());
            jTFNomAsis.setText(jTVeh.getValueAt(fila, 8).toString());
            jTFNumSoat.setText(jTVeh.getValueAt(fila, 9).toString());
            jTFNumTecno.setText(jTVeh.getValueAt(fila, 10).toString());
            
            try {
            jTFIDCond.setText(DBConductor.buscarIdConductor(jTVeh.getValueAt(fila, 7).toString()));
            jTFIDAsis.setText(DBAsistente.buscarIdAsistente(jTVeh.getValueAt(fila, 8).toString()));
            jLFoto.setIcon(cargarFoto(DBVehiculo.buscarRutaFoto(jTVeh.getValueAt(fila, 0).toString())));
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }           
            
      }
      
      public ImageIcon cargarFoto(String ruta) {
        ImageIcon icon = new ImageIcon(ruta);
        //extrae la imagen del icono
        Image img = icon.getImage();
        //cambiando el tamaño a la imagen
        Image newImg = img.getScaledInstance(170, 130, java.awt.Image.SCALE_SMOOTH);
        //se genera un imagenIcon con la nueva imagen
        ImageIcon newIcon = new ImageIcon(newImg);
        rutaFotoVeh=ruta;
        return newIcon;
    }
      
       public boolean validarDatos(){
         
         if(jTFPlaLetras.getText().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Ingresar Letras Placa", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFPlaLetras.requestFocus();
             return false;
         } else if (jTFPlaNumeros.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Números Placa", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFPlaNumeros.requestFocus();
             return false;
         }else if(jTFNumVeh.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Número Vehiculo", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFNumVeh.requestFocus();
             return false;
         }else if(jTFModelo.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Modelo", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFModelo.requestFocus();
             return false;
         }else if(jCBTipVeh.getSelectedIndex()==0){
             JOptionPane.showMessageDialog(null, "Seleccionar Tipo Vehiculo", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jCBTipVeh.requestFocus();
             return false;
         }else if(Integer.parseInt(jSCap.getValue().toString())==0){
             JOptionPane.showMessageDialog(null, "Ingresar Capacidad", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jSCap.requestFocus();
             return false;
         }else if(jTFEmpresa.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Empresa", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFEmpresa.requestFocus();
             return false;
         }else if(jCBMarca.getSelectedIndex()==0){
             JOptionPane.showMessageDialog(null, "Seleccionar Marca", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jCBMarca.requestFocus();
             return false;
         }else if(jTFIDCond.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Seleccionar Conductor", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jCBCond.requestFocus();
             return false;
         }else if(jTFIDAsis.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Seleccionar Asistente", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jCBAsis.requestFocus();
             return false;
         }else if(jTFNumSoat.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar SOAT", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jBSoat.requestFocus();
             return false;
         }else if(jTFNumTecno.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Tecnomecanica", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jBTecno.requestFocus();
             return false;
         }else{
             return true;
         }
         
     }
       
       public void limpiarCajas(){
         jTFPlaLetras.setText(null);
         jTFPlaNumeros.setText(null);
         jTFNumVeh.setText(null);
         jTFModelo.setText(null);
         jCBTipVeh.setSelectedIndex(0);
         jSCap.setValue(0);
         jTFEmpresa.setText(null);
         jCBMarca.setSelectedIndex(0);
         jLFoto.setIcon(null);
         jTFIDCond.setText(null);
         jTFIDAsis.setText(null);
         jTFNomCond.setText(null);
         jTFNomAsis.setText(null);
         jCBCond.setSelectedIndex(0);
         jCBAsis.setSelectedIndex(0);
         jTFNumSoat.setText(null);
         jTFNumTecno.setText(null);
                  
     }
     
       //revisar
     public VehiculoVO enviarDatos(){
         
         placaVeh=jTFPlaLetras.getText()+jTFPlaNumeros.getText();
         numVeh=Integer.parseInt(jTFNumVeh.getText());
         modeloVeh=jTFModelo.getText();
         tipoVeh= jCBTipVeh.getSelectedItem().toString();
         capVeh=Integer.parseInt(jSCap.getValue().toString());
         empresaVeh= jTFEmpresa.getText();
         marcaVeh=jCBMarca.getSelectedItem().toString();
         idCond=jTFIDCond.getText();
         idAsis=jTFIDAsis.getText();
         codSoat=jTFNumSoat.getText();
         codTecno=jTFNumTecno.getText();
         estadoVeh=true;
         
         transDatosVeh = new VehiculoVO(placaVeh, numVeh, modeloVeh, tipoVeh, capVeh, empresaVeh, marcaVeh, rutaFotoVeh, idCond, idAsis, codSoat, codTecno, idFunc, estadoVeh);
         
         return transDatosVeh;
         
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPVeh = new javax.swing.JPanel();
        jLPlaca = new javax.swing.JLabel();
        jLNum = new javax.swing.JLabel();
        jLModelo = new javax.swing.JLabel();
        jLTipVeh = new javax.swing.JLabel();
        jLCCap = new javax.swing.JLabel();
        jLEmp = new javax.swing.JLabel();
        jLMarca = new javax.swing.JLabel();
        jTFPlaLetras = new javax.swing.JTextField();
        jTFPlaNumeros = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFNumVeh = new javax.swing.JTextField();
        jTFModelo = new javax.swing.JTextField();
        jCBTipVeh = new javax.swing.JComboBox<>();
        jSCap = new javax.swing.JSpinner();
        jTFEmpresa = new javax.swing.JTextField();
        jCBMarca = new javax.swing.JComboBox<>();
        jLFoto = new javax.swing.JLabel();
        jBFoto = new javax.swing.JButton();
        jPIngCondAsis = new javax.swing.JPanel();
        jLCond = new javax.swing.JLabel();
        jCBCond = new javax.swing.JComboBox<>();
        jTFIDCond = new javax.swing.JTextField();
        jTFNomCond = new javax.swing.JTextField();
        jLAsis = new javax.swing.JLabel();
        jTFIDAsis = new javax.swing.JTextField();
        jTFNomAsis = new javax.swing.JTextField();
        jCBAsis = new javax.swing.JComboBox<>();
        jBBuscarCond = new javax.swing.JButton();
        jBBuscarAsis = new javax.swing.JButton();
        jPIngSoatTecno = new javax.swing.JPanel();
        jBSoat = new javax.swing.JButton();
        jBTecno = new javax.swing.JButton();
        jLNumSoat = new javax.swing.JLabel();
        jLNumTecno = new javax.swing.JLabel();
        jTFNumSoat = new javax.swing.JTextField();
        jTFNumTecno = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTVeh = new javax.swing.JTable();
        jBEliminar = new javax.swing.JButton();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jLFunc = new javax.swing.JLabel();
        jBCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar Vehiculo");

        jPVeh.setBackground(new java.awt.Color(255, 255, 255));
        jPVeh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Vehiculo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLPlaca.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLPlaca.setText("Placa:");

        jLNum.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLNum.setText("No Vehiculo:");

        jLModelo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLModelo.setText("Modelo:");

        jLTipVeh.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLTipVeh.setText("Tipo Vehiculo:");

        jLCCap.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCCap.setText("Capacidad:");

        jLEmp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLEmp.setText("Empresa:");

        jLMarca.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLMarca.setText("Marca:");

        jTFPlaNumeros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFPlaNumerosKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Letras");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("Números");

        jTFNumVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNumVehActionPerformed(evt);
            }
        });
        jTFNumVeh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFNumVehKeyTyped(evt);
            }
        });

        jTFModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFModeloActionPerformed(evt);
            }
        });
        jTFModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFModeloKeyTyped(evt);
            }
        });

        jCBTipVeh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBTipVeh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Bus", "Buseta", "Micro_Bus" }));

        jTFEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFEmpresaActionPerformed(evt);
            }
        });

        jCBMarca.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Audi", "BMW", "Chevrolet", "Citroen", "Daihatsu", "Dodge", "Fiat", "Ford", "Honda", "Hyundai", "Jeep", "Kia", "Mazda", "Mercedes-Benz", "Mini", "Mitsubishi", "Nissan", "Peugeot", "Porsche", "Renault", "Seat", "Skoda", "SsangYong", "Subaru", "Suzuki", "Toyota", "Volkswagen", "Otro…" }));

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

        javax.swing.GroupLayout jPVehLayout = new javax.swing.GroupLayout(jPVeh);
        jPVeh.setLayout(jPVehLayout);
        jPVehLayout.setHorizontalGroup(
            jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVehLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPVehLayout.createSequentialGroup()
                        .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLPlaca)
                            .addComponent(jLNum)
                            .addComponent(jLModelo))
                        .addGap(31, 31, 31)
                        .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPVehLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addComponent(jTFNumVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPVehLayout.createSequentialGroup()
                                .addComponent(jTFPlaLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFPlaNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTFModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPVehLayout.createSequentialGroup()
                        .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLTipVeh)
                            .addComponent(jLCCap)
                            .addComponent(jLEmp)
                            .addComponent(jLMarca))
                        .addGap(18, 18, 18)
                        .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jCBMarca, javax.swing.GroupLayout.Alignment.LEADING, 0, 222, Short.MAX_VALUE)
                                .addComponent(jTFEmpresa, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPVehLayout.createSequentialGroup()
                                .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSCap, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBTipVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPVehLayout.createSequentialGroup()
                                        .addGap(104, 104, 104)
                                        .addComponent(jBFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPVehLayout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addComponent(jLFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPVehLayout.setVerticalGroup(
            jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVehLayout.createSequentialGroup()
                .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPVehLayout.createSequentialGroup()
                        .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPVehLayout.createSequentialGroup()
                                .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTFPlaLetras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFPlaNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLPlaca, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFNumVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLNum))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLModelo)
                            .addComponent(jTFModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTipVeh)
                            .addComponent(jCBTipVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPVehLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCCap)
                    .addComponent(jSCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBFoto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLEmp)
                    .addComponent(jTFEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPVehLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMarca)
                    .addComponent(jCBMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPIngCondAsis.setBackground(new java.awt.Color(255, 255, 255));
        jPIngCondAsis.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso Conductor y Asistente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N
        jPIngCondAsis.setToolTipText("");

        jLCond.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCond.setText("Conductor:");

        jCBCond.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBCond.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Conductor..." }));
        jCBCond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBCondActionPerformed(evt);
            }
        });

        jTFIDCond.setEditable(false);
        jTFIDCond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFIDCondActionPerformed(evt);
            }
        });

        jTFNomCond.setEditable(false);
        jTFNomCond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNomCondActionPerformed(evt);
            }
        });

        jLAsis.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLAsis.setText("Asistente:");

        jTFIDAsis.setEditable(false);
        jTFIDAsis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFIDAsisActionPerformed(evt);
            }
        });

        jTFNomAsis.setEditable(false);
        jTFNomAsis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNomAsisActionPerformed(evt);
            }
        });

        jCBAsis.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBAsis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Asistente..." }));
        jCBAsis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBAsisActionPerformed(evt);
            }
        });

        jBBuscarCond.setBackground(new java.awt.Color(255, 255, 255));
        jBBuscarCond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscardet.png"))); // NOI18N
        jBBuscarCond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarCondActionPerformed(evt);
            }
        });

        jBBuscarAsis.setBackground(new java.awt.Color(255, 255, 255));
        jBBuscarAsis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscardet.png"))); // NOI18N
        jBBuscarAsis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarAsisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPIngCondAsisLayout = new javax.swing.GroupLayout(jPIngCondAsis);
        jPIngCondAsis.setLayout(jPIngCondAsisLayout);
        jPIngCondAsisLayout.setHorizontalGroup(
            jPIngCondAsisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPIngCondAsisLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPIngCondAsisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPIngCondAsisLayout.createSequentialGroup()
                        .addComponent(jCBCond, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jBBuscarCond, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPIngCondAsisLayout.createSequentialGroup()
                        .addComponent(jCBAsis, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jBBuscarAsis, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPIngCondAsisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPIngCondAsisLayout.createSequentialGroup()
                            .addComponent(jLAsis)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFIDAsis, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jTFNomAsis, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPIngCondAsisLayout.createSequentialGroup()
                            .addComponent(jLCond)
                            .addGap(18, 18, 18)
                            .addComponent(jTFIDCond, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jTFNomCond, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPIngCondAsisLayout.setVerticalGroup(
            jPIngCondAsisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPIngCondAsisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPIngCondAsisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCond)
                    .addComponent(jTFIDCond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNomCond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPIngCondAsisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBCond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBuscarCond))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPIngCondAsisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLAsis)
                    .addComponent(jTFIDAsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNomAsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPIngCondAsisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBAsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBuscarAsis))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPIngSoatTecno.setBackground(new java.awt.Color(255, 255, 255));
        jPIngSoatTecno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso SOAT y Tecnomecanica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jBSoat.setBackground(new java.awt.Color(255, 255, 255));
        jBSoat.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        jBSoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/soat.png"))); // NOI18N
        jBSoat.setText("SOAT");
        jBSoat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBSoat.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBSoat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBSoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSoatActionPerformed(evt);
            }
        });

        jBTecno.setBackground(new java.awt.Color(255, 255, 255));
        jBTecno.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        jBTecno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tecnomecanica.png"))); // NOI18N
        jBTecno.setText("TECNO");
        jBTecno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBTecno.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jBTecno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBTecno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTecnoActionPerformed(evt);
            }
        });

        jLNumSoat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLNumSoat.setText("No SOAT:");

        jLNumTecno.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLNumTecno.setText("No TECNO:");

        jTFNumSoat.setEditable(false);
        jTFNumSoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNumSoatActionPerformed(evt);
            }
        });

        jTFNumTecno.setEditable(false);
        jTFNumTecno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNumTecnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPIngSoatTecnoLayout = new javax.swing.GroupLayout(jPIngSoatTecno);
        jPIngSoatTecno.setLayout(jPIngSoatTecnoLayout);
        jPIngSoatTecnoLayout.setHorizontalGroup(
            jPIngSoatTecnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPIngSoatTecnoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBSoat, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBTecno, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPIngSoatTecnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNumTecno)
                    .addComponent(jLNumSoat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPIngSoatTecnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFNumSoat, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(jTFNumTecno))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPIngSoatTecnoLayout.setVerticalGroup(
            jPIngSoatTecnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPIngSoatTecnoLayout.createSequentialGroup()
                .addGroup(jPIngSoatTecnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPIngSoatTecnoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPIngSoatTecnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBTecno, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBSoat, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPIngSoatTecnoLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPIngSoatTecnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLNumSoat)
                            .addComponent(jTFNumSoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPIngSoatTecnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLNumTecno)
                            .addComponent(jTFNumTecno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTVeh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTVeh);

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

        jLFunc.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLFunc.setText("Funcionario");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPIngCondAsis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPVeh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPIngSoatTecno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(291, 291, 291)
                                .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLFunc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPIngCondAsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPIngSoatTecno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jBNuevo)
                                            .addComponent(jBGuardar)
                                            .addComponent(jBEditar)
                                            .addComponent(jBBuscar)
                                            .addComponent(jBEliminar))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLFunc))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFNumVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNumVehActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNumVehActionPerformed

    private void jTFModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFModeloActionPerformed

    private void jTFEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFEmpresaActionPerformed

    private void jBFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFotoActionPerformed
        jLFoto.setIcon(subirFoto());
    }//GEN-LAST:event_jBFotoActionPerformed

    private void jTFIDCondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFIDCondActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIDCondActionPerformed

    private void jTFNomCondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNomCondActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNomCondActionPerformed

    private void jTFIDAsisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFIDAsisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIDAsisActionPerformed

    private void jTFNomAsisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNomAsisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNomAsisActionPerformed

    private void jBBuscarCondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarCondActionPerformed
    String idBuscar = JOptionPane.showInputDialog("Digite Identificación de Conductor");
    if(idBuscar!=null)
        {
            String nombre=null;
        try {
            nombre=DBConductor.buscarConductorVeh(idBuscar);
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(nombre.equals(null)|| nombre.isEmpty()){
            JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);
        }else{
            jTFIDCond.setText(idBuscar);
            jTFNomCond.setText(nombre);
            jCBCond.setSelectedIndex(0);
        }
        
        
        }
    }//GEN-LAST:event_jBBuscarCondActionPerformed

    private void jBBuscarAsisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarAsisActionPerformed
        String idBuscar = JOptionPane.showInputDialog("Digite Identificación de Asistente");
    if(idBuscar!=null)
        {
            String nombre=null;
        try {
            nombre=DBAsistente.buscarAsistenteVeh(idBuscar);
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(nombre.equals(null)|| nombre.isEmpty()){
            JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);
        }else{
            jTFIDAsis.setText(idBuscar);
            jTFNomAsis.setText(nombre);
            jCBAsis.setSelectedIndex(0);
        }
        
        
        }
    }//GEN-LAST:event_jBBuscarAsisActionPerformed

    private void jBSoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSoatActionPerformed
       SOAT miSOAT = new SOAT(idFunc, nombreFunc,enviarDatos(),true);
       miSOAT.setVisible(true);
       dispose();
    }//GEN-LAST:event_jBSoatActionPerformed

    private void jBTecnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTecnoActionPerformed
       Tecnomecanica miTecno = new Tecnomecanica(idFunc, nombreFunc,enviarDatos(),true);
       miTecno.setVisible(true);
       dispose();
    }//GEN-LAST:event_jBTecnoActionPerformed

    private void jTFNumSoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNumSoatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNumSoatActionPerformed

    private void jTFNumTecnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNumTecnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNumTecnoActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        fila=jTVeh.getSelectedRow();
        if(fila!=-1){
            String idEl=jTVeh.getValueAt(fila, 0).toString();
            boolean validarEliminar = DBVehiculo.modificarEstadoVehiculo(idEl);
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
        jTFPlaLetras.requestFocus();
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
        fila=jTVeh.getSelectedRow();
        if(fila!=-1){
            mostrarDatos();
            activar();
            jTFPlaLetras.setEnabled(false);
            jTFPlaNumeros.setEnabled(false);
        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono Registro\nSeleccione en la tabla la fila que desea editar", "Error", 0);
        }
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        String idBuscar = JOptionPane.showInputDialog("Digite Placa del Vehiculo: \n(Ejemplo: PFK432)");
        ArrayList<String> datosBusqueda = new ArrayList<>();

        if(idBuscar!=null)
        {
            datosBusqueda = DBVehiculo.buscarVehiculo(idBuscar);

            if(datosBusqueda==null || datosBusqueda.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);
            }else{
                String est=datosBusqueda.get(12).equals("1")?"Activo":"Inactivo";
                String conductor="",asistente="";
                
                try {
                    conductor=DBConductor.buscarConductorVeh(datosBusqueda.get(8));
                    asistente=DBAsistente.buscarAsistenteVeh(datosBusqueda.get(9));
                } catch (SQLException ex) {
                    Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JOptionPane.showMessageDialog(null, "Vehiculo:\nPlaca: "+idBuscar+"\nNo Vehiculo: "+datosBusqueda.get(1)+"\nModelo: "+datosBusqueda.get(2)
                    +"\nTipo Vehiculo: "+datosBusqueda.get(3)+"\nCapacidad: "+datosBusqueda.get(4)+"\nEmpresa: "+datosBusqueda.get(5)+"\nMarca: "+datosBusqueda.get(6)+"\nRuta Foto: "+datosBusqueda.get(7)
                    +"\nConductor: "+conductor+"\nAsistente: "+asistente+"\nSOAT: "+datosBusqueda.get(10)+"\nTecnomecanica: "+datosBusqueda.get(11)+"\nEstado: "+est);
            }
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jTFPlaNumerosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFPlaNumerosKeyTyped
        char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }
    }//GEN-LAST:event_jTFPlaNumerosKeyTyped

    private void jTFNumVehKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNumVehKeyTyped
        char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }
    }//GEN-LAST:event_jTFNumVehKeyTyped

    private void jTFModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFModeloKeyTyped
        char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }
    }//GEN-LAST:event_jTFModeloKeyTyped

    private void jCBCondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBCondActionPerformed
        if(jCBCond.getSelectedIndex()!=0){
        jTFNomCond.setText(jCBCond.getSelectedItem().toString());
        try {
            jTFIDCond.setText(DBConductor.buscarIdConductor(jTFNomCond.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jCBCondActionPerformed

    private void jCBAsisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBAsisActionPerformed
        if(jCBCond.getSelectedIndex()!=0){
        jTFNomAsis.setText(jCBAsis.getSelectedItem().toString());
        try {
            jTFIDAsis.setText(DBAsistente.buscarIdAsistente(jTFNomAsis.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jCBAsisActionPerformed

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
//            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Vehiculo().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBBuscarAsis;
    private javax.swing.JButton jBBuscarCond;
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBFoto;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jBSoat;
    private javax.swing.JButton jBTecno;
    private javax.swing.JComboBox<String> jCBAsis;
    private javax.swing.JComboBox<String> jCBCond;
    private javax.swing.JComboBox<String> jCBMarca;
    private javax.swing.JComboBox<String> jCBTipVeh;
    private javax.swing.JLabel jLAsis;
    private javax.swing.JLabel jLCCap;
    private javax.swing.JLabel jLCond;
    private javax.swing.JLabel jLEmp;
    private javax.swing.JLabel jLFoto;
    private javax.swing.JLabel jLFunc;
    private javax.swing.JLabel jLMarca;
    private javax.swing.JLabel jLModelo;
    private javax.swing.JLabel jLNum;
    private javax.swing.JLabel jLNumSoat;
    private javax.swing.JLabel jLNumTecno;
    private javax.swing.JLabel jLPlaca;
    private javax.swing.JLabel jLTipVeh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPIngCondAsis;
    private javax.swing.JPanel jPIngSoatTecno;
    private javax.swing.JPanel jPVeh;
    private javax.swing.JSpinner jSCap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFEmpresa;
    private javax.swing.JTextField jTFIDAsis;
    private javax.swing.JTextField jTFIDCond;
    private javax.swing.JTextField jTFModelo;
    private javax.swing.JTextField jTFNomAsis;
    private javax.swing.JTextField jTFNomCond;
    private javax.swing.JTextField jTFNumSoat;
    private javax.swing.JTextField jTFNumTecno;
    private javax.swing.JTextField jTFNumVeh;
    private javax.swing.JTextField jTFPlaLetras;
    private javax.swing.JTextField jTFPlaNumeros;
    private javax.swing.JTable jTVeh;
    // End of variables declaration//GEN-END:variables
}
