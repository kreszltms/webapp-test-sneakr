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
import sneakr.sneakrproject.model.ResellCipok;

/**
 *
 * @author Krisztián
 */
public class ResellShoeService {
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sneakr_sneakRproject_war_1.0-SNAPSHOTPU");
    
    private ResellCipok layer = new ResellCipok();
    
    public ArrayList<ResellCipok> getAllResellShoesData() {
    ArrayList<ResellCipok>ResellShoesList = new ArrayList<>(); 
    try {
        ResellShoesList = layer.getAllResellShoesData();     
        
    } catch (Exception e) {
        System.err.println("Error fetching shoes: " + e.getMessage());
    }

    return ResellShoesList;
}
    
    public JSONObject uploadResellShoes(ResellCipok u) {
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
            StoredProcedureQuery spq = em.createStoredProcedureQuery("uploadResellShoes");
            
            // Register parameters (fixed parameter name mismatch)
            spq.registerStoredProcedureParameter("resell_nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("resell_markaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("resell_nemIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("resell_allapotIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("resell_meretIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("resell_arIN", Float.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("resell_imgIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("resell_userIdIN", Integer.class, ParameterMode.IN);
            
            
            // Set parameters
            spq.setParameter("resell_nevIN", u.getNev());
            spq.setParameter("resell_markaIN", u.getMarka());
            spq.setParameter("resell_nemIN", u.getNem());
            spq.setParameter("resell_allapotIN", u.getAllapot());
            spq.setParameter("resell_meretIN", u.getMeret());
            spq.setParameter("resell_arIN", u.getAr());
            spq.setParameter("resell_imgIN", u.getImg());
            spq.setParameter("resell_userIdIN", u.getUserId());

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
    
    
}
