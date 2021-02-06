package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MessageDTO;
import facades.ClubMessageFacade;
import facades.ClubPersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

@Path("/clubmessage")
public class ClubMessageResource {

    private final EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final ClubMessageFacade facade = ClubMessageFacade.getInstance(emf);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMessages() {
        List<MessageDTO> messageDTOS = facade.getAllMessages();
        return Response.ok(gson.toJson(messageDTOS)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/person/{personid}")
    public Response getMessageByPerson(@PathParam("personid") long personid) {
        List<MessageDTO> messageDTOS = facade.getAllMessagesByPerson(personid);
        return Response.ok(gson.toJson(messageDTOS)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMessageById(@PathParam("id") long id) {
        MessageDTO message = facade.getMessageById(id);
        return Response.ok(gson.toJson(message)).build();
    }
}