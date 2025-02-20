/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Krisztián
 */
@Entity
@Table(name = "kuponkodok")
@NamedQueries({
    @NamedQuery(name = "Kuponkodok.findAll", query = "SELECT k FROM Kuponkodok k"),
    @NamedQuery(name = "Kuponkodok.findById", query = "SELECT k FROM Kuponkodok k WHERE k.id = :id"),
    @NamedQuery(name = "Kuponkodok.findByKod", query = "SELECT k FROM Kuponkodok k WHERE k.kod = :kod"),
    @NamedQuery(name = "Kuponkodok.findByKedvezmenySzazalek", query = "SELECT k FROM Kuponkodok k WHERE k.kedvezmenySzazalek = :kedvezmenySzazalek"),
    @NamedQuery(name = "Kuponkodok.findByErvenyesTol", query = "SELECT k FROM Kuponkodok k WHERE k.ervenyesTol = :ervenyesTol"),
    @NamedQuery(name = "Kuponkodok.findByErvenyesIg", query = "SELECT k FROM Kuponkodok k WHERE k.ervenyesIg = :ervenyesIg")})
public class Kuponkodok implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "kod")
    private String kod;
    @Column(name = "kedvezmeny_szazalek")
    private Integer kedvezmenySzazalek;
    @Column(name = "ervenyes_tol")
    @Temporal(TemporalType.DATE)
    private Date ervenyesTol;
    @Column(name = "ervenyes_ig")
    @Temporal(TemporalType.DATE)
    private Date ervenyesIg;
    @OneToMany(mappedBy = "kuponkodId")
    private Collection<Rendelesek> rendelesekCollection;

    public Kuponkodok() {
    }

    public Kuponkodok(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Integer getKedvezmenySzazalek() {
        return kedvezmenySzazalek;
    }

    public void setKedvezmenySzazalek(Integer kedvezmenySzazalek) {
        this.kedvezmenySzazalek = kedvezmenySzazalek;
    }

    public Date getErvenyesTol() {
        return ervenyesTol;
    }

    public void setErvenyesTol(Date ervenyesTol) {
        this.ervenyesTol = ervenyesTol;
    }

    public Date getErvenyesIg() {
        return ervenyesIg;
    }

    public void setErvenyesIg(Date ervenyesIg) {
        this.ervenyesIg = ervenyesIg;
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
        if (!(object instanceof Kuponkodok)) {
            return false;
        }
        Kuponkodok other = (Kuponkodok) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sneakr.sneakrproject.model.Kuponkodok[ id=" + id + " ]";
    }
    
}
