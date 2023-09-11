package resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.DAO.DAOFactory;
import model.entidades.Category;
import model.entidades.Type;

@Path("/category")
public class CategoryResource {
	@GET
	@Path("/byId")
	@Produces(MediaType.APPLICATION_JSON)
	public Category getById(@QueryParam("id") int id) {
		return DAOFactory.getFactory().getCategoryDAO().getById(id);
	}
	
	@GET
	@Path("/byType")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getCategoryList(@QueryParam("type")  Type type) {
		return DAOFactory.getFactory().getCategoryDAO().getCategoryList(type);
	}

	@PUT
	@Path("/updateValue")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateValue(@QueryParam("id") int id, @QueryParam("amount") double amount) {
		DAOFactory.getFactory().getCategoryDAO().updateValue(id, amount);
	}
	
}
