package com.coding.membersview.interfaces;

import com.coding.models.CouponDetails;
import com.coding.models.RequestObject;

import java.util.List;

public interface MembersViewDao {


    List<CouponDetails> getMemberCouponDetails(RequestObject memberData) throws Exception;
    public void validateMemberId(Integer memberId) throws Exception;
}
