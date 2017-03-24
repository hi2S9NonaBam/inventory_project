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
    @NamedQuery(name = "EntityType.findAll", query = "SELECT e FROM EntityType e")})
public class EntityType implements Serializable {

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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityType")
    private List<entitys.Entity> entityList;
    
    @OneToMany(mappedBy = "parentId")
    private List<EntityType> entitytypeList;
    
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ENTITYTYPE_ID")
    @ManyToOne
    private EntityType parentId;

    public EntityType() {
    }

    public EntityType(long entitytypeId) {
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
    public List<EntityType> getEntitytypeList() {
        return entitytypeList;
    }

    public void setEntitytypeList(List<EntityType> entitytypeList) {
        this.entitytypeList = entitytypeList;
    }

    public EntityType getParentId() {
        return parentId;
    }

    public void setParentId(EntityType parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "entitys.Entitytype[ entitytypeId=" + entitytypeId + " ]";
    }
    
}
