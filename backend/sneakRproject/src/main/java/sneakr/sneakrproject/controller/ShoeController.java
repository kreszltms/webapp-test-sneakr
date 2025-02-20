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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sneakr.sneakrproject.model.Cipok;
import sneakr.sneakrproject.service.ShoeService;

/**
 *
 * @author Krisztián
 */
@Path("cipok")
public class ShoeController {
    
    @Context
    
    private UriInfo context;
    private ShoeService layer = new ShoeService();
    
     public ShoeController() {
    }
     
@GET
@Path("getAllShoes")
@Produces(MediaType.APPLICATION_JSON)
public Response getAllShoes() {
    JSONObject responseObj = new JSONObject();

    try {
        // Call the service to get the list of users
        ArrayList<Cipok> shoesList = layer.getAllShoes();  // Assuming layer.getAllUsers() returns an ArrayList<User>

        // Initialize a JSON array to store user data
        JSONArray shoesArray = new JSONArray();

        // Iterate over the user list and convert each user to a JSONObject
        for (Cipok u : shoesList) {
            JSONObject shoeJson = new JSONObject();
            shoeJson.put("nev", u.getNev());
            shoeJson.put("img", u.getImg());

            // Add the user JSON object to the array
            shoesArray.put(shoeJson);
        }

        // Add the users array to the response object
        responseObj.put("statusCode", 200);
        responseObj.put("shoes", shoesArray);

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

@GET
@Path("getShoesNamePrice")
@Produces(MediaType.APPLICATION_JSON)
public Response getShoesNamePrice() {
    JSONObject responseObj = new JSONObject();

    try {
        // Call the service to get the list of users
        ArrayList<Cipok> shoesList = layer.getAllShoes();  // Assuming layer.getAllUsers() returns an ArrayList<User>

        // Initialize a JSON array to store user data
        JSONArray shoesArray = new JSONArray();

        // Iterate over the user list and convert each user to a JSONObject
        for (Cipok u : shoesList) {
            JSONObject shoeJson = new JSONObject();
            shoeJson.put("nev", u.getNev());
            shoeJson.put("ar", u.getAr());
            shoeJson.put("img", u.getImg());

            // Add the user JSON object to the array
            shoesArray.put(shoeJson);
        }

        // Add the users array to the response object
        responseObj.put("statusCode", 200);
        responseObj.put("shoes", shoesArray);

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

@GET
@Path("getAllShoesData")
@Produces(MediaType.APPLICATION_JSON)
public Response getAllShoesData() {
    JSONObject responseObj = new JSONObject();

    try {
        // Call the service to get the list of users
        ArrayList<Cipok> shoesList = layer.getAllShoesData();  // Assuming layer.getAllUsers() returns an ArrayList<User>

        // Initialize a JSON array to store user data
        JSONArray shoesArray = new JSONArray();

        // Iterate over the user list and convert each user to a JSONObject
        for (Cipok u : shoesList) {
            JSONObject shoeJson = new JSONObject();
            shoeJson.put("id", u.getId());
            shoeJson.put("nev", u.getNev());
            shoeJson.put("marka", u.getMarka());
            shoeJson.put("nem", u.getNem());
            shoeJson.put("allapot", u.getAllapot());
            shoeJson.put("meret", u.getMeret());
            shoeJson.put("ar", u.getAr());
            shoeJson.put("akcioId", u.getAkcioId());
            shoeJson.put("exkluzivId", u.getExkluzivId());
            shoeJson.put("ujdonsagId", u.getUjdonsagId());
            shoeJson.put("img", u.getImg());

            // Add the user JSON object to the array
            shoesArray.put(shoeJson);
        }

        // Add the users array to the response object
        responseObj.put("statusCode", 200);
        responseObj.put("shoes", shoesArray);

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

@POST
@Path("uploadShoes")
@Consumes(MediaType.APPLICATION_JSON)
public Response uploadShoes(String bodyString) {
    try {
        JSONObject body = new JSONObject(bodyString);
        
        Cipok u = new Cipok(
            body.getString("nev"),
            body.getString("marka"),
            body.getString("nem"), 
            body.getString("allapot"),
            body.getInt("meret"),
            (float) body.getDouble("ar"),
            body.optString("img", "")
        );
        
        JSONObject obj = layer.uploadShoes(u);
        return Response.status(obj.getInt("statusCode"))
                       .entity(obj.toString())
                       .build();
    } catch (JSONException e) {
        JSONObject error = new JSONObject();
        error.put("status", "InvalidRequest");
        error.put("statusCode", 400);
        error.put("message", "Malformed JSON input");
        return Response.status(400).entity(error.toString()).build();
    }
}

    @DELETE
    @Path("deleteShoes/{idIN}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteShoes(@PathParam("idIN") Integer idIN) {
        JSONObject obj = layer.deleteShoes(idIN);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
@Path("updateShoe/{id}")
@Consumes(MediaType.APPLICATION_JSON)
public Response updateShoe(@PathParam("id") Integer id, String bodyString) {
    try {
        JSONObject body = new JSONObject(bodyString);
        
        Cipok u = new Cipok(
            body.getString("nev"),
            body.getString("marka"),
            body.getString("nem"),
            body.getString("allapot"),
            body.getInt("meret"),
            (float) body.getDouble("ar"),
            body.optString("img", "")
        );

        JSONObject obj = layer.updateShoe(u, id);
        return Response.status(obj.getInt("statusCode"))
                       .entity(obj.toString())
                       .build();
    } catch (JSONException e) {
        JSONObject error = new JSONObject();
        error.put("status", "InvalidRequest");
        error.put("statusCode", 400);
        error.put("message", "Malformed JSON input");
        return Response.status(400).entity(error.toString()).build();
    }
}

}
