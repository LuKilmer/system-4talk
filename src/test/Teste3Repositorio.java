package test;
import repository.*;

public class Teste3Repositorio {
	public static void main(String[] args) {
		Repositorio seila = new Repositorio();
		seila.carregarObjetos();
		System.out.println("ok");
		seila.salvarObjetos();
	}
}
