package repository;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

import model.Grupo;
import model.Individual;
import model.Mensagem;
import model.Participante;

//---- atualizado em 30/06

public class Repositorio {

    private TreeMap<String, Participante> participantes = new TreeMap<>();
    private ArrayList<Mensagem> mensagens = new ArrayList<>();

    public Repositorio() {
        carregarObjetos();
    }

    public void carregarObjetos() {
        // carregar para o repositorio os objetos dos arquivos csv
        try {
            // caso os arquivos nao existam, serao criados vazios
            File f1 = new File(new File("./data/mensagens.csv").getCanonicalPath());
            File f2 = new File(new File("./data/individuos.csv").getCanonicalPath());
            File f3 = new File(new File("./data/grupos.csv").getCanonicalPath());
            if (!f1.exists() || !f2.exists() || !f3.exists()) {
                // System.out.println("criando arquivo .csv vazio");
                FileWriter arquivo1 = new FileWriter(f1);
                arquivo1.close();
                FileWriter arquivo2 = new FileWriter(f2);
                arquivo2.close();
                FileWriter arquivo3 = new FileWriter(f3);
                arquivo3.close();
                return;
            }
        } catch (Exception ex) {
            throw new RuntimeException("criacao dos arquivos vazios:" + ex.getMessage());
        }

        String linha;
        String[] partes;

        try {
            String nome, senha, administrador;
            File f = new File(new File("./data/individuos.csv").getCanonicalPath());
            Scanner arquivo1 = new Scanner(f); // pasta do projeto
            while (arquivo1.hasNextLine()) {
                linha = arquivo1.nextLine().trim();
                partes = linha.split(";");
                // System.out.println(Arrays.toString(partes));
                nome = partes[0];
                senha = partes[1];
                administrador = partes[2];
                Individual ind = new Individual(nome, senha, Boolean.parseBoolean(administrador));
                this.adicionar(ind);
            }
            arquivo1.close();
        } catch (Exception ex) {
            throw new RuntimeException("leitura arquivo de individuos:" + ex.getMessage());
        }

        try {
            String nome;
            Grupo grupo;
            Individual individuo;
            File f = new File(new File("./data/grupos.csv").getCanonicalPath());
            Scanner arquivo2 = new Scanner(f); // pasta do projeto
            while (arquivo2.hasNextLine()) {
                linha = arquivo2.nextLine().trim();
                partes = linha.split(";");
                // System.out.println(Arrays.toString(partes));
                nome = partes[0];
                grupo = new Grupo(nome);
                if (partes.length > 1)
                    for (int i = 1; i < partes.length; i++) {
                        individuo = this.localizarIndividual(partes[i]);
                        grupo.adicionar(individuo);
                        individuo.adicionar(grupo);
                    }
                this.adicionar(grupo);
            }
            arquivo2.close();
        } catch (Exception ex) {
            throw new RuntimeException("leitura arquivo de grupos:" + ex.getMessage());
        }

        try {

            try {
                String nomeemitente, nomedestinatario, texto;
                int id;
                LocalDateTime datahora;
                Mensagem m;
                Participante emitente, destinatario;
                File f = new File(new File("./data/mensagens.csv").getCanonicalPath());
                Scanner arquivo3 = new Scanner(f); // pasta do projeto
                while (arquivo3.hasNextLine()) {
                    linha = arquivo3.nextLine().trim();
                    partes = linha.split(";");
                    id = Integer.parseInt(partes[0]);
                    nomeemitente = partes[1];
                    nomedestinatario = partes[2];
                    texto = String.join(";", Arrays.copyOfRange(partes, 3, partes.length - 1));
                    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                            .optionalStart()
                            .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
                            .optionalEnd()
                            .toFormatter();
                    datahora = LocalDateTime.parse(partes[partes.length - 1], formatter);

                    emitente = this.localizarParticipante(nomeemitente);
                    destinatario = this.localizarParticipante(nomedestinatario);
                    m = new Mensagem(id, emitente, destinatario, texto, datahora);
                    this.adicionar(m);
                    emitente.adicionarEnviada(m);
                    destinatario.adicionarRecebida(m);
                }
                arquivo3.close();
            } catch (Exception ex) {
                throw new RuntimeException("leitura arquivo de mensagens:" + ex.getMessage());
            }
        } catch (Exception ex) {
            throw new RuntimeException("criacao dos arquivos vazios:" + ex.getMessage());
        }
    }

    public void salvarObjetos() {
        // gravar nos arquivos csv os objetos que estão no repositório
        try {
            File f = new File(new File("./data/mensagens.csv").getCanonicalPath());
            FileWriter arquivo1 = new FileWriter(f, false);
            for (Mensagem m : mensagens) {
                arquivo1.write(m.getId() + ";" +
                        m.getEmitente().getNome() + ";" +
                        m.getDestinatario().getNome() + ";" +
                        m.getTexto() + ";" +
                        m.getData() + "\n");
            }
            arquivo1.close();
        } catch (Exception e) {
            throw new RuntimeException("problema na criação do arquivo  mensagens " + e.getMessage());
        }

        try {
            File f = new File(new File("./data/individuos.csv").getCanonicalPath());
            FileWriter arquivo2 = new FileWriter(f);
            for (Individual ind : this.getIndividuos()) {
                arquivo2.write(ind.getNome() + ";" + ind.getSenha() + ";" + ind.getAdministrador() + "\n");
            }
            arquivo2.close();
        } catch (Exception e) {
            throw new RuntimeException("problema na criação do arquivo  individuos " + e.getMessage());
        }

        try {
            File f = new File(new File("./data/grupos.csv").getCanonicalPath());
            FileWriter arquivo3 = new FileWriter(f);
            for (Grupo g : this.getGrupos()) {
                String texto = "";
                for (Individual ind : g.getIndividuos())
                    texto += ";" + ind.getNome();
                arquivo3.write(g.getNome() + texto + "\n");
            }
            arquivo3.close();
        } catch (Exception e) {
            throw new RuntimeException("problema na criação do arquivo  grupos " + e.getMessage());
        }
    }

    public Individual localizarIndividual(String nome) {

        Individual chosen = null;

        for (Participante part : this.participantes.values()) {
            if (part instanceof Individual) {

                if (part.getNome().equals(nome)) {
                    chosen = (Individual) part;
                    break;
                }
            }
        }
        /*
         * ArrayList<Individual> IndividuosList = this.getIndividuos();
         * Individual chosen = null;
         * for(Individual ind: IndividuosList) {
         * if(ind.getNome().equals(nome)) {
         * chosen = ind;
         * break;
         * }
         * }
         */

        return chosen;
    }

    public Grupo localizarGrupo(String nome) {
        Grupo chosen = null;
        ArrayList<Grupo> listaGrupos = this.getGrupos();
        for (Grupo aux : listaGrupos) {
            if (aux.getNome().equals(nome)) {
                chosen = aux;
                break;
            }
        }
        return chosen;
    }

    public Participante localizarParticipante(String nome) {
        Participante member = this.participantes.get(nome);
        if (member == null) {
            // penso em fazer um tratamento especial
            return null;
        }
        return member;
    }

    public ArrayList<Mensagem> getMensagems() {
        ArrayList<Mensagem> listaMensagem = new ArrayList<>();
        for (Mensagem msg : mensagens) {
            listaMensagem.add(msg);
        }
        return listaMensagem;
    }

    public ArrayList<Individual> getIndividuos() {

        ArrayList<Individual> IndividuosList = new ArrayList<>();
        for (Participante part : this.participantes.values()) {
            if (part instanceof Individual) {
                IndividuosList.add((Individual) part);
            }
        }
        return IndividuosList;
    }

    public ArrayList<Grupo> getGrupos() {
        ArrayList<Grupo> ListaDeGrupos = new ArrayList<>();
        for (Participante aux : participantes.values()) {
            if (aux instanceof Grupo) {
                ListaDeGrupos.add((Grupo) aux);
            }
        }
        /*
         * ArrayList<Individual> IndividuosList = this.getIndividuos();
         * ArrayList<Grupo> GroupList = new ArrayList<>();
         * 
         * for(Individual person: IndividuosList) {
         * if(person.getGrupos() != null){
         * for(Grupo group: person.getGrupos()) {
         * if(!GroupList.contains(group)) {
         * GroupList.add(group);
         * }
         * }
         * }
         * 
         * }
         */
        return ListaDeGrupos;
    }

    // fim dos métodos temporários

    public void remover(Mensagem msg) {
        this.mensagens.remove(msg);

    }

    public void adicionar(Individual ind) {
        participantes.put(ind.getNome(), ind);

    }

    public void adicionar(Grupo ind) {
        participantes.put(ind.getNome(), ind);

    }

    public void adicionar(Mensagem msg) {
        this.mensagens.add(msg);

    }

    public void remover(Participante p) {
        this.participantes.remove(p.getNome());
        this.salvarObjetos();
    }

}