package model;

import java.util.ArrayList;

public class Grupo extends Participante {

	private ArrayList<Individual> individuos;

	public Grupo(String nome) {
		super(nome);
	}

	public ArrayList<Individual> getIndividuos() {
		return individuos;
	}

	public boolean addIndividuo(Individual individuo) {
		if (this.individuos.indexOf(individuo) == -1) {
			this.individuos.add(individuo);
			return true;
		} else
			return false;
	}

	public boolean removerIndividuo(Individual individuo) {
		return this.individuos.remove(individuo);
	}

	@Override
	public String toString() {
		return "Nome do Grupo:" + this.getNome() + " Quantidade de participantes: " + this.individuos.size();
	}

}
