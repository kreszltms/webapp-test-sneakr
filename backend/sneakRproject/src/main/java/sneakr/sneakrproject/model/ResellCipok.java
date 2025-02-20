/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Krisztián
 */
@Entity
@Table(name = "resell_cipok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResellCipok.findAll", query = "SELECT r FROM ResellCipok r"),
    @NamedQuery(name = "ResellCipok.findById", query = "SELECT r FROM ResellCipok r WHERE r.id = :id"),
    @NamedQuery(name = "ResellCipok.findByNev", query = "SELECT r FROM ResellCipok r WHERE r.nev = :nev"),
    @NamedQuery(name = "ResellCipok.findByMarka", query = "SELECT r FROM ResellCipok r WHERE r.marka = :marka"),
    @NamedQuery(name = "ResellCipok.findByNem", query = "SELECT r FROM ResellCipok r WHERE r.nem = :nem"),
    @NamedQuery(name = "ResellCipok.findByAllapot", query = "SELECT r FROM ResellCipok r WHERE r.allapot = :allapot"),
    @NamedQuery(name = "ResellCipok.findByMeret", query = "SELECT r FROM ResellCipok r WHERE r.meret = :meret"),
    @NamedQuery(name = "ResellCipok.findByAr", query = "SELECT r FROM ResellCipok r WHERE r.ar = :ar")})
public class ResellCipok implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "nev")
    private String nev;
    @Size(max = 100)
    @Column(name = "marka")
    private String marka;
    @Size(max = 100)
    @Column(name = "nem")
    private String nem;
    @Size(max = 100)
    @Column(name = "allapot")
    private String allapot;
    @Column(name = "meret")
    private Integer meret;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ar")
    private Float ar;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "img")
    private String img;
    
     static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sneakr_sneakRproject_war_1.0-SNAPSHOTPU");

    public ResellCipok() {
    }

    public ResellCipok(Integer id) {
        this.id = id;
    }

    public ResellCipok(Integer id, String img) {
        this.id = id;
        this.img = img;
    }
    
//    public ResellCipok(String nev, String marka,String nem, String allapot, Integer meret,Float ar, String img, Integer user_id) {
//        this.id = id;
//        this.nev = nev;
//        this.marka = marka;
//        this.nem = nem;
//        this.allapot = allapot;
//        this.meret = meret;
//        this.ar = ar;
//        this.img = img;
//        this.userId = userId;
//    }
    public ResellCipok(String nev, String marka,String nem, String allapot, Integer meret,Float ar, String img, Integer userId) {
        EntityManager em = emf.createEntityManager();
        this.nev = nev;
        this.marka = marka;
        this.nem = nem;
        this.allapot = allapot;
        this.meret = meret;
        this.ar = ar;
        this.img = img;
        this.userId = userId;
    }
    
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getNem() {
        return nem;
    }

    public void setNem(String nem) {
        this.nem = nem;
    }

    public String getAllapot() {
        return allapot;
    }

    public void setAllapot(String allapot) {
        this.allapot = allapot;
    }

    public Integer getMeret() {
        return meret;
    }

    public void setMeret(Integer meret) {
        this.meret = meret;
    }

    public Float getAr() {
        return ar;
    }

    public void setAr(Float ar) {
        this.ar = ar;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        if (!(object instanceof ResellCipok)) {
            return false;
        }
        ResellCipok other = (ResellCipok) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sneakr.sneakrproject.model.ResellCipok[ id=" + id + " ]";
    }
    
    public static ArrayList<ResellCipok> getAllResellShoesData() {
    EntityManager em = emf.createEntityManager();
    ArrayList<ResellCipok> ResellShoesList = new ArrayList<>();

    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllResellShoesData", ResellCipok.class);
        spq.execute();
        ResellShoesList = new ArrayList<>(spq.getResultList());

    } catch (Exception e) {
        System.err.println("Error: " + e.getLocalizedMessage());
    } finally {
        em.clear();
        em.close();
    }

    return ResellShoesList;
}

     public Boolean uploadResellShoes(ResellCipok u) {
        EntityManager em = emf.createEntityManager();
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("uploadResellShoes");
            
            spq.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("markaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nemIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("allapotIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("meretIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("arIN", Float.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("imgIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("user_IdIN", String.class, ParameterMode.IN);
            
            spq.setParameter("nevIN", u.getNev());
            spq.setParameter("markaIN", u.getMarka());
            spq.setParameter("nemIN", u.getNem());
            spq.setParameter("allapotIN", u.getAllapot());
            spq.setParameter("meretIN", u.getMeret());
            spq.setParameter("arIN", u.getAr());
            spq.setParameter("imgIN", u.getImg());
            spq.setParameter("user_IdIN", u.getUserId());
          
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
    
}
