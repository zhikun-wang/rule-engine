package com.somecode.rule.core;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somecode.rule.api.IRule;
import com.somecode.rule.api.IRuleEngine;
import com.somecode.rule.api.IRuleListener;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DefaultRuleEngine implements IRuleEngine {

	Logger logger = LoggerFactory.getLogger("rule.message");

	private List<IRuleListener> ruleListeners;

	protected Set<IRule> rules;

	private RuleEngineConfig config;

	public DefaultRuleEngine(RuleEngineConfig config) {
		this.config = config;
		ruleListeners = new ArrayList<>();
		rules = new TreeSet<>();
	}

	@Override
	public RuleEngineConfig getConfig() {
		return config;
	}

	@Override
	public void registerRule(IRule rule) {
		rules.add(rule);
	}

	@Override
	public void unregisterRule(IRule rule) {
		rules.remove(rule);
	}

	@Override
	public Set<IRule> getRules() {
		return rules;
	}

	@Override
	public List<IRuleListener> getRuleListeners() {
		return ruleListeners;
	}

	private void fireBeforeAccept(IRule rule, RuleContext ctx) {
		for (IRuleListener ruleListener : ruleListeners) {
			ruleListener.beforeAccept(rule, ctx);
		}
	}

	private void fireAfterAccept(IRule rule, RuleContext ctx) {
		for (IRuleListener ruleListener : ruleListeners) {
			ruleListener.afterAccept(rule, ctx);
		}
	}

	private void fireBeforeExecute(IRule rule, RuleContext ctx) {
		for (IRuleListener ruleListener : ruleListeners) {
			ruleListener.beforeExecute(rule, ctx);
		}
	}

	private void fireOnSuccess(IRule rule, RuleContext ctx) {
		for (IRuleListener ruleListener : ruleListeners) {
			ruleListener.onSuccess(rule, ctx);
		}
	}

	private void fireOnFailure(IRule rule, RuleContext ctx, RuleException exception) {
		for (IRuleListener ruleListener : ruleListeners) {
			ruleListener.onFailure(rule, ctx, exception);
		}
	}

	@Override
	public void fireRules(RuleContext ctx) {
		logger.info("fire rule,size == " + rules.size());
		if (rules.isEmpty()) {
			return;
		}

		for (IRule rule : rules) {
			logger.info("rule name == " + rule);
			logger.info("ctx == " + ctx.isBreakFlag());
			if (ctx.isBreakFlag()) {
				logger.info("break by ctx");
				break;
			}
			// listener
			fireBeforeAccept(rule, ctx);
			boolean acceptFlag = rule.accept(ctx);
			logger.info("accept result == " + acceptFlag);
			fireAfterAccept(rule, ctx);
			if (acceptFlag) {
				try {
					fireBeforeExecute(rule, ctx);
					rule.execute(ctx);
					logger.info("rule execute end");
					fireOnSuccess(rule, ctx);
				} catch (RuleException re) {
					logger.warn("execute throw an error", re);
					fireOnFailure(rule, ctx, re);
				}
			}
		}
	}

	@Override
	public void clearRules() {
		rules.clear();
	}

}
