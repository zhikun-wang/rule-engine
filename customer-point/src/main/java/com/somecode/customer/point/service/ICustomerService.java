package com.somecode.customer.point.service;


import java.util.List;

import com.somecode.customer.point.bean.CustomerOperatorResult;

public interface ICustomerService {

	//
	public void adjustPoing(CustomerOperatorResult result);
	
	public List<CustomerOperatorResult> getResultById(Long id );

}
