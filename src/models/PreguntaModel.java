package models;

import java.util.Vector;

public class PreguntaModel {
	private String idPregunta= "";
	private String pregunta= "";
	private Vector<RespuestaModel> respuestas= null;
	
	public PreguntaModel(){
		respuestas= new Vector<RespuestaModel>();
	}
	
	public PreguntaModel(String pregunta){
		this();
		this.pregunta= pregunta;
	}
	
	public String getIdPregunta() {
		return idPregunta;
	}
	
	public void setIdPregunta(String idPregunta) {
		this.idPregunta = idPregunta;
	}
	
	public void generarNuevoId(){
		setIdPregunta(Tool.generarId("P"));
	}
	
	public String getPregunta() {
		return pregunta;
	}
	
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	public Vector<RespuestaModel> getRespuestas() {
		return this.respuestas;
	}
	
	public void setRespuestas(Vector<RespuestaModel> respuestas) {
		this.respuestas = respuestas;
	}
}
