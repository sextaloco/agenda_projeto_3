package agenda.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import agenda.ejb.EstabelecimentoEJBRemote;
import agenda.entidades.Estabelecimento;

@ManagedBean
@SessionScoped
public class EstabelecimentoController {

	public static final String PAGINA_FORM = "/views/estabelecimento/cadastro/cadastroEstabelecimento.xhtml";
	public static final String PAGINA_DESBLOQUEIO = "/views/estabelecimento/cadastro/desbloqueioEstabelecimento.xhtml";
	
	
	@EJB
	private EstabelecimentoEJBRemote estabelecimentoEJB;

	private List<Estabelecimento> estabelecimentos;
	
	private Estabelecimento estabelecimento;
	
	private Estabelecimento estabelecimentoSelecionado;
	
	private String emailDesbloqueio;
	
	private Long codigoDesbloqueio;
	
	@PostConstruct
	public void init(){
//		atualizarLista();
		estabelecimento = new Estabelecimento();
	}
	
	
//	public String doListar(){
//		atualizarLista();
//		return PAGINA_LISTA;
//	}
	
	
	public String doDesbloquear() throws ValidatorException, Exception{
		boolean resposta = false;
		if(emailDesbloqueio.trim().length()>0&&codigoDesbloqueio>0){
			resposta = estabelecimentoEJB.desbloquearEstabelecimento(codigoDesbloqueio, emailDesbloqueio);
		}
		if(!resposta){
			FacesMessage message = new FacesMessage("email e/ou c�digo de desbloqueio inv�lido(s)");
			FacesContext.getCurrentInstance().addMessage("emailField",message);
			return PAGINA_DESBLOQUEIO;
		}
		return PAGINA_FORM;
	}
	
	public String doNovo(){
		estabelecimento = new Estabelecimento();
		return PAGINA_FORM;
	}
	
	public String doSalvar() throws Exception{
		estabelecimentoEJB.salvar(estabelecimento);
		return PAGINA_DESBLOQUEIO;
	}
	
	public String doEditar(Estabelecimento estabelecimento){
		this.estabelecimento = estabelecimento;
		return PAGINA_FORM;
	}
	
	public String doEditar(){
		if(estabelecimentoSelecionado!=null){
			doEditar(estabelecimentoSelecionado);
			return PAGINA_FORM;
		}else{
			return null;
		}
	}
	
//	public void atualizarLista(){
//		estabelecimentos = estabelecimentoEJB.buscarTodos();
//	}
	
	//Getters e Setters

	public List<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}
	public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}
	public EstabelecimentoEJBRemote getEstabelecimentoEJB() {
		return estabelecimentoEJB;
	}
	public void setEstabelecimentoEJB(EstabelecimentoEJBRemote estabelecimentoEJB) {
		this.estabelecimentoEJB = estabelecimentoEJB;
	}
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}
	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Estabelecimento getEstabelecimentoSelecionado() {
		return estabelecimentoSelecionado;
	}

	public void setEstabelecimentoSelecionado(Estabelecimento estabelecimentoSelecionado) {
		this.estabelecimentoSelecionado = estabelecimentoSelecionado;
	}

	public String getEmailDesbloqueio() {
		return emailDesbloqueio;
	}

	public void setEmailDesbloqueio(String emailDesbloqueio) {
		this.emailDesbloqueio = emailDesbloqueio;
	}


	public Long getCodigoDesbloqueio() {
		return codigoDesbloqueio;
	}


	public void setCodigoDesbloqueio(Long codigoDesbloqueio) {
		this.codigoDesbloqueio = codigoDesbloqueio;
	}



	
	
}
