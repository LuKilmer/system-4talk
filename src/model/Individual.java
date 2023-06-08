package model;

import java.util.ArrayList;

import model.Mensagem;

public class Individual extends Participante {
	private String senha;
	private boolean administrador;
	private ArrayList<Mensagem> enviadas;
	private ArrayList<Grupo> grupos;

	public Individual(String nome, String senha, boolean administrador) {
		super(nome);
		this.senha = senha;
		this.administrador = administrador;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public ArrayList<Mensagem> getEnviadas() {
		return enviadas;
	}

	public ArrayList<Grupo> getGrupos() {
		return grupos;
	}


	public String toString() {
		String checkAdministrador = "Não é";

		if (this.administrador)
			checkAdministrador = "Sim,";

		return "Nome: " + this.getNome() + " " + checkAdministrador + " administrador." + "grupos=" + grupos;
	}
}
