package com.coding.membersview.interfaces;

import com.coding.models.CouponDetails;
import com.coding.models.RequestObject;

import java.util.List;
import java.util.Map;

public interface MembersViewService {

    public List<CouponDetails> getMemberCouponDetails(RequestObject memberData) throws Exception;

    public void validateIncomingRequestObject(RequestObject memberData) throws Exception;

}