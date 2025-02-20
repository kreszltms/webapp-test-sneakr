/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.service;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import org.json.JSONObject;
import sneakr.sneakrproject.model.Cipok;

/**
 *
 * @author Krisztián
 */
public class ShoeService {
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sneakr_sneakRproject_war_1.0-SNAPSHOTPU");
    
    private Cipok layer = new Cipok();
    
     public ArrayList<Cipok> getAllShoes() {
    ArrayList<Cipok> shoesList = new ArrayList<>(); 
    try {
        shoesList = layer.getAllShoes();     
        
    } catch (Exception e) {
        System.err.println("Error fetching shoes: " + e.getMessage());
    }

    return shoesList;
}
     
     public ArrayList<Cipok> getShoesNamePrice() {
    ArrayList<Cipok> shoesList = new ArrayList<>(); 
    try {
        shoesList = layer.getShoesNamePrice();     
        
    } catch (Exception e) {
        System.err.println("Error fetching shoes: " + e.getMessage());
    }

    return shoesList;
}
    
    public ArrayList<Cipok> getAllShoesData() {
    ArrayList<Cipok> shoesList = new ArrayList<>(); 
    try {
        shoesList = layer.getAllShoesData();     
        
    } catch (Exception e) {
        System.err.println("Error fetching shoes: " + e.getMessage());
    }

    return shoesList;
}
    
    public JSONObject uploadShoes(Cipok u) {
    JSONObject toReturn = new JSONObject();
    String status = "success";
    int statusCode = 200;
    EntityManager em = emf.createEntityManager();

    try {
        // Existing validations
        if (u.getNev() == null || u.getNev().trim().isEmpty()) {
            status = "InvalidName";
            statusCode = 400;
        } else if (u.getMarka() == null || u.getMarka().trim().isEmpty()) {
            status = "InvalidBrand";
            statusCode = 400;
        } 
        // Add gender validation
        else if (u.getNem() == null) {
            status = "InvalidGender";
            statusCode = 400;
        }
        else if (u.getAllapot() == null)
        {
            status = "InvalidCondition";
            statusCode = 400;
        }
        
        else if (u.getMeret() <= 0) {
            status = "InvalidSize";
            statusCode = 400;
        } else if (u.getAr() <= 0) {
            status = "InvalidPrice";
            statusCode = 400;
        } else {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("uploadShoes");
            
            // Register parameters (fixed parameter name mismatch)
            spq.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("markaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nemIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("allapotIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("meretIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("arIN", Float.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("imgIN", String.class, ParameterMode.IN);
            
            // Set parameters
            spq.setParameter("nevIN", u.getNev());
            spq.setParameter("markaIN", u.getMarka());
            spq.setParameter("nemIN", u.getNem());
            spq.setParameter("allapotIN", u.getAllapot());
            spq.setParameter("meretIN", u.getMeret());
            spq.setParameter("arIN", u.getAr());
            spq.setParameter("imgIN", u.getImg());

            spq.execute();
        }
    } catch (Exception e) {
        status = "ServerError";
        statusCode = 500;
        System.err.println("Error during shoe upload: " + e.getMessage());
    } finally {
        if (em != null) {
            em.clear();
            em.close();
        }
    }

    toReturn.put("status", status);
    toReturn.put("statusCode", statusCode);
    return toReturn;
}
    
    public JSONObject deleteShoes(Integer id) {
    JSONObject toReturn = new JSONObject();
    String status = "success";
    int statusCode = 200;

    try {
        boolean deleteResult = Cipok.deleteShoes(id);
        if (!deleteResult) {
            status = "DeleteFailed";
            statusCode = 500;
        }
    } catch (Exception e) {
        status = "ServerError";
        statusCode = 500;
        System.err.println("Hiba a törléskor: " + e.getMessage());
    }

    toReturn.put("status", status);
    toReturn.put("statusCode", statusCode);
    return toReturn;
}
     
    public JSONObject updateShoe(Cipok u, Integer id) {
    JSONObject toReturn = new JSONObject();
    String status = "success";
    int statusCode = 200;
    EntityManager em = emf.createEntityManager();

    try {
        // Validations
        if (id == null || id <= 0) {
            status = "InvalidID";
            statusCode = 400;
        } else if (u.getNev() == null || u.getNev().trim().isEmpty()) {
            status = "InvalidName";
            statusCode = 400;
        } else if (u.getMarka() == null || u.getMarka().trim().isEmpty()) {
            status = "InvalidBrand";
            statusCode = 400;
        } else if (u.getNem() == null) {
            status = "InvalidGender";
            statusCode = 400;
        } else if (u.getAllapot() == null) {
            status = "InvalidCondition";
            statusCode = 400;
        } else if (u.getMeret() <= 0) {
            status = "InvalidSize";
            statusCode = 400;
        } else if (u.getAr() <= 0) {
            status = "InvalidPrice";
            statusCode = 400;
        } else {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateShoe");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("markaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nemIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("allapotIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("meretIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("arIN", Float.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("imgIN", String.class, ParameterMode.IN);

            spq.setParameter("idIN", id);
            spq.setParameter("nevIN", u.getNev());
            spq.setParameter("markaIN", u.getMarka());
            spq.setParameter("nemIN", u.getNem());
            spq.setParameter("allapotIN", u.getAllapot());
            spq.setParameter("meretIN", u.getMeret());
            spq.setParameter("arIN", u.getAr());
            spq.setParameter("imgIN", u.getImg());

            spq.execute();
        }
    } catch (Exception e) {
        status = "ServerError";
        statusCode = 500;
        System.err.println("Error during shoe update: " + e.getMessage());
    } finally {
        if (em != null) {
            em.clear();
            em.close();
        }
    }

    toReturn.put("status", status);
    toReturn.put("statusCode", statusCode);
    return toReturn;
}
}
