package huiswerknakijken.hu.DAO;

import java.util.List;

public interface DAOInterface<E> {
	public List<E> retrieveAll();
	public boolean delete(E obj);
	public boolean add(E obj);
	public boolean update(E obj);
	public E retrieve(String s);
	//Retrieve with a unique identifier? Always string? Which paramaters?
	
}
