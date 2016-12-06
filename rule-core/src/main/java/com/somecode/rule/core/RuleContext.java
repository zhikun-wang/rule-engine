package com.somecode.rule.core;

import java.util.ArrayList;
import java.util.List;

import com.somecode.rule.api.IRule;

public class RuleContext<T> {

	private T businessData; // 业务数据

	private boolean breakFlag = false; // 打断规则链标志
	private List<IRule<T>> historyRules=new ArrayList<>();// 保存已经执行过的规则

	public T getBusinessData() {
		return businessData;
	}

	public void setBusinessData(T businessData) {
		this.businessData = businessData;
	}

	public boolean isBreakFlag() {
		return breakFlag;
	}

	public void setBreakFlag(boolean breakFlag) {
		this.breakFlag = breakFlag;
	}

	public List<IRule<T>> getHistoryRules() {
		return historyRules;
	}

	public void setHistoryRules(List<IRule<T>> historyRules) {
		this.historyRules = historyRules;
	}

}
