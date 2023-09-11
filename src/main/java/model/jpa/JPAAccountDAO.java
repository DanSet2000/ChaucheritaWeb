package model.jpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.DAO.AccountDAO;
import model.entidades.Account;
import model.entidades.User;

public class JPAAccountDAO  extends JPAGenericDAO<Account, Integer> implements AccountDAO{

	public JPAAccountDAO() {
		super(Account.class);
	}

	@Override
	public Account getById(int id) {
		try {
			Account account = (Account) em.find(Account.class, id);
			return account;
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAll(){
		Query q = em.createQuery("SELECT a FROM Account a");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void updateBalance(int id, double amount) {
		Account account = em.find(Account.class, id);
		double balance = account.getBalance();
		account.setBalance(balance + amount);
		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualizarUsuarioCuentas(User user) {
		Query q = em.createQuery("SELECT a FROM Account a");
		List<Account> accounts = q.getResultList();
		em.getTransaction().begin();
		for (Account account : accounts) {
			account.setUser(user);
			em.merge(account);
		}
		em.getTransaction().commit();		
	}

}
