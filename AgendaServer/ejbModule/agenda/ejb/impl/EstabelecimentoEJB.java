package agenda.ejb.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agenda.ejb.EstabelecimentoEJBRemote;
import agenda.entidades.Estabelecimento;

@Stateless
@Remote(EstabelecimentoEJBRemote.class)
public class EstabelecimentoEJB implements EstabelecimentoEJBRemote, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="agendaPU")
	public EntityManager em;
	
	@Override
	public Estabelecimento salvar(Estabelecimento obj) throws Exception {
		if(Estabelecimento.verificarSeEstabelecimentoPossuiId(obj)){
			obj = em.merge(obj);
		}else{
			em.persist(obj);
		}
		return obj;
	}

	@Override
	public Estabelecimento remover(Estabelecimento obj) throws Exception {
		em.remove(em.merge(obj));
		return obj;
	}

	@Override
	public Estabelecimento buscarPorId(Long id) {
		return em.find(Estabelecimento.class, id);
	}

	@Override
	public boolean desbloquearEstabelecimento(Long codigoDeDesbloqueio,
			String email) throws Exception {
		//Faz uma busca pelo estabelecimento através do código
		Query query = em.createNamedQuery("Estabelecimento.buscarEstabelecimentoPorEmail");
		query.setParameter("email", email);
		
		@SuppressWarnings("unchecked")
		List<Estabelecimento> lista = query.getResultList();
		if(lista==null || lista.isEmpty()){
			return false;
		}
		Estabelecimento estabelecimento = lista.get(0);
		
		//Verifica se o codigo do desbloqueio confere
		if(estabelecimento.getCodigoBloqueio().equals(codigoDeDesbloqueio)){
			//Desbloqueia o estabelecimento e salva
			estabelecimento.desbloquearEstabelecimento();
			this.salvar(estabelecimento);
			return true;
		}
		return false;
		
	}

	@Override
	public boolean verificarSeEmailExiste(String email) {
		Query query = em.createNamedQuery("Estabelecimento.verificarSeEmailExiste");
		query.setParameter("email", email);
		Long resultado = (Long) query.getSingleResult();
		if(resultado>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean verificarSeEstabelecimentoExiste(
			Estabelecimento estabelecimento) {
		if(!Estabelecimento.verificarSeEstabelecimentoPossuiId(estabelecimento)){
			return false;
		}
		
		if(this.buscarPorId(estabelecimento.getId())!=null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean verificarSeLoginExiste(String login) {
		Query query = em.createNamedQuery("Estabelecimento.verificarSeLoginExiste");
		query.setParameter("login", login);
		Long resultado = (Long) query.getSingleResult();
		if(resultado>0){
			return true;
		}else{
			return false;
		}
	}

}
