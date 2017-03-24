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
    private long entityId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARAMETERSTYPES_ID")
    private long parameterstypesId;

    public ParametersPK() {
    }

    public ParametersPK(long entityId, long parameterstypesId) {
        this.entityId = entityId;
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
