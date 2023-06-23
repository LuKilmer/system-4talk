package backend;

import java.time.Instant;
import java.util.ArrayList;

import java.util.function.Predicate;

import model.Grupo;
import model.Individual;
import model.Mensagem;
import model.Participante;
import repository.Repositorio;

public class Fachada {
	
	private static Repositorio repositorio = new Repositorio();
	
	public static void salvarDados() {
		repositorio.salvarObjetos();
	};

	public static Individual localizarIndividual(String nome){

		return repositorio.localizarIndividual(nome);
	}

	public static Grupo localizarGrupo(String name){
		return repositorio.localizarGrupo(name);
	}

	public static ArrayList<Individual> listarIndividuos() {
		return repositorio.getIndividuos();
	}

	public static ArrayList<Grupo> listarGrupos() {
		return repositorio.getGrupos();
	}

	public static ArrayList<Mensagem> listarMensagensEnviadas(String nome) throws Exception {
		Individual ind = repositorio.localizarIndividual(nome);
		if (ind == null)
			throw new Exception("listar  mensagens enviadas - nome nao existe:" + nome);

		return ind.getEnviadas();
	}

	public static ArrayList<Mensagem> listarMensagensRecebidas(String nome) throws Exception {
		Individual ind = repositorio.localizarIndividual(nome);
		if (ind == null) 
			throw new Exception("listar  mensagens recebidas - nome nao existe:" + nome);
		
		return ind.getRecebidas();

	}

	public static void criarIndividuo(String nome, String senha) throws Exception {
		if (nome.isEmpty())
			throw new Exception("criar individual - nome vazio:");
		if (senha.isEmpty())
			throw new Exception("criar individual - senha vazia:");

		Participante p = repositorio.localizarParticipante(nome);
		if (p != null)
			throw new Exception("criar individual - nome ja existe:" + nome);

		Individual individuo = new Individual(nome, senha, false);
		repositorio.adicionar(individuo);
		repositorio.salvarObjetos();
	}

	/**
	 * – cria um administrador no repositório, caso inexista no repositório
	 * 
	 * @param nome
	 * @param senha
	 * @throws Exception
	 */
	public static void criarAdministrador(String nome, String senha) throws Exception {
		Individual individuo = repositorio.localizarIndividual(nome);

		/*
		 * O índividuo não existe — Então ele é criado com o valor de adm True.
		 * O índividuo existe, mas não é adm — Ele recebe true no lugador do adm
		 * O índividuo existe e é um adm — Retornamos uma Exception.
		*/
		if (individuo == null) {
			individuo = new Individual(nome, senha, true);
			repositorio.adicionar(individuo);
		} else if (!(individuo.getAdministrador()))
			individuo.setAdministrador(true);

		else
			throw new Exception("Esse índividuo já é um administrador!");

	}
	public static void criarGrupo(String nome) throws Exception {
		// localizar nome no repositorio
		// criar o grupo
		if (repositorio.localizarGrupo(nome) != null) {
			throw new Exception("Já existe um grupo com o nome " + nome);
		}
		Grupo novoGrupo = new Grupo(nome);
		repositorio.adicionar(novoGrupo);
		repositorio.salvarObjetos();}
		//repositorio.adicionar(new Grupo(nome));



	public static void inserirGrupo(String nomeindividuo, String nomegrupo) throws Exception {
		// localizar nomeindividuo no repositorio
		// localizar nomegrupo no repositorio
		// verificar se individuo nao esta no grupo
		// adicionar individuo com o grupo e vice-versa

		Individual participante = repositorio.localizarIndividual(nomeindividuo);
		if (participante == null)
			throw new Exception("Usuario não encontrado");

		Grupo grupo = repositorio.localizarGrupo(nomegrupo);
		if (grupo == null)
			throw new Exception("Grupo não encontrado");

		if (!grupo.adicionar(participante) || !participante.adicionar(grupo))
			throw new Exception(
					"O Índivíduo " + participante.getNome() + " já está presente no grupo: " + grupo.getNome());
		repositorio.salvarObjetos();
	}

	public static void removerGrupo(String nomeindividuo, String nomegrupo) throws Exception {
		// localizar nomeindividuo no repositorio
		// localizar nomegrupo no repositorio
		// verificar se individuo ja esta no grupo
		// remover individuo com o grupo e vice-versa

		Individual participante = repositorio.localizarIndividual(nomeindividuo);
		if (participante == null)
			throw new Exception("Usuario não encontrado");

		Grupo grupo = repositorio.localizarGrupo(nomegrupo);
		if (grupo == null)
			throw new Exception("Grupo não encontrado");

		if (!grupo.removerIndividuo(participante))
			throw new Exception(" Participante não encontrado: " + nomeindividuo);
		else
			participante.remover(grupo);
		repositorio.salvarObjetos();
	}

	public static void criarMensagem(String nomeemitente, String nomedestinatario, String texto) throws Exception {
		if (texto.isEmpty())
			throw new Exception("criar mensagem - texto vazio:");

		Individual emitente = repositorio.localizarIndividual(nomeemitente);
		if (emitente == null)
			throw new Exception("criar mensagem - emitente nao existe:" + nomeemitente);

		Participante destinatario = repositorio.localizarParticipante(nomedestinatario);
		if (destinatario == null)
			throw new Exception("criar mensagem - destinatario nao existe:" + nomeemitente);

		/* Foi uma ideia doida minha */
		int id = (int) Instant.now().toEpochMilli(); // Obtém o valor do timestamp em milissegundos
		Mensagem enviada = new Mensagem(id, emitente, destinatario, texto);

		if (destinatario instanceof Grupo) {

			if (emitente.localizarGrupo(destinatario.getNome()) == null)
				throw new Exception("criar mensagem - grupo nao permitido:" + nomedestinatario);

			/*
			 * O Emitente deve adicionar a mensagem enviada em sua caixa de enviados.
			 * O Grupo deve adicionar a mensagem enviada em sua caixa de recebidas.
			 * Em seguida, cada índividuo no grupo receberá a mensagem que foi enviada.
			 */
			emitente.adicionar(enviada);
			destinatario.adicionarRecebida(enviada);

			for (Participante participante : ((Grupo) destinatario).getIndividuos()) {
				participante.adicionarRecebida(enviada);

			}
		} else {
			/*
			 * O Emitente deve adicionar a mensagem enviada em sua caixa de enviados.
			 * O Destinatário deve adicionar a mensagem enviada em sua caixa de recebidas.
			 */
			emitente.adicionar(enviada);
			destinatario.adicionarRecebida(enviada);
		}

		// cont.
		// gerar id no repositorio para a mensagem
		// criar mensagem
		// adicionar mensagem ao emitente e destinatario
		// adicionar mensagem ao repositorio
		//
		// caso destinatario seja tipo Grupo então criar copias da mensagem, tendo o
		// grupo como emitente e cada membro do grupo como destinatario,
		// usando mesmo id e texto, e adicionar essas copias no repositorio

	}

	public static ArrayList<Mensagem> obterConversa(String nomeindividuo, String nomedestinatario) throws Exception {
		// localizar emitente no repositorio
		// localizar destinatario no repositorio
		// obter do emitente a lista enviadas
		// obter do emitente a lista recebidas

		// criar a lista conversa
		// Adicionar na conversa as mensagens da lista enviadas cujo destinatario é
		// igual ao parametro destinatario
		// Adicionar na conversa as mensagens da lista recebidas cujo emitente é igual
		// ao parametro destinatario
		// ordenar a lista conversa pelo id das mensagens
		// retornar a lista conversa
		Individual emitente = repositorio.localizarIndividual(nomeindividuo);
		if (emitente == null){
			throw new Exception("criar mensagem - emitente nao existe:" + nomeindividuo);
		}

		Participante destinatario = repositorio.localizarParticipante(nomedestinatario);
		if (destinatario == null){
			throw new Exception("criar mensagem - destinatario nao existe:" + nomedestinatario);
		}
		ArrayList<Mensagem> enviadas = emitente.getEnviadas();
		ArrayList<Mensagem> recebidas = emitente.getRecebidas();
		ArrayList<Mensagem> conversa = new ArrayList<>();
		if(enviadas.size()>0){
			for(Mensagem msg: enviadas){
			if(msg.getDesitnatario().getNome().equals(nomedestinatario)){
				conversa.add(msg);
			}}
		}
		
		if(recebidas.size()>0){
			for(Mensagem msg: recebidas){
			if(msg.getEmitente().getNome().equals(nomedestinatario)){
				conversa.add(msg);
			}
		}}
		//metodo temporario de organização, pq estou com preguiça, precisa criar um método novo dentro da classe 
		ArrayList<Mensagem> conversaOrganizada = new ArrayList<>();
		int sizeList = conversa.size();
			for(int i = 0; i < sizeList; i++){
				Mensagem minMsg = conversa.get(0);
				for(Mensagem msg : conversa){
					if(msg.getId()<minMsg.getId()){
						minMsg = msg;
					}}
			conversa.remove(minMsg);
			conversaOrganizada.add(minMsg);
			}
		return conversaOrganizada;
	}

	public static void apagarMensagem(String nomeindividuo, int id) throws Exception {
		Individual emitente = repositorio.localizarIndividual(nomeindividuo);
		if (emitente == null)
			throw new Exception("apagar mensagem - nome nao existe:" + nomeindividuo);

		Mensagem m = emitente.localizarEnviada(id);
		if (m == null)
			throw new Exception("apagar mensagem - mensagem nao pertence a este individuo:" + id);

		emitente.remover(m);
		Participante destinatario = m.getDestinatario();
		destinatario.removerRecebida(m);
		repositorio.remover(m);

		if (destinatario instanceof Grupo) {
			ArrayList<Mensagem> lista = destinatario.getEnviadas();
			lista.removeIf(new Predicate<Mensagem>() {
				@Override
				public boolean test(Mensagem t) {
					if (t.getId() == m.getId()) {
						t.getDestinatario().removerRecebida(t);
						repositorio.remover(t);
						return true; // apaga mensagem da lista
					} else
						return false;
				}
			});
		}
	}

	public static ArrayList<Mensagem> espionarMensagens(String nomeadministrador, String termo) throws Exception {
		// localizar individuo no repositorio
		// verificar se individuo é administrador
		// listar as mensagens que contem o termo no texto

		return null;
	}

	public static ArrayList<String> ausentes(String nomeadministrador) throws Exception {
		// localizar individuo no repositorio
		// verificar se individuo é administrador
		// listar os nomes dos participante que nao enviaram mensagens

		return null;
	}

	public static ArrayList<Mensagem> listarMensagens() {
		return repositorio.getMensagems();
	}

}