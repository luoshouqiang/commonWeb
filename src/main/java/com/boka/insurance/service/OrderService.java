package com.boka.insurance.service;

import java.util.List;
import java.util.Map;

import com.boka.insurance.dto.Inquiry;
import com.boka.insurance.dto.InsuranceOrder;
import com.boka.insurance.dto.OrderQuery;

/**
 * 订单服务
 * @author luosq
 *
 */
public interface OrderService {

	/**
	 * 查询询价系统
	 * @param quiry
	 * @return
	 */
	public List<Map<String, Object>> selectInquiry(Inquiry quiry);

	/**
	 * 根据订单ID查询
	 * @param orderId
	 * @return
	 */
	public Map<String, Object> selectInquiry(long orderId);

	/**
	 * 处理询价
	 * @param status
	 * @param orderId
	 * @param remark
	 */
	public void processInquiry(String status, long orderId, String remark);

	/**
	 * 生成订单
	 * @param order
	 */
	public void createOrder(InsuranceOrder order);

	/**
	 * 查询要到期的用户
	 * @param period
	 * @param insurId
	 * @return
	 */
	public List<Map<String, Object>> queryMostDue(int period, long insurId);

	/**
	 * 查询行驶证信息
	 * @param vehicleId
	 * @return
	 */
	public Map<String, Object> queryDriverLisenceInfo(long vehicleId);

	/**
	 * 查询保险订单列表
	 * @param orderQuery
	 * @return
	 */
	public List<InsuranceOrder> queryInsuranceOrder(OrderQuery orderQuery);

	/**
	 * 查询对应保险的保险数据
	 * @param insurceId
	 * @return
	 */
	public List<Map<String, Object>> listItem(long insurceId);

}