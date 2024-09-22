package fr.efrei.web.my_project;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("ping")
public class PingEndpoint {
	 // localhost:8000/java-project-api/ping
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public String ping() {
        return "{\"message\": \"png\"}";
    }
}
