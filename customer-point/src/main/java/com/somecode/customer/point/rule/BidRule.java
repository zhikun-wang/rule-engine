package com.somecode.customer.point.rule;

import com.somecode.customer.point.bean.CustomerOperator;
import com.somecode.customer.point.bean.CustomerOperatorResult;
import com.somecode.customer.point.bean.OperatorType;
import com.somecode.customer.point.service.ICustomerService;
import com.somecode.customer.point.service.impl.CustomerService;
import com.somecode.rule.core.AbstractBaseRule;
import com.somecode.rule.core.RuleContext;
import com.somecode.rule.core.RuleException;

public class BidRule extends AbstractBaseRule<CustomerOperator> {

	ICustomerService customerService = new CustomerService();

	@Override
	public String getName() {
		return "Bid";
	}

	@Override
	public String getDescription() {
		return "中标增加2分";
	}

	@Override
	public int getPriority() {
		return 100;
	}

	@Override
	public boolean accept(RuleContext<CustomerOperator> ctx) {
		return checkCtx(ctx) && ctx.getBusinessData().getOperatorType() == OperatorType.WinTheBid;
	}

	@Override
	public void execute(RuleContext<CustomerOperator> ctx) throws RuleException {
		Logger.info("执行增加积分操作" + getDescription() + "begin");
		// 保存数据
		int point = 2;//
		CustomerOperatorResult result = ctx.getBusinessData().getResult(point);
		customerService.adjustPoing(result);
		Logger.info(getDescription() + "增加" + point);
		Logger.info("执行增加积分操作" + getDescription() + "end");
	}

}
