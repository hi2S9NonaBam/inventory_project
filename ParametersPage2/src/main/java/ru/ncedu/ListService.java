package ru.ncedu;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ListService extends ParameterService{

    public Map<Integer, Object> getListValues(int parametersTypesID) {
        initConnection();
        Map<Integer, Object> result = new HashMap<>();
        String query = "SELECT LIST_ID, VALUE FROM LIST WHERE PARAMETERSTYPES_ID = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, parametersTypesID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.put(resultSet.getInt("LIST_ID"), resultSet.getObject("VALUE"));
            }
            preparedStatement.close();
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
        return result;
    }
}
