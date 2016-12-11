package entitys;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ParametersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTITY_ID")
    private BigInteger entityId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARAMETERSTYPES_ID")
    private BigInteger parameterstypesId;

    public ParametersPK() {
    }

    public ParametersPK(BigInteger entityId, BigInteger parameterstypesId) {
        this.entityId = entityId;
        this.parameterstypesId = parameterstypesId;
    }

    public BigInteger getEntityId() {
        return entityId;
    }

    public void setEntityId(BigInteger entityId) {
        this.entityId = entityId;
    }

    public BigInteger getParameterstypesId() {
        return parameterstypesId;
    }

    public void setParameterstypesId(BigInteger parameterstypesId) {
        this.parameterstypesId = parameterstypesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entityId != null ? entityId.hashCode() : 0);
        hash += (parameterstypesId != null ? parameterstypesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametersPK)) {
            return false;
        }
        ParametersPK other = (ParametersPK) object;
        if ((this.entityId == null && other.entityId != null) || (this.entityId != null && !this.entityId.equals(other.entityId))) {
            return false;
        }
        if ((this.parameterstypesId == null && other.parameterstypesId != null) || (this.parameterstypesId != null && !this.parameterstypesId.equals(other.parameterstypesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.ParametersPK[ entityId=" + entityId + ", parameterstypesId=" + parameterstypesId + " ]";
    }
    
}
