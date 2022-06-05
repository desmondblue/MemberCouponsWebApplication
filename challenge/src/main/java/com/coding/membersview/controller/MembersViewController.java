package com.coding.membersview.controller;

import com.coding.constants.MembersCouponsConstants;
import com.coding.membersview.serviceImpl.MembersViewServiceImpl;
import com.coding.models.CouponDetails;
import com.coding.models.RequestObject;
import com.coding.models.ResponseObject;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/coupons")
public class MembersViewController {

    private static final Logger logger = LogManager.getLogger(MembersViewController.class);
    @Autowired
    private MembersViewServiceImpl membersViewServiceImpl;
    /*
    Method for REST API call to get member coupons
     */
    @RequestMapping(value="/get-member-coupons",method=RequestMethod.POST)
    public  ResponseObject getMemberCoupons(@RequestBody RequestObject memberData)
    {
        ResponseObject responseObject = new ResponseObject();
        try{
            // Incoming Data Validation : Not null on incoming data fields / valid member id.
        membersViewServiceImpl.validateIncomingRequestObject(memberData);
        List<CouponDetails> returnData = membersViewServiceImpl.getMemberCouponDetails(memberData);
        responseObject.setStatus(MembersCouponsConstants.RESPONSE_SUCCESS);
        responseObject.setReturn_data(returnData);
        }catch(Exception exception){
            logger.error("Error occured in members view service controller",exception);
            responseObject.setStatus(MembersCouponsConstants.RESPONSE_FAILURE);
            responseObject.setReturn_data(exception.getMessage());
        }
        return responseObject;
    }

}
