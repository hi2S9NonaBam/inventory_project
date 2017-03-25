package ejb;

import entitys.*;
import java.util.List;

import javax.ejb.Local;
@Local
public interface CRUDBeanLocal {
    
    List<Entity> getAll();
    
    List<ParametersType> getParametrsTypes();
    
    List<EntityType> getEntityTypes();
    
    void addEntity(Entity entity);
    
    void addEntity(String _entityType, String entityName, List<Parameter> parametrs);
    
    void updateEntity(Entity editedEntity);
    void deleteEntity(Entity entity);
}
