
package controllers;

import ejb.CRUDBeanLocal;
import entitys.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "entityViewController")
@ViewScoped
public class EntityViewController
{

    @EJB
    private CRUDBeanLocal entityService;
    private List<Entity> entityList = new ArrayList<>();
    
    public List<Entity> getEntitys()
    {
        entityList = entityService.getAll();
        return entityList;
    }
    
}
