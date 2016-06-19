package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Tool {
	public static String generarId(String cadena){
		String resultado= cadena;
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssS");
		Date date = new Date();
		return resultado.concat(dateFormat.format(date));
	}
	
	public static String limpiarPregunta(String pregunta){
		return pregunta.replaceAll("#\\?.*?\\?#", "__________");
	}
	
	public static String generarMD5(String cadena) throws NoSuchAlgorithmException{
		MessageDigest md= MessageDigest.getInstance("MD5");
		byte[] messageDigest= md.digest(cadena.getBytes());
		BigInteger number= new BigInteger(1, messageDigest);
		String hashtext= number.toString(16);
		while(hashtext.length() < 32){
			hashtext= "0" + hashtext;
		}
		return hashtext;
	}
	
    public static JSONObject stringToJSON(String jsonString){
		JSONParser parser = new JSONParser();

		try{
			JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
			return jsonObject;
		}catch(org.json.simple.parser.ParseException e){
			e.getStackTrace();
		}
		
		return null;
	}
    
    public static String leerArchivoConfiguracion(String archivo) throws BaseDatosExceptionModel {
    	String cadena;
		File arch = new File(archivo);
		try (BufferedReader lectura = new BufferedReader(new FileReader(arch));) {
			if ((cadena = lectura.readLine()) != null) {
				return cadena;
			}
			
			return "cliente";
		} catch(IOException e) {
			throw new BaseDatosExceptionModel(e.getMessage());
		}		
	}
}
