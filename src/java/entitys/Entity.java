package entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@javax.persistence.Entity
@Table(name = "ENTITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entity.findAll", query = "SELECT e FROM Entity e")})
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTITY_ID")
    private BigDecimal entityId;
    
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    
    @OneToMany(mappedBy = "parentId")
    private List<Entity> entityList;
    
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ENTITY_ID")
    @ManyToOne
    private Entity parentId;
    
    @JoinColumn(name = "ENTITYTYPE_ID", referencedColumnName = "ENTITYTYPE_ID")
    @ManyToOne(optional = false)
    private Entitytype entitytypeId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entity")
    private List<Parameters> parametersList;

    public Entity() {
    }

    public Entity(BigDecimal entityId) {
        this.entityId = entityId;
    }

    public BigDecimal getEntityId() {
        return entityId;
    }

    public void setEntityId(BigDecimal entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public Entity getParentId() {
        return parentId;
    }

    public void setParentId(Entity parentId) {
        this.parentId = parentId;
    }

    public Entitytype getEntitytypeId() {
        return entitytypeId;
    }

    public void setEntitytypeId(Entitytype entitytypeId) {
        this.entitytypeId = entitytypeId;
    }

    @XmlTransient
    public List<Parameters> getParametersList() {
        return parametersList;
    }

    public void setParametersList(List<Parameters> parametersList) {
        this.parametersList = parametersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entityId != null ? entityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entity)) {
            return false;
        }
        Entity other = (Entity) object;
        if ((this.entityId == null && other.entityId != null) || (this.entityId != null && !this.entityId.equals(other.entityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Entity[ entityId=" + entityId + " ]";
    }
    
}
