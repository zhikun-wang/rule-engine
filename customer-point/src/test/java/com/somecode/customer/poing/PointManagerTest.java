package com.somecode.customer.poing;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somecode.customer.point.bean.CustomerOperator;
import com.somecode.customer.point.bean.OperatorType;
import com.somecode.customer.point.rule.BidRule;
import com.somecode.customer.point.rule.BothBidAndDeal;
import com.somecode.customer.point.rule.DealRule;
import com.somecode.rule.api.IRuleEngine;
import com.somecode.rule.core.DefaultRuleEngine;
import com.somecode.rule.core.RuleContext;
import com.somecode.rule.core.RuleEngineConfig;

public class PointManagerTest {

	Logger logger = LoggerFactory.getLogger(PointManagerTest.class);

	IRuleEngine ruleEngine;

	@Before
	public void setUp() {
		logger.debug("开始装载规则");
		// 构造规则
		RuleEngineConfig config = new RuleEngineConfig();
		config.setName("测试规则");
		ruleEngine = new DefaultRuleEngine(config);
		ruleEngine.registerRule(new BidRule());
		ruleEngine.registerRule(new DealRule());
		ruleEngine.registerRule(new BothBidAndDeal());
		logger.debug("规则装载完毕");
	}

	@Test
	public void test() {
		deal(100L, 3L);
		bid(100L, 3L);
		deal(100L, 3L);

		bid(200L, 4L);
		deal(200L, 4L);
		bid(200L, 4L);
		deal(200L, 4L);

		bid(100L, 3L);
		deal(100L, 3L);

	}

	private void deal(Long id, Long carId) {
		CustomerOperator co = new CustomerOperator();
		co.setId(id);
		co.setCarId(carId);
		co.setOperatorType(OperatorType.Deal);
		RuleContext<CustomerOperator> ctx = new RuleContext<>();
		ctx.setBusinessData(co);
		ruleEngine.fireRules(ctx);
	};

	private void bid(Long id, Long carId) {
		CustomerOperator co = new CustomerOperator();
		co.setId(id);
		co.setCarId(carId);
		co.setOperatorType(OperatorType.WinTheBid);
		RuleContext<CustomerOperator> ctx = new RuleContext<>();
		ctx.setBusinessData(co);
		ruleEngine.fireRules(ctx);
	};

}
