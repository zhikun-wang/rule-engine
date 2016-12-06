package com.somecode.rule.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.somecode.rule.core.RuleContext;
import com.somecode.rule.core.RuleEngineConfig;

public interface IRuleEngine {

	RuleEngineConfig getConfig();

	void registerRule(IRule rule);

	void unregisterRule(IRule rule);

	Set<IRule> getRules();

	List<IRuleListener> getRuleListeners();

	void fireRules(RuleContext ctx);

	void clearRules();
}
