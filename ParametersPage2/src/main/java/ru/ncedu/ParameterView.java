package ru.ncedu;

import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "dtParameterView")
@ViewScoped
public class ParameterView {
    private List<Parameter> parameters = new ArrayList<>();

    @ManagedProperty("#{parameterService}")
    private ParameterService parameterService;

    public List<Parameter> getParameters() {
        return parameters;
    }

    public List<Object> getListValues(Parameter parameter) {
        List<Object> list = new ArrayList<>(parameter.getListValues().values());
        return list;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @PostConstruct
    public void init() {
        parameters = parameterService.getAllParameters();
    }

    public void onRowEdit(RowEditEvent event) {
        getParameterService().updateParameter((Parameter)event.getObject());
    }

    public void onRowCancel(RowEditEvent event) {

    }
}
