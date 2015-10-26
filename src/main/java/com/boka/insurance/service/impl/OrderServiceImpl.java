package com.boka.insurance.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.boka.insurance.common.DateUtils;
import com.boka.insurance.common.InsuranceStatus;
import com.boka.insurance.common.OrderStatus;
import com.boka.insurance.common.QiniuConfig;
import com.boka.insurance.dto.Inquiry;
import com.boka.insurance.dto.InsurOrderDetail;
import com.boka.insurance.dto.InsurOrderItem;
import com.boka.insurance.dto.InsuranceOrder;
import com.boka.insurance.dto.OrderQuery;
import com.boka.insurance.exception.BusinessException;
import com.boka.insurance.exception.ResponseStatus;
import com.boka.insurance.exception.ResponseStatus.ErrorCode;
import com.boka.insurance.service.OrderService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.qiniu.util.Auth;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boka.insurance.service.impl.OrderService#selectInquiry(com.boka.insurance
	 * .dto.Inquiry)
	 */
	@Override
	public List<Map<String, Object>> selectInquiry(Inquiry quiry) {
		String querySql = "SELECT II.Insur_inquire_id, II.Insur_year,BA.user_name,BA.login_name AS mobile,BV.plat_no,BV.vehicle_id,"
				+ " BV.vin,IIR.begin_time,IIR.end_time,IIR.inquiry_status,IIR.inquiry_remark "
				+ " FROM insur_inquiry II,insur_inquiry_rel IIR,base_account BA,base_vehicle BV,base_insur BI"
				+ " WHERE IIR.Insur_id = BI.Insur_id AND II.Insur_inquire_id = IIR.Insur_inquire_id AND II.user_id = BA.user_id"
				+ " AND II.vehicle_id = BV.vehicle_id ";
		if (!Strings.isNullOrEmpty(quiry.getStartDate())) {
			querySql += " AND IIR.begin_time>='" + quiry.getStartDate() + "'";
		}
		if (!Strings.isNullOrEmpty(quiry.getEndDate())) {
			querySql += " AND IIR.end_time<='" + quiry.getEndDate() + "'";
		}
		if (!Strings.isNullOrEmpty(quiry.getInquiryStatus())) {
			querySql += " AND IIR.inquiry_status='" + quiry.getInquiryStatus() + "'";
		}
		if (!Strings.isNullOrEmpty(quiry.getVin())) {
			querySql += " AND BV.vin='" + quiry.getVin() + "'";
		}
		if (!Strings.isNullOrEmpty(quiry.getVehiclePlatNo())) {
			querySql += " AND BV.plat_no='" + quiry.getVehiclePlatNo() + "'";
		}

		return this.jdbcTemplate.queryForList(querySql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boka.insurance.service.impl.OrderService#selectInquiry(long)
	 */
	@Override
	public Map<String, Object> selectInquiry(long orderId) {
		String querySql = "SELECT II.Insur_year,BA.user_name,BA.login_name AS mobile,BV.plat_no,BV.vehicle_id,"
				+ " BV.vin,IIR.begin_time,IIR.end_time,IIR.inquiry_status,IIR.inquiry_remark "
				+ " FROM insur_inquiry II,insur_inquiry_rel IIR,base_account BA,base_vehicle BV,base_insur BI"
				+ " WHERE IIR.Insur_id = BI.Insur_id AND II.Insur_inquire_id = IIR.Insur_inquire_id AND II.user_id = BA.user_id"
				+ " AND II.vehicle_id = BV.vehicle_id AND II.Insur_inquire_id=?";
		return this.jdbcTemplate.queryForMap(querySql, orderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boka.insurance.service.impl.OrderService#processInquiry(java.lang
	 * .String, long, java.lang.String)
	 */
	@Override
	public void processInquiry(String status, long orderId, String remark) {
		InsuranceStatus insuranceStatus = checkStatus(status);
		int result = jdbcTemplate.update(
				"update insur_inquiry_rel set inquiry_status=?,inquiry_remark=? where insur_inquire_id=?",
				insuranceStatus.name(), remark, orderId);
		if (result == 0) {
			throw new BusinessException(ResponseStatus.ErrorCode.SYS_ERROR.getCode(), "没有执行成功,没有找到对应的订单，订单号:" + orderId);
		}
	}
	
	public List<Map<String,Object>> listItem(long insurceId) {
		String querySql="select insur_item_id,insur_id,insur_item_name,insur_item_force,insur_item_type,insur_item_unit"
				+ " from insur_order_item where insur_id=? order by insur_item_id";
		List<Map<String,Object>> itemList= jdbcTemplate.queryForList(querySql,insurceId);
		if(CollectionUtils.isEmpty(itemList)){
			throw new BusinessException(200, "请先联系客户人员录入贵公司数据");
		}
		String enumSql="select insur_item_id,enum_name,enum_value"
				+ " from insur_order_item_enum where insur_item_id=? order by enum_order ";
		
		for(Map<String,Object> map:itemList){
			long itemId=(Long)map.get("insur_item_id");
			List<Map<String,Object>> enumList=jdbcTemplate.queryForList(enumSql,itemId);
			map.put("enumList", enumList);
		}
		return itemList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boka.insurance.service.impl.OrderService#createOrder(com.boka.insurance
	 * .dto.InsuranceOrder)
	 */
	@Override
	public void createOrder(final InsuranceOrder order) {
		long id = createMainOrder(order);
		createOrderDetail(order, id);
		buildInquiryRel(order.getInsurId(), order.getInquriyId(), id, order.getOrderText());
	}

	private long createMainOrder(final InsuranceOrder order) {
		final int insurYear = DateUtils.getYear(new Date());
		String queryVehicleId="select plat_no,user_id from base_vehicle v,base_user_vehicle uv "
				+ " where v.vehicle_id = uv.vehicle_id and uv.vehicle_id=?";
		List<Map<String,Object>> vehicleList= jdbcTemplate.queryForList(queryVehicleId,order.getVehicleId());
		if(CollectionUtils.isEmpty(vehicleList)){
			throw new BusinessException(100,"没有找对车辆信息");
		}
		String platNo=(String)vehicleList.get(0).get("plat_no");
		long userId=(Long)vehicleList.get(0).get("user_id");
		order.setVehiclePlatNo(platNo);
		order.setUserId(userId);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			String sql = "insert into(insur_number,vehicle_id,user_id,insur_year,"
					+ "insur_amount,order_status,order_text,insur_id,insure_title,"
					+ "insure_begin_date,insure_end_date) values(?,?,?,?,?,?,?,?,?,?,?))";

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				int i = 1;
				PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(i++, order.getInsurNumber());
				ps.setLong(i++, order.getVehicleId());
				ps.setLong(i++, order.getUserId());
				ps.setInt(i++, insurYear);
				ps.setBigDecimal(i++, order.getInsurAmount());
				ps.setString(i++, OrderStatus.NOPAY.name());
				ps.setString(i++, order.getOrderText());
				ps.setLong(i++, order.getInsurId());
				ps.setString(i++, order.getInsureTitle());
				ps.setString(i++, order.getInsureBeginDate());
				ps.setString(i++, order.getInsureEndDate());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	private void createOrderDetail(final InsuranceOrder order, final long insurOrderId) {
		final List<InsurOrderDetail> detailList = order.getItemList();
		if (CollectionUtils.isEmpty(detailList)) {
			throw new BusinessException(ErrorCode.INPUT_ERROR.getCode(), "保险订单没有保险项目");
		}
		final String sql = "insert into insur_order_detail(insur_order_id,insur_item_id,amount,real_price) values(?,?,?,?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				InsurOrderDetail detail = detailList.get(i);
				ps.setLong(1, insurOrderId);
				ps.setLong(2, detail.getInsurItemId());
				ps.setBigDecimal(3, detail.getAmount());
				ps.setBigDecimal(4, detail.getRealPrice());
			}

			@Override
			public int getBatchSize() {
				return detailList.size();
			}
		});

	}

	private void buildInquiryRel(long insurId, long inquiryId, long orderId, String remark) {
		jdbcTemplate.update(
				"insert into insur_inquiry_rel(insur_inquire_id,insur_id,insur_order_id,inquiry_status,inquiry_remark)"
						+ " values(?,?,?,?,?)", insurId, inquiryId, orderId, InsuranceStatus.REPLY, remark);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boka.insurance.service.impl.OrderService#queryMostDue(int)
	 */
	@Override
	public List<Map<String, Object>> queryMostDue(int period, long insurId) {
		String querySql = "SELECT 	ba.user_name,	ba.login_name AS mobile,	bv.model,	bv.plat_no,	bv.vin,	bv.register_date"
				+ " FROM 	base_account ba,	base_vehicle bv,	base_user_vehicle buv ,area_insur_rel rel,base_user u,base_area_user au"
				+ " WHERE 	ba.user_id = buv.user_id AND bv.vehicle_id = buv.vehicle_id AND "
				+ " func_insurdate ( 	DATE (now()),	DATE (bv.register_date),?) "
				+ " AND rel.area_id=au.area_id AND u.user_id=ba.user_id AND au.user_id=u.user_id AND rel.insur_id=?";
		return this.jdbcTemplate.queryForList(querySql, period, insurId);
	}

	private InsuranceStatus checkStatus(String status) {
		InsuranceStatus insuranceStatus;
		try {
			insuranceStatus = InsuranceStatus.valueOf(status);
		} catch (Exception e) {
			throw new BusinessException(ResponseStatus.ErrorCode.INPUT_ERROR.getCode(), "没有这个状态值" + status);
		}

		return insuranceStatus;
	}

	@Override
	public Map<String, Object> queryDriverLisenceInfo(long vehicleId) {
		String querySql = "SELECT vehicle_license_url,vehicle_id,plat_no,model,swept_volume,vehicle_type,vehicle_owner,use_character,"
				+ " address,register_date,issue_date,VIN,engine_no from base_vehicle  " + " WHERE vehicle_id=? ";
		List<Map<String, Object>> result = this.jdbcTemplate.queryForList(querySql, vehicleId);
		if (CollectionUtils.isEmpty(result)) {
			return null;
		} else {
			for (Map<String, Object> map : result) {
				String baseUrl = (String) map.get("vehicle_license_url");
				Auth auth = Auth.create(QiniuConfig.ACCESS_KEY, QiniuConfig.SECRET_KEY);
				String downUrl = auth.privateDownloadUrl(baseUrl);
				map.put("vehicle_license_url", downUrl);
			}
		}
		return result.get(0);
	}

	@Override
	public List<InsuranceOrder> queryInsuranceOrder(OrderQuery orderQuery) {
		String queryOrderSql = "select insur_id,insur_order_id,insur_number,o.vehicle_id,user_id,insur_year,insur_amount,order_status,"
				+ "insure_begin_date,insure_end_date,insure_title,order_text,engine_no,plat_no "
				+ " from insur_order o,base_vehicle v where o.vehicle_id=v.vehicle_id AND plat_no=?";
		List<InsuranceOrder> orderList = jdbcTemplate.query(queryOrderSql, new RowMapper<InsuranceOrder>() {
			@Override
			public InsuranceOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
				InsuranceOrder order = new InsuranceOrder();
				order.setEngineNo(rs.getString("engine_no"));
				order.setInsurAmount(rs.getBigDecimal("insur_amount"));
				order.setInsureBeginDate(DateUtils.formatDate(rs.getDate("insure_begin_date"), DateUtils.DATE_FORMAT));
				order.setInsureEndDate(DateUtils.formatDate(rs.getDate("insure_end_date"), DateUtils.DATE_FORMAT));
				order.setInsureTitle(rs.getString("insure_title"));
				order.setInsurId(rs.getLong("insur_id"));
				order.setInsurNumber(rs.getString("insur_number"));
				order.setInsurOrderId(rs.getLong("insur_order_id"));
				order.setInsurYear(rs.getInt("insur_year"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setOrderText(rs.getString("order_text"));
				order.setUserId(rs.getLong("user_id"));
				order.setVehicleId(rs.getLong("vehicle_id"));
				order.setVehiclePlatNo(rs.getString("plat_no"));
				return order;
			}
		}, orderQuery.getPlatNo());

		if (CollectionUtils.isEmpty(orderList)) {
			return Collections.emptyList();
		}
		String detailSql = "select insur_order_id,amount,real_price,insur_item_name,insur_item_unit from "
				+ " insur_order_detail detail,insur_order_item item where detail.insur_item_id=item.insur_item_id"
				+ " and detail.insur_order_id=?";
		Map<Long, List<InsurOrderDetail>> detailMap = Maps.newHashMap();
		for (InsuranceOrder order : orderList) {

			List<InsurOrderDetail> detailList = this.jdbcTemplate.query(detailSql, new RowMapper<InsurOrderDetail>() {

				@Override
				public InsurOrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
					InsurOrderDetail detail = new InsurOrderDetail();
					detail.setAmount(rs.getBigDecimal("amount"));
					detail.setInsurOrderId(rs.getLong("insur_order_id"));
					detail.setRealPrice(rs.getBigDecimal("real_price"));
					InsurOrderItem item = new InsurOrderItem();
					item.setInsurItemName(rs.getString("insur_item_name"));
					item.setInsurItemUnit(rs.getString("insur_item_unit"));
					detail.setItem(item);
					return detail;
				}

			}, order.getInsurOrderId());
			detailMap.put(order.getInsurOrderId(), detailList);
		}
		for (InsuranceOrder order : orderList) {
			order.setItemList(detailMap.get(order.getInsurOrderId()));
		}
		return orderList;
	}
}
