package entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTITY_ID")
    @GeneratedValue(generator = "entitySeq")
    @SequenceGenerator(name="entitySeq",sequenceName="ENTITY_SEQ", allocationSize=1)
    private Long entityId;
    
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    
    @OneToMany(mappedBy = "parentEntity")
    private List<Entity> childEntitysList;   
    
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ENTITY_ID")
    @ManyToOne
    private Entity parentEntity;
    
    @JoinColumn(name = "ENTITYTYPE_ID", referencedColumnName = "ENTITYTYPE_ID")
    @ManyToOne(optional = false)
    private EntityType entityType;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entity")
    private List<Parameter> parametersList;
    
    public Entity()
    {
    }

    public Entity(String entityName, Entity parent, EntityType entityType, List<Parameter> parameters) {
        this.entityType = entityType;
        this.name = entityName;
        this.parentEntity = parent;
        if(parameters != null)
            parameters.forEach(Entity::addParametr);
    }
    
    public void addChild(Entity child)
    {
        if(childEntitysList == null)
            childEntitysList = new ArrayList<>();
        
        child.setParentEntity(this);
        childEntitysList.add(child);
    }
    
    public void addParametr(Parameter parameter)
    {
        if(parametersList == null)
            parametersList = new ArrayList<>();
        
        parameter.parametersPK.setEntityId(this.entityId);
        parameter.setEntity(this);
        
        parametersList.add(parameter);
    }

    public long getEntityId() 
    {
        return entityId;
    }

    public void setEntityId(long entityId) 
    {
        this.entityId = entityId;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    @XmlTransient
    public List<Entity> getEntityList() 
    {
        return childEntitysList;
    }

    public Entity getParentEntity() 
    {
        return parentEntity;
    }

    public void setParentEntity(Entity parent) 
    {
        this.parentEntity = parent;
    }

    public EntityType getEntitytypeId() 
    {
        return entityType;
    }

    public void setEntityType(EntityType entityType) 
    {
        this.entityType = entityType;
    }

    @XmlTransient
    public List<Parameter> getParametersList() 
    {
        return parametersList;
    }

    public void setParametersList(List<Parameter> parametersList) 
    {
        this.parametersList = parametersList;
    }

    @Override
    public String toString() 
    {
        return "entitys.Entity[ entityId=" + entityId + " ]";
    }
    
}
