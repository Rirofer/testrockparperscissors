package test.ricardo.rpsgame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.mediatype.hal.forms.HalFormsConfiguration;
import org.springframework.hateoas.mediatype.hal.forms.HalFormsOptions;

import test.ricardo.rpsgame.adapters.game.in.http.dto.PlayRoundRandomCommandDTO;
import test.ricardo.rpsgame.core.game.domain.Move;

@Configuration
@EnableHypermediaSupport(type = HypermediaType.HAL_FORMS)
public class HateoasConfig {

	@Bean
	HalFormsConfiguration halFormsConfiguration() {
		return new HalFormsConfiguration().withOptions(PlayRoundRandomCommandDTO.class, "playerOneMove",
				metadata -> HalFormsOptions.inline(Move.values()));
	}
}
