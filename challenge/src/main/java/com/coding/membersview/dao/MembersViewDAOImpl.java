package com.coding.membersview.dao;

import com.coding.membersview.interfaces.MembersViewDao;
import com.coding.models.CouponDetails;
import com.coding.models.RequestObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class MembersViewDAOImpl implements MembersViewDao {

    Logger logger = LogManager.getLogger(MembersViewDAOImpl.class);
    @Autowired
    protected DataSource primaryDataSource;

    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @PostConstruct
    public void init_template(){

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(primaryDataSource);
    }

    private String getMemberCouponDetails = "select * from member_coupon_map_view where member_id =:memberId;";
    private String validateMemberId = "select member_id from member_coupons.member_details where member_id =:memberId";
    public String getGetMemberCouponDetails() {
        return getMemberCouponDetails;
    }

    public void setGetMemberCouponDetails(String getMemberCouponDetails) {
        this.getMemberCouponDetails = getMemberCouponDetails;
    }

    /*
        Method for fetching coupons by member id from database sorted
     */
    public List<CouponDetails> getMemberCouponDetails(RequestObject memberData) throws Exception {
        logger.debug("Inside getMemberCouponDetails dao method call");
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("memberId", memberData.getMemberId());
            return this.namedParameterJdbcTemplate.query(getMemberCouponDetails, params, new ResultSetExtractor<List<CouponDetails>>() {

                @Override
                public List<CouponDetails> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    List<CouponDetails> memberCouponDetails = new ArrayList<>();
                    while (rs.next()) {
                        CouponDetails coupon = new CouponDetails();
                        coupon.setCoupon_name(rs.getString("coupon_name"));
                        coupon.setCoupon_description(rs.getString("coupon_description"));
                        coupon.setLatitude(rs.getDouble("latitude"));
                        coupon.setLongitude(rs.getDouble("longitude"));
                        coupon.setValid_from(rs.getDate("valid_from"));
                        coupon.setValid_until(rs.getDate("valid_until"));
                        coupon.calculate_distance(memberData.getLatitude(), memberData.getLongitude());
                        memberCouponDetails.add(coupon);
                    }
                    return memberCouponDetails;
                }
            });

        } catch (Exception exception) {
            logger.error("Exception occured in getMemberCouponDetails dao method call",exception);
            throw new Exception(exception);
        }

    }
    /*
    Helper method to validate if member id exists in the records
     */
    public void validateMemberId(Integer memberId) throws Exception {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("memberId",memberId);
        try {
            this.namedParameterJdbcTemplate.queryForObject(validateMemberId, params,Integer.class);
        }catch (Exception exception){
            throw new Exception("The following memberId is not present in records");
        }

    }

}
