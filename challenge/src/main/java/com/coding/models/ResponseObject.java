package com.coding.models;
/*
    A response Object Class - Class is Accessible to integrate on UI
 */
public class ResponseObject<Template>{

    private String status;
    private Template return_data;

    public Template getReturn_data() {
        return return_data;
    }

    public void setReturn_data(Template return_data) {
        this.return_data = return_data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
