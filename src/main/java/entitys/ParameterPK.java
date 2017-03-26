package entitys;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ParameterPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTITY_ID")
    private Long entityId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARAMETERSTYPES_ID")
    private long parameterstypesId;

    public ParameterPK() {
    }

    public ParameterPK(long parameterstypesId) {
        this.parameterstypesId = parameterstypesId;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public long getParameterstypesId() {
        return parameterstypesId;
    }

    public void setParameterstypesId(long parameterstypesId) {
        this.parameterstypesId = parameterstypesId;
    }

    @Override
    public String toString() {
        return "entitys.ParametersPK[ entityId=" + entityId + ", parameterstypesId=" + parameterstypesId + " ]";
    }
    
}
