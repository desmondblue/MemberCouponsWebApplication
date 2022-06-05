package com.coding.models;


import java.sql.Date;
import java.time.LocalDateTime;


public class CouponDetails {

    private String coupon_name;
    private String coupon_description;
    private Date valid_from;
    private Date valid_until;
    private Double latitude;
    private Double longitude;
    private double distance;

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_description() {
        return coupon_description;
    }

    public void setCoupon_description(String coupon_description) {
        this.coupon_description = coupon_description;
    }

    public Date getValid_from() {
        return valid_from;
    }

    public void setValid_from(Date valid_from) {
        this.valid_from = valid_from;
    }

    public Date getValid_until() {
        return valid_until;
    }

    public void setValid_until(Date valid_until) {
        this.valid_until = valid_until;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void calculate_distance(double latitude, double longitude)
    {
        /*
        Formula for calculating distance between two longitudes and latitudes taken from online reference
         */

        this.longitude = Math.toRadians(this.longitude);
        longitude = Math.toRadians(longitude);
        this.latitude = Math.toRadians(this.latitude);
        latitude= Math.toRadians(latitude);

        // Haversine formula
        double dlon = longitude - this.longitude;
        double dlat = latitude - this.latitude;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(this.latitude) * Math.cos(latitude)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        this.distance = (c * r);
    }



}
