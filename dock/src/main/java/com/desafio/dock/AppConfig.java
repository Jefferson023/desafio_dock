package com.desafio.dock;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.desafio.dock.converter.TerminalHtmlConverter;

/**
 * Necessary configuration to register the {@link TerminalHtmlConverter}
 * @see TerminalHtmlConverter
 * @author Jefferson
 *
 */
@Configuration
public class AppConfig implements WebMvcConfigurer{

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new TerminalHtmlConverter());
	}
}
