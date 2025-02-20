/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.service;
import java.util.ArrayList;
import sneakr.sneakrproject.model.Userek;
import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sneakr.sneakrproject.model.Cipok;

/**
 *
 * @author Krisztián
 */
public class UserService {
    
    private Userek layer = new Userek();
    
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        
        boolean hasNumber = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if ("!@#$%^&*()_+-=[]{}|;':,.<>?/`~".indexOf(c) != -1) {
                hasSpecialChar = true;
            }
        }
        return hasNumber && hasUpperCase && hasLowerCase && hasSpecialChar;
    }
    
    
    public JSONObject login(String email, String password){
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        if(isValidEmail(email)){
            Userek modelResult = layer.login(email, password);
            
            if(modelResult == null){
                status = "modelException";
                statusCode = 500;
            } else {
                if (modelResult.getId() == null) {
                    status = "userNotFound";
                    statusCode = 417;
                } else {
                    JSONObject result = new JSONObject();
                    result.put("id", modelResult.getId());
                    result.put("nev", modelResult.getNev());
                    result.put("email", modelResult.getEmail());
                    result.put("jelszo", modelResult.getJelszo());
                    result.put("admin", modelResult.getAdmin());

                    toReturn.put("result", result);
                }
            }
        }else{
            status = "invalidEmail";
            statusCode = 417;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    
    public JSONObject registerUser(Userek u) {
    JSONObject toReturn = new JSONObject();
    String status = "success";
    int statusCode = 200;

    try {
        if (!isValidEmail(u.getEmail())) {
            status = "InvalidEmail";
            statusCode = 400; // Bad Request
        } else if (!isValidPassword(u.getJelszo())) {
            status = "InvalidPassword";
            statusCode = 400; // Bad Request
        } else {
            Boolean userIsExists = Userek.isUserExists(u.getEmail());

            if (userIsExists == null) {
                status = "ModelException";
                statusCode = 500; // Internal Server Error
            } else if (userIsExists) {
                status = "UserAlreadyExists";
                statusCode = 409; // Conflict
            } else {
                boolean registerUser = layer.registerUser(u);
                if (!registerUser) {
                    status = "RegistrationFailed";
                    statusCode = 500; // Internal Server Error
                }
            }
        }
    } catch (Exception e) {
        status = "ServerError";
        statusCode = 500; // Internal Server Error
        System.err.println("Error during user registration: " + e.getMessage());
    }

    toReturn.put("status", status);
    toReturn.put("statusCode", statusCode);
    return toReturn;
}
    
    public ArrayList<Userek> getAllUsers() {
    ArrayList<Userek> userList = new ArrayList<>(); 
    try {
        userList = layer.getAllUsers();     
        
    } catch (Exception e) {
        System.err.println("Error fetching users: " + e.getMessage());
    }

    return userList;
}

    
    public JSONObject deleteUser(Integer userId) {
    JSONObject toReturn = new JSONObject();
    String status = "success";
    int statusCode = 200;

    try {
        boolean deleteResult = Userek.deleteUser(userId);
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
   
   
}
