package ejb;

import entitys.*;
import java.util.List;

import javax.ejb.Local;
@Local
public interface CRUDBeanLocal {
    /**
     * Получает список всех сущностей из БД.
     * @return список Entity
     */
    List<Entity> getAll();
    
    /**
     * Вспомогательный метод. Получает список типов параметров.
     * @return список ParametersType
     */
    List<ParametersType> getParametrsTypes();
    
    /**
     * Вспомогательный метод. Получает список типов сущностей.
     * @return список EntityType
     */
    List<EntityType> getEntityTypes();
    
    /**
     * Добавление сущности в БД.
     * @param entity сущность, которую вставляем
     * @param parameters список её параметров
     */
    void saveEntity(Entity entity, List<Parameter> parameters);
    
    /**
     * Обновляет сущность в БД.
     * @param editedEntity измененная сущность
     */
    void updateEntity(Entity editedEntity);
    
    /**
     * TODO: Доработать этот метод.
     * @param entity 
     */
    void deleteEntity(Entity entity);
}
