package ru.ncedu;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Parameter {
    private String name;
    private int parametersTypesId;
    private Type type;
    private int entityID;
    private Object value;
    private String valueColumnName;
    private Map<Integer, Object> listValues;

    public HashMap<Integer, Object> getListValues() {
        return (HashMap) listValues;
    }

    public void setListValues(Map<Integer, Object> listValues) {
        this.listValues = listValues;
    }

    public int getParametersTypesId() {
        return parametersTypesId;
    }

    public void setParametersTypesId(int parametersTypesId) {
        this.parametersTypesId = parametersTypesId;
    }

    public String getValueColumnName() {
        return valueColumnName;
    }

    public void setValueColumnName(String valueColumnName) {
        this.valueColumnName = valueColumnName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public Parameter() {
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    enum Type {
        DATE, CHAR, LIST, CLOB
    }

    public int getListIdByValue() {
        int result = 0;
        for (Map.Entry<Integer, Object> entry : listValues.entrySet()) {
            if (entry.getValue().equals(value)) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }
}