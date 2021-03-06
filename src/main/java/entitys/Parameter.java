package entitys;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PARAMETERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parameter.findAll", query = "SELECT p FROM Parameter p")})
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParameterPK parametersPK;
    
    @Column(name = "VALUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valueDate;
    
    @Size(max = 50)
    @Column(name = "VALUE_CHAR")
    private String valueChar;
    
    @Lob
    @Column(name = "VALUE_CLOB")
    private String valueClob;
    
    @JoinColumn(name = "ENTITY_ID", referencedColumnName = "ENTITY_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private entitys.Entity entity;
    
    @JoinColumn(name = "LIST_ID", referencedColumnName = "LIST_ID")
    @ManyToOne
    private List listId;
    
    @JoinColumn(name = "PARAMETERSTYPES_ID", referencedColumnName = "PARAMETERSTYPES_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ParametersType parameterstype;

    public Parameter() 
    {
    }

    /**
     * Создает параметр.
     * (Не персистить отдельно от сущности! Используется составной ключ, 
     * который становится полным только при добавлении параметра в сущность)
     * @param parametersType тип параметра
     */
    public Parameter(ParametersType parametersType) 
    {
        this.parameterstype = parametersType;
        parametersPK = new ParameterPK(parametersType.getParameterstypesId());
    }

    public ParameterPK getParametersPK() 
    {
        return parametersPK;
    }

    public void setParametersPK(ParameterPK parametersPK) 
    {
        this.parametersPK = parametersPK;
    }

    public Date getValueDate() 
    {
        return valueDate;
    }

    public void setValueDate(Date valueDate) 
    {
        this.valueDate = valueDate;
    }

    public String getValueChar() 
    {
        return valueChar;
    }

    public void setValueChar(String valueChar) 
    {
        this.valueChar = valueChar;
    }

    public String getValueClob() 
    {
        return valueClob;
    }

    public void setValueClob(String valueClob) 
    {
        this.valueClob = valueClob;
    }

    public entitys.Entity getEntity() 
    {
        return entity;
    }

    public void setEntity(entitys.Entity entity) 
    {
        this.entity = entity;
    }

    public List getListId() 
    {
        return listId;
    }

    public void setListId(List listId) 
    {
        this.listId = listId;
    }

    public ParametersType getParameterstypes() 
    {
        return parameterstype;
    }

    public void setParameterstypes(ParametersType parameterstypes) 
    {
        this.parameterstype = parameterstypes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametersPK != null ? parametersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parameter)) {
            return false;
        }
        Parameter other = (Parameter) object;
        if ((this.parametersPK == null && other.parametersPK != null) || (this.parametersPK != null && !this.parametersPK.equals(other.parametersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Parameters[ parametersPK=" + parametersPK + " ]";
    }
    
}
