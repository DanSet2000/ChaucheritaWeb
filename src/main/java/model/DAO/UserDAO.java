package model.DAO;
import model.entidades.User;

public interface UserDAO extends GenericDAO<User, Integer>{
	public User autorizar(String User, String password);
	public User getById(int id);
}
