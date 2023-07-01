package test;

import backend.*;

public class Teste3Repositorio {
	public static void main(String[] args) {
		try {

			Fachada.criarGrupo("GolpeDoBolsonaro");

			Fachada.criarIndividuo("Xuxa", "2222");
			Fachada.criarIndividuo("Vitor", "16421");
			Fachada.criarAdministrador("Tonho", "Bolsonaro2022");

			Fachada.inserirGrupo("Vitor", "GolpeDoBolsonaro");
			Fachada.inserirGrupo("Xuxa", "GolpeDoBolsonaro");
			Fachada.inserirGrupo("Tonho", "GolpeDoBolsonaro");

			Fachada.criarMensagem("Vitor", "GolpeDoBolsonaro", "Vamos nos reunir amanhã!");
			Fachada.criarMensagem("Xuxa", "GolpeDoBolsonaro", "Vamos sim Baixinho!");
			Fachada.criarMensagem("Xuxa", "GolpeDoBolsonaro", "Ylali lali ê ô ô ô");
			Fachada.criarMensagem("Tonho", "GolpeDoBolsonaro", "Fora Golpistas!");
			Fachada.criarMensagem("Tonho", "GolpeDoBolsonaro", "Viva a Democracia!");
			Fachada.criarMensagem("Tonho", "GolpeDoBolsonaro", "Conto com todos");
			Fachada.criarMensagem("Vitor", "GolpeDoBolsonaro", "Eu Vou levar a minha filha... Hehe");
			Fachada.criarMensagem("Tonho", "GolpeDoBolsonaro", "Que isso Compatriota! Criança não Pode.");
			Fachada.criarMensagem("Xuxa", "GolpeDoBolsonaro", "Baixinhos e baixinhas podem se machucar! Vivaaaaa.");
			Fachada.criarMensagem("Vitor", "GolpeDoBolsonaro", "Eita! Vou deixar para lá então.");
			Fachada.apagarMensagem("Vitor", 7);
			Fachada.salvarDados();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
