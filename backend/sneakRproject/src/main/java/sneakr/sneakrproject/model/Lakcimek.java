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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "lakcimek")
@NamedQueries({
    @NamedQuery(name = "Lakcimek.findAll", query = "SELECT l FROM Lakcimek l"),
    @NamedQuery(name = "Lakcimek.findById", query = "SELECT l FROM Lakcimek l WHERE l.id = :id"),
    @NamedQuery(name = "Lakcimek.findByOrszag", query = "SELECT l FROM Lakcimek l WHERE l.orszag = :orszag"),
    @NamedQuery(name = "Lakcimek.findByVaros", query = "SELECT l FROM Lakcimek l WHERE l.varos = :varos"),
    @NamedQuery(name = "Lakcimek.findByIranyitoszam", query = "SELECT l FROM Lakcimek l WHERE l.iranyitoszam = :iranyitoszam"),
    @NamedQuery(name = "Lakcimek.findByUtca", query = "SELECT l FROM Lakcimek l WHERE l.utca = :utca"),
    @NamedQuery(name = "Lakcimek.findByHazszam", query = "SELECT l FROM Lakcimek l WHERE l.hazszam = :hazszam")})
public class Lakcimek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "orszag")
    private String orszag;
    @Size(max = 255)
    @Column(name = "varos")
    private String varos;
    @Size(max = 255)
    @Column(name = "iranyitoszam")
    private String iranyitoszam;
    @Size(max = 255)
    @Column(name = "utca")
    private String utca;
    @Size(max = 255)
    @Column(name = "hazszam")
    private String hazszam;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Userek userId;
    @OneToMany(mappedBy = "szallitasiCimId")
    private Collection<Rendelesek> rendelesekCollection;

    public Lakcimek() {
    }

    public Lakcimek(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrszag() {
        return orszag;
    }

    public void setOrszag(String orszag) {
        this.orszag = orszag;
    }

    public String getVaros() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public String getIranyitoszam() {
        return iranyitoszam;
    }

    public void setIranyitoszam(String iranyitoszam) {
        this.iranyitoszam = iranyitoszam;
    }

    public String getUtca() {
        return utca;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public String getHazszam() {
        return hazszam;
    }

    public void setHazszam(String hazszam) {
        this.hazszam = hazszam;
    }

    public Userek getUserId() {
        return userId;
    }

    public void setUserId(Userek userId) {
        this.userId = userId;
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
        if (!(object instanceof Lakcimek)) {
            return false;
        }
        Lakcimek other = (Lakcimek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sneakr.sneakrproject.model.Lakcimek[ id=" + id + " ]";
    }
    
}
