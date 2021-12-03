/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;
import java.io.*;
import java.net.*;

/**
 *
 * @author cesarromanzuniga
 */
public class Cliente {
//    public Cliente() {
//        String host = "<ip del servidor>";
//        int puerto = 5001;
//
//    try{
//        Socket skcliente = new Socket(host, puerto);
//        OutputStream os = skcliente.getOutputStream();
//        DataOutputStream dos = new DataOutputStream(os);
//        dos.writeUTF("Texto");
//	
//    }catch(Exception e){
//        System.out.println("Error: "+ e.toString());
//    }
//            
//        }

    public static void main(String[] args) {
        Base base = new Base();
        
        String Resp = base.editEvento("hora", 11, "12:00");

        System.out.println(Resp);
        
    }
    
    
    
}
