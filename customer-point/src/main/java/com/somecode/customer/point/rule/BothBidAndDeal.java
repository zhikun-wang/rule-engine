package com.somecode.customer.point.rule;

import java.util.List;

import com.somecode.customer.point.bean.CustomerOperator;
import com.somecode.customer.point.bean.CustomerOperatorResult;
import com.somecode.customer.point.bean.OperatorType;
import com.somecode.customer.point.service.ICustomerService;
import com.somecode.customer.point.service.impl.CustomerService;
import com.somecode.rule.core.AbstractBaseRule;
import com.somecode.rule.core.RuleContext;
import com.somecode.rule.core.RuleException;

public class BothBidAndDeal extends AbstractBaseRule<CustomerOperator> {

	@Override
	public String getDescription() {
		return "中标和成交不能同时累计";
	}

	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public boolean accept(RuleContext<CustomerOperator> ctx) {
		// both
		return checkCtx(ctx) && (ctx.getBusinessData().getOperatorType() == OperatorType.WinTheBid || ctx.getBusinessData().getOperatorType() == OperatorType.Deal);
	}

	ICustomerService customerService = new CustomerService();

	@Override
	public void execute(RuleContext<CustomerOperator> ctx) throws RuleException {
		Logger.info("执行操作" + getDescription() + "begin");
		// 保存数据
		// 检查是否冲突
		List<CustomerOperatorResult> results = customerService.getResultById(ctx.getBusinessData().getId());
		if (results != null) {
			for (CustomerOperatorResult result : results) {
				if (ctx.getBusinessData().getCarId() == result.getCarId() && accept(ctx)) {
					if (result.getOperatorType() == OperatorType.WinTheBid || result.getOperatorType() == OperatorType.Deal) {
						ctx.setBreakFlag(true);// 打断规则执行
						Logger.info("规则被打断，" + getDescription());
						break;
					}
				}
			}
		}
		Logger.info("执行操作" + getDescription() + "end");
	}

	@Override
	public String getName() {
		return this.getClass().getName();
	}

}
