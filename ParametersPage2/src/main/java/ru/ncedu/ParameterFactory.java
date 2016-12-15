package ru.ncedu;

import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class ParameterFactory {
    public static Parameter getParameter(ResultSet rs) {
        Parameter parameter = new Parameter();
        Parameter.Type type;
        Map<Integer, Object> map;


        try {
            parameter.setName(rs.getString("NAME"));
            parameter.setEntityID(rs.getInt("ENTITY_ID"));
            type = Parameter.Type.valueOf((rs.getString("TYPE")).toUpperCase());
            parameter.setType(type);
            parameter.setParametersTypesId(rs.getInt("PARAMETERSTYPES_ID"));
            switch (type) {
                case DATE:
                    parameter.setValue(rs.getDate("VALUE_DATE"));
                    parameter.setValueColumnName("VALUE_DATE");
                    break;
                case CHAR:
                    parameter.setValue(rs.getString("VALUE_CHAR"));
                    parameter.setValueColumnName("VALUE_CHAR");
                    break;
                case LIST:
                    parameter.setValueColumnName("LIST_ID");
                    int listID = rs.getInt("LIST_ID");
                    ListService listService = new ListService();
                    map = listService.getListValues(parameter.getParametersTypesId());
                    parameter.setValue(map.get(listID));
                    parameter.setListValues(map);
                    break;
                case CLOB:
                    parameter.setValue(rs.getString("VALUE_CLOB"));
                    parameter.setValueColumnName("VALUE_CLOB");
                    break;
                default:
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parameter;
    }
}
