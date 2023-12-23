package test.ricardo.rpsgame.adapters.shared.in.http;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.core.EmbeddedWrapper;
import org.springframework.hateoas.server.core.EmbeddedWrappers;

public final class CollectionResourceUtils {

	public static CollectionModel<EmbeddedWrapper> wrap(Collection<?> collection, String embeddedRelation) {
		EmbeddedWrappers wrappers = new EmbeddedWrappers(true);
		EmbeddedWrapper wrapper = wrappers.wrap(collection, LinkRelation.of(embeddedRelation));
		List<EmbeddedWrapper> elements = Collections.singletonList(wrapper);
		return CollectionModel.of(elements);
	}

	private CollectionResourceUtils() {

	}
}
