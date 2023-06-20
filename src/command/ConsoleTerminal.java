package command;
import backend.Fachada;
import model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleTerminal {
    private static Scanner scanner = new Scanner(System.in);
    private static final List<String> listaDeComandos = Collections.unmodifiableList(
    new ArrayList<String>() {{
        add("1-Mostrar Individuos");
        add("2-Mostrar Grupos");
        add("3-Mostrar Mensagens");
        add("4-Fazer Login");
        add("5-Cadastrar");
        add("6-Criar Grupo");
    }});
    private static final List<String> listaDeComandosUsuario = Collections.unmodifiableList(
    new ArrayList<String>() {{
        add("1-Enviar Mensagem");
        add("2-Mudar senha");
        add("3-Ler Mensagens");
        add("4-Logout");
    }});

    private static void mostrarIndividuos(){
        ArrayList<Individual> lista = Fachada.listarIndividuos();
        for(Individual person: lista){
            System.out.println(person);
        }
    }

    private static void mostrarGrupos(){
        ArrayList<Grupo> lista = Fachada.listarGrupos();
        for(Grupo grupo: lista){
            System.out.println(grupo);
        }
    }

    private static void mostrarMensagens(){
        ArrayList<Mensagem> lista = Fachada.listarMensagens();
        for(Mensagem mensagem: lista){
            System.out.println(mensagem);
        }
    }


    private static void usuarioMenu(){
        System.out.println("Enter para a lista de comandos");
        
            while(scanner.hasNextLine()){
                String command = scanner.nextLine();
                if(command.equals("exit")){
                    break;
                }else{
                    if(command.equals("0")){
                         for(String lines: listaDeComandosUsuario){System.out.println(lines);}
                    }else{
                        processarComando("1"+command);
                  
                    }
                    
                }
        }
    }

    private static void login(){
        while(true){
            System.out.println("Login\ndigite o nome de usuario:");
            String nome = scanner.nextLine();
            Individual ind = Fachada.localizarIndividual(nome);
            if(ind == null){
                System.out.println("Erro ao digitar nome de login");
            }else{
                System.out.println("Senha:");
                String senha = scanner.nextLine();
                if(ind.getSenha().equals(senha)){
                    usuarioMenu();
                }
            }
        }  
    }

    private static void cadastrar(){
        System.out.println("Cadastrar novo usuário\nNome:");
        String nome = scanner.nextLine();
       
        System.out.println("Senha:");
        String senha = scanner.nextLine();
        try{
            Fachada.criarIndividuo(nome, senha);
        }catch(Exception e){
            System.out.println("Erro ao cadastrar,"+e.getMessage());
        }
    }

    private static void criarGrupo(){
        try{
            while(true){
                System.out.println("Inseira o nome do novo grupo");
                String nome = scanner.nextLine();
                Grupo grupo = Fachada.localizarGrupo(nome);
                if(grupo == null){
                    Fachada.criarGrupo(nome);
                    break;
                }else{
                    System.out.println("Nome já existe...");
                }
        }
        }catch (Exception e){

        }
        
    }




    private static void processarComando(String comando){
        switch (comando){
            case "1":
                mostrarIndividuos();
                break;
            case "2":
                mostrarGrupos();
                break;
            case "3":
                mostrarMensagens();
                break;
            case "4":
                login();
                break;
            case "5":
                cadastrar();
                break;
            case "6":
                criarGrupo();
                break;
            case "11":

                break;
            case "12":

                break;
            case "13":

                break;
            case "14":

                break;
        }
    }

 

    public static void start(){
        System.out.println("Enter para a lista de comandos");
        
            while(scanner.hasNextLine()){
                String command = scanner.nextLine();
                if(command.equals("exit")){
                    break;
                }else{
                    if(command.equals("0")){
                         for(String lines: listaDeComandos){System.out.println(lines);}
                    }else{
                        processarComando(command);
                  
                    }
                    
                }
                
            
        }
    }
    public static void main(String[] args){
        ConsoleTerminal.start();
    }
}
