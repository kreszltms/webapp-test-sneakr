/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static sneakr.sneakrproject.model.Userek.emf;

/**
 *
 * @author Krisztián
 */
@Entity
@Table(name = "cipok")
@NamedQueries({
    @NamedQuery(name = "Cipok.findAll", query = "SELECT c FROM Cipok c"),
    @NamedQuery(name = "Cipok.findById", query = "SELECT c FROM Cipok c WHERE c.id = :id"),
    @NamedQuery(name = "Cipok.findByNev", query = "SELECT c FROM Cipok c WHERE c.nev = :nev"),
    @NamedQuery(name = "Cipok.findByAllapot", query = "SELECT c FROM Cipok c WHERE c.allapot = :allapot"),
    @NamedQuery(name = "Cipok.findByAr", query = "SELECT c FROM Cipok c WHERE c.ar = :ar")})
public class Cipok implements Serializable {

    @Size(max = 100)
    @Column(name = "nem")
    private String nem;
    
    
    public String getNem() {
        return nem;
    }

    public void setNem(String nem) {
        this.nem = nem;
    }

    @Size(max = 100)
    @Column(name = "marka")
    private String marka;

    @Column(name = "meret")
    private Integer meret;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "nev")
    private String nev;
    @Size(max = 8)
    @Column(name = "allapot")
    private String allapot;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ar")
    private Float ar;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "img")
    private String img;
    @JoinColumn(name = "akcio_id", referencedColumnName = "id")
    @ManyToOne
    private Akciok akcioId;
    @JoinColumn(name = "exkluziv_id", referencedColumnName = "id")
    @ManyToOne
    private Exkluzivok exkluzivId;
    @ManyToOne
    @JoinColumn(name = "ujdonsag_id")  // This is critical
    private Ujdonsagok ujdonsagId;
    @OneToMany(mappedBy = "cipokId")
    private Collection<RendelesTetelek> rendelesTetelekCollection;
    @OneToMany(mappedBy = "cipokId")
    private Collection<ShoppingSession> shoppingSessionCollection;
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sneakr_sneakRproject_war_1.0-SNAPSHOTPU");
    

    public Cipok() {
    }
    public Cipok(String nev,Float ar, String img) {
      this.nev = nev;
      this.ar = ar;
      this.img = img;
    }

    public Cipok(Integer id, String img) {
        this.id = id;
        this.img = img;
    }
    
    public Cipok(String nev, String marka,String nem, String allapot, Integer meret,Float ar,Akciok akcioId,Exkluzivok exkluzivId,Ujdonsagok ujdonsagId, String img) {
        this.id = id;
        this.nev = nev;
        this.marka = marka;
        this.nem = nem;
        this.allapot = allapot;
        this.meret = meret;
        this.ar = ar;
        this.akcioId = akcioId;
        this.exkluzivId = exkluzivId;
        this.ujdonsagId = ujdonsagId;
        this.img = img;
    }
    
    public Cipok(String nev, String marka,String nem, String allapot, Integer meret,Float ar, String img) {
        EntityManager em = emf.createEntityManager();
        this.nev = nev;
        this.marka = marka;
        this.nem = nem;
        this.allapot = allapot;
        this.meret = meret;
        this.ar = ar;
        this.img = img;
    }

    public Integer getMeret() {
        return meret;
    }

    public void setMeret(Integer meret) {
        this.meret = meret;
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

    public String getAllapot() {
        return allapot;
    }

    public void setAllapot(String allapot) {
        this.allapot = allapot;
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

    public Akciok getAkcioId() {
        return akcioId;
    }

    public void setAkcioId(Akciok akcioId) {
        this.akcioId = akcioId;
    }

    public Exkluzivok getExkluzivId() {
        return exkluzivId;
    }

    public void setExkluzivId(Exkluzivok exkluzivId) {
        this.exkluzivId = exkluzivId;
    }
    
    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public Ujdonsagok getUjdonsagId() {
        return ujdonsagId;
    }

    public void setUjdonsagId(Ujdonsagok ujdonsagId) {
        this.ujdonsagId = ujdonsagId;
    }

    public Collection<RendelesTetelek> getRendelesTetelekCollection() {
        return rendelesTetelekCollection;
    }

    public void setRendelesTetelekCollection(Collection<RendelesTetelek> rendelesTetelekCollection) {
        this.rendelesTetelekCollection = rendelesTetelekCollection;
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
        if (!(object instanceof Cipok)) {
            return false;
        }
        Cipok other = (Cipok) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static ArrayList<Cipok> getAllShoes() {
    EntityManager em = emf.createEntityManager();
    ArrayList<Cipok> shoesList = new ArrayList<>();

    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllShoes", Cipok.class);
        spq.execute();
        shoesList = new ArrayList<>(spq.getResultList());

    } catch (Exception e) {
        System.err.println("Error: " + e.getLocalizedMessage());
    } finally {
        em.clear();
        em.close();
    }

    return shoesList;
}
    public static ArrayList<Cipok> getShoesNamePrice() {
    EntityManager em = emf.createEntityManager();
    ArrayList<Cipok> shoesList = new ArrayList<>();

    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getShoesNamePrice", Cipok.class);
        spq.execute();
        shoesList = new ArrayList<>(spq.getResultList());

    } catch (Exception e) {
        System.err.println("Error: " + e.getLocalizedMessage());
    } finally {
        em.clear();
        em.close();
    }

    return shoesList;
}
    
    public static ArrayList<Cipok> getAllShoesData() {
    EntityManager em = emf.createEntityManager();
    ArrayList<Cipok> shoesList = new ArrayList<>();

    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllShoesData", Cipok.class);
        spq.execute();
        shoesList = new ArrayList<>(spq.getResultList());

    } catch (Exception e) {
        System.err.println("Error: " + e.getLocalizedMessage());
    } finally {
        em.clear();
        em.close();
    }

    return shoesList;
}
  
    public Boolean uploadShoes(Cipok u) {
        EntityManager em = emf.createEntityManager();
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("uploadShoes");
            
            spq.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("markaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nemIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("allapotIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("meretIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("arIN", Float.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("imgIN", String.class, ParameterMode.IN);
            
            spq.setParameter("nevIN", u.getNev());
            spq.setParameter("markaIN", u.getMarka());
            spq.setParameter("nemIN", u.getNem());
            spq.setParameter("allapotIN", u.getAllapot());
            spq.setParameter("meretIN", u.getMeret());
            spq.setParameter("arIN", u.getAr());
            spq.setParameter("imgIN", u.getImg());
          
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

    public static boolean deleteShoes(Integer idIN) {
    EntityManager em = emf.createEntityManager();
    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteShoes");
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", idIN);
        spq.execute();
        return true;
    } catch (Exception e) {
        System.err.println("Hiba a cipő törlésekor: " + e.getMessage());
        return false;
    } finally {
        em.clear();
        em.close();
    }
}

    public static boolean updateShoe(Cipok u, Integer id) {
    EntityManager em = emf.createEntityManager();
    try {
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
        return true;
    } catch (Exception e) {
        System.err.println("Update error: " + e.getMessage());
        return false;
    } finally {
        em.clear();
        em.close();
    }
}
 



    
}
