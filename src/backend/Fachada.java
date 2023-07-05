package backend;

import java.util.ArrayList;

import model.Grupo;
import model.Individual;
import model.Mensagem;
import model.Participante;
import repository.Repositorio;

public class Fachada {
	public Fachada() {
		System.out.println("ok");
	}

	private static Repositorio repositorio = new Repositorio();

	/* Salva os objetos no repositório. */
	public static void salvarDados() {
		repositorio.salvarObjetos();
	};

	/* adivinha o que este metodo faz */
	public static void carregarDados() {
		repositorio.carregarObjetos();
	};

	/**
	 * Valida um indivíduo com base em seu nome e senha.
	 *
	 * @param nome  O nome do indivíduo
	 * @param senha A senha do indivíduo
	 * @return true se o indivíduo é válido, false caso contrário
	 */
	public static Individual validarIndividuo(String nome, String senha) {
		Individual usuario = localizarIndividual(nome);
		if (usuario == null) {
			return null;
		} else {
			if (usuario.equals(usuario)) {
				return usuario;
			} else {
				return null;
			}
		}

	}

	/**
	 * Localiza um indivíduo no repositório com base em seu nome.
	 *
	 * @param nome O nome do indivíduo a ser localizado
	 * @return O objeto Individual correspondente, ou null se não encontrado
	 */
	public static Individual localizarIndividual(String nome) {

		return repositorio.localizarIndividual(nome);
	}

	/**
	 * Localiza um grupo no repositório com base em seu nome.
	 *
	 * @param nome O nome do grupo a ser localizado
	 * @return O objeto Grupo correspondente, ou null se não encontrado
	 */
	public static Grupo localizarGrupo(String name) {
		return repositorio.localizarGrupo(name);
	}

	/**
	 * Retorna uma lista de todos os indivíduos no repositório.
	 *
	 * @return Uma lista contendo todos os objetos Individual no repositório
	 */
	public static ArrayList<Individual> listarIndividuos() {
		return repositorio.getIndividuos();
	}

	/**
	 * Retorna uma lista de todos os grupos no repositório.
	 *
	 * @return Uma lista contendo todos os objetos Grupo no repositório
	 */
	public static ArrayList<Grupo> listarGrupos() {
		return repositorio.getGrupos();
	}

	/**
	 * Retorna uma lista de todas as mensagens enviadas por um determinado
	 * indivíduo.
	 *
	 * @param nome O nome do indivíduo
	 * @return Uma lista contendo todas as mensagens enviadas pelo indivíduo
	 */
	public static ArrayList<Mensagem> listarMensagensEnviadas(String nome) throws Exception {
		Individual ind = repositorio.localizarIndividual(nome);
		if (ind == null)
			throw new Exception("listar  mensagens enviadas - nome nao existe:" + nome);

		return ind.getEnviadas();
	}

	/**
	 * Retorna uma lista de todas as mensagens recebidas por um determinado
	 * indivíduo.
	 *
	 * @param nome O nome do indivíduo
	 * @return Uma lista contendo todas as mensagens recebidas pelo indivíduo
	 */
	public static ArrayList<Mensagem> listarMensagensRecebidas(String nome) throws Exception {
		Individual ind = repositorio.localizarIndividual(nome);
		if (ind == null)
			throw new Exception("listar  mensagens recebidas - nome nao existe:" + nome);

		return ind.getRecebidas();

	}

	/**
	 * 
	 * Cria um indivíduo no repositório.
	 * 
	 * @param nome  O nome do indivíduo.
	 * 
	 * @param senha A senha do indivíduo.
	 * 
	 * @throws Exception Se o nome ou a senha estiverem vazios, ou se o nome já
	 *                   existir no repositório.
	 */
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

	/**
	 * 
	 * Cria um grupo no repositório.
	 * 
	 * @param nome O nome do grupo.
	 * @throws Exception Se já existir um grupo com o mesmo nome.
	 */

	public static void criarGrupo(String nome) throws Exception {
		// localizar nome no repositorio
		// criar o grupo
		if (repositorio.localizarGrupo(nome) != null) {
			throw new Exception("Já existe um grupo com o nome " + nome);
		}
		Grupo novoGrupo = new Grupo(nome);
		repositorio.adicionar(novoGrupo);
		repositorio.salvarObjetos();
	}

	/**
	 * 
	 * Insere um indivíduo em um grupo.
	 * 
	 * @param nomeindividuo O nome do indivíduo.
	 * @param nomegrupo     O nome do grupo.
	 * @throws Exception Se o usuário ou o grupo não forem encontrados, ou se o
	 *                   indivíduo já estiver no grupo.
	 */
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

		if (!grupo.adicionar(participante) && !participante.adicionar(grupo))
			throw new Exception(
					"O Índivíduo " + participante.getNome() + " já está presente no grupo: " + grupo.getNome());
		participante.adicionar(grupo);
		repositorio.salvarObjetos();
	}

	/**
	 * Remove o indivíduo de um grupo
	 * 
	 * @param nomeindividuo
	 * @param nomegrupo
	 * @throws Exception - se não achar o indívidio ou o grupo
	 */
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

		if (grupo.getIndividuos().size() == 0)
			repositorio.remover(grupo);
		/* repositorio.salvarObjetos(); */
	}

	/**
	 * Cria uma nova mensagem e a adiciona ao repositório.
	 *
	 * @param nomeemitente     O nome do remetente.
	 * @param nomedestinatario O nome do destinatário.
	 * @param texto            O texto da mensagem.
	 * @throws Exception Se ocorrer um erro ao criar a mensagem.
	 */
	public static void criarMensagem(String nomeemitente, String nomedestinatario, String texto) throws Exception {

		if (texto.isEmpty())
			throw new Exception("criar mensagem - texto vazio:");

		Individual emitente = repositorio.localizarIndividual(nomeemitente);
		if (emitente == null)
			throw new Exception("criar mensagem - emitente nao existe:" + nomeemitente);

		Participante destinatario = repositorio.localizarParticipante(nomedestinatario);
		if (destinatario == null)
			throw new Exception("criar mensagem - destinatario nao existe:" + nomeemitente);

		ArrayList<Mensagem> todasMensagens = repositorio.getMensagems();
		int id = (todasMensagens.size() == 0) ? 1 : todasMensagens.get(todasMensagens.size() - 1).getId() + 1;

		Mensagem enviada = new Mensagem(id, emitente, destinatario, texto);

		emitente.adicionarEnviada(enviada);
		destinatario.adicionarRecebida(enviada);
		repositorio.adicionar(enviada);

		/*
		 * O Emitente deve adicionar a mensagem enviada em sua caixa de enviados.
		 * O Destinatário deve adicionar a mensagem enviada em sua caixa de recebidas.
		 */

		if (destinatario instanceof Grupo) {

			if (emitente.localizarGrupo(destinatario.getNome()) == null)
				throw new Exception("criar mensagem - grupo nao permitido:" + nomedestinatario);

			for (Participante p : ((Grupo) destinatario).getIndividuos()) {

				if (!p.equals(emitente)) {
					String newtexto = emitente.getNome() + " / " + texto;

					/* O grupo é o novo emitente da mensagem */
					Mensagem copia = new Mensagem(id, destinatario, p, newtexto);
					p.adicionarRecebida(copia);
					destinatario.adicionarEnviada(copia);
					repositorio.adicionar(copia);

				}
				/*
				 * else {
				 * Mensagem original = new Mensagem(id, destinatario, p, texto);
				 * destinatario.adicionarRecebida(original);
				 * emitente.adicionarEnviada(original);
				 * repositorio.adicionar(original);
				 * 
				 * }
				 */
			}
		}
		repositorio.salvarObjetos();

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

	/**
	 * Recupera uma conversa entre dois participantes.
	 *
	 * @param nomeindividuo    O nome do remetente.
	 * @param nomedestinatario O nome do destinatário.
	 * @return A conversa entre o remetente e o destinatário.
	 * @throws Exception Se ocorrer um erro ao recuperar a conversa.
	 */
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
		if (emitente == null) {
			throw new Exception("criar mensagem - emitente nao existe:" + nomeindividuo);
		}

		Participante destinatario = repositorio.localizarParticipante(nomedestinatario);
		if (destinatario == null) {
			throw new Exception("criar mensagem - destinatario nao existe:" + nomedestinatario);
		}

		System.out.println(emitente.getRecebidas());
		System.out.println(emitente.getEnviadas());
		ArrayList<Mensagem> mensagensEnviadas = emitente.getEnviadas();
		ArrayList<Mensagem> mensagensRecebidas = emitente.getRecebidas();
		ArrayList<Mensagem> conversa = new ArrayList<>();
		// ArrayList<Mensagem> conversaOrganizada = new ArrayList<>();

		int indexEnviada = 0;
		int indexRecebida = 0;

		while (conversa.size() < (mensagensEnviadas.size() + mensagensRecebidas.size())) {

			// Checamos se o index não irá extrapolar o tamanho dos array
			if (indexEnviada >= mensagensEnviadas.size()) {
				conversa.addAll(mensagensRecebidas.subList(indexRecebida, mensagensRecebidas.size()));

			} else if (indexRecebida >= mensagensRecebidas.size()) {
				conversa.addAll(mensagensEnviadas.subList(indexEnviada, mensagensEnviadas.size()));

			} else {
				// Se não extrapolar, coletamos as mensagens que iremos comparar
				Mensagem enviada = mensagensEnviadas.get(indexEnviada);
				Mensagem recebida = mensagensRecebidas.get(indexRecebida);

				// caso as duas coleções ainda possuam mensagens.
				// Checa se a mensagem enviada é anterior a mensagem recebida naquela conversa
				// pelo ID.

				if (enviada.getId() < recebida.getId()) {
					conversa.add(enviada);
					indexEnviada += 1;
				} else {
					conversa.add(recebida);
					indexRecebida += 1;
				}
			}

		}
		return conversa;
		/*
		 * if (mensagensEnviadas.size() > 0) {
		 * for (Mensagem msg : mensagensEnviadas) {
		 * if (msg.getDestinatario().getNome().equals(nomedestinatario)) {
		 * conversa.add(msg);
		 * }
		 * }
		 * }
		 * 
		 * if (mensagensRecebidas.size() > 0) {
		 * for (Mensagem msg : mensagensRecebidas) {
		 * if (msg.getEmitente().getNome().equals(nomedestinatario)) {
		 * conversa.add(msg);
		 * }
		 * }
		 * }
		 * // metodo temporario de organização, pq estou com preguiça, precisa criar um
		 * // método novo dentro da classe
		 * int sizeList = conversa.size();
		 * for (int i = 0; i < sizeList; i++) {
		 * Mensagem minMsg = conversa.get(0);
		 * for (Mensagem msg : conversa) {
		 * if (msg.getId() < minMsg.getId()) {
		 * minMsg = msg;
		 * }
		 * }
		 * conversa.remove(minMsg);
		 * conversaOrganizada.add(minMsg);
		 * }
		 * System.out.println(conversaOrganizada);
		 * 
		 * return conversaOrganizada;
		 */
	}

	/**
	 * Exclui uma mensagem do repositório.
	 *
	 * @param nomeindividuo O nome do remetente.
	 * @param id            O ID da mensagem a ser excluída.
	 * @throws Exception Se ocorrer um erro ao excluir a mensagem.
	 */
	public static void apagarMensagem(String nomeindividuo, int id) throws Exception {

		Individual emitente = repositorio.localizarIndividual(nomeindividuo);
		if (emitente == null)
			throw new Exception("apagar mensagem - nome nao existe:" + nomeindividuo);

		Mensagem m = emitente.localizarEnviada(id);
		if (m == null)
			throw new Exception("apagar mensagem - mensagem nao pertence a este individuo:" + id);

		Participante destinatario = m.getDestinatario();

		emitente.removerEnviada(m.getId());
		destinatario.removerRecebida(m.getId());
		repositorio.remover(m);
		if (destinatario instanceof Grupo) {
			for (Participante p : ((Grupo) destinatario).getIndividuos()) {
				if (!p.equals(emitente)) {
					Mensagem clone = p.removerRecebida(id);
					repositorio.remover(clone);
				}
			}
			destinatario.removerEnviada(id);
		}

		Fachada.salvarDados();
	}

	/**
	 * Procura por mensagens que contenham um termo específico.
	 *
	 * @param nomeadministrador O nome do administrador.
	 * @param termo             O termo de busca.
	 * @return A lista de mensagens que contêm o termo de busca.
	 * @throws Exception Se ocorrer um erro ao procurar por mensagens.
	 */
	public static ArrayList<Mensagem> espionarMensagens(String nomeadministrador, String termo) throws Exception {

		Individual adm = repositorio.localizarIndividual(nomeadministrador);

		if (!adm.getAdministrador())
			throw new Exception("Usuário " + nomeadministrador + " não é administrador");

		ArrayList<Mensagem> mensagemEncontrada = new ArrayList<>();

		for (Mensagem m : repositorio.getMensagems()) {

			if (m.getTexto().contains(termo))
				mensagemEncontrada.add(m);
		}
		return mensagemEncontrada;
	}

	/**
	 * Retorna todos os individuos que não enviaram mensagens no <b>sistema</b>
	 * 
	 * @param nomeadministrador
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<String> ausentes(String nomeadministrador) throws Exception {
		// localizar individuo no repositorio
		// verificar se individuo é administrador
		// listar os nomes dos participante que nao enviaram mensagens
		/*
		 * Minha solução:
		 * Adicionamos todos os individuos do sistema em um arrayList;
		 * caso seja encontrado uma mensagem que contenha o nome do indivíduo, nós o
		 * removemos do arrayList;
		 * No final, retornamos o nome de todos os Individuos que sobraram nesse array.
		 */

		if (!repositorio.localizarIndividual(nomeadministrador).getAdministrador())
			throw new Exception("Usuário " + nomeadministrador + " não é administrador");

		ArrayList<Individual> participantesAusentes = new ArrayList<>();
		participantesAusentes.addAll(repositorio.getIndividuos());

		ArrayList<Mensagem> todasMensagens = repositorio.getMensagems();

		ArrayList<String> ausentesNome = new ArrayList<>();

		for (Mensagem m : todasMensagens) {

			Participante emitente = m.getEmitente();

			if (participantesAusentes.contains(emitente))
				participantesAusentes.remove(emitente);
		}

		for (Individual ind : participantesAusentes) {
			ausentesNome.add(ind.getNome());
		}
		return ausentesNome;
	}

	/**
	 * Retorna todas as mensagens no repositório.
	 *
	 * @return A lista de todas as mensagens no repositório.
	 */
	public static ArrayList<Mensagem> listarMensagens() {
		return repositorio.getMensagems();
	}

}