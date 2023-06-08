package model;

import java.time.LocalDateTime;

public class Mensagem {
	private int id;
	private String texto;
	private Individual emitente;
	private Participante desitnatario;
	private LocalDateTime dataHora;
	
	public Mensagem(int id, String texto, Individual emitente, Participante desitnatario, LocalDateTime dataHora) {
		this.id = id;
		this.texto = texto;
		this.emitente = emitente;
		this.desitnatario = desitnatario;
		this.dataHora = dataHora;
	}
	
	public int getId() {
		return id;
	}
	public String getTexto() {
		return texto;
	}
	public Individual getEmitente() {
		return emitente;
	}
	public Participante getDesitnatario() {
		return desitnatario;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public String toString() {
		return "id: " + id + "\n"  + emitente.getNome() +  "disse: " + texto + "\n" + dataHora ;
	}
}
