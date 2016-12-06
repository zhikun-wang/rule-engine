package com.somecode.rule.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somecode.rule.api.IRule;

public abstract class AbstractBaseRule<T> implements IRule<T> {

	protected Logger Logger = LoggerFactory.getLogger("rule.message");


	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		@SuppressWarnings("unchecked")
		AbstractBaseRule<T> basicRule = (AbstractBaseRule<T>) o;

		if (this.getPriority() != basicRule.getPriority())
			return false;
		if (!getName().equals(basicRule.getName()))
			return false;
		return true;

	}

	@Override
	public String toString() {
		return "name == " + getName() + "; description == " + getDescription() + "; priority == " + getPriority();
	}

	@Override
	public int compareTo(final IRule<T> rule) {
		if (getPriority() < rule.getPriority()) {
			return -1;
		} else if (getPriority() > rule.getPriority()) {
			return 1;
		}
		return getName().compareTo(rule.getName());
	}

	protected boolean checkCtx(RuleContext<T> ctx) {
		return null != ctx && null != ctx.getBusinessData();
	}
}
