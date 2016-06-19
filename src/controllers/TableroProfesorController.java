package controllers;

import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JMenuItem;

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
	
	public void crearTablaAlumnos() throws BaseDatosExceptionModel, SQLException{
		BaseDatosController bDController= new BaseDatosController();
		super.agregarContenidoTabla(bDController.obtenerListaAlumnos(), 
				new String[] {"Nombre completo", "Grupo", "Identificación"});
		super.mnAlumno.setEnabled(true);
		super.mnExamen.setEnabled(false);
	}
	
	public void crearTablaExamenes() throws BaseDatosExceptionModel, SQLException{
		BaseDatosController baseDatosController= new BaseDatosController();
		super.agregarContenidoTabla(baseDatosController.obtenerExamenes(), 
				new String[] {"Materia", "Descripción", "Calificación"});
		super.mnExamen.setEnabled(true);
		super.mnAlumno.setEnabled(false);
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
	
	/*public AlumnoModel funcionalidadMostrarAlumno() throws BaseDatosExceptionModel, ExceptionModel, NoSuchAlgorithmException, SQLException {
		BaseDatosController bdController= new BaseDatosController();
		return bdController.obtenerAlumno(super.table.getValueAt(this.getTableRowSelected(),
				super.table.getColumn("Identificación").getModelIndex()).toString());		
	}*/
	
	public void cerrarVentana(){
		super.dispose();
	}
}
