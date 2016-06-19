package controllers;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.ExamenModel;
import models.PreguntaModel;
import models.Tool;
import views.ExamenAltaCambioView;

public class ExamenAltaCambioController extends ExamenAltaCambioView{
	private ExamenModel examen= null;
	private MouseListener mouseListener= null;
	public Vector<JPanel> jPanelPreguntas= null;
	private int posParaEditar= -1991; //Pocición para editar
	
	public JButton getBtnAgregar(){
		return super.btnAgregar;
	}
	
	public JButton getBtnEliminar(){
		return super.btnEliminar;
	}
	
	public JButton getBtnGuardar(){
		return super.btnGuardar;
	}
	
	public ExamenAltaCambioController(ActionListener actionListener, WindowListener windowListener, MouseListener mouseListener){
		this.mouseListener= mouseListener;
		agregarEventos(actionListener);
		super.addWindowListener(windowListener);
		jPanelPreguntas= new Vector<JPanel>();
		super.btnGuardar.setEnabled(false);
		super.btnEliminar.setEnabled(false);
	}
	
	public ExamenAltaCambioController(ActionListener actionListener, WindowListener windowListener, MouseListener mouseListener, ExamenModel examen){
		this(actionListener, windowListener, mouseListener);
		this.examen= examen;
		this.mostrarPreguntas();
		//this.mostrarRespuestas();
	}
	
	private void agregarEventos(ActionListener actionListener){
		super.btnAgregar.addActionListener(actionListener);
		super.btnEliminar.addActionListener(actionListener);
		super.btnGuardar.addActionListener(actionListener);
	}
	
	private JPanel prepararPanelPregunta(String pregunta){
		JPanel jPanelPregunta= null;
		jPanelPregunta= new JPanel();
		FlowLayout fLayout= (FlowLayout) jPanelPregunta.getLayout();
		fLayout.setAlignment(FlowLayout.LEFT);
		jPanelPregunta.add(new JLabel(pregunta));
		jPanelPregunta.addMouseListener(mouseListener);
		return jPanelPregunta;
	}
	
	private void agregarNuevaPreguntaGUI(String pregunta){
		this.jPanelPreguntas.add(this.prepararPanelPregunta(pregunta));
		super.verticalBoxPreguntas.add(this.jPanelPreguntas.lastElement());
		super.verticalBoxPreguntas.revalidate();
	}
	
	private void mostrarPreguntas(){
		Vector<PreguntaModel> preguntas = this.examen.getPreguntas();
		
		for(int i=0; i<preguntas.size(); i++){
			this.agregarNuevaPreguntaGUI(preguntas.get(i).getPregunta());
		}
		///this.mostrarRespuestas();
	}
	
	/*private void mostrarRespuestas(int pos){
		Vector<PreguntaModel> preguntas = this.examen.getPreguntas();
	}*/
	
	public void colocarPreguntaEnTextField(int pos){
		this.posParaEditar= pos;
		if(this.posParaEditar >= 0){
			super.textFieldPregunta.setText(this.examen.getPreguntas().get(this.posParaEditar).getPregunta());
		}
	}
	
	private void agregarPregunta(String pregunta){
		if(this.posParaEditar >= 0){
			this.examen.getPreguntas().remove(posParaEditar);
			this.jPanelPreguntas.remove(posParaEditar);
			this.verticalBoxPreguntas.remove(posParaEditar);
		}
		this.examen.getPreguntas().add(new PreguntaModel(pregunta));
		this.agregarNuevaPreguntaGUI(pregunta);
	}
	
	public void funcionalidadAgregar(){
		String preguntaCompleta= super.textFieldPregunta.getText();
		
		if(this.examen == null){
			this.examen= new ExamenModel(preguntaCompleta);
		}
		
		if(!preguntaCompleta.isEmpty()){
			this.agregarPregunta(Tool.limpiarPregunta(preguntaCompleta));
		}
		
		this.posParaEditar= -1991;
		
		super.textFieldPregunta.setText("");
		super.btnEliminar.setEnabled(false);
		super.btnGuardar.setEnabled(true);
	}
	
	public void funcionalidadEliminar(){
		if(this.posParaEditar >= 0){
			this.examen.getPreguntas().remove(posParaEditar);
			this.jPanelPreguntas.remove(posParaEditar);
			verticalBoxPreguntas.remove(posParaEditar);
			super.repaint();
			super.verticalBoxPreguntas.revalidate();
		}
		
		super.textFieldPregunta.setText("");
		super.btnEliminar.setEnabled(false);
		super.btnGuardar.setEnabled(true);
		
		this.posParaEditar= -1991;
	}
	
	public ExamenModel funcionalidadGuardar(){
		return this.examen;
	}
	
	public void cerrarVentana(){
		super.dispose();
	}
}
