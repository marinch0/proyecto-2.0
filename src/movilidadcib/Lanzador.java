/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movilidadcib;

import Interfaz.Aseguradora;
import Interfaz.Asistente;
import Interfaz.Conductor;
import Interfaz.Estudiante;
import Interfaz.Funcionario;
import Interfaz.InicioSesion;
import Interfaz.MenuPrincipal;
import Interfaz.SOAT;
import Interfaz.Tecnomecanica;
import Interfaz.Vehiculo;
import java.sql.SQLException;

/**
 *
 * @author SFS
 */
public class Lanzador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        
        InicioSesion miVentana = new InicioSesion();
        miVentana.setVisible(true);
    }
    
}
