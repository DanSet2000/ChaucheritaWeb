package resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.DAO.DAOFactory;
import model.entidades.Account;

@Path("/account")
public class AccountResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAll() {
		return DAOFactory.getFactory().getAccountDAO().getAll();
	}
	
	@GET
	@Path("/byId")
	@Produces(MediaType.APPLICATION_JSON)
	public Account getById(@QueryParam("id") int id) {
		return DAOFactory.getFactory().getAccountDAO().getById(id);
	}
	
	@PUT
	@Path("/updateBalance")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateBalance(@QueryParam("id") int id, @QueryParam("amount") double amount) {
		DAOFactory.getFactory().getAccountDAO().updateBalance(id, amount);
	}
}
