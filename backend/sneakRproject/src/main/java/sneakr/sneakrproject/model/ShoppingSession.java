/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Krisztián
 */
@Entity
@Table(name = "shopping_session")
@NamedQueries({
    @NamedQuery(name = "ShoppingSession.findAll", query = "SELECT s FROM ShoppingSession s"),
    @NamedQuery(name = "ShoppingSession.findById", query = "SELECT s FROM ShoppingSession s WHERE s.id = :id"),
    @NamedQuery(name = "ShoppingSession.findBySessionStart", query = "SELECT s FROM ShoppingSession s WHERE s.sessionStart = :sessionStart"),
    @NamedQuery(name = "ShoppingSession.findByActive", query = "SELECT s FROM ShoppingSession s WHERE s.active = :active"),
    @NamedQuery(name = "ShoppingSession.findByMennyiseg", query = "SELECT s FROM ShoppingSession s WHERE s.mennyiseg = :mennyiseg"),
    @NamedQuery(name = "ShoppingSession.findByAr", query = "SELECT s FROM ShoppingSession s WHERE s.ar = :ar")})
public class ShoppingSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "session_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionStart;
    @Column(name = "active")
    private Boolean active;
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

    public ShoppingSession() {
    }

    public ShoppingSession(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(Date sessionStart) {
        this.sessionStart = sessionStart;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppingSession)) {
            return false;
        }
        ShoppingSession other = (ShoppingSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sneakr.sneakrproject.model.ShoppingSession[ id=" + id + " ]";
    }
    
}
