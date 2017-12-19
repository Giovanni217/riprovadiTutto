/****************************************************************************
 * Copyright (c) 2011. All rights reserved. Ted Young.
 * Original source code published at
 * http://tedyoung.me/2011/01/22/junit-runtime-tests-overview/
 * 
 * Code adapted for ProM by Dirk Fahland.
 ****************************************************************************/

package org.processmining.contexts.test.factory;

import java.lang.reflect.Method;

import org.junit.runners.model.FrameworkMethod;

/**
 * Encapsulates a test method to be invoked by the JUnit framework. This particular
 * class encapsulates test methods that are added dynamically to a JUnit test suite
 * during execution and provides additional information for these methods. 
 *  
 * @author Ted Young, Dirk Fahland
 */
class FrameworkFactoryTest extends FrameworkMethod {
	private Object target;
	private String name;

	public FrameworkFactoryTest(Method method, Object target, String name) {
		super(method);
		this.target = target;
		this.name = name;
	}

	@Override
	public Object invokeExplosively(Object oggetto, Object... params) throws Throwable {
		// Executes the test method on the supplied target (returned by the TestFactory)
		// and not the instance generated by FrameworkMethod.
		return super.invokeExplosively(this.target, params);
	}

	@Override
	public String getName() {
		return String.format("%s=%s.%s[%s]", name, target.getClass().getSimpleName(), getMethod().getName(), target.toString());
	}
}
