package com.coding.challenge;

import com.coding.membersview.dao.MembersViewDAOImpl;
import com.coding.membersview.interfaces.MembersViewDao;
import com.coding.membersview.interfaces.MembersViewService;
import com.coding.membersview.serviceImpl.MembersViewServiceImpl;
import com.coding.models.CouponDetails;
import com.coding.models.RequestObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ChallengeApplicationServiceTests {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Mock
    MembersViewDAOImpl membersViewDao;
    @InjectMocks
    MembersViewServiceImpl membersViewService = new MembersViewServiceImpl();
/*

        Junit Test for checking sorted result set per distance to the coordinates being returned
 */
    @Test
    public void testCouponValidityAndSorting() throws Exception {
//        Test request Object
        RequestObject requestObject_test = new RequestObject();
        requestObject_test.setLatitude(80.0);
        requestObject_test.setLongitude(50.0);
        requestObject_test.setMemberId(2);

        //Populating expected response for Assertion - Array List stores order of insertion hence can be used to simulate sorted result
        List<CouponDetails> couponDetailsList = new ArrayList<>();
        CouponDetails cp1 = new CouponDetails();
        cp1.setCoupon_name("Flat 30");
        cp1.setCoupon_description("Flat 30% off for purchases more than EUR 2000");
        cp1.setValid_from(Date.valueOf("2022-02-21"));
        cp1.setValid_until(Date.valueOf("2022-07-19"));
        cp1.setLongitude(1.2313297872819995);
        cp1.setLatitude(1.5280357601210355);
        cp1.setDistance(4179.2660119790435);
        CouponDetails cp2 = new CouponDetails();
        cp2.setCoupon_name("BOGO");
        cp2.setCoupon_description("Buy One Get One with your purchase");
        cp2.setValid_from(Date.valueOf("2022-01-01"));
        cp2.setValid_until(Date.valueOf("2022-10-30"));
        cp2.setLongitude(-0.7077310116837006);
        cp2.setLatitude(1.5803956376808654);
        cp2.setDistance(4416.97277098985);
        couponDetailsList.add(cp1);
        couponDetailsList.add(cp2);
        Mockito.when(membersViewDao.getMemberCouponDetails((requestObject_test))).thenReturn(couponDetailsList);
        List<CouponDetails> actualList = membersViewService.getMemberCouponDetails(requestObject_test);
        Assertions.assertEquals(couponDetailsList,actualList);
    }

}
