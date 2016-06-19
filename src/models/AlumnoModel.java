package models;

import java.security.NoSuchAlgorithmException;

public class AlumnoModel {
	private String nombreCompleto= "Javier Burón Gutiérrez";
	private String identificacion= "09120002";
	private String grupo= "1012";
	private String pass= "1991";
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}
	
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) throws NoSuchAlgorithmException {
		this.pass = Tool.generarMD5(pass);
	}

	public String toString(){
		return this.nombreCompleto + " | " + this.identificacion + " | " + this.grupo;
	}
}
