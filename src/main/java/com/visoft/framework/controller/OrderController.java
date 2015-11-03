package com.visoft.framework.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visoft.framework.dto.Inquiry;
import com.visoft.framework.dto.InsuranceOrder;
import com.visoft.framework.dto.OrderQuery;
import com.visoft.framework.exception.BusinessException;
import com.visoft.framework.exception.ResponseStatus;
import com.visoft.framework.exception.ResponseStatus.ErrorCode;
import com.visoft.framework.service.OrderService;

/**
 * 处理订单
 * @author luosq
 *
 */
@Controller
@RequestMapping("order")
public class OrderController {
private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 生成新的订单
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"},
			consumes={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseStatus createOrder(@RequestBody InsuranceOrder order) {
		ResponseStatus response=new ResponseStatus();
		try{
		orderService.createOrder(order);
		}catch(BusinessException ex){
			response=new ResponseStatus(ex);
		}catch(Exception ex){
			ex.printStackTrace();
			response=new ResponseStatus();
			response.setCode(ErrorCode.SYS_ERROR.getCode());
			response.setMsg("系统异常");
		}
		return response;
	}
	
	/**
	 * 列出当前保险公司的保险列表
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/listItem", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"}			)
	@ResponseBody
	public ResponseStatus<List<Map<String, Object>>> listItem(HttpSession session) {
		ResponseStatus<List<Map<String, Object>>> response=new ResponseStatus<List<Map<String, Object>>>();
		try{
			long insurceId=(Long)session.getAttribute("companyId");
			List<Map<String,Object>> itemList=orderService.listItem(insurceId);
			response.setData(itemList);
		}catch(BusinessException ex){
			response=new ResponseStatus(ex);
		}catch(Exception ex){
			ex.printStackTrace();
			response=new ResponseStatus();
			response.setCode(ErrorCode.SYS_ERROR.getCode());
			response.setMsg("系统异常");
		}
		return response;
	}
	
	
	
	/**
	 * 列出所有的询价请求
	 * @param quiry
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"},
			consumes={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseStatus<List<Map<String, Object>>> list(@RequestBody Inquiry quiry) {
		ResponseStatus<List<Map<String, Object>>> response=new ResponseStatus<List<Map<String, Object>>>();
		try{
			List<Map<String, Object>> result=orderService.selectInquiry(quiry);
			response.setData(result);
		}catch(BusinessException ex){
			response=new ResponseStatus<List<Map<String, Object>>>(ex);
		}catch(Exception ex){
			ex.printStackTrace();
			response=new ResponseStatus<List<Map<String, Object>>>();
			response.setCode(ErrorCode.SYS_ERROR.getCode());
			response.setMsg("系统异常");
		}
		return response;
	}
	
	
	/**
	 * 订单详细信息
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/orderDetail", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseStatus<Map<String, Object>> detail(Long orderId) {
		ResponseStatus<Map<String, Object>> response=new ResponseStatus<Map<String, Object>>();
		try{
			Map<String, Object> result=orderService.selectInquiry(orderId);
			response.setData(result);
		}catch(BusinessException ex){
			response=new ResponseStatus<Map<String, Object>>(ex);
		}catch(Exception ex){
			ex.printStackTrace();
			response=new ResponseStatus<Map<String, Object>>();
			response.setCode(ErrorCode.SYS_ERROR.getCode());
			response.setMsg("系统异常");
		}
		return response;
	}
	
	
	
	/**
	 * 查询保险快要到期的人员信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/insurceMostDuePerson", method = RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseStatus<List<Map<String, Object>>> insurceMostDue(HttpSession session) {
		ResponseStatus<List<Map<String, Object>>> response=new ResponseStatus<List<Map<String, Object>>>();
		try{
			long insurId=(Long)session.getAttribute("companyId");
			List<Map<String, Object>> result=orderService.queryMostDue(30,insurId);
			response.setData(result);
		}catch(BusinessException ex){
			response=new ResponseStatus<List<Map<String, Object>>>(ex);
		}catch(Exception ex){
			ex.printStackTrace();
			response=new ResponseStatus<List<Map<String, Object>>>();
			response.setCode(ErrorCode.SYS_ERROR.getCode());
			response.setMsg("系统异常");
		}
		return response;
	}
	
	/**
	 * 处理询价
	 * @param inquiryId
	 * @param status
	 * @param remark
	 * @return
	 */
	@RequestMapping(value = "/processInquiry", method = RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseStatus processInquiry(long inquiryId,String status,String remark) {
		ResponseStatus<List<Map<String, Object>>> response=new ResponseStatus<List<Map<String, Object>>>();
		try{
			orderService.processInquiry(status, inquiryId,remark);
		}catch(BusinessException ex){
			response=new ResponseStatus(ex);
		}catch(Exception ex){
			ex.printStackTrace();
			response=new ResponseStatus();
			response.setCode(ErrorCode.SYS_ERROR.getCode());
			response.setMsg("系统异常");
		}
		return response;
	}
	
	/**
	 * 查询行驶证的信息
	 * @param vehicleId
	 * @return
	 */
	@RequestMapping(value = "/driverLisence", method = RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseStatus<Map<String,Object>> driverLisence(Long vehicleId) {
		ResponseStatus<Map<String,Object>> response=new ResponseStatus<Map<String,Object>>();
		try{
			Map<String,Object> result=orderService.queryDriverLisenceInfo(vehicleId);
			response.setData(result);
		}catch(BusinessException ex){
			response=new ResponseStatus(ex);
		}catch(Exception ex){
			ex.printStackTrace();
			response=new ResponseStatus();
			response.setCode(ErrorCode.SYS_ERROR.getCode());
			response.setMsg("系统异常");
		}
		return response;
	}
	
	@RequestMapping(value = "/insuranceOrderList", method = RequestMethod.POST,
			consumes={"application/json;charset=UTF-8"},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseStatus<List<InsuranceOrder>> insuranceOrderList(@RequestBody OrderQuery orderQuery) {
		ResponseStatus<List<InsuranceOrder>> response=new ResponseStatus<List<InsuranceOrder>>();
		try{
			List<InsuranceOrder> result=orderService.queryInsuranceOrder(orderQuery);
			response.setData(result);
		}catch(BusinessException ex){
			response=new ResponseStatus(ex);
		}catch(Exception ex){
			ex.printStackTrace();
			response=new ResponseStatus();
			response.setCode(ErrorCode.SYS_ERROR.getCode());
			response.setMsg("系统异常");
		}
		return response;
	}
	@RequestMapping("/testForObj")
	@ResponseBody
	public String test(OrderQuery orderQuery) {
		System.out.println(orderQuery.getPlatNo());
		return "ok";
	}
	
	
	 @ExceptionHandler(Exception.class)  
	    public String runtimeExceptionHandler(Exception exception) {  
	        logger.error(exception.getLocalizedMessage());  
	        exception.printStackTrace();
	        return "exception";  
	    } 
	
}
