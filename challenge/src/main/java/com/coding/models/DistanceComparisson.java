package com.coding.models;

import java.util.Comparator;

public class DistanceComparisson implements Comparator<CouponDetails> {
    /*
    Comparator to help sort List of CouponDetails objects based on the distance from the incoming coordinates
     */
    @Override
    public int compare(CouponDetails coupon1, CouponDetails coupon2) {
        if (coupon1.getDistance()>coupon2.getDistance()){
            return 1;
        } else if (coupon1.getDistance()==coupon2.getDistance()) {
            return 0;

        } else
            return -1;

    }
}
