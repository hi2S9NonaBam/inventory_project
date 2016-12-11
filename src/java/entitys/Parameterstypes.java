package entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "PARAMETERSTYPES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parameterstypes.findAll", query = "SELECT p FROM Parameterstypes p")})
public class Parameterstypes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARAMETERSTYPES_ID")
    private BigDecimal parameterstypesId;
    
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    
    @Size(max = 10)
    @Column(name = "TYPE")
    private String type;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameterstypes")
    private List<Parameters> parametersList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameterstypesId")
    private List<entitys.List> listList;

    public Parameterstypes() {
    }

    public Parameterstypes(BigDecimal parameterstypesId) {
        this.parameterstypesId = parameterstypesId;
    }

    public BigDecimal getParameterstypesId() {
        return parameterstypesId;
    }

    public void setParameterstypesId(BigDecimal parameterstypesId) {
        this.parameterstypesId = parameterstypesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<Parameters> getParametersList() {
        return parametersList;
    }

    public void setParametersList(List<Parameters> parametersList) {
        this.parametersList = parametersList;
    }

    @XmlTransient
    public List<entitys.List> getListList() {
        return listList;
    }

    public void setListList(List<entitys.List> listList) {
        this.listList = listList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parameterstypesId != null ? parameterstypesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parameterstypes)) {
            return false;
        }
        Parameterstypes other = (Parameterstypes) object;
        if ((this.parameterstypesId == null && other.parameterstypesId != null) || (this.parameterstypesId != null && !this.parameterstypesId.equals(other.parameterstypesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Parameterstypes[ parameterstypesId=" + parameterstypesId + " ]";
    }
    
}