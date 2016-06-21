package controllers;

import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JMenuItem;

import org.json.simple.JSONObject;

import models.AlumnoModel;
import models.BaseDatosExceptionModel;
import models.ExceptionModel;
import views.TableroProfesorView;

public class TableroProfesorController extends TableroProfesorView {
	public JMenuItem getMntmSalir(){
		return super.mntmSalir;
	}
	
	public JMenuItem getMntmEditarAlumno(){
		return super.mntmEditarAlumno;
	}
	
	public JMenuItem getMntmEliminarAlumno(){
		return super.mntmEliminarAlumno;
	}
	
	public JMenuItem getMntmNuevoAlumno(){
		return super.mntmNuevoAlumno;
	}
	
	public JMenuItem getMntmVerAlumno(){
		return super.mntmVerAlumno;
	}
	
	public JMenuItem getMntmCrearPdfExamen(){
		return super.mntmCrearPdfExamen;
	}
	
	public JMenuItem getMntmEditarExamen(){
		return super.mntmEditarExamen;
	}
	
	public JMenuItem getMntmEliminarExamen(){
		return super.mntmEliminarExamen;
	}
	
	public JMenuItem getMntmNuevoExamen(){
		return super.mntmNuevoExamen;
	}
	
	public JMenuItem getMntmDetallesExamen(){
		return super.mntmDetallesExamen;
	}
	
	public JMenuItem getMntmManualDeUsuario(){
		return super.mntmManualDeUsuario;
	}
	
	public JMenuItem getMntmAlumnos(){
		return super.mntmAlumnos;
	}
	
	public JMenuItem getMntmExamenes(){
		return super.mntmExamenes;
	}
	
	public TableroProfesorController(ActionListener actionListener) throws BaseDatosExceptionModel, SQLException{
		this.crearTablaAlumnos();
		this.agregarEventos(actionListener);
	}
	
	private void agregarEventos(ActionListener actionListener){
		super.mntmSalir.addActionListener(actionListener);
		super.mntmEditarAlumno.addActionListener(actionListener);
		super.mntmEliminarAlumno.addActionListener(actionListener);
		super.mntmNuevoAlumno.addActionListener(actionListener);
		super.mntmVerAlumno.addActionListener(actionListener);
		super.mntmCrearPdfExamen.addActionListener(actionListener);
		super.mntmEditarExamen.addActionListener(actionListener);
		super.mntmEliminarExamen.addActionListener(actionListener);
		super.mntmNuevoExamen.addActionListener(actionListener);
		super.mntmDetallesExamen.addActionListener(actionListener);
		super.mntmAlumnos.addActionListener(actionListener);
		super.mntmExamenes.addActionListener(actionListener);
		super.mntmManualDeUsuario.addActionListener(actionListener);
	}
	
	private int getTableRowSelected() throws ExceptionModel{
		int rowTable= super.table.getSelectedRow();
		
		if(rowTable < 0){
			throw new ExceptionModel(ExceptionModel.ELEJIR_FILA_DE_TABLA);
		}
		
		return rowTable;
	}
	
	public String crearTablaAlumnos() throws BaseDatosExceptionModel, SQLException{
		super.mnAlumno.setEnabled(true);
		super.mnExamen.setEnabled(false);
		return funcionalidadPedirAlServidor("consultarAlumnosEvaluador");
	}
	
	/**
	 * Modifica el contenido de la tabla, además, activa el menu examen y desactiva el menu alumno
	 * @return json del metodo a activar en el servidor
	 */
	public String crearTablaExamenes() throws BaseDatosExceptionModel, SQLException{
		super.mnExamen.setEnabled(true);
		super.mnAlumno.setEnabled(false);
		return funcionalidadPedirAlServidor("consultarMateriasEvaluador");
	}
	
	public void funcionalidadEliminarAlumno() throws ExceptionModel, BaseDatosExceptionModel, SQLException {
		BaseDatosController bdController= new BaseDatosController();
		
		bdController.bajaAlumno(table.getValueAt(this.getTableRowSelected(),
				table.getColumn("Identificación").getModelIndex()).toString());
		
		this.crearTablaAlumnos();
	}
	
	public AlumnoModel funcionalidadObtenerAlumno() throws BaseDatosExceptionModel, ExceptionModel, NoSuchAlgorithmException, SQLException {
		BaseDatosController bdController= new BaseDatosController();
		return bdController.obtenerAlumno(super.table.getValueAt(this.getTableRowSelected(),
				super.table.getColumn("Identificación").getModelIndex()).toString());		
	}
	
	private String funcionalidadPedirAlServidor(String metodoName) {
		JSONObject json= new JSONObject();
		json.put("metodo", metodoName);
		
		return json.toJSONString();
	}
	/*public AlumnoModel funcionalidadMostrarAlumno() throws BaseDatosExceptionModel, ExceptionModel, NoSuchAlgorithmException, SQLException {
		BaseDatosController bdController= new BaseDatosController();
		return bdController.obtenerAlumno(super.table.getValueAt(this.getTableRowSelected(),
				super.table.getColumn("Identificación").getModelIndex()).toString());		
	}*/
	
	public void cerrarVentana(){
		super.dispose();
	}
}
