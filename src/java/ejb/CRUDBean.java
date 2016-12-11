package ejb;

import entitys.Entity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CRUDBean implements CRUDBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Entity> getAll() {
        return entityManager.createQuery("select x from Entity x").getResultList();
    }

    @Override
    public void addEntity(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
