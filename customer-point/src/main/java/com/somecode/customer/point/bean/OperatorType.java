package com.somecode.customer.point.bean;

public enum OperatorType {

	WinTheBid(1), Deal(2), BreachOfContract(3),Bidding(4);//所有的操作类型，不爱写了

	public int key;

	OperatorType(int k) {
		this.key = k;
	}

	public static OperatorType getOperator(int key) {
		for (OperatorType ot : OperatorType.values()) {
			if (ot.key == key)
				return ot;
		}
		return null;
	}

}
