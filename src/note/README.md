

# 

<div >

#coisas para fazer:


## regras de negócio:

<table width=800>
  <tr>
    <th >regra</th>
    <th>status</th>
  </tr>
  <tr>
    <td>1-Participantes possuem nome único, que não pode ser vazio</td>
    <td align=center><b>FEITO<b></td>
  </tr>
  <tr>
    <td>2-Mensagens possuem id único sequencial (1,2,3...), gerado pelo sistema, e data-hora do computador</td>
    <td align=center><b>FEITO<b></td>
  </tr>
  <tr>
    <td>3-Um indivíduo não pode ter senha vazia</td>
    <td align=center><b>FEITO<b></td>
  </tr>
  <tr>
    <td>4-Uma mensagem não pode ter texto vazio</td>
    <td align=center><b>FEITO<b></td>
  </tr>
  <tr>
    <td>5-Uma mensagem tem um emitente (Individual) e um destinatário (Participante) que podem ser iguais ou diferentes</td>
    <td align=center><b>FEITO<b></td>
  </tr>
  <tr>
    <td>6- Quando o destinatário de uma mensagem for um grupo, todos os indivíduos do grupo recebem a referida mensagem</td>
    <td align=center><b>FEITO<b></td>
  </tr>
  <tr>
    <td>7- Uma conversa entre um indivíduo e um participante (indivíduo ou grupo) é uma lista de mensagens, enviadas e recebidas entre eles, organizada em ordem cronológica</td>
    <td align=center><b>FEITO<b></td>
  </tr>
  <tr>
  	<td>8- Uma mensagem excluída do sistema deve ser removida do seu emitente e do seu destinatário e, se o destinatário for um grupo, ela deve ser removida por todos os indivíduos desse grupo</td>
  	<td align=center><b>fazer<b></td>
  </tr>
  <tr>
  	<td>9- Um administrador é um indivíduo que pode espionar as mensagens do sistema e obter outras informações secretas</td>
  	<td align=center><b>fazer<b></td>
  </tr>
  <tr>
   <td>10- Existe um administrador nato com nome=”admin”/senha=”admin”
   </td>
   <td align=center><b>fazer<b></td>
  </tr>
</table> 


## Métodos obrigatórios da classe Fachada

classe possuidora de todas as regras de negócio, todas são "public static"


<table width=900>
  <tr>
    <th width=200>metodo</th>
    <th width=500>descrição</th>
    <th width=100>status</th>
  </tr>
  
  <tr>
    <td>void criarIndividuo(nomeindivíduo, senha)</td>
    <td>Cria um indivíduo no repositório, caso inexista no repositório</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>boolean validarIndividuo (nomeindivíduo,senha) </td>
    <td>Retorna true se o indivíduo existir no repositório</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>void criarAdministrador(nomeadministrador, senha)</td>
    <td>Cria um administrador no repositório, caso inexista no repositório</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>void criarGrupo (nomegrupo)</td>
    <td>Cria um grupo no repositório, caso inexista no repositório</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>void inserirGrupo (nomeindivíduo, nomegrupo)</td>
    <td>Localiza o indivíduo e o grupo no repositório e cria o relacionamento entre eles</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>void removerGrupo (nomeindividuo, nomegrupo)</td>
    <td>Localiza o indivíduo e o grupo no repositório e remove o relacionamento entre eles</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>void criarMensagem (nomeindivíduo, nomedestinatario, texto)</td>
    <td>Localiza o indivíduo e o participante destinatário no repositório, cria a mensagem e a relaciona com eles dois</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>void obterConversa (nomeindivíduo, nomedestinatario)</td>
    <td>Localiza o indivíduo e o participante
destinatário no repositório e retorna todas as mensagens enviadas e recebidas entre eles, em
ordem cronológica</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>void apagarMensagem (nomeindivíduo, id)</td>
    <td>Localiza no repositório o indivíduo e o id da mensagem
emitida por ele, remove os relacionamentos entre mensagem, emitente e destinatário e exclui a mensagem do repositório</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>ArrayList(Mensagem)listarMensagensEnviadas(nomeindivíduo)</td>
    <td>Localiza no repositório o indivíduo e retorna as mensagens enviadas por ele</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>ArrayList(Mensagem)listarMensagensRecebidas(nomeparticipante)</td>
    <td>Localiza no repositório o participante e retorna
as mensagens recebidas por ele</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>ArrayList(String)listarIndividuos()</td>
    <td>Retorna o nome dos indivíduos do repositório, com o nome dos grupos relacionados (informar individuo ativo/não ativo e grupo ativo/não ativo)</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>ArrayList(String) listarGrupos()</td>
    <td>Retorna o nome dos grupos do repositório juntamente com o nome dos indivíduos relacionados (informar grupo ativo/não ativo e individuo ativo/não ativo)</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
  <tr>
    <td>ArrayList(Mensagem) espeionarMensagens (nomeadministrador, temo)</td>
    <td>Localiza no repositório o administrador e retorna as mensagens do sistema, contendo o termo fornecido (termo vazio retorna todas as mensagens do sistema)</td>
    <td align=center><b>fazer<b></td>
  </tr>
  
</table> 

## Considerações finais:


Não se pode alterar as assinaturas dos métodos da Fachada
<p>• Serão fornecidas classes de teste.
<p>• A chave do mapa participantes é o nome do participante
<p>• A chave do mapa mensagens é o id da mensagem
<p>• O método carregarDados() lê de arquivo texto todos os objetos do repositório e o método salvarDados() grava em arquivo texto todos os objetos do repositório, usando os arquivos:
<p> &nbsp;&nbsp;  o Os indivíduos são armazenados no arquivo individual.txt (nomeindividuo;senha;administrador)
<p>	&nbsp;&nbsp; o Os grupos são armazenados no arquivo grupo.txt (nomegrupo;p1;p2;...;pN) onde p1,p2,...,pN são os nomes dos indivíduos de cada grupo.
<p>	&nbsp;&nbsp; o As mensagens são armazenadas no arquivo mensagem.txt (id;texto,nomeemitente;nomedestinatário;datahora), onde datahora está no formato “dd/MM/yyyy HH:mm:ss”

</div>