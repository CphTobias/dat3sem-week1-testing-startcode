package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import entities.ClubPerson;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;
import facades.ClubPersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("clubperson")
public class ClubPersonResource {

    private final EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
       
    private final ClubPersonFacade facade =  ClubPersonFacade.getFacadeExample(emf);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPeople() {
        List<PersonDTO> people = facade.getAllPeopleDTO();
        return Response.ok(gson.toJson(people)).build();
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleByName(@PathParam("name") String name) {
        List<PersonDTO> people = facade.getPeopleByName(name);
        return Response.ok(gson.toJson(people)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClubPeople() {
        List<ClubPerson> people = facade.getAllClubPeople();
        return Response.ok(gson.toJson(people)).build();
    }
}
