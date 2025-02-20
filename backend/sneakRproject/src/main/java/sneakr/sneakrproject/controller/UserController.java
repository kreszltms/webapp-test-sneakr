/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.controller;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import sneakr.sneakrproject.model.Cipok;
import sneakr.sneakrproject.model.Userek;
import sneakr.sneakrproject.service.UserService;
import javax.ws.rs.PathParam;

/**
 *
 * @author Krisztián
 */
@Path("userek")
public class UserController {
    
    @Context
    
    private UriInfo context;
    private UserService layer = new UserService();
    
     public UserController() {
    }

    /**
     * Retrieves representation of an instance of com.iakk.backendvizsga.controller.UserController
     * @return an instance of java.lang.String
     */
    
     // ITT JÁRTAM
    @DELETE
    @Path("deleteUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") Integer userId) {
        JSONObject obj = layer.deleteUser(userId);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
     // IDÁIG JÁRTAM
     
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UserController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String bodyString){
        JSONObject body = new JSONObject(bodyString);
        
        JSONObject obj = layer.login(body.getString("email"), body.getString("password"));
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(String bodyString){
        JSONObject body = new JSONObject(bodyString);
        
        Userek u = new Userek(
                body.getString("nev"),
                body.getString("email"),
                body.getString("password")
        );
        
        JSONObject obj = layer.registerUser(u);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
//    @POST
//    @Path("registerAdmin")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response registerAdmin(String bodyString){
//        JSONObject body = new JSONObject(bodyString);
//        
//        Userek u = new Userek(
//                body.getString("email"),
//                body.getString("phoneNumber"),
//                body.getString("firstName"),
//                body.getString("lastName"),
//                body.getString("password"),
//                body.getBoolean("isAdmin")
//        );
//        
//        JSONObject obj = layer.registerUser(u);
//        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
//    }
    
@GET
@Path("getAllUsers")
@Produces(MediaType.APPLICATION_JSON)
public Response getAllUsers() {
    JSONObject responseObj = new JSONObject();

    try {
        // Call the service to get the list of users
        ArrayList<Userek> userList = layer.getAllUsers();  // Assuming layer.getAllUsers() returns an ArrayList<User>

        // Initialize a JSON array to store user data
        JSONArray usersArray = new JSONArray();

        // Iterate over the user list and convert each user to a JSONObject
        for (Userek u : userList) {
            JSONObject userJson = new JSONObject();
            userJson.put("id", u.getId());
            userJson.put("nev", u.getNev());
            userJson.put("email", u.getEmail());
            userJson.put("jelszo", u.getJelszo());
            userJson.put("admin", u.getAdmin());

            // Add the user JSON object to the array
            usersArray.put(userJson);
        }

        // Add the users array to the response object
        responseObj.put("statusCode", 200);
        responseObj.put("users", usersArray);

        // Return the response with a 200 OK status
        return Response.ok(responseObj.toString(), MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {
        // Handle any exceptions
        responseObj.put("statusCode", 500);
        responseObj.put("message", "Failed to retrieve users");
        responseObj.put("error", e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}  

}
