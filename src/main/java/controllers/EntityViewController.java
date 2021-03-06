
package controllers;

import ejb.CRUDBeanLocal;
import entitys.Entity;
import entitys.EntityType;
import entitys.ParametersType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "entityViewController")
@SessionScoped
public class EntityViewController
{

    @EJB
    private CRUDBeanLocal entityService;
    private List<Entity> entityList = new ArrayList<>();
    private List<EntityType> entityTypes;
    private List<ParametersType> parametersTypes;
    
    public List<Entity> getEntitys()
    {
        entityList = entityService.getAll();
        return entityList;
    }
    
}
