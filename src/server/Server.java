package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server implements Runnable {
    private final int puerto = 2027;
    private final int noConexiones = 20;
    //private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    
    public void escuchar() throws IOException{
        ServerSocket servidor = new ServerSocket(puerto, noConexiones);
        System.out.println("Servidor iniciado, esperando clientes....\n");
        while(true){
            //Socket cliente = servidor.accept();
        	//usuarios.add(cliente);
            
            Thread thread = new Thread(
            	new Hilo(servidor.accept())
            );
            thread.start();
            
            System.out.println("Nuevo cliente conectado...\n");
        }
    }

	public void run() {
		try {
			escuchar();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
