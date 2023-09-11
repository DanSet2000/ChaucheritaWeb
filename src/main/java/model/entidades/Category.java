package model.entidades;

import java.io.Serializable;

import javax.persistence.*;

//import org.eclipse.jdt.internal.compiler.ast.ThisReference;



@Entity
@Table(name = "category")
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
//	------------------ propiedades/Variables Privadas	-----------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "value")
	private Double value;
	@Column(name = "type")
	private Type type;
	
//	------------------------ BUILDER ------------------------
	public Category() {
		
	}
	public Category( String categoryName, Double value, Type type) {
		
		this.categoryName = categoryName;
		this.value = value;
		this.type = type;
	}
	
//	------------------------ SET && GET ------------------------ 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
}
