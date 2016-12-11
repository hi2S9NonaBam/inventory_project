package ejb;

import entitys.*;
import java.util.List;

import javax.ejb.Local;
@Local
public interface CRUDBeanLocal {
    List<Entity> getAll();
    void addEntity(Entity entity);
    void deleteEntity();
}
