/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import controlador.EstudianteDAO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.EstudianteVO;


/**
 *
 * @author SFS
 */
public class Estudiante extends javax.swing.JFrame {

    String codEst,idEst,nombreEst,apellidoEst,celularEst,correoEst,direccionEst,gradoEst,ciudadEst,nombreAcu,celularAcu,correoAcu,direccionAcu,nombreFunc,idFunc;
    int fila,eleccion;
    boolean estadoEst;
    EstudianteVO transDatosEst;
    EstudianteDAO DBEstudiante;
    DefaultTableModel modelo;
    String datos[];
    ArrayList<String> listaEst;
    boolean validacionGuardar=false;
    
    public Estudiante(String idFunc,String nomFunc) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        jTEst.setBackground(Color.WHITE);
        
        DBEstudiante = new EstudianteDAO();
        this.nombreFunc=nomFunc;
        this.idFunc=idFunc;
        jLFunc.setText("Funcionario: "+this.nombreFunc);
        bloquear();
        datos = new String[13];
        listaEst = new ArrayList<>();
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
        jTFCodigo.setEnabled(false);
        jTFID.setEnabled(false);
        jTFNombre.setEnabled(false);
        jTFApellido.setEnabled(false);
        jTFCelular.setEnabled(false);
        jTFDireccion.setEnabled(false);
        jTFCorreo.setEnabled(false);
        jCBGrado.setEnabled(false);
        jCBCiudad.setEnabled(false);
        jTFNomAcu.setEnabled(false);
        jTFCelAcu.setEnabled(false);
        jTFCorreAcu.setEnabled(false);
        jTFDireAcu.setEnabled(false);
        jBGuardar.setEnabled(false);
        jBEditar.setEnabled(true);
        jBEliminar.setEnabled(true);
        jBBuscar.setEnabled(true);
    }
    
    public void activar(){
        jTFCodigo.setEnabled(true);
        jTFID.setEnabled(true);
        jTFNombre.setEnabled(true);
        jTFApellido.setEnabled(true);
        jTFCelular.setEnabled(true);
        jTFDireccion.setEnabled(true);
        jTFCorreo.setEnabled(true);
        jCBGrado.setEnabled(true);
        jCBCiudad.setEnabled(true);
        jTFNomAcu.setEnabled(true);
        jTFCelAcu.setEnabled(true);
        jTFCorreAcu.setEnabled(true);
        jTFDireAcu.setEnabled(true);
        jBGuardar.setEnabled(true);
        jBNuevo.setEnabled(false);
        jBEditar.setEnabled(false);
        jBEliminar.setEnabled(false);
        jBBuscar.setEnabled(false);
        
    }
    
     public void llamarDatosTabla(){
        
        modelo = new DefaultTableModel();
        modelo.addColumn("COD");
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Celular");
        modelo.addColumn("Correo");
        modelo.addColumn("Dirección");
        modelo.addColumn("Grado");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Acudiente");
        modelo.addColumn("Cel Acu");
        modelo.addColumn("Correo Acu");
        modelo.addColumn("Dirección Acu");
        this.jTEst.setModel(modelo);
        this.jTEst.getColumn(jTEst.getColumnName(0)).setMaxWidth(50);
        this.jTEst.getColumn(jTEst.getColumnName(1)).setMaxWidth(90);
        this.jTEst.getColumn(jTEst.getColumnName(4)).setMaxWidth(90);
        this.jTEst.getColumn(jTEst.getColumnName(7)).setMaxWidth(50);
        this.jTEst.getColumn(jTEst.getColumnName(6)).setWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(6)).setMinWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(6)).setMaxWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(8)).setWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(8)).setMinWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(8)).setMaxWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(10)).setMaxWidth(90);
        this.jTEst.getColumn(jTEst.getColumnName(11)).setWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(11)).setMinWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(11)).setMaxWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(12)).setWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(12)).setMinWidth(0);
        this.jTEst.getColumn(jTEst.getColumnName(12)).setMaxWidth(0);
              
        
        listaEst = DBEstudiante.listaEstudiante();
        
        for (int i = 0; i < listaEst.size(); i=i+13) {
            
            datos[0]=listaEst.get(i);
            datos[1]=listaEst.get(i+1);
            datos[2]=listaEst.get(i+2);
            datos[3]=listaEst.get(i+3);
            datos[4]=listaEst.get(i+4);
            datos[5]=listaEst.get(i+5);
            datos[6]=listaEst.get(i+6);
            datos[7]=listaEst.get(i+7);//
            datos[8]=listaEst.get(i+8);//
            datos[9]=listaEst.get(i+9);
            datos[10]=listaEst.get(i+10);
            datos[11]=listaEst.get(i+11);//
            datos[12]=listaEst.get(i+12);//
            
            modelo.addRow(datos);
            
        }
    }
     
    
     
      public boolean guardarDatos(){
        
        boolean validar= validarDatos();
        
         if(validar!=false)
        {
            codEst = jTFCodigo.getText();
            idEst = jTFID.getText();
            nombreEst = jTFNombre.getText();
            apellidoEst = jTFApellido.getText();
            celularEst = jTFCelular.getText();
            direccionEst = jTFDireccion.getText();
            correoEst = jTFCorreo.getText();
            gradoEst = (String)jCBGrado.getSelectedItem();
            ciudadEst = (String)jCBCiudad.getSelectedItem();
            nombreAcu = jTFNomAcu.getText();
            celularAcu = jTFCelAcu.getText();
            correoAcu = jTFCorreAcu.getText();
            direccionAcu = jTFDireAcu.getText();
            estadoEst = true;
            
            
            transDatosEst = new EstudianteVO(codEst,idEst, nombreEst,apellidoEst,celularEst,correoEst,direccionEst, gradoEst,ciudadEst,nombreAcu,celularAcu,correoAcu,direccionAcu,idFunc,estadoEst);
            DBEstudiante.ingresarEstudiante(transDatosEst);
            llamarDatosTabla();
            return true;        
        }
        return false;
     }
      
      public boolean editarDatos(){
         boolean validar= validarDatos();
        
        if(validar!=false)
        {
            codEst = jTFCodigo.getText();
            idEst = jTFID.getText();
            nombreEst = jTFNombre.getText();
            apellidoEst = jTFApellido.getText();
            celularEst = jTFCelular.getText();
            direccionEst = jTFDireccion.getText();
            correoEst = jTFCorreo.getText();
            gradoEst = (String)jCBGrado.getSelectedItem();
            ciudadEst = (String)jCBCiudad.getSelectedItem();
            nombreAcu = jTFNomAcu.getText();
            celularAcu = jTFCelAcu.getText();
            correoAcu = jTFCorreAcu.getText();
            direccionAcu = jTFDireAcu.getText();
            transDatosEst = new EstudianteVO(codEst,idEst, nombreEst,apellidoEst,celularEst,correoEst,direccionEst, gradoEst,ciudadEst,nombreAcu,celularAcu,correoAcu,direccionAcu,idFunc,estadoEst);
            DBEstudiante.modificarEstudiante(transDatosEst);
            llamarDatosTabla();
            return true;        
        }
        return false;
    }
      
      public void mostrarDatos(){
            jTFCodigo.setText(jTEst.getValueAt(fila, 0).toString());
            jTFID.setText(jTEst.getValueAt(fila, 1).toString());
            jTFNombre.setText(jTEst.getValueAt(fila, 2).toString());
            jTFApellido.setText(jTEst.getValueAt(fila, 3).toString());
            jTFCelular.setText(jTEst.getValueAt(fila, 4).toString());
            jTFCorreo.setText(jTEst.getValueAt(fila, 5).toString());
            jTFDireccion.setText(jTEst.getValueAt(fila, 6).toString());
            jCBGrado.setSelectedItem(jTEst.getValueAt(fila, 7).toString());
            jCBCiudad.setSelectedItem(jTEst.getValueAt(fila, 8).toString());
            jTFNomAcu.setText(jTEst.getValueAt(fila, 9).toString());
            jTFCelAcu.setText(jTEst.getValueAt(fila, 10).toString());
            jTFCorreAcu.setText(jTEst.getValueAt(fila, 11).toString());
            jTFDireAcu.setText(jTEst.getValueAt(fila, 12).toString());
       
            
            
            
      }
      
      
      
       public boolean validarDatos(){
         if(jTFCodigo.getText().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Ingresar Código", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFCodigo.requestFocus();
             return false;
         } else if(jTFID.getText().isEmpty()){
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
         }else if(jCBGrado.getSelectedIndex()==0){
             JOptionPane.showMessageDialog(null, "Seleccionar Grado", "Error de selección",JOptionPane.ERROR_MESSAGE);
             jCBGrado.requestFocus();
             return false;
         }else if(jCBCiudad.getSelectedIndex()==0){
             JOptionPane.showMessageDialog(null, "Seleccionar Ciudad", "Error de selección",JOptionPane.ERROR_MESSAGE);
             jCBCiudad.requestFocus();
             return false;
         }else if(jTFNomAcu.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Acudiente", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFNomAcu.requestFocus();
             return false;
         }else if(jTFCelAcu.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Celular Acudiente", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFCelAcu.requestFocus();
             return false;
         }else if(jTFCorreAcu.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Correo Acudiente", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFCorreAcu.requestFocus();
             return false;
         }else if(jTFDireAcu.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Ingresar Dirección Acudiente", "Error caja Vacia",JOptionPane.ERROR_MESSAGE);
             jTFDireAcu.requestFocus();
             return false;
         }else{
             return true;
         }
         
     }
       
       public void limpiarCajas(){
         jTFCodigo.setText(null);
         jTFID.setText(null);
         jTFNombre.setText(null);
         jTFApellido.setText(null);
         jTFCelular.setText(null);
         jTFDireccion.setText(null);
         jTFCorreo.setText(null);
         jTFNomAcu.setText(null);
         jTFCelAcu.setText(null);
         jTFCorreAcu.setText(null);
         jTFDireAcu.setText(null);
         jCBCiudad.setSelectedIndex(0);
         jCBGrado.setSelectedIndex(0);
                  
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPEst = new javax.swing.JPanel();
        jLID = new javax.swing.JLabel();
        jLCod = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLApellido = new javax.swing.JLabel();
        jLCelular = new javax.swing.JLabel();
        jLCorreo = new javax.swing.JLabel();
        jLDireccion = new javax.swing.JLabel();
        jLGrado = new javax.swing.JLabel();
        jLCiudad = new javax.swing.JLabel();
        jTFCodigo = new javax.swing.JTextField();
        jTFID = new javax.swing.JTextField();
        jTFNombre = new javax.swing.JTextField();
        jTFApellido = new javax.swing.JTextField();
        jTFCelular = new javax.swing.JTextField();
        jTFCorreo = new javax.swing.JTextField();
        jTFDireccion = new javax.swing.JTextField();
        jCBGrado = new javax.swing.JComboBox<>();
        jCBCiudad = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLNomAcu = new javax.swing.JLabel();
        jLCelAcu = new javax.swing.JLabel();
        jLCorrAcu = new javax.swing.JLabel();
        jLDireAcu = new javax.swing.JLabel();
        jTFNomAcu = new javax.swing.JTextField();
        jTFCelAcu = new javax.swing.JTextField();
        jTFCorreAcu = new javax.swing.JTextField();
        jTFDireAcu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTEst = new javax.swing.JTable();
        jBBuscar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBNuevo = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jLFunc = new javax.swing.JLabel();
        jBCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingreso Estudiante");

        jPEst.setBackground(new java.awt.Color(255, 255, 255));
        jPEst.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Estudiante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLID.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLID.setText("Identificación:");

        jLCod.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCod.setText("Código:");

        jLNombre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLNombre.setText("Nombre:");

        jLApellido.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLApellido.setText("Apellido:");

        jLCelular.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCelular.setText("Celular:");

        jLCorreo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCorreo.setText("E-mail:");

        jLDireccion.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLDireccion.setText("Dirección:");

        jLGrado.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLGrado.setText("Grado:");

        jLCiudad.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCiudad.setText("Ciudad:");

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

        jCBGrado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBGrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "PJ A", "PJ B", "J A", "J B", "T A", "T B", "1 A", "1 B", "1 C", "2 A", "2 B", "2 C", "3 A", "3 B", "3 C", "4 A", "4 B", "4 C", "5 A", "5 B", "5 C", "6 A", "6 B", "6 C", "7 A", "7 B", "7 C", "8 A", "8 B", "8 C", "9 A", "9 B", "9 C", "10 A", "10 B", "10 C", "11 A", "11 B", "11 C" }));

        jCBCiudad.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Armenia", "Barranquilla", "Bogotá", "Bucaramanga", "Buenaventura", "Caicedonia", "Calarcá", "Cali", "Cartagena", "Circasia", "Génova", "Ibagué", "Manizales", "Medellín", "Pereira", "Puerto Colombia", "Santa Marta", "Tebaida", "Tuluá" }));

        javax.swing.GroupLayout jPEstLayout = new javax.swing.GroupLayout(jPEst);
        jPEst.setLayout(jPEstLayout);
        jPEstLayout.setHorizontalGroup(
            jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEstLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCod)
                    .addComponent(jLID)
                    .addComponent(jLNombre)
                    .addComponent(jLApellido)
                    .addComponent(jLCelular)
                    .addComponent(jLCorreo)
                    .addComponent(jLDireccion)
                    .addComponent(jLGrado)
                    .addComponent(jLCiudad))
                .addGap(25, 25, 25)
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBGrado, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFDireccion)
                    .addComponent(jTFCorreo)
                    .addComponent(jTFCelular)
                    .addComponent(jTFApellido)
                    .addComponent(jCBCiudad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFID)
                    .addComponent(jTFNombre))
                .addGap(18, 18, 18))
        );
        jPEstLayout.setVerticalGroup(
            jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEstLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCod)
                    .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLID)
                    .addComponent(jTFID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombre)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLApellido)
                    .addComponent(jTFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCelular)
                    .addComponent(jTFCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCorreo)
                    .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLDireccion)
                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLGrado)
                    .addComponent(jCBGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCiudad)
                    .addComponent(jCBCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Acudiente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLNomAcu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLNomAcu.setText("Nombre Acudiente:");

        jLCelAcu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCelAcu.setText("Celular Acudiente:");

        jLCorrAcu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLCorrAcu.setText("E-mail Acudiente:");

        jLDireAcu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLDireAcu.setText("Dirección Acudiente:");

        jTFCelAcu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCelAcuKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLNomAcu)
                        .addGap(18, 18, 18)
                        .addComponent(jTFNomAcu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLCelAcu)
                        .addGap(24, 24, 24)
                        .addComponent(jTFCelAcu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLCorrAcu)
                        .addGap(30, 30, 30)
                        .addComponent(jTFCorreAcu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLDireAcu)
                        .addGap(7, 7, 7)
                        .addComponent(jTFDireAcu)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNomAcu)
                    .addComponent(jTFNomAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCelAcu)
                    .addComponent(jTFCelAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCorrAcu)
                    .addComponent(jTFCorreAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDireAcu)
                    .addComponent(jTFDireAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTEst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTEst);

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
                            .addComponent(jPEst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(103, 103, 103)
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
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        String idBuscar = JOptionPane.showInputDialog("Digite Codigo del Estudiante:");
        ArrayList<String> datosBusqueda = new ArrayList<>();

        if(idBuscar!=null)
        {
            datosBusqueda = DBEstudiante.buscarEstudiante(idBuscar);

            if(datosBusqueda==null || datosBusqueda.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "codigo no existe", "Incorrecto", 2);
            }else{
                String est=datosBusqueda.get(13).equals("1")?"Activo":"Inactivo";
                JOptionPane.showMessageDialog(null, "Estudiante:\nCódigo: "+idBuscar+"\nIdentificación: "+datosBusqueda.get(1)+"\nNombre: "+datosBusqueda.get(2)+"\nApellido: "+datosBusqueda.get(3)
                    +"\nCelular: "+datosBusqueda.get(4)+"\nCorreo: "+datosBusqueda.get(5)+"\nDirección: "+datosBusqueda.get(6)+"\nGrado: "+datosBusqueda.get(7)+"\nCiudad: "+datosBusqueda.get(8)+"\nAcudiente: "+datosBusqueda.get(9)
                        +"\nCel Acu: "+datosBusqueda.get(10)+"\nCorreo Acu: "+datosBusqueda.get(11)+"\nDirección Acu: "+datosBusqueda.get(12)+"\nEstado: "+est);
            }
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
        validacionGuardar=true;
        fila=jTEst.getSelectedRow();
        if(fila!=-1){
            mostrarDatos();
            activar();
            jTFID.setEnabled(false);

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

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        fila=jTEst.getSelectedRow();
        if(fila!=-1){
            boolean validarEliminar = DBEstudiante.modificarEstadoEstudiante(jTEst.getValueAt(fila, 0).toString());
            if(validarEliminar!=false)
            {
                llamarDatosTabla();
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

    private void jTFCelAcuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCelAcuKeyTyped
        char caracter = evt.getKeyChar();

        if ((caracter < '0' || caracter > '9')
                && (caracter != '\b'/*corresponde a Back_space*/)
                && (caracter != '.')) {
            evt.consume();//ignota el evento del teclado
        }
    }//GEN-LAST:event_jTFCelAcuKeyTyped

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
//            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Estudiante().setVisible(true);
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
    private javax.swing.JComboBox<String> jCBCiudad;
    private javax.swing.JComboBox<String> jCBGrado;
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLCelAcu;
    private javax.swing.JLabel jLCelular;
    private javax.swing.JLabel jLCiudad;
    private javax.swing.JLabel jLCod;
    private javax.swing.JLabel jLCorrAcu;
    private javax.swing.JLabel jLCorreo;
    private javax.swing.JLabel jLDireAcu;
    private javax.swing.JLabel jLDireccion;
    private javax.swing.JLabel jLFunc;
    private javax.swing.JLabel jLGrado;
    private javax.swing.JLabel jLID;
    private javax.swing.JLabel jLNomAcu;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JPanel jPEst;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTEst;
    private javax.swing.JTextField jTFApellido;
    private javax.swing.JTextField jTFCelAcu;
    private javax.swing.JTextField jTFCelular;
    private javax.swing.JTextField jTFCodigo;
    private javax.swing.JTextField jTFCorreAcu;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFDireAcu;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFID;
    private javax.swing.JTextField jTFNomAcu;
    private javax.swing.JTextField jTFNombre;
    // End of variables declaration//GEN-END:variables
}
