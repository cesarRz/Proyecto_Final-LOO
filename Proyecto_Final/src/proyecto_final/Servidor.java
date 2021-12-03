/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket; //atiende solicitudes
import java.net.Socket; //cliente porque tengo responder
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesarromanzuniga
 */


public class Servidor {
    Hilo hilo = new Hilo();
    Thread t = new Thread(hilo);
    int puerto=5432;
    class Hilo implements Runnable{
        public void run(){ // es el método que hace que se ejecute el hilo
            try{
                //agregaré el socket que estará corriendo para escuchar 
                //a los clientes o peticiones
                ServerSocket ss = new ServerSocket(puerto);
                Object request;
                while (true){
                    Socket s =ss.accept();// esta dormido hasta que alguien 
                                          // le manda un mensaje
//                    Recibe la informacion del cliente
                    InputStream is = s.getInputStream();
                    ObjectInputStream dis= new ObjectInputStream(is);
                    request = dis.readObject();
                    System.out.println("El servidor recibio la informacion");
                    
//                    Prepara el output stream para enviar la respuesta
                    OutputStream os = s.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(os);
                    
                    
//                    Parsea la informacion a un array
                    Object[] data = (Object[]) request;
                    String nombre = (String) data[0];
                    String apellido = (String) data[1];
                    int event_id = (int) data[2];
                    int asiento = (int) data[3];
                    
//                    Revisa que el asineto siga disponible
                    Boolean disponible = revisarReservacion(event_id, asiento);
                    System.out.println("El asiento esta disponible: " + disponible);
                    if(disponible == true){
                        System.out.println("El asiento " + asiento + " sera reservado" );
//                        Si el asineto es disponible crea la reservacion y recarga la sala de eventos
                        Boolean reservacion = nuevaReservacion(nombre, apellido, event_id, asiento);
                        
                        if(reservacion == true){
                            
                            dos.writeUTF("La reservacion fue realizada con exito");
                        }else{
                            dos.writeUTF("Hubo un error con la reservacion, intentelo mas tarde");
                        }
                        
                        
                    }else{
                        dos.writeUTF("El asiento " + asiento + " ya esta ocupado");
                        
                    }
                    s.close();
                }
                
            }catch(Exception e){
                System.out.println("Error de Servidor: " + e.toString());
            }
        }
    }

    public Servidor() {
        System.out.println("El servidor esta escuchando");
        t.start();
        
    }
    
    private Boolean revisarReservacion(int event_id, int asiento){
        Base base = new Base();
        ResultSet busy_seats = base.busyseats(event_id);
        
        try {
            while(busy_seats.next()){
                int busy = Integer.parseInt(busy_seats.getString("no_asiento"));
                if(busy == asiento){
                    return  false;
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        base.closeBase();
        return true;
    }
    
    private Boolean nuevaReservacion(String nombre, String apellido, int event_id, int asiento){
        Base base = new Base();
        String respuesta = base.addReservacion(nombre, apellido, event_id, asiento);
        base.closeBase();
        if(respuesta == "Reservacion Creada con Exito"){
            return true;
        }else{
            
            return false;
        }
    }
}
