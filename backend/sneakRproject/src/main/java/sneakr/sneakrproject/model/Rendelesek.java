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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "rendelesek")
@NamedQueries({
    @NamedQuery(name = "Rendelesek.findAll", query = "SELECT r FROM Rendelesek r"),
    @NamedQuery(name = "Rendelesek.findById", query = "SELECT r FROM Rendelesek r WHERE r.id = :id"),
    @NamedQuery(name = "Rendelesek.findByRendelesDatum", query = "SELECT r FROM Rendelesek r WHERE r.rendelesDatum = :rendelesDatum"),
    @NamedQuery(name = "Rendelesek.findByRendelesAllapot", query = "SELECT r FROM Rendelesek r WHERE r.rendelesAllapot = :rendelesAllapot")})
public class Rendelesek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "rendeles_datum")
    @Temporal(TemporalType.DATE)
    private Date rendelesDatum;
    @Size(max = 255)
    @Column(name = "rendeles_allapot")
    private String rendelesAllapot;
    @OneToMany(mappedBy = "rendelesId")
    private Collection<RendelesTetelek> rendelesTetelekCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Userek userId;
    @JoinColumn(name = "szallitasi_cim_id", referencedColumnName = "id")
    @ManyToOne
    private Lakcimek szallitasiCimId;
    @JoinColumn(name = "szallitas_id", referencedColumnName = "id")
    @ManyToOne
    private Szallitasok szallitasId;
    @JoinColumn(name = "kuponkod_id", referencedColumnName = "id")
    @ManyToOne
    private Kuponkodok kuponkodId;

    public Rendelesek() {
    }

    public Rendelesek(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRendelesDatum() {
        return rendelesDatum;
    }

    public void setRendelesDatum(Date rendelesDatum) {
        this.rendelesDatum = rendelesDatum;
    }

    public String getRendelesAllapot() {
        return rendelesAllapot;
    }

    public void setRendelesAllapot(String rendelesAllapot) {
        this.rendelesAllapot = rendelesAllapot;
    }

    public Collection<RendelesTetelek> getRendelesTetelekCollection() {
        return rendelesTetelekCollection;
    }

    public void setRendelesTetelekCollection(Collection<RendelesTetelek> rendelesTetelekCollection) {
        this.rendelesTetelekCollection = rendelesTetelekCollection;
    }

    public Userek getUserId() {
        return userId;
    }

    public void setUserId(Userek userId) {
        this.userId = userId;
    }

    public Lakcimek getSzallitasiCimId() {
        return szallitasiCimId;
    }

    public void setSzallitasiCimId(Lakcimek szallitasiCimId) {
        this.szallitasiCimId = szallitasiCimId;
    }

    public Szallitasok getSzallitasId() {
        return szallitasId;
    }

    public void setSzallitasId(Szallitasok szallitasId) {
        this.szallitasId = szallitasId;
    }

    public Kuponkodok getKuponkodId() {
        return kuponkodId;
    }

    public void setKuponkodId(Kuponkodok kuponkodId) {
        this.kuponkodId = kuponkodId;
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
        if (!(object instanceof Rendelesek)) {
            return false;
        }
        Rendelesek other = (Rendelesek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sneakr.sneakrproject.model.Rendelesek[ id=" + id + " ]";
    }
    
}
