package entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
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

@Entity
@Table(name = "LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "List.findAll", query = "SELECT l FROM List l")})
public class List implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIST_ID")
    private BigDecimal listId;
    
    @Size(max = 20)
    @Column(name = "VALUE")
    private String value;
    
    @OneToMany(mappedBy = "listId")
    private java.util.List<Parameters> parametersList;
    
    @JoinColumn(name = "PARAMETERSTYPES_ID", referencedColumnName = "PARAMETERSTYPES_ID")
    @ManyToOne(optional = false)
    private ParametersTypes parameterstypesId;

    public List() {
    }

    public List(BigDecimal listId) {
        this.listId = listId;
    }

    public BigDecimal getListId() {
        return listId;
    }

    public void setListId(BigDecimal listId) {
        this.listId = listId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlTransient
    public java.util.List<Parameters> getParametersList() {
        return parametersList;
    }

    public void setParametersList(java.util.List<Parameters> parametersList) {
        this.parametersList = parametersList;
    }

    public ParametersTypes getParameterstypesId() {
        return parameterstypesId;
    }

    public void setParameterstypesId(ParametersTypes parameterstypesId) {
        this.parameterstypesId = parameterstypesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listId != null ? listId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof List)) {
            return false;
        }
        List other = (List) object;
        if ((this.listId == null && other.listId != null) || (this.listId != null && !this.listId.equals(other.listId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.List[ listId=" + listId + " ]";
    }
    
}
