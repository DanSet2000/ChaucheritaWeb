package model.jpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.entidades.User;
import model.DAO.UserDAO;

public class JPAUserDAO extends JPAGenericDAO<User, Integer> implements UserDAO{

	public JPAUserDAO() {
		super(User.class);
	}

	@Override
	public User autorizar(String User, String password) {
		String sentencia = "SELECT u from User u WHERE u.username= :username AND u.password= :password";
		Query query = em.createQuery(sentencia);
		query.setParameter("username", User);
		query.setParameter("password", password);
		try {
			User userAutorizado = (User) query.getSingleResult();
			return userAutorizado;
		} catch (NoResultException e) {
			return null;
		}
	}
	@Override
	public User getById(int id) {
		try {
			User user = (User) em.find(User.class, id);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

}