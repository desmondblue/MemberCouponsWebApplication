package com.coding.challenge;

import com.coding.membersview.dao.MembersViewDAOImpl;
import com.coding.membersview.interfaces.MembersViewDao;
import com.coding.models.CouponDetails;
import com.coding.models.RequestObject;
import com.coding.models.ResponseObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ChallengeApplicationDAOTests {
	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Mock
	DataSource primaryDataSource;
	@InjectMocks
	public MembersViewDao membersViewDAO = new MembersViewDAOImpl();


	@Test
	public void testCouponValidity() throws Exception {

		/*
		For member id 2 there is an invalid coupon(couponId: 4) for which currentDate  is greater than validUntil
		Hence if the coupon is returned as part of the call then test fails otherwise successful
		 */
		RequestObject requestObject_test = new RequestObject();
		requestObject_test.setLatitude(80.0);
		requestObject_test.setLongitude(50.0);
		requestObject_test.setMemberId(2);

		//Populating expected response for Assertion
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
//		Getting actual response from the service
		ResponseObject actualResponse = new ResponseObject();
		Mockito.when(namedParameterJdbcTemplate.query(Mockito.anyString(),Mockito.any(MapSqlParameterSource.class),Mockito.any(ResultSetExtractor.class))).thenReturn(couponDetailsList);

		List<CouponDetails> couponDetailsList_actual = membersViewDAO.getMemberCouponDetails(requestObject_test);

		Assertions.assertEquals(couponDetailsList,couponDetailsList_actual);
	}
//	@Test
//	test
}
