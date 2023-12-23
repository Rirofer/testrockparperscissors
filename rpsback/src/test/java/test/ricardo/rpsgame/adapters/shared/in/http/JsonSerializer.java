package test.ricardo.rpsgame.adapters.shared.in.http;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class JsonSerializer {

	private static ObjectMapper mapper;

	public static String toJson(Object obj) {
		try {
			return getMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new JsonSerializationException("JsonProcessingException serializing object", e);
		}
	}

	private static ObjectMapper getMapper() {
		if (Objects.isNull(mapper)) {
			mapper = new ObjectMapper();
			mapper = mapper.registerModule(new JavaTimeModule())
					.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		}
		return mapper;
	}

	private JsonSerializer() {

	}

}
