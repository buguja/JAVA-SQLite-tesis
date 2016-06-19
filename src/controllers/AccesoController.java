package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;

import org.json.simple.JSONObject;

import models.AccesoModel;
import models.BaseDatosExceptionModel;
import models.Tool;
import views.AccesoView;

public class AccesoController extends AccesoView{
	//private AccesoModel accesoModel= null;
	public JButton getBtnCancelar(){
		return super.btnCancelar;
	}
	
	public JButton getBtnAcceder(){
		return super.btnAcceder;
	}
	
	public AccesoController(ActionListener actionListener){
		this.agregarEventos(actionListener);
	}
	
	private void agregarEventos(ActionListener actionListener){
		super.btnCancelar.addActionListener(actionListener);
		super.btnAcceder.addActionListener(actionListener);
	}
	
	public void funcionalidadCancelar(){
		super.usuario.setText("");
		super.contraseña.setText("");
		super.dispose();
	}
	
	public String funcionalidadAcceder() throws BaseDatosExceptionModel, NoSuchAlgorithmException, SQLException{
		/*BaseDatosController baseDatosController= new BaseDatosController();
		return baseDatosController.consultarUsuario(super.usuario.getText(), 
				Tool.generarMD5(new String(super.contraseña.getPassword())));*/
		JSONObject json= new JSONObject();
		json.put("metodo", "iniciarSesion");
		json.put("usuario", super.usuario.getText().trim());
		json.put("contraseña", Tool.generarMD5(new String(super.contraseña.getPassword())).trim());
		
		return json.toJSONString();
	}
	
	public void cerrarVentana(){
		super.dispose();
	}
}
