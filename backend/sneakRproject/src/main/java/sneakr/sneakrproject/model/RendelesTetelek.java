/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.model;

import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author Krisztián
 */
@Entity
@Table(name = "rendeles_tetelek")
@NamedQueries({
    @NamedQuery(name = "RendelesTetelek.findAll", query = "SELECT r FROM RendelesTetelek r"),
    @NamedQuery(name = "RendelesTetelek.findById", query = "SELECT r FROM RendelesTetelek r WHERE r.id = :id"),
    @NamedQuery(name = "RendelesTetelek.findByMennyiseg", query = "SELECT r FROM RendelesTetelek r WHERE r.mennyiseg = :mennyiseg"),
    @NamedQuery(name = "RendelesTetelek.findByAr", query = "SELECT r FROM RendelesTetelek r WHERE r.ar = :ar")})
public class RendelesTetelek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mennyiseg")
    private Integer mennyiseg;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ar")
    private Float ar;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Userek userId;
    @JoinColumn(name = "cipok_id", referencedColumnName = "id")
    @ManyToOne
    private Cipok cipokId;
    @JoinColumn(name = "rendeles_id", referencedColumnName = "id")
    @ManyToOne
    private Rendelesek rendelesId;

    public RendelesTetelek() {
    }

    public RendelesTetelek(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(Integer mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public Float getAr() {
        return ar;
    }

    public void setAr(Float ar) {
        this.ar = ar;
    }

    public Userek getUserId() {
        return userId;
    }

    public void setUserId(Userek userId) {
        this.userId = userId;
    }

    public Cipok getCipokId() {
        return cipokId;
    }

    public void setCipokId(Cipok cipokId) {
        this.cipokId = cipokId;
    }

    public Rendelesek getRendelesId() {
        return rendelesId;
    }

    public void setRendelesId(Rendelesek rendelesId) {
        this.rendelesId = rendelesId;
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
        if (!(object instanceof RendelesTetelek)) {
            return false;
        }
        RendelesTetelek other = (RendelesTetelek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sneakr.sneakrproject.model.RendelesTetelek[ id=" + id + " ]";
    }
    
}
