/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Krisztián
 */
@Entity
@Table(name = "userek")
@NamedQueries({
    @NamedQuery(name = "Userek.findAll", query = "SELECT u FROM Userek u"),
    @NamedQuery(name = "Userek.findById", query = "SELECT u FROM Userek u WHERE u.id = :id"),
    @NamedQuery(name = "Userek.findByNev", query = "SELECT u FROM Userek u WHERE u.nev = :nev"),
    @NamedQuery(name = "Userek.findByEmail", query = "SELECT u FROM Userek u WHERE u.email = :email"),
    @NamedQuery(name = "Userek.findByJelszo", query = "SELECT u FROM Userek u WHERE u.jelszo = :jelszo"),
    @NamedQuery(name = "Userek.findByAdmin", query = "SELECT u FROM Userek u WHERE u.admin = :admin")})
public class Userek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "nev")
    private String nev;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "jelszo")
    private String jelszo;
    @Size(max = 255)
    @Column(name = "admin")
    private String admin;
    @OneToMany(mappedBy = "userId")
    private Collection<Lakcimek> lakcimekCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<RendelesTetelek> rendelesTetelekCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Rendelesek> rendelesekCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<ShoppingSession> shoppingSessionCollection;
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sneakr_sneakRproject_war_1.0-SNAPSHOTPU");

    public Userek() {
    }
    
    public Userek(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Userek u = em.find(Userek.class, id);
            
            this.id = u.getId();
            this.nev = u.getNev();
            this.email = u.getEmail();
            this.jelszo = u.getJelszo();
            this.admin = u.getAdmin();
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
    }
    public Userek(Integer id, String nev, String email, String password, String admin) {
        this.id = id;
        this.nev = nev;
        this.email = email;
        this.jelszo = password;
        this.admin = admin;
    }

    public Userek(String nev, String email, String password) {
        this.nev = nev;
        this.email = email;
        this.jelszo = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Collection<Lakcimek> getLakcimekCollection() {
        return lakcimekCollection;
    }

    public void setLakcimekCollection(Collection<Lakcimek> lakcimekCollection) {
        this.lakcimekCollection = lakcimekCollection;
    }

    public Collection<RendelesTetelek> getRendelesTetelekCollection() {
        return rendelesTetelekCollection;
    }

    public void setRendelesTetelekCollection(Collection<RendelesTetelek> rendelesTetelekCollection) {
        this.rendelesTetelekCollection = rendelesTetelekCollection;
    }

    public Collection<Rendelesek> getRendelesekCollection() {
        return rendelesekCollection;
    }

    public void setRendelesekCollection(Collection<Rendelesek> rendelesekCollection) {
        this.rendelesekCollection = rendelesekCollection;
    }

    public Collection<ShoppingSession> getShoppingSessionCollection() {
        return shoppingSessionCollection;
    }

    public void setShoppingSessionCollection(Collection<ShoppingSession> shoppingSessionCollection) {
        this.shoppingSessionCollection = shoppingSessionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userek)) {
            return false;
        }
        Userek other = (Userek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sneakr.sneakrproject.model.Userek[ id=" + id + " ]";
    }
    
    public Userek login(String email, String password){
        EntityManager em = emf.createEntityManager();
        
        try {
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("login");
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            
            spq.setParameter("emailIN", email);
            spq.setParameter("passwordIN", password);
            
            spq.execute();
            
            List<Object[]> resultList = spq.getResultList();
            Userek toReturn = new Userek();
            for(Object[] o : resultList){
                
                Userek u = new Userek(
                        Integer.valueOf(o[0].toString()),
                        o[1].toString(),
                        o[2].toString(),
                        o[3].toString(),
                        o[4].toString()
                );
                toReturn = u;
            }
            
            return toReturn;
            
        } catch (NumberFormatException e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
    public Boolean registerUser(Userek u) {
        EntityManager em = emf.createEntityManager();
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("registerUser");
            
            spq.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("jelszoIN", String.class, ParameterMode.IN);
            
            spq.setParameter("nevIN", u.getNev());
            spq.setParameter("emailIN", u.getEmail());
            spq.setParameter("jelszoIN", u.getJelszo());
            
            spq.execute();
            
            return true;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return false;
        } finally{
            em.clear();
            em.close();
        }
    }
    
    public static Boolean isUserExists(String email){
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("isUserExists");

            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("resultOUT", Boolean.class, ParameterMode.OUT);
            
            spq.setParameter("emailIN", email);
            
            spq.execute();
            
            Boolean result = Boolean.valueOf(spq.getOutputParameterValue("resultOUT").toString());
            
            return result;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    // ITT JÁRTAM
    
    public static boolean deleteUser(Integer userId) {
    EntityManager em = emf.createEntityManager();
    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteUser");
        spq.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN);
        spq.setParameter("userId", userId);
        spq.execute();
        return true;
    } catch (Exception e) {
        System.err.println("Hiba a felhasználó törlésekor: " + e.getMessage());
        return false;
    } finally {
        em.clear();
        em.close();
    }
}
        // IDÁIG JÁRTAM 
    
    
    public static ArrayList<Userek> getAllUsers() {
    EntityManager em = emf.createEntityManager();
    ArrayList<Userek> userList = new ArrayList<>();

    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllUsers", Userek.class);
        spq.execute();
        userList = new ArrayList<>(spq.getResultList());

    } catch (Exception e) {
        System.err.println("Error: " + e.getLocalizedMessage());
    } finally {
        em.clear();
        em.close();
    }

    return userList;
}
  
    
}
