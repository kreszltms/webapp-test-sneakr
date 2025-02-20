/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Krisztián
 */
@Entity
@Table(name = "szallitasok")
@NamedQueries({
    @NamedQuery(name = "Szallitasok.findAll", query = "SELECT s FROM Szallitasok s"),
    @NamedQuery(name = "Szallitasok.findById", query = "SELECT s FROM Szallitasok s WHERE s.id = :id"),
    @NamedQuery(name = "Szallitasok.findByTipus", query = "SELECT s FROM Szallitasok s WHERE s.tipus = :tipus"),
    @NamedQuery(name = "Szallitasok.findByAr", query = "SELECT s FROM Szallitasok s WHERE s.ar = :ar")})
public class Szallitasok implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "tipus")
    private String tipus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ar")
    private Float ar;
    @OneToMany(mappedBy = "szallitasId")
    private Collection<Rendelesek> rendelesekCollection;

    public Szallitasok() {
    }

    public Szallitasok(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Float getAr() {
        return ar;
    }

    public void setAr(Float ar) {
        this.ar = ar;
    }

    public Collection<Rendelesek> getRendelesekCollection() {
        return rendelesekCollection;
    }

    public void setRendelesekCollection(Collection<Rendelesek> rendelesekCollection) {
        this.rendelesekCollection = rendelesekCollection;
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
        if (!(object instanceof Szallitasok)) {
            return false;
        }
        Szallitasok other = (Szallitasok) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sneakr.sneakrproject.model.Szallitasok[ id=" + id + " ]";
    }
    
}
