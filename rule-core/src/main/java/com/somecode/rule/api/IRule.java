package com.somecode.rule.api;

import com.somecode.rule.core.RuleContext;
import com.somecode.rule.core.RuleException;

public interface IRule<T> extends Comparable<IRule<T>> {

	String getName();

	String getDescription();

	// 优先级
	int getPriority();

	// 规则满足
	boolean accept(RuleContext<T> ctx);

	// 执行规则
	void execute(RuleContext<T> ctx) throws RuleException;

}
