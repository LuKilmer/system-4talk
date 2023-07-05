package model;

import java.time.LocalDateTime;


public class Mensagem {
	private int id;
	private String texto;
	private Participante emitente;
	private Participante destinatario;
	private LocalDateTime dataHora;

	public Mensagem(int id, Participante emitente, Participante desitnatario, String texto, LocalDateTime datahora) {
		this.id = id;
		this.texto = texto;
		this.emitente = emitente;
		this.destinatario = desitnatario;
		this.dataHora = datahora;
	}

	public Mensagem(int id, Participante emitente, Participante desitnatario, String texto) {

		this.id = id;
		this.texto = texto;
		this.emitente = emitente;
		this.destinatario = desitnatario;
		this.dataHora = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getData() {
		return this.dataHora;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Participante getEmitente() {
		return emitente;
	}

	public Participante getDestinatario() {
		return this.destinatario;
	}

	public String toString() {
		return "id: " + id + "\n" + emitente.getNome() + " : " + texto + "\n" + dataHora;
	}

	public boolean equals(Mensagem m) {
		return (this.id == m.getId());
	}

	public void setEmitente(Participante p) {
		this.emitente = p;
	}

	public void setDestinario(Participante p) {
		this.destinatario = p;
	}
	
    public Mensagem compareTo(Mensagem outroObjeto) {
		if(this.getId()< outroObjeto.getId()){
			return this;
		}else{return outroObjeto;}
        
    }
}
