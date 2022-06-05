package com.coding.models;

import java.util.Date;

public class RequestObject {

    Integer memberId;
    Double latitude;
    Double longitude;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void validate_request_object() throws Exception {
        if((!this.latitude.isNaN() && this.memberId != null && !this.longitude.isNaN()));
        else{
            throw new Exception("Incoming parameters are coming with null value");
        }
    }

}
