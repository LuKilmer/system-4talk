package model;

import java.util.ArrayList;

public  class  Participante {

	private String nome;
	
	private ArrayList<Mensagem> recebidas;
	
	public Participante(String nome) {
		this.nome= nome.strip();
		}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.strip();
	}
	
	public ArrayList<Mensagem> getRecebidas() {
		return recebidas;
	}

}
