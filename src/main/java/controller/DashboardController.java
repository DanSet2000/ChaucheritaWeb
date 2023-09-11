package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.DAOFactory;
import model.entidades.Account;
import model.entidades.Category;
import model.entidades.Move;
import model.entidades.Type;
import model.entidades.User;

@WebServlet("/DashboardController")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DashboardController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Siempre se Redirecciona
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Siempre se Redirecciona
		this.ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String ruta = (request.getParameter("ruta") == null) ? "inicio" : request.getParameter("ruta");

		switch (ruta) {
		case "inicio":
			this.inicio(request, response);
			break;
		case "dashboard":
			this.dashboard(request, response);
			break;
		case "gasto":
			this.gasto(request, response);
			break;
		case "ingreso":
			System.out.println("estamos en ingreso");
			this.ingreso(request, response);
			break;
		case "transferencia":
			this.transferencia(request, response);
			break;
		case "salir":
			this.salir(request, response);
			break;
		case "eliminarMovimiento":
			System.out.println("eliminarMovimiento");
			this.eliminarMovimiento(request, response);
			break;
		case "error":
			this.error(request, response);
			break;
//			C.U: Ver Movmientos
		case "verPorTodosMovimientos":
			System.out.println("estamos en movimientos 1");
			this.verPorTodosMovimientos(request, response);
			break;
		case "verPorCuenta":
			int cuentaID = Integer.parseInt(request.getParameter("cuentaID"));
			System.out.println(cuentaID);
			System.out.println("estamos en ver movimientos por Cuenta");
			this.verPorCuenta(request, response, cuentaID);

		case "verPorCategoria":
			int catID = Integer.parseInt(request.getParameter("catID"));
			System.out.println("estamos en ver movimientos por Categoria");
			this.verPorCategoria(request, response, catID);
		default:
			break;
		}
	}

	private void error(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.sendRedirect("jsp/moveError.jsp");
	}

	private void transferencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1.- Obtener datos que me envï¿½an en la solicitud
		String descripcion = request.getParameter("descripcion");
		String fecha = request.getParameter("fecha");
		String monto = request.getParameter("monto");
		Integer cuentaIdOrigen = Integer.parseInt(request.getParameter("origen"));
		Integer cuentaIdDestino = Integer.parseInt(request.getParameter("destino"));
		// 2.- Llamo al Modelo para obtener datos
		Date fechaFormatted = Date.valueOf(fecha);
		String montoSinSigno = monto.replaceAll("[^\\d.]", "");
		// Convertir la cadena a un valor double
		double montoDouble = Double.parseDouble(montoSinSigno);

		List<Category> categories = DAOFactory.getFactory().getCategoryDAO().getCategoryList(Type.TRANSFER);
		Category category = categories.get(0);
		Account accountO = DAOFactory.getFactory().getAccountDAO().getById(cuentaIdOrigen);
		Account accountD = DAOFactory.getFactory().getAccountDAO().getById(cuentaIdDestino);

		if (accountO.check(montoDouble)) {
			// Se resta el dinero de la cuenta de origen
			Move transferMove = new Move(fechaFormatted, montoDouble, descripcion, category, accountO, accountD);
			DAOFactory.getFactory().getMoveDAO().insertMove(transferMove);
			DAOFactory.getFactory().getAccountDAO().updateBalance(cuentaIdOrigen, -montoDouble);
			// Se aumenta el dinero en la cuenta de destino
			DAOFactory.getFactory().getAccountDAO().updateBalance(cuentaIdDestino, montoDouble);
			// 3.- Llamo a la Vista
			response.sendRedirect("DashboardController?ruta=dashboard");
		} else {
			System.out.println("NO SE PUEDE REALIAR LA TRANSFERENCIA");

			response.sendRedirect("DashboardController?ruta=error");
		}

	}

	private void inicio(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.- Obtener datos que me envï¿½an en la solicitud

		// 2.- Llamo al Modelo para obtener datos

		// 3.- Llamo a la Vista
		response.sendRedirect("jsp/login.jsp");
	}

	private void dashboard(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 1.- Obtener datos que me envian en la solicitud
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("ctaUser");
		String nameUser = user.getUsername();
		session.setAttribute("nameUser", nameUser);
		// java.util.Date utilDate = new java.util.Date();

		// 2.- Llamo al Modelo para obtener datos
		// Date date = new Date(utilDate.getTime());
		List<Account> accounts = DAOFactory.getFactory().getAccountDAO().getAll();
		List<Category> categoriesSpent = DAOFactory.getFactory().getCategoryDAO().getCategoryList(Type.SPENT);
		List<Category> categoriesIncome = DAOFactory.getFactory().getCategoryDAO().getCategoryList(Type.INCOME);

		Double income = DAOFactory.getFactory().getMoveDAO().getBalanceByType(Type.INCOME);
		Double discharge = DAOFactory.getFactory().getMoveDAO().getBalanceByType(Type.SPENT);
		Double balance = income - discharge;

		request.setAttribute("categoriasG", categoriesSpent);
		request.setAttribute("categoriasI", categoriesIncome);
		request.setAttribute("cuentas", accounts);
		request.setAttribute("balance", balance);
		request.setAttribute("income", income);
		request.setAttribute("discharge", discharge);

		// 3.- Llamo a la Vista
		request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);
	}

//	y especificamente con este metodo nos ahorramos el logoutController, aplicando asi la teoria del ruteador
	private void salir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect("jsp/login.jsp");
	}

	private void gasto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Integer catId = Integer.parseInt(request.getParameter("categoria"));
		String descripcion = request.getParameter("descripcion");
		String fecha = request.getParameter("fecha");
		String monto = request.getParameter("monto");
		Integer cuentaId = Integer.parseInt(request.getParameter("cuenta"));

		// 2.- Llamo al Modelo para obtener datos
		Date fechaFormatted = Date.valueOf(fecha);
		String montoSinSigno = monto.replaceAll("[^\\d.]", "");

		// Convertir la cadena a un valor double
		double montoDouble = Double.parseDouble(montoSinSigno);

		Account account = DAOFactory.getFactory().getAccountDAO().getById(cuentaId);
		Category category = DAOFactory.getFactory().getCategoryDAO().getById(catId);

		if (account.check(montoDouble)) {
			Move spentMove = new Move(fechaFormatted, montoDouble, descripcion, category, account, null);
			DAOFactory.getFactory().getMoveDAO().insertMove(spentMove);
			DAOFactory.getFactory().getAccountDAO().updateBalance(cuentaId, -montoDouble);
			DAOFactory.getFactory().getCategoryDAO().updateValue(catId, montoDouble);
			// 3.- Llamo a la Vista

			response.sendRedirect("DashboardController?ruta=dashboard");
		} else {
			System.out.println("NO SE PUEDE REALIAR EL GASTO");
			response.sendRedirect("DashboardController?ruta=error");
		}

	}

	private void ingreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer catId = Integer.parseInt(request.getParameter("categoria"));
		String descripcion = request.getParameter("descripcion");
		String fecha = request.getParameter("fecha");
		String monto = request.getParameter("monto");
		Integer cuentaId = Integer.parseInt(request.getParameter("cuenta"));

		// 2.- Llamo al Modelo para obtener datos
		Date fechaFormatted = Date.valueOf(fecha);
		String montoSinSigno = monto.replaceAll("[^\\d.]", "");

		// Convertir la cadena a un valor double
		double montoDouble = Double.parseDouble(montoSinSigno);
		Account account = DAOFactory.getFactory().getAccountDAO().getById(cuentaId);
		Category category = DAOFactory.getFactory().getCategoryDAO().getById(catId);
		Move incomeMove = new Move(fechaFormatted, montoDouble, descripcion, category, account, null);
		DAOFactory.getFactory().getMoveDAO().insertMove(incomeMove);
		DAOFactory.getFactory().getAccountDAO().updateBalance(cuentaId, montoDouble);
		DAOFactory.getFactory().getCategoryDAO().updateValue(catId, montoDouble);

		System.out.println("" + catId + descripcion + fecha + montoDouble + cuentaId);
		// 3.- Llamo a la Vista
		response.sendRedirect("DashboardController?ruta=dashboard");

	}

//	----------------------------------- VER MOVIMIENTOS ---------------------------------------------------
	private void verPorTodosMovimientos(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 1.- Obtener datos que me envï¿½an en la solicitud
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("ctaUser");
		String nameUser = user.getUsername();
		session.setAttribute("nameUser", nameUser);
		Date date = new Date(new java.util.Date().getTime()); // Inicializar con la fecha actual por defecto

		try {
			String fecha = request.getParameter("fecha");
			if (fecha != null && !fecha.isEmpty()) {
				date = Date.valueOf(fecha);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Manejo de la excepción (puedes personalizarlo)
		}
		// 2.- Llamo al Modelo para obtener datos
		ArrayList<Move> movimientos = DAOFactory.getFactory().getMoveDAO().getAllMovebyUser(date, user);

		// 3.- Llamo a la Vista enviando datos
		request.setAttribute("accountName", "Todas las Cuentas");
		request.setAttribute("verTipo", "Todas");
		request.setAttribute("user", user);
		request.setAttribute("date", date);
		request.setAttribute("movimientos", movimientos);
		request.getRequestDispatcher("jsp/moves.jsp").forward(request, response);

	}

	private void verPorCuenta(HttpServletRequest request, HttpServletResponse response, int cuentaID)
			throws IOException, ServletException {
		// 1.- Obtener datos que me env�an en la solicitud
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("ctaUser");
		String nameUser = user.getUsername();
		session.setAttribute("nameUser", nameUser);
		Date date = new Date(new java.util.Date().getTime()); // Inicializar con la fecha actual por defecto

		try {
			String fecha = request.getParameter("fecha");
			if (fecha != null && !fecha.isEmpty()) {
				date = Date.valueOf(fecha);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Manejo de la excepción (puedes personalizarlo)
		}

		// Este metodo me ayuda a obtener por Id una cuenta
		Account account = DAOFactory.getFactory().getAccountDAO().getById(cuentaID);
		System.out.println(account.getAccountName());

		// 2.- Llamo al Modelo para obtener datos
		ArrayList<Move> movimientos = DAOFactory.getFactory().getMoveDAO().filtrar(date, account);

		// 3.- Llamo a la Vista enviando datos
		request.setAttribute("user", user);
		request.setAttribute("cuentaID", cuentaID);
		request.setAttribute("verTipo", "Cuenta");
		request.setAttribute("accountName", account.getAccountName());
		request.setAttribute("movimientos", movimientos);
		request.setAttribute("date", date);
		request.getRequestDispatcher("jsp/moves.jsp").forward(request, response);
	}

	private void verPorCategoria(HttpServletRequest request, HttpServletResponse response, int catID)
			throws ServletException, IOException {
		// 1.- Obtener datos que me env�an en la solicitud
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("ctaUser");
		String nameUser = user.getUsername();
		session.setAttribute("nameUser", nameUser);
		Date date = new Date(new java.util.Date().getTime()); // Inicializar con la fecha actual por defecto

		try {
			String fecha = request.getParameter("fecha");
			if (fecha != null && !fecha.isEmpty()) {
				date = Date.valueOf(fecha);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Manejo de la excepción (puedes personalizarlo)
		}

		// Este metodo me ayuda a obtener por Id una cuenta
		Category category = DAOFactory.getFactory().getCategoryDAO().getById(catID);

		// 2.- Llamo al Modelo para obtener datos
		ArrayList<Move> movimientos = DAOFactory.getFactory().getMoveDAO().filtrar(date, category);

		// 3.- Llamo a la Vista enviando datos
		request.setAttribute("user", user);
		request.setAttribute("catID", catID);
		request.setAttribute("verTipo", "Cat");
		request.setAttribute("accountName", category.getCategoryName());
		request.setAttribute("movimientos", movimientos);
		request.setAttribute("date", date);
		request.getRequestDispatcher("jsp/moves.jsp").forward(request, response);

	}

	private void eliminarMovimiento(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 1.- Obtener datos que me env�an en la solicitud
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("ctaUser");
		String nameUser = user.getUsername();
		session.setAttribute("nameUser", nameUser);
		String verTipo = request.getParameter("verTipo");
		Integer moveId = Integer.parseInt(request.getParameter("movimientoId"));
		Double amount = Double.parseDouble(request.getParameter("amount"));
		Integer accountIdO = Integer.parseInt(request.getParameter("accountIdO"));
		Integer accountIdD = -1;
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
		try {
			accountIdD = Integer.parseInt(request.getParameter("accountIdD"));
		} catch (Exception e) {
			System.out.println("Id nulo");
		}

		// 2.- Llamo al Modelo para obtener datos
		DAOFactory.getFactory().getMoveDAO().deleteMove(moveId);
		Category category = DAOFactory.getFactory().getCategoryDAO().getById(categoryId);

		if (category.getType().equals(Type.INCOME)) {
			DAOFactory.getFactory().getCategoryDAO().updateValue(categoryId, -amount);
			DAOFactory.getFactory().getAccountDAO().updateBalance(accountIdO, -amount);
		} else if (category.getType().equals(Type.SPENT)) {
			DAOFactory.getFactory().getCategoryDAO().updateValue(categoryId, -amount);
			DAOFactory.getFactory().getAccountDAO().updateBalance(accountIdO, amount);
		} else if (category.getType().equals(Type.TRANSFER)) {
			DAOFactory.getFactory().getAccountDAO().updateBalance(accountIdO, amount);
			DAOFactory.getFactory().getAccountDAO().updateBalance(accountIdD, -amount);
		}

		System.out.println(verTipo);
		// 3.- Llamo a la Vista enviando datos
		if (verTipo.equals("Todas")) {
			request.getRequestDispatcher("DashboardController?ruta=verPorTodosMovimientos").forward(request, response);
		} else if (verTipo.equals("Cuenta")) {
			response.sendRedirect("DashboardController?ruta=verPorCuenta&cuentaID=" + accountIdO);
		} else if (verTipo.equals("Cat")) {
			response.sendRedirect("DashboardController?ruta=verPorCategoria&catID=" + categoryId);
		}
	}

}