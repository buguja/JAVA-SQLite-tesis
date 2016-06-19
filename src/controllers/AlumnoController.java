package controllers;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JTextField;

import models.AlumnoModel;
import models.BaseDatosExceptionModel;
import models.ExceptionModel;
import views.AlumnoView;

public class AlumnoController extends AlumnoView {
	private AlumnoModel alumno= null;
	private boolean editar= false;
	
	public JTextField getTextFieldNombreCompleto(){
		return super.textFieldNombreCompleto;
	}
	
	public JTextField getTextFieldIdentificacion(){
		return super.textFieldIdentifiacion;
	}
	
	public JTextField getTextFieldGrupo(){
		return super.textFieldGrupo;
	}
	
	public JTextField getTextFieldPass(){
		return super.textFieldPass;
	}
	
	public JButton getBtnCancelar(){
		return btnCancelar;
	}
	
	public JButton getBtnLimpiar(){
		return super.btnLimpiar;
	}
	
	public JButton getBtnGuardar(){
		return super.btnGuardar;
	}
	
	public AlumnoController(ActionListener actionListener, WindowListener windowListener){
		this.agregarEventos(actionListener);
		super.addWindowListener(windowListener);
		this.alumno= new AlumnoModel();
	}
	
	public AlumnoController(ActionListener actionListener, WindowListener windowListener, AlumnoModel alumno, boolean esParaEditar) throws ExceptionModel{
		this(actionListener, windowListener);
		this.editar= esParaEditar;
		this.alumno= alumno;
		
		if(!this.editar){
			super.textFieldNombreCompleto.setEditable(false);
			super.textFieldGrupo.setEditable(false);
			super.btnLimpiar.setEnabled(false);
			super.btnGuardar.setEnabled(false);
			super.textFieldPass.setEditable(false);
		}
		super.textFieldIdentifiacion.setEditable(false);
		
		if(!this.editar && (this.alumno == null)){
			throw new ExceptionModel(ExceptionModel.VER_ALUMNO_NULL);
		}

		this.mostrarDatos();
	}
	
	private void mostrarDatos(){
		super.textFieldNombreCompleto.setText(this.alumno.getNombreCompleto());
		super.textFieldIdentifiacion.setText(this.alumno.getIdentificacion());
		super.textFieldGrupo.setText(this.alumno.getGrupo());
		super.textFieldPass.setText("* * * * *");
	}
	
	private void agregarEventos(ActionListener actionListener){
		super.btnCancelar.addActionListener(actionListener);
		super.btnLimpiar.addActionListener(actionListener);
		super.btnGuardar.addActionListener(actionListener);
	}
	
	public void funcionalidadLimpiar(){
		super.textFieldNombreCompleto.setText("");
		if(super.textFieldIdentifiacion.isEditable()){
			super.textFieldIdentifiacion.setText("");
		}
		super.textFieldPass.setText("");
		super.textFieldGrupo.setText("");
	}
	
	public boolean funcionalidadGuardar() throws NoSuchAlgorithmException, BaseDatosExceptionModel{
		BaseDatosController baseDatosController= new BaseDatosController();
		
		this.alumno.setNombreCompleto(textFieldNombreCompleto.getText());
		if(super.textFieldIdentifiacion.isEditable()){
			this.alumno.setIdentificacion(super.textFieldIdentifiacion.getText());
		}
		this.alumno.setPass(super.textFieldPass.getText());
		this.alumno.setGrupo(textFieldGrupo.getText());
		
		if(this.editar){
			baseDatosController.editarAlumno(alumno);
			return true;
		}
		baseDatosController.altaAlumno(alumno);
		this.funcionalidadLimpiar();
		return false; //Siempre que no sea editar, es para saber si cerrar o no la ventana.
	}
	
	public void cerrarVentana(){
		super.dispose();
	}

	public void funcionalidadCancelar() {
		this.funcionalidadLimpiar();
		super.dispose();	
	}
}