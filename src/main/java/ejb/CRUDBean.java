package ejb;

import entitys.Entity;
import entitys.EntityType;
import entitys.Parameter;
import entitys.ParametersType;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CRUDBean implements CRUDBeanLocal {

    @PersistenceContext(unitName = "ru.netcracker_Inventorka")
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
    public List<ParametersType> getParametrsTypes() {
        return (List<ParametersType>)entityManager.createQuery("SELECT p FROM ParametersType p").getResultList();
    }

    @Override
    public List<EntityType> getEntityTypes() {
        return entityManager.createQuery("SELECT e FROM EntityType e").getResultList();
    }
    
    @Override
    public void saveEntity(Entity entity, List<Parameter> parameters)
    {
        entityManager.persist(entity);
        parameters.forEach(entity::addParametr);
        entityManager.merge(entity);
    }

    @Override
    public void updateEntity(Entity editedEntity) {
        if(editedEntity == null) 
            return; //Throw exception (потом =))
        
            entityManager.merge(editedEntity);            
    }
}
