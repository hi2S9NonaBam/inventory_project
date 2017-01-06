package ejb;

import entitys.*;
import java.util.List;

import javax.ejb.Local;
@Local
public interface CRUDBeanLocal {
    List<Entity> getAll();
    List<Parameterstypes> getParametrsTypes();
    List<Entitytype> getEntityTypes();
    void addEntity(Entity entity);
    
    /**
     * Добавить сущность в базу
     * @param _entityType пока в виде строки. Т.к. список типов будет 
     *        вытягиваться с сервера в виде списка сущностей (Entitytype), то 
     *        надо будет заменить на сущность.
     * @param entityName Имя сущности
     * @param parametrs Список параметров. Думаю, что лучше заменить на список 
     *        пар (Parameterstypes, Object)
     */
    void addEntity(String _entityType, String entityName, List<Parameters> parametrs);
    
    void updateEntity(Entity editedEntity);
    void deleteEntity(Entity entity);
}
