package fr.efrei.web.my_project;

import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ranking")
public class Rank {

    private static final Score scoreManager = new Score();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopPlayers() {
        Map<String, Integer> topPlayers = scoreManager.getTopPlayers();
        return Response.ok(topPlayers).build();
    }
}
