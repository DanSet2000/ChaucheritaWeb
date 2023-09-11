package model.entidades;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "move")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Move implements Serializable {
	
	private static final long serialVersionUID = 1L;
//	------------------ propiedades/Variables Privadas	-----------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "date")
	private Date date;
	@Column(name = "description")
	private String description;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "accountO")
	private Account accountO;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "accountD")
	private Account accountD;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "category")
	private Category category;
	
//	------------------------ BUILDER ------------------------
	public  Move() {
		
	}

	public Move(Date date, double amount, String description, Category category, Account account, Account accountD) {
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.category = category;
		this.accountO = account;
		this.accountD = accountD;
	}

//	------------------------ SET && GET ------------------------ 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Account getAccountO() {
		return accountO;
	}

	public void setAccountO(Account accountO) {
		this.accountO = accountO;
	}
	
	public Account getAccountD() {
		return accountD;
	}

	public void setAccountD(Account accountD) {
		this.accountD = accountD;
	}

//	------------------------ Methodes || Business Rules ------------------------
	@Override
	public String toString() {
		return "Category=" + category.getCategoryName() + "\n"+ "amount=" + amount + ", description=" + description+", date=" + date  
				+ ", account=" + accountO + "]";
	}

	

}
