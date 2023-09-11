package model.jpa;

import java.util.List;

import javax.persistence.Query;

import model.DAO.CategoryDAO;
import model.entidades.Category;
import model.entidades.Type;

public class JPACategoryDAO extends JPAGenericDAO<Category, Integer> implements CategoryDAO{

	public JPACategoryDAO() {
		super(Category.class);
	}

	@Override
	public Category getById(int id) {
		Category category = em.find(Category.class, id);		
		return category;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoryList(Type type) {
		Query q = em.createQuery("SELECT c FROM Category c WHERE c.type= :type");
		q.setParameter("type", type);
		return q.getResultList();
	}

	@Override
	public void updateValue(int id, double amount) {
		Category category = em.find(Category.class, id);
		double value = category.getValue();
		category.setValue(amount + value);
		em.getTransaction().begin();
		em.merge(category);
		em.getTransaction().commit();	
	}

}
