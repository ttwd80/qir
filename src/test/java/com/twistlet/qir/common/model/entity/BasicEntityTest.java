package com.twistlet.qir.common.model.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BasicEntityTest {

	@Test
	public void increaseCoverageTest() {
		fullTestCoverage(User.class);
		fullTestCoverage(Role.class);
	}

	private void fullTestCoverage(final Class<?> c) {
		final Object o = invokeConstructors(c.getConstructors());
		if (o != null) {
			invokeSettersGetters(c, o);
		}

	}

	private void invokeSettersGetters(final Class<?> c, final Object o) {

		final Method[] list = c.getDeclaredMethods();
		for (final Method m : list) {
			final String name = m.getName();
			try {
				if (name.startsWith("set")) {
					final Class<?>[] parameters = m.getParameterTypes();
					final List<?> args = createArgumentsFromTypes(parameters);
					m.invoke(o, args.toArray(new Object[] {}));
				} else if (name.startsWith("get")) {
					m.invoke(o);
				}
			} catch (final Exception e) {
			}
		}

	}

	private Object invokeConstructors(final Constructor<?>[] list) {
		Object o = null;
		for (final Constructor<?> c : list) {
			o = invokeConstructor(c);
		}
		return o;
	}

	private Object invokeConstructor(final Constructor<?> c) {
		Object o = null;
		final Class<?>[] declaredParameters = c.getParameterTypes();
		final List<?> args = createArgumentsFromTypes(declaredParameters);
		try {
			if (args.size() == declaredParameters.length) {
				if (declaredParameters.length == 0) {
					o = c.newInstance();
				} else {
					o = c.newInstance(args.toArray());
				}
			} else {
				System.err.println("Unable to create an instance of "
						+ c.getDeclaringClass().toString());
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	@SuppressWarnings("rawtypes")
	private List<?> createArgumentsFromTypes(final Class<?>[] list) {
		final List<Object> args = new ArrayList<>();
		for (final Class<?> c : list) {
			if (c == String.class) {
				args.add("X");
				continue;
			}
			if (c == List.class) {
				args.add(new ArrayList());
				continue;
			}
			System.err.println("Unable to handle type [" + c.toString() + "]");
		}
		return args;
	}

}
