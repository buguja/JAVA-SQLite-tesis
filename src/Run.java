
import java.io.IOException;

import client.Cliente;
import controllers.MainController;
import models.BaseDatosExceptionModel;
import models.Tool;
import server.Server;

public class Run {

	public static void main(String[] args){
		try {
			// Ejecutar el servidor
			if(Tool.leerArchivoConfiguracion("tipoApp.data").compareToIgnoreCase("servidor") == 0){
				Thread serverThread= new Thread(
					new Server()
				);
				serverThread.start();
			}
			
			Thread clientThread = new Thread(
				new MainController()
			);
			clientThread.start();
		} catch (IOException | BaseDatosExceptionModel e) {
			e.printStackTrace();
		}
	}
}
