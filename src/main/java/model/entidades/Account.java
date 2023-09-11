package model.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "Account")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
//	------------------------ Variables Privadas	------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "account_name")
	private String accountName;
	@Column(name = "balance")
	private Double balance;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "user")
	@JsonBackReference
	private User user;

//	------------------------ BUILDER ------------------------
	public Account() {
	}

	public Account(String accountName, double balance) {
		this.accountName = accountName;
		this.balance = balance;
	}

	public Account(int id, String accountName, double balance, User user) {
		this.id = id;
		this.accountName = accountName;
		this.balance = balance;
		this.user = user;
	}

//	------------------------ SET && GET ------------------------ 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	------------------------ Methodes || Business Rules ------------------------	
	public Boolean check(Double amount) {
		if (amount > this.balance) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountName=" + accountName + ", balance=" + balance + ", user=" + user + "]";
	}
}
