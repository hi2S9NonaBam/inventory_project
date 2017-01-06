package entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author torne
 */
@Entity
@Table(name = "ENTITYTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entitytype.findAll", query = "SELECT e FROM Entitytype e")})
public class Entitytype implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTITYTYPE_ID")
    private long entitytypeId;
    
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entitytypeId")
    private List<entitys.Entity> entityList;
    
    @OneToMany(mappedBy = "parentId")
    private List<Entitytype> entitytypeList;
    
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ENTITYTYPE_ID")
    @ManyToOne
    private Entitytype parentId;

    public Entitytype() {
    }

    public Entitytype(long entitytypeId) {
        this.entitytypeId = entitytypeId;
    }

    public long getEntitytypeId() {
        return entitytypeId;
    }

    public void setEntitytypeId(long entitytypeId) {
        this.entitytypeId = entitytypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<entitys.Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<entitys.Entity> entityList) {
        this.entityList = entityList;
    }

    @XmlTransient
    public List<Entitytype> getEntitytypeList() {
        return entitytypeList;
    }

    public void setEntitytypeList(List<Entitytype> entitytypeList) {
        this.entitytypeList = entitytypeList;
    }

    public Entitytype getParentId() {
        return parentId;
    }

    public void setParentId(Entitytype parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "entitys.Entitytype[ entitytypeId=" + entitytypeId + " ]";
    }
    
}
