package model.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.DAO.GenericDAO;

public class JPAGenericDAO<T, ID> implements GenericDAO<T, ID>{

	private Class<T> persistenceClass;
	protected EntityManager em;
	
	public JPAGenericDAO(Class<T> clasePersistente) {
		this.persistenceClass = clasePersistente;
		this.em = Persistence.createEntityManagerFactory("jpaMiChaucherita").createEntityManager();
	}

	@Override
	public T getById(ID id) {
		return em.find(persistenceClass, id);
	}

	@Override
	public List<T> getAll() {
		return null;
	}

	@Override
	public void create(T entity) {
		 em.getTransaction().begin();
		 try {
			 em.persist(entity);
			 em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("error de insercion");
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		 try {
			 em.merge(entity);
			 em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("error de actualizacion");
			em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		 try {
			 em.remove(entity);
			 em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("error de eliminacion");
			em.getTransaction().rollback();
		}
	}

	@Override
	public void deleteById(ID id) {
		T entity = this.getById(id);
		if(entity != null) {
			this.delete(entity);
		}
	}

}
