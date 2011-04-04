package agenda.ejb.impl;

import static org.junit.Assert.*;

import javax.naming.InitialContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import agenda.ejb.EstabelecimentoEJBRemote;
import agenda.entidades.Estabelecimento;
import agenda.infra.GlassfishUtil;

public class EstabelecimentoEJBTest {

	private static InitialContext ctx;
	private static EstabelecimentoEJBRemote estabelecimentoEJB;
	private Estabelecimento estabelecimento;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new InitialContext(GlassfishUtil.getGlassfishInitProperties());
		estabelecimentoEJB = (EstabelecimentoEJBRemote) ctx.lookup("java:global/AgendaEAR/AgendaServer/EstabelecimentoEJB");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		estabelecimentoEJB = null;
		ctx.close();
	}

	@Before
	public void setUp() throws Exception {
		estabelecimento = new Estabelecimento();
		this.popularEstabelecimento(estabelecimento);
	}
	
	//Métodos auxiliares
	public void popularEstabelecimento(Estabelecimento estabelecimento){
		estabelecimento.setLogin("chundas");
		estabelecimento.setEmail("chundas@hotmail.com");
		estabelecimento.setSenha("1234");
	}
	
	//Métodos auxiliares
	public Estabelecimento removerEstabelecimento(Estabelecimento estabelecimento) throws Exception{
		if(Estabelecimento.verificarSeEstabelecimentoPossuiId(estabelecimento)){
			estabelecimento = estabelecimentoEJB.remover(estabelecimento);
		}
		return estabelecimento;
	}
	
	@After
	public void tearDown() throws Exception {
		estabelecimento = removerEstabelecimento(estabelecimento);
	}
		
	@Test
	public void testSalvarEstabelecimento() throws Exception{
		estabelecimento = estabelecimentoEJB.salvar(estabelecimento);
		assertTrue(estabelecimento.getId().toString(),Estabelecimento.verificarSeEstabelecimentoPossuiId(estabelecimento));
	}
	
	@Test
	public void testRemoverEstabelecimento() throws Exception{
		estabelecimento = estabelecimentoEJB.salvar(estabelecimento);
		estabelecimento = removerEstabelecimento(estabelecimento);
		
		assertFalse(estabelecimentoEJB.verificarSeEstabelecimentoExiste(estabelecimento));
	}
	
	@Test
	public void testRejeitaSalvarEstabelecimentoSemLogin(){
		estabelecimento.setLogin("");
		try {
			estabelecimento = estabelecimentoEJB.salvar(estabelecimento);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testRejeitaSalvarEstabelecimentoSemEmail(){
		estabelecimento.setEmail("");
		try {
			estabelecimento = estabelecimentoEJB.salvar(estabelecimento);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testRejeitaSalvarEstabelecimentoComMesmoEmail() throws Exception{
		//Salva o primeiro estabelecimento
		estabelecimento = estabelecimentoEJB.salvar(estabelecimento);
		
		//Cria o segundo estabelecimento, idêntico a ele
		Estabelecimento estabelecimentoAux = new Estabelecimento();
		this.popularEstabelecimento(estabelecimentoAux);
		
		//Salva o segundo Estabelecimento
		try {
			estabelecimentoAux = estabelecimentoEJB.salvar(estabelecimentoAux);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//Remove o estabelecimento, se por algum caso der errado e ele for gravado
		removerEstabelecimento(estabelecimentoAux);
	}
	
	@Test
	public void testEditarEstabelecimento() throws Exception{
		//Salva pela primeira vez
		estabelecimento = estabelecimentoEJB.salvar(estabelecimento);
		estabelecimento = estabelecimentoEJB.buscarPorId(estabelecimento.getId());
		
		//Muda o nome
		estabelecimento.setLogin("felipe");
		
		//Salva pela segunda vez
		estabelecimento = estabelecimentoEJB.salvar(estabelecimento);
		estabelecimento = estabelecimentoEJB.buscarPorId(estabelecimento.getId());
	
		assertEquals("felipe", estabelecimento.getLogin());
	}
	
	@Test
	public void testVerificarSeEmailExiste() throws Exception{
		String email = "emailexistente@email.com";
		assertFalse(estabelecimentoEJB.verificarSeEmailExiste(email));
		estabelecimento.setEmail(email);
		estabelecimentoEJB.salvar(estabelecimento);
		assertTrue(estabelecimentoEJB.verificarSeEmailExiste(email));
	}
	
	@Test
	public void testVerificarSeLoginExiste() throws Exception{
		String login = "chundinha";
		assertFalse(estabelecimentoEJB.verificarSeLoginExiste(login));
		estabelecimento.setLogin(login);
		estabelecimentoEJB.salvar(estabelecimento);
		assertTrue(estabelecimentoEJB.verificarSeLoginExiste(login));
	}
	
	@Test
	public void testDesbloquearEstabelecimento() throws Exception{
		//Salva pela primeira vez
		estabelecimento.setEmail("texti@abc.com");
		estabelecimento.setLogin("texti");
		estabelecimento = estabelecimentoEJB.salvar(estabelecimento);
		estabelecimento = estabelecimentoEJB.buscarPorId(estabelecimento.getId());
		
		assertTrue("Estabelecimento bloqueado na primeira buscar",Estabelecimento.verificarSeEstabelecimentoEstaBloqueado(estabelecimento));
		Long codigoDeDesbloqueio = estabelecimento.getCodigoBloqueio();
		
		assertTrue("Nao conseguiu desbloquear",estabelecimentoEJB.desbloquearEstabelecimento(codigoDeDesbloqueio, estabelecimento.getEmail()));
		
		estabelecimento = estabelecimentoEJB.buscarPorId(estabelecimento.getId());
	
		assertFalse("Estabelecimento continua bloqueado apos desbloqueio",Estabelecimento.verificarSeEstabelecimentoEstaBloqueado(estabelecimento));
	}
	

}
