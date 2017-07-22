package dao;

import beans.Bean;

public abstract class ObjectDAO implements Bean {
	protected DAOFactory daoFactory;

	public ObjectDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public abstract void create(Bean bean) throws DAOException;
	public abstract Bean read(Long id) throws DAOException;
	public abstract void update(Bean bean) throws DAOException;
	public abstract void delete(Bean bean) throws DAOException;
}
