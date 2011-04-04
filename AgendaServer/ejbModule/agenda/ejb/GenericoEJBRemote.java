package agenda.ejb;


public interface GenericoEJBRemote<T> {
	public T salvar(T obj) throws Exception;
	public T remover(T obj) throws Exception;
	public T buscarPorId(Long id);
	
}
