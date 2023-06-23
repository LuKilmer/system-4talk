package model;

import java.time.LocalDateTime;

public class Mensagem {
	private int id;
	private String texto;
	private Participante emitente;
	private Participante destinatario;
	private LocalDateTime dataHora;

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

	public String getTexto() {
		return texto;
	}

	public Participante getEmitente() {
		return emitente;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public Participante getDestinatario() {
		return this.destinatario;
	}

	public String toString() {
		return "id: " + id + "\n" + emitente.getNome() + "disse: " + texto + "\n" + dataHora;
	}

}
