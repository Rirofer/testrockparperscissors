package test.ricardo.rpsgame.core.shared.domain;

import java.lang.reflect.Constructor;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Assertions {

	public static void assertNotNull(Object value, Class<? extends RuntimeException> exceptionClass, String msg) {
		if (Objects.isNull(value)) {
			throw newInstance(exceptionClass, msg);
		}
	}

	private static RuntimeException newInstance(Class<? extends RuntimeException> exceptionClass, String msg) {
		try {
			Constructor<? extends RuntimeException> constructor = exceptionClass.getConstructor(String.class);
			return constructor.newInstance(msg);
		} catch (Exception e) {
			log.error("Exception creating instance of exception " + exceptionClass.getSimpleName(), e);
			throw new RuntimeException(e);
		}
	}

	private Assertions() {

	}
}
