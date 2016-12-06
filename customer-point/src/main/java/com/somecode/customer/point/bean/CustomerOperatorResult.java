package com.somecode.customer.point.bean;

import java.util.Date;

//流水记录
public class CustomerOperatorResult {

	private Long id;

	private Long carId;

	private OperatorType operatorType;

	private int adjustPoint;// 调整的分数，可以为负数

	private Date createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	public int getAdjustPoint() {
		return adjustPoint;
	}

	public void setAdjustPoint(int adjustPoint) {
		this.adjustPoint = adjustPoint;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}
}
