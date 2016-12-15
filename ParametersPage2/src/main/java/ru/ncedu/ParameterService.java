package ru.ncedu;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.*;

@ManagedBean(name = "parameterService")
@ApplicationScoped
public class ParameterService {
     Connection connection = null;
     Statement statement = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;

    static {
        Locale.setDefault(Locale.US);

    }

    public  void initConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "inventory",
                    "inventory");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Parameter> getAllParameters() {
        List<Parameter> list = new ArrayList<>();
        try {
            initConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * from parameters NATURAL JOIN parameterstypes");
            while (resultSet.next()) {
                list.add(ParameterFactory.getParameter(resultSet));
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return list;
    }

    public void updateParameter(Parameter parameter) {
        try {
            initConnection();
            String updateRowStmnt = String.format("UPDATE PARAMETERS SET %s = ? WHERE ENTITY_ID = ? AND PARAMETERSTYPES_ID = ?", parameter.getValueColumnName());
            preparedStatement = connection.prepareStatement(updateRowStmnt);
            preparedStatement.setInt(2, parameter.getEntityID());
            preparedStatement.setInt(3, parameter.getParametersTypesId());
            Object val = parameter.getValue();
            Parameter.Type type = parameter.getType();
            if (type == Parameter.Type.DATE) {
                java.sql.Date sqlDate = new java.sql.Date(((java.util.Date) val).getTime());
                preparedStatement.setDate(1, sqlDate);
            } else if (type == Parameter.Type.CHAR) {
                preparedStatement.setString(1, (String) val);
            } else if (type == Parameter.Type.CLOB) {
                Clob clob = connection.createClob();
                clob.setString(1, (String) val);
                preparedStatement.setClob(1, clob);
            } else if (type == Parameter.Type.LIST){
                int newListID = parameter.getListIdByValue();
                preparedStatement.setInt(1, newListID);
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }



}
