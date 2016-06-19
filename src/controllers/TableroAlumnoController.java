package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import views.TableroAlumnoView;

public class TableroAlumnoController extends TableroAlumnoView{
	public JMenuItem getMntmSalir(){
		return super.mntmSalir;
	}
	
	public JMenuItem getMntmCrearPDF(){
		return super.mntmCrearPdf;
	}
	
	public JMenuItem getMntmResolver(){
		return super.mntmResolver;
	}
	
	public JMenuItem getMntmDetalles(){
		return super.mntmDetalles;
	}
	
	public JMenuItem getMntmManualDeUsuario(){
		return super.mntmManualDeUsuario;
	}
	
	public TableroAlumnoController(ActionListener actionListener, String[][] contenido, String[] titulo){
		super(contenido, titulo);
		this.agregarEventos(actionListener);
	}
	
	public int obtenerNumeroFilaSeleccionada(){
		return super.table.getSelectedRow();
	}
	
	private void agregarEventos(ActionListener actionListener){
		super.mntmSalir.addActionListener(actionListener);
		super.mntmCrearPdf.addActionListener(actionListener);
		super.mntmResolver.addActionListener(actionListener);
		super.mntmDetalles.addActionListener(actionListener);
		super.mntmManualDeUsuario.addActionListener(actionListener);
	}
	
	public void cerrarVentana(){
		super.dispose();
	}
}
