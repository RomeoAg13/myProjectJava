package fr.efrei.web.my_project;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;



@Path("hello")
public class HelloEndPoint {
    // localhost:8000/java-project-api/hello
    // GET
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        return "<h1>hello</h1>";
    }
}
