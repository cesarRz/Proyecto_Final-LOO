/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cesarromanzuniga
 */
public class Cliente {

    String ip = "192.168.1.70";
//            "10.104.94.130";
    int puerto=5432;
    
    
    private Object[] registro =  new Object[4];

    public Cliente() {
    }
    
    public Cliente(String nombre, String apellido, int event_id, int asiento) {
        this.registro[0] = nombre ;
        this.registro[1] = apellido ;
        this.registro[2] = event_id ;
        this.registro[3] = asiento ;  
    }

    public String sendData(){
        try{
            Socket s = new Socket(ip,puerto);
            OutputStream os = s.getOutputStream();
            ObjectOutputStream dos = new ObjectOutputStream(os);
            dos.writeObject(registro);
            
            //entro en una espera de respuesta del servidor
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            String mensaje = dis.readUTF();
            System.out.println("El cliente recibio respuesta del servidor");
            return mensaje;
            
        }catch(Exception e){
            return e.toString();
        }

    }
    
}
