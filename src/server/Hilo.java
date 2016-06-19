package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import controllers.BaseDatosController;
import models.BaseDatosExceptionModel;
import models.Tool;

public class Hilo implements Runnable{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    
    public Hilo(Socket soc) throws IOException{
        socket = soc;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }
    
    private void interpretarEntrada(String recibido) throws IOException, BaseDatosExceptionModel, SQLException{
    	JSONObject json= Tool.stringToJSON(recibido);
    	String result= "";
    	
    	switch(json.get("metodo").toString()){
	    	case "iniciarSesion":
	    		result= iniciarSesion(json);
	    	break;
	    	case "consultarMateriasDelAlumno":
	    		result= iniciarSesion(json);
	    	break;
    	}
    	
		out.writeUTF(result);
    }
    
    private String iniciarSesion(JSONObject json) throws BaseDatosExceptionModel, SQLException{
    	BaseDatosController baseDatosController= new BaseDatosController();
    	String resultBd= baseDatosController.consultarUsuario(
				json.get("usuario").toString(),
				json.get("contraseña").toString()
		);
    	
    	json= new JSONObject();
    	json.put("metodo", "accederComo");
    	json.put("resultadoMetodo", resultBd);
    	
    	return json.toJSONString();
    }
    
    private String consultarMateriasDelAlumno(){
    	JSONObject jsonObject= new JSONObject();
    	JSONArray jsonArray= new JSONArray();
    	jsonArray.add("Biología");
    	jsonArray.add("Parcial 1");
    	jsonArray.add("10.00");
    	json.put("contenido", )

    	return null;
    }
    
    public void run() {
        try {
            //out.writeUTF("[Servidor-Hilo]: Hola, bienvenido cliente");
            //Ciclo infinito para escuchar por mensajes del cliente
            while(true){
               String recibido = in.readUTF();
               //Cuando se recibe un mensaje se envia a todos los usuarios conectados 
                /*for (int i = 0; i < usuarios.size(); i++) {
                    out = new DataOutputStream(usuarios.get(i).getOutputStream());
                   // out.writeUTF("{{Servidor-Hilo}}" + recibido);
                    System.out.println(recibido + "\n");
                }*/
               interpretarEntrada(recibido);
            }
        } catch (BaseDatosExceptionModel | SQLException e) {
        	System.out.println("Error en Hilo.java: " + e.getMessage());
        } catch(IOException e){
        	JOptionPane.showMessageDialog(null, "Cliente, cerró la conexión");
        }
    }
}