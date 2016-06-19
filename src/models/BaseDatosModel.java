package models;
import java.sql.*;
import java.util.Vector;
import java.io.*;

public class BaseDatosModel {
	private Connection conexion = null;
	private String controlador = null;
	private String url = null;

  	public BaseDatosModel(String archivo) throws BaseDatosExceptionModel {
		leerArchivoConfiguracion(archivo);			
	}
	
	private void leerArchivoConfiguracion(String archivo) throws BaseDatosExceptionModel {
		String cadena;
		File arch = new File(archivo);
		try (BufferedReader lectura = new BufferedReader(new FileReader(arch));) {
			if ((cadena = lectura.readLine()) != null) {
				controlador = cadena;
			}
			if ((cadena = lectura.readLine()) != null) {
				url = cadena;
			}
		} catch(IOException e) {
			throw new BaseDatosExceptionModel(e.getMessage());
		}		
	}
	
	protected void realizarConexion() throws BaseDatosExceptionModel {
		try {
			Class.forName(controlador);
			conexion = DriverManager.getConnection(url);
		} catch (SQLException excepcionSQL) {
			throw new BaseDatosExceptionModel(excepcionSQL.getMessage());
		} catch (ClassNotFoundException claseNoEncontrada) {
			throw new BaseDatosExceptionModel("No se encontró el controlador");
		}
	}
		 	
	protected void cerrarConexion() throws BaseDatosExceptionModel {
		try {
			if (conexion != null) {
				conexion.close();
			}
		} catch (SQLException excepcionSQL) {
			throw new BaseDatosExceptionModel(excepcionSQL.getMessage());
		}
	}
	
	protected ResultSet realizarConsulta(String consulta) throws BaseDatosExceptionModel{
		try {
			Statement instruccion = conexion.createStatement();
			ResultSet resultados = instruccion.executeQuery(consulta);
			return resultados;
	 	} catch (SQLException excepcionSQL) {
			throw new BaseDatosExceptionModel(excepcionSQL.getMessage());
		}
  	}

	protected int realizarAccion(String accion) throws BaseDatosExceptionModel{
 		try {
 			Statement instruccion = conexion.createStatement();
			int dato = instruccion.executeUpdate(accion);
			instruccion.close();
			return dato;
		} catch (SQLException excepcionSQL) {
			throw new BaseDatosExceptionModel(excepcionSQL.getMessage());
		}
	}
	
	protected String[] consultarTablas() throws BaseDatosExceptionModel {
		try {
			String[] tipotabla = {"TABLE"};
			Vector<String> nombres = new Vector<String>(); 
			// de la base de datos se obtiene todo lo referente a sus tablas
			ResultSet nomTablas = conexion.getMetaData().getTables(null, null, null, tipotabla);
			while (nomTablas.next()) {
				nombres.add(nomTablas.getString(3));
			}
			String[] arregloNombres = new String[nombres.size()];
			return nombres.toArray(arregloNombres);
		} catch (SQLException e) {
			throw new BaseDatosExceptionModel(e.getMessage());
		}
		
	}
}
