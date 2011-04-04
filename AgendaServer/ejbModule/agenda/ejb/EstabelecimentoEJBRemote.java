package agenda.ejb;

import agenda.entidades.Estabelecimento;

public interface EstabelecimentoEJBRemote extends GenericoEJBRemote<Estabelecimento> {
	public boolean desbloquearEstabelecimento(Long codigoDeDesbloqueio, String email) throws Exception;
	public boolean verificarSeEmailExiste(String email);
	public boolean verificarSeEstabelecimentoExiste(Estabelecimento estabelecimento);
	public boolean verificarSeLoginExiste(String login);
}
