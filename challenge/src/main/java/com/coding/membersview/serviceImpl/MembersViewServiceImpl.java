package com.coding.membersview.serviceImpl;
import com.coding.membersview.interfaces.MembersViewService;
import com.coding.membersview.dao.MembersViewDAOImpl;
import com.coding.models.CouponDetails;
import com.coding.models.DistanceComparisson;
import com.coding.models.RequestObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MembersViewServiceImpl implements MembersViewService {

    Logger logger = LogManager.getLogger(MembersViewServiceImpl.class);
    @Autowired
    MembersViewDAOImpl membersViewDAO;
    /*
    Method for fetching coupons by member id - sortedBy validUntil descending and then sorted by nearest distance to given coordinates
     */
    @Override
    public List<CouponDetails> getMemberCouponDetails(RequestObject memberData) throws Exception {
        logger.debug("Inside getMemberCouponDetails service method call");
        try {
            List<CouponDetails> couponsForMember = membersViewDAO.getMemberCouponDetails(memberData);
            /*
            To sort result set by nearest distance to incoming coordinates
             */
            Collections.sort(couponsForMember, new DistanceComparisson());
            return couponsForMember;
        }catch(Exception exception){
            logger.error("Exception occured inside getMemberCouponDetails service method",exception);
            throw exception;
            }
    }
    @Override
    public void validateIncomingRequestObject(RequestObject memberData) throws Exception{
        memberData.validate_request_object();
        membersViewDAO.validateMemberId(memberData.getMemberId());
    }
}
