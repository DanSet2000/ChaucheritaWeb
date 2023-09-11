package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.DAO.DAOFactory;
import model.entidades.User;

@Path("/user")
public class UserResource {
	@GET
	@Path("/byId")
	@Produces(MediaType.APPLICATION_JSON)
	public User getById(@QueryParam("id") int id) {
		return DAOFactory.getFactory().getUserDAO().getById(id);
	}
}
