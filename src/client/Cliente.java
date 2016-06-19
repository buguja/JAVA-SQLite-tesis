package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import controllers.MainController;
import server.Server;

public class Cliente {
    private Socket cliente;
    protected DataInputStream in;
    private DataOutputStream out;
    private int puerto = 2027;
    private String host = "192.168.43.100";
    protected String mensajes = "";
    
    public Cliente() throws UnknownHostException, IOException{
        cliente = new Socket(host,puerto);
        in = new DataInputStream(cliente.getInputStream());
        out = new DataOutputStream(cliente.getOutputStream());
    }
    
    //Funcion sirve para enviar mensajes al servidors
    public void enviarMsg(String msg) throws IOException{
    	out.writeUTF(msg);
    }
}