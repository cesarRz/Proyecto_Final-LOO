/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;

import java.sql.Connection; //Conexion
import java.sql.DriverManager; //Driver
import java.sql.ResultSet; //Para la tabla
import java.sql.SQLException; //Para los errores
import java.sql.Statement; //Para la base de datos
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author cesarromanzuniga
 */
public class Base {
    
    public Statement Base(){
	try{
		// Creacion de Clases
		Connection conexion;
		Statement base;
		// Cargar Driver
		Class.forName("org.sqlite.JDBC");
		// Cargar base de datos
		conexion = DriverManager.getConnection("jdbc:sqlite:terrenos.db");
		// Base de datos en variable
		base = conexion.createStatement();
                
                System.out.println("Conexion Exitosa");
		return base;
	}catch(Exception e){
		JOptionPane.showMessageDialog(null, "Error: "+ e.toString());
                return null;
	}
    }
    
    public void addValueExhibicion(){
            
     }
    
}
