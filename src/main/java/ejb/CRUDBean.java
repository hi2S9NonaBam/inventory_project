package ejb;

import entitys.Entity;
import entitys.EntityType;
import entitys.Parameters;
import entitys.ParametersTypes;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@ManagedBean(name = "entityService")
public class CRUDBean implements CRUDBeanLocal {

    @PersistenceContext(unitName = "Inventory_NetCrackerPU")
    private EntityManager entityManager;
    
    
    @Override
    public List<Entity> getAll() {
        return entityManager.createQuery("select x from Entity x").getResultList();
    }

    @Override
    public void deleteEntity(Entity entity) {
        Entity tmp = entityManager.merge(entity);
        entityManager.remove(tmp);
    }

    @Override
    public List<ParametersTypes> getParametrsTypes() {
        return entityManager.createQuery("SELECT p FROM ParametersTypes p").getResultList();
    }

    @Override
    public List<EntityType> getEntityTypes() {
        return entityManager.createQuery("SELECT e FROM EntityType e").getResultList();
    }
    
    @Override
    public void addEntity(Entity entity)
    {
        entityManager.persist(entity);
    }

    @Override
    public void addEntity(String _entityType, String entityName, List<Parameters> parametrs) {
        if(entityName == null || _entityType == null)
            return;
        //EntityType в сервлете (заменить)
        EntityType entityType = (EntityType)entityManager.createQuery("SELECT x FROM EntityType x WHERE x.name = '" + _entityType + "'").getSingleResult();
        
        Entity entity = new Entity(entityName, null, entityType, null);
        entityManager.persist(entity);
        entityManager.flush();
        //System.err.println("EntityId = " + entity.getEntityId());
        /*
        *   Что-бы заперсистить параметры, сущность должна получить ключ.
        *   Может есть способ лучше?
        */
        for(Parameters tmp : parametrs)
        {
            tmp.getParametersPK().setEntityId(entity.getEntityId()+1);
        }
        entity.setParametersList(parametrs);
        
        entityManager.merge(entity);
    }

    @Override
    public void updateEntity(Entity editedEntity) {
        if(editedEntity == null) 
            return; //Throw exception (потом =))
        
            entityManager.merge(editedEntity);            
    }
}
