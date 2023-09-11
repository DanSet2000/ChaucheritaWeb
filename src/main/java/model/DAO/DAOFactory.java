package model.DAO;

import model.jpa.JPADAOFactory;

public abstract class DAOFactory {
	protected static DAOFactory factory = new JPADAOFactory(); //Inyecci�n de dependencias
	
	public static DAOFactory getFactory() {
		return factory;
	}
	
	public abstract UserDAO getUserDAO();
	public abstract AccountDAO getAccountDAO();
	public abstract CategoryDAO getCategoryDAO() ;
	public abstract MoveDAO getMoveDAO();
}
