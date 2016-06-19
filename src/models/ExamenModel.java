package models;

import java.util.Vector;

public class ExamenModel {
	private float calificacion= 0.0f;
	private String idExamen= "";
	private String materia= "";
	
	private Vector<PreguntaModel> preguntas= null;
	
	public ExamenModel(String materia){
		preguntas= new Vector<PreguntaModel>();
		this.setMateria(materia);
	}
	
	public float getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}
	public Vector<PreguntaModel> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(Vector<PreguntaModel> preguntas) {
		this.preguntas = preguntas;
	}
	public String getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(String idExamen) {
		this.idExamen = idExamen;
	}
	public void crearNuevoId(){
		setIdExamen(Tool.generarId("E"));
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
}