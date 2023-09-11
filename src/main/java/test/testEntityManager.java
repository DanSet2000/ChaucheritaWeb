package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.DAO.DAOFactory;
import model.entidades.Account;
import model.entidades.Category;
import model.entidades.Type;
import model.entidades.User;


public class testEntityManager {

	public static void main(String[] args) {
		
		insertarCategorias();
		insertarCuentas();
		insertarUsuarios();

		
	}


	private static void insertarUsuarios() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMiChaucherita");
		EntityManager em = emf.createEntityManager();
		
		List<Account> accounts = DAOFactory.getFactory().getAccountDAO().getAll();
//		tener la entity a insertar
		User p1 = new User("Jairo","jairo123", accounts);
		User p2 = new User("David","david123", accounts);
		User p3 = new User("Daniel","daniel123", accounts);
		User p4 = new User("Dorian","dorian123", accounts);

		
//		INSERTAR
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(p4);
		em.getTransaction().commit();
		
	}
	private static void insertarCuentas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMiChaucherita");
		EntityManager em = emf.createEntityManager();
	
		
//		tener la entity a insertar
		Account account1 = new Account("Banco Pichincha", 0.0);
		Account account2 = new Account("Banco Bolivariano", 0.0);
		Account account3 = new Account("Efectivo", 0.0);

		
//		INSERTAR
		em.getTransaction().begin();
		em.persist(account1);
		em.persist(account2);
		em.persist(account3);
		em.getTransaction().commit();
		
	}
	
	private static void insertarCategorias() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMiChaucherita");
		EntityManager em = emf.createEntityManager();
		
//		tener la entity a insertar
		Category cat1 = new Category("Auto", 0.0, Type.SPENT);
		Category cat2 = new Category("Casa", 0.0, Type.SPENT);
		Category cat3 = new Category("Comida", 0.0, Type.SPENT);
		Category cat4 = new Category("Deportes", 0.0, Type.SPENT);
		Category cat5 = new Category("Comunicaciones", 0.0, Type.SPENT);
		Category cat6 = new Category("Entretenimiento", 0.0, Type.SPENT);
		Category cat7 = new Category("Facturas", 0.0, Type.SPENT);
		Category cat8 = new Category("Higiene", 0.0, Type.SPENT);
		Category cat9 = new Category("Mascotas", 0.0, Type.SPENT);
		Category cat10 = new Category("Regalos", 0.0, Type.SPENT);
		Category cat11 = new Category("Restaurante", 0.0, Type.SPENT);
		Category cat12 = new Category("Ropa", 0.0, Type.SPENT);
		Category cat13 = new Category("Salud", 0.0, Type.SPENT);
		Category cat14 = new Category("Transporte", 0.0, Type.SPENT);
		
		Category cat15 = new Category("Ahorros", 0.0, Type.INCOME);
		Category cat16 = new Category("Deposito", 0.0, Type.INCOME);
		Category cat17 = new Category("Salario", 0.0, Type.INCOME);
		
		Category cat18 = new Category("Transferencia", 0.0, Type.TRANSFER);
		
//		INSERTAR
		em.getTransaction().begin();
		em.persist(cat1);
		em.persist(cat2);
		em.persist(cat3);
		em.persist(cat4);
		em.persist(cat5);
		em.persist(cat6);
		em.persist(cat7);
		em.persist(cat8);
		em.persist(cat9);
		em.persist(cat10);
		em.persist(cat11);
		em.persist(cat12);
		em.persist(cat13);
		em.persist(cat14);
		em.persist(cat15);
		em.persist(cat16);
		em.persist(cat17);
		em.persist(cat18);
		em.getTransaction().commit();
		
	}

}