package fr.efrei.web.my_project;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

@Path("/get-score")
public class ScorePoint {

    private static final Score scoreManager = new Score();

    @GET
    @Path("/{player}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScore(@PathParam("player") String player) {
        Map<String, Integer> allResults = scoreManager.getAllResults();
        int score = allResults.getOrDefault(player, 0);
        String jsonResponse = "{\"player\": \"" + player + "\", \"score\": " + score + "}";
        return Response.ok(jsonResponse).build();
    }
}
