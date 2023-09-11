package resources;

import java.sql.Date;
import java.util.ArrayList;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.DAO.DAOFactory;
import model.entidades.Account;
import model.entidades.Category;
import model.entidades.Move;
import model.entidades.Type;
import model.entidades.User;

@Path("/move")
public class MoveResource {

	@GET
	@Path("/byUserId")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Move> getAllMovebyUserId(@QueryParam("date") String date, @QueryParam("id") int id) {

		User user = DAOFactory.getFactory().getUserDAO().getById(id);
		return DAOFactory.getFactory().getMoveDAO().getAllMovebyUser(Date.valueOf(date), user);
	}

	@GET
	@Path("/byAccountId")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Move> getAllMovebyAccounId(@QueryParam("date") String date, @QueryParam("id") int id) {

		Account account = DAOFactory.getFactory().getAccountDAO().getById(id);
		return DAOFactory.getFactory().getMoveDAO().filtrar(Date.valueOf(date), account);
	}

	@GET
	@Path("/byCategoryId")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Move> getAllMovebyCategoryId(@QueryParam("date") String date, @QueryParam("id") int id) {

		Category category = DAOFactory.getFactory().getCategoryDAO().getById(id);
		return DAOFactory.getFactory().getMoveDAO().filtrar(Date.valueOf(date), category);
	}

	@GET
	@Path("/balanceByType")
	@Produces(MediaType.APPLICATION_JSON)
	public Double getBalanceByType(@QueryParam("type") Type categoryType) {

		return DAOFactory.getFactory().getMoveDAO().getBalanceByType(categoryType);
	}

	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public void insertMove(@QueryParam("date") String date, @QueryParam("amount") double amount,
			@QueryParam("des") String description, @QueryParam("cId") int categoryId,
			@QueryParam("aoId") int accountOid, @QueryParam("adId") int accountDid) {
		Category category = DAOFactory.getFactory().getCategoryDAO().getById(categoryId);
		Account accountO = DAOFactory.getFactory().getAccountDAO().getById(accountOid);
		Account accountD = null;
		if (accountDid != -1) {
			accountD = DAOFactory.getFactory().getAccountDAO().getById(accountDid);
		}
		Move move = new Move(Date.valueOf(date), amount, description, category, accountO, accountD);
		DAOFactory.getFactory().getMoveDAO().insertMove(move);
	}

	@DELETE
	@Path("/deleteById")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteMove(@QueryParam("id") int id) {
		DAOFactory.getFactory().getMoveDAO().deleteMove(id);
	}
}
