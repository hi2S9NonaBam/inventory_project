package testserv;

import ejb.CRUDBeanLocal;
import entitys.Entity;
import entitys.EntityType;
import entitys.Entity_;
import entitys.Parameter;
import entitys.ParameterPK;
import entitys.ParametersType;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author torne
 */
@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @EJB
    CRUDBeanLocal crud;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<ParametersType> parametersTypes = crud.getParametrsTypes();
        List<EntityType> entityTypes = crud.getEntityTypes();
        
        System.err.println(request.getParameter("parametrType"));
        System.err.println(request.getParameter("entityName"));
        
        if((request.getParameter("parametrType") != null) && (request.getParameter("entityName") != null))
        {
            

            List<Parameter> parametrsList = new ArrayList<>();



            parametersTypes.forEach((type) -> 
                    {
                        if(type.getName().compareTo(request.getParameter("parametrType")) == 0)
                        {
                            Parameter tmp = new Parameter(type);
                            tmp.setValueChar(request.getParameter("parametr"));
                            parametrsList.add(tmp);
                            
                            return;
                        }
                    });

            entitys.Entity entity = new Entity(request.getParameter("entityName"), null, entityTypes.get(0));
            crud.saveEntity(entity, parametrsList);
        }
        
        //System.out.println(request.getParameter("entityName"));
        
//        List<Parameter> parameters = new ArrayList<>();
//        
//        for(int i = 0; i<1; i++)
//        {
//            Parameter tmp = new Parameter();
//            //tmp.setParametersPK(new ParameterPK(0, 6));
//            tmp.setValueChar(request.getParameter("parametr"));
//            parameters.add(tmp);
//        }
        
        //crud.addEntity(request.getParameter("entityType"), request.getParameter("entityName"), parameters);
        
        
        
        try (PrintWriter out = response.getWriter()) {
            List<Entity> entitys= crud.getAll();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>KKKKKKKKK</h1>");
            out.println("<form action=\"TestServlet\">"
                        + "<h1>Добавить сущность</h1><br>"
                        + "<label>Сущность  </label>"
                        + "<select name=\"entityType\">");
            for(EntityType tmp : entityTypes)
                out.println("<option>"+ tmp.getName() +"</option>\n");
            out.println("</select><input type=\"text\" name=\"entityName\" value=\"\" />");
            
            out.println("<br><label>Параметр  </label>"
                        + "<select name=\"parametrType\">");
            for(ParametersType tmp : parametersTypes)
                out.println("<option>"+ tmp.getName() +"</option>\n");
            out.println("</select><input type=\"text\" name=\"parametr\" value=\"\" />");
            
            out.println("<br><input type=\"submit\" name=\"ok\" />"
                    + "</form><br><br><br>");

            out.println("<table border=\"5\">" +
            "<thead>" +
                "<tr>" +
                    "<th>Название сущности</th>" +
                    "<th>Тип сущности</th>" +
                    "<th>Тип параметра</th>" +
                    "<th>Параметр ДАТА</th>" +
                    "<th>Параметр CLOB</th>" +
                    "<th>Параметр СТРОКА</th>" +
                    "<th>Параметр СПИСОК</th>" +
                "</tr>" +
            "</thead>" +
            "<tbody>");
            for(Entity tmp : entitys)
            {
                out.println("<tr>");
                out.println("<td>" + tmp.getName() + "</td>");
                out.println("<td>" + tmp.getEntitytypeId().getName() + "</td>");
                for(Parameter param : tmp.getParametersList())
                {
                    out.println("<td>" + param.getParameterstypes().getName()+ "</td>");
                    out.println("<td>" + param.getValueDate()+ "</td>");
                    out.println("<td>" + param.getValueClob()+ "</td>");
                    out.println("<td>" + param.getValueChar()+ "</td>");
                    if(param.getListId() != null)
                    {
                        out.println("<td>" + param.getListId().getValue() + "</td>");
                    }
                    else
                        out.println("<td>NULL</td>");
                    out.println("</tr><tr><td></td><td></td>");
                }
                out.println("</tr>");
            }
            //out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            out.println("</tbody>" +
                "</table>");
            //crud.deleteEntity(entitys.get(entitys.size()-1));
            //crud.deleteEntity(entitys.get(entitys.size()-2));
            //entitys.get(0).setName("KEK2");
            //entitys.get(0).getParametersList().get(1).setValueChar("KEKEKEKE");
            //crud.updateEntity(entitys.get(0));
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
