package model.DAO;

import java.util.List;

import model.entidades.Account;
import model.entidades.User;

public interface AccountDAO {
	
	public Account getById(int id);
	public List<Account> getAll();
	public void updateBalance(int id, double amount);
	public void actualizarUsuarioCuentas(User user);
	
}

