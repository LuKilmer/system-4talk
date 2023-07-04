package test;

import backend.Fachada;

public class Teste4Reositorio {
    public static void main(String[] args){
        try {
            Fachada.criarMensagem("maria","joao","depressao");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
