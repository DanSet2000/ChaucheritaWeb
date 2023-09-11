package model.jpa;

import model.DAO.AccountDAO;
import model.DAO.CategoryDAO;
import model.DAO.DAOFactory;
import model.DAO.MoveDAO;
import model.DAO.UserDAO;

public class JPADAOFactory extends DAOFactory{

	@Override
	public UserDAO getUserDAO() {
		return new JPAUserDAO();
	}

	@Override
	public AccountDAO getAccountDAO() {
		return new JPAAccountDAO();
	}
	
	@Override
	public CategoryDAO getCategoryDAO() {
		return new JPACategoryDAO();
	}
	
	@Override
	public MoveDAO getMoveDAO() {
		return new JPAMoveDAO();
	}

}