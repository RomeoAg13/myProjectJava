package fr.efrei.web.my_project;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/post-game-result")
public class PostGameResult {

    private static final Score scoreManager = new Score();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postGameResult(GameResult result) {
        scoreManager.addResult(result);
        return Response.ok().build();
    }
}
