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

	@Override
	public String toString() {
		return "Nome do Grupo:" + this.getNome() + " Quantidade de participantes: " + this.individuos.size();
	}
}
