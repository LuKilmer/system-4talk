package model;

import java.util.ArrayList;

public class Grupo extends Participante {

	private ArrayList<Individual> individuos = new ArrayList<>();

	public Grupo(String nome) {
		super(nome);
	}

	/* Retorna o array contendo todos os individuos do Grupo */
	public ArrayList<Individual> getIndividuos() {
		return individuos;
	}

	public boolean adicionar(Individual individuo) {
		if (this.individuos.indexOf(individuo) == -1) {
			this.individuos.add(individuo);
			return true;
		} else
			return false;
	}

	public void removerIndividuo(Individual individuo) {
		this.individuos.remove(individuo);
	}

	public Individual localizarIndividual(String nome) {
		for (Individual i : this.individuos) {
			if (i.getNome().equals(nome)) {
				return i;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Nome do Grupo:" + this.getNome() + " Quantidade de participantes: " + this.individuos.size();
	}

	public void setIndividuos(ArrayList<Individual> individuos) {
		this.individuos = individuos;
	}

	@Override
	public Mensagem removerEnviada(int id) {
		ArrayList<Mensagem> enviadas = this.getEnviadas();
		try {
			for (Mensagem m : enviadas) {

				if (m.getId() == id)
					enviadas.remove(id);

				else if (m.getId() > id)
					break;
			}
			return null;

		} catch (Exception E) {
			return null;
		}

	}

	/*
	 * @Override
	 * public void removerRecebida(int id) {
	 * for (Participante i : this.individuos) {
	 * i.removerRecebida(id);
	 * }
	 * this.getRecebidas().remove(this.localizarRecebida(id));
	 * 
	 * }
	 */

	public ArrayList<Mensagem> localizarClones(int id) {

		ArrayList<Mensagem> clones = new ArrayList<>();

		for (Mensagem m : this.getEnviadas()) {
			if (m.getId() == id)
				clones.add(m);
			else if (m.getId() > id)
				break;
		}
		return clones;
	}

}
