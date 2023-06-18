package test;
import repository.*;
import backend.*;
import model.*;

public class Teste3Repositorio {
	public static void main(String[] args) {
		try {
			Repositorio repo = new Repositorio();
			Grupo cemiterio = new Grupo("Cemiterio 2023");
			Individual p2 = new Individual("Palmeirinha", "123456", false);
			repo.adicionar(cemiterio);
			repo.adicionar(p2);
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
