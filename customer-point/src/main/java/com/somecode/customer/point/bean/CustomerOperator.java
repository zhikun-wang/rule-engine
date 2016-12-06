package com.somecode.customer.point.bean;

import java.util.Date;

//业务bean
public class CustomerOperator {

	// 操作类型
	private OperatorType operatorType;

	// 随便写的
	private String otherDate;
	private Long id;
	private Long carId;

	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	public String getOtherDate() {
		return otherDate;
	}

	public void setOtherDate(String otherDate) {
		this.otherDate = otherDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public CustomerOperatorResult getResult(int point) {
		CustomerOperatorResult result = new CustomerOperatorResult();
		result.setAdjustPoint(point);
		result.setCreateDate(new Date());
		result.setId(this.id);
		result.setCarId(this.carId);
		result.setOperatorType(this.operatorType);
		return result;
	}

}
