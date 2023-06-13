package model;

import java.util.ArrayList;


public class Individual extends Participante {
	private String senha;
	private boolean administrador;
	private ArrayList<Mensagem> enviadas;
	private ArrayList<Grupo> grupos;

	public Individual(String nome, String senha, boolean administrador) throws Exception {
		super(nome);
		if (senha.length() == 0)
			throw new Exception("O CAMPO DE SENHA PRECISA SER PREENCHIDO");
		this.senha = senha;
		this.administrador = administrador;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws Exception {
		if (senha.length() == 0)
			throw new Exception("O CAMPO DE SENHA PRECISA SER PREENCHIDO");
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

	public boolean AdicionarGrupo(Grupo grupo) {
		if (this.grupos.indexOf(grupo) == -1) {
			this.grupos.add(grupo);
			return true;
		} else
			return false;
	}

	public boolean removerGrupo(Grupo grupo) {
		return this.grupos.remove(grupo);
	}

	public boolean AdicionarMensagem(Mensagem mensagem) {
		return this.enviadas.add(mensagem);

	}

	public boolean removerMensagem(Mensagem mensagem) {
		return this.enviadas.remove(mensagem);
	}

	public String toString() {
		String checkAdministrador = "Não é";

		if (this.administrador)
			checkAdministrador = "Sim,";

		return "Nome: " + this.getNome() + " " + checkAdministrador + " administrador." + "grupos=" + grupos;
	}

	public void removerEnviada(Mensagem m) {
	}

    public Mensagem localizarEnviada(int id) {
        return null;
    }

    public Object localizarGrupo(String nome) {
        return null;
    }

    
}
