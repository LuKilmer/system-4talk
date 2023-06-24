package test;

import backend.*;

public class Teste3Repositorio {
	public static void main(String[] args) {
		try {
			Fachada.criarGrupo("GolpeDoBolsonaro");
			Fachada.criarIndividuo("Vitor", "16421");
			Fachada.inserirGrupo("Vitor", "GolpeDoBolsonaro");
			Fachada.criarAdministrador("Tonho Pereira", "Bolsonaro2022");
			Fachada.inserirGrupo("Tonho Pereira", "GolpeDoBolsonaro");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
