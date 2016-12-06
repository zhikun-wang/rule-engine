package com.somecode.rule.api;

import com.somecode.rule.core.RuleContext;
import com.somecode.rule.core.RuleException;

public interface IRuleListener {

	<T> void beforeAccept(IRule<T> rule, RuleContext<T> ctx);

	<T> void afterAccept(IRule<T> rule, RuleContext<T> ctx);

	<T> void beforeExecute(IRule<T> rule, RuleContext<T> ctx);

	<T> void onSuccess(IRule<T> rule, RuleContext<T> ctx);

	<T> void onFailure(IRule<T> rule, RuleContext<T> ctx, RuleException exception);
}
