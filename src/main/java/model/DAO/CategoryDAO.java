package model.DAO;


import java.util.List;

import model.entidades.Category;
import model.entidades.Type;

public interface CategoryDAO {
	public Category getById(int id);
	public List<Category> getCategoryList(Type type);
	public void updateValue(int id, double amount);
}
