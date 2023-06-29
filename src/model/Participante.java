package model;

import java.util.ArrayList;

public class Participante {

	private String nome;
	private ArrayList<Mensagem> recebidas = new ArrayList<>();

	public Participante(String nome) {
		this.nome = nome.strip();
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

	public void removerRecebida(Mensagem m) {
		this.recebidas.remove(m);
	}

	public void adicionarRecebida(Mensagem m) {
		this.recebidas.add(this.recebidas.size(), m);
	}

}
