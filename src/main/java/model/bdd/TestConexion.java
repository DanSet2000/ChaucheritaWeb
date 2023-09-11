package model.bdd;

import java.sql.Connection;

public class TestConexion {

	public static void main(String[] args) {
		
//		toda la parte de la prueba de conexion
		Connection cnn = BddConeccion.getConexion();
//		si es nulo vale
		if(cnn != null) {
			System.out.println("Tenemos conexion");
		}else {
			System.out.println("Conexion fallida");
		}

	}

}