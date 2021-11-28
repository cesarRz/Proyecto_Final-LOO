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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author cesarromanzuniga
 */
public class Base {

	private Statement base;
    
    public Base(){
		try{
			// Creacion de Clases
			Connection conexion;
			// Cargar Driver
			Class.forName("org.sqlite.JDBC");
			// Cargar base de datos
			conexion = DriverManager.getConnection("jdbc:sqlite:base.db");
			// Base de datos en variable
			this.base = conexion.createStatement();		
			
		}catch(Exception e){
			
		}
    }
	// Edicion de eventos
	public String addEvento(String fecha, String hora, String titulo, int sala) {
		String sql = String.format("INSERT INTO eventos (fecha, hora, titulo, id_sala) values('%s','%s','%s','%2d')", fecha, hora, titulo, sala);
		try {
			base.executeUpdate(sql);
			return "Evento Creado con Exito";
		} catch (SQLException e) {
			return e.toString();
		}
	}
	public String editEvento(String column, int id, String newValue) {
		String sql;
		if(column == "id_sala"){
			int value = Integer.parseInt(newValue);
			sql = String.format("UPDATE eventos set %s = '%2d' where id= '%2d'", column, value, id);
		}else{
			String value = newValue;
			sql = String.format("UPDATE eventos set %s = '%s' where id= '%2d'", column, value, id);
		}
		try {
			base.executeUpdate(sql);
			return "Evento editado";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.toString();
		}

	}

	public String getEventoId(String column, String valor) {

		String sql = String.format("SELECT id FROM eventos WHERE %s = '%s'", column, valor);
		try {
			ResultSet resultado = base.executeQuery(sql);
			return resultado.getString("Id");

		} catch (SQLException e) {
			System.out.println();
			return e.toString();
		}
		
	}

	// Edicion de Reservaciones
	public String addReservacion(String nombre, String apellido, int id_evento, int no_asiento ) {


		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter fecha_format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter hora_format = DateTimeFormatter.ofPattern("k:mm");
		String fecha = now.format(fecha_format);
		String hora = now.format(hora_format);

		String sql = String.format("INSERT INTO reservaciones (fecha, hora, nombre,  apellido, id_evento, no_asiento) values('%s','%s','%s','%s','%2d','%2d')", 
		fecha, hora, nombre,  apellido, id_evento, no_asiento);
		try {
			base.executeUpdate(sql);
			return "Reservacion Creada con Exito";
		} catch (SQLException e) {
			return e.toString();
		}
	}
	public String editReservacion(String column, int id, String newValue) {
		String sql;
		if(column == "no_asiento" || column == "id_evento"){
			int value = Integer.parseInt(newValue);
			sql = String.format("UPDATE reservaciones set %s = '%2d' where id= '%2d'", column, value, id);
		}else{
			String value = newValue;
			sql = String.format("UPDATE reservaciones set %s = '%s' where id= '%2d'", column, value, id);
		}
		try {
			base.executeUpdate(sql);
			return "Reservacion editada con exito";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.toString();
		}
	}

	public String getReservacionId(String column, String valor) {
		String sql = String.format("SELECT id FROM reservaciones WHERE %s = '%s'", column, valor);
		try {
			ResultSet resultado = base.executeQuery(sql);
			return resultado.getString("Id");

		} catch (SQLException e) {
			System.out.println();
			return e.toString();
		}
		
	}
        
        public ResultSet getSalas(){
            String sql = "SELECT * FROM salas";
            try {
                return base.executeQuery(sql);
            } catch (SQLException ex) {
                return null;
            }
        }
        
        public ResultSet busyHours(int id_sala){
            
            String sql = String.format("SELECT hora FROM eventos WHERE  ", id_sala);
            ResultSet busyHours;
            try {
                busyHours = base.executeQuery(sql);
                return busyHours;
            } catch (SQLException ex) {
                return null;
            }
            
            
        }
        
        public String closeBase(){
            try {
                base.close();
                return "La conexion ha sido cerrada con Exito";
            } catch (SQLException ex) {
                return ex.toString();
            }
        }
}
