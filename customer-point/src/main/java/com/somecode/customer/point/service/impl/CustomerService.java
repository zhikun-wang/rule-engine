package com.somecode.customer.point.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.somecode.customer.point.bean.CustomerOperatorResult;
import com.somecode.customer.point.service.ICustomerService;

public class CustomerService implements ICustomerService {

	static private Map<Long, List<CustomerOperatorResult>> allRecord = new HashMap<>();

	@Override
	public void adjustPoing(CustomerOperatorResult result) {
		if (!allRecord.containsKey(result.getId())) {
			allRecord.put(result.getId(), new ArrayList<CustomerOperatorResult>());
		}
		allRecord.get(result.getId()).add(result);
	}

	@Override
	public List<CustomerOperatorResult> getResultById(Long id) {
		return allRecord.get(id);
	}

}
